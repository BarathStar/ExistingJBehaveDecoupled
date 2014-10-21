package steps

import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named

import pages.AirTranRedirectModal
import pages.AirTranSelectFlightsPage
import pages.SearchFlightsPage
import static util.Locators.ELEMENT_IDS
import pages.SelectFlightsPage
import pages.elements.AutoCompleteWidget
import pages.elements.StaticBookingWidget
import org.openqa.selenium.By

/**
 * Contains the steps for navigating through any stories that deal with the Customized Booking Widget
 */
class StaticBookingWidgetSteps {

    AirTranRedirectModal airTranRedirectModal
    AirTranSelectFlightsPage airTranSelectFlightsPage
    AutoCompleteWidget autoCompleteWidget
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    StaticBookingWidget staticBookingWidget

    static final String FROM_WATERMARK = "Departure City or Airport Code"
    static final String TO_WATERMARK = "Arrival City or Airport Code"
    private static final String ARRIVAL_DROPDOWN = "destinationAirport"
    private static final String DEPARTURE_DROPDOWN = "originAirport"
    private static final String PROMO_VALUE = "REDDY"
    private static final By PROMO_CODE = By.name("promoCode")
    private static final By OUTBOUND_DATE = By.name("outboundDateString")
    private static final By RETURN_DATE = By.name("returnDateString")

    @When("I search a flight with given url \$url")
    void navigateToBookingWidget(String url) {
        staticBookingWidget.openBookingWidget(url)
    }

    /**
     * Enters in a valid return and departure date and then clicks the search button
     * @return
     */
    @When("I enter valid dates and click on the search button")
    def enterValidDates(){
        completeWidgetWithValidOutboundDates()
        completeWidgetWithValidReturnDates()
        staticBookingWidget.clickOnSearchButtonAndValidatePage()
    }

    /**
     * Enters in a valid departure date
     * @return
     */
    @When("I enter valid outbound date")
    def enterDateField() {
        completeWidgetWithValidOutboundDates()
    }

    @When("I enter valid dates and click on the search button and wait for modal")
    def enterValidDatesWebReferral(){
        completeWidgetWithValidOutboundDates()
        completeWidgetWithValidReturnDates()
        staticBookingWidget.clickOnSearchButton()
    }


    @When("I submit the static widget page")
    def submitStaticWidget(){
       //this submit is expecting a page with different page title
        String currentPageTitle = staticBookingWidget.getTitle()
        staticBookingWidget.clickOnSearchButton()
        staticBookingWidget.waitForPageTitleToChangeOrOops(currentPageTitle)
    }

    /**
     * Handles clicking the search button as specified in the story
     * @return
     */
    @When("I click on search.")
    def clickOnSearch() {
        staticBookingWidget.clickOnSearchButton()
    }

    private completeWidgetWithValidOutboundDates() {
        Calendar today = Calendar.getInstance()
        today.add(Calendar.DATE, 1)
        staticBookingWidget.fillBookingOutboundDate(today.getTime())
    }

    private completeWidgetWithValidReturnDates() {
        Calendar today = Calendar.getInstance()
        today.add(Calendar.DATE, 2)
        staticBookingWidget.fillBookingReturnDate(today.getTime())
    }

    @Then("I am redirected to the AirTran webpage after seeing a info modal")
    def viewAirTranRedirectModalWithWcmContent() {
        airTranRedirectModal.viewAirTranRedirectModalWithWcmContent()
        clickContinueOnAirTranRedirectModal()
        viewAirTranSearchFlightsPage()
    }

    private clickContinueOnAirTranRedirectModal() {
        airTranRedirectModal.clickContinueButton()
    }

    private viewAirTranSearchFlightsPage() {
        airTranSelectFlightsPage.isDisplayed()
    }

