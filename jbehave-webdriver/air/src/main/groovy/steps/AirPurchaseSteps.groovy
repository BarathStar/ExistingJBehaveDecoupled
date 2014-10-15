package steps

import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.When
import org.jbehave.core.model.ExamplesTable
import pages.elements.CreditCardSubForm
import pages.elements.FlightSearchForm
import pages.elements.PurchaseSubForm
import pages.elements.ShoppingCart
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.ItineraryData
import pages.*
import com.swacorp.dotcom.webscenarios.air.CreditCard
import util.PricePageData
import util.PurchasePageData
import pages.elements.FeeCalculator
import util.CustomerInfoData

class AirPurchaseSteps {
    public static final String CSS_CLASS_FOR_NON_ERROR_OOPS = "errors_no_image"
    private static String CREDIT_CARD_DINERS = "DINERS_CLUB"
    private static String CREDIT_CARD_VISA = "VISA"
    private static String CREDIT_CARD_UATP = "UATP"

    Flow flow

    PurchasePage purchasePage
    ConfirmationPage confirmationPage
    UnaccompaniedMinorPurchasePage unaccompaniedMinorPurchasePage
    NavigationSteps navigationSteps
    Data data
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    PricePage pricePage
    RepricePage repricePage
    PurchaseSubForm[] subForms;
    FlightSearchForm flightSearchForm
    CreditCardSubForm creditCardSubForm
    AirPricingSteps airPricingSteps
    AirSelectSteps airSelectSteps
    CommonSteps commonSteps
    UnaccompaniedMinorGuardianPage unaccompaniedMinorGuardianPage
    AccompanyingAdultPage accompanyingAdultPage
    YoungTravelerGuardianPage youngTravelerGuardianPage
    ItineraryData itineraryData
    Itinerary itinerary
    ShoppingCart shoppingCart
    PassengerData passengerData
    ScenarioState scenarioState
    PricePageData pricePageData
    PurchasePageData purchasePageData
    FeeCalculator feeCalculator
    CustomerInfoData customerInfoData


    @Given("I am on the purchase page")
    @When("I am on the purchase page")
    def goToPurchasePage() {
        searchFlightsPage.open()
        def adultCnt = itineraryData.adultPassengerCount ?: "1"
        def seniorCnt = itineraryData.seniorPassengerCount ?: "0"
        flightSearchForm.selectAdultAndSeniorPassengers(adultCnt.toInteger(), seniorCnt.toInteger())
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        airPricingSteps.selectFlightsAndPrice()
        pricePage.clickContinue()
        purchasePage.verifyPage()
    }

    @Given("I have added the EB checkin to the flight")
    @When("I include early bird add-on")
    def haveFilledPurchasePageWithEarlyBird() {
        if(DynaStubsIntegration.useDynaStubs()) {
            itineraryData.earlyBird = true
        } else {
            purchasePage.clickAddEarlyBird()
        }
    }

    @Given("I have completed the booking process adding the Air product to a new trip named \$tripName")
    void haveCompletedBookingAddingFlightToATrip(String tripName){
        if(DynaStubsIntegration.useDynaStubs()) {
            AirReservation airReservation = new DynaStubsIntegration().createAirReservation(itineraryData, [tripName:tripName], passengerData.getPassengers())
            scenarioState.add airReservation
        } else {
            completeBookingAddingFlightToATrip(tripName)
        }
    }

    @When("I search and select flights and continue to the Purchase page")
    void searchSelectFlightsAndContinueToPurchasePage() {
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        airSelectSteps.selectFlightsAndContinue()
        navigationSteps.continueToPurchasePage()
        purchasePage.verifyPage()
    }

    @When("I select flights and continue to the Purchase page")
    void selectFlightsAndContinueToPurchasePage() {
        airSelectSteps.selectFlightsAndContinue()
        navigationSteps.continueToPurchasePage()
        purchasePage.verifyPage()
    }

    @When("I select, price and view the Purchase page for a flight")
    def selectFlightAndPriceAndContinueToPurchase() {
        airPricingSteps.selectFlightsAndPrice()
        pricePage.clickContinue()
        purchasePage.verifyPage()
    }

