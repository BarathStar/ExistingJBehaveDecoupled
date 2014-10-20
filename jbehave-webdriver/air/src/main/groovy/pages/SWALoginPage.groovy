package pages


import pages.elements.GlobalNavigationHeader
import state.RRUserQueue
import org.jbehave.web.selenium.WebDriverProvider
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import fixture.stubs.DynaStubsIntegration
import org.openqa.selenium.By
import state.Flow


class SWALoginPage extends BasePage {
    Data data
    Flow flow
    private static final By ACCOUNT_NUMBER = By.id("accountNumber")
    private static final By ACCOUNT_PASSWORD = By.id("accountPassword")
    private static final By LOGIN_SUBMIT_BUTTON = By.id("loginSubmitButton")
    GlobalNavigationHeader globalNavigationHeader


    public SWALoginPage(WebDriverProvider driverProvider) {
        super(driverProvider, "");

    }

    def open() {
        super.open()
        checkSomethingServed()
        checkNoOops()
    }

    def login(String member_type) {
        def memberType = member_type.replaceAll('\\s+', '').replaceAll('\\-', '').toLowerCase()
        switch (memberType) {
            case "anonymous":
            case "anonymoususer":
                break

            case "customer":
                loginAsCustomerUser()
                break

            case "rrmember":
                loginAsRRmember()
                break
            case "preferredname":
                logInAsAMemberWithAPreferredName()
                break

            case "alist":
            case "alistmember":
                logInAsAListMember()
                break

            case "rrcreditcard":
                logRRwithCC()
                break
            case "rrseniorcreditcard":
                logRRSeniorWithCC()
                break
            case "rrminorpoints":
                logRRMinorPoints()
                break
            case "ageverifiedsenior":
                loginIntoAgeVerifiedAccount(memberType)
                break
            case "ageverifiedcustomer":
                loginIntoAccount(memberType)
                break
            default:
                throw new RuntimeException("Invalid Login, Enter valid Member Type")
        }
    }
    def logRRwithCC() {
        def user = createRRUser("RRCreditCard")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }
    def logRRMinorPoints(){
        def user = createRRUser("RRMinorPoints")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }
    def logRRSeniorWithCC() {
        def user = createRRUser("RRSeniorCreditCard")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }
    def logInAsAListMember() {
        def user = createRRUser("Alist")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
            globalNavigationHeader.verifyTierStatusFlagInHotState("A-List")
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
            accountBar.verifyTierIsDisplayed("A-List");
        }
    }

    def loginAsRRmember() {
        if (flow.rrUser == null){
            flow.setUser(createRRUser())
        }
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(flow.getUser())
        } else {
            accountBar.logInToRapidRewards(flow.getUser().getRRNumber(),flow.getUser().getRRPassword())
        }
    }
    def logInAsAMemberWithAPreferredName() {
        def user = createRRUser("preferredName")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }


    def loginAsCustomerUser() {
        def user = createRRUser("customer")
        if (ToggleHomepage2.isOn() && globalNavigationHeader.isHomePage()) {
            logIntoCustomerAccount(user)
        } else {
            logInAsCustomer(user.getAccountName(), user.getPassword())
        }
    }

    RRUser createRRUser(String userType = "goodUser") {
        userType = (userType.equalsIgnoreCase("valid")) ? "goodUser" : userType
        assert [userType] ==
                ["ageVerifiedSenior","noAPlusCredits", "preferredName", "noDebitRapidRewardsAccount", "awardsOnly", "creditsOnly", "pointsOnly", "AlistPreferred", "Alist", "invalid", "promoUser", "goodUser", "goodUserWithNoActivityInLast30Days", "accountExpiredUser", "promoCertUser", "customer", "RRCreditCard", "RRSeniorCreditCard", "RRMinorPoints"].grep(userType)
        RRUser user = flow.isRapidRewardsPointsPurchaseOnly ? RRUserQueue.pollUser() : data.getUser(userType)

        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().createRRMember(user)
        }

        flow.setUser(user)
        userType = (userType.equalsIgnoreCase("goodUser") || userType.equalsIgnoreCase("RRCreditCard") || userType.equalsIgnoreCase("RRSeniorCreditCard") || userType.equalsIgnoreCase("RRMinorPoints") || userType.equalsIgnoreCase("preferredName")) ? "Rapid Rewards Member" : "A-List"
       // flow.ser.setUserLoggedInRapidRewardsAccountType(userType)
        return user
    }

    private void logIntoCustomerAccount(RRUser user) {
        logIntoAccount(user)
        flow.isCustomer = true
    }

    def logInAsCustomer(String username, String password) {
        logInAsAUser(username, password)
        flow.isCustomer = true
        flow.isLoggedIn = true
    }

    def logInAsAUser(String username, String password) {
        fillIn(ACCOUNT_NUMBER, username)
        fillIn(ACCOUNT_PASSWORD, password)
        waitForElement(LOGIN_SUBMIT_BUTTON).click()
    }

    private void logIntoAccount(RRUser user) {
        globalNavigationHeader.logIntoAccount(user)
        flow.isLoggedIn = true
    }

    private void loginIntoAgeVerifiedAccount(String userType) {
        RRUser user= null
        if (userType.contains("senior")) {
            user = createRRUser("ageVerifiedSenior")
            logIntoRRAccount(user)
        } else if (userType.contains("customer")) {
            user = createRRUser("customer")
            logIntoCustomerAccount(user)
        }

    }

    private void logIntoRRAccount(RRUser user) {
        logIntoAccount(user)
        flow.isRapidRewards = true
        flow.userLoggedInRapidRewardsNumber = flow.getUser().getUserName()
    }
}