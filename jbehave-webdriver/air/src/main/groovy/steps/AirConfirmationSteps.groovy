package steps

import builders.RelativeDateTimeParser
import com.github.tanob.groobe.GrooBe
import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.apache.commons.lang.StringUtils
import org.jbehave.core.annotations.*
import org.joda.time.DateTime
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import pages.*
import pages.elements.*
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.*

import static org.junit.Assert.fail;
import static java.util.Calendar.YEAR
import com.swacorp.dotcom.webscenarios.air.CreditCardData
import com.swacorp.dotcom.webscenarios.air.CreditCard
import fixtures.air.enums.CreditCardRejectedReason
import steps.thenSteps.ConfirmationThenSteps

class AirConfirmationSteps {

    private final static HOME_PAGE_SUBMIT_BUTTON = "booking_widget_content_row_btn_search"
    private static final String INBOUND_AVAILABLE_FLIGHT = "55"

    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    RepricePage repricePage
    PricePage pricePage
    PriceTable priceTable
    PurchasePage purchasePage
    ConfirmationPage confirmationPage
    CancelReservationConfirmationPage cancelReservationConfirmationPage
    ChangeAirReservationPage changeAirReservationPage
    SwaBizPage swaBizPage
    AirTranSeatSelectPage airTranSeatSelectPage
    ItineraryData itineraryData
    Itinerary itinerary
    NavigationSteps navigationSteps
    AirSearchSteps airSearchSteps
    AirSelectSteps airSelectSteps
    AirPurchaseSteps airPurchaseSteps
    AirCancelSteps airCancelSteps
    CommonSteps commonSteps
    FlightSearchForm flightSearchForm
    PassengerData passengerData
    AirPricingSteps airPricingSteps
    CancelAirReservationPage cancelAirReservationPage
    Flow flow
    Data data
    GlobalNavigationHeader globalNavigationHeader
    AirGuardianPurchaseSteps airGuardianPurchaseSteps
    ScenarioState scenarioState
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    HomePage homePage
    HomePageFlightSearchForm homePageFlightSearchForm
    CrossBookingSteps crossBookingSteps
    LowFareCalendarPage lowFareCalendarPage
    MyAccountPage myAccountPage
    RapidRewardsSteps rapidRewardsSteps
    AccountSteps accountSteps
    NonRevLandingPage nonRevLandingPage
    PayPalPage payPal;
    ShoppingCart shoppingCart
    RRContactInformation rrContact
    PricePageData pricePageData
    CustomerInfoData customerInfoData
    RapidRewardsAccountBar rapidRewardsAccountBar
    DisabilitiesPage disabilitiesPage
    CreditCardSubForm creditCardSubForm
    ConfirmationThenSteps confirmationThenSteps
    PayPalPage payPalPage
    PassengerInfo passengerInfo

    def AirConfirmationSteps() {
        GrooBe.activate()
    }

    @Given("I have booked an Early Bird eligible flight with \$numberOfPax passengers on a trip named \$tripName")
    def earlyBirdEligibleItineraryForMultiPaxOnANamedTrip(int numberOfPax, String tripName) {

        flow.tripName = tripName

        itineraryData.adultPassengerCount = numberOfPax
        createEarlyBirdEligibleItinerary()
    }