    @When("I search and select a flight and am on the purchase page")
    def searchAndSelectAFlight(){
        searchFlightsPage.open()
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        if(passengerData.containsSeniorAndAdultPassengers()){
            selectFlightsPage.verifyPage()
            selectFlightsPage.selectFlights()
            selectFlightsPage.clickContinueForAdultAndSenior()
            selectFlightsPage.selectFlights()
        }else{
            selectFlightsPage.selectFlights()
        }

        selectFlightsPage.clickContinue()
        if (repricePage?.isRepricingPage()) {
            repricePage.clickContinue()
        }
        pricePage.verifyBasicPage()
        pricePage.clickContinue()
    }

    @When("I fill out the purchase form")
    def fillOutPurchaseForm() {
        purchasePage.fillInAllInformation()
    }

    @Given("I filled in the Purchase form and submit")
    @When("I fill in the Purchase form and submit")
    @Alias("I complete the booking process without adding the air product to a trip")
    def fillInPurchasePageAndCompleteBooking() {

        if (flow.isTravelFundApplied){
            AirReservation airReservation =  scenarioState.getLastAirReservation()
            passengerData.passengers=airReservation.getPassengers()
        }
        purchasePage.fillInAllInformation()
        navigationSteps.clickContinueToComplete()
    }

    @When("I fill in the Purchase form with RR Signup checked and submit")
    def fillInPurchasePageWithRRSignupCheckedAndSubmit() {
        flow.isBuyerEqualsFlyer = true
        purchasePage.fillInAllInformation()
        purchasePage.clickOnRRSignupCheckbox()
        navigationSteps.clickContinueToComplete()
    }

    @When("I view RR Signup module is not present in purchase page")
    def verifyRRSignUpModuleIsNotPresent() {
        flow.isBuyerEqualsFlyer = true
        purchasePage.verifyRRModuleNotPresent()
    }

    @When("I view RR Signup module present in purchase page")
    def verifyRRSignUpModuleIsPresent() {
        flow.isBuyerEqualsFlyer = true
        purchasePage.verifyRRModulePresent()
    }

    @When("I select and attempt to purchase a flight")
    void selectAndAttemptToPurchase(){
        selectFlightsPage.verifyPage()
        selectFlightsPage.selectFlights()
        flow.completePurchase = false
        selectFlightAndPriceAndContinueToPurchase()
        purchasePage.fillInAllInformation()
        if (flow.completePurchase) {
            navigationSteps.clickContinueToComplete()
        } else {
            purchasePage.clickVisibleSubmit()
            flow.completePurchase = true // for next scenario
        }
    }

    @When("I fill in the credit card fields and complete the purchase")
    def fillInCreditCardOnPurchasePageAndCompleteBooking() {
        fillInCreditCardOnPurchasePage()
        navigationSteps.clickContinueToComplete()
    }

    def fillInCreditCardOnPurchasePage() {
        customerInfoData.formOfPayment = null
        purchasePage.with {
            verifyPage()
            verifyNonEditablePaxDetails()
            verifyEditPassengerInfoButton()
            if (!purchasePageData.umHasCompanion) {
                verifyViewParentGuardianInfoLink()
                verifyEditParentGuardianInfoLink()
            }
            if (!DynaStubsIntegration.useDynaStubs()) {
                verifyTripTotalAndPurchaseTripTotalAreEqual(pricePageData.tripTotal)
            }
        }
        shoppingCart.with {
            verifyShoppingCartIsExpanded()
            verifyAirInShoppingCartIsCollapsed()
            expandAirShoppingCart()
            modifyAndRemoveLinks()
            verifyDate()
            verifyDepartureAndArrivalCities()
            if (!DynaStubsIntegration.useDynaStubs()) {
                verifyOutboundAndInboundTotals()
                verifyTotalCostBreakdown()
                verifyTripTotalWithUMFee()
                verifyFlightNumber()
                verifyDepartureAndArrivalTime()
            }
            if (!purchasePageData.umHasCompanion) {
                verifyUMFeeUnderShoppingCart()
            }
            verifySaveTripButton()
        }
        creditCardSubForm.fillForm()
    }

