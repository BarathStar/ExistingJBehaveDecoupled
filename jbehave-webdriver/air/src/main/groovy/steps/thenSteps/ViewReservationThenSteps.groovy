package steps.thenSteps

import domain.AirReservation

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Named
import org.jbehave.core.annotations.Then
import pages.ConfirmationPage
import pages.ViewReservationPage
import pages.elements.RapidRewardsAccountBar
import state.Flow
import state.ScenarioState
import util.ItineraryData
import util.RRContactInformation
import util.TripManagement
import state.PassengerData
import domain.Passenger

class ViewReservationThenSteps {

    private static final String OOPS_MYIDTRAVEL_MESSAGE = "The nonrevenue flight listing that you're attempting to access was made through MyID Travel and is not accessible via southwest.com, swabiz.com or mobile.southwest.com. To access your flight listing, please visit the MyID Travel site."

    ViewReservationPage viewReservationPage
    ScenarioState scenarioState
    ConfirmationPage confirmationPage
    ItineraryData itineraryData
    Flow flow
    RRContactInformation rrContactInformation
    RapidRewardsAccountBar rapidRewardsAccountBar
    PassengerData passengerData

    @Then("I should see the correct view itinerary page")
    def checkItineraryPage() {
        viewReservationPage.verifyCarrierInformation()
    }

    @Then("I should see the correct one-way view itinerary page")
    def checkOneWayItineraryPage() {
        viewReservationPage.verifyCarrierInformation()
    }

    @Then("I view the oops messages <msgs>")
    def pageMessage(@Named("msgs") List<String> msgs) {
        viewReservationPage.verifyOopsMessages(msgs)
    }

