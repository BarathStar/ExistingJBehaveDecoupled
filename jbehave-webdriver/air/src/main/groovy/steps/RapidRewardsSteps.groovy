package steps

import builders.AccountSpecificationBuilder
import builders.RelativeDateTimeParser
import com.swacorp.common.clcs.CertificateStatus
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.AirReservation
import domain.Companion
import domain.Passenger
import domain.RapidRewardsAccount
import fixture.stubs.DynaStubsIntegration
import fixtures.air.AccountSpecification
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import steps.conditional.ToggleGlobalNav
import steps.conditional.ToggleHomepage2

import java.security.SecureRandom
import org.joda.time.LocalDate
import state.AccountManagementData
import state.Flow
import state.PassengerData
import state.ScenarioState
import org.jbehave.core.annotations.*
import pages.*
import pages.elements.*
import util.*
import static fixture.stubs.DynaStubsIntegration.useDynaStubs;
import static Arrays.asList;

class RapidRewardsSteps {

    public static final String MODAL_SUBMIT_BUTTON_ID = "tripSearchModalSubmit"
    public static final int INSUFFICIENT_POINTS = 5000
    public static final int SUFFICIENT_POINTS = 10000
    public static final int ENOUGH_POINTS_TO_BOOK = 50000
    public static final String COMPANION_ZIP_CODE = "60637"

    SearchFlightsPage searchFlightsPage
    RapidRewardsOverviewPage rapidRewardsOverviewPage
    FlightSearchForm flightSearchForm
    AddRapidRewardsPage addRapidRewardsPage
    RapidRewardsAwardsPage rapidRewardsAwardsPage
    RapidRewardsPromotionalCertificatePage rapidRewardsPromotionalCertificatePage
    MyAccountPage myAccountPage
    ScenarioState scenarioState
    CreateAnAccountPage createAnAccountPage
    HomePage homePage
    ItineraryData itineraryData
    Data data
    Flow flow
    PassengerData passengerData
    MyRapidRewardsPage myRapidRewardsPage
    ResetPasswordData resetPasswordData
    ResetPasswordPage resetPasswordPage
    RapidRewardsEmailPage rapidRewardsEmailPage
    AccountManagementData accountManagementData
    AccountSteps accountSteps
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    CreateAccountConfirmationPage createAccountConfirmationPage
    HowToEarnPage howToEarnPage
    SummaryEmailListPage summaryEmailListPage
    ConfirmationEmailSubscriptionPage confirmationEmailSubscriptionPage
    AccountBarSteps accountBarSteps
    DestinationFinderPage destinationFinderPage
    LoginForm loginForm
    AccountBar accountBar
    NavigationSteps navigationSteps
    SelectFlightsPage selectFlightPage
    LowFareCalendarPage lowFareCalendarPage
    PromotionalCertificateDetailsPage promotionalCertificateDetailsPage
    AddRapidRewardsPNRPage addRapidRewardsPNRPage
    RRContactInformation rrContactInformation
    GlobalNavigationHeader globalNavigationHeader

    @Given("I am on the Rapid Rewards Overview page")
    @Alias("I am a Southwest User at Rapid Rewards Overview page")
    def viewRapidRewardsOverviewPage() {
        searchFlightsPage.open()
        rapidRewardsOverviewPage.openRapidRewardOverviewPage()
    }

    @Given("I am looged on my account on the Search Flights Page")
    def loginOnSearchFlightsPage() {
        searchFlightsPage.open()
        rapidRewardsOverviewPage.openRapidRewardMarketingPage()
    }

    @Given("I am on the Add Rapid Rewards Number Page")
    void openAddRapidRewardsNumberPage() {
        addRapidRewardsPage.open()
    }

    @Given("I am a customer on the Tier and More page")
    @Composite(steps = ["Given I am on the Rapid Rewards Overview page",
    "When I select the Tier and More tab",
    "Then I view alist status and benefits"])
    def onTierMorePage() {
        println("-------> On the Tier and More Page")
    }

    @Given("I am a Rapid Rewards Member not logged-in trying to reset my password with an \$tokenStatus token")
    @Alias("I am a Rapid Rewards Member not logged-in trying to reset my password with a \$tokenStatus token")
    def openPageWithToken(String tokenStatus){
        resetPasswordData.tokenStatus = tokenStatus.toUpperCase()

        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.prepareResetPassword(resetPasswordData)
        }else{
            resetPasswordData.token = new BigInteger(130, new SecureRandom()).toString(32)
        }