    @When("I fill in the credit card information and attempt to purchase a flight as a young traveler")
    def fillInCreditCardOnPurchasePageAndCompleteBookingAsYoungTraveler() {
        creditCardSubForm.fillForm()
        purchasePage.clickVisibleSubmitAsYoungTraveler()
    }

    @When("I add a Disability Option")
    def addDisabilityOption(){
        purchasePage.addDisability()
    }

    @When("I add SSR Options as follows: \$ssrTbl")
    def addSSROptions(ExamplesTable ssrTbl){
        List<Map<String, String>> rows = ssrTbl.getRows()
        int idx = -1
        rows.each {
            try {
                String index = it.getAt("Pax Index")
                String options = it.getAt("Options")
                purchasePage.addSSROptions(index, options)
            }
            catch (NoSuchFieldException x) {
                x.printStackTrace()
            }
            catch (IllegalAccessException x) {
                x.printStackTrace();
            }
        }
    }

    @When("I fill out the purchase form with Early Bird")
    def fillPurchasePageWithEarlyBird() {
        purchasePage.clickAddEarlyBird()
        fillOutPurchaseForm()
    }

    @When("I fill out the purchase form with Early Bird and a long first and last name")
    def fillPurchasePageWithEarlyBirdAndALongFirstAndLastName() {
        passengerData.addPassengerWithLongName()
        fillPurchasePageWithEarlyBird()
    }

    @When("I enter an incomplete confirmation number as a travel fund")
    def enterIncompleteConfirmationNumberAsTravelFund() {
        purchasePage.fillInFirstLastName(data.firstName, data.lastName)
        purchasePage.clickApplyTravelFunds()
        purchasePage.enterTravelFundConfirmationNumber("ABC", data.firstName, data.lastName)
        purchasePage.submitTravelFunds()
    }

    @When("I enter an invalid confirmation number as a travel fund")
    def enterInvalidConfirmationNumberAsTravelFund() {
        purchasePage.fillInFirstLastName(data.firstName, data.lastName)
        purchasePage.clickApplyTravelFunds()
        purchasePage.enterTravelFundConfirmationNumber("ABC123", data.firstName, data.lastName)
        purchasePage.submitTravelFunds()
    }

    @When("I click on Apply Travel Funds")
    def clickApplyTravelFunds() {
        purchasePage.clickApplyTravelFunds()
    }

    @When("I attempt to book a flight as UM")
    void attemptToBookCodeShareUmFlight()  {
        purchasePage.shouldNotBookFlightAsUm()
    }

    @When("I attempt to book a flight as UM under 5")
    void attemptToBookCodeShareUmUnder5Flight() {
        purchasePage.shouldBookFlightAsUm()
    }

    @When("I attempt to book a flight as UM and the payment method is <payment>")
    @Alias("I attempt to book a flight as UM and the payment method is \$payment")
    void attemptToBookUmFlightWithMethodPayment(@Named("payment") String payment) {
        purchasePage.bookFlightAsUm(payment)
        purchasePage.clickNoUMModal()
    }

    @When("I attempt to add an accompanying traveler using an invalid PNR")
    void addAccompanyingTravelerToMinorOnPurchasePage() {
        purchasePage.selectAddAccompanyingTravelerToMinor()
        purchasePage.retrieveAccompanyingAdultPNR("ABC123", "Sam", "Leapfrog")
    }

    @When("I fill passenger and parent guardian information and the payment method is <payment>")
    @Alias("I fill passenger and parent guardian information and the payment method is \$payment")
    def whenIBookAFlightAsUM(@Named("payment") String payment) {
        purchasePage.bookFlightAsUm(payment)
        purchasePage.clickNoUMModal()
        unaccompaniedMinorPurchasePage.fillInParentGuardiansInformation()
        unaccompaniedMinorPurchasePage.clickContinue()
    }

    @When("I complete the credit card information and finish the purchase")
    def endUmBooking() {
        completeCreditCardInfo()
        navigationSteps.clickContinueToComplete()
        confirmationPage.checkSomethingServed()
    }