    @Then("I view my itinerary")
    @Alias("I view my Adult itinerary")
    def viewItinerary() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        passengerData.passengers = airReservation.passengers
        viewReservationPage.verifyPage()
        viewReservationPage.verifyItineraryNote()
        viewReservationPage.verifyHideDetailsExpanded()
        viewReservationPage.verifyStationInDetails()
        viewReservationPage.verifyAdultPassengerName()
        if(flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer)) {
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            if(flow.isRapidRewards) {
                rapidRewardsAccountBar.verifyTier()
                rapidRewardsAccountBar.verifyRRacountNumber()
            }
        } else {
            viewReservationPage.verifyAdultRRLinkPresent()
        }
        viewReservationPage.verifyAddEarlyBirdCheckInButtonDisplayed()
        if (!itineraryData.pnrSource) {
            viewReservationPage.verifyResendReceipt()
            viewReservationPage.verifyChangeReservationLink()
            viewReservationPage.verifyCancelReservationLink()
        }
        viewReservationPage.verifyPrintAndShare()
        viewReservationPage.verifyDisabilityLinkPresent()
        if(itineraryData.isValidForCheckin.toBoolean()) {
            viewReservationPage.verifyCheckInButtonDisplayed()
        }
        viewReservationPage.verifyConfirmationNumber(airReservation.adultPnr)
        if (!DynaStubsIntegration.useDynaStubs()) {
            viewReservationPage.verifySectionTitle(itineraryData.departureDate)
            //viewReservationPage.verifyTripName(itineraryData.getDefaultTripName())   //aruna
            viewReservationPage.verifyDate()
            viewReservationPage.verifyDepartureAndArrivalTime()
            viewReservationPage.verifyTravelTime()
            viewReservationPage.verifyFlightNumber()
            viewReservationPage.verifyDepartureAndArrivalCities()
            viewReservationPage.verifyFareType()
            viewReservationPage.verifyRoutingType()
            if (flow.isRapidRewards) {
                viewReservationPage.verifyRrNumber(rrContactInformation.accountNumber)
            }
            if (flow.isUM) {
                viewReservationPage.with {
                    verifyUnaccompainedMinorIconIsDisplayed()
                    verifyPassengerAssistanceRequested()
                }
            }
        }
        if (itineraryData.hasDisabilities){
            viewReservationPage.verifyAddEditDisabilitiesButtonIsPresent()
            viewReservationPage.verifyAssistanceRequestedText()
        }
        passengerData.passengers = new ArrayList<Passenger>()
    }

    @Then("I view my Senior itinerary")
    def viewSeniorItinerary() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        passengerData.passengers = airReservation.passengers
        viewReservationPage.verifyPage()
        viewReservationPage.verifySectionTitle(airReservation.itineraryData.departureDate)
        viewReservationPage.verifyConfirmationNote()
        viewReservationPage.verifyPrintAndShare()
        if (!DynaStubsIntegration.useDynaStubs()) {
            viewReservationPage.verifyTripName(itineraryData.getDefaultTripName())
            viewReservationPage.verifyDate()
            viewReservationPage.verifyDepartureAndArrivalTime()
            viewReservationPage.verifyTravelTime()
            viewReservationPage.verifyFlightNumber()
            viewReservationPage.verifyDepartureAndArrivalCities()
            viewReservationPage.verifySeniorFareType()
            viewReservationPage.verifyRoutingType()
        }
        if (!itineraryData.pnrSource) {
            viewReservationPage.verifyResendReceipt()
            viewReservationPage.verifyChangeReservationLink()
            viewReservationPage.verifyCancelReservationLink()
        }
        viewReservationPage.verifyStationInDetails()
        viewReservationPage.verifyConfirmationNumber(airReservation.seniorPnr)
        viewReservationPage.verifySeniorPassengerNameOnReservation()
        viewReservationPage.verifySeniorRRLinkPresent()
        viewReservationPage.verifyDisabilityLinkPresent()
        viewReservationPage.verifyAddEarlyBirdCheckInButtonDisplayed()
        viewReservationPage.verifyItineraryNote()
        viewReservationPage.verifyHideDetailsExpanded()
    }

    @Then("I see the EB indicator")
    def verifyEBIndicator() {
        viewReservationPage.verifyEBIndicator()
    }

    @Then("I see an icon which informs that the EB Checkin has been added to the flight on the View Reservation Page")
    def verifyEBIndicatorAndText() {
        viewReservationPage.verifyEBIndicator()
        viewReservationPage.verifyEBIndicatorText()
    }

    @Then("I should not see any references to the Business Select")
    def verifyBusinessSelectIconNotPresent() {
        viewReservationPage.verifyBusinessSelectIconIsNotPresent()
    }

    @Then("I see my fareclass link")
    void verifyBusinessSelectLink(){
        viewReservationPage.verifyFareClassLink(itineraryData.arrivingFlight_fareClass)
    }

    @Then("I see the business select text")
    void verifyBusinessSelectText(){
        viewReservationPage.verifyBusinessSelectText()
    }

    @Then("I see fare information page")
    void verifyFareInformationPage(){
        viewReservationPage.verifyFareInformationPage()
    }

    @Then("I view the OOPS message for the invalid PNR on the View/Share Itinerary page")
    def verifyExpectedOOPSMessagePresent() {
        viewReservationPage.verifyUnableToRetrievePNROOPSMessage()
    }

    @Then('I see the trip name \$tripName')
    void verifyTripName(String tripName) {
        viewReservationPage.verifyTripName(tripName)
    }

    @Then('I see the Rename Trip button enable')
    void verifyRenameButtonEnable() {
        viewReservationPage.verifyRenameButtonEnable()
    }

    @Then('I see the default trip name')
    @Alias('I see on the View Reservation page that the Air product has the default trip name')
    void verifyDefaultTripName() {
        String tripName = TripManagement.getDefaultTripName(itineraryData.departureDate, itineraryData.arrivalStation)
        viewReservationPage.verifyTripNameIsFormattedByDefault(tripName)
    }

    @Then('I see on the View Reservation page that the Air product has the flow trip name')
    void verifyFlowTripName() {
        viewReservationPage.verifyTripNameIsFormattedByDefault(flow.tripName)
    }

    @Then('I am not able to change the trip name')
    void verifyRenameButtonNotDisplayed() {
        viewReservationPage.verifyRenameButtonNotDisplayed()
    }

    @Then("I see the OOPS message for the failing unassociated credit card PNR retrieval")
    void verifyInvalidCreditCardOOPSMessage() {
        viewReservationPage.verifyInvalidCreditCardOOPSMessage()
    }

    @Then('I see expanded flight details for the air product')
    @Alias('I see the Air product details')
    void verifyAirProductExpandedDetails() {
        viewReservationPage.verifyAirProductExpandedDetails()
    }

    @Then('I see the Items associated with this Trip verbiage')
    void verifyItemAssociatedVerbiage() {
        viewReservationPage.verifyItemsAssociatedVerbiageDisplayed()
    }

    @Then('I see the Items associated with this Trip verbiage on the cancelation modal')
    void verifyItemAssociatedVerbiageOnCancelationModal() {
        viewReservationPage.verifyItemsAssociatedVerbiageModal()
    }

    @Then('I see all the associated items sorted by the earliest date')
    @Alias('I see the Associated Items to the trip sorted by the earliest date with their details hidden')
    void verifyAssociatedItemsSortedByEarliestDate() {
        viewReservationPage.verifyAssociatedItemsSortedByEarliestDate()
        viewReservationPage.verifyAssociatedItemsCollapsed()
    }

    @Then('I see all the associated items collapsed')
    void verifyAssociatedItemsCollapsed() {
        viewReservationPage.verifyAssociatedItemsCollapsed()
    }

    @Then('I see the Car Product as an associated item')
    void verifyCarProductAssociatedItem() {
        viewReservationPage.verifyCarProductAssociatedInfo()
    }

    @Then('I see the Hotel Product as an associated item')
    void verifyHotelProductAssociatedItem() {
        viewReservationPage.verifyHotelProductAssociatedItem()
    }

    @Then('I see the air information on the cancellation modal')
    void verifyAirInformationOnCancelationModal() {
        viewReservationPage.verifyAirInformationOnCancelationModal()
    }

    @Then('I see the Car Product on the cancellation modal')
    void verifyCarProductOnCancelationModal() {
        viewReservationPage.verifyCarProductOnCancelationModal()
    }

    @Then('I should not see the Hotel and Air Products as associated items on the Air Associated Products Modal')
    void verifyHotelAndAirProductOnCancellationModalNotDisplayed() {
        verifyHotelProductOnCancellationModalNotDisplayed()
        verifyAirProductOnCancellationModalNotDisplayed()
    }

    @Then('I do not see the Hotel Product as an associated item on the Air Cancellation Modal')
    void verifyHotelProductOnCancellationModalNotDisplayed() {
        viewReservationPage.verifyCancelationModalHotelItemIsNotPresent()
    }

    @Then('I do not see the Air Product as an associated item on the Air Cancellation Modal')
    void verifyAirProductOnCancellationModalNotDisplayed() {
        viewReservationPage.verifyCancelationModalAirItemIsNotPresent()
    }

    @Then('I do not see the Hotel Product as an associated item')
    void verifyHotelProductInformationNotDisplayed() {
        viewReservationPage.verifyIsHotelInfoNotDisplayed()
    }

    @Then('I should not see the Air, Car and Hotel cross sell sections')
    void verifyAirCarHotelProductsCrossSellNotDisplayed() {
        verifyAirProductCrossSellNotDisplayed()
        verifyCarProductCrossSellNotDisplayed()
        verifyHotelProductCrossSellNotDisplayed()
    }

    @Then('I should not see the Air Cross Sell Section')
    void verifyAirProductCrossSellNotDisplayed() {
        viewReservationPage.verifyAirProductCrossSellNotDisplayed()
    }

    @Then('I should not see the Car Cross Sell Section')
    void verifyCarProductCrossSellNotDisplayed() {
        viewReservationPage.verifyCarProductCrossSellNotDisplayed()
    }

    @Then('I should not see Hotel Cross Sell Section')
    void verifyHotelProductCrossSellNotDisplayed(){
        viewReservationPage.verifyHotelProductCrossSellNotDisplayed()
    }

    @Then('I see the Hotel Cross Sell Section')
    void verifyHotelProductCrossSellDisplayed() {
        viewReservationPage.verifyHotelProductCrossSellDisplayed()
    }

    @Then('I should not see items associated to the trip')
    void verifyAssociatedItemsNotDisplayed() {
        viewReservationPage.verifyAssociatedItemsNotDisplayed()
    }

    @Then('I do not see the Disambiguation page')
	@Alias('I should not see the Air Disambiguation Page')
    void verifyINotSeeDisambiguationPage() {
        viewReservationPage.verifyINotSeeDisambiguationPage()
    }

    @Then('I am able to add the Early Bird Check-in to my Air product')
    void verifyAddEarlyBirdCheckInButtonDisplayed() {
        viewReservationPage.verifyAddEarlyBirdCheckInButtonDisplayed()
    }

    @Then("I see the breadcrumb 'change trip'")
    def verifyChangeTripBreadcrumb() {
        viewReservationPage.verifyChangeTripBreadcrumb()
    }

    @Then("I should not see the Air Associated Products Modal")
    def associatedProductsModalIsNotOnPage() {
        viewReservationPage.associatedProductsModalIsNotPresent()
        flow.isCarReservationPresent = false
        flow.hasHotel = false
    }

    @Then('I see an Oops message \$oopsMessage')
    void shouldShowOopsMessage(String oopsMessage) {
        viewReservationPage.shouldShowOopsMessage(oopsMessage)
    }

    @Then('I should not see the Cross Sell Sections')
    void verifyProductCrossSellSectionsNotDisplayed() {
        verifyAirProductCrossSellNotDisplayed()
        verifyCarProductCrossSellNotDisplayed()
        verifyHotelProductCrossSellNotDisplayed()
    }

    @Then("I see seat selection options for the AirTran flights on the view itinerary page")
    def verifySeatSelectionVerbiage() {
        viewReservationPage.verifySeatSelectionVerbiage()
    }

    @Then("I click the \$outboundInbound seat selection button on the view itinerary page")
    def clickSeatSelectionButton(String outboundInbound) {
        viewReservationPage.clickSeatSelectionButton(outboundInbound)
    }

    @Then("I see the seat selection unavailable message for the \$outBoundInBound AirTran flight on the view itinerary page")
    def verifySeatSelectionErrorVerbiage(String outBoundInBound) {
        viewReservationPage.verifySeatSelectionUnavailableVerbiage(outBoundInBound)
    }


    @Then("I see the default trip name which cannot be changed")
    void verifyDefaultTripNameAndRenameButton() {
        verifyDefaultTripName()
        verifyRenameButtonNotDisplayed()
    }

    @Then("I see expanded flight details for the air product with the Business Select icon and its tooltip")
    void verifyAirProductsDetailsAndBusinessSelectIcon() {
        verifyAirProductExpandedDetails()
        verifyBusinessSelectIconIsDisplayed()
    }

    @Then("I should not see the Cross Sell sections, the associated products and the Add EarlyBird Check-In button")
    void verifyCrossSellAssociatedProductsAndEBNotPresent() {
        verifyProductCrossSellSectionsNotDisplayed()
        verifyAssociatedItemsNotDisplayed()
        verifyAddEarlyBirdsCheckInButtonIsDisplayed()
    }

    @Then("I view the trip name \$tripName which can be changed")
    void verifyTripNameAndRenameButton(String tripName) {
        verifyTripName(tripName)
        viewReservationPage.verifyRenameButtonEnable()
    }

    @Then("I see the Air product details and the Car and Hotel Cross sell sections")
    void verifyFlightDetailsAndCarHotelCrossSellSections() {
        verifyAirProductExpandedDetails()
        viewReservationPage.verifyCarProductCrossSellDisplayed()
        verifyHotelProductCrossSellDisplayed()
    }

    @Then("I should not see the Air Cross Sell section and the associated products")
    void verifyAirCrossSellAndAssociatedProductsNotDisplayed() {
        verifyAirProductCrossSellNotDisplayed()
        verifyAssociatedItemsNotDisplayed()
    }

    @Then("I see the names of the passengers I have filled in")
    void verifyPassengersTitleAndPassengersNames() {
        viewReservationPage.verifyPassengerTitleDisplayed()
        viewReservationPage.verifyPassengersName()
    }

    @Then('I see the Early Bird Icon and the tool tip text')
    void verifyEarlyBirdIconAndToolTipDisplayed() {
        viewReservationPage.isEarlyBirdIconDisplayed().shouldBe true, "The Early Bird icon should be visible"
        viewReservationPage.isEarlyBirdToolTipDisplayed().shouldBe false, "The EarlyBird Check-In tool-tip text should be visible"
    }

    @Then('I see the A List Icon and the tool tip text')
    @Alias("I see an icon which informs that the passenger is an A-List Member")
    void verifyAListIconAndToolTipDisplayed() {
        viewReservationPage.isAListIconDisplayed().shouldBe true, "The A List icon should be visible"
        viewReservationPage.isAListToolTipDisplayed().shouldBe false, "The A List tool-tip text should be visible"
    }

    @Then('I see the itinerary dates formatted like mm/dd/yyyy - mm/dd/yyyy')
    void verifyItineraryDatesFormat(){
        viewReservationPage.isItineraryDatesCorrectlyFormatted().shouldBe true, "The itinerary dates should be formated like mm/dd/yyyy"
    }

    @Then('I see the cities of my open jaw flight')
    void verifyOpenJawCities(){
        viewReservationPage.isOpenJawCitiesCorrectlyDisplayed()
    }

    @Then('I see the Disambiguation Page')
    void verifyISeeDisambiguationPage() {
        viewReservationPage.verifyISeeDisambiguationPage()
        viewReservationPage.verifyDisambiguationTableColumns()
    }

    @Then("I see the Air products' Confirmation Numbers")
    void verifyAirReservations() {
        viewReservationPage.verifyAirReservations()
    }

    @Then ("I see the oops messages indicating that all mandatory field were not entered for the Credit Card")
    def verifyEmptyFieldsCreditCardOOPSMessage(){
        viewReservationPage.verifyEmptyCreditCardOOPSMessage()
    }

    @Then ("I see the oops messages indicating that all mandatory field were not entered retrieving a reservation by confirmation number")
    def verifyEmptyFieldsPNROOPSMessage(){
        viewReservationPage.verifyEmptyPNROOPSMessage()
    }

    @Then('I see the valid characters and I should not see the invalid characters entered as part of the new name')
    void verifyTripNameWithValidAndInvalidCharacters() {
        verifyTripName('My Trip')
    }

    @Then("I view an Oops message which indicates that my reservation has been cancelled")
    def verifyReservationHasBeenCancelledOopsMessage() {
        viewReservationPage.verifyReservationHasBeenCancelledOopsMessage()
    }

    @Then ("I see an icon which informs that the passenger is an Unaccompanied Minor on the View Reservation Page")
    def verifyUnaccompainedMinorIconIsDisplayed(){
        viewReservationPage.verifyUnaccompainedMinorIconIsDisplayed()
    }

    @Then("I see an informative text which indicates that the passenger is an Unaccompanied Minor on the View Reservation Page")
    def verifyUnaccompainedMinorIconTooltip(){
        viewReservationPage.verifyUnaccompainedMinorIconTooltip()
    }

    @Then("I see a thank you popup")
    def confirmationPopup(){
        viewReservationPage.verifyConfirmationPopup()
    }

	@Then('I see an icon which informs that the air product has been purchased on Business Select on the View Reservation Page')
	def verifyBusinessSelectIconIsDisplayed() {
		viewReservationPage.isBusinessSelectIconDisplayed().shouldBe true, "The Business Select icon should be visible"
		viewReservationPage.isBusinessSelectToolTipDisplayed().shouldBe false, "The Business Select tool-tip text should be visible"
	}

	@Then('I should not be able to add the Early Bird Check-in to my Air product')
	def verifyAddEarlyBirdsCheckInButtonIsDisplayed() {
		viewReservationPage.isAddEarlyBirdCheckInButtonDisplayed().shouldBe false, "The Add EarlyBird Check-In button should be not visible"
	}

    @Then('I see an error message for each of the products in the trip specifying that the product could not be retrieved')
    def verifyAssociatedProductsCouldNotRetrieved() {
        viewReservationPage.verifyAssociatedProductsCouldNotRetrieved()
    }

    @Then('I see my air product on the Retrieve Itinerary page')
    def verifyAirProductIsDispleyed() {
        verifyAirProductExpandedDetails()
    }

    @Then("I view the message for \$msg")
    def pageMessage(String msg) {
        viewReservationPage.verifyItineraryMessage(msg)
    }

    @Then("I see the My Id Travel Oops message on view itinerary page")
    def shouldShowMyIdTravelOopsMessage() {
        viewReservationPage.shouldShowOopsMessage(OOPS_MYIDTRAVEL_MESSAGE)
    }

    @Then("I see that the Change option is not shown")
    def shouldNotShowChangeOption(){
        viewReservationPage.shouldNotShowChangeOption()
    }

    @Then("I see that the Resend Receipt option is not shown")
    def shouldNotShowResendReceiptOption(){
        viewReservationPage.shouldNotShowResendReceiptOption()
    }

    @Then("I see that the Depart/Return data is not shown")
    def shouldNotShowDepartReturnData(){
        viewReservationPage.shouldNotShowDepartReturnData()
    }

    @Then("I see that the Fare Product data is not shown")
    def shouldNotShowFareProductData(){
        viewReservationPage.shouldNotShowFareProductData()
    }

    @Then("I see that the Early Bird option is not shown")
    def shouldNotShowEarlyBirdOption(){
        viewReservationPage.shouldNotShowEarlyBirdOption()
    }

    @Then("I see that the Check In option is not shown")
    def shouldNotShowCheckinOption(){
        viewReservationPage.shouldNotShowCheckinOption()
    }

    @Then("I see that the Check In option is shown")
    def shouldShowCheckinOption(){
        viewReservationPage.shouldShowCheckinOption()
    }

    @Then( "I see the seat selection button on the itinerary page" )
    def iSeeTheSeatSelectionButtonOnTheItineraryPage() {
        viewReservationPage.verifySeatSelectionButton()
    }

    @Then( "I should not see the seat selection button on the itinerary page" )
    def iShouldNotSeeTheSeatSelectionButtonOnTheItineraryPage() {
        viewReservationPage.verifySeatSelectionButtonNotShown()
    }

    @Then("I see that the Hotel Dynamic Cross-Sell shown displaying results for my destination city and dates of travel")
    def shouldSeeHotelDynamicCrossSell() {
        viewReservationPage.shouldSeeHotelDynamicCrossSell()
    }

    @Then("I should not see the Hotel Dynamic Cross-Sell")
    def shouldNotSeeHotelDynamicCrossSell() {
        viewReservationPage.shouldNotSeeHotelDynamicCrossSell()
    }

    @Then("I should see an error message for the air product in the trip specifying that the product could not be retrieved")
    def verifyAirAssociatedProductCouldNotRetrieved() {
        viewReservationPage.verifyAirAssociatedProductCouldNotRetrieved()
    }
}
