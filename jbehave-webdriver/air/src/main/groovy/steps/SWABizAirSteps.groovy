package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.elements.FlightSearchForm
import util.ItineraryData
import pages.*
import org.jbehave.core.annotations.Named
import fixture.stubs.DynaStubsIntegration
import domain.AirReservation
import state.Flow
import state.PassengerData
import domain.Passenger
import state.ScenarioState
import state.CompanyData
import com.swacorp.dotcom.webscenarios.air.SwaBizData
import org.jbehave.core.annotations.Then
import org.openqa.selenium.By

class SWABizAirSteps {

    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    ItineraryData itineraryData
    Data data
    SwaBizPage swaBizPage
    SwaBizTravelAccountLoginPage swaBizTravelAccountLoginPage
    SwaBizLinkWithCompanyPage swaBizLinkWithCompanyPage
    PurchasePage purchasePage
    MyAccountPage myAccountPage
    FlightSearchForm flightSearchForm
    TravelCenterBusinessUpgradePage travelCenterBusinessUpgradePage
    AirSearchSteps airSearchSteps
    AirConfirmationSteps airConfirmationSteps
    NavigationSteps navigationSteps
    AirPurchaseSteps airPurchaseSteps
    ScenarioState scenarioState
    PassengerData passengerData
    SelectFlightsPage selectFlightsPage
    AirSelectSteps airSelectSteps
    SwabizTermsAndConditionsPage swabizTermsAndConditionsPage
    SwabizSetupCompanyPage swabizSetupCompanyPage
    SwabizSetupTravelManagerPage swabizSetupTravelManagerPage
    SwaBizEnrollmentPage swaBizEnrollmentPage
    SwaBizCompletePage swaBizCompletePage
    SwaBizHomePage swaBizHomePage
    Flow flow
    CompanyData companyData
    SwaBizData swabiz_data
    SwabizBookTravelPage swaBizBookTravelPage

    @Given("I am a SWABiz Anonymous user logged into a SWABiz account")
    def swaBizAnonymousUserLoggingInWithNewCompanyId() {
        if (!DynaStubsIntegration.useDynaStubs() &&
            (swaBizPage.getCompanyId().equals(swaBizPage.getDEFAULT_CID()) ||
             swaBizPage.getCompanyId().equals(""))) {
            generateCompany()
            swaBizPage.setCompanyId(data.swaBizTravelAccountCompanyId)
        }
        swaBizPage.loginAsAnonymousSWABizUser()
    }

    @Given("I am a SWABiz Anonymous user")
    @Alias("I have anonymously logged into a SWABiz account")
    def swaBizAnonymousUserCompanyLogin() {
        swaBizPage.loginAsAnonymousSWABizUser()
    }

    @Given("I open the Swabiz Search Booking page")
    @When("I open the Swabiz Search Booking page")
    def swaBizOpenSearChBookingPage() {
        swaBizPage.openCompanyTravelReservationPage()
    }

    @Given("I select flight in Swabiz")
    @When("I select flight in Swabiz")
    def swaBizSelectFlightInSwabiz() {
        selectFlightsPage.selectFlights()
    }

    @Given("I am on the swabiz home page")
    def open() {
        swaBizTravelAccountLoginPage.open()
    }

    @Given("I work for a SwaBiz company \$cid")
    def addSWABIZCompany(String cid) {
        swaBizPage.setCompanyId(cid)
    }

    @Given("I work for a company with embedded dollar off discount")
    def setEmbeddedDollarDiscountCompany() {
        addSWABIZCompany(swabiz_data.getDiscountCode("SB_company_dollarsOff_all"))
    }

    @Given("I work for a company with embedded percent off discount")
    def setEmbeddedPercentDiscountCompany() {
        addSWABIZCompany(swabiz_data.getDiscountCode("SB_company_percentOff_all"))
    }

    @Given("I have booked a round trip flight on swabiz selecting the option save time on futures purchases")
    def bookRoundTripFlightAnonymousSwabizUserSavingCreditCardInfo() {
        swaBizAnonymousUserCompanyLogin()
        onlySelectAFlight()
        selectFlightsPage.selectFlights()
        airSelectSteps.continueToPricePage()
        navigationSteps.continueToPurchasePage()
        airPurchaseSteps.fillOutPurchaseForm()
        purchasePage.clickSaveCreditCardHolderInfo()
        navigationSteps.clickContinueToComplete()
    }

    @Given("I am an anonymous SWABiz user with a booked flight eligible for checkin \$passengerCount \$passengerType")
    def bookSwabizRoundtrip(int passengerCount, String passengerType) {
        swaBizAnonymousUserCompanyLogin()
        airConfirmationSteps.bookAFlightLeavingToday(passengerCount, passengerType)
    }

