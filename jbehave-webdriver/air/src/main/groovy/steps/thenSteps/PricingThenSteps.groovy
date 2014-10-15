package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Aliases
import org.jbehave.core.annotations.Then
import pages.ConfirmationPage
import pages.Itinerary
import pages.PricePage
import pages.elements.DotRequiredLinks
import pages.elements.FareBreakdownFlyOut
import pages.elements.PriceTable
import pages.elements.ShoppingCart
import state.Flow
import util.ItineraryData
import state.CarReservationData

class PricingThenSteps {

    CarReservationData carReservationData
    PricePage pricePage
    ShoppingCart shoppingCart
    PriceTable priceTable
    DotRequiredLinks dotRequiredLinks
    ItineraryData itineraryData
    Itinerary itinerary
    Flow flow
    ConfirmationPage confirmationPage

    @Then("I should see the correct pricing page")
    def verifyItineraryPage() {
        pricePage.verifyBasicPage()
        pricePage.verifyCarrierInformation()
    }

    @Then("I verify the blue informational message for not enough points")
    def verifyNotEnoughPointsInformationalMessage() {
        pricePage.verifyNotEnoughPointsInformationalMessage()
    }

    @Then("I should see that I am redirected to the pricing page")
    @Alias("I should see the correct pricing page without logo verify")
    def verifyRedirectToPricingPage() {
        pricePage.verifyBasicPage()
    }

    @Then("I should see the same departure date and time in price page that I selected in Select flights page")
    def verifyDepartureDateAndTime(){
        pricePage.verifyDepartureFlightDateAndTimeEquality()
    }
     @Then("I should see the same arrival date and time in price page that I selected in Select flights page")
    def verifyArrivalDateAndTime(){
        pricePage.verifyArrivalFlightDateAndTimeEquality()
    }

    @Then("I see no AirTran Indicator")
    @Alias("I see no AirTran Indicator in the shopping cart")
    def verifyShoppingCartCollapsed() {
        shoppingCart.verifyShoppingCartCollapsed()
    }

    @Then("I see AirTran indicators in the shopping cart")
    def verifyExpandedShoppingAirTranIndicator() {
        shoppingCart.verifyExpandedShoppingAirTranIndicator()
    }

    @Then("I should see the hotel upsell widget")
    def verifyHotelUpsellWidgetVisible() {
        pricePage.isShowingHotelUpsell()
    }

    @Then("I should see the car upsell widget")
    def verifyCarUpsellWidgetVisible() {
        pricePage.isShowingCarUpsell()
    }

    @Then("I should not see the car cross sell widget")
    def isCarCrossSellWidgetHidden() {
        pricePage.carCrossSellWidgetShouldBeHidden()
    }

    @Then("I should see the car cross sell widget")
    def isCarCrossSellWidgetDisplayed() {
        pricePage.carCrossSellWidgetIsDisplayed()
    }

    @Then("I see \$airline \$route flight in the shopping cart")
    def verifyAirlineInShoppingCart(String airline, String route) {
        shoppingCart.verifyAirlineInShoppingCart(airline, route)
    }

    @Then("I see Business Select in the shopping cart")
    void verifyBusinessSelectIsInShoppingCart() {
        shoppingCart.verifyBusinessSelectIsInShoppingCart()
    }

    @Then("I view my price breakdown in points")
    def verifyTotalInPointsOnPricePage() {
        pricePage.verifyGrandTotalInPoints()
    }

    @Then("I see the confirm remove modal")
    void verifyModalRemoveModal() {
        shoppingCart.verifyModalRemoveModal()
    }

    @Then("I should see that the price page air section header has the total price label")
    def newAirSectionHeaderTotalPriceLabel() {
        pricePage.verifyAirSectionHeaderTotalPriceLabel()
    }

    @Then("I should see that the price page air section header has the modify price label")
    def oldAirSectionHeaderModifyLabel() {
        pricePage.verifyAirSectionHeaderModifyLabel()
    }

    @Then("I should see that the price page itinerary has the day of the week and date calendar icon")
    def newCalendarIconHasMonthDateAndDayOfWeek() {
        pricePage.verifyMonthDateAndDayOfWeekOnCalendarIcon()
    }

    @Then("I should see that the price page itinerary has the month and date calendar icon")
    def oldCalendarIconHasMonthAndDate() {
        pricePage.verifyMonthAndDateOnCalendarIcon()
    }

