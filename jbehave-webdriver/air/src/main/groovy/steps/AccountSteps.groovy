package steps

import builders.AccountSpecificationBuilder
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.AirReservation
import domain.Companion
import domain.RapidRewardsAccount
import fixture.stubs.DynaStubsIntegration
import fixtures.air.AccountSpecification
import org.jbehave.core.model.ExamplesTable
import org.openqa.selenium.By
import state.AccountManagementData
import state.Flow
import state.PassengerData
import state.RRUserQueue
import state.ScenarioState
import org.jbehave.core.annotations.*
import pages.*
import pages.elements.*
import steps.conditional.ToggleGlobalNav
import steps.conditional.ToggleHomepage2
import util.*

import static org.junit.Assert.fail
import static util.Locators.ELEMENT_IDS

class AccountSteps {
    public static final String MODAL_SUBMIT_BUTTON_ID = "tripSearchModalSubmit"

    AccountBar accountBar
    MyAccountPage myAccountPage
    DestinationFinderPage destinationFinderPage
    CreateAnAccountPage createAnAccountPage
    SearchFlightsPage searchFlightsPage
    PurchasePage purchasePage
    CreditCardSubForm creditCardSubForm
    BookATripModal bookATripModal
    FlightSearchForm flightSearchForm
    Flow flow
    ScenarioState scenarioState
    private boolean loggedIn = false
    ItineraryData itineraryData
    SavedTripsPage savedTripsPage
    LoginForm loginForm
    GlobalNavigationHeader globalNavigationHeader
    NavigationSteps navigationSteps
    CreateAccountConfirmationPage createAccountConfirmationPage
    AccountSetupPage accountSetupPage
    AccountUpdatePage accountUpdatePage
    IntroducingNewRapidRewardsPage introducingNewRapidRewardsPage
    CommonSteps commonSteps
    ConfirmCustomerPage confirmCustomerPage
    AccountManagementData accountManagementData
    RapidRewardsEnrollmentPage rapidRewardsEnrollmentPage
    AccountTravelPage accountTravelPage
    AccountTravelPastTripPage accountTravelPastTripPage
    AccountTravelPastAirDetailsPage accountTravelPastAirDetails
    UpcomingTripsPage upcomingTripsPage
    ViewReservationPage viewReservationPage
    CertificatesPage certificatesPage
    ReissuePricingPage reissuePricingPage
    MyRapidRewardsPage myRapidRewardsPage
    PromotionsPage promotionsPage
    AddPastTripPage addPastTripPage
    CancelAirReservationPage cancelAirReservationPage
    AddressValidationReviewPage addressValidationReviewPage
    HomePage homePage
    AccountLogInPage accountLogInPage
    ExternalRedirectPage externalRedirectPage
    AddCompanionPage addCompanionPage
    SelectFlightsToChangePage selectFlightsToChangePage
    AirTripDetailsPage airTripDetailsPage
    EarlyBirdPurchasePage earlyBirdPurchasePage
    ChangeAirReservationPage changereservationpage
    EditPaymentInfoPage editPaymentInfoPage
    PassengerData passengerData
    SelectFlightsPage selectFlightsPage
    PricePage pricePage
    RRContactInformation rrAccountData
    AirPurchaseSteps airPurchaseSteps
    Data data

    @Given("I am on the Rapid Rewards Account page")
    @When("I am on  the Rapid Rewards Account page")
    void onRapidRewardsAccountPage() {
        myAccountPage.open()
    }

    @When("I log in from the home page")
    void rapidRewardsMemberLoggedInFromHomePage(){
        homePage.open()
        homePage.loginRapidRewardsMember()
    }

    @Given("I fill in account number and password from the home page")
    void fillInLoginInformationFromHomePage() {
        homePage.open()
        homePage.attemptToLoginRapidRewardMember()
    }

    @Given("I click on login button")
    void clickLoginButton() {
        loginForm.clickLoginButton()
    }

    @When("I log in from the account sidebar at the Search Flights page")
    void rapidRewardsMemberLoggedInFromSearchFlightsPage() {
        searchFlightsPage.open()
        searchFlightsPage.verifyPage()
        loginForm.rapidRewardsMemberLoggedIn()
    }

    @Then("I log in on the Account Login page")
    void logInOnAccountLoginPage() {
        accountLogInPage.login(flow.getRrUser())
    }

    @When("I log in from the Account Login page")
    void logInFromAccountLoginPage() {
        myAccountPage.open()
        accountLogInPage.login(flow.getRrUser())
        myAccountPage.verifyPage()
    }

    @When("I log in as a customer from the account sidebar at the Search Flight page")
    void customerLogInAtTheSideBar(){
        loginForm.fillLoginFormAndSubmit(flow.getRrUser().getRRNumber(), flow.getRrUser().getPassword())
		loginForm.verifyPage()
    }

    @Given("I am logged in as a \$userType Rapid Rewards member on the Search Flights page")
    @When("I login as a \$userType Rapid Rewards member on the Search Flights page")
    void openSearchFlightsAndLogIntoAccount(String userType) {
        searchFlightsPage.open()
        loginForm.loginRapidRewardsMember(userType)
        accountBar.verifyRapidRewardsLoggedIn()
    }

    @Given("I am logged-in as an A-List Member on the Search Flights page")
    @Alias("I am logged-in as a valid Rapid Rewards member with booked trips")
    void logInAsAListOnSearchFlightsPage() {
        openSearchFlightsAndLogIntoAccount("Alist")
    }

    @Given("I am logged in as Rapid Rewards member on the Rapid Rewards Account page")
    @When("I login as a valid Rapid Rewards member on the Rapid Rewards Account page")
    void loggedInAsRapidRewardsMember() {
        loginAsRapidRewardsMember("goodUser")
    }

    @Given("I am logged in as Rapid Rewards member")
    void loggedInAsRRMember() {
        if(flow.getUser() != null) {
            loginAsRapidRewardsMember("withAwards", flow.getUser())
        } else {
            loginAsRapidRewardsMember("withAwards")
        }

    }

    @Given("I login as an RR member with points")
    def loginAsRRMemberWithPoints() {
        flow.isRapidRewardsPointsPurchaseOnly = true
        loginAsRRmember()
    }

    @Given("I am logged in as Rapid Rewards customer (not a Rapid Rewards member)")
    def loggedInAsNonRRMember() {
        RRUser user = new RRUser("Customer", "Bob", "Wills", "Test1234", RRUser.getValidRRAccountNumberRandom(), 250, 0, 0, "", new Date(80, 0, 1), "Male")
        logInAsCustomer("", user)
    }

    private void logInAsCustomer(String user, RRUser rrUser = null) {
        onRapidRewardsAccountPage()
        myAccountPage.logIn(user, rrUser)
    }

    private void loginAsRapidRewardsMember(String user, RRUser rrUser = null) {
        onRapidRewardsAccountPage()
        myAccountPage.logIn(user, rrUser)
        verifyOnRapidRewardsAccountPage()
    }

    @Given("I am logged in as Rapid Rewards member without A+ credits on the Rapid Rewards Account page")
    void loggedInAsARapidRewardsMemberWithoutAPlusCredits() {
        onRapidRewardsAccountPage()
        myAccountPage.logIn("noAPlusCredits")
        verifyOnRapidRewardsAccountPage()
    }

    @Given("I am logged in as Rapid Rewards member whose account cannot be debited")
    void loggedInAsARapidRewardsMemberWhoCannotDebit() {
        onRapidRewardsAccountPage()
        myAccountPage.logIn("noDebitRapidRewardsAccount")
        verifyOnRapidRewardsAccountPage()
    }

    @Given("I am logged in as a \$rewardType Rapid Rewards member")
    void loggedInAsRapidRewardsMember(String rewardType) {
        onRapidRewardsAccountPage()
        myAccountPage.logIn(rewardType)
        verifyOnRapidRewardsAccountPage()
    }

    private def verifyOnRapidRewardsAccountPage() {
        myAccountPage.verifyPage()
        accountBar.verifyRapidRewardsLoggedIn()

        RRUser user = flow.getRrUser()
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flow.userLoggedInFirstName = user.getRRFirstName()
        flow.userLoggedInLastName = user.getRRLastName()
        flow.userLoggedInGender = user.getRRGender()
        flow.userLoggedInRapidRewardsNumber = user.RRNumber
        flow.userLoggedInRapidRewardsAccountType = user.accountType
    }

