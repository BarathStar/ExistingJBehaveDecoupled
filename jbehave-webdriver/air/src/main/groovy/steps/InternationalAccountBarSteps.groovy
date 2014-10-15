package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import pages.AccountLogInPage
import com.swacorp.dotcom.webscenarios.air.RRUser
import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.Data
import state.Flow
import pages.MyAccountPage

class InternationalAccountBarSteps {


    public static String[] PARMS =[]
    public static final String LOGIN_ENTRY_POINT = "loginEntryPoint=INTERNATIONAL"
    public static final String RETURN_URL = "returnUrl=https://localhost:7001/html/WDSAccountBarSimulator.html"
    private static final String ERROR_STATUS_SUCCESS = "errorStatus=SUCCESS"
    private static final String ENC = "ENC"
    private static final String ENCT = "ENCT"
    private static final String ERROR_STATUS_FAILURE = "errorStatus=FAILURE"

    AccountLogInPage loginPage
    MyAccountPage myAccountPage
    Data data
    Flow flow

    @Given("I am an RR user on the International site")
    void setupParamsRR() {
        setupParams("goodUser")
    }

    @Given("I am an invalid user on the International site")
    void setupParamsInvalid() {
        setupParams("invalid", false)
    }

    private def setupParams(String userType, Boolean createDynaStubsRR=true) {
        RRUser rrUser = data.getUser(userType)
        flow.setRrUser(rrUser)

        if (DynaStubsIntegration.useDynaStubs()) {
            if (createDynaStubsRR)
                new DynaStubsIntegration().createRRMember(rrUser)
            else {
                rrUser.setNumber("123456789")
            }
        }

        PARMS = [
                "credential=" + rrUser.getRRNumber(),
                "password=" + rrUser.getPassword(),
                LOGIN_ENTRY_POINT,
                RETURN_URL
        ]
    }

    @When("I send an international login request")
    void sendRequest() {
        loginPage.openWithArgs(PARMS)
    }


    @Then("I should be logged in and receive the proper response")
    void verifyURLSuccess() {
        loginPage.getCurrentUrl().shouldContain ERROR_STATUS_SUCCESS
        loginPage.getCurrentUrl().shouldContain ENC
        loginPage.getCurrentUrl().shouldContain ENCT
    }

    @Then("I should fail login and receive a failure response")
    void verifyFailure() {
        loginPage.getCurrentUrl().shouldContain ERROR_STATUS_FAILURE
        loginPage.getCurrentUrl().shouldNotContain ENC
        loginPage.getCurrentUrl().shouldNotContain ENCT
    }

    @Then("I should enter the My Account page successfully")
    void enterMyAccountPage() {
        PARMS = [
            parseParam(loginPage.getCurrentUrl(), ENC),
            parseParam(loginPage.getCurrentUrl(), ENCT),
            LOGIN_ENTRY_POINT,
            RETURN_URL
            ]
        myAccountPage.openWithArgs(PARMS, myAccountPage.getRelativePath())
        myAccountPage.verifyPage()
        myAccountPage.verifyMyAccountSnapshotPageTitle()
    }

    private def parseParam(String url, String param) {
        url.substring(url.indexOf(param), url.indexOf("&", url.indexOf(param)))
    }
}