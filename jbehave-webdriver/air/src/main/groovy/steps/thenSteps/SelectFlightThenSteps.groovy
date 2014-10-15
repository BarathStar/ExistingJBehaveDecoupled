package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import pages.SelectFlightsPage

class SelectFlightThenSteps {

    static final String SAN_JUAN_RESTRICTION_MESSAGE = "The flight combination that you have selected cannot be booked. Please try a different combination in order to complete your booking."

    SelectFlightsPage selectFlightsPage

    @Then("I view the flight detail fly out close")
    def verifyFlyOutClose() {
        selectFlightsPage.verifyFlightDetailFlyOutClosed()
    }

    @Then("I view the flight detail fly out")
    def verifyRoutingPopsUp() {
        selectFlightsPage.verifyRoutingPopsUp()
    }

    @Then("I view the airline logo")
    def verifySWALogo() {
        selectFlightsPage.verifyLogoInSelectFlightFlyout()
    }

    @Then("I view all the fares in points")
    def viewFaresInPoints() {
        selectFlightsPage.verifyNoFareInDollars()
    }

    @Then("I see the Certificate radio button selected to redeem a promotion")
    void viewCertificateRadioSelected() {
        selectFlightsPage.verifyCertificateRadioButtonIsSelected()
    }

    @Then("I see the Standard and Freedom Awards Columns in the Select Flights columns")
    void viewAwardsColumns() {
        selectFlightsPage.verifyStandardAndFreedomAwards()
    }

    @Then("I should see that the outbound routing column is sorted in descending order")
    def verifyOutboundRoutingDescendingOrder() {
        selectFlightsPage.isOutboundRoutingOrderDescending().shouldBe true
    }

    @Then("I should see the change planes icon in the routing column")
    def outboundRoutingHasChangePlanesIcon() {
        selectFlightsPage.changePlanesHasIcon().shouldBe true
    }

    @Then("I should see the change planes text in the routing column")
    def outboundRoutingHasChangePlanesText() {
        selectFlightsPage.changePlanesHasText().shouldBe true
    }

    @Then("I should see a link that opens the full fare disclosure in a new window")
    def verifyWhatYouSeeIsWhatYouPayLink(){
        selectFlightsPage.isAnyWhatYouSeeIsWhatYouPayLinkPresentWithTarget().shouldBe true
    }

    @Then("I should see that the outbound upsell drawer is closed")
    def upgradeDrawerHidden() {
        selectFlightsPage.outboundUpsellDrawerHidden().shouldBe true
    }

    @Then("I should see that the outbound upsell drawer is opened")
    def outboundUpsellDrawerOpened() {
        selectFlightsPage.outboundUpsellDrawerDisplayed().shouldBe true
    }

    @Then("I should see a text link for No thanks, keep Anytime")
    def verifyCloseUpsellButtonLinkText() {
        selectFlightsPage.closeUpsellLinkTextDisplayed().shouldBe true
    }

    @Then("I should see the welcome drawer for Business Select")
    def outboundWelcomeDrawerDisplayed() {
        selectFlightsPage.outboundWelcomeToBusinessSelectDrawerDisplayed().shouldBe true
    }

    @Then("I should see the return flights button displayed in the welcome drawer")
    def outboundWelcomeDrawerSelectFlightsButtonDisplayed() {
        selectFlightsPage.welcomeDrawerReturnFlightButtonDisplayed().shouldBe true
    }

    @Then("the outbound fare type headers should have matching descriptions")
    def verifyFareTypesHaveMatchingDescriptions() {
        selectFlightsPage.eachFareTypeHeaderContainsMatchingFareTypeDescription().shouldBe true
    }

    @Then("I see the Oops messages indicating that the return date was not entered on the new flight search form")
    void verifyOopsMandatoryFieldsOnNewFlightSearch() {
        selectFlightsPage.verifyOopsReturnDate()
    }

    @Then("I should see the southwest select flight page")
    def viewSouthWestSelectFlightsPage() {
        selectFlightsPage.isDisplayed()
    }

    @Then("I verify that BS fares are highlighted")
    void verifyBSColumnHighlighted() {
        selectFlightsPage.isBSFareColumnHighlighted().shouldBe true, "The BS fare column should be highlighted."
    }

    @Then("I verify that BS fares are not highlighted")
    void verifyBSColumnNotHighlighted() {
        selectFlightsPage.isBSFareColumnHighlighted().shouldBe false, "The BS fare column should not be highlighted."
    }

    @Then("I verify ACS drawer is not being displayed")
    def verifyACSDrawerNotDisplayed() {
        selectFlightsPage.isACSDrawerDisplayed().shouldBe false, "The ACS drawer should not be shown when booking with points."
    }

    @Then("I see the Aggressive Business Select Upsell Drawer")
    def verifyACSDrawerDisplayed() {
        selectFlightsPage.isACSDrawerDisplayed().shouldBe true, "The ACS drawer should be shown when booking with dollars and is not."
    }

    @Then("I verify that departure airport has FlyBy availability")
    void verifyFlyByPresentBestValueBox() {
        selectFlightsPage.isFlyByBestValueBoxPresent()
    }

    @Then("I verify that the upsell modal is being displayed")
    def verifyUpsellModalDisplayed() {
        selectFlightsPage.isUpsellModalDisplayed().shouldBe true, "The upsell modal is not being shown."
    }

    @Then("I see that the upsell modal displays only the outbound flight as not eligible for upgrade to BS")
    def verifyUpsellModalNoApplyBusinessSelect(){
        verifyUpsellModalDisplayed()
        selectFlightsPage.verifyNoApplySectionOnUpsellModalToOutBound()
    }

    @Then("I see that the upsell modal displays only the inbound flight as not eligible for upgrade to BS")
    def verifyUpsellModalNoApplyBusinessSelectToWGA(){
        verifyUpsellModalDisplayed()
        selectFlightsPage.verifyNoApplySectionOnUpsellModalToInBound()
    }

    @Then("I verify that the upsell modal is not being displayed")
    def verifyUpsellModalNotDisplayed() {
        selectFlightsPage.isUpsellModalDisplayed().shouldBe false, "The upsell modal is being shown."
    }

    @Then("I verify that both of my segments are eligible for upsell")
    def verifyBothSegmentsUpsellModal() {
        selectFlightsPage.verifyBothSegmentsUpsellModal()
    }

    @Then("I verify that the book together button is not being displayed")
    @Alias("I verify that the SWA Vacations cross-sell on select flights page is not being displayed")
    def verifyBookTogetherButtonNotDisplayed() {
        selectFlightsPage.isBookTogetherButtonNotDisplayed()
    }

    @Then("I verify that the book together button is being displayed")
    def verifyBookTogetherButtonDisplayed() {
        selectFlightsPage.isBookTogetherButtonDisplayed()
    }

    @Then("I see the flight information in the single fly-out")
    def verifySingleFlyOutInfo() {
        selectFlightsPage.verifyFlightDetailsPopup()
        selectFlightsPage.verifySingleFlyoutInformation()
    }

    @Then("I see the AirTran flight information in the single fly-out")
    def verifyAirTranSingleFlyOutInfo() {
        selectFlightsPage.verifyAirTranSingleFlyoutInformation()
    }

    @Then("I see the flight information in the connecting fly-out")
    def verifyConnectingFlyOutInfo() {
        selectFlightsPage.verifyFlightDetailsPopup()
        selectFlightsPage.verifyConnectingFlyoutInformation()
    }

    @Then("I should see the searched flights on the select flights page")
    def verifyFlights() {
        selectFlightsPage.verifyPage()
        selectFlightsPage.verifyFlightsPresent()
    }

    @Then("I view full fare details")
    void viewFullFareDetails() {
        selectFlightsPage.displayOutboundFareDetails()
    }

    @Then("I should see Flight Details popup")
    def verifyFlightDetailsPopup() {
        selectFlightsPage.verifyFlightDetailsPopup()
    }