    @Given("I have booked a flight")
    @When("I book a flight")
    def bookAFlight() {
        def adultCount = itineraryData.adultPassengerCount ?: "0"
        def seniorCount = itineraryData.seniorPassengerCount ?: "0"
        if (adultCount.equals("0") && seniorCount.equals("0")) {
            adultCount = "1"
        }
        bookAFlightAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(adultCount.toInteger(), seniorCount.toInteger()) })
    }

    @Given("I have a flight booked")
    @When("I have a flight booked")
    def createAnAirReservation() {
        createAnAirReservation(false, false);
    }

    @When("I click on 'View Details' on the car rental option")
    def clickCarRentalOption(){
        confirmationPage.clickOnCarRentalOption()
    }

    def createAnAirReservation(boolean missingATTicketNumber, boolean eligibleForCheckin) {
        if (DynaStubsIntegration.useDynaStubs()) {
            Map params = [tripName: "", missingAirTranTicketNumber: missingATTicketNumber];
            if (eligibleForCheckin) {
                params.put("eligibleForCheckIn","eligibleForCheckIn")
            }
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, params, passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            bookAFlight()
        }
    }

    @Given("I have a flight booked for checkin and round trip")
    def createAnAirReservationForCheckinAndRoundTrip() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "", eligibleForReturnCheckIn: "eligibleForReturnCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            bookAFlight()
        }
    }

    @Given("I have an OJ flight booked eligible for checkin")
    @When("I have an OJ flight booked eligible for checkin")
    def createAnAirReservationForOJ() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "", eligibleForReturnCheckIn: "eligibleForReturnCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            bookAFlight()
        }
    }

    @Given("I have a flight booked for BS")
    def createAnAirReservationForBS() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [upgradeToBS: true], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            bookAFlight()
        }
    }

    @Given("I have booked a flight using the accordion page")
    @When("I book a flight using the accordion page")
    def bookAFlightUsingAccordionPage() {
        def adultCount = itineraryData.adultPassengerCount ?: "0"
        def seniorCount = itineraryData.seniorPassengerCount ?: "0"
        if (adultCount == "0" && seniorCount == "0"){
            adultCount = "1"
        }
        bookAFlightAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(adultCount.toInteger(), seniorCount.toInteger()) },true)
    }

    def havePurchasedAFlight(String itineraryType, String tripName = null) {
        if (itineraryType == "round-trip") {
            commonSteps.setupRoundTripCarrierFlight("Southwest", "Southwest")
        } else if (itineraryType == "open-jaw") {
            commonSteps.setupOpenJawCarrierFlight("Southwest", "Southwest")
        }
        else {
            commonSteps.setupOneWayCarrierFlight("Southwest")
        }
        flow.tripName = tripName

        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dynaStubs = new DynaStubsIntegration()
            AirReservation pnr = dynaStubs.createAirReservation(itineraryData, [tripName: flow?.tripName], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            attemptToPurchaseAFlight()
            confirmationPage.verifyPage()
            confirmationPage.storeCurrentPNR()
        }
    }

    @Given("I have purchased a \$itineraryType flight")
    @Alias("I have purchased an \$itineraryType flight")
    def havePurchasedAFlightForAParticularItinerary(String itineraryType) {
        havePurchasedAFlight(itineraryType)
    }

    @Given("I have a \$itineraryType flight booked on a new trip named \$tripName")
    @Alias("I have an \$itineraryType flight booked on a new trip named \$tripName")
    def havePurchasedAFlightForAParticularItineraryOnATrip(String itineraryType, String tripName) {
        havePurchasedAFlight(itineraryType, tripName)
    }

    @Given("I have booked a round-trip \$carrier1 \$carrier2 flight for an adult")
    def bookRoundTripFlightForAnAdult(String carrier1, String carrier2) {
        flow.hasAir = true
        commonSteps.setupRoundTripCarrierFlight(carrier1, carrier2)

        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: ""], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            purchaseAFlight()
        }
    }

    @Given("I have booked a flight from \$origin to \$destination departing at \$departureDateTimeSpec")
    def bookACheckInFlightWithParameters(String origin, String destination, String departureDateTimeSpec) {
        commonSteps.setupOneWayConnectingFlight("SouthwestNS", "Nonstop")
        DateTime departureAt = new DateTime()
        // This is dynastubs only
        if (dynaStubsIntegration.useDynaStubs()) {
            departureAt = parseDateTime(departureDateTimeSpec)
            itineraryData.departureStation =  origin
            itineraryData.arrivalStation = destination
            itineraryData.departureDate = departureAt.toDate()
            itineraryData.departureTime = departureAt.toLocalTime();
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, null, passengerData.getPassengers())
            scenarioState.add pnr
            return
        }
     }

    @Given("I have booked a flight eligible for check-in for an Unaccompanied Minor")
    @Alias("I have booked a flight for an Unaccompanied Minor")
    def bookACheckInFlightForUM() {
        commonSteps.setupOneWayConnectingFlight("SouthwestNS", "Nonstop")
        if (dynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [eligibleForCheckIn: "eligibleForCheckIn", unaccompaniedMinor: "unaccompaniedMinor"], passengerData.getPassengers())
            scenarioState.add pnr
            return
        }
        itineraryData.departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
        airSearchSteps.addAMinorPassenger()
        searchFlightsPage.open()
        flightSearchForm.selectItineraryType(itineraryData)
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation, itineraryData.returnStation)
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        flightSearchForm.fillInPassengers()
        flightSearchForm.submit()
        selectFlightsPage.selectFlights()
        priceAndFillPurchaseInformation()
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
        airPurchaseSteps.clickNoButtonOnUmModal()
        airGuardianPurchaseSteps.fillDepartingUMGuardianForm()
        airGuardianPurchaseSteps.clickContinueOnTheUMGuardianForm()
        airPurchaseSteps.fillInCreditCardOnPurchasePageAndCompleteBookingAsUnaccompaniedMinor()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @Given("I am on the confirmation page")
    def viewConfirmationPage() {
        airSearchSteps.bookAFlightAdultAndSenior()
    }

    @Given("I am on the confirmation page after a successful booking")
    def iAmOnTheConfirmationPage() {
        confirmationPage.verifyBasicPage()
    }

    @Given("I have purchased two separate Air products without adding them to trips and with the same information")
    void bookTwoFlightsWithTheSameData() {
        if (DynaStubsIntegration.useDynaStubs()) {
            crossBookingSteps.addDynaStubsProducts([creditCardNumber: data.getStandardCreditCard(1).getWholeNumber()])
            crossBookingSteps.addDynaStubsProducts([creditCardNumber: data.getStandardCreditCard(1).getWholeNumber()])
        } else {
            flow.useStandardCreditCard = true
            bookARoundTripFlightForTomorrow()
            passengerData.clearPassengers()
            globalNavigationHeader.clickOnAirLinkMenu()
            bookARoundTripFlightForTomorrow()
        }
    }

    @Given("I have booked a round trip flight with Wanna get Away - web only fare in both flights")
    def bookFlightWithWannaGetAwayWebOnly() {
        itineraryData.isWebOnly = true
        airSearchSteps.viewSearchFlightPage()
        airSearchSteps.searchForRoundTrip()
        airSelectSteps.selectFlightsAndContinue()
        navigationSteps.continueToPurchasePage()
        airPurchaseSteps.fillOutPurchaseForm()
        navigationSteps.clickContinueToComplete()
    }

    @Given("I have cancelled a booked trip named \$tripName")
    def bookAndCancelANamedTrip(String tripName) {
        flow.tripName = tripName
        commonSteps.setupRoundTripCarrierFlight("Southwest", "Southwest")
        if (dynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: tripName, canceled: "canceled"], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            bookAFlight()
            airCancelSteps.cancelFlight()
        }
    }

    @Given("I have selected BS for the outbound flight and AT for the inbound flight and continued to purchase page")
    def setBSOutboundFlightATInboundFlight() {
        itineraryData.departingFlight_fareClass = "BusinessSelect"
        itineraryData.arrivingFlight_fareClass = "Anytime"
        if (!dynaStubsIntegration.useDynaStubs()) {
            airPurchaseSteps.searchAndSelectAFlight()
        }
    }

    @Given('I have searched and added an Air product to my shopping cart')
    void searchAFlighWithoutCompleteThePurchase() {
        searchForAFlightFromASpecificPageAndChooseCheckinEligibility(null, 'search flights page')
        addSelectedFlighToMyCart()
    }

    private def addSelectedFlighToMyCart() {
        selectFlightsPage.clickContinue()
    }

    @Given("I have finished an Early Bird purchase without adding the Air product to a trip")
    def finishPurchaseWithEarlyBird() {
        if (DynaStubsIntegration.useDynaStubs()) {
            itineraryData.earlyBird = true
            AirReservation pnr = new DynaStubsIntegration().createAirReservation(itineraryData, [tripName: ""], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            pricePage.clickContinue()
            purchasePage.verifyPage()
            purchasePage.uncheckNameThisTripOption()
            purchasePage.clickAddEarlyBird()
            airPurchaseSteps.fillOutPurchaseForm()
            navigationSteps.clickContinueToComplete()
        }
    }

    @Given("I have searched and booked a flight without adding this product to a trip")
    void searchForAFlightWithNoTrip() {
        flow.tripName = TripManagement.NO_TRIP
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dynaStubs = new DynaStubsIntegration()
            AirReservation pnr = dynaStubs.createAirReservation(itineraryData, [creditCardNumber: data.getRandomCreditCard().getWholeNumber()], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            flow.useStandardCreditCard = true
            searchForAFlightFromPageSpecified('search flights page')
        }
    }

    @Given("I have at least one Upcoming Trip in my account with an Air product which is not part of a trip")
    def setupAirProductWithDepartToday() {
        itineraryData.departureDate = new Date()
        setupAirProduct()
    }

    @Given("I have an Air product which is not part of a trip")
    def setupAirProductNotInATrip() {
        flow.tripName = TripManagement.NO_TRIP
        setupAirProduct()
    }

    private setupAirProduct(){
        flow.hasAir = true
        itineraryData.carSpecification = null
        itineraryData.hotelSpecification = null
        flow.isCarReservationPresent = false
        createAnAirReservation()
    }

    private DateTime parseDateTime(String departureDateTimeSpec){
        DateTime departureAt = new DateTime()
        departureAt = new RelativeDateTimeParser().parseRelativeDateTime(departureDateTimeSpec)
        return departureAt
    }

    @Given("I have an air reservation for a member with companion pass achieved and boarding pass issued")
    def createReservationForMemberWithCompanionPassAchievedAndBoardingPassIssued() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUserWithCompanionPassAchieved())
            passengerData.addRapidRewardsUser()

            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [outboundBoardingPassIssued: true, inboundBoardingPassIssued: true], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have booked an air reservation for a group with outbound boarding pass issued")
    def createReservationWithOutboundBPIssued() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [eligibleForCheckIn: "eligibleForCheckIn", eligibleForReturnCheckIn: "eligibleForReturnCheckIn",outboundBoardingPassIssued: true], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have an air reservation for a companion passenger with boarding pass issued")
    def createReservationForCompanionPassengerWithBoardingPassIssued() {
        if (DynaStubsIntegration.useDynaStubs()) {
            Passenger companion = passengerData.getPassengerWithName("Companion", "Guy")
            dynaStubsIntegration.designateCompanionPassForAPassenger(flow.getRrUser().getRRNumber(), companion)
            dynaStubsIntegration.createAirReservation(this.itineraryData,
                    [outboundBoardingPassIssued: true, inboundBoardingPassIssued: true,
                        crossPnrNumber: scenarioState.getLastAirReservation().adultPnr],
                    [companion])
        }
    }

    @Given("I have an air reservation but IET hub is down")
    def createReservationForSimulatingIETHubOutage() {
        if (DynaStubsIntegration.useDynaStubs()) {
            passengerData.clearPassengers()
            passengerData.addAdultPassenger("IET", "HUB", "OUTAGE", "")
            AirReservation pnr = dynaStubsIntegration.createAirReservation(this.itineraryData,  [tripName: ""],
                           passengerData.getPassengers())
            scenarioState.add pnr
        }

    }

    private void bookARoundTripFlightForTomorrow() {
        searchFlightsPage.open()
        itineraryData.departureDate = ItineraryDateFactory.getTomorrowDate()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        airPurchaseSteps.selectFlightAndPriceAndContinueToPurchase()
        airPurchaseSteps.fillInPurchasePageAndCompleteBooking()
    }

    private def priceAndPurchaseFlightAsUnaccompaniedMinor() {
        priceAndFillPurchaseInformation()

        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
            flow.completePurchase = true // for next scenario
        }
    }

    @When("I purchase a flight")
    def purchaseAFlight() {
        attemptToPurchaseAFlight()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I book a flight using \$earlyBirdSelectedOrNotInFlow")
    def purchaseWithEarlyBird(String earlyBirdSelectedOrNotInFlow) {
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(1, 0)
        flightSearchForm.fillFromAndToAirportAndDepartureDate(itineraryData, "EB")
        airPurchaseSteps.selectFlightAndPriceAndContinueToPurchase()
        if (earlyBirdSelectedOrNotInFlow.equals("Early Bird")) {
            purchasePage.clickAddEarlyBird()
        }

        purchasePage.fillInAllInformation()
        purchasePage.clickContinue()

        if (earlyBirdSelectedOrNotInFlow.equals("Early Bird")) {
            confirmationPage.verifyEarlyBirdPurchased()
        }
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I select and purchase a flight")
    def selectAndPurchaseAFlight() {
        selectAFlight()
        priceAndPurchaseFlight()
    }

    @When("I select and purchase a flight with a RR# containing leading/trailing spaces")
    def selectAndPurchaseAFlightWithRRNumberWithSpaces() {
        selectAFlight()
        priceAndPurchaseFlightForAnonymousUserWithRRNumber(true)
    }

    @When("I select and purchase a flight with a RR#")
    def selectAndPurchaseAFlightWithRRNumber() {
        selectAFlight()
        priceAndPurchaseFlightForAnonymousUserWithRRNumber(false)
    }

    public selectAFlight() {
        if (passengerData.containsSeniorAndAdultPassengers()) {
            selectAdultAndSeniorFlights()
        } else {
            selectFlightsPage.selectFlights()
        }
    }

    @When("I attempt to purchase a flight")
    void attemptToPurchaseAFlight() {
        searchFlightsAndFillPurchasePage()
        purchasePage.clickVisibleSubmit()
    }

    @When("I attempt to purchase a flight using credit card rejected by: \$reason")
    void attemptToPurchaseAFlightWithRejectedCreditCard(String reason) {
        selectFlightForAdultOrSenior()
        CreditCard cardData = CreditCardData.getRejectedCreditCardByReason(reason)
        if(DynaStubsIntegration.useDynaStubs()) {
            CreditCardRejectedReason rejectedReason = CreditCardRejectedReason.getCreditCardRejectedReason(reason)
            dynaStubsIntegration.generateRejectedCreditCard(cardData.getWholeNumber(), cardData.getSecurityCode(),cardData.getType().toString(), "", rejectedReason);
        }
        purchasePage.fillInAllInformation("REJECTED", cardData)
        purchasePage.clickVisibleSubmit()
    }

    private searchFlightsAndFillPurchasePage() {
        selectFlightForAdultOrSenior()
        purchasePage.fillInAllInformation()
    }

    @Given("I select a flight for purchase")
    @When("I select a flight for purchase")
    void selectFlightForAdultOrSenior() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        if (passengerData.containsSeniorAndAdultPassengers()) {
            selectAdultAndSeniorFlights()
        } else {
            selectFlightsPage.selectFlights()
        }

        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
    }

    @Given("I have booked this flight using all the available standard awards in my account and there will be a SODA event")
    def bookAFlightUsingFreedomAwardsWithSODA() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "",
                        certificateNumber: flow.getRrUser().getCertificateNumber(),
                        secondCertificateNumber: flow.getRrUser().getSecondCertificateNumber(),
                        sodaAffectedMark: true],
                    passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have booked this flight")
    @Alias("I have booked this flight using all the available standard awards in my account")
    def bookAFlightUsingFreedomAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "",
                        certificateNumber: flow.getRrUser().getCertificateNumber(),
                        secondCertificateNumber: flow.getRrUser().getSecondCertificateNumber()],
                    passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have booked this flight using my promotional certificate")
    def bookAFlightUsingPromotionalCertificates() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "",
                        certificateNumber: flow.getRrUser().getCertificateNumber(),
                        secondCertificateNumber: flow.getRrUser().getSecondCertificateNumber(),
                        certificateForPromoCert: true, paymentType : "certificate",
                        rapidRewardsAccountNumber: flow.getRrUser().getRRNumber(),
                        rapidRewardsAccountType: flow.getRrUser().getAccountType()],
                    passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have booked this flight using my points")
    def bookAFlightUsingPoints() {
        flow.purchaseWithPoints = true
        bookAFlightUsingFreedomAwards()
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.generatePayment(scenarioState.getFirstAirReservation().adultPnr, flow.rapidRewardsPoints)
        } else {
            bookALiveFlightUsingPoints()
        }
    }

    private void bookALiveFlightUsingPoints() {
        commonSteps.searchFlightFrom("flight search page")
        airSelectSteps.selectPointsRadioButton()
        airPricingSteps.selectFlightsAndPriceAndAttemptPurchasePoints()
        purchasePage.selectTextMeAsContactMethod()
        if(flow.isLoggedIn) {
            purchasePage.selectCreditCarFromStoredFormOfPayment()
        }
        purchasePage.clickContinue()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
        confirmationPage.storeRRPointsUsedOnPurchase()
    }

    @Given("I have booked this flight using \$awardNumber freedom award(s)")
    def bookAFlightUsingMoreThatOneAward(int awardNumber) {
        if (DynaStubsIntegration.useDynaStubs()) {
            bookAFlightUsingFreedomAwards()
            dynaStubsIntegration.generatePaymentByAwards(scenarioState.getFirstAirReservation().adultPnr, awardNumber)
        } else {
            bookALiveFlightUsingFreedomAwards()
        }
    }

    @Given("I have booked this flight using an X and V promotional certificate")
    def bookAFlightUsingMoreThatOneXAndVPromotionalCertificate() {
        if (DynaStubsIntegration.useDynaStubs()) {
            bookAFlightUsingPromotionalCertificates()
            dynaStubsIntegration.generatePaymentByPromotionalCertificate(scenarioState.getFirstAirReservation().adultPnr, 1,flow.getRrUser().RRNumber)
        }
    }
    @Given("I have booked this flight using an X and V promotional certificate that have a blackout date on itinerary departure date plus two")
    def bookAFlightUsingMoreThatOneXAndVPromotionalCertificateWithBlackoutDate() {
        if (DynaStubsIntegration.useDynaStubs()) {
            bookAFlightUsingPromotionalCertificates()
            dynaStubsIntegration.generatePaymentByPromotionalCertificateWithUnAvailableDate(scenarioState.getFirstAirReservation().adultPnr, 1,flow.getRrUser().RRNumber, itineraryData.departureDate.plus(2)  );
        }
    }

    @Given("I have booked this flight with \$awardNumber alternate freedom award(s)")
    def bookAFlightUsingMoreThatOneAlternateAward(int awardNumber) {
        if (DynaStubsIntegration.useDynaStubs()) {
            bookAFlightUsingFreedomAwards()
            dynaStubsIntegration.generatePaymentByAwards(scenarioState.getFirstAirReservation().adultPnr, awardNumber)
        } else {
            bookALiveFlightUsingAlternateFreedomAwards()
        }
    }

    private void bookALiveFlightUsingFreedomAwards() {
        searchAndSelectNonStopFlightWithFreedomAwards()
        finishPurchase()
        accountSteps.logOutIfLoggedIn()
    }

    private void searchAndSelectNonStopFlightWithFreedomAwards() {
        myAccountPage.open()
        myAccountPage.loginWithUserNameAndPassword(flow.rrUser.number, flow.rrUser.RRPassword)
        myAccountPage.verifyPage()
        myAccountPage.viewCreditsAndAwards()
        myAccountPage.clickViewAwardsLink()
        rapidRewardsSteps.searchAFlightFromCertificatesPage()
        searchFlightsPage.selectNonStopFlightWithFreedomAwards(itineraryData)
    }

    private void bookALiveFlightUsingAlternateFreedomAwards() {
        searchAndSelectNonStopFlightWithFreedomAwards()
        finishPurchase(true)
        accountSteps.logOutIfLoggedIn()
    }

    @Given("I change to a new Rapid Rewards User with No awards")
    def createANewRapidRewardsUser() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.setRrUser(dynaStubsIntegration.generateRRUser())
        }
    }

    @Given("I change to a new Rapid Rewards User with Standard Awards")
    def createANewRapidRewardsUserWithStandardAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            createANewRapidRewardsUser()
            dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_STND")
        }
    }

    @Given("I change to a new Rapid Rewards User with BuyUp Awards")
    def createANewRapidRewardsUserWithStandardBuyUpAwards() {
        if (DynaStubsIntegration.useDynaStubs()) {
            createANewRapidRewardsUser()
            dynaStubsIntegration.generatePromotialProductWithCertificateCategory(flow.rrUser.number, "RR1_0_TRANSITIONAL_BUYUP")
        }
    }

    @When("I attempt to purchase a flight as a minor")
    @Aliases(values = ['I attempt to purchase a flight as an infant',
    'I attempt to purchase a flight as a YT'])
    void attemptToPurchaseAFlightAsUM() {
        searchFlightsAndFillPurchasePage()
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
    }

    @When("I select and attempt to purchase a flight")
    void selectAndAttemptToPurchase() {
        selectFlightsForIncompletePurchase()
        priceAndPurchaseFlight()
    }

    @When("I select and attempt to purchase a flight as a young traveler")
    void selectAndAttemptToPurchaseAsYoungTraveler() {
        selectFlightsForIncompletePurchase()
        priceAndPurchaseFlightAsYoungTraveler()
    }

    @When("I attempt to purchase a flight with an infant on the departure who is a minor on the return")
    void selectAndAttemptToPurchaseAsUnaccompaniedMinor() {
        selectFlightsForIncompletePurchase()
        priceAndPurchaseFlightAsUnaccompaniedMinor()
    }

    @When("I book an Early Bird eligible flight")
    def createEarlyBirdEligibleItinerary() {

        flow.hasAir = true
        itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDateForEarlyBird()
        itineraryData.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate)

        createAnAirReservation()
    }

    private void searchForACheckinFlight() {
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        flightSearchForm.saveStations()
        itineraryData.departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        if (itineraryData.itineraryType != "One Way") {
            itineraryData.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate)
            flightSearchForm.enterReturnDate(itineraryData.returnDate)
        }
        flightSearchForm.submit()
        selectFlightsPage.chooseFlightsEligibleForCheckin(itineraryData)
    }

    @When("I attempt to book a flight with an itinerary which matches the itinerary booked before for the adult")
    void bookAFlightInfantWithPreviousItinerary() {
        ItineraryData previousItineraryData = new ItineraryData(scenarioState.getLastAirReservation().itineraryData)
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(previousItineraryData, true)
        selectFlightsPage.selectFlightsByNumber(previousItineraryData)
        priceAndFillPurchaseInformation()
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
    }

    @When("I attempt to search a flight with an itinerary which match the itinerary booked before for the adult")
    def searchAFlightWithPreviousItinerary() {
        ItineraryData previousItineraryData = new ItineraryData(scenarioState.getLastAirReservation().itineraryData)
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(previousItineraryData, true)
        selectFlightsPage.selectFlightsByNumber(previousItineraryData)
        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
    }

    @When("I book a flight for \$aCount adults and \$sCount seniors")
    def bookMixedPaxFlight(def int aCount, def int sCount) {
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(aCount, sCount);
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectAndPurchaseAdultAndSenior()
    }

    @When("I select flights for an adult and a senior")
    def selectAdultAndSeniorFlights() {
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinueForAdultAndSenior()
        selectFlightsPage.waitForSeniorFareSelectFlightPage()
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
    }

    @When("I book a flight for \$aCount adults and \$sCount seniors with \$fareForSenior fare")
    def bookMixedPaxFlightWithSenior(def int aCount, def int sCount, def String fareForSenior) {
        bookMixedPaxFlightWithDifferentFaresForSenior(aCount, sCount, fareForSenior, fareForSenior)
    }

    @When("I book a flight for \$aCount adults and \$sCount seniors with \$outboundFare fare for outbound and \$inboundFare fare for inbound")
    def bookMixedPaxFlightWithDifferentFaresForSenior(def int aCount, def int sCount, def String outboundFare, def String inboundFare) {
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(aCount, sCount);
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        selectFlightsPage.clickContinueForAdultAndSenior()
        itineraryData.departingFlight_fareClass = outboundFare
        itineraryData.arrivingFlight_fareClass = inboundFare
        selectFlightsPage.selectFlights()
        priceAndPurchaseFlight()
    }

    @When("I book a flight for \$aCount adults")
    def bookAFlightForAdult(def int aCount) {
        bookAFlightAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(aCount, 0) })
    }

    @When("I book a flight for \$sCount seniors")
    def bookAFlightForSenior(def int sCount) {
        bookAFlightAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(0, sCount) })
    }

    @When("I book a flight as a senior")
    def bookAFlightAsASenior() {
        bookAFlightAsAnX({ flightSearchForm.selectAdultAndSeniorPassengers(0, 1) })
    }

    @When("I book a flight as a senior and search only")
    def bookAFlightAsASeniorAndSearchOnly() {
        searchFlightsPage.open()
        flightSearchForm.selectAdultAndSeniorPassengers(0, 1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        searchFlightsPage.submit()
    }

    @When("I book a flight as a POS")
    def bookAFlightAsAPos() {
        bookAFlightAsAnX({ flightSearchForm.selectAdultPassengerOfSize(1) })
    }

    @When("I book a flight and fill in an invalid middle name")
    def bookAFlightWithAnInvalidMiddleName() {
        flow.completePurchase = false
        bookAFlightAsAnX({ flightSearchForm.selectAdultWithInvalidMiddleName() })
    }

    @When("I book a flight that is eligible for checkin using a compound first name, a compound middle name and a compound last name")
    def bookAFlightWithCompoundFirstAndLastName() {
        searchFlightsPage.open()

        itineraryData.departureDate = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
        itineraryData.returnDate = ItineraryDateFactory.chooseDayEligibleForReturnCheckin()

        flightSearchForm.selectPassengerWithCompoundName()
        flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
        flightSearchForm.enterDepartureDate(itineraryData.departureDate)
        flightSearchForm.enterReturnDate(itineraryData.returnDate)
        flightSearchForm.submit()

        selectFlightsPage.chooseFlightsEligibleForCheckin(itineraryData)
        finishPurchase()
    }

    private void finishPurchase(boolean selectAlternateAwards = false) {
        selectFlightsPage.clickContinue()
        navigationSteps.acceptNewPriceAndContinue()
        pricePage.clickContinue()

        if(selectAlternateAwards) {
            purchasePage.clickAlternateAwards()
            purchasePage.verifyPage()
            purchasePage.selectAlternateFreedomAwards()
            purchasePage.clickOnSelectAwards()
            purchasePage.verifyPage()
        }

        purchasePage.fillInAllInformation()

        purchasePage.clickContinue()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I book a nonrev flight")
    def bookANonRevenueFlight() {
        if (DynaStubsIntegration.useDynaStubs()) {
            passengerData.addPassengers([PassengerType.ADULT])
            itineraryData.departingFlight_fareClass = itineraryData.arrivingFlight_fareClass = "Nonrev"

            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [eligibleForCheckIn: "eligibleForCheckIn"], passengerData.getPassengers());
            scenarioState.add pnr
        }
    }

    @When("I book a nonrev flight \$eligibility")
    def bookANonRevenueFlightIneligible(String eligibility) {
        if (DynaStubsIntegration.useDynaStubs()) {
            passengerData.addPassengers([PassengerType.ADULT])
            itineraryData.departingFlight_fareClass = itineraryData.arrivingFlight_fareClass = "Nonrev"
            def eligible = eligibility.equalsIgnoreCase("eligible") ? "true" : "false"
            def params = [eligibleForCheckIn: "eligibleForCheckIn", eligibility: "$eligible"]
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, params, passengerData.getPassengers());
            scenarioState.add pnr
        }
    }

    @Given("Im on the nonrev landing page")
    def proceedToNonrevLandingPage() {
        bookANonRevenueFlight()
        nonRevLandingPage.open()
    }

    @When("I select \$originStation as origin station")
    def selectOriginStation(String originStation){
        nonRevLandingPage.fillOriginStation(originStation)
    }

    @When("I select \$destinationStation as destination station")
    def selectDestinationStation(String destinationStation){
        nonRevLandingPage.fillDestinationStation(destinationStation)
    }

    @Given("I book a flight eligible for checkin \$passengerCount \$passengerType")
    @When("I book a flight eligible for checkin \$passengerCount \$passengerType")
    def bookAFlightLeavingToday(int passengerCount, String passengerType) {
        bookAFlightWithEligibleDetails(passengerCount, passengerType, false)
    }

    @When("I book outbound and return flights eligible for checkin \$passengerCount \$passengerType")
    def bookAFlightLeavingTodayWithEligibleReturn(int passengerCount, String passengerType) {
        bookAFlightWithEligibleDetails(passengerCount, passengerType, true)
    }

    @Given("I have an AirTran only reservation for a verified senior and an unverified senior that is eligible for check in")
    def iHaveAnAirTranOnlyReservationForAVerifiedSeniorAndAnUnverifiedSeniorThatIsEligibleForCheckIn() {
        iHaveAReservationForAVerifiedSeniorAndAnUnverifiedSeniorThatIsEligibleForCheckIn("AirTranOnly", "AirTranOnly")
    }
    @Given("I have a Southwest only reservation for a verified senior and an unverified senior that is eligible for check in")
    def iHaveASouthwestReservationForAVerifiedSeniorAndAnUnverifiedSeniorThatIsEligibleForCheckIn() {
        iHaveAReservationForAVerifiedSeniorAndAnUnverifiedSeniorThatIsEligibleForCheckIn("Southwest", "Southwest")
    }

    @Given("I have a reservation for an unverified senior that is eligible for check in")
    def iHaveAReservationForAnUnverifiedSeniorThatIsEligibleForCheckIn() {
        iHaveAReservationForAnUnverifiedSeniorThatIsEligibleForCheckIn("Southwest", "Southwest")
    }

    @Given("I have an existing AirTran reservation eligible for check in")
    def iHaveAnExistingAirTranReservationEligibleForCheckIn() {
        if ( itineraryData.departureStation.isEmpty() ) {
            createDefaultItineraryDataForAirtran()
        }

        bookAFlightWithEligibleDetails( 1, "adult", false )
    }

    def createDefaultItineraryDataForAirtran() {
        commonSteps.setupOneWayCarrierFlight("AirTranOnly")
    }

    def iHaveAReservationForAVerifiedSeniorAndAnUnverifiedSeniorThatIsEligibleForCheckIn(departingCarrier, arrivingCarrier) {
        if (DynaStubsIntegration.useDynaStubs()) {
            RRUser verifiedRRUser = rapidRewardsSteps.createRRMemberSenior(true, "seniorFirstNameOne")

            List<Passenger> passengers = new ArrayList<Passenger>()
            Passenger verifiedPassenger = new Passenger(verifiedRRUser.firstName, verifiedRRUser.lastName, verifiedRRUser.gender)
            verifiedPassenger.beSenior()
            verifiedPassenger.setRapidRewardsNumber(verifiedRRUser.getRRNumber())
            verifiedPassenger.setRapidRewardsType(verifiedRRUser.getAccountType())
            passengers.add(verifiedPassenger)

            RRUser notverifiedRRUser = rapidRewardsSteps.createRRMemberSenior(false, "seniorFirstNameTwo");
            Passenger notverifiedPassenger = new Passenger(notverifiedRRUser.firstName, notverifiedRRUser.lastName, notverifiedRRUser.gender)
            notverifiedPassenger.beSenior()

            flow.rrUser = notverifiedRRUser

            notverifiedPassenger.setRapidRewardsNumber(notverifiedRRUser.getRRNumber())
            notverifiedPassenger.setRapidRewardsType(notverifiedRRUser.getAccountType())
            passengers.add(notverifiedPassenger)

            flow.hasAir = true
            itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup()).withDepartingCarrier(departingCarrier).withArrivalCarrier(arrivingCarrier).withRoundTripStations(departingCarrier, arrivingCarrier).withFareClass("Senior").build())

            Map parms = [eligibleForCheckIn: "eligibleForCheckIn"]

            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, parms, passengers)
            scenarioState.add pnr
        }
    }
    def iHaveAReservationForAnUnverifiedSeniorThatIsEligibleForCheckIn(departingCarrier, arrivingCarrier) {
        if (DynaStubsIntegration.useDynaStubs()) {
            List<Passenger> passengers = new ArrayList<Passenger>()

            RRUser notVerifiedRRUser = rapidRewardsSteps.createRRMemberSenior(false, "seniorFirstNameTwo");
            Passenger notVerifiedPassenger = new Passenger(notVerifiedRRUser.firstName, notVerifiedRRUser.lastName, notVerifiedRRUser.gender)
            notVerifiedPassenger.beSenior()

            flow.rrUser = notVerifiedRRUser

            notVerifiedPassenger.setRapidRewardsNumber(notVerifiedRRUser.getRRNumber())
            notVerifiedPassenger.setRapidRewardsType(notVerifiedRRUser.getAccountType())
            passengers.add(notVerifiedPassenger)

            flow.hasAir = true
            itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup())
                                        .withDepartingCarrier(departingCarrier)
                                        .withArrivalCarrier(arrivingCarrier)
                                        .withRoundTripStations(departingCarrier, arrivingCarrier)
                                        .withFareClass("Senior").build())

            Map parms = [eligibleForCheckIn: "eligibleForCheckIn"]

            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, parms, passengers)
            scenarioState.add pnr
        }
    }

    def bookAFlightWithEligibleDetails(int passengerCount, String passengerType, boolean returnEligibleForCheckin) {
        if (DynaStubsIntegration.useDynaStubs()) {
            for (int i = 0; i < passengerCount; i++) {
                passengerData.addPassengers([PassengerType.fromType(passengerType)])
            }
            Map parms = [eligibleForCheckIn: "eligibleForCheckIn"]
            if (returnEligibleForCheckin) {
                parms = [eligibleForCheckIn: "eligibleForCheckIn", eligibleForReturnCheckIn: "eligibleForReturnCheckIn"]
            }
            if (flow?.getRrUser()) {
                def passenger = passengerData.getPassengers().get(0)
                passenger.setRapidRewardsNumber(flow.getRrUser().getRRNumber())
                passenger.setRapidRewardsType(flow.getRrUser().getAccountType())
            }
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, parms, passengerData.getPassengers())
            scenarioState.add pnr
            return
        }
        //SWABiz stories should have logged-in and already landed on the SWABiz search page
        if (!flow.isSwabiz) {
            searchFlightsPage.open()
        }

        flightSearchForm.selectItineraryType(itineraryData)
        if (passengerType.contains("adult")) {
            flightSearchForm.selectMultipleAdultPassengers(passengerCount)
        }
        if (passengerType.contains("senior")) {
            flightSearchForm.selectAdultAndSeniorPassengers(0, passengerCount)
        }
        searchForACheckinFlight()
        priceAndPurchaseFlight()
    }

    @When("I have a compound name and book a flight eligible for checkin")
    def bookAFlightLeavingTodayForPassengerWithCompoundName() {
        if (DynaStubsIntegration.useDynaStubs()) {
            passengerData.addPassengerWithCompoundName()
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [eligibleForCheckIn: "eligibleForCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
            return
        }
        //SWABiz stories should have logged-in and already landed on the SWABiz search page
        if (!flow.isSwabiz) {
            searchFlightsPage.open()
        }

        flightSearchForm.selectItineraryType(itineraryData)
        flightSearchForm.selectPassengerWithCompoundName()

        searchForACheckinFlight()
        priceAndPurchaseFlight()
    }

    @When("I book an OpenJaw flight")
    def bookAnOpenJawFlight() {
        airSearchSteps.selectOpenJawTrip()
        selectAndPurchaseAFlight()

    }

    @When("I try to book an OpenJaw flight with less than 25 minutes layover")
    def bookAnOpenJawFlightWithLessThenTwentyFiveMinutesLayover() {
        airSearchSteps.selectOpenJawTrip()
        selectFlightsPage.selectFlightsWithLessThenTwentyFiveMinutesLayover()
        selectFlightsPage.clickContinue()
    }

    @When("I book a one way flight")
    def bookAOneWayFlight(){
        airSearchSteps.selectOneWayTrip()
    }

    @When("I book an OpenJaw flight with less than 4 hour layover")
    def bookAnOpenJawFlightWithLessThanFourHourLayover() {
        searchFlightsPage.open()
        itineraryData.lessThan4HourLayover = true
        airSearchSteps.openJawTripSelectionUsingDatesFromItineraryData(1)
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlightsWithLessThanFourHourLayover()
        priceAndPurchaseFlight()
    }

    @When("I book a flight for adult and child passengers")
    def bookAFlightAsAnAdultWithYoungChild() {
        bookAFlightAsAnX({ flightSearchForm.selectChildAndAdultPassengers(1, 1) })
    }

    @When("I book a flight for an unaccompanied child")
    void bookFlightForUnaccompaniedChild() {
        flow.completePurchase = false
        bookAFlightFor([PassengerType.MINOR])
    }

    @When("I book a flight for a minor traveling with a youth")
    void bookAFlightMinorAndYouthPassengers() {
        flow.completePurchase = false
        bookAFlightAsAnX(
                {
                    flightSearchForm.selectChildAndYouthPassengers(1, 1)
                }
                )
    }

    @When("I book a new flight for an infant traveling with a minor from \$departure to \$arrival")
    void bookAFlightInfantAndMinorPassengers(String departure, String arrival) {
        flow.completePurchase = false
        itineraryData.departureStation = departure
        itineraryData.arrivalStation = arrival
        bookAFlightFor([
            PassengerType.MINOR,
            PassengerType.INFANT
        ])
    }

    @When("I book a flight for an unaccompanied young child")
    void bookFlightForUnaccompaniedYoungChild() {
        flow.completePurchase = false
        bookAFlightFor([PassengerType.INFANT])
    }

    private void bookAFlightFor(List<PassengerType> passengers) {
        searchFlightsPage.open()
        flightSearchForm.selectPassengers(passengers)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        if ((passengers.size() == 1) && (passengers[0].equals(PassengerType.YT))) {
            selectFlightsForIncompletePurchase()
            priceAndPurchaseFlightAsYoungTraveler()
        }
        else if ((passengers.size() == 1) && (passengers[0].equals(PassengerType.MINOR))) {
            selectFlightsForIncompletePurchase()
            priceAndPurchaseFlightAsUnaccompaniedMinor()
        }
        else {
            selectAndPurchaseAFlight()
        }
    }

    private def priceAndPurchaseFlightAsYoungTraveler() {
        priceAndFillPurchaseInformation()

        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmitAsYoungTraveler()
            flow.completePurchase = true // for next scenario
        }
    }

    private void bookAFlightAsAnX(Closure asAnX, boolean accordionPage = false) {
        searchFlightsPage.open()
        asAnX.call()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        def passenger = flightSearchForm.passengerData.passengers[0]
        if (passenger?.mustBeAccompanied()) {
            selectFlightsPage.selectFlights()
            priceAndPurchaseFlightAsYoungTraveler()
        } else {
            if(accordionPage) {
                selectAFlight()
                priceAndPurchaseUsingAccordionPage()
            }else {
                selectAndPurchaseAFlight()
            }
        }
    }

    def priceAndPurchaseFlight() {
        priceAndFillPurchaseInformation()
        if (itineraryData.lessThan4HourLayover){
            navigationSteps.clickContinueToConfirmation()
        } else if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmit()
            flow.completePurchase = true // for next scenario
        }
    }

    private def priceAndPurchaseAnAwardsFlight() {
        priceAndFillPurchaseInformationForALoggedInUser()

        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmit()
            flow.completePurchase = true // for next scenario
        }
    }

    private def priceAndPurchaseFlightForAnonymousUserWithRRNumber(boolean padNumberWithSpaces) {
        priceAndFillPurchaseInformationForAnonymousUserWithRRNumber(padNumberWithSpaces)
        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmit()
            flow.completePurchase = true // for next scenario
        }
    }

    @When("I book a flight for senior and child passengers")
    def bookAFlightForMultipleAdultPassengers() {
        searchFlightsPage.open()
        flightSearchForm.selectChildAndSeniorPassengers(1, 1)
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        selectAndPurchaseAdultAndSenior()
    }

    @When("I book a flight with \$currency")
    def previouslyBookedFlightWithCurrency(String currency) {
        if (currency == "travel funds") {
            bookFlightWithTravelFunds()
        } else {
            searchFlightsPage.open()
            flightSearchForm.selectAdultAndSeniorPassengers(1, 0)
            if (currency == "points") {
                flightSearchForm.chooseFaresInPoints()
                flow.isRapidRewardsPointsPurchaseOnly = true
                flow.isRapidRewards = true
                flow.isLoggedIn = true
            } else if (currency == "dollars") {
                flightSearchForm.chooseFaresInDollars()
            }
            flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
            selectFlightsPage.selectFlights()
            WaitForPageToLoad
            priceAndPurchaseFlight()
            confirmationPage.verifyPage()
        }
    }

    @When("I apply retained travel funds and fill in all required fields")
    def applyTravelFundsToCurrentPurchase() {
        purchasePage.applyTravelFunds()
        purchasePage.fillInAllInformation()
    }

    def bookFlightWithTravelFunds() {
        boolean refundable = false
        flow.isTravelFundApplied = true //for travel fund purchase we can not use rand passenger name. it only work if purchaser and flyer name is same
        bookAFlight()
        airCancelSteps.cancelFlight(refundable)
        cancelReservationConfirmationPage.verifyCancellation()
        //============= start a new booking
        searchFlightsPage.open()
        flightSearchForm.fillFromAndToAirportAndDepartureDate(itineraryData)
        airPurchaseSteps.selectFlightAndPriceAndContinueToPurchase()
        purchasePage.applyTravelFunds()
    }

    @When("I select flights and finish booking using a standard award")
    def selectStandardAwardsFlights() {
        /**
         *  We are only able to manually "purchase" a flight with a certificate when running the jBehave story.
         *  We CANNOT purchase a flight w/ a certificate if we are manually driving through the booking flow in
         *      DynaStubs.  If using a certificate in DynaStubs, the certificate number must be posted as a
         *      PaymentSpecification in order to complete the purchase
         */
        flow.hasAir = true
        selectFlightsPage.selectFlights()
        if ( DynaStubsIntegration.useDynaStubs() ) {
            dynaStubsIntegration.generatePaymentByAwards(flow.getUser().number,flow.getUser().getCertificateNumber())
        }
        priceAndPurchaseFlight()
    }

    @When("I select flights and finish booking using an award")
    def selectAwardsFlights() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.generatePaymentByAwards(flow.getUser().number,flow.getUser().getSecondCertificateNumber())
        }
        selectFlightsPage.selectFlights()
        priceAndPurchaseAnAwardsFlight()
    }

    @When("I select flights and finish booking")
    def selectAndPurchaseAdultAndSenior() {
        selectAdultAndSeniorFlights()
        priceAndPurchaseFlight()
    }

    @When("I click the Early Bird upsell button")
    clickEBUpsellButton() {
        confirmationPage.clickEarlyBirdUpsellButton()
    }

    @When("I click on the Fare Breakdown link on the Confirmation page")
    void clickOnFareBreakdownLink() {
        confirmationPage.clickOnFareBreakdownLink()
    }

    @When("I continue to the purchase page and complete the booking for an Unaccompanied Minor")
    def continueToPurchaseAndCompleteBookingForUM() {
        navigationSteps.continueToPurchasePage()
        purchasePage.verifyPage()
        airPurchaseSteps.fillOutPurchaseForm()
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
        airPurchaseSteps.clickNoButtonOnUmModal()
        airGuardianPurchaseSteps.fillUMGuardianFormForARoundTrip()
        airPurchaseSteps.fillInCreditCardOnPurchasePageAndCompleteBooking()
        confirmationPage.storeCurrentPNR()
    }

    @When("I continue to the purchase page and complete the booking")
    def continueToPurchaseAndCompleteBooking() {
        navigationSteps.continueToPurchasePage()
        completePurchase()
    }

    @When("I continue to the purchase page")
    def continueToPurchase() {
        navigationSteps.continueToPurchasePage()
    }

    @When("I complete the booking and view the confirmation page")
    def completePurchase() {
        airPurchaseSteps.fillOutPurchaseForm()
        airPurchaseSteps.clickPurchase()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I redirect to view my reservation from the confirmation page")
    public void clickViewReservationFromConfirmationPage() {
        confirmationPage.clickViewReservationFromConfirmationPage()
    }

    @When("I click the \$outboundInbound seat selection button")
    def clickSeatSelectionButton(String outboundInbound) {
        confirmationPage.clickSeatSelectionButton(outboundInbound)
    }

    @When("I attempt to book a flight with a date which DOES NOT match the flight date of the Adult itinerary")
    def bookAFlightNotMatchingPreviousItinerary() {
        itineraryData = scenarioState.getLastAirReservation().itineraryData
        itineraryData.departureDate = itineraryData.departureDate.plus(1)
        itineraryData.returnDate = itineraryData.returnDate.plus(1)
        attemptToPurchaseAFlightAsUM()
    }

    @Given("As a Rapid Reward user, I have booked a flight to be used for checkin, change or cancel flows from the \$page")
    @When("As a Rapid Reward user, I search and book a flight to be used for checkin, change or cancel flows from the \$page")
    void searchForAFlightForCheckinChangeCancelFromPageSpecifiedWhenLoggedIn(String page) {
        if (DynaStubsIntegration.useDynaStubs()) {
            passengerData.addRapidRewardsUser()
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, ["": ""], passengerData.getPassengers())
            scenarioState.add pnr
        } else {
            searchForAFlightFromPageSpecified(page)
        }
    }

    @When("I search and book a flight to be used for checkin, change or cancel flows from the \$page")
    @Alias("I search and book a flight to be used for view reservation \$page")
    void searchForAFlightForCheckinChangeCancelFromPageSpecified(String page) {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, ["inboundFlightNumber": INBOUND_AVAILABLE_FLIGHT])
            scenarioState.add pnr
        } else {
            searchForAFlightFromPageSpecified(page)
        }
    }

    @When("I search and book a flight from \$page")
    void searchForAFlightFromPageSpecified(String page) {
        def seniorCount = itineraryData.seniorPassengerCount ?: "0"
        def adultCount = itineraryData.adultPassengerCount ?: "0"
        passengerData.addAdultPassenger(adultCount.toInteger())
        passengerData.addSeniorPassengers(seniorCount.toInteger())
        searchForAFlightFromASpecificPageAndChooseCheckinEligibility(null, page)
        priceAndPurchaseFlight()

    }

    @When("I have compound name and book a flight from \$page")
    void searchForAFlightFromPageSpecifiedForPassengerWithCompoundName(String page) {
        searchForAFlightFromASpecificPageAndChooseCheckinEligibility(null, page)
        Passenger passenger = flightSearchForm.getPassengerData().getAdults()[0];
        passenger.setFirstName(PassengerData.COMPOUND_PASSENGER_FIRST_NAME.toUpperCase())
        passenger.setMiddleName(PassengerData.COMPOUND_PASSENGER_MIDDLE_NAME.toUpperCase())
        passenger.setLastName(PassengerData.COMPOUND_PASSENGER_LAST_NAME.toUpperCase())
        priceAndPurchaseFlight()
    }

    @When("I search and book a flight \$eligibleForCheckin from \$page")
    void searchForAFlightEligibleForCheckinFromPageSpecified(String eligibleForCheckin, String page) {
        eligibleForCheckin = "eligible for checkin"
        searchForAFlightFromASpecificPageAndChooseCheckinEligibility(eligibleForCheckin, page)
        priceAndPurchaseFlight()
    }

    void searchForAFlightFromASpecificPageAndChooseCheckinEligibility(String eligibleForCheckin, String page) {
        switch (page) {
            case ("home page"):
                homePage.open()
                homePage.selectAdultAndSeniorPassengers(itineraryData, passengerData)
                homePageFlightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true, HOME_PAGE_SUBMIT_BUTTON)
                selectAFlight()
                break
            case ("the low fare calendar page"):
                lowFareCalendarPage.open()
                flightSearchForm.fillInAirports(itineraryData.departureStation, itineraryData.arrivalStation)
                lowFareCalendarPage.selectMonth(itineraryData)
                lowFareCalendarPage.selectDatesAndContinue(itineraryData)
                selectAFlight()
                break
            case ("search flights page"):
                searchFlightsPage.open()
                if (eligibleForCheckin == "eligible for checkin") {
                    searchForACheckinFlight()
                } else {
                    flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)

                    selectFlightsPage.verifyPage()
                    if (flow.isFaultInjected) {
                        println "Entry >>>> airconfirmation Verifypage"
                        if (flow.isFaultInjected) {sleep(30000)}
                        println "Exit >>>> airconfirmation Verifypage"
                    }
                    if (passengerData.containsSeniorAndAdultPassengers()) {
                        selectAdultAndSeniorFlights()
                    } else {
                        selectFlightsPage.selectFlights()
                    }
                }
                break

            default:
                fail("Story line did not have proper page name, expected page: " + page)
                break
        }
    }

    private def selectFlightsForIncompletePurchase() {
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        flow.completePurchase = false
    }

    @When("I continue to filling fields to purchase 2 adults")
    def priceFillPurchaseInformation(){
        selectFlightsPage.selectFlights()
        priceAndFillPurchaseInformation()
        purchasePage.fillSecondPassengerMiddleName("XS")
    }

    private def priceAndFillPurchaseInformation() {
        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
        purchasePage.fillInAllInformation()
        purchasePage.getTripTotal()
    }

    private def priceAndFillPurchaseInformationForALoggedInUser() {
        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
        purchasePage.fillInInformationForUserLoggedIn()
    }

    private def priceAndFillPurchaseInformationForAnonymousUserWithRRNumber(boolean padRRNumberWithSpaces) {
        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
        purchasePage.fillInAllInformation()
        if (padRRNumberWithSpaces) {
            StringBuilder rrNumber = new StringBuilder(flow.user.RRNumber)
            rrNumber.insert((int)(rrNumber.length()/2), "  ").insert(0," ").append(" ")
            purchasePage.fillInRapidRewardsNumber(rrNumber.toString())
        } else {
            purchasePage.fillInRapidRewardsNumber(flow.user.RRNumber)
        }
    }

    @Given("I have the flight booked for a passenger named \$firstName \$lastName")
    def createAnAirReservationForAnSpecifiedPassenger(String firstName, String lastName) {
        passengerData.addPassengerWithName(firstName, lastName)
        createAnAirReservation()
    }

    @Given("I have this flight booked from \$pnr_Source with crossReference")
    def createAirReservationFromPNRSourceAndCrosReference(@Named("pnr_Source") String pnrSource) {
        AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData,[crossPnrNumber: scenarioState.getLastAirReservation().adultPnr, pnrSource: pnrSource], passengerData.getPassengers())
        scenarioState.add(pnr)
    }

    @Given("I have at least one Upcoming Trip in my account with a Hotel product which is not part of a trip")
    def setupHotelProduct() {
        flow.hasAir = false
        itineraryData.carSpecification = null
        itineraryData.departureDate = new Date()
        crossBookingSteps.purchaseCart()
    }

    @Given("I have booked this group reservation with all its passengers eligible for checkin online")
    @Alias("I have booked this group reservation eligible to checkin online")
    def createGroupReservationEligibleForCheckIn() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [eligibleForCheckIn: "eligibleForCheckIn", eligibleForReturnCheckIn: "eligibleForReturnCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have my reservation with all its passengers ineligible for checkin online")
    def updateGroupCheckInReservationToRequireAirportCheckInForAllPassengers() {
        updateGroupCheckInReservationForPassengersThatRequireAirportCheckIn(passengerData.getPassengers().size())
    }

    @Given("I have my reservation with all its passengers eligible to checkin online except for \$passengers")
    def updateGroupCheckInReservationForPassengersThatRequireAirportCheckIn(int passengers) {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updateGroupCheckInReservationForPassengersThatRequireAirportCheckIn(scenarioState.lastAirReservation.adultPnr, passengers, true, true)
        }
    }

    @Given("I have my reservation with all its passengers eligible to checkin online and boarding pass is not allow on Line")
    def updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllow() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllow(scenarioState.lastAirReservation.adultPnr,0, true, false,[0,1].toArray(new Integer[2]))
        }
    }

    @Given("I have my reservation with all its passengers eligible to checkin online and boarding pass is not allow on Line at First Segment")
    def updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllowAtFirstSegment() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllow(scenarioState.lastAirReservation.adultPnr,0, true, false,[0].toArray(new Integer[2]))
        }
    }

    @Given("I have my reservation with all its passengers eligible to checkin online and boarding pass is not allow on Line at Second Segment")
    def updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllowAtSecondSegment() {
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllow(scenarioState.lastAirReservation.adultPnr,0, true, false,[-1].toArray(new Integer[2]))
        }
    }

    @Given("I have \$count passengers with errors in their checkin")
    def updateGroupCheckInReservationForPassengersThatHaveCheckinErrors(int count) {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.passengersWithCheckinErrors = dynaStubsIntegration.updateGroupCheckInReservationForPassengersThatRequireAirportCheckIn(scenarioState.lastAirReservation.adultPnr, count, false, false)
        }
    }

    @Given("I have a MyIdTravel flight booked")
    @When("I have a MyIdTravel flight booked")
    def createAMyIdTravelAirReservation() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [myIdTravelMark: true, eligibleForCheckIn: "eligibleForCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have a MyIdTravel flight booked with Credit Card")
    def createAMyIdTravelAirReservationWithCC() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [creditCardNumber: data.getRandomCreditCard().getWholeNumber(), myIdTravelMark: true, eligibleForCheckIn: "eligibleForCheckIn"], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have a flight booked affected by a SODA event")
    def createAnAirReservationWithSODAEvent() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [sodaAffectedMark: true, tripName: ""], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have a flight booked from \$pnrSource")
    def createAnAirReservationWithPnrSource(String pnrSource) {
        if (DynaStubsIntegration.useDynaStubs()) {
            if(StringUtils.isNotBlank(pnrSource)){
                AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [pnrSource: pnrSource, tripName: "", eligibleForCheckIn: "eligibleForCheckIn"], passengerData.getPassengers())
                scenarioState.add pnr
            }
        }
    }

    @Given("I have booked this group reservation")
    def createGroupReservation() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [isGroupPNR: true], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have booked this group reservation with all its passengers eligible for checkin online from \$pnrSource")
    def createGroupReservationEligibleForCheckInWithQIK(String pnrSource) {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [pnrSource: pnrSource, eligibleForCheckIn: "eligibleForCheckIn", eligibleForReturnCheckIn: "eligibleForReturnCheckIn", isGroupPNR: true], passengerData.getPassengers())
            scenarioState.add pnr
        }
    }

    @Given("I have a Southwest one-way reservation for an adult with an infant that is eligible for check in")
    def iHaveASoutwestOneWayReservationForOneAdultWithAnInfantThatIsEligibleForCheckIn() {
        if (DynaStubsIntegration.useDynaStubs()) {
              createOneWayPnrWithInfant("Southwest")
        }
    }

    @Given("I have an AirTran one-way reservation for an adult with an infant that is eligible for check in")
    def iHaveAnAirTranOneWayReservationForAnAdultWithAnAdultWithAnInfantThatIsEligibleForCheckIn() {
        if(DynaStubsIntegration.useDynaStubs()) {
            createOneWayPnrWithInfant("AirTranOnly")
        }
    }

    private def createOneWayPnrWithInfant( String carrier ) {
        passengerData.addPassengers([PassengerType.ADULT])
        passengerData.getPassengers().get(0).setHasInfant( true )
        itineraryData.setValues(new ItineraryDataBuilder(new RandomTicketTypeGroup()).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withOneWayStations(carrier).build())
        Map params = [eligibleForCheckIn: "eligibleForCheckIn"]
        AirReservation pnr = dynaStubsIntegration.createAirReservation( itineraryData, params, passengerData.getPassengers() )
        scenarioState.add pnr
    }

    @When("I select \$direction flight number \$flightnumber")
    void selectAnOutboundOrInboundFlightUsingFlightNumber(String direction, String flightnumber) {
        selectFlightsPage.selectFlightByNumber(direction, flightnumber)
    }

    @When("I continue from the select flights page to the purchase page")
    void clickContinueOnSelectFlightsPage() {
        selectFlightsPage.clickContinue()
        navigationSteps.continueToPurchasePage()
    }

    @Given( "I have a reservation" )
    def iHaveAnAirTranReservationForAnAdult() {
        commonSteps.setupRoundTripCarrierFlight( "Southwest", "Southwest")
        createAnAirReservation()
    }

    @Given( "I have an AirTran reservation")
    def iHaveAnAirTranReservation() {
        commonSteps.setupRoundTripCarrierFlight( "AirTran", "AirTran")
        createAnAirReservation()
    }

    @Given( "I have an AirTran reservation that is missing ticket numbers")
    def iHaveAnAirTranReservationWithMissingTicketNumbers() {
        commonSteps.setupRoundTripCarrierFlight( "AirTran", "AirTran")
        createAnAirReservation(true, false)
    }

    @Given( "I have a Southwest AirTran reservation" )
    def iHaveASouthwestAirTranReservation() {
        commonSteps.setupRoundTripCarrierFlight( "Southwest", "AirTran")
        createAnAirReservation()
    }

    @Given( "I have an AirTran reservation that is eligible for check in" )
    def iHaveAnAirTranReservationThatIsEligibleForCheckIn() {
        commonSteps.setupRoundTripCarrierFlight( "AirTran", "AirTran")
        createAnAirReservation(false, true)
    }

    @Given( "I have an AirTran reservation that is eligible for check in and is missing ticket numbers" )
    def iHaveAnAirTranReservationThatIsEligibleForCheckInWithMissingTicketNumbers() {
        commonSteps.setupRoundTripCarrierFlight( "AirTran", "AirTran")
        createAnAirReservation(true, true)
    }

    @Given("I select text me for the contact method")
    void selectContactMeDropDown() {
        purchasePage.selectTextMeAsContactMethod()
    }

    @Given("I select paypal as the payment method")
    void paypalPaymentMethod(){
        purchasePage.selectPayPal()
        purchasePage.openPayPalWindow();
        payPal.switchToPayPalWindow();
        payPal.enterCredentialsOnPayPalHomePage();
        payPal.clickContinueOnPayPalHomePage();
        purchasePage.switchBackToDotCom();
    }

    @Given("I select to name my trip")
    void selectNameMyTrip(){
        purchasePage.ensureNameThisTripCheckBoxIsChecked();
        purchasePage.verifyTripNameSectionIsEnabled()
    }

    @When("I enter PayPal credentials and pay for my flight")
    void enterPayPalCredentialsAndPayForFlight(){
        payPal.enterPayPalCredentialsOnPurchaseTransitionPage();
        payPal.acceptPriceOnReviewYourInformationPage();
    }

    @When("I click Add Rapid Rewards Number Link in the confirmation page")
    def clickAddRapidRewardsNumberLink(){
        confirmationPage.clickAddRapidRewardsNumberLink()
    }

    @Given("I select paypal")
    def selectPayPal(){
        customerInfoData.formOfPayment = "Paypal"
    }

    @Given("I enter a luv voucher")
    def selectLuvVoucher(){
        itineraryData.luvVoucher = true
    }

    @When("I fill out the purchase page")
    def completePurchaseForm() {
        completePurchasePageForm(true)
    }

    def completePurchasePageForm(boolean clickSubmit =  true) {
        purchasePage.with {
            verifyBasicPage()
            verifyFormOfPaymentHeader()
            if(flow.isLoggedIn && flow.isRapidRewards) {
                verifyRRNumber()
            }
            flow.tripName = TripManagement.NO_TRIP
            if (itineraryData.luvVoucher) {
                clickApplyTravelFunds()
                fillVoucherAndApply()
                verifyLuvVoucherApplied()
            }
            if (flow.useSavedCreditCard) {
                verifyPrePopulatedPassengerInfo()
                verifySavedCreditCardSelectedByDefault()
                verifySavedCreditCardName()
                verifyCCPaypalOptionsArePresent()
            } else {
                verifyCreditCardSelectedByDefault()
            }
            if (itineraryData.giftCard) {
                clickApplyTravelFunds()
                fillGiftCardAndApply()
                verifyTravelFundsRoutingSubheader()
                verifyTotalAmountForNewFlight()
                verifyAirCharges()
                verifyTravelFundsFlyoutData()
                verifyTravelFundsTable()
                verifyTravelFundsTotalDueNow()
                verifyFundsApplied()
                verifyTotalDueNow()
            }
        }
        rapidRewardsAccountBar.with {
            if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                if(flow.isRapidRewards) {
                    verifyTier()
                    verifyRRTripPoints()
                    verifyRRacountNumber()
                }
            }
        }
        if (itineraryData.hasTravelFunds){
            passengerInfo.fillForm()
            purchasePage.with{
                applyTravelFunds()
                verifyTravelFundsRoutingSubheader()
                verifyTotalAmountForNewFlight()
                verifyAirChargesApplied()
                verifyTravelFundsTableForCancelledPNR()
                verifyTravelFundsFlyoutDataforCancelledPNR()
                verifyTravelFundsAppliedToday()
                verifyTotalAndExchangedTicket()
                verifyAirTotalDueNow()
                verifyCreditCardSelectedByDefault()
                verifyPayPalIsEnabled()
                verifySummaryFundsApplied()
                verifyTotalDueNowLink()
                verifySummaryTotalCostDue()
            }
            customerInfoData.formOfPayment = null
        }
        purchasePage.with {
            verifyCreditCardSelectedByDefault()
            verifyAddEditDisabilitiesButtonIsPresent()
            fillInAllInformation()
        }
        if (itineraryData.hasDisabilities){
            purchasePage.addDisabilities()
            shoppingCart.with {
                verifyShoppingCartIsExpanded()
                verifyAirWidgetIsCollapsed()
                modifyAndRemoveLinks()
                expandAirShoppingCart()
                verifyFlightNumber()
// button has been removed in R14.2, maybe returning.
//                verifySaveTripButton()
//                verifySaveButton()
                verifyFareType()
                verifyDepartureAndArrivalCities()
                verifyDepartureAndArrivalTime()
                verifyTotalCostBreakdown()
                verifyTripTotal()
                verifyDate()
            }
            disabilitiesPage.with {
                addAssistanceWithWheelchair()
                checkBlindOrHaveLowVision()
                verifyBasicPage()
                verifyPageHeader()
                verifyContinueButton()
                verifyCancelButton()
                submit()
            }
            purchasePage.with {
                verifyBasicPage()
                verifyPaxDetails()
                verifySelectedDisabilities()
            }
            customerInfoData.formOfPayment = null
            creditCardSubForm.fillForm()
        }
        shoppingCart.with {
            verifyShoppingCartIsExpanded()
            verifyAirWidgetIsCollapsed()
            modifyAndRemoveLinks()
            verifyTotalAndTotalTripOnShoppingCartAreEqual()
            expandAirShoppingCart()
            verifyFlightNumber()
            verifySaveTripButton()
            verifyDepartureAndArrivalCities()
            verifyDepartureAndArrivalTime()
            verifyTotalCostBreakdown()
            verifyTripTotal()
            verifyGovtTaxesFeesLinkIsPresent()
            verifyFareType()
            verifyDate()
            verifyOutboundAndInboundTotals()
        }
        purchasePage.with {
            verifyTripTotalAndPurchaseTripTotalAreEqual(pricePageData.tripTotal)
            verifyTripTotalAndPurchaseTripTotalAreEqual(shoppingCart.tripTotal())
            verifyTravelersInfoIsDisplayed()
            if(customerInfoData.formOfPayment == "PayPal") {
                verifyPayPalIsSelected()
                verifyCreditCardDataNotDisplayed()
                verifyPayPalTripTotal()
            }
            if(clickSubmit) {
                clickVisibleSubmit(flow.isUM)
            }
        }
    }

    @When("I complete the purchase with points")
    def completePurchaseWithPoints(){
        completePurchasePoints()
    }

    def completePurchasePoints(boolean isUM=false) {
        purchasePage.with {
            verifyBasicPage()
            verifyFormOfPaymentHeader()
        }
        rapidRewardsAccountBar.with {
            verifyRRGreeting()
            verifyLogOutLink()
            verifyRRName()
            verifyMyAccountLink()
            verifyTier()
            verifyRRacountNumber()
            saveAvailablePoints()
        }
        shoppingCart.with {
            verifyShoppingCartIsExpanded()
            verifyAirWidgetIsCollapsed()
            modifyAndRemoveLinks()
            expandAirShoppingCart()
            verifySaveTripButton()
            verifyDate()
            verifyFlightNumber()
            verifyDepartureAndArrivalCities()
            verifyDepartureAndArrivalTime()
            verifyOutboundAndInboundTotals()
            verifyTotalCostBreakdown()
            verifySaveButton()
        }

        purchasePage.with {
            verifyPrePopulatedPassengerInfo()
            verifyTripTotalAndPurchaseTripTotalAreEqual(pricePageData.tripTotal)
            verifyTotalPoints()
            fillInAllInformation()
            savePurchaserInfo()
            clickVisibleSubmit(isUM)
        }
    }

    @When("I continue to the Confirmation Page")
    def continueThroughThePurchasePage() {
        purchasePage.verifyPage()

        if(!DynaStubsIntegration.useDynaStubs()) {
            purchasePage.fillInCreditCardSubFormIfBlank()

            purchasePage.verifyRRContact(rrContact)
            purchasePage.verifyNameThisTripIsChecked()
            purchasePage.verifyNewTripNameIsChecked()
            purchasePage.uncheckNameThisTripOption()
            purchasePage.saveTripTotal()
            purchasePage.savePurchaserInfo()
            shoppingCart.verifyAirInShoppingCartIsCollapsed()
            shoppingCart.expandAirShoppingCart()
            shoppingCart.collapseAirShoppingCart()
            shoppingCart.verifyCollapsedAirDepartureAndArrivalDates()
            shoppingCart.verifyCollapsedAirDepartureAndArrivalCities()
            shoppingCart.verifyCollapsedAirTotal()
            shoppingCart.expandAirShoppingCart()
            shoppingCart.verifyTripTotal()

            if(itineraryData.isPromoCertBooking()){
                purchasePage.savePromoCertExpirationDate()
            }

            purchasePage.selectTextMeAsContactMethod()
            purchasePage.clickContinue()
        }
    }

    @Given("I have cancelled the booked trip")
    def cancelBookedTrip() {
        AirReservation pnr = dynaStubsIntegration.createAirReservation(itineraryData, [tripName: "", canceled: "canceled"], passengerData.getPassengers())
        scenarioState.add pnr
    }

    @When("I click on Add Rapid Rewards Number link")
    def clickOnAddRRNumberLink() {
        confirmationPage.clickOnAddRRNumberLink()
    }

    @Given("I select and book a flight")
    def selectAndBookAFlight() {
        airSelectSteps.selectFlightsAndContinueToPrice()
        navigationSteps.continueToPurchasePage()
        completePurchasePageForm()
        confirmationThenSteps.verifyCompleteItinerary()
    }

    def fillOutPurchasePageAsUM() {
        completePurchasePageForm()
        airPurchaseSteps.clickNoButtonOnUmModal()
        airGuardianPurchaseSteps.fillDepartingUMGuardianForm()
        airGuardianPurchaseSteps.clickContinueOnTheUMGuardianForm()
        airPurchaseSteps.fillInCreditCardOnPurchasePage()
        airPurchaseSteps.clickPurchase()
    }

    @When("I book the trip")
    def tripBooking() {
        selectOutboundFlightAndContinueToPrice()
        continueToPricePageInUpsellModal()
        continueToPurchasePage()
        completePurchasePageForm()
    }


    def selectOutboundFlightAndContinueToPrice() {

        if(flow.isCustomer){
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
            }
        }
        selectFlightsPage.with {
            verifyBasicPage()
            verifySectionHeaders()
            verifyModifySearchWidgetIsCollapsed()
            verifyTripTypeIsCorrect()
            verifyStations()
            verifyDates()
            verifyPassengers()
            verifyDollarsOptionCheckedByDefault()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            selectFlights()
            saveOutboundRouteTypeAndDetails()
            if (itineraryData.isRoundTripOrOpenJaw()) {
                saveInboundRouteTypeAndDetails()
            }
            assignShouldBSUpsellModalShow()
            clickContinueToUpsellModal()
        }
    }

    def continueToPricePageInUpsellModal() {
        selectFlightsPage.with {
            verifyUpsellModal()
            dismissBusinessUpgradeModalIfPresent()
        }
    }

    def continueToPurchasePage() {
        acceptNewPriceAndContinue()
        if (!DynaStubsIntegration.useDynaStubs()) {
            if(flow.isRapidRewards || flow.isCustomer) {
                rapidRewardsAccountBar.with {
                    verifyRRGreeting()
                    verifyLogOutLink()
                    verifyRRName()
                    verifyMyAccountLink()
                    if(flow.isRapidRewards) {
                        verifyRRacountNumber()
                        verifyTier()
                    }
                }
            }
            pricePage.with {
                verifyBasicPage()
                verifyPageHeader()
                verifyAirTotalAndWidgetHeather()
                verifyDepartureAndArrivalTime()
                verifyFlightNumber()
                verifyDepartureAndArrivalCities()
                verifyTravelTime()
                verifyFareType()
                verifyRoutingType()
                verifyDate()
                verifyAirTotalIsEqualToTripTotal()
                verifyPriceMatchInPriceTableAndShoppingCart();
                verifyAirTotalMatchInPriceTableAndShoppingCart()
            }

            priceTable.with {
                verifyPassengerType()
                verifyTripTypes()
                verifyRouting()
                verifyFareType()
                verifyNumberOfPassengers()
                verifySubTotal()
                verifyPriceFareBreakDown()
                verifyRedeemableMemberPoints()

                if(passengerData.containsSeniorAndAdultPassengers()) {
                    verifyMixedPaxSubTotalFromSelectFlightPage()
                }
                verifyAirTotalIsPresent()
                verifyAdultSeniorCount()
                verifyOutboundAndInboundTotalAmounts()
                verifyAirTotalAndFareBreakDownTotal()
                verifyPageAndShoppingCartTotal()
                verifyTotalOnPage()
                verifyBaseFarePlusTax()
                verifyTaxesAndBaseFareInFareBreakDown()
                verifyTotalPlusPassengerCount()
                verifyTotalOnPageWithTotalInFareBreakDown()
            }

            shoppingCart.with {
                airInShoppingCartIsExpanded()
                modifyAndRemoveLinks()
                verifyDate()
                verifyFlightNumber()
                verifyDepartureAndArrivalTime()
                verifyOutboundAndInboundTotals()
                verifyTotalCostBreakdown()
                verifyTripTotal()
                verifySaveAndCheckoutButton()
                verifySaveTripButton()
                verifyDepartureAndArrivalCities()
                verifyGovtTaxesFeesLinkIsPresent()
                verifyFareType()
            }
        }
        pricePage.clickContinue()
    }

    void acceptNewPriceAndContinue() {
        if (repricePage?.isRepricingPage()) {
            if (!DynaStubsIntegration.useDynaStubs()) {
                repricePage.init()
            }
            repricePage.clickContinue()
        }
    }

    @When("I get to the confirmation page")
    def getToConfirmation() {
        selectFlightsAndContinueToPrice()
        continueToPurchasePage()
        completePurchasePageForm()
        if(customerInfoData.formOfPayment.equals("PayPal")){
            completePayPalPurchase()
        }
    }

    def selectFlightsAndContinueToPrice() {
        selectFlightsPage.verifyBasicPage()
        if(flow.isRapidRewards){
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyRRacountNumber()
                verifyTier()
            }
        }
        selectFlightsPage.with {
            verifyModifySearchWidgetIsCollapsed()
            verifyTripTypeIsCorrect()
            verifyStations()
            verifySectionHeaders()
            toggleModifiedSearch()
            verifyDates()
            verifyPassengers()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            if(!flow.isRapidRewardsPointsPurchaseOnly){
                verifyDollarsOptionCheckedByDefault()
            }
            selectFlights()
            if (passengerData.containsSeniorPassenger()) {
                clickContinueForAdultAndSenior()
                selectFlights(true)
            }
            saveOutboundRouteTypeAndDetails()
            if (itineraryData.isRoundTripOrOpenJaw()) {
                saveInboundRouteTypeAndDetails()
            }
            clickContinue()
        }
        acceptNewPriceAndContinue()
    }

    void completePayPalPurchase() {
        payPalPage.with {
            verifyTotal()
            enterPayPalCredentialsOnPurchaseTransitionPage()
            verifyTotal()
            clickPayNow()
        }
    }

    @When("I book a \$pax_fare fare flight and continue to the price page")
    def selectSeniorFareFlightAndContinueToPricePage(@Named("pax_fare") String paxFare) {
        selectFlightForSenior(paxFare)
        continueToPurchasePage()
        completePurchasePageForm()
    }


    @When("I book a senior fare flight and continue to the purchase page")
    def selectSeniorFareFlightAndContinueToPurchasePage() {
        selectFlightForSenior("senior")
        continueToPurchasePage()
    }

    @When("I book a senior fare flight and try to book by entering invalid date of birth")
    def selectSeniorFareFlightAndContinueToPrice() {
        selectFlightForSenior("senior")
        continueToPurchasePage()
        completePurchasePageForm(false)
        passengerInfo.fillInDob(new Date())
        purchasePage.clickVisibleSubmit(false)
    }

    @When("I enter the correct date of birth and book my flight")
    def enterValidDobForSenior() {
        Date birthDay = new Date()
        birthDay[YEAR] = birthDay[YEAR] - 66
        passengerInfo.fillInDob(birthDay)
        purchasePage.clickVisibleSubmit(false)
    }

    @When("I book the trip with points")
    def tripBookingWithPoints() {
        clickOnPointsInSelectFlight()
        selectFlightsAndContinueToPrice()
        continueToPurchasePageWithPoints()
        completePurchasePoints()
    }

    @When("I book the trip with travel funds")
    def tripBookingWithTravelFunds() {
        airSelectSteps.with {
            selectOutboundFlightAndContinueToPrice()
            continueToPricePageInUpsellModal()
        }
        navigationSteps.with {
            navigationSteps.continueToPurchasePage()
        }
        itineraryData.hasTravelFunds = true
        completePurchasePageForm()
    }

    def clickOnPointsInSelectFlight() {
        selectFlightsPage.clickOnPoints()
        flow.isRapidRewardsPointsPurchaseOnly = true
    }

    def continueToPurchasePageWithPoints() {
        acceptNewPriceAndContinue()
        pricePage.verifyBasicPage()
        if (!DynaStubsIntegration.useDynaStubs()) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyRRacountNumber()
                verifyTier()
            }
            pricePage.with {
                verifyPageHeader()
                verifyAirTotalAndWidgetHeather()
                verifyDepartureAndArrivalTime()
                verifyFlightNumber()
                verifyDepartureAndArrivalCities()
                verifyTravelTime()
                verifyFareType()
                verifyRoutingType()
                verifyDate()
                verifyAirTotalIsEqualToTripTotal()
            }
            priceTable.with {
                verifyPassengerType()
                verifyTripTypes()
                verifyRouting()
                verifyFareType()
                verifyNumberOfPassengers()
                verifySubTotal()
                verifyPriceFareBreakDown()
                verifyTotalPoints()
                verifyPageAndShoppingCartTotal()
                verifyAirTotalAndFareBreakDownTotal()
            }
            shoppingCart.with {
                airInShoppingCartIsExpanded()
                modifyAndRemoveLinks()
                verifyDate()
                verifyFlightNumber()
                verifyDepartureAndArrivalTime()
                verifyFareType()
                verifyTotalCostBreakdown()
                verifyOutboundAndInboundTotals()
                verifyGovtTaxesFees()
                verifySaveAndCheckoutButton()
                verifySaveTripButton()
            }

        }
        pricePage.clickContinue()
    }

    def selectFlightForSenior(String paxFare) {
        selectFlightsPage.with {
            verifySeniorBasicPage()
            verifySectionHeaders()
            verifyModifySearchWidgetIsCollapsed()
            verifyTripTypeIsCorrect()
            verifyStations()
            verifyDates()
            verifyPassengers()
            verifyDollarPointToggleOff()
            verifyFareTypesNames()
            verifyCalendarCarouselContainsTenDates()
            verifyDateSelectedIsHighlighted()
            if (paxFare.equals("senior")){
                selectFlights(true)
            }else{
                selectFlights()
            }
            saveOutboundRouteTypeAndDetails()
            clickContinue()
        }
    }

    @When("I book a flight for UM")
    def selectFlightAndContinueToPurchasePage() {
        selectOutboundFlightAndContinueToPrice()
        continueToPricePageInUpsellModal()
        continueToPurchasePage()
        fillOutPurchasePageAsUM()
    }

    @When("I book a flight for passengers \$passengers")
    def iBookMyEarlyBirdFlight(List<String> passengers) {
        itineraryData.adultPassengerCount = passengers.size()
        for (String passengerTitle : passengers) {
            passengerData.addRRUserToPassengerList(data.getUser(passengerTitle))
        }
        airSearchSteps.fillInFlightSearchInfoAndClick()
        selectFlightsPage.selectFlights()
        priceAndFillPurchaseInformation()
        navigationSteps.clickContinueToComplete()
    }
    
    def fillOutPurchasePageAsUMWithPoints() {
        boolean isUM = true
        completePurchasePoints(isUM)
        airPurchaseSteps.clickNoButtonOnUmModal()
        if(itineraryData.isOneWay()) {
            airGuardianPurchaseSteps.fillDepartingUMGuardianForm()
        }
        else {
            airGuardianPurchaseSteps.fillUMGuardianFormForARoundTrip()
        }
        airPurchaseSteps.fillInCreditCardOnPurchasePage()
        airPurchaseSteps.clickPurchase()
    }

    @When("I continue and fill the purchase page as an UM with points")
    def continueToPurchasePageForUMWithPoints(){
        continueToPurchasePageWithPoints()
        fillOutPurchasePageAsUMWithPoints()
    }

    @When("I book a \$itineraryType flight with a promo cert and codeshare")
    def bookTripWithPromoCertAndCodeshare(String itineraryType) {
        Map yaml = itineraryData.yamlItineraryData()

        String[] itineraryTypeSplit = itineraryType.split("\\s")
        itineraryData.itineraryType = itineraryTypeSplit[0].capitalize() + " " + itineraryTypeSplit[1].capitalize()

        if (itineraryData.itineraryType == "Round Trip"){
            itineraryData.departureStation = yaml.codeShare.departAirport
            itineraryData.arrivalStation = yaml.codeShare.arriveAirport
            itineraryData.departingFlight_carrierCode = yaml.codeShare.southwestCarrier
            itineraryData.arrivingFlight_carrierCode = yaml.codeShare.airtranCarrier
            accountSteps.bookRoundTripTripWithPromoCert()
        }
        else if (itineraryData.itineraryType == "One Way"){
            itineraryData.departureStation = yaml.codeShare.departAirport
            itineraryData.arrivalStation = yaml.codeShare.arriveAirport
            itineraryData.outboundConnectingStation = yaml.market.default.outboundConnectingStation
            itineraryData.inbound_connection_carrierCode = yaml.codeShare.southwestCarrier
            itineraryData.outbound_connection_carrierCode = yaml.codeShare.airtranCarrier
            flow.hasConnectionFlight = true
            flow.hasInboundConnectionFlight = true
            itineraryData.inboundRouting = "1 stop"
            itineraryData.outboundRouting = "1 stop"
            accountSteps.bookOneWayTripWithPromoCert()
        }
        navigationSteps.continueToPurchasePage()
        continueThroughThePurchasePage()
    }

    @Given("I search for an open-jaw flight with \$fareType fare")
    def initializeOpenJawTripData(@Named("fareType") String fareType) {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.openjaw.departAirport
        itineraryData.arrivalStation = yaml.openjaw.arriveAirport
        itineraryData.returnStation = yaml.openjaw.returnAirport
        itineraryData.itineraryType = itineraryData.OPEN_JAW_ITINERARY
        itineraryData.departingFlight_carrierCode = "WN"
        itineraryData.arrivingFlight_carrierCode = "WN"
        itineraryData.returningFlight_carrierCode = "WN"
        itineraryData.departingFlight_fareClass = fareType
        itineraryData.arrivingFlight_fareClass = fareType
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        customerInfoData.formOfContact = "Text"
        flow.hasAir = true
    }
}