    @Then("I should see that the price page itinerary has the two digit hour time format")
    def newTwoDigitHourTimeFormat() {
        pricePage.verifyTwoDigitHourTimeFormat()
    }

    @Then("I should see that the price page itinerary has the one digit hour time format")
    def oldOneDigitHourTimeFormat() {
        pricePage.verifyOneDigitHourTimeFormat()
    }

    @Then("the price table should have the old column headers")
    def verifyPriceTableColumnsPriceToggle_oldLayout() {
        priceTable.oldColumnsHeaders()
    }

    @Then("the price table should have the revised column headers")
    def verifyPriceTableColumnsPriceToggle_newLayout() {
        priceTable.revisedColumnHeaders()
    }

    @Then("I should see fare type descriptions in the price rows")
    def verifyFareTypeDescriptionsPriceToggle_shouldBeThere() {
        priceTable.anyPriceTableRowContainsFareTypeDescription().shouldBe true
    }

    @Then("I should not see fare type descriptions in the price rows")
    def verifyFareTypeDescriptionsPriceToggle_shouldNotBe() {
        priceTable.anyPriceTableRowContainsFareTypeDescription().shouldBe false
    }

    @Then("I should see that the price table \$location has a link that opens the fare rules in a new window")
    def verifyRulesLink(String location) {
        if (location == "fare type column header") {
            priceTable.headerFareTypeLinkPresent().shouldBe true
        } else {
            priceTable.footerFareTypeLinkPresent().shouldBe true
        }
    }

    @Then("I should not see the fare rules link in the price table \$location")
    def verifyRulesLinkNotPresent(String location) {
        if (location == "fare type column header") {
            priceTable.headerFareTypeLinkPresent().shouldBe false
        } else {
            priceTable.footerFareTypeLinkPresent().shouldBe false
        }
    }

    @Then("the fare types should have matching descriptions")
    def verifyFareTypesHaveMatchingDescriptions() {
        priceTable.eachRowContainsMatchingFareTypeDescription()
    }

    @Then("individual fare type links in the fare type column open the fare rules in a new window")
    def verifyPriceRowFareTypeLink() {
        priceTable.fareTypeLinkPresentWithTargetInEachPriceRow().shouldBe true
    }

    @Then("I should see a link in the price table that opens the points calculation in a popup")
    def verifyPriceTablePointsCalculationLink() {
        dotRequiredLinks.isAnyPointsCalculationLinkDisplayed().shouldBe true
    }

    @Then("I should see a link that opens the checked baggage policy in a new window")
    def checkedBagsPolicyWithTarget() {
        dotRequiredLinks.isAnyCheckedBagsLinkPresentWithTarget().shouldBe true
    }

    @Then("I should see the modal telling me my flight is saved")
    def verifyFlightSavedMessage() {
        pricePage.verifyTripSavedMessage()
    }

    @Then("I should see the login modal popup")
    def verifyLoginModal() {
        pricePage.verifyLoginModal()
    }

    @Then("I verify that the points.com popup is displayed")
    def verifyPointsDotcomPopupIsDisplayed() {
        pricePage.verifyPointsDotcomPopupIsDisplayed()
    }

    @Then("I verify that the correct params are set for opening popup")
    def verifyCorrectParamsForPointsDotcomPopup() {
        pricePage.verifyCorrectParamsForPointsDotcomPopup()
    }

    @Then("I see the Fare Breakdown flyOut")
    void verifyTotalPriceFlyOutOnPricePageAndStoreCharges() {
        FareBreakdownFlyOut flyout = pricePage.openFareBreakDownFlyOut()
        flyout.getTitle().shouldBe "Fare Breakdown", "The flyout title should be 'Fare Breakdown'"
        itineraryData.totalBaseFare = flyout.baseFare
        itineraryData.totalTaxes = flyout.taxes
        itineraryData.totalCharge = flyout.total
        itineraryData.segmentFee = flyout.segmentFee
        itineraryData.passengerFacilityFee = flyout.passengerFacilityFee
        itineraryData.securityFee = flyout.securityFee
        itineraryData.totalPerPassenger = flyout.totalPerPassenger
        itineraryData.passengerCount = flyout.passengerCount
        itineraryData.dollarTotal = flyout.dollarTotal()
        flyout.closeFlyout()
    }