        resetPasswordPage.openWithToken(resetPasswordData.token)
        resetPasswordPage.verifyPage()
    }

    @Given("I am a Rapid Rewards Member without user name and security questions")
    def rrUserWithoutUserNameAndSecurityQuestion() {
        if (DynaStubsIntegration.useDynaStubs()){
            flow.setUser(new DynaStubsIntegration().generateRRUserWithoutUserNameAndSecurityQuestion())
            flow.isLoggedIn = true
            flow.isRapidRewards = true
        }
    }

    @Given("I am a Rapid Rewards Member with my account held")
    def loginAsRapidRewardsWithAccountHeld() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithAccountHeld())
        }
    }

    @Given("I am a Rapid Rewards Member with my account inactive")
    def createRapidRewardsMemberWithAccountInactive() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithAccountInactive())
            flow.hasGeneratedUser = true
        }
    }

    @Given("I am a Rapid Rewards Member")
    def iAmRapidRewardsMember() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            flow.hasGeneratedUser = true
        }else{
            createRRAccountProcess()
        }
    }

    @Given("I am a Rapid Rewards Member with multiple postal addresses")
    def iAmRapidRewardsMemberWithMultipleAddresses() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithMultipleAddresses())
            flow.hasGeneratedUser = true
        }else{
            createRRAccountProcess()
        }
    }

    @Given("I am a Rapid Rewards Member without a Credit Card")
    def iAmRapidRewardsMemberWithoutCreditCard() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        }else{
            createRRAccountProcess()
        }
    }

    @Given("I am a Rapid Rewards Member with a Chase Credit Card")
    def iAmRapidRewardsMemberWithChaseCard() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithChaseCreditCard())
        }
    }

    @Given("I am a Rapid Rewards Member without a dream trip setup")
    @Alias("I am a Rapid Rewards Member without Upcoming Trips in my account")
    def loginAsRapidRewardsWithoutDreamTripSetup() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        }
        else {
            createRRAccountProcess()
        }
    }

    @Given("I am a Rapid Rewards Member with a dream trip setup named \$dreamTripName")
    def createDreamTripWithName(String dreamTripName) {
        flow.dreamTripName = dreamTripName
    }

    @Given("I am a Rapid Rewards Member with an Altea dream trip setup named \$dreamTripName")
    def createAlteaDreamTripWithName(String dreamTripName) {
        flow.dreamTripName = dreamTripName
        flow.alteaDreamTrip = true
    }

    @Given("I have enough points to achieve the dream trip")
    def loginAsRapidRewardsWithEnoughPointsAndDreamTrip() {
        if (DynaStubsIntegration.useDynaStubs()) {
            if(flow.alteaDreamTrip) {
                flow.setRrUser(dynaStubsIntegration.generateRRUserWithPointsAndAlteaDreamTrip(SUFFICIENT_POINTS, flow.dreamTripName))
            } else {
                flow.setRrUser(dynaStubsIntegration.generateRRUserWithPointsAndDreamTrip(SUFFICIENT_POINTS, flow.dreamTripName))
            }

        }else{
            flow.setRrUser(data.getUser("promoUser"))
        }
    }

    @Given("I DO NOT have enough points to achieve the dream trip")
    def loginAsRapidRewardsWithoutEnoughPointsAndDreamTrip() {
        if (DynaStubsIntegration.useDynaStubs()) {
            if( flow.alteaDreamTrip) {
                flow.setRrUser(dynaStubsIntegration.generateRRUserWithPointsAndAlteaDreamTrip(INSUFFICIENT_POINTS, flow.dreamTripName))
            } else {
                flow.setRrUser(dynaStubsIntegration.generateRRUserWithPointsAndDreamTrip(INSUFFICIENT_POINTS, flow.dreamTripName))
            }
        }else{
            RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount(null, null, "RR_Customer_Account")
            flow.setRrUser(new RRUser(rrAccount))
            createAnAccountPage.open()
            accountSteps.fillAndConfirmNewRapidRewardsAccount(rrAccount)
            accountBarSteps.expandRapidRewardsDrawer()
            accountBarSteps.clickOnTrackYourTripLink()
            createADreamTripFromDefaultOriginCity("Austin", flow.dreamTripName)
            accountSteps.logOutIfLoggedIn()
        }
    }

    @Then("I should see the dream trip snapshot section")
    def verifyDreamTripsSnapshotSection() {
        myRapidRewardsPage.verifyDreamTripsSnapshotSection()
    }

    @Then("I should see the dream trip list section")
    def verifyDreamTripsListSection() {
        myRapidRewardsPage.verifyDreamTripsListSection()
    }

    @When("I click the Track Your Trip link")
    def clickTrackYourTripLink() {
        myRapidRewardsPage.clickTrackYourTripLink()
    }

    @When("I select Add Rapid Rewards Number from Quick Links")
    def selectAddRapidRewardsNumberFromQuickLinks() {
        myRapidRewardsPage.selectAddRapidRewardsNumberFromQuickLinks()
    }

    @Then("I view the Reward Flight Trackers page")
    def verifyOnRewardFlightTrackersPage() {
        myRapidRewardsPage.shouldBeInPage("dream-trips")
    }

    @When("I click the Flight Tracker Check Dates button")
    @Alias("I click GO button in dream trip details")
    def clickFlightTrackerCheckDatesButton() {
        if (DynaStubsIntegration.useDynaStubs() && !flow.alteaDreamTrip) {
            dynaStubsIntegration.prepareLowFareCalendar("DAL","HOU")
        }

        if (flow.alteaDreamTrip) {
            myRapidRewardsPage.clickFlightTrackerCheckDatesButtonAndDoNotVerify()
        } else {
            myRapidRewardsPage.clickFlightTrackerCheckDatesButton()
        }
    }

    private createADreamTripFromDefaultOriginCity(String destinationCity, String dreamTripName) {
        destinationFinderPage.fillDestinationCity(destinationCity)
        destinationFinderPage.clickOnTrackIt()
        destinationFinderPage.verifyPage()
        destinationFinderPage.fillTripNameField(dreamTripName)
        destinationFinderPage.clickOnSaveDreamTripButton()
        destinationFinderPage.verifyPage()
        destinationFinderPage.clickOnSuccessModalCloseButton()
        destinationFinderPage.verifyPage()
    }

    @Given("I have enough points to book a flight")
    def loginAsRapidRewardsWithEnoughPoints() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithPoints(ENOUGH_POINTS_TO_BOOK))
        }
    }

    @When("I view all Flight Trackers")
    def clickOnViewAllFlightTrackersLink() {
        myRapidRewardsPage.clickViewAllFlightTrackersLink()
    }

    @Given("I am a Rapid Rewards Member passenger")
    def loginAsRapidRewardsMember() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        }else{
            flow.setRrUser(data.getUser("withAwards"))
            searchFlightsPage.open()
            searchFlightsPage.verifyPage()
            loginForm.rapidRewardsMemberLoggedIn()
        }
        passengerData.addLoggedInPassenger()
    }

    @Given("I have \$count passengers traveling with me")
    def addPassengers(int count) {
        passengerData.addAdultPassenger(count)
    }

    @Given("I am flying with the following passenger: \$firstName \$lastName")
    def addFollowingPassenger(String firstName, String lastName) {
        passengerData.addAdultPassenger(firstName, "", lastName, "", false)
    }

    @Given("I am a Rapid Rewards Member with enough points to complete a purchase")
    def loginAsRapidRewardsMemberWithEnoughPoints() {
        if(DynaStubsIntegration.useDynaStubs()) {
            flow.rapidRewardsPoints = SUFFICIENT_POINTS
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        } else {
            flow.setRrUser(data.getUser("Alist"))
            myAccountPage.open()
            myAccountPage.loginWithUserNameAndPassword(flow.rrUser.number, flow.rrUser.RRPassword)
            myAccountPage.verifyPage()
        }
        passengerData.addLoggedInPassenger()
        flow.isLoggedIn = true
    }

    @Given("I am a Rapid Rewards Member passenger with freedom awards")
    def iAmRapidRewardsMemberWithFreedomAwards() {
        setPassengerWithFreedomAwards("withAwardsAndCredits")
    }

    @Given("I am a Rapid Rewards Member passenger with reissuable Freedom Awards in my account")
    def iAmRapidRewardsMemberWithReissuableFreedomAwards() {
        setPassengerWithFreedomAwards("withAwardsAndCredits")
    }

    private void setPassengerWithFreedomAwards(String user) {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            flow.getRrUser().setCertificateNumber(dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_FRDM"))
        } else {
            flow.setRrUser(data.getUser(user))
        }
        passengerData.addLoggedInPassenger()
    }

    @Given("I am a Rapid Rewards Member with both standard and freedom awards")
    def iAmRapidRewardsMemberWithBothStandardAndFreedomAwards() {
        if(DynaStubsIntegration.useDynaStubs()){
            iAmRapidRewardsMemberWithFreedomAwards()
            flow.getRrUser().setSecondCertificateNumber(dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND"))

        }
    }

    @When("I enter the new password and a matching password confirmation")
    public void enterPasswordsToChange() {
        resetPasswordData.setValues(new ResetPasswordDataBuilder(resetPasswordData)
            .withPasswordWithValidCharacters()
            .build())

        resetPasswordPage.fillInPasswords()
        resetPasswordPage.clickVisibleSubmit()
        resetPasswordPage.checkSomethingServed()
    }

    @When("I login as a \$rrUserType Rapid Rewards Account user")
    def addRapidRewardsUserToScenario(String rrUserType) {
        RRUser rrUser = data.getUser(rrUserType)
        passengerData.addAdultPassenger();
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setUser(new DynaStubsIntegration().createRRMember(rrUser, passengerData.getPassengers().get(0)))
            flow.isLoggedIn = true;
            flow.isRapidRewards = true;
        }
    }

    @When("I attempt to retrieve an Itinerary using an invalid PNR on the Add Rapid Rewards page")
    void enterInvalidPnrOnRapidRewardsPage() {
        addRapidRewardsPage.enterReservationInfo("ABC123", "Sam", "Leapfrog")
    }

    @When("I enter my PNR on the add Rapid Rewards number page")
    void viewAddRapidRewardsNumberPage() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        addRapidRewardsPage.verifyPage()
        addRapidRewardsPage.verifyTitle()
        addRapidRewardsPage.verifyLookupConfirmationLink()
        addRapidRewardsPage.enterReservationInfo(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When("I enter my PNR and invalid Passenger Name on the add Rapid Rewards number page")
    void viewAddRapidRewardsNumberPageInvalidPassengerName() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        addRapidRewardsPage.enterReservationInfo(airReservation.adultPnr, "IncorrectFirstName", "IncorrctLastName")
    }

    @When("I enter invalid PNR on the add Rapid Rewards number page")
    void viewAddRapidRewardsNumberPageInvalidPNR() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        addRapidRewardsPage.enterReservationInfo("ABC123", passenger.firstName, passenger.lastName)
    }

    @When("I enter my Rapid Rewards Number on the add Rapid Rewards Number page")
    void addRapidRewardsNumber() {
        addRapidRewardsPage.enterRapidRewardsNumber()
    }

    @When("I enter my Rapid Rewards Number \$rrNumber on the add Rapid Rewards Number page at Passenger \$passengerNumber")
    void addRapidRewardsNumberAtIndex(String rrNumber, int passengerNumber) {
        int fieldIndex = (passengerNumber > 0) ? passengerNumber - 1 : 0
        addRapidRewardsPage.enterRapidRewardsNumber(rrNumber, fieldIndex)
    }


    @When("I enter an invalid Rapid Rewards Number on the add Rapid Rewards Number page")
    void addRapidRewardsNumberInvalid() {
        addRapidRewardsPage.enterRapidRewardsNumber("500010545")
    }

    @When("I enter an valid AirTran Loyalty number on the add Rapid Rewards Number page")
    void addValidAirTranLoyaltyNumber() {
        addRapidRewardsPage.enterRapidRewardsNumber("500010545")
    }

    @When("I click the How to Earn tab")
    def clickHowToEarnTab() {
        rapidRewardsOverviewPage.selectHowToEarnPage()
    }

    @When("I select the A List Preferred link")
    def selectAlistPreferred() {
        rapidRewardsOverviewPage.selectAlistPreferred()
    }

    @When("I select the Tier and More tab")
    def selectTiersAndMore() {
        rapidRewardsOverviewPage.selectTiersAndMore()
    }

    @When("I select the companion pass")
    def selectCompanionPass() {
        rapidRewardsOverviewPage.selectCompanionPass()
    }

    @When("I select fare in dollars")
    void chooseFaresInDollars() {
        flightSearchForm.chooseFaresInDollars()
    }

    @When("I search flights using a promotional certificate")
    void searchFlightsUsingPromotionalCertificate() {
        rapidRewardsPromotionalCertificatePage.selectPromotionalCertificate()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I attempt to search flights using a promotional certificate")
    void searchFlightsUsingPromotionalCertificates() {
        rapidRewardsPromotionalCertificatePage.selectPromotionalCertificate()
    }

    @When("I search flights using my awards")
    void searchFlightsUsingAwards() {
        myAccountPage.viewCreditsAndAwards()
        searchAFlightFromCertificatesPage()
    }

    @When( "I search Airtran flights using my awards" )
    void iSearchAirTranFlightsUsingMyAwards() {
        itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup()).withDepartingCarrier("AirTranOnly").withDepartingFlightFareClass("StandardAward").withArrivalCarrier("AirTranOnly").withOneWayStations("AirTranOnly").build())
        itineraryData.departureStation = "ATL"
        itineraryData.arrivalStation = "BWI"
        myAccountPage.viewCreditsAndAwards()
        searchAFlightFromCertificatesPage()
    }

    def void searchAFlightFromCertificatesPage() {
        rapidRewardsAwardsPage.openSearchFlightsModalUsingAwards()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I add a companion ticket to my trip")
    void addCompanionTicket() {
        myAccountPage.addCompanionToPurchase()
    }

    @When("I view the Snapshot page")
    void viewSnapshotPage() {
        myAccountPage.clickMyAccount()
    }

    @When("I access the My Rapid Rewards account page")
    void openMyRewardsAccountPage() {
        viewSnapshotPage()
        myAccountPage.clickMyRapidRewardsLink()
        myRapidRewardsPage.verifyPage()
    }

    @When("I view the My Travel page")
    void viewTravelPage() {
        myAccountPage.clickMyTravelLink()
        myAccountPage.verifyPage()
    }

    @When("I view the Tier Status page")
    public void openTierStatusDetails() {
        myAccountPage.clickMyRapidRewardsLink()
        myAccountPage.viewTierStatus()
        myAccountPage.verifyPage()
    }

    @When("I enter the new password and a matching password confirmation with invalid characters")
    public void enterPasswordsWithInvalidCharacters() {
        resetPasswordData.setValues(new ResetPasswordDataBuilder(resetPasswordData)
            .withPasswordWithInvalidCharacters().build())
        resetPasswordPage.fillInPasswords()
        resetPasswordPage.clickVisibleSubmit()
        resetPasswordPage.checkSomethingServed()
    }

    @When("I enter the new password and a matching password confirmation containing a blacklisted password \$blacklistPassword")
    public void enterBlacklistedPassword(String blacklistPassword) {
            resetPasswordData.setValues(new ResetPasswordDataBuilder(resetPasswordData)
                .withPasswordBlackListed(blacklistPassword).build())
            resetPasswordPage.fillInPasswords()
            resetPasswordPage.clickVisibleSubmit()
            resetPasswordPage.checkSomethingServed()
        }

    @When("I enter the new password and a password confirmation that do not match")
    public void enterPasswordsThatDoNotMatch() {
        resetPasswordData.setValues(new ResetPasswordDataBuilder(resetPasswordData)
            .withNotMatchingPasswords().build())
        resetPasswordPage.fillInPasswords()
        resetPasswordPage.clickVisibleSubmit()
        resetPasswordPage.checkSomethingServed()
    }

    @Given("I am a Rapid Rewards Member not logged-in located on the Rapid Rewards E-Mail page")
    def openRapidRewardsEmail(){
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        } else {
            flow.setRrUser(data.getUser("withAwards"))
        }
        rapidRewardsEmailPage.open()
        rapidRewardsEmailPage.verifyPage()
    }

    @When("I enter my Rapid Rewards credentials on the Rapid Rewards E-Mail Page")
    def enterMyRrCredentialsOnTheRrEmailPage(){
        rapidRewardsEmailPage.fillInNameAndAccountNumber()
    }

    @When("I subscribe to \$mailingList by entering the e-mail address \$email")
    def subscribeToMailingList(String mailingList, String email){
        accountManagementData.setAccountData(mailingList, email)
        rapidRewardsEmailPage.fillInFirstEmailAddress()
        rapidRewardsEmailPage.selectFirstBoxRapidRewardsReport()
        rapidRewardsEmailPage.clickSuscribeButton()
        confirmationEmailSubscriptionPage.verifyPage()
    }

    @When("I subscribe to Rapid Rewards E-mail Update and The Rapid Rewards Report by entering the email address \$email")
    def enterEmailAndSuscribeToRrEmailAndRrReportAndSubmit(String email){
        accountManagementData.emailAddress = email
        rapidRewardsEmailPage.fillInFirstEmailAddress()
        rapidRewardsEmailPage.selectFirstBoxRapidRewardsEmailUpdate()
        rapidRewardsEmailPage.selectFirstBoxRapidRewardsReport()
        rapidRewardsEmailPage.clickSuscribeButton()
        rapidRewardsEmailPage.verifyPage()
    }

    @When("I subscribe to Rapid Rewards E-mail Update and The Rapid Rewards Report by entering the first email address \$email")
    def enterFirstEmailAndSuscribeToRrEmailAndRrReport(String email){
        accountManagementData.emailAddress = email
        rapidRewardsEmailPage.fillInFirstEmailAddress()
        rapidRewardsEmailPage.selectFirstBoxRapidRewardsEmailUpdate()
        rapidRewardsEmailPage.selectFirstBoxRapidRewardsReport()
    }

    @When("I subscribe to The Rapid Rewards Report by entering my second email address \$email")
    def enterSecondEmailAndSuscribeToRrReport(String email){
        accountManagementData.secondEmailAddress = email
        rapidRewardsEmailPage.fillInSecondEmailAddress()
        rapidRewardsEmailPage.selectSecondBoxRapidRewardsReport()
    }

    @When("I subscribe to Rapid Rewards E-mail Update by entering my third email address \$email")
    def enterThirdEmailAndSuscribeToRrEmailAndSubmit(String email){
        accountManagementData.thirdEmailAddress = email
        rapidRewardsEmailPage.fillInThirdEmailAddress()
        rapidRewardsEmailPage.selectThirdBoxRapidRewardsEmailUpdate()
        rapidRewardsEmailPage.clickSuscribeButton()
        summaryEmailListPage.verifyPage()
    }

    @Given("I am a Southwest User at How to earn page")
    def viewHowToEarnPage() {
        howToEarnPage.open()
    }

    @When("I access Tiers and More page")
    def clickOnTiersAndMoreLink() {
        howToEarnPage.clickOnTiersAndMoreLink()
    }

    @When("I select A-List Preferred card")
    def clickOnAListPreferredCard() {
        howToEarnPage.clickOnAListPreferredCard()
    }

    @When("I select Companion Pass card")
    def clickOnACompanionPassCard() {
        howToEarnPage.clickOnACompanionPassCard()
    }

    @Given("I am a Rapid Rewards Member logged in from Login page")
    def rrUserLoggedFromLoginPage() {
        myAccountPage.open()
    }

    @Given("I am a Customer not logged in")
    def createRapidRewardsCustomer() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(new DynaStubsIntegration().generateCustomer())
        } else {
            RRUser customer = accountSteps.generateCustomer()
            flow.setRrUser(customer)
        }
    }

    @When("I access Search Flight page")
    def openSearchFlightPage() {
        searchFlightsPage.open()
    }

    @Given("I am a Rapid Rewards Member with Companion Pass status achieved")
    @Alias("I am a Rapid Rewards Member with Companion Pass status achieved without a designated Companion Pass")
    def rrUserWithCompanionPassAchieved(){
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithCompanionPassAchieved())
        }else{
            flow.setRrUser(data.getUser("promoUser"))
        }
    }

    @Given("I have designated my Companion Pass")
    def rrHaveDesignatedCompanionPass(){
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.designateCompanionPass(flow.getRrUser().getRRNumber())
        }
    }

    @Given("I am a Rapid Rewards Member with Promotions and no Promotional Certificates")
    def createRRMemberWithPromotions() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            dynaStubsIntegration.generatePromotion(flow.rrUser.number)
        }
    }

    @Given("I am a senior age verified Rapid Rewards Member")
    def createRRMemberAsVerifiedSenior() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser( createRRMemberSenior(true))
        }
    }

    RRUser createRRMemberSenior(boolean verified, String firstName = "Pat") {
        DynaStubsIntegration dyna = new DynaStubsIntegration()
        LocalDate dateOfBirth = new LocalDate().minusYears(71)
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withDateOfBirth(dateOfBirth)
                                                                .withFirstName(firstName)
                                                                .withHasAgeVerified(verified).build();
        RRUser rrUser = dyna.createCustomer(accountSpecification)
        return rrUser
    }

    @Given("I am a senior age non-verified Rapid Rewards Member")
    def createRRMemberAsNonVerifiedSenior() {
        if (DynaStubsIntegration.useDynaStubs()) {
             flow.setRrUser( createRRMemberSenior(false))
        }
    }


    @Given("I am a Rapid Rewards Member with only one active promotion in my account")
    def createRRMemberWithOneActivePromotion() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            dynaStubsIntegration.generatePromotionalProduct(flow.rrUser.number)
        }
    }

    @Given("I am a Rapid Rewards Member with two active promotional certificates in my account")
    def createRRMemberWithTwoPromotionalCertificates() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            dynaStubsIntegration.generatePromotionalProductWithTwoPromotionalCertificates(flow.rrUser.number)
        }
    }

    @Given("I am a Rapid Rewards Member with two active X and V promotional certificates in my account")
    def createRRMemberWithTwoActiveXAndVPromotionalCertificates() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setUser(dynaStubsIntegration.generateRRUserWithPromotionalCertificates())
            List<String> certificateNumbers = new ArrayList<String>();
            certificateNumbers.add(0,flow.getUser().getCertificateNumber());
            certificateNumbers.add(1,flow.getUser().getSecondCertificateNumber());
            dynaStubsIntegration.generatePromotionalProductWithTwoXAndVPromotionalCertificates(flow.getUser().getRRNumber(), certificateNumbers)
        }
    }

    @Given("I am a Rapid Rewards Member with active X and V promotional certificates \$certificateNumbers with expiration dates \$expirationDates in my account")
    def createRRMemberWithActiveXAndVPromotionalCertificates(String certificateNumbers, String expirationDates) {
        if (useDynaStubs()) {
            flow.setUser(dynaStubsIntegration.generateRRUser())
            List<String> certNumbersList = asList(certificateNumbers.split(","))
            List<LocalDateTime> expirationDatesList = transformExpirationDatesToList(expirationDates)
            dynaStubsIntegration.generatePromotionalProductWithXAndVPromotionalCertificates(flow.getUser().getRRNumber(), certNumbersList, expirationDatesList)
        }
    }

    @Given("I am a Rapid Rewards Member with X and V promotional certificates \$certificateNumbers with expiration dates \$expirationDates and certificateStatuses \$certificateStatuses in my account")
    def createRRMemberWithXAndVPromotionalCertificatesWithExpirationDatesAndStatuses(String certificateNumbers, String expirationDates, String certificateStatuses) {
        if (useDynaStubs()) {
            flow.user = dynaStubsIntegration.generateRRUser()
            List<String> certNumbersList = asList(certificateNumbers.split(","))
            List<LocalDateTime> expirationDatesList = transformExpirationDatesToList(expirationDates)
            List<CertificateStatus> certificateStatusList = transformCertificateStatusesToList(certificateStatuses)
            dynaStubsIntegration.generatePromotionalProductWithXAndVPromotionalCertificates(flow.getUser().getRRNumber(), certNumbersList, expirationDatesList, certificateStatusList)
        }
    }

    private List<LocalDateTime> transformExpirationDatesToList(String expirationDates) {
        List<LocalDateTime> expirationDatesList = new ArrayList<LocalDateTime>();
        for (String value : expirationDates.split(",")) {
            RelativeDateTimeParser relativeDateTimeParser = new RelativeDateTimeParser(DateTime.now());
            DateTime date = new DateTime().plusMonths(6)
            if (relativeDateTimeParser.doesItMatchesWithRelativeTimeParserFormat(value)) {
                date = relativeDateTimeParser.parseRelativeDateTime(value)
            }
            expirationDatesList.add(new LocalDateTime(date.getMillis()))
        }
        return expirationDatesList;
    }

    private List<CertificateStatus> transformCertificateStatusesToList(String certificateStatuses) {
        List<CertificateStatus> certificateStatusList = new ArrayList<CertificateStatus>();
        for (String value : certificateStatuses.split(",")) {
            certificateStatusList.add(CertificateStatus.valueOf(value))
        }
        return certificateStatusList;
    }

    @Given("I am a Rapid Rewards Member with two active X and V promotional certificates with unavailable date on itinerary departure date")
    def createRRMemberWithTwoActiveXAndVPromotionalCertificatesWithUnAvailableDates() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setUser(dynaStubsIntegration.generateRRUserWithPromotionalCertificates())
            List<String> certificateNumbers = new ArrayList<String>();
            certificateNumbers.add(0,flow.getUser().getCertificateNumber());
            certificateNumbers.add(1,flow.getUser().getSecondCertificateNumber());
            dynaStubsIntegration.generatePromotionalProductWithTwoXAndVPromotionalCertificatesAndUnAvailableDates(flow.getUser().getRRNumber(), certificateNumbers, itineraryData.departureDate)
        }
    }

    @Given("I am a Rapid Rewards Member with two active X and V promotional certificates with unavailable date on itinerary departure date plus two")
    def createRRMemberWithTwoActiveXAndVPromotionalCertificatesWithUnAvailableDatesOnDepartDatePlusTwo() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setUser(dynaStubsIntegration.generateRRUserWithPromotionalCertificates())
            List<String> certificateNumbers = new ArrayList<String>();
            certificateNumbers.add(0,flow.getUser().getCertificateNumber());
            certificateNumbers.add(1,flow.getUser().getSecondCertificateNumber());
            dynaStubsIntegration.generatePromotionalProductWithTwoXAndVPromotionalCertificatesAndUnAvailableDates(flow.getUser().getRRNumber(), certificateNumbers, itineraryData.departureDate.plus(2))
        }
    }

    @When("CLCS Promotions service is unavailable but CLCS Certificates service is available")
    def setCLCSPromotionsServiceAsUnavailable() {
        if(DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updatePromotionService(flow.rrUser.number, false);
        }
    }

    @Given("I am a Rapid Rewards Member with Promotions and Promotional Certificates")
    @Alias("I am a Rapid Rewards Member with services available for Promotions and Promotional Certificates")
    def createRRMemberWithPromotionsAndPromotionsCertificates() {
        if (DynaStubsIntegration.useDynaStubs()) {
            createRRMemberWithPromotions()
            dynaStubsIntegration.generatePromotionalProduct(flow.rrUser.number)
        }
    }

    @Given("CLCS Promotions and Certificates services are unavailable")
    def setCLCSPromotionsAndCertificatesAsUnavailable() {
        if (DynaStubsIntegration.useDynaStubs()) {
            setCLCSPromotionsServiceAsUnavailable()
            setCLCSCertificatesServiceAsUnavailable()
        }

    }

    @Given("CLCS Certificates service is unavailable but CLCS Promotions service is available")
    def setCLCSCertificatesServiceAsUnavailable() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updatePromotionalProduct(flow.rrUser.number, false)
        }
    }

    @Given("I am a Rapid Rewards Member with multiple active promotions in my account")
    def createRRMemberWithMultipleActivePromotion() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            dynaStubsIntegration.generatePromotionalProduct(flow.rrUser.number)
            dynaStubsIntegration.generatePromotionalProduct(flow.rrUser.number)
        }
    }

    @Given("I am a Rapid Rewards Member with Awards From Transition Program in my account")
    def iAmRapidRewardsMemberWithTrasitionalAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_BUYUP")
        } else {
            flow.setRrUser(data.getUser("withAwards"))
            accountSteps.loggedInAsRapidRewardsMemberWithUserNameAndPassword()
            accountSteps.purchasePoints()
            myAccountPage.viewCreditsAndAwards()
            myAccountPage.verifyPage()
            myAccountPage.clickOnCreateAwardButton()
            myAccountPage.verifyPage()
            myAccountPage.clickCompleteConversionSubmitButton()
            myAccountPage.verifyPage()
            accountBar.logOut()
        }
    }

    @Given("I am a Rapid Rewards Member passenger with reissuable Standard Awards in my account")
    @Alias("I am a Rapid Rewards Member with a Transitional Award")
    def iAmRapidRewardsMemberWithStandardAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            final String certificateNumber = dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND")
            flow.getRrUser().setCertificateNumber(certificateNumber)
			passengerData.addLoggedInPassenger()
        } else {
            flow.setRrUser(data.getUser("withAwardsAndCredits"))
        }
    }

    @Given("I am a Rapid Rewards Member with reissuable Standard Awards with my account Inactive")
    def iAmRapidRewardsInactiveMemberWithStandardAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithAccountInactive())
            final String certificateNumber = dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND")
            flow.getRrUser().setCertificateNumber(certificateNumber)
            passengerData.addLoggedInPassenger()
        } else {
            flow.setRrUser(data.getUser("withAwardsAndCredits"))
        }
    }

    @Given( "I am a Rapid Rewards member with both a Freedom and Standard Award" )
    def iAmARapidRewardsMemberWithBothAFreedomAndStandardAward() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            final String freedomCertificateNumber = dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_FRDM")
            flow.getRrUser().setCertificateNumber(freedomCertificateNumber)
            final String standardCertificatNumber = dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND")
            flow.getRrUser().setCertificateNumber(standardCertificatNumber)
			passengerData.addLoggedInPassenger()
        }
    }

    @When("I select to see the Freedom Award Terms and Conditions")
    def clickFreedomAwardTermsAndConditions() {
        myRapidRewardsPage.clickFreedomAwardTermsAndConditions()
    }

    @When("I select to see the Standard Award Terms and Conditions")
    def clickStandardAwardTermsAndConditions() {
        myRapidRewardsPage.clickStandardAwardTermsAndConditions()
    }

    @When("I select to see the Transition Award Terms and Conditions")
    def clickTransitionAwardTermsAndConditions() {
        myRapidRewardsPage.clickTransitionAwardTermsAndConditions()
    }

    private createRRAccountProcess() {
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount(null, null, "RR_Customer_Account")
        flow.setRrUser(new RRUser(rrAccount))
        createAnAccountPage.open()
        accountSteps.fillAndConfirmNewRapidRewardsAccount(rrAccount)
        accountSteps.logOutIfLoggedIn()
    }

    @Given("I am logged in and on the snapshot page")
    def logInAndViewSnapshotPage() {
        navigationSteps.openSwaHomepage()

        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.openLoginDropdown()
            globalNavigationHeader.logInAsRapidRewardsMember(flow.rrUser)
            globalNavigationHeader.openNewHeaderMenuRapidRewards()
            globalNavigationHeader.clickOnMyAccountLink()
        } else {
            accountBar.logInToRapidRewards(flow.rrUser.number, flow.rrUser.RRPassword)
            myAccountPage.clickMyAccount()
        }
    }

    @Given("I am logged in and on My Rapid Rewards page")
    def logInAndViewMyRRPage() {
        navigationSteps.openSwaHomepage()

        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.openLoginDropdown()
            globalNavigationHeader.logInAsRapidRewardsMember(flow.rrUser)
            globalNavigationHeader.openNewHeaderMenuRapidRewards()
            globalNavigationHeader.clickOnMyAccountLink()
            myAccountPage.clickMyRapidRewardsLink()
        } else {
            accountBar.logInToRapidRewards(flow.rrUser.number, flow.rrUser.RRPassword)
            myAccountPage.clickMyAccount()
            myAccountPage.clickMyRapidRewardsLink()
        }

        myRapidRewardsPage.verifyPage()
    }

    @Given("I am logged in and on the home page")
    def logInOnHomePage() {
        navigationSteps.openSwaHomepage()
        accountBar.logInToRapidRewards(flow.rrUser.number, flow.rrUser.RRPassword)
    }

    @When("I enter the Companion pass Information accessing through My Rapid Rewards Tab")
    def completeCompanionInfo() {
        myAccountPage.viewCompanionPassDetails()
        myAccountPage.verifyPage()

        flow.companion = new CompanionBuilder().build()
        myAccountPage.fillForm(flow.companion)
        myAccountPage.submitForm()
        myAccountPage.verifyPage()
    }

    def completeCompanionInfoAsSelf() {
        myAccountPage.viewCompanionPassDetails()
        myAccountPage.verifyPage()

        flow.companion = createCompanionMatchingMember(flow.rrUser)
        myAccountPage.fillForm(flow.companion)
        myAccountPage.submitForm()
    }

    Companion createCompanionMatchingMember(RRUser rrUser) {
        Companion companion = new CompanionBuilder().build()
        companion.firstName = rrUser.firstName
        companion.lastName = rrUser.lastName

        def dateOfBirth = new DateTime(rrUser.RRDateOfBirth)
        companion.dayOfBirth = dateOfBirth.dayOfMonth
        companion.monthOfBirth = dateOfBirth.monthOfYear
        companion.yearOfBirth = dateOfBirth.year
        companion.zipCode = COMPANION_ZIP_CODE

        return companion
    }

    @When("I enter the Companion pass Information through My Snapshot Tab")
    def completeCompanionInfoThroughSnapshotPage() {
        myAccountPage.unfoldCompanionSection()
        completeCompanionInfo()
    }

    @When("I enter the Companion pass Information which matches my info through My Snapshot Tab")
    def completeCompanionInfoAsSelfThroughSnapshotPage() {
        myAccountPage.unfoldCompanionSection()
        completeCompanionInfoAsSelf()
    }


    @Then("I view the message for airtran disclaimer")
    def pageMessage() {
        addRapidRewardsPage.verifyAirtranDisclaimerMessage()
    }

    @Given("I log in as a Rapid Rewards Member with both standard and freedom awards")
    void loggedInAsRapidRewardsMemberWithBothStandardAndFreedomAwards() {
        if(DynaStubsIntegration.useDynaStubs()){
            setPassengerWithFreedomAwards("withAwardsAndCredits")
            flow.getRrUser().setSecondCertificateNumber(dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND"))

        }
        navigationSteps.openSwaHomepage()
        accountBar.logInToRapidRewards(flow.rrUser.number, flow.rrUser.RRPassword)
    }

    @Given("I am on the Certificates Page")
    void loggedInAsRapidRewardsMemberOnTheCertificatesPage() {
        myAccountPage.clickMyAccount()
        myAccountPage.viewCreditsAndAwards()
    }

    @Given("I attempt to book an awards flight")
    @When("I attempt to book an awards flight")
    void openStandardAwardsAvailabilityWidget() {
        searchAFlightFromCertificatesPage()
    }

    @When("I attempt to book a promo flight")
    void fillStandardAwardsAvailabilityWidget() {
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @Then("I should see multiple fare class columns from select flight page")
    def observeMultipleFareClassColumnsInSelectFlightPage() {
        selectFlightPage.verifyMultipleFareClassColumnsDisplayed()
    }

    @Then("I should see single promo certificate fare class column from select flight page")
    def observeSinglePromoCertificateFareClassColumnsInSelectFlightPage() {
        selectFlightPage.verifySinglePromoCertFareClassColumnDisplayed()
    }

    @Then("I should see flights available in the select flight page in promotional certificate column")
    def observeAvailableFlightsInPromoCertificateColumn() {
        selectFlightPage.verifyFlightsPresent()
    }

    @Then("I should see flights unAvailable in the select flight page in promotional certificate column And the results grayed out")
    def observeFlightsInPromoCertificateColumnGrayedOut() {
        selectFlightPage.verifyUnavailableToRedeemPromotion()
    }

    @Given("I am a Rapid Rewards Member with two active X or V promotional certificates in my account")
    def ensureTwoActiveXOrVPromoCertsCreatedAfterOctober12012() {
        if ( DynaStubsIntegration.useDynaStubs() ) {
            flow.setUser(dynaStubsIntegration.generateRRUserWithPromotionalCertificates())
            List<String> certificateNumbers = new ArrayList<String>();
            certificateNumbers.add(0,flow.getUser().getCertificateNumber());
            certificateNumbers.add(1,flow.getUser().getSecondCertificateNumber());
            dynaStubsIntegration.generatePromotionalProductWithTwoXAndVPromotionalCertificates(flow.getUser().getRRNumber(), certificateNumbers)
        }
    }

    @Then("I see the promotion name link")
    def verifyViewPromotionalLink() {
        rapidRewardsPromotionalCertificatePage.verifyPromotionNameLink()
    }

    @When("I click on the promotion name link")
    def clickOnPromotionalLink() {
        rapidRewardsPromotionalCertificatePage.clickOnPromotionNameLink()
    }

    @Then("I see Promotional Certificate Details page")
    def verifyIamOnThePromotionalCertificateDetailsPage() {
        promotionalCertificateDetailsPage.verifyPage()
    }

    @When("I click on the book a trip button on promotion details page")
    void bookATripOnPromotionalProductDetailsPage() {
        promotionalCertificateDetailsPage.selectPromotionalCertificate()
    }

    @Then("I see dream trip link for \$dreamTripName")
    def verifyDreamTripLink(String dreamTripName){
        myRapidRewardsPage.verifyDreamTripLink(dreamTripName)
    }
    @When("I click dream trip link \$dreamTripName")
    def clickDreamTripNameLink(String dreamTripName){
        myRapidRewardsPage.clickDreamTripNameLink(dreamTripName)
    }

    @Then("I should see dream trip details page for \$dreamTripName")
    def verifyDreamTripDetailsPage(String dreamTripName){
        myRapidRewardsPage.verifyDreamTripDetailsPageFor(dreamTripName)
    }

    @Given("I am a Rapid Rewards Member with an existing Upcoming AirTran Trip")
    def iAmARapidRewardsMemberWithAnExistingUpcomingAirTranTrip() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
            passengerData.addPassengers([PassengerType.ADULT])
            itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup()).withDepartingCarrier("AirTran").withArrivalCarrier("AirTran").withRoundTripStations("AirTran", "AirTran").build())
            dynaStubsIntegration.addUpcomingTrip( flow.rrUser.RRNumber, itineraryData )
        }
    }

    @When( "I log in as a Rapid Rewards Member" )
    def iLogInAsARapidRewardsMember() {
        loginForm.openRapidRewardsLoginPage()
        loginForm.fillInLoginInformationAndSubmit( flow.rrUser.number, flow.rrUser.password )
        flow.isLoggedIn = true
    }

    @When( "I click on my Upcoming AirTran Trip" )
    def iClickOnMyUpcomingAirTranTrip() {
        myAccountPage.viewTripDetails()
    }

    @Then("I see a Account Expiration countdown in month(s) & days(s) based on rules")
    @Aliases(values = ["I see a Account is Expired based on rules",
    "I see a Account Expiration countdown in month(s) & days(s) based on rules in recent reward activity section of my RR page"])
    def verifyAccountExpirationDate(){
        myRapidRewardsPage.shouldSeeAccountExpirationDate("Expiration Date")
    }

    @Then("I see a Account is Expired in Rewards Activity Details page")
    def verifyAccountExpiredTextInRewardsActivityPage(){
        myRapidRewardsPage.shouldSeeAccountExpirationDate("Account is Expired")
    }

    @Given("I have had Qualifying Activity in the last 30 days")
    def qualifyingActivityInLast30Days() {
        if (DynaStubsIntegration.useDynaStubs()) {
            (new DynaStubsIntegration()).createRewardActivity(flow.getRrUser())
        }
    }

    @Then("I should see activities on the Recent Rewards Activity")
    def verifyRecentRewardsActivity() {
        myRapidRewardsPage.shouldSeeRecentRewardsActivities()
    }

    @Then("Last Activity Date and Expiration Countdown are not displayed")
    def verifyLastActivityDateAndExpirationCountdownAreNotDisplayed() {
        myRapidRewardsPage.shouldNotSeeLastActivityDateOrExpirationCountdown()
    }

    @When("I attempt to book an awards flight with flexible dates")
    def attemptToSearchFlightWithAwardsWithFlexibleDates() {
        rapidRewardsAwardsPage.openSearchFlightsModalUsingAwards()
        rapidRewardsAwardsPage.checkFlexibleDatesCheckbox()
        flightSearchForm.fillInFlightSearchInfoAndClickForFlexibleDates(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I attempt to book an awards flight from \$departure to \$destination")
    def attemptToSearchFlightWithAwardsWithFlexibleDates(@Named("departure") String departureStation, @Named("destination") String arrivalStation) {
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
        rapidRewardsAwardsPage.openSearchFlightsModalUsingAwards()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I click Add Rapid Rewards Number Link in the Add Rapid Rewards Number confirmation page")
    void clickAddRapidRewardsNumberLink() {
        addRapidRewardsPage.clickAddRapidRewardsNumberLink()
    }

    @When("I click the button Standard Awards Availability")
    def verifyStandardAwardsAvailability() {
        myRapidRewardsPage.clickStandardAwardsAvailability()
    }

    @When("I click on the wcm image for enrollment")
    def clickOnEnrollImage() {
        addRapidRewardsPage.clickRREnrollImage()
    }

    @When("I enter a Rapid Rewards Number")
    void addPassengerRapidRewardsNumber() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        addRapidRewardsPNRPage.verifyPage()
        addRapidRewardsPNRPage.verifyTitle()
        addRapidRewardsPNRPage.verifyConfirmationNumber(airReservation.adultPnr)
        addRapidRewardsPNRPage.verifyPassengerName(passenger.firstName, passenger.lastName)
        flow.isRapidRewards = true
        addRapidRewardsPNRPage.enterRapidRewardsNumber(rrContactInformation.accountNumber)
    }

    @When("I click view Old Awards and Credits")
    void clickViewCreditsAndAwards() {
        myAccountPage.viewCreditsAndAwards()
    }

    @Then("I see the Awards Details Page with Standard Awards Availability Link")
    def verifyStandardAvailabilityButton() {
        rapidRewardsAwardsPage.checkStandardAwardsAvailabilityLink()
    }

    @Given("I am a Rapid Rewards Member with status pending")
    def createRRMemberWithStatusPending() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setUser(dynaStubsIntegration.generateRRUserWithStatusPending())
        }
    }
}