    @Then("I should see Fare Details popup")
    def verifyFareDetailsPopup() {
        selectFlightsPage.verifyFareDetailsPopup()
    }

    @Then("I should see the Ding column")
    void verifyDingColumnIsVisible(){
        selectFlightsPage.verifyDingColumnIsPresent();
    }

    @Then("there should be ding fares available")
    void verifyDingFaresAreAvailable(){
        selectFlightsPage.selectFlights();
    }

    @Then("I see fares in dollars as default option on the Select Flights page")
    def verifyFaresInDollarsAsDefaultOption() {
        selectFlightsPage.verifyDollarsOptionCheckedByDefault()
    }

    @Then("I see selected the fares in points option on the Select Flights page")
    def verifyFaresInPointsSelected() {
        selectFlightsPage.verifyFarePointsChecked()
    }

    @Then("I see the flight information in the single fly-out with the aircraft description section suppressed")
    def verifyAircraftInformationSuppressed() {
        selectFlightsPage.verifyIfAircraftInformationSuppressed()
    }

    @Then("I should see a Oops message indicating that no flight has been selected")
    def verifyThatFlightsWereSelected(){
        selectFlightsPage.verifyOopsInboundOutboundShouldBeSelected()
    }

    @Then("I see the oops message indicating the San Juan restriction")
    void verifySanJuanRestrictionOopsMessage() {
        List<String> restrictionSJUMessages = new ArrayList<String>()
        restrictionSJUMessages.add("In order to successfully book the San Juan flight combination, please ensure the following:")
        restrictionSJUMessages.add("The connection time between flights to and from San Juan is 4 hours or less.")
        restrictionSJUMessages.add("You are not attempting to connect through San Juan when traveling between two U.S. domestic cities.")
        selectFlightsPage.verifyOopsMessages(restrictionSJUMessages)
    }

    @Then("I see Business Select one-way Fare containing Southwest Airtran segments")
    void verifyBusinessSelectFareAvailableForONDWithWNFLSegments() {
        selectFlightsPage.verifyBusinessSelectFareForOutboundWithWNFLSegments()
    }

    @Then("Verify that I am on the Select Flights page")
    void shouldBeOnTheSelectFlightspage() {
        selectFlightsPage.verifyCurrentPageLocation()
    }

    @Then("I see that the upsell modal Ding displays only the inbound flight as not eligible for upgrade to BS")
    void verifyUpsellModalDisplaysNotEligibleUpgradeToBS() {
        verifyUpsellModalDisplayed()
        selectFlightsPage.verifyNoApplySectionOnUpsellModalToInBound()
    }

    @Then("I see the Select flights page with flights from \$origin to \$destination")
    void verifySelectFlightPageWithOriginAndDestination(String origin, String destination) {
		selectFlightsPage.checkSomethingServed()
        selectFlightsPage.verifyOriginAirport(origin)
        selectFlightsPage.verifyDestinationAirport(destination)
    }

    @Then("I do not see an oops message on the Select Flights Page")
    def doNotSeeOopsMessageOnSelectFlightsPage() {
        selectFlightsPage.checkNoOops()
    }

    @Then("I see strike through for original redemption points")
    def verifyStrikeThroughOnOriginalPoints(){
        selectFlightsPage.isStrikeThroughRedemptionPointsDisplayed()
    }
    
    @Then("I check that flight information of my selected flights is included on the SWA Vacations Form with fare type \$fareType")
    void verifyLinkSwaVacationButton(String fareType) {
        selectFlightsPage.verifyLinkSwaVacationButton(fareType)
    }

    @Then("I should see blue informational alert message")
    def verifyBlueInformationalAlertMessage(){
        selectFlightsPage.isBlueInformationalAlertDisplayed().shouldBe true
    }

    @Then("I should see dollar-points toggle")
    def verifyDollarPointsFareTypeToggle(){
        selectFlightsPage.verifyDollarPointToggleOn()
        selectFlightsPage.verifyCertificateToggleOff()
    }
}