    @Then("Verify that I am on the Select Flights page and \$origin and \$destination are in \$field1 and \$field2 fields")
    def verifySelectFlightsPageAndValues(@Named("origin") String origin, @Named("destination") String destination,@Named("field1") String field1, @Named("field2") String field2) {
        staticBookingWidget.verifyInField(ELEMENT_IDS.get(field1), origin)
        staticBookingWidget.verifyInField(ELEMENT_IDS.get(field2), destination)
    }

    @Given("I see watermarks in the \$field1 and \$field2 fields")
    def watermarksInFields(@Named("field1") String field1, @Named("field2") String field2){
        verifyStationInField(field1, FROM_WATERMARK)
        verifyStationInField(field2, TO_WATERMARK)
    }

    private verifyStationInField(field, watermark) {
        searchFlightsPage.verifyInField(ELEMENT_IDS.get(field), watermark)
    }

    @When ("I select \$origin and \$destination in the \$field1 and \$field2 fields and verify")
    def enterValidCityPairAndVerify(@Named("origin") String origin, @Named("destination") String destination,@Named("field1") String field1, @Named("field2") String field2){
        autoCompleteWidget.fillInAndSelectAutoCompleteData(ELEMENT_IDS.get(field1), origin)
        autoCompleteWidget.fillInAndSelectAutoCompleteData(ELEMENT_IDS.get(field2), destination)
        staticBookingWidget.verifyInField(ELEMENT_IDS.get(field1), origin)
        staticBookingWidget.verifyInField(ELEMENT_IDS.get(field2), destination)
    }

    @When ("I select \$city in the \$field field in the static booking widget")
    def enterValidCity(@Named("city") String city, @Named("field") String field){
        autoCompleteWidget.fillInAndSelectAutoCompleteData(ELEMENT_IDS.get(field), city)
    }

    @Given ("I verify the radio button and the outbound date is disabled and pre-populated")
    def verifyRadioAndOutboundDate(){
        checkOneWayTripTypeSelected()
        returnDateDisabled()
        checkForWatermarkOnOutboundDate()
    }

    private checkOneWayTripTypeSelected() {
        staticBookingWidget.verifyOneWayRadioButtonIsChecked()
    }

    /**
     * Sets the trip type to round trip by clicking the Round Trip radio button
     */
    @Then("The trip type is round trip")
    void checkRoundTrip() {
        //TODO add assertions
    }


    private returnDateDisabled() {
        staticBookingWidget.verifyReturnDateIsDisabled()
    }

    private checkForWatermarkOnOutboundDate(){
        staticBookingWidget.verifyWatermarkOnOutboundDate()
    }

    @Then("I enter valid dates and click on the search button")
    @When("I select the travel date and I click on search.")
    def thenEnterValidDates(){
        completeWidgetWithValidOutboundDates()
        completeWidgetWithValidReturnDates()
        staticBookingWidget.clickOnSearchButtonAndValidatePage()
    }

    /**
     * Sets the departure city to a defined departure city as found in the story
     * @param inputData the departure city defined in the story file
     * @return
     */
    @When("The departure city is \$input")
    def enterDepartureCityInField(@Named("input") String inputData) {
        staticBookingWidget.chooseInDropDownByValue(DEPARTURE_DROPDOWN, inputData)
    }

    /**
     * Sets the arrival city to a defined arrival city as found in the story
     * @param inputData the arrival city defined in the story file
     * @return
     */
    @When("The arrival city is \$input")
    def enterArrivalCityInDropdown(@Named("input") String inputData) {
        staticBookingWidget.chooseInDropDownByValue(ARRIVAL_DROPDOWN, inputData)
    }

    @When("I select \$input in the custom arrival dropdown")
    def enterArrivalCityInField(@Named("input") String inputData) {
        staticBookingWidget.chooseInDropDownByValue(ARRIVAL_DROPDOWN, inputData)
    }

    @Then("I see that the promo code is pre-populated")
    def verifyPromoCodeValue() {
        verifyPromoCode()
    }

    @Then("I see that the promo code is disabled")
    def verifyPromoCodeFieldIsDisabled() {
        verifyPromoCodeDisabled()
    }

    @Then ("I verify the date fields do not contain a watermark")
    def checkForWatermarkOnDates(){
        verifyWatermarkOnDates()
    }

    @Then ("I verify the date fields are not empty")
    def checkDatesNotEmpty(){
        verifyDatesAreNotEmpty()
    }

    @When("I complete static widget with valid outbound date and click search")
    def enterValidOutboundDate(){
        completeWidgetWithValidOutboundDates()
        Calendar today = Calendar.getInstance()
        staticBookingWidget.fillBookingOutboundDate(today.getTime())
        staticBookingWidget.clickOnSearchButtonAndValidatePage()
    }

    private verifyPromoCode() {
        String promoValue = staticBookingWidget.findElement(PROMO_CODE).getAttribute("value")
        promoValue.equals(PROMO_VALUE).shouldBe true, "The value on Promo Code is not correct"
    }

    private verifyPromoCodeDisabled() {
        staticBookingWidget.waitForElement(PROMO_CODE).getAttribute("disabled").shouldBe "true", "The promo code field was not disabled"
    }

    private verifyWatermarkOnDates() {
        staticBookingWidget.waitForElement(OUTBOUND_DATE).getAttribute("value").shouldNotContain "Depart", "The outbound date field should not show a watermark"
        staticBookingWidget.waitForElement(RETURN_DATE).getAttribute("value").shouldNotContain "Return", "The return date field should not show a watermark"
    }

    private verifyDatesAreNotEmpty(){
        boolean dateEmpty
        dateEmpty = staticBookingWidget.waitForElement(OUTBOUND_DATE).getAttribute("value").isEmpty()
        dateEmpty = staticBookingWidget.waitForElement(RETURN_DATE).getAttribute("value").isEmpty()
    }

    /**
     * Verifies that the number of adults is correct
     * @param adult the number of adults specified in the story
     */
    @Then("The adult is \$adult")
    void setAdults(String adult)
    {
        //TODO add assertions
    }

    /**
     * Verifies that the number of seniors is correct
     * @param senior the number of seniors specified in the story
     */
    @Then("The seniors is \$senior")
    void setSenior(String senior)
    {
       //TODO add assertions
    }

    /**
     * Verifies that the booking itinerary has been completly filled out
     */
    @Then ("I verify my complete itinerary on the customized booking widget")
    void verifyCompleteItinerary()
    {
        //TODO add assertions
    }

    /**
     * Verifies that the booking widget has been successfully loaded
     */
    @Then ("I view the customized booking widget")
    void verifyViewingCustomizedBookingWidget(){
        //TODO add assertions
    }

    /**
     * Verifies that the departure date is set to the parameters from the story
     * @param departDate
     */
    @Then("The depart date is \$departDate")
    void departureDate(String departDate) {
        //TODO add assertions
    }

    /**
     * Verifies that the arrival date is set to the parameters from the story
     * @param arrivalDate
     */
    @Then("The arrival date is \$arrivalDate")
    void arrivalDate(String arrivalDate) {
        //TODO add assertions
    }

    /**
     * Verifies that the promocode is correct with what was specified in the story
     * @param promoCode
     */
    @Then("The promocode is \$promoCode")
    void checkPromocode(String promoCode){
        //TODO add assertions
    }

    /**
     * Verifies that the booking widget has been displayed correctly
     */
    @Then("The booking widget displayed as expected")
    void bookingWidgetDisplaysCorrectly() {
        //TODO add assertions
    }

    /**
     * Verifies that the arrival city has been set correctly
     * @param arrival
     */
    @Then("The arrival city is \$arrival")
    void arrivalCityVerification(String arrival)
    {
        //TODO add assertions
    }

    /**
     * Verifies that the departure city has been set correctly
     * @param depart
     */
    @Then("The departure city is \$depart")
    void departCityVerification(String depart){
        //TODO add assertions
    }


}