    @Given("I purchase the flight")
    @When("I click on the Purchase button")
    def clickPurchase() {
        purchasePage.clickVisibleSubmit()
    }

    @When("I change the credit card owner")
    def changeCreditCardOwner() {
        purchasePage.fillInCreditCardOwner("Bob","Foo")
    }

    @When("I change the passenger's name")
    def changePassengerName() {
        purchasePage.fillInFirstLastName("Bob","Foo")
    }

    @When("I confirm there will not be a person traveling as a companion on the YT modal")
    void clickOnTheNoButtonInYTModal() {
        purchasePage.verifyPage()
        purchasePage.clickOnTheNoButtonInYTModal()
        youngTravelerGuardianPage.verifyPage()
    }

    @When("I confirm there will be a person traveling as a companion on the YT modal")
    void clickOnTheYesButtonInYTModal() {
        purchasePage.verifyPage()
        purchasePage.clickOnTheYesButtonInYTModal()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I view the Parent/Guardian Information on the Non-Editable Purchase page")
    void clickOnViewParentGuardianInfoLink() {
        purchasePage.clickOnViewParentGuardianInfoLink()
    }

    @When("I attempt to edit the Parent/Guardian information on the Non-Editable Purchase page")
    void clickOnEditParentGuardianInfoLink() {
        purchasePage.verifyPage()
        purchasePage.clickOnEditParentGuardianInfoLink()
        unaccompaniedMinorGuardianPage.verifyPage()
    }

    @When("I confirm there will not be a person traveling as a companion on the UM modal")
    def clickNoButtonOnUmModal() {
        flow.isUM = true
        purchasePage.verifyPage()
        purchasePage.verifyUnaccompaniedMinorDialog()
        purchasePage.clickNoButtonOnUmModal()
        purchasePageData.guardianFee = feeCalculator.calculateGuardianCharge()
        purchasePage.checkSomethingServed()
    }

    @When("I confirm there will be a person traveling as a companion on the UM modal")
    def clickYesButtonOnUmModal() {
        purchasePage.verifyPage()
        purchasePage.clickYesButtonOnUmModal()
        purchasePageData.umHasCompanion = true
        accompanyingAdultPage.verifyPage()
    }

    @When("I click on the Edit Passenger Information button on the Non-Editable Purchase Page")
    void clickOnEditPassengerInfoButton() {
        purchasePage.clickOnEditPassengerInfoButton()
        purchasePage.verifyPage()
    }

    @Given("I apply a LUV Voucher as form of payment")
    @When("I apply a LUV Voucher as form of payment")
    void enterLUVVoucher() {
        purchasePage.clickApplyTravelFunds()
        purchasePage.fillVoucherAndApply()
    }

    @When("I select the Air product on the Shopping Cart")
    void clickOnPlusAirButton() {
        shoppingCart.clickOnPlusAirButton()
    }

    @When("I do not check the name this trip option on the Purchase page")
    @Given('I unchecked the name this trip option on the Purchase page')
    void uncheckNameThisTripOption() {
        purchasePage.uncheckNameThisTripOption()
    }

    @When("I complete the booking process adding the Air product to a new trip named \$tripName")
    void completeBookingAddingFlightToATrip(String tripName){
        purchasePage.fillInAllInformation()
        purchasePage.enterNewTripName(tripName)
        navigationSteps.clickContinueToComplete()
    }

    @When("I click on the Unaccompanied Minor Fee link")
    void clickUMFeeLink() {
        shoppingCart.openUMChargeFlyOut()
    }

    @When("I fill in the credit card information and attempt to purchase a flight as an unaccompanied minor")
    def fillInCreditCardOnPurchasePageAndCompleteBookingAsUnaccompaniedMinor() {
        creditCardSubForm.fillForm()
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
    }

    @When("I complete the booking process adding the credit card to the account")
    def storeNewCreditCard() {
        purchasePage.fillInInformationForUserLoggedIn()
        creditCardSubForm.addCreditCardToAccount()
        navigationSteps.clickContinueToComplete()
    }

    @When("I check name your trip")
    def checkNameYourTrip() {
        purchasePage.ensureNameThisTripCheckBoxIsChecked()
    }

    @When("I complete the booking process by adding the Air product to the existing trip \$tripName")
    def fillInPurchasePageAndCompleteBookingAddingToExistingTrip(String tripName) {
        purchasePage.selectExistingTripName(tripName)
        purchasePage.fillInInformationForUserLoggedIn()
        navigationSteps.clickContinueToComplete()
    }

    @When("I complete the credit card information")
    def completeCreditCardInfo() {
        subForms.each {if (it instanceof CreditCardSubForm){
                it.fillForm()
            }}
    }

    @When("I fill out the purchase form as a logged in user")
    def fillOutPurchaseFormAsLoggedInUser() {
        purchasePage.fillInInformationForUserLoggedIn()
    }

    @Given("I am on the purchase page booking with Points")
    def goToPurchasePageBookingWithPoints() {
        searchFlightsPage.open()
        def adultCnt = itineraryData.adultPassengerCount ?: "1"
        def seniorCnt = itineraryData.seniorPassengerCount ?: "0"
        flightSearchForm.selectAdultAndSeniorPassengers(adultCnt.toInteger(), seniorCnt.toInteger())
        flightSearchForm.chooseFaresInPoints()
        flow.isRapidRewardsPointsPurchaseOnly = true
        flow.isRapidRewards = true
        flow.isLoggedIn = true
        flightSearchForm.fillInFlightSearchInfoAndClick(itineraryData, true)
        airPricingSteps.selectFlightsAndPrice()
        pricePage.clickContinue()
        purchasePage.verifyPage()
    }

    @When("I select the Click N'Save checkbox")
    def selectClickAndSaveCheckbox() {
        purchasePage.clickOnClickAndSaveCheckbox()
    }

	@When("I click on Chase Instant Credit banner in the purchase page")
	public void clickOnChaseInstantCreditBannerPruchasePage() {
		purchasePage.clickOnChaseInstantCreditBanner()
	}

	@When("I click on Chase Instant Credit banner in the price page")
	public void clickOnChaseInstantCreditBannerPricePage() {
		pricePage.clickOnChaseInstantCreditBanner()
	}

    @When("I make a companion reservation")
    public void verifyCompanionBookingAndBook() {
        purchasePage.verifyCompanionBooking()
        purchasePage.bookCompanion()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @Given("I have selected Yes to add Early Bird to my air purchase")
    def addEarlyBirdToPurchase() {
        purchasePage.clickAddEarlyBird()
    }

    @Given("I have selected \$ccType credit card as my form of payment")
    def setCreditCardForPurchase(String ccType) {
        if (!"".equals(ccType)) {
            String creditCardType = CreditCard.TYPE.VISA

            switch (ccType) {
                case CreditCard.TYPE.UATP.toString():
                    creditCardType = CreditCard.TYPE.UATP
                    break
            }

            creditCardSubForm.fillForm(creditCardType)
        }
    }

    @Given("I apply a valid TravelFund with the full amount of the purchase")
    @When("I apply a valid TravelFund with the full amount of the purchase")
    def fillValidTravelFund(){
        purchasePage.applyTravelFunds()
        purchasePage.verifyTravelFundsFlyOut()
    }

    @Given("I complete mandatory information for Passenger \$paxIndex")
    @When("I complete mandatory information for Passenger \$paxIndex")
    def fillModulePassenger(Integer paxIndex) {
        paxIndex = paxIndex - 1
        purchasePage.fillModulePassenger(paxIndex)
    }

    @When("I attempt to purchase Early Bird using \$creditCardType credit card")
    def attemptToPurchaseEarlyBirdWithCreditCard(String creditCardType) {
        String ccType = CREDIT_CARD_VISA;

        switch (creditCardType){
            case "Diners" :
                ccType = CREDIT_CARD_DINERS
                break
            case "UATP" :
                ccType = CREDIT_CARD_UATP
                break
        }

        purchasePage.clickAddEarlyBird()
        purchasePage.fillInAllInformation(ccType)
        purchasePage.clickContinue()
    }

}