    @Then("I am redirected to the Altea EarlyBird page")
    def verifyAlteaEarlyBirdPage() {
        earlyBirdPurchasePage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
        AlteaUrlValidationHelper.verifyEarlyBirdButton(earlyBirdPurchasePage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }

    @Given("I am an anonymous user")
    @When("I log out from my Rapid Reward Account")
    def logOutIfLoggedIn() {
        if (ToggleGlobalNav.isOn()
                && globalNavigationHeader.isHomePage()) {
            if (globalNavigationHeader.isLoggedIn()) {
                globalNavigationHeader.clickOnLogoutLink()
            }
        } else {
            if (accountBar.isLoggedIn()) {
                accountBar.logOut()
            }
        }
    }

    @Given("I have stored \$creditCardsNumbers credit cards")
    def storeCreditCars(String creditCardsNumbers)
    {
        rapidRewardsMemberOnThePreferencePage()
        navigatePaymentInformationLink()
        editPaymentInfoPage.clickOnAddEditCreditCardLink()
        int index = creditCardsNumbers.toInteger()
        for(int i = 0; i < index; i++) {
            if(i>0) {
                editPaymentInfoPage.clickOnAddAnotherCreditCard()
            }
            editPaymentInfoPage.fillACreditCardInfoAndMakePrimary(i)
        }
        editPaymentInfoPage.clickOnSubmitButton()
    }

    def fillAndConfirmNewRapidRewardsAccount(RapidRewardsAccount rrAccount) {
        createAnAccountPage.fillInRequiredInformation(rrAccount)
        createAnAccountPage.clickOnCreateARapidRewardAccount()

        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            RRUser user = flow.getUser()
            dyna.generateValidateAddressResponseSpecification(user.getAddress(),user.getAddressTwo(),user.getCity(),user.getZipCode(),
                    user.getFirstName(),user.getMiddleName(),user.getLastName(),user.getStateCode(), user.getCountryCode(),user.getAddressType(),false)
        }
        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
        addressValidationReviewPage.clickConfirm()

        flow.isLoggedIn = true
        flow.isRapidRewards = true
        flow.userLoggedInFirstName = rrAccount.firstName
        flow.userLoggedInLastName = rrAccount.lastName
        flow.userLoggedInRapidRewardsNumber = accountBar.getRRNumber()
        flow.userLoggedInBirthMonth = rrAccount.birthMonth
        flow.userLoggedInBirthDay = rrAccount.birthDay
        flow.userLoggedInBirthYear = rrAccount.birthYear
        flow.userLoggedInGender = rrAccount.gender
        flow.userLoggedInEmail = rrAccount.email
        flow.getRrUser().setNumber(flow.userLoggedInRapidRewardsNumber)

        accountBar.verifyRapidRewardsLoggedIn()
    }

    RRUser generateCustomer() {
        goToCreateAnAccountPage()
        completeAllMandatoryFields()
        selectNotCreateRapidRewardsAccount()

        clickOnCreateMyAccount()
        confirmCustomerPage.verifyCurrentPageLocation()
        confirmCustomerPage.verifyAccountNumberContainsOnlyNumbers()
        String number = confirmCustomerPage.waitForElement(confirmCustomerPage.ACCOUNT_NUMBER).text
        RRUser rrUser = flow.getRrUser()
        rrUser.setNumber(number)
        logOutIfLoggedIn()

        return rrUser
    }

    @Given("I am an Anonymous User on the Account Login Page")
    def goToAccountLoginPage() {
        navigationSteps.openSwaHomepage()
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuRapidRewards()
            globalNavigationHeader.clickOnMyAccountLink()
        } else {
            globalNavigationHeader.openRapidRewardsSubMenu().clickOnMyAccountLink()
        }
        myAccountPage.verifyPage()
        myAccountPage.verifyCurrentPageLocation()
    }

    @Given("I am a Southwest User")
    def createASouthwestUser(){
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        flow.setRrUser(new RRUser(rrAccount))
    }

    @When("I select the preference page")
    @Aliases(values = ["I select My Preferences tab", "I navigate to the My Preferences page"])
    def rapidRewardsMemberOnThePreferencePage() {
        myAccountPage.goToMyPreferences()
        myAccountPage.verifyPage()
    }

    @When("I click the snapshot link")
    @Alias("I access my account's Snapshot")
    def clickSnapshotLink() {
        myAccountPage.verifyPage()
        myAccountPage.clickSnapshotLink()
        myAccountPage.checkSomethingServed()
    }

    @When("I enroll for a Rapid Rewards account")
    def enrollForRapidRewardsAccount() {
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount("rrtest", "fortest")
        createAnAccountPage.fillInRequiredInformation(rrAccount)
        fillAndConfirmNewRapidRewardsAccount(rrAccount)
    }

    @When("I enroll for a Rapid Reward account using an email address with not allowed special character")
    def enrollForRapidRewardsAccountWithInvalidEmail(){
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        rrAccount.setEmail("test,test@wnco.com")
        createAnAccountPage.fillAndConfirmNewRapidRewardsAccountWithInvalidEmail(rrAccount)
    }

    @When("I choose to navigate the Contact Information link")
    @Alias("I access my account's Contact Information")
    def navigateContactInformationLink() {
        myAccountPage.gotoContactInformation()
        myAccountPage.verifyPage()
    }
     @When("I choose another Primary IRN")
     def chooseAnotherPrimaryIRN() {
         myAccountPage.changePrimaryIrn()
         myAccountPage.verifyPage()
         myAccountPage.clickOnUpdateBillingInfo()
         myAccountPage.verifyPage()
     }

    @When("I logout from my Traveler Account")
    def logoutFromMyTravelerAccount() {
        myAccountPage.clickOnTravelerAccountLogout()
    }


    @When("I select the \$breadcrumb link")
    void selectBreadCrumbUnderMyPreferences(String breadcrumb) {
        switch (breadcrumb) {

            case "Contact Information":
                myAccountPage.gotoContactInformation()
                break

            case "Communication Preferences":
                myAccountPage.gotoCommunicationPreferences()
                break

            case "Payment Information":
                myAccountPage.gotoPaymentInformationPage()
                break

            default:
                fail("Message did not return ${breadcrumb}")
        }
        myAccountPage.verifyPage()
    }

    @When("I select the Communication Preferences link")
    @Alias("I access my account's Communication Preferences")
    def navigateCommunicationPreferencesLink() {
        myAccountPage.gotoCommunicationPreferences()
        myAccountPage.verifyPage()
    }

    @Given("I have chosen to navigate the Payment Information link")
    @When("I choose to navigate the Payment Information link")
    @Alias("I access my account's Payment Information")
    def navigatePaymentInformationLink() {
        myAccountPage.gotoPaymentInformationPage()
        myAccountPage.verifyPage()
    }

    @When("I am on the destination finder page")
    @Alias("I am on the destination finder page for the second time")
    def destinationFinder() {
        destinationFinderPage.destinationFinder()
    }

    @When("I click edit for User Name and Password")
    void clickEditUserName(){
        myAccountPage.clickEditUserName()
    }

    @When("I click add/edit for \"\$sectionName\"")
    void clickAddEditLink(String sectionName){
        myAccountPage.clickAddEditLink(sectionName)
    }

    @When("I am on the destination finder page for the first time")
    def destinationFinderFirstLogin() {
        destinationFinderPage.deleteDestinationFinderHelpHasBeenShown() //delete cookie
        destinationFinder()
    }

    @When("I select My Travel link")
    @Alias("I access my account's Travels")
    def myTravelLink() {
        myAccountPage.clickMyTravelLink()
        myAccountPage.verifyPage()
    }

    @When("I select My Rapid Rewards link")
    @Aliases(values = ["I view the My Rapid Rewards page",
                       "I access my account's Rapid Rewards", "I select \"My Rapid Rewards\" link"])
    def myRapidRewardLink() {
        myAccountPage.clickMyRapidRewardsLink()
        myRapidRewardsPage.verifyPage()
    }

    @When("I view my rapid rewards promotions")
    void openMyAwardsPage() {
        myAccountPage.viewPromotions()
    }

    @When("I click on promotion link in my account")
    void clickOnPromotionLinkMyAccount() {
        myAccountPage.clickOnPromotions()
    }

    @When("I view my award certificates")
    def bookFreeFlight() {
        searchFlightsPage.expandMyRapidRewards()
        searchFlightsPage.clickPromotionalAwards()
    }

    @When("I select the link My Preferences")
    @Given("I select the link My Preferences")
    @Alias("I access my account's Preferences")
    def goToMyPreferences() {
        myAccountPage.goToMyPreferences()
        myAccountPage.waitForUrlToChangeOrOops(myAccountPage.getRelativePath())
        myAccountPage.checkSomethingServed()
        myAccountPage.verifyIamOnTheCorrectPage(myAccountPage.MY_PREFERENCES_URL)
    }

    @When("I select the points to be purchased")
    def selectPointsAndContinue() {
        myAccountPage.pointsSelection()
        myAccountPage.pointsSubmitButton()
        myAccountPage.verifyPage()
    }

    @When("I complete the points purchase")
    def completePointsPurchase() {
        purchasePage.fillInAllInformation()
        creditCardSubForm.addCreditCardWithDefaultName()
        myAccountPage.pointsPurchaseSubmitButton()
    }

    @When("I purchase points")
    def purchasePoints() {
        myAccountPage.purchasePoints()
        selectPointsAndContinue()
        completePointsPurchase()
    }

    @Given("I select book a flight")
    @When("I select book a flight")
    @Alias("I click the book a trip button")
    def selectBookAFlightOnSnapShotPage() {
        myAccountPage.clickBookATrip()
    }

    @When("I search for flights with points from my account")
    void searchForFlightsWithPoints() {
        selectBookAFlightOnSnapShotPage()
        myAccountPage.selectPointsFareOption()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I book a flight as a senior and search from Rapid Rewards")
    void bookFlightAsSeniorFromRapidRewards() {
        flightSearchForm.selectAdultAndSeniorPassengers(0, 1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I search for flights from my account")
    void searchForFlightsWithModal() {
        selectBookAFlightOnSnapShotPage()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, MODAL_SUBMIT_BUTTON_ID)
    }

    @When("I close the book a flight modal")
    def closeBookAFlightModal() {
        myAccountPage.closeBookAFlightModal()
    }

    @When("I delete all saved flights")
    def deleteSavedFlights() {
        savedTripsPage.open()
        savedTripsPage.deleteAllSavedTrips()
    }

    @When("I click on the modal search button")
    def clickModalSearchButton() {
        bookATripModal.clickSearchButton()
    }

    @When("I click my account link")
    def clickMyAccountLink() {
		myAccountPage.verifyPage()
        myAccountPage.clickMyAccount()
        myAccountPage.verifyPage()
    }

    @When("I click to see details of my upcoming trip")
    void clickUpComingTripToSeeDetails() {
        myAccountPage.storeUpcomingTripDetail()
        myAccountPage.viewTripDetails()
    }

    @When("I attempt to access the site using an invalid account username and password")
    def loginWithInvalidAccountUserNameAndPassword() {
        myAccountPage.loginWithUserNameAndPassword("notvalid","account");
        myAccountPage.checkSomethingServed()
    }

    @When("I attempt to access the site using my account number and password")
    def loginWithValidAccount() {
        myAccountPage.logIn("goodUser")
        myAccountPage.verifyPage()
    }

    @Given("I am a Southwest User at \"Create an Account\" page")
    @When("I access Create an Account page")
    def goToCreateAnAccountPage() {
        createAnAccountPage.open()
        createAnAccountPage.verifyPage()
    }

    @Given("I have completed all the mandatory fields to create an account")
    @When("I complete all the mandatory fields")
    def completeAllMandatoryFields() {
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        flow.setRrUser(new RRUser(rrAccount))
        accountManagementData.emailAddress = rrAccount.email
        createAnAccountPage.verifyPage()
        createAnAccountPage.fillInRequiredInformation(rrAccount)
    }

    @When("I complete all the mandatory fields for \$firstName \$lastName")
    def completeAllMandatoryFieldsForGivenName(String firstName, String lastName) {
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        rrAccount.setFirstName(firstName)
        rrAccount.setLastName(lastName)
        flow.setRrUser(new RRUser((rrAccount)))
        fillAndConfirmNewRapidRewardsAccount(rrAccount)
    }

    @When("I submit the account creation form with a password containing invalid characters")
        def fillFormWithInvalidPassword() {
            fillFormWithPassword("blah&blah@")
        }

    @When("I submit the account creation form with a short password")
        def fillFormWithShortPassword() {
            fillFormWithPassword("blah")
        }

    private void fillFormWithPassword(String password){
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount();
        rrAccount.password =  password;
        flow.setRrUser(new RRUser(rrAccount));
        accountManagementData.emailAddress = rrAccount.email;
        createAnAccountPage.checkSomethingServed();
        createAnAccountPage.fillInRequiredInformation(rrAccount);
        createAnAccountPage.clickOnCreateARapidRewardAccount();
        createAnAccountPage.submit();
    }

    @Then("I see oops message for an invalid password")
        def verifyOopsforInvalidPassword() {
            createAnAccountPage.verifyInvalidPasswordOopsMessagesIsPresent()
    }

    @Then("I see oops message for a short password")
        def verifyOopsforShortPassword() {
            createAnAccountPage.verifyShortPasswordOopsMessagesIsPresent()
    }


    @When("I submit the account creation form with a blacklisted password \$blacklistPassword")
    def fillFormWithBlacklistedPassword(String blacklistPassword) {
        fillFormWithPassword(blacklistPassword);
    }

    @Then("I see oops message for blacklisted password")
        def verifyOopsforBlacklistedPassword() {
            createAnAccountPage.verifyBlackListedPasswordOopsMessagesIsPresent(flow.getRrUser().getRRPassword().toLowerCase())
    }

    @When("I submit the Enrollment form that has been properly filled")
    @Alias("I submit the Enrollment form that has been properly filled for a random user")
    def submitTheEnrollmentForm() {
        completeEnrollmentFieldsAndMoveToReviewPage(false)
    }



    private completeEnrollmentFieldsAndMoveToReviewPage(boolean cleanse) {
        completeAllMandatoryFields()
        selectCreateRapidRewardAccount()
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            RRUser user = flow.getUser()
            dyna.generateValidateAddressResponseSpecification(user.getAddress(), user.getAddressTwo(), user.getCity(), user.getZipCode(),
                    user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getStateCode(), user.getCountryCode(), user.getAddressType(), cleanse)
        }
        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
    }

    @When("I select to create a Rapid Rewards account")
    def selectCreateRapidRewardAccount() {
        createAnAccountPage.clickOnCreateARapidRewardAccount()
    }

    @When("I confirm the account creation")
    def clickOnCreateMyAccount(){

        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            RRUser user = flow.getUser()
            dyna.generateValidateAddressResponseSpecification(user.getAddress(),user.getAddressTwo(),user.getCity(),user.getZipCode(),
                    user.getFirstName(),user.getMiddleName(),user.getLastName(),user.getStateCode(), user.getCountryCode(),user.getAddressType(),false)
        }
        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
        addressValidationReviewPage.clickConfirm()
    }

    @When("I attempt to confirm the account creation")
    def attemptCreateMyAccount(){
        createAnAccountPage.submit()
    }

    @When("I decide to complete my account's Preferences")
    def clickCompleteMyAccountPreferences() {
        if(DynaStubsIntegration.useDynaStubs()) {
            createAccountConfirmationPage.verifyPage()
            createAccountConfirmationPage.clickOnCompleteYourPreferencesButton()
            myAccountPage.verifyPage()
        } else {
            flow.getRrUser().setNumber(accountBar.getRRNumber())
            logOutIfLoggedIn()
            loggedInAsRapidRewardsMemberWithUserNameAndPassword()
            checkMyPreferences()
        }
    }

    @When('I access my account\'s information through the account sidebar')
    def clickOnMyAccountLink() {
		confirmCustomerPage.verifyPage()
        confirmCustomerPage.clickOnMyAccountLink()
        myAccountPage.verifyPage()
    }

    @When("I login as a Rapid Rewards Member")
    @Alias("I login as Customer")
    @Given("I am logged in as a Rapid Rewards Member")
    @Aliases(values = ["I login from Login page", "I login as a Rapid Rewards Member from Login page"])
    @Then("I login")
    void loggedInAsRapidRewardsMemberWithUserNameAndPassword() {
        RRUser rrUser = flow.getUser()
        flow.setIsLoggedIn(true)
        onRapidRewardsAccountPage()
        myAccountPage.loginWithUserNameAndPassword(rrUser.getRRNumber(), rrUser.getRRPassword())
        myAccountPage.verifyPage()
    }

    @When("I check my preferences in my account")
    void checkMyPreferences(){
        myAccountPage.goToMyPreferences()
        myAccountPage.verifyPage()
    }

    @When("I select to see my communication preferences in my account")
    void selectToSeeMyCommunicationPreferences(){
        myAccountPage.gotoCommunicationPreferences()
        myAccountPage.verifyPage()
    }

    @When('I complete the Username and Security Questions for my Rapid Rewards account')
    def fillInUserNameAndSecurityQuestion() {
        accountSetupPage.fillInUserNameAndSecurityQuestions()
        accountSetupPage.checkSomethingServed()
    }

    @Given("I am a Customer without user name and security questions")
    def customerWithoutUserNameAndSecurityQuestion() {
        if (DynaStubsIntegration.useDynaStubs()){
            flow.setUser(new DynaStubsIntegration().generateCustomerWithoutUserNameAndSecurityQuestion())
            flow.isLoggedIn = true
            flow.isRapidRewards = false
        }
    }

    @When('I accept rules and regulations')
    def acceptRulesAndRegulations() {
        accountSetupPage.selectAcceptRulesAndRegulations()
    }

    @When("I complete the Update Your Rapid Rewards Account page")
    def fillInSecurityQuestionsOnUpdateAccountPage() {
        accountUpdatePage.fillInSecurityQuestions()
    }

    @When("I select not to create a Rapid Rewards account")
    void selectNotCreateRapidRewardsAccount(){
        createAnAccountPage.clickOnCreateACustomerAccount()
    }

    @When("I continue to see my account's settings")
    @Alias("I click continue in Congratulations page")
    def continueToAccountSetting() {
        introducingNewRapidRewardsPage.clickOnContinueButton()
        introducingNewRapidRewardsPage.checkSomethingServed()
    }

    @When("I decide to get more points")
    def clickOnGetPoints() {
        myAccountPage.clickOnGetPointsButton()
        myAccountPage.verifyPage()
    }

    @When("I decide to obtain more points")
    def clickOnShortOnPointsLink() {
        myAccountPage.clickShortOnPointsLink()
        myAccountPage.verifyPage()
    }

    @When("I create a Rapid Rewards account")
    def createARapidRewardsAccount() {
        completeAllMandatoryFields()
        selectCreateRapidRewardAccount()
        clickOnCreateMyAccount()
    }

    @When("I select to subscribe to Click N Save email option")
    def subscribeToClickNSaveEmailOptionOnCreateAnAccountPage() {
        createAnAccountPage.clickOnClickNSaveCheckbox()
    }

    @When("I select to subscribe to In a Nutshell email option")
    def subscribeToInANutshellEmailOptionOnCreateAnAccountPage() {
        createAnAccountPage.clickOnInANutshellCheckbox()
    }

    @When("I select to subscribe to The Rapid Rewards Report email option")
    def subscribeToTheRapidRewardsReportOnCreateAnAccountPage() {
        createAnAccountPage.clickOnRrrpCheckBox()
    }

    @When("I select to subscribe to The Rapid Rewards E-mail Update email option")
    def subscribeToTheRapidRewardsEmailUpdateOnCreateAnAccountPage() {
        createAnAccountPage.clickOnRreuCheckBox()
    }

    @When("I access my account's Rapid Rewards Companion Pass Details")
    clickOnRapidRewardsCompanionPassDetails(){
        myAccountPage.clickOnViewDetailsCompanionLink()
        myAccountPage.verifyPage()
    }

    @When("I decide to update my account's information")
    def clickOnUpdateLink(){
        confirmCustomerPage.clickOnUpdateLink()
   }

    @When("I close the help popup")
    def clickOnGetStartedLink() {
        destinationFinderPage.clickOnGetStarted()
    }

    @When("I select to see the edit mode for my \$optionToEdit")
    def clickOnAddEditLink(String optionToEdit) {
        if(optionToEdit.equals("IRN")){
            myAccountPage.clickOnAddEditLink("IRN")
        } else {
            myAccountPage.clickOnAddEditLink()
        }

    }

    @When("I enroll to the Rapid Rewards program from the Communication Preferences page")
    def clickOnRREnroll() {
        myAccountPage.clickOnRREnroll()
        rapidRewardsEnrollmentPage.clickOnAcceptRulesCheckbox()
        rapidRewardsEnrollmentPage.clickOnEnrollButton()
        myAccountPage.verifyPage()
    }

    @When("I decide to edit my account's Preferences from the Upgrade Account Confirmation page")
    def clickOnEditPreferences() {
        myAccountPage.clickOnEditPreferencesButton()
        myAccountPage.verifyPage()
    }

    @When("I select to unsubscribe to The Rapid Rewards Report email option")
    def unsuscribeRapidRewardsReportEmail() {
        createAnAccountPage.uncheckRRReportOption()
    }

    @When("I select to unsubscribe to The Rapid Rewards E-mail Update email option")
    def unsuscribeRapidRewardsUpdateEmail() {
        createAnAccountPage.uncheckRRUpdateOption()
    }

    @When("I attempt to edit the Contact Information")
    def clickOnEditContactInformationLink() {
        myAccountPage.clickOnEditContactInformationLink()
        myAccountPage.verifyPage()
    }

    @Given("I am a Rapid Rewards Member with one Past Flight in my account")
    def addPastFlightToAccountWithAnAirProduct() {
        if (DynaStubsIntegration.useDynaStubs()){
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            flow.setRrUser(dyna.generateRRUser())
            flow.setPastTrip(dyna.addPastTrip(flow.rrUser.number, itineraryData))
        }
    }

    @When("I access my account's Past Flights")
    def clickOnPastFlightLink() {
        myAccountPage.clickOnPastFlightsLink()
        myAccountPage.verifyPage()
    }

    @When("I decide to view all the past flights from account's Snapshot")
    def  clickOnViewAllLinkInsideThePastFlightsSectionOnMyAccountPage(){
        myAccountPage.clickOnViewAllLinkInsideThePastFlightsSection()
    }

    @When("I decide to view all the past flights from account's Travels")
    def clickOnViewAllLinkOnPastTripSectionOnAccountTravelPage(){
        accountTravelPage.clickOnViewAllLinkOnPastTripSection()
        accountTravelPastTripPage.verifyPage()
    }

    @When("I decide to view all the upcoming trips from account's Snapshot")
    def clickOnViewAllUpcomingTrips() {
        myAccountPage.clickOnViewAllUpcomingTrips()
        upcomingTripsPage.verifyPage()
    }

    @When("I change the sorting option to descending by name")
    def changeSortingOptionDescendingByName() {
        myAccountPage.changeSortingOptionDescendingByName()
    }

    @When("I change the sorting option to ascending by Trip Name")
    def changeSortingOptionAscendingByName() {
        myAccountPage.changeSortingOptionAscendingByName()
    }

    @When("I access my account's Promotions from My Account Snapshot page")
    def clickOnPromotionsLink() {
        myAccountPage.clickOnPromotionsLink()
    }

    @When("I attempt to create a dream trip from my account's Rapid Rewards")
    void attemptToCreateDreamTrip() {
        myAccountPage.clickOnCreateFreeFlightTracker()
    }

    @When("I have selected the Car product from My Trip on Upcoming Trips page")
    def clickOnCarProductFromUpcomingTrips() {
        myAccountPage.clickOnCarProductFromUpcomingTrips()
        myAccountPage.verifyPage()
    }

    @When("I select an Air reservation which is part of My Trip")
    def selectAirReservationFromUpcomingTrip() {
        myAccountPage.viewTripDetails()
    }

    @When("I access Destination Finder page for the second time")
    def accessDestinationFinderPage() {
        destinationFinderPage.open()
    }

    @When("I attempt to cancel the Car product on Trip Details page")
    def cancelCarProductFromUpcomingTrip() {
        myAccountPage.cancelCarProductFromUpcomingTrip()
        myAccountPage.verifyPage()
    }

    @When("I attempt to cancel the Air product on Trip Details page")
    @Alias("I click on the trip details Cancel Reservation link")
    def cancelAirProductFromUpcomingTrip() {
        airTripDetailsPage.clickOnCancelReservationLink()
    }

    @When("I attempt to purchase Early Bird on Trip Details page")
    def purchaseEarlyBirdFromUpcomingTrip() {
        airTripDetailsPage.clickOnEarlyBirdButton()
    }

    @When("I attempt to change the Air product on Trip Details page")
    @Alias("I click on the trip details Change Reservation link")
    def attemptToChangeAirProduct() {
        airTripDetailsPage.clickOnChangeReservationLink()
    }

    @When("I confirm that I cancel the car product")
    def confirmCarCancellationOnUpcomingTrip() {
        myAccountPage.clickOnConfirmCancellation()
        myAccountPage.verifyPage()
    }

    @When("I attempt to change the Air product on Upcoming Trips page")
    def attemptToChangeAirProductOnUpcomingTripPage() {
        myAccountPage.clickOnChangeReservationFromUpcomingTrip()
    }

    @When("I select a past flight from Past Trips page")
    @Aliases(values = ["I select a Round Trip past flight from Past Trips page",
    "I select a Open Jaw past flight from Past Trips page",
    "I select a One Way past flight from Past Trips page"])
    def selectFirstTripAvailableOnPastTripPage(){
        accountTravelPastTripPage.clickOnFirstPastTripAvailable()
    }

    @When("I attempt to change the Air product on My Account Snapshot page")
    def attemptToChangeAirProductOnAccountSnapshotPage() {
        myAccountPage.clickOnChangeReservationFromAccountSnapshot()
    }

    @When("I access my account's Promotions from My Rapid Rewards page")
    def clickOnViewAllRegisteredPromotionsLink() {
        myAccountPage.clickOnViewAllRegisteredPromotionsLink()
        promotionsPage.verifyPage()
    }

    @When("I select the first product of My Trip")
    def clickOnFirstProduct() {
        upcomingTripsPage.clickOnViewCarDetailsLink()
    }

    @When("I rename the trip by entering valid characters")
    def renameTripName() {
        flow.tripName = "Multiproduct"
        viewReservationPage.renameTrip(flow.tripName)
    }

    @When("I select a Hotel reservation which is not part of a trip from Upcoming Trips page")
    def clickOnFirstHotelReservation() {
        upcomingTripsPage.clickFirstHotelReservationLink()
        upcomingTripsPage.verifyPage()
    }

    @Given("I am a Rapid Rewards Member with a designated companion and an Altea upcoming trip")
    def rapidRewardsMemberWithCompanionPassStatusAndAnUpcomingTripForAltea() {
        rapidRewardsMemberWithCompanionPassStatus()
        bookAnyAirTripWithAccrual(true)
    }

    @Given("I have logged in and I am in view all my upcoming trips")
    @When("I view My Travel")
    def whenIViewAllUpcomingTrips() {
        accountLogInPage.open()
        accountLogInPage.login( flow.rrUser )
        myAccountPage.clickOnViewAllUpcomingTrips();
    }

    @When("I click on Set Up Flight Status Messaging from my first upcoming trip")
    def whenIClickOnSetUpFlightStatusMessagingFromMyFirstUpcomingTrip(){
        accountLogInPage.open()
        accountLogInPage.login( flow.rrUser )
        myAccountPage.clickOnViewAllUpcomingTrips();
        upcomingTripsPage.clickOnFirstFlightStatusNotificationLink()
    }

    @When("I click on Add a Companion from my first trip")
    def whenIClickOnAddACompanionFromMyFirstTrip() {
        upcomingTripsPage.clickOnFirstAddCompanionLink()
    }

    @When("I click on Change Reservation from my first trip")
    def whenIClickOnChangeReservationFromMyFirstTrip() {
        upcomingTripsPage.clickOnFirstChangeReservationLink()
    }

    @When("I click on my first upcoming trip")
    def whenIClickOnMyFirstUpcomingTrip() {
        upcomingTripsPage.clickOnFirstUpcomingTripLink()
    }

    @When("I click on Cancel Reservation from my first trip")
    def whenIClickOnCancelReservationFromMyFirstTrip() {
        upcomingTripsPage.clickOnFirstCancelReservationLink()
    }

    @When("I view Snapshot")
    def iViewMySnapshot() {
        accountLogInPage.open()
        accountLogInPage.login( flow.rrUser )
    }

    @Then("I see the Altea Low Fare Calendar for \$origin - \$dest")
    def viewAlteaLowFareCalendar(String origin, String dest) {
        myRapidRewardsPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
        AlteaUrlValidationHelper.verifyLowFareSearchPage(myRapidRewardsPage.getCurrentUrl(), origin, dest, flow.isRapidRewards)
    }

    @Then("I see the Altea Low Fare Calendar")
    def viewAlteaLowFareCalendar(){
        viewAlteaLowFareCalendar("HOU", "CUN")
    }

    @Given("I am a Rapid Rewards Member with a designated companion and an upcoming trip")
    def rapidRewardsMemberWithCompanionPassStatusAndAnUpcomingTrip() {
        if (DynaStubsIntegration.useDynaStubs()){
            rapidRewardsMemberWithCompanionPassStatus()
            bookAnyAirTripWithAccrual()
        } else {
            pending("need to investigate where we can obtain a live RR member with a designated companion")
        }
    }

    @Given("I am a Rapid Rewards Member with a designated companion and an upcoming trip from a given itinerary")
    def rapidRewardsMemberWithCompanionPassStatusAndAnUpcomingTripWithGivenItinerary() {
        if (DynaStubsIntegration.useDynaStubs()){
            rapidRewardsMemberWithCompanionPassStatus()
            bookAirTripWithExistingItineraryData()
        } else {
            pending("need to investigate where we can obtain a live RR member with a designated companion")
        }
    }

    @Given("I am a Rapid Rewards Member with an upcoming trip")
    def iAmARapidRewardsMemberWithAnUpcomingTrip() {
        bookAnyAirTripWithAccrual()
    }

    @Given("I am a Rapid Rewards Inactive Member with an upcoming trip")
    def iAmARapidRewardsInactiveMemberWithAnUpcomingTrip() {
        bookAnyAirTripWithAccrualInactiveRRUser()
    }

    @Then ("I am redirected to the Southwest Add a Companion page")
    def thenIAmRedirectedToTheSouthwestAddACompanionPage() {
        addCompanionPage.getWrapper()
    }

    @Then ("I am redirected to the Southwest Change Reservation page")
    @Alias ("I am redirected to the Change Reservation page")
    def thenIAmRedirectedToTheSouthwestChangeReservationPage() {
        selectFlightsToChangePage.verifyPageAndTitle()
    }

    @Given("I am a Rapid Rewards Member with an Altea upcoming trip")
    @Aliases(values = ["I am a Rapid Rewards Member with an Altea upcoming trip next day",
                       "I am a Rapid Rewards Member with an Altea upcoming trip on the My Account page"])
    def addUpcomingTripToAccountWithAnAirProductNextDay() {
        bookAnyAirTripWithAccrual(true)
        loggedInAsRapidRewardsMemberWithUserNameAndPassword()
    }

    @Given("I have an Altea upcoming trip")
    def addAlteaUpcomingTripToAccount() {
        bookAnyAirTripWithAccrual(true)
    }

    @Given("I have a domestic upcoming trip")
    def addUpcomingTripToAccount() {
        bookAnyAirTripWithAccrual(false)
    }

    @Given("I am a Rapid Rewards Member with an Altea upcoming trip next month")
    @Alias ("I have an Altea upcoming trip next month")
    def iAmARapidRewardsMemberWithAnAlteaUpcomingTripNextMonth() {
        bookAirTripOneMonthInFuture(true)
    }

    @Given("I am a Rapid Rewards Member with an Altea upcoming trip today")
    def addUpcomingTripToAccountWithAnAirProductToday() {
        bookAirTripToday(true)
        loggedInAsRapidRewardsMemberWithUserNameAndPassword()
    }

    @Given("I am logged in as Rapid Rewards Member with the following itinerary data: \$itineraryData")
    def addUpcomingTripToAccountWithItineraryData(ExamplesTable itineraryData) {
        commonSteps.loadItineraryTable(itineraryData)
        bookAirTripWithExistingItineraryData()
        loggedInAsRapidRewardsMemberWithUserNameAndPassword()
    }

    def rapidRewardsMemberWithCompanionPassStatus() {
         if (DynaStubsIntegration.useDynaStubs()){
            DynaStubsIntegration dyna = new DynaStubsIntegration()

            AccountSpecification accountSpecification = new AccountSpecificationBuilder().withCompanionPassAchieved().build();
            RRUser rrUser = dyna.createCustomer(accountSpecification)
            flow.setRrUser(rrUser)
            flow.setIsRapidRewards(true)

            Companion companion = new CompanionBuilder().build()
            dyna.designateCompanionPass(rrUser.getNumber(), companion)

            flow.setCompanion(companion)
        } else {
            pending("need to investigate where we can obtain a live RR member with a designated companion")
        }
    }

    @Given("I am a Rapid Rewards Member with an Upcoming Trip")
    def addUpcomingTripToAccountWithAnAirProduct() {
        bookAnyAirTripWithAccrual()
    }

    @When("I view My Travel Upcoming Trips List")
    def iViewMyTravelUpcomingTripsList() {
        accountLogInPage.open()
        accountLogInPage.login( flow.rrUser )
        myAccountPage.clickMyTravelLink()
    }

    private AirReservation bookAirTripToday(boolean isAltea = false) {
        if (DynaStubsIntegration.useDynaStubs()){
            if (flow.getRrUser() == null) {
                flow.setRrUser(new DynaStubsIntegration().generateRRUser())
                flow.setIsRapidRewards(true)
            }

            itineraryData = generateAnyItineraryDataToday()
            itineraryData.isAltea = isAltea
            def airReservation = new DynaStubsIntegration().addUpcomingTrip(flow.rrUser.number, itineraryData)
            scenarioState.add(airReservation)
            airReservation
        }
    }

    private AirReservation bookAnyAirTripWithAccrual(boolean isAltea = false) {
        if (DynaStubsIntegration.useDynaStubs()){
            if (flow.getRrUser() == null) {
                flow.setRrUser(new DynaStubsIntegration().generateRRUser())
                flow.setIsRapidRewards(true)
            }

            itineraryData = generateAnyItineraryData()
            itineraryData.isAltea = isAltea
            def airReservation = new DynaStubsIntegration().addUpcomingTrip(flow.rrUser.number, itineraryData)
            scenarioState.add(airReservation)
            airReservation
        }
    }

    private AirReservation bookAirTripWithExistingItineraryData() {

        if (DynaStubsIntegration.useDynaStubs()){
            if (flow.getRrUser() == null) {
                flow.setRrUser(new DynaStubsIntegration().generateRRUser())
                flow.setIsRapidRewards(true)
            }

            def airReservation = new DynaStubsIntegration().addUpcomingTrip(flow.rrUser.number, itineraryData)
            scenarioState.add(airReservation)
            airReservation
        }
    }

    private ItineraryData generateAnyItineraryData() {
        ItineraryData itineraryData
        RandomTicketTypeGroup randomTicketTypeGroup = new RandomTicketTypeGroup();

        ItineraryDataBuilder builder = new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("Southwest").withArrivalCarrier("Southwest")
        builder = builder.withFlightsDates("tomorrow", "day-after").withFareClass("Anytime")
        builder.departureStation = "DAL"
        builder.arrivalStation = "HOU"
        itineraryData = builder.build()
        return itineraryData
    }

    private AirReservation bookAirTripOneMonthInFuture(boolean isAltea = false) {
        if (DynaStubsIntegration.useDynaStubs()){
            if (flow.getRrUser() == null) {
                flow.setRrUser(new DynaStubsIntegration().generateRRUser())
            }

            itineraryData = generateItineraryDataOneMonthInFuture()
            itineraryData.isAltea = isAltea
            def airReservation = new DynaStubsIntegration().addUpcomingTrip(flow.rrUser.number, itineraryData)
            scenarioState.add(airReservation)
            airReservation
        }
    }

    private AirReservation bookAnyAirTripWithAccrualInactiveRRUser(boolean isAltea = false) {
        if (DynaStubsIntegration.useDynaStubs()){
            if (flow.getRrUser() == null) {
                flow.setRrUser(new DynaStubsIntegration().generateRRUserWithAccountInactive())
                flow.setIsRapidRewards(true)
            }

            itineraryData = generateAnyItineraryData()
            itineraryData.isAltea = isAltea
            def airReservation = new DynaStubsIntegration().addUpcomingTrip(flow.rrUser.number, itineraryData)
            scenarioState.add(airReservation)
            airReservation
        }
    }

    private ItineraryData generateItineraryDataOneMonthInFuture() {
        ItineraryData itineraryData
        RandomTicketTypeGroup randomTicketTypeGroup = new RandomTicketTypeGroup();

        ItineraryDataBuilder builder = new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("Southwest").withArrivalCarrier("Southwest")
        builder = builder.withFlightDatesFromToday("30");
        builder.departureStation = "DAL"
        builder.arrivalStation = "HOU"
        itineraryData = builder.build()
        return itineraryData
    }

    private ItineraryData generateAnyItineraryDataToday() {
        ItineraryData itineraryData
        RandomTicketTypeGroup randomTicketTypeGroup = new RandomTicketTypeGroup();

        ItineraryDataBuilder builder = new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("Southwest").withArrivalCarrier("Southwest")
        builder = builder.withFlightsDates("today", "tomorrow").withFareClass("Anytime")
        builder.departureStation = "DEN"
        builder.arrivalStation = "CUN"
        itineraryData = builder.build()
        return itineraryData
    }

    @When("I change the sorting option to descending by Trip Date")
    def changeSortingOptionDescendingByDate() {
        myAccountPage.changeSortingOptionDescendingByDate()
    }

    @When("I attempt to cancel the Air product on Upcoming Trips page")
    def attemptToCancelAirProductOnUpcomingTripPage() {
        upcomingTripsPage.clickOnCancelReservation()
        cancelAirReservationPage.verifyPage()
    }

    @When("I attempt to cancel the Air product on Upcoming Trip Details page")
    def attemptToCancelAirProductOnUpcomingTripDetailsPage() {
        upcomingTripsPage.clickOnViewAirDetailsLink()
        airTripDetailsPage.clickOnCancelReservationLink()
        cancelAirReservationPage.verifyPage()
    }

    @When("I select to see my old awards")
    def selectSeeMyOldAwards() {
        myAccountPage.viewCreditsAndAwards()
        myAccountPage.verifyPage()
    }

    @When("I select to extend the standard awards available for reissue")
    def selectExtendReissuableStandardAward(){
        certificatesPage.clickOnYesReissuableLink()
    }

    @When("I attempt to reissue one of my standard awards")
    def attemptReissueStandardAward(){
        certificatesPage.clickOnCheckBoxReissuableAward()
        certificatesPage.clickOnReissueAwardButton()
    }

    @When("I attempt to complete the award reissue without entering the credit card information")
    @Alias("I confirm to finish the purchase")
    def purchaseReissuableAward(){
        reissuePricingPage.clickOnPurchase()
    }

    @When("I choose my security question 1")
    def chooseSecurityQuestion1() {
        createAnAccountPage.chooseSecurityQuestion1()
    }

    @When("I choose my security question 2")
    def chooseSecurityQuestion2() {
        createAnAccountPage.chooseSecurityQuestion2()
    }

    @When("I deselect my security question 1")
    def deSelectSecurityQuestion1() {
        createAnAccountPage.deselectSecurityQuestion1()
    }

    @Given("I login as an Alist Preferred Rapid Rewards member on the Rapid Rewards Account page")
    void loggedInAsAlistPreferredRapidRewardsMember() {
        onRapidRewardsAccountPage()
        myAccountPage.logIn("AlistPreferred")
        verifyOnRapidRewardsAccountPage()
    }

    @When("I click on Enroll link in RR Menu")
    def clickOnEnrollInRRMenu(){
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuRapidRewards()
            globalNavigationHeader.clickOnEnrollLink()
        } else {
            globalNavigationHeader.openRapidRewardsSubMenu().clickOnEnrollLink()
        }
    }

    @When("I view the submit button")
    def checkSubmitButtonIsPresent() {
        createAnAccountPage.verifySubmitButtonPresent()
    }

    @When("I confirm my account creation on the Review Page")
    def confirmAccountCreationOnReviewPage(){
        addressValidationReviewPage.clickConfirm()
        confirmCustomerPage.verifyPage()
    }

    @Given("I am on the Review page with my address and name information successfully cleansed")
    def continueToReviewPageWithCleansedInformation(){
        goToCreateAnAccountPage()
        completeEnrollmentFieldsAndMoveToReviewPage(true)
    }

    @When("I choose to edit my account information")
    def clickOnEditButton(){
        addressValidationReviewPage.clickEdit()
        createAnAccountPage.verifyPage()
    }

    @When("I submit the Enrollment form filling the password fields")
    def completePasswordFieldsAndSubmit(){
        createAnAccountPage.fillInPassword(RapidRewardsAccountFactory.createRapidRewardsAccount())
        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
    }

    @Given("I am on the Review page with my address information NOT cleansed")
    def continueToReviewPageWithNonCleansedInformation(){
        goToCreateAnAccountPage()
        completeEnrollmentFieldsAndMoveToReviewPage(false)
    }

    @Given("I go to the Enrollment Confirmation page")
    def goToConfirmationEnrollPage(){
        createAnAccountPage.open()
        createAnAccountPage.verifyPage()
        completeEnrollmentFieldsAndMoveToReviewPage(false)
        addressValidationReviewPage.clickConfirm()
        confirmCustomerPage.verifyPage()
    }

    @When("I go back to the Enrollment page")
    def goBackSinceEnrollmentConfirmationToEnrollmentPage(){
       confirmCustomerPage.clickBrowserBackButton()
       confirmCustomerPage.switchAndAcceptAlert()
       addressValidationReviewPage.verifyOnCurrentPage()
       addressValidationReviewPage.clickEdit()
       createAnAccountPage.verifyPage()
    }
    @When("I click Update button")
    def clickUpdateButton(){
        myAccountPage.clickUpdateButton()
    }
    @Then("I should see \$textValue in \$pageField textField")
    def verifyTextField(String textValue, String pageField){
        myAccountPage.verifyTextPresent(textValue,By.id(ELEMENT_IDS.get(pageField)))
    }

    @Then("I am redirected to the Altea Add a Companion page")
    def verifyAlteaAddCompanion(){
        addCompanionPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
        AlteaUrlValidationHelper.verifyLoyaltyAddCompanionLink(addCompanionPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }
    @Then("I am redirected to the Altea Cancel Reservation Page")
    def verifyAlteaCancelPage(){
        cancelAirReservationPage.waitForElement(AlteaUrlValidationHelper.PROXY_RESPONSE)
        AlteaUrlValidationHelper.verifyCancelReservationLink(cancelAirReservationPage.getCurrentUrl(), scenarioState.getLastAirReservation(), flow.isRapidRewards)
    }
    @When("I am on My Travel")
    def whenIViewAllMyUpcomingTrips() {
            myAccountPage.clickOnViewAllUpcomingTrips();
    }

    @Then("I am taken to the Account Login page")
    def onAccountLoginPage() {
        accountLogInPage.verifyPage()
    }

    @When("I click on the My Account link in the page footer")
    def clickOnMyAccountLinkInPageFooter() {
        homePage.clickOnMyAccountLinkInFooter()
    }

    @Then("I am taken to the My Account page")
    def onMyAccountPage() {
        myAccountPage.verifyPage()
    }

    @When("I click on companion confirmation number")
    def clickOnCompanionConfirmationNumber(){
        myAccountPage.clickCompanionConfirmationNumber()
    }

    @When("I attempt to use autocomplete to complete account number field")
    def attemptToFillPartialAccountNumberAndTryToAutoComplete() {
        RRUser user = flow.getRrUser()

        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.openLoginDropdown()
            globalNavigationHeader.attemptToFillInAccountNumber(user.getRRNumber())
        } else {
            loginForm.attemptToFillPartialAccountNumber(user.getRRNumber())
        }
    }

    @When("I click on My Preferences page")
    def clickOnMyPreferencesPage() {
        myAccountPage.clickOnMyPreferencesPage()
    }

    @When("I click on edit Username & Password button")
    def clickOnUserNameAndPasswordLink() {
        myAccountPage.clickOnUserNameAndPasswordEditLink()
    }

    @When("I attempt to change my password with a blacklisted password \$password")
    @Aliases(values = ["I attempt to change my password with a short password \$password",
           "I attempt to change my password with password containing invalid characters \$password"])
    def changePassword(String password) {
        myAccountPage.changePassword(flow.getRrUser().getRRPassword(), password)
    }

    @Then("I can see Last Activity Date in account bar")
    @Aliases(values= ["I can see Last Activity Date in recent reward activity section of my RR page",
           "I can see Last Activity Date"])
    def verifyLastActivityDateForAccount() {
        myAccountPage.shouldSeeLastActivityDate()
    }

    @Given("I am logged in as Rapid Rewards member with no qualified activity (as defined by Siebel) in last \$months months")
    void loggedInAsRapidRewardsMemberWithNoActivitywithMonths(int months) {
        String userType
        if(months >= 24) {
            userType = "accountExpiredUser"
        }
        loginAsRapidRewardsMember(userType)
    }

    @Given("I am logged in as Rapid Rewards member with no qualified activity (as defined by Siebel) in last \$days days")
    void loginWithRapidRewardMemberWithNoActivityWithDays(int days) {
        String userType
        if(days == 30){
            userType = "goodUserWithNoActivityInLast30Days"
        }
        loginAsRapidRewardsMember(userType)
    }

    @When("I click on the Question Mark icon")
    def clickOnLastActivityDateHelpIcon() {
        myAccountPage.clickOnLastActivityDateHelpIcon()
    }

    @Given("I am at the My Account Page")
    def myAccountPage() {
        myAccountPage.clickMyAccount()
        myAccountPage.clickMyRapidRewardsLink()
        myAccountPage.clickPromotionBookTripButton()
    }

    @When("I select a round-trip flight with a promo cert")
    def bookRoundTripTripWithPromoCert() {
        itineraryData.itineraryType = ItineraryData.ROUND_TRIP_ITINERARY
        bookTripWithPromoCert()
    }

    @When("I select a one-way flight with a promo cert")
    def bookOneWayTripWithPromoCert() {
        itineraryData.itineraryType = ItineraryData.ONE_WAY_ITINERARY
        bookTripWithPromoCert()
    }

    def bookTripWithPromoCert(){
        bookATripModal.verifyModal()
        Map yaml = itineraryData.yamlItineraryData()
        if (!itineraryData.isMultiCarrierItinerary()){
            if(itineraryData.isOneWay()){
                itineraryData.departureStation = yaml.oneway.departAirport
                itineraryData.arrivalStation= yaml.oneway.arriveAirport
            }
            else (itineraryData.isRoundTrip()){
                itineraryData.departureStation = yaml.roundtrip.departAirport
                itineraryData.arrivalStation= yaml.roundtrip.arriveAirport
            }
        }
        itineraryData.departingFlight_fareClass = "PromoCert"
        itineraryData.arrivingFlight_fareClass = "PromoCert"

        bookATripModal.fillInFlightSearchInfoAndClick(itineraryData, false, "tripSearchModalSubmit")
        selectFlightsPage.verifyPromoCertNotificationContainer()
        selectFlightsPage.verifyCertificateRadioButtonIsSelected()
        selectFlightsPage.selectFlights()
        selectFlightsPage.saveOutboundRouteTypeAndDetails()
        if (itineraryData.isRoundTripOrOpenJaw()){
            selectFlightsPage.saveInboundRouteTypeAndDetails()
        }
        selectFlightsPage.clickContinue()
    }

    @Given("I want to book a flight eligible for check in")
    def bookFlightEligibleForCheckin(){
        itineraryData.departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
        itineraryData.returnDate = ItineraryDateFactory.chooseDayEligibleForReturnCheckin()
    }

    @Then("I see a flyout dialog box for more information")
    def verifyLastActivityDatePopUp() {
        myAccountPage.shouldSeeLastActivityDatePopUp()
    }

    @When("I click on the View Details icon in the Recent Rewards Activity section of My RR page")
    def clickOnViewDetailsIcon(){
        myAccountPage.clickOnViewDetailsIcon();
    }

    @Then("I see a Account Expiration countdown in month(s) & days(s) based on rules")
    def verifyAccountExpirationDate(){
        myAccountPage.shouldSeeAccountExpirationDate("Expiration Date")
    }

    @When("I attempt to add another phone number")
    def addOtherPhoneNumber() {
        myAccountPage.clickOnAddAnotherPhoneNumber()
        myAccountPage.verifyPage()
    }

    @When("I attempt to cancel the hotel product on Upcoming Trips page by clicking on Cancel Reservation link")
    def attemptToCancelHotelProductOnUpcomingTripPage() {
        upcomingTripsPage.clickOnHotelCancelReservationLink()
    }

    @When("I attempt to cancel the car product on Upcoming Trips page by clicking on Cancel Reservation link")
    def attemptToCancelCarProductOnUpcomingTripPage() {
        upcomingTripsPage.clickOnCarCancelReservationLink()
    }

    @When("I complete the new Rapid Rewards member creation")
    def createRRInfoForNewUser(){
       def rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccountForCert()
        createAnAccountPage.fillInRequiredInformation(rrAccount)
        createAnAccountPage.clickOnCreateARapidRewardAccount()
        createAnAccountPage.submit()
        addressValidationReviewPage.verifyPage()
        addressValidationReviewPage.clickConfirm()
    }

    @Then("I see the modal for single product car cancellation")
    def verifyStandAloneCarCancellationModal() {
        upcomingTripsPage.verifyStandAloneCarCancellationModal()
    }

    @Then("I see the modal for single product hotel cancellation")
    def verifyStandAloneHotelCancellationModal() {
        upcomingTripsPage.verifyStandAloneHotelCancellationModal()
    }

    @Then("I see the modal for multiple product hotel cancellation")
    def verifyMultipleProductHotelCancellationModal() {
        upcomingTripsPage.verifyMultipleProductHotelCancellationModal(itineraryData)
    }

    @Then("I see the modal for multiple product car cancellation")
    def verifyMultipleProductCarCancellationModal() {
        upcomingTripsPage.verifyMultipleProductCarCancellationModal(itineraryData)
    }

    @Given("I have stored all of my account information")
    def storeMyAccountInfoForFutureUse() {
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.openNewHeaderMenuRapidRewards()
            globalNavigationHeader.clickOnMyAccountLink()
        } else {
            searchFlightsPage.clickAccountSnapshotLink()
        }
        myAccountPage.clickOnMyPreferencesPage()
        myAccountPage.gotoContactInformation()
        getAccountInfo()
    }

    @Given("I have stored all of Credit Card information")
    def storeMyCCForFutureUse() {
        myAccountPage.gotoPaymentInformationPage()
        getPaymentInfo()
        flow.useSavedCreditCard = true
    }

    def getPaymentInfo() {
        rrAccountData.populateCCInformation(myAccountPage.retrieveCCinformation())
        myAccountPage.clickAddEditLink("credit card information")
        myAccountPage.storeCreditCardBillingAdress()
    }

    private void getAccountInfo() {
        if(flow.isCustomer) {
            rrAccountData.populateCustomerInformation(myAccountPage.retrieveContactInformationElements())
        } else {
            rrAccountData.populateInformation(myAccountPage.retrieveContactInformationElements(), myAccountPage.retrieveAccountNumber())
        }
    }

    @When("I click on points in the Book A Trip Modal")
    def clickOnPointsInBookATripModal() {
        bookATripModal.clickOnPoints()
    }

    @Given("the Promo Code field is enabled on the Book A Trip Modal")
    @Then("I see Promo Code field is enabled on the Book A Trip Modal")
    def promoCodeIsEnabled() {
        flightSearchForm.promoCodeIsEnabled()
    }

    @Given("the Senior Passenger Count field is enabled on the Book A Trip Modal")
    def seniorPassengerCountFieldIsEnabled() {
        flightSearchForm.seniorPassengerCountIsEnabled()
    }

    @When("I fill in the form with the same data and submit")
    def fillInSameInformation(){
        RapidRewardsAccount rrAccount = RapidRewardsAccountFactory.createRapidRewardsAccount()
        rrAccount.setFirstName(flow.rrUser.getFirstName())
        rrAccount.setLastName(flow.rrUser.getLastName())
        createAnAccountPage.fillInRequiredInformation(rrAccount)
        createAnAccountPage.fillInMiddleName(flow.rrUser.getMiddleName())
        createAnAccountPage.clickOnCreateARapidRewardAccount()
        createAnAccountPage.submit()
        addressValidationReviewPage.clickConfirm()
    }

    @When("I click in My Travel link in the account bar")
    def clickMyTravelLynk(){
        myAccountPage.clickAccountBarMyTravelLink()
    }

    @Then("I see Senior Passenger Count field is disabled on the Book A Trip Modal")
    def seniorPassengerCountFieldIsDisabled() {
        flightSearchForm.seniorPassengerCountIsDisabled()
    }

    @Given("I change my username to: \$usernameChosen")
    def changeUsernameTo(String usernameChosen) {
        createAnAccountPage.attemptFillInUsername(usernameChosen)
    }

    @When("I add a buddy name and email under Share Your Itinerary section")
    def addShareYourItineraryData() {
        myAccountPage.addBuddyData()
    }

    @When("I click on Update button")
    def updateCommunicationPreferences() {
        myAccountPage.clickOnUpdateCommunicationPreferences()
    }

    @When("I save Call Me as preferred method of contact")
    def fillInContactMethodsInfo() {
        myAccountPage.fillPreferredMethodOfContactSection()
        myAccountPage.verifyPage()
    }

    @Given("I login as an A-list member")
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

    @Given("I login as a member with a preferred name")
    def logInAsAMemberWithAPreferredName() {
        def user = createRRUser("preferredName")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }

    @Given("I login as an RR member with a stored CC")
    def logRRwithCC() {
        def user = createRRUser("RRCreditCard")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }

    @When("I login as an RR member")
    @Given("I login as an RR member")
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

    @Given("I login as a Customer user")
    def loginAsCustomerUser() {
        def user = createRRUser("customer")
        if (ToggleHomepage2.isOn() && globalNavigationHeader.isHomePage()) {
            logIntoCustomerAccount(user)
        } else {
            accountBar.logInAsCustomer(user.getAccountName(), user.getPassword())
        }
    }
    @Given("I login as an RR Senior member with a stored CC")
    def logRRSeniorWithCC() {
        def user = createRRUser("RRSeniorCreditCard")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }

    @Given("I login as an RR Minor member with Points")
    def logRRMinorPoints(){
        def user = createRRUser("RRMinorPoints")
        if (useNewHomePageToLogin()) {
            logIntoRRAccount(user)
        } else {
            accountBar.logInToRapidRewards(user.getRRNumber(), user.getRRPassword())
        }
    }

    private boolean useNewHomePageToLogin() {
        ToggleHomepage2.isOn() && globalNavigationHeader.isHomePage()
    }

    private void logIntoCustomerAccount(RRUser user) {
       logIntoAccount(user)
        flow.isCustomer = true
    }

    private void logIntoRRAccount(RRUser user) {
        logIntoAccount(user)
        flow.isRapidRewards = true
        flow.userLoggedInRapidRewardsNumber = flow.getUser().getUserName()
    }

    private void logIntoAccount(RRUser user) {
        globalNavigationHeader.logIntoAccount(user)
        flow.isLoggedIn = true
    }

    RRUser createRRUser(String userType = "goodUser") {
        userType = (userType.equalsIgnoreCase("valid")) ? "goodUser" : userType
        assert [userType] ==
                ["noAPlusCredits", "preferredName", "noDebitRapidRewardsAccount", "awardsOnly", "creditsOnly", "pointsOnly", "AlistPreferred", "Alist", "invalid", "promoUser", "goodUser", "goodUserWithNoActivityInLast30Days", "accountExpiredUser", "promoCertUser", "customer", "RRCreditCard", "RRSeniorCreditCard","RRMinorPoints"].grep(userType)
        RRUser user = flow.isRapidRewardsPointsPurchaseOnly? RRUserQueue.pollUser(): data.getUser(userType)

        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().createRRMember(user)
        }

        flow.setUser(user)
        userType = (userType.equalsIgnoreCase("goodUser") || userType.equalsIgnoreCase("RRCreditCard") || userType.equalsIgnoreCase("RRSeniorCreditCard") || userType.equalsIgnoreCase("RRMinorPoints") || userType.equalsIgnoreCase("preferredName")) ? "Rapid Rewards Member" : "A-List"
        flow.setUserLoggedInRapidRewardsAccountType(userType)
        return user
    }
}
