package steps

import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import domain.Passenger
import org.jbehave.core.annotations.*
import pages.*
import pages.elements.AccountBar
import pages.elements.ForgotRRNumberModal
import state.Flow
import state.RRUserQueue
import state.ScenarioState
import steps.conditional.ToggleHomepage2

class AccountBarSteps {

    AccountBar accountBar
    Data data
    ForgotRRNumberModal forgotRRNumberModal
    SearchFlightsPage searchFlightsPage
    DestinationFinderPage destinationFinderPage
    UpcomingTripsPage upcomingTripsPage
    MyAccountPage myAccountPage
    HomePage homePage
    Flow flow
    ScenarioState scenarioState
    FlightStatusPage flightStatusPage

    @When("I log in from the account sidebar at the Search Flight page")
    def logInFromAccountSidebar() {
        searchFlightsPage.open()
        accountBar.logInToRapidRewards(flow.getUser().getRRNumber(), flow.getUser().getRRPassword())
        searchFlightsPage.verifyPage()
    }

    @Given("I click my account link")
    def clickMyAccountLink(){
        myAccountPage.clickMyAccount()
    }

    @Given("I log in from the account sidebar at and click my account link")
    def loginFromAccountSidebarAndClickSnapshotLink(){
        logInFromAccountSidebar()
        clickMyAccountLink()
    }

    @When("I insert the correct \$usernameOrAccountNumber and password on the account bar")
    def loginUsingAccountBar(String usernameOrAccountNumber) {
        String loginCredential = accountBar.returnRapidRewardsUserNameOrAccountNumber(usernameOrAccountNumber)
        accountBar.logInToRapidRewards(loginCredential, data.getUser("goodUser").getRRPassword())
    }

    @When("I insert a wrong combination of \$usernameOrAccountNumber and password on the account bar")
    def enterWrongUsernameAndPasswordOnAccountBar(String usernameOrAccNumber) {
        accountBar.enterWrongRapidRewardsPasswordOnAccountBar(usernameOrAccNumber)
    }

    @When("I insert empty username and a password on the account bar")
    def enterEmptyUsernameAndPasswordOnAccountBar() {
        accountBar.logInToRapidRewards("", data.getUser("goodUser").getRRPassword())
    }

    @When("I insert an username and empty password on the account bar")
    def enterUsernameAndEmptyPasswordOnAccountBar() {
        accountBar.logInToRapidRewards(data.getUser("goodUser").getRRAccountName(), "")
    }

    @When("I click on login without entering any credentials on account bar")
    def tryToLogInWithNoCredentialsOnAccountBar() {
        accountBar.logInToRapidRewards("", "")
    }

    @When("I click on the \"\$linkName\" link on the login section on the account bar")
    def clickOnLogInSectionLink(String linkName) {
        accountBar.getLoginSectionLink(linkName).click()
    }

    @When("I click on the \"\$linkName\" on the login page")
    def clickOnCreateMyAccountLink(String linkName){
        accountBar.getLoginSectionMyAccountLink(linkName).click()
    }

    @When("I check the option \"Remember Me\"")
    @Given("I check the option \"Remember Me\"")
    def selectRememberMeOnAccountBar() {
        accountBar.selectOnRememberMe()
    }

    @When("I click on the \"Lookup Rapid Rewards Account #\" link in the \"Need help logging in?\" modal")
    def clickOnLookupRRAccountLink() {
        forgotRRNumberModal.getLookupRRAccountLink().click()
    }

    @When("I enter a space into the username field")
    def tryToLoginUsingLoginWithSpace() {
        accountBar.logInToRapidRewards("abc def", data.getUser("goodUser").getRRPassword())
    }

    @When("I enter an invalid character into the password field")
    def tryToLoginUsingPasswordWithInvalidChar() {
        accountBar.logInToRapidRewards(data.getUser("goodUser").getRRAccountName(), "!@##% %%")
    }

    @When("I enter the incorrect username and password for 5 times in a row")
    def tryToLogInWithWrongPasswordFiveTimes() {
        accountBar.tryToLogInWithWrongPasswordFiveTimes()
    }

    @When("I expand My Rapid Rewards section")
    def expandRapidRewardsDrawer() {
        accountBar.expandRapidRewardsDrawer()
    }

    @When("I click the option to track my trip from the account sidebar")
    def clickOnTrackYourTripLink() {
        accountBar.clickOnTrackYourTripLink()
        destinationFinderPage.verifyPage()
    }

    @When("I access the Upcoming Trips through the Companion Pass section on the account sidebar")
    @Alias("I access the Companion Pass Status page through the Companion Pass section on the account sidebar")
    def clickOnButtonFromCompanionPassSection(){
        accountBar.clickOnCompanionButton()
        upcomingTripsPage.verifyPage()
    }

    @When("I request to see my promotions from the account bar")
    def clickPromotionalAwardViewNow() {
        accountBar.clickOnPromotionalAwardViewNowButton()
        myAccountPage.verifyPage()
    }

    @When("I click Enroll Now on the Account Bar")
    def gotToEnrollPage(){
        accountBar.clickOnEnrollNow()
    }

    @When("I select Optional Travel Charges beneath the account bar")
    def clickOnOptionalTravelChargesLink() {
        if(ToggleHomepage2.isOn()){
            homePage.clickOnBaggageAndOptionalFees()
        }else{
            accountBar.clickOnOptionalTravelCharges()
        }
    }

    @When("I click on the Check In Section of the Account Bar or Check In Tab on Home Page")
    def clickOnCheckInSectionOfAccountBar() {
        if(ToggleHomepage2.isOn()){
            homePage.clickOnCheckInTab()
        }else{
            accountBar.clickOnCheckIn()
        }
    }

    @When("I click on the Change Flight Section of the Account Bar or Change Flight Link on Home Page")
    def clickOnChangeFlightSectionOrLink() {
        if(ToggleHomepage2.isOn()) {
            homePage.clickOnChangeReservationLink()
        } else {
            accountBar.clickOnChangeFlight()
        }
    }

    @When("I click on the Check Flight Status Section of the Account Bar or Flight Status Tab on Home Page")
    def clickOnCheckFlightStatusSectionOfAccountBar() {
        if(ToggleHomepage2.isOn()) {
            homePage.clickOnCheckFlightStatusTab()
        } else {
            accountBar.clickOnCheckFlightStatus()
        }
    }

    @When("I enter the confirmation number, first name, last name, and click check in")
    def enterConfirmationNumberFirstNameLastNameAndClickCheckIn() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr

        if(ToggleHomepage2.isOn()){
            homePage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
        }else{
            accountBar.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
        }
    }

    @When("I enter the origin, destination, date, and click continue")
    def enterOriginDestinationDateAndClickCheckFlightStatus() {
        if(ToggleHomepage2.isOn()){
            homePage.enterOriginDestinationDateAndClickCheckFlightStatus("DAL", "HOU")
        }else{
            accountBar.enterOriginDestinationDateAndClickCheckFlightStatus("DAL", "HOU")
        }
    }

    @Then("I verify I am on the Flight Status Page")
    def verifyFlightStatusPage() {
        flightStatusPage.verifyPage()
    }

    @AfterStory
    def resetUserQueue() {
        int count = 5
        if (flow.isRapidRewardsPointsPurchaseOnly && null != flow.rrUser) {
            while (!RRUserQueue.putUser(flow.rrUser) && count-- > 0){
                RRUserQueue.putUser(flow.rrUser)
            }
            if (count <= 0 ){
                println ("RR user account queue was not reset !!!!")
            }
        }
    }
}