    @Given("I am logged in as a SWABIZ Company Travel Manager")
    def swaSWABizTravelManagerLogin() {
        swaBizPage.loginAsCompanyTravelManager()
    }

    @Given("I am logged in as a SWABIZ CTM")
    def swabizLoginsAsCTM() {
        swaBizPage.loginAsCTM()
    }

    @Given("I am logged in as a SWABIZ Company Travel Manager with CID: \$companyId, First Name: \$firstName, LastName: \$lastName and Password: \$password")
    def SWABizLoginAsCTMAtEFix(String companyId, String firstName, String lastName, String password) {
        swaBizPage.setCompanyId(companyId)
        swaBizPage.setTravelManagerFirstName(firstName)
        swaBizPage.setTravelManagerLastName(lastName)
        swaBizPage.setPassword(password)
        swaBizPage.loginAsCTM()
    }

    @Given("I am booking in behalf of a traveler")
    def bookForTravelerAsCTM() {
        swaBizPage.bookForTravelerAsCTM()
    }

    @Given("I am on the SWABIZ Business Select Upgrade Page")
    void openBusinessUpgradePage() {
        travelCenterBusinessUpgradePage.open()
    }

    @Given("I have a flight booked for a SWABiz Anonymous user")
    def createAnAirReservationOnSwabiz() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [swabiz: true] , passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            if (flightSearchForm.waitFor({flightSearchForm.isJavaScriptsDoneLoading()})) {
                flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
                Passenger passenger = passengerData.passengers[0]
                if (passenger?.youngChild) {
                    selectFlightsPage.verifyPage()
                    selectFlightsPage.selectFlights()
                    airConfirmationSteps.priceAndPurchaseFlightAsYoungTraveler()
                } else {
                    airConfirmationSteps.selectAndPurchaseAFlight()
                }
            }
        }
    }

    @Given("I have a flight booked with \$currency on SWABiz")
    def createAnAirReservationOnSwabizWithCurrency(String currency) {
        passengerData.addLoggedInPassenger()
        boolean isPoints = false
        if (currency.equals("points")) {
            isPoints = true
        }
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [swabiz: true, points: isPoints, rapidRewardsAccountNumber:flow.userLoggedInRapidRewardsNumber] , passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            if (flightSearchForm.waitFor({flightSearchForm.isJavaScriptsDoneLoading()})) {
                flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
                Passenger passenger = passengerData.passengers[0]
                selectFlightsPage.verifyPage()
                if (isPoints) {
                    selectFlightsPage.togglePoints()
                }
                selectFlightsPage.selectFlights()
                if (passenger?.youngChild) {
                    airConfirmationSteps.priceAndPurchaseFlightAsYoungTraveler()
                } else {
                    airConfirmationSteps.selectAndPurchaseAFlight()
                }
            }
        }
    }

    @Given('I am a SWABIZ Traveler located in the Traveler Account Login')
    def goToSwabizTravelerLogin(){
        swaBizTravelAccountLoginPage.open()
        swaBizTravelAccountLoginPage.verifyPage()

        if (!DynaStubsIntegration.useDynaStubs()) {
            generateCompany()
        }
        swaBizTravelAccountLoginPage.selectTravelAccountLogin()
        swaBizTravelAccountLoginPage.verifyPage()
    }

    private generateCompany(){
        companyData.generateRandomCompany()

        swaBizHomePage.open()
        swaBizHomePage.verifyPage()
        swaBizHomePage.clickOnEnrollNowButton()

        swabizTermsAndConditionsPage.verifyPage()
        swabizTermsAndConditionsPage.selectAcceptTermsCheckbox()
        swabizTermsAndConditionsPage.clickSubmitButton()

        swabizSetupCompanyPage.verifyPage()
        swabizSetupCompanyPage.fillRequiredFields(companyData)
        swabizSetupCompanyPage.clickSubmitButton()

        swabizSetupTravelManagerPage.verifyPage()
        swabizSetupTravelManagerPage.fillRequiredFields(companyData)
        swabizSetupTravelManagerPage.clickSubmitButton()

        swaBizEnrollmentPage.verifyPage()
        swaBizEnrollmentPage.fillRequiredFields(companyData)
        swaBizEnrollmentPage.clickSubmitButton()

        swaBizCompletePage.verifyPage()
        data.swaBizTravelAccountCompanyId = swaBizCompletePage.getCompanyId()
    }

    @Given("I have my account setup with an associated company ID")
    @Alias("I am a SWABIZ Traveler Member")
    def rrUserWithAssociatedCompany() {
        rrUserWithNoAssociatedCompany()
        associateExistingRRUserWithCompany()
    }

    @Given("I have my existing account setup with an associated company ID")
    def associateExistingRRUserWithCompany() {
        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().associateCustomerToCompany(flow.rrUser.number, data.getswaBizTravelAccountCompanyId())
        } else {
            travelerAccountLogin()
            confirmAssocationWithCompany()
            myAccountPage.logOutClick()
            swaBizTravelAccountLoginPage.verifyPage()
        }
    }

    @Given("All travelers must select from Company-Defined IRNs")
    def onlyCompanyIRN(){
        data.swaBizTravelAccountCompanyId = "99011099"
    }

    @Given("I have my RR account setup with an associated company ID")
    def RRAcountWithAssociatedCompany() {
        if (DynaStubsIntegration.useDynaStubs()){
            RRUser rrUser = new DynaStubsIntegration().generateRRUser()
            flow.rrUser = rrUser
            flow.hasGeneratedUser = true
            flow.userLoggedInFirstName = rrUser.getRRFirstName()
            flow.userLoggedInLastName = rrUser.getRRLastName()
            flow.userLoggedInGender = rrUser.getRRGender()
            flow.userLoggedInRapidRewardsNumber = rrUser.RRNumber
            flow.userLoggedInRapidRewardsAccountType = rrUser.accountType
            new DynaStubsIntegration().associateCustomerToCompany(rrUser.number, data.getswaBizTravelAccountCompanyId())
        }
    }

    @Given("I have my account setup with no associated company ID")
    def rrUserWithNoAssociatedCompany() {
        if (DynaStubsIntegration.useDynaStubs()){
            RRUser rrUser = new DynaStubsIntegration().generateRRUser()
            flow.rrUser = rrUser
            flow.hasGeneratedUser = true
        }
    }

    @Given("I login as a Traveler")
    @When("I login as a Traveler")
    enterCredentialsForTravelAccount() {
        swaBizTravelAccountLoginPage.open()
        swaBizTravelAccountLoginPage.selectTravelAccountLogin()
        swaBizTravelAccountLoginPage.enterCredentialsForTravelAccount()
    }

    @When("I am a swabiz customer searching for flights from \$departure to \$arrival")
    def searchForRoundTrip(@Named("departure") String departureStation, @Named("arrival") String arrivalStation) {
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
        flightSearchForm.searchForRoundTrip(itineraryData)
    }

    @When("I am on the car reservation page")
    void goToCarRentalPage() {
        swaBizPage.goToCarRentalPage()
    }

    @When("I search for a flight for an adult and young traveler")
    void searchFlightForAdultYT() {
        flightSearchForm.selectYoungTravelerAndAdult(1, 1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I search for a flight for an unaccompanied child")
    void searchFlightForUm() {
        flightSearchForm.selectChildAndYouthPassengers(1, 0)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I book a new swabiz flight with no promo code")
    void enterNewInformationWithoutPromoCode() {
        swaBizPage.clickOnBookTravel()
        flightSearchForm.clearPromoCode()
        flightSearchForm.selectMultipleAdultPassengers(1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @Given("I search for a flight")
    @When("I search for a flight")
    void whenIClickOnBookTravelLink() {
        swaBizPage.clickOnBookTravel()
        flightSearchForm.selectMultipleAdultPassengers(1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I search for a flight as a Rapid Rewards customer")
    void whenIClickOnBookTravelLinkAsARapidRewardsCustomer() {
        swaBizPage.clickOnBookTravel()
        flightSearchForm.selectMultipleAdultPassengersWithRapidRewards(1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
    }

    @When("I enter my flight information")
    void whenIEnterItineraryInformation() {
        if (flightSearchForm.waitFor({flightSearchForm.isJavaScriptsDoneLoading()})){
            flightSearchForm.selectMultipleAdultPassengers(1)
            flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        }
    }

	@Given("I have a booked SwaBiz flight")
    @When("I have a booked SwaBiz flight")
    void whenIBookSwaBiz() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [swabiz: true])
            scenarioState.add pnr
        }
        else {
            swaBizPage.clickOnBookTravel()
            whenIClickOnBookTravelLink()
            airConfirmationSteps.selectAndPurchaseAFlight()
        }
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the SWABIZ Business Select Upgrade page")
    void retrieveInvalidPNR() {
        travelCenterBusinessUpgradePage.retrievePnrForBusinessSelectUpgrade("ABC123", "Sam", "Leapfrog")
    }

    @When("I click where we fly link on the swabiz search page")
    def clickWhereWeFlyLinkOnSwabizSearchPage() {
        swaBizPage.clickWhereWeFlyLinkOnSwabizSearchPage()
    }

    @When("I click where we fly link on the swabiz low fare calendar")
    def clickWhereWeFlyLinkOnSwabizShortCutPage() {
        swaBizPage.clickWhereWeFlyLinkOnSwabizShortCutPage()
    }

    @Given("I have clicked on the Low Fare Calendar link")
    @When("I click on the Low Fare Calendar link")
    def clickLowFareCalendarLink() {
        swaBizPage.clickLowFareCalendarLink()
    }

    @Given("I only search a flight on Swabiz")
    @When("I only search a flight on Swabiz")
    def onlySelectAFlight() {
        if (flightSearchForm.waitFor({flightSearchForm.isJavaScriptsDoneLoading()})) {
            flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
            selectFlightsPage.verifyPage()
        }
    }

    @Given("I login into SWABIZ by entering my company ID, my account number and password")
    @When("I login into SWABIZ by entering my company ID, my account number and password")
    def travelerAccountLogin(){
        swaBizTravelAccountLoginPage.enterCredentialsForTravelAccount()
        swaBizTravelAccountLoginPage.verifyPage()
    }

    @When("I confirm my association with the company")
    def confirmAssocationWithCompany() {
        swaBizLinkWithCompanyPage.clickSubmit()
        myAccountPage.verifyPage()
    }

    @When("I decline the association with the company")
    def declineAssocationWithCompany() {
        swaBizLinkWithCompanyPage.clickCancel()
        swaBizLinkWithCompanyPage.verifyPage()
    }

    @When("I select a ghost card")
    def selectGhostCard() {
        purchasePage.fillInPurchaseWithGhostCard()
    }

    @When("I attempt to book a travel selecting \$stationCode as a departure station")
    def selectDepartureStation(String stationCode){
        swaBizPage.clickOnBookTravel()
        swaBizPage.verifyPage()
        flightSearchForm.selectDepartureStation(stationCode)
    }

    @When("I attempt to book a travel selecting \$stationCode as an arrival station")
    def selectArrivalStation(String stationCode){
        swaBizPage.clickOnBookTravel()
        swaBizPage.verifyPage()
        flightSearchForm.selectArrivalStation(stationCode)
    }

    @Given("I have a flight booked affected by a SODA event for a SWABiz Anonymous user")
    def createAnAirReservationOnSwabizWithSODAEvent() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [sodaAffectedMark: true, swabiz: true] , passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @When("I navigate to the swabiz My Preferences page")
    def rapidRewardsMemberOnThePreferencePage() {
        myAccountPage.goToMyPreferences()
        myAccountPage.verifyPage()
    }

    @Given("I select the Book Travel tab")
    def selectBookTravelTab() {
        swaBizPage.clickOnBookTravel()
        swaBizPage.verifyPage()
    }

    @Given("I select the Show Fares in Points option")
    @When("I select the Show Fares in Points option")
    def selectShowFareInPointsOption() {
        swaBizBookTravelPage.clickOnShowFaresInPoints()
    }

    @When("I select the Show Fares in Dollars option")
    def selectShowFareInDollarsOption() {
        swaBizBookTravelPage.clickOnShowFaresInDollars()
    }

    @Given("the Senior Passenger Count option is enabled")
    @Then("the Senior Passenger Count option is enabled")
    @Alias("I see the Senior Passenger Count option is enabled")
    def seniorPassengerCountOptionIsEnabled() {
        swaBizBookTravelPage.seniorPassengerCountIsEnabled()
    }

    @Then("the Senior Passenger Count option is disabled")
    @Alias("I see the Senior Passenger Count Option is disabled")
    def seniorPassengerCountOptionIsDisabled() {
        swaBizBookTravelPage.seniorPassengerCountIsDisabled()
    }

    @Given("the Promo Code option is enabled")
    @Then("the Promo Code option is enabled")
    @Alias("I see the Promo Code option is enabled")
    def promoCodeOptionIsEnabled() {
        swaBizBookTravelPage.promoCodeIsEnabled()
    }

    @Then("the Promo Code option is disabled")
    def promoCodeOptionIsDisabled() {
        swaBizBookTravelPage.promoCodeIsDisabled()
    }

    @When("I navigate to Traveler Account Management")
    def accountTravelerManagement() {
        swaBizPage.clickOnTravelerAccountManagement()
    }

    @When("I click on generate report")
    def generateReport() {
        swaBizPage.clickOnGenerateReport()
    }

    @When("I try to book a flight for traveler with last name is: \$name")
    def selectTravelerWithLastName(String name) {
        swaBizPage.selectAccountWithLastName(name)
    }

    @Given("I am on the Select Flight page on SWABiz")
    def viewSelectFlightPage() {

        viewSelectFlightPage({})
    }

    void viewSelectFlightPage(Closure passengerClosure) {
        passengerClosure.call()

        itineraryData.departureStation = data.getRoute().from
        itineraryData.arrivalStation = data.getRoute().to

        flightSearchForm.fillInAirports(data.getRoute().from, data.getRoute().to)

        itineraryData.verifyValidDates()
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        flightSearchForm.enterReturnDate(itineraryData.returnDate)

        flightSearchForm.submit()
    }
}