    @Then("I see the Fare Breakdown flyout has a fare discount greater than \$amount")
    def verifyFareDiscountExists(String amount) {
        FareBreakdownFlyOut flyout = pricePage.openFareBreakDownFlyOutById()
        flyout.fareDiscount.shouldBeGreaterThan amount.toBigDecimal(), "Discount not greater than specified amount"
        flyout.closeFlyout()
    }

    @Then("I should see the price page")
    void verifyDingDingPrice(){
        pricePage.verifyBasicPage();
    }

    @Then("I should be redirected to the car price page")
    void verifyCarPagePrice(){
        flow.hasAir = false
        flow.isFaultInjected = false
        pricePage.verifyBasicPage()

    }

    @Then("I see the Southwest-only specific travel guidelines")
    def verifySouthwestOnlyWCMContent() {
        itinerary.verifyTravelGuidelineWCMContent("pricing_note_only_wn")
    }

    @Then("I see the SouthwestCodeshare-mixed specific travel guidelines")
    @Aliases(values = ["I see the Southwest-mixed specific travel guidelines",
    "I see the AirTran-mixed specific travel guidelines"])
    def verifySouthwestCodeshareMixedWCMContent() {
        itinerary.verifyTravelGuidelineWCMContent("pricing_note_mixed_other")
    }

    @Then("I see the AirTran-only specific travel guidelines")
    def verifyAirTranOnlyWCMContent() {
        itinerary.verifyTravelGuidelineWCMContent("pricing_note_only_fl")
    }

    @Then("I see the AirTranCodeshare-mixed specific travel guidelines")
    def verifyAirTranCodeshareMixedWCMContent() {
        itinerary.verifyTravelGuidelineWCMContent("pricing_note_mixed_flwn")
    }

    @Then("I see the price page")
    void verifyIamOnThePricePage(){
        pricePage.verifyCurrentPageLocation()
    }

    @Then("I should not see Swabiz company information displayed on price page of .com")
    void shouldNotSeeSwabizCompanyInformationOnDotcom (){
        pricePage.verifySwabizCompanyNameIsNotDisplayed()
    }

    @Then("I should see Swabiz company information displayed on price page of swabiz")
    void shouldSeeSwabizCompanyInformationOnSwabiz (){
        pricePage.verifySwabizCompanyNameIsDisplayed()
    }

    @Then("I verify the Continue button is disabled")
    void verifyDisabledContinueButton() {
        pricePage.verifyDisabledContinueButton()
    }

    @Then("I should see an Oops saying that is not possible save flight at the moment")
    void shouldSeeOopsIsNotPossibleSaveFlightAtTheMoment() {
         pricePage.verifyOopsNotSaveFlightAtTheMoment()
    }

    @Then("I see the static car cross-sell ad")
    void shouldSeeStaticCarCrossSellAd(){
        pricePage.verifyStaticCarCrossSell()
    }

    @Then("I see Hertz vendor on the car cross-sell ad")
    void shouldSeeDynamicCarCrossSell(){
        // we look for Hertz since it's the vendor set for tests on localCaches/upsellSearchVendors.properties
        pricePage.verifyHertzCarCrossSell()
    }

    @Then("I should see a message saying that I will be charged upon car rental return")
    void shouldSeeVerbiageCarVendor() {
        shoppingCart.verifyShoppingCartCarVendorMessage()
    }
    @Then("I should see a message saying that I will be charged upon car rental pickup")
    void shouldSeeVerbiageFoxVendor() {
        shoppingCart.verifyShoppingCartFoxVendorMessage()
    }

    @Then("I see a fare discount on price flyout equal to \$amount")
    def verifyFareDiscountEqual(String amount) {
        FareBreakdownFlyOut flyout = pricePage.openFareBreakDownFlyOut()
        flyout.fareDiscount.shouldBe amount.toBigDecimal(), "Discount not equal to specified amount"
        flyout.closeFlyout()
    }

    @Then("I see a discounted points fare on the fare breakdown flyout")
    def verifyPointsFareDiscountEqual() {
        FareBreakdownFlyOut flyout = priceTable.openFareBreakDownFlyOut()
        int calculatedFareDiscount = flyout.pointsBaseFare - flyout.pointsAdjustedFare
        flyout.pointsFareDiscount.shouldBe calculatedFareDiscount, "Discount not equal to specified amount"
        flyout.closeFlyout()
    }
}