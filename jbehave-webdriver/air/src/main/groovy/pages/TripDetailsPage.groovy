package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import pages.elements.StopLogicInfo;
import state.CarReservationData
import state.Flow
import util.HotelItineraryData
import state.ScenarioState
import util.ItineraryData
import util.TripManagement
import util.Station

class TripDetailsPage extends BasePage{

    private static final By RENAME_TRIP_BUTTON = By.className("rename_trip_button")
    private static final By TRIP_NAME = By.className("trip_itinerary_title")
    private static final By FLIGHT_DATES = By.id("flightDates")
    private static final By FLIGHT_ITINERARY = By.id("flightItinerary")
    public final static String FLIGHT_ITINERARY_PATTERN = ~/^\w+ \([\w ]+\), \w{2} - \w{3}  to  \w+ \([\w ]+\), \w{2} - \w{3}$/
    private static final By CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By PASSENGER_CONF = By.className("passenger_conf")
    private static final By CAR_ITINERARY_DATES = By.className("car_itinerary_car_date")
    private static final By CAR_ITINERARY_RENTAL_INFO = By.className("car_itinerary_rental_info")
    public final static String ITINERARY_DATES = ~/^\d\d\/\d\d\/\d\d\d\d - \d\d\/\d\d\/\d\d\d\d$/
    private static final By MODAL_TITLE = By.cssSelector("#trip_products_associated_with_car_hotel_being_cancelled_modal  h5.product_car")
    private static final String MODAL_CANCEL_CAR_RESERVATION_TITLE = "Cancel Car Reservation"
    private static final By MODAL_NO_CANCEL_BUTTON = By.cssSelector(".cancellation_modal_actions_button .simplemodal-close")
    private static final By TRIP_ITINERARY_CONTAINER = By.className("itinerary_container")
    private static final By HOTEL_ITINERARY = By.cssSelector(".itinerary_container.hotel_itinerary_container_with_vertical_label .itineraries_header_data.itineraries_header_data_cancel")
    private static final By HOTEL_DATES = By.className("hotel_itinerary_hotel_date")
    private static final By MODAL_YES_CANCEL_BUTTON = By.cssSelector(".cancellation_modal_actions_button #cancelReservationButton")
    private static final By MODAL_CAR_ITINERARY_RENTAL_INFO = By.cssSelector("#cancellation_modal_product_of_interest .car_itinerary_rental_info")
    private static final By MODAL_CAR_ITINERARY_DATES = By.cssSelector("#cancellation_modal_product_of_interest .car_itinerary_car_date")
    private static final By MODAL_CAR_ITINERARY_PNR = By.cssSelector("#cancellation_modal_product_of_interest  .confirmation_number")
    private static final By MODAL_ASSOCIATED_PRODUCT_TITLE = By.className("cancellation_modal_associated_products_title")
    private static final By MODAL_FLIGHT_ITINERARY = By.cssSelector("#trip_products_associated_with_car_hotel_being_cancelled_modal #flightItinerary")
    private static final By MODAL_FLIGHT_DATES = By.cssSelector("#trip_products_associated_with_car_hotel_being_cancelled_modal #flightDates")
    private static final By MODAL_ASSOCIATED_AIR_PNR = By.cssSelector(".air_itinerary_container_with_image .confirmation_number")
    private static final By RAPID_REWARD_NUMBER = By.className("passenger_row_rr_number")
    private static final By ADD_RAPID_REWARDS_NUMBER_LINK = By.cssSelector(".passenger_row_rr_number a")

    private static final String ASSOCIATED_ITEMS_VERBIAGE = "Items associated with this Trip"
    private static final String DATE_FORMAT = "MM/dd/yyyy"
	private static final String AIR_PRODUCT = "flight"
	private static final String CAR_PRODUCT = "car"
	private static final String HOTEL_PRODUCT = "hotel"

    Flow flow
    ScenarioState scenarioState
    ItineraryData itineraryData
    CarReservationData carReservationData
    HotelItineraryData hotelItineraryData
    StopLogicInfo stopLogicInfo

     public TripDetailsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/travel/upcoming-trips/air-trip-details');
    }

     String getRelativePath() {
        return "/account/travel/upcoming-trips/air-trip-details"
    }

    public verifyPageVisible(){
        waitForElement(By.id("plane_icon_confirmation_label"))
        waitForElement(By.className("trip_itinerary_title")).getText().shouldContain flow.tripName
    }

    def verifyRenameButtonIsNotVisible() {
        verifyElementNotPresent("Rename Trip Button", RENAME_TRIP_BUTTON)
    }

    def verifyDefaultTripNameForAir() {
        verifyElementPresent("Trip Name", TRIP_NAME)
        verifyDefaultTripNameIsFormattedCorrectly()
        TripManagement.validateTripName(findElement(TRIP_NAME).getText(), itineraryData)
    }

    def verifyAirDetails() {
        validatePNR(waitForElement(CONFIRMATION_NUMBER).getText(),scenarioState.lastAirReservation.getAdultPnr())
		verifyAirItinerary()
    }

    def verifyAirDetailsWhenTripManagementToggleOff() {
        validatePNR(waitForElement(PASSENGER_CONF).getText(),scenarioState.lastAirReservation.getAdultPnr())
    }

    def verifyAirItinerary() {
        validateDatesFormat(waitForElement(FLIGHT_DATES).getText(), TripManagement.ITINERARY_DATES, AIR_PRODUCT)
        validateDatesFormat(waitForElement(FLIGHT_ITINERARY).getText(), FLIGHT_ITINERARY_PATTERN, AIR_PRODUCT)
    }

    def verifyCarDetails() {
        verifyCarItinerary()
        validatePNR(waitForElement(CONFIRMATION_NUMBER).getText(), carReservationData.confirmationNumber)
    }

    def verifyCarItinerary() {
        validateDatesFormat(waitForElement(CAR_ITINERARY_DATES).getText(), ITINERARY_DATES, CAR_PRODUCT)
        TripManagement.validateCarRentalInfo(waitForElement(CAR_ITINERARY_RENTAL_INFO), carReservationData)
    }

    def verifyDefaultTripNameIsFormattedCorrectly() {
        findElement(TRIP_NAME).getText().matches(TripManagement.DEFAULT_TRIP_NAME).shouldBe true, 'The trip name in the View Reservation Page should be: MM/dd/yy - Arrival City Name'
    }

    private void verifyHotelItinerary() {
        validateDatesFormat(waitForElement(HOTEL_DATES).getText(), ITINERARY_DATES, HOTEL_PRODUCT)
        waitForElement(HOTEL_ITINERARY).getText().shouldContain hotelItineraryData.hotelName, "The hotel name should be " + hotelItineraryData.hotelName
    }

    private void validatePNR(String current, String expected) {
        current.shouldContain expected, "The PNR should be ${expected}"
    }

    def verifyTripNameOnTripDetailsPage() {
        isElementWithTextPresent(TRIP_NAME, flow.tripName).shouldBe true, "The trip name should be " + flow.tripName
    }

    def verifyTripProductsListedByDateAndType() {
        verifyProductTypeSort()
        verifyCarItinerary()
        verifyAirItinerary()
        verifyHotelItinerary()
    }

    private void verifyProductTypeSort() {
        List tripInformationContainers = waitForElements(TRIP_ITINERARY_CONTAINER)
        tripInformationContainers.get(0).getAttribute("class").shouldContain "car_itinerary_container_with_vertical_label", "The car product should be listed first"
        tripInformationContainers.get(1).getAttribute("class").shouldContain "air_itinerary_container_with_vertical_label", "The air product should be listed second"
        tripInformationContainers.get(2).getAttribute("class").shouldContain "hotel_itinerary_container_with_vertical_label", "Then hotel product should be listed third"
    }

    def verifyAssociatedProductsModal() {
        waitForElement(MODAL_TITLE).getText().shouldContain MODAL_CANCEL_CAR_RESERVATION_TITLE, "The modal title should be ${MODAL_CANCEL_CAR_RESERVATION_TITLE}"
        isElementPresent(MODAL_NO_CANCEL_BUTTON).shouldBe true, "The No Cancel button should be present on modal"
        isElementPresent(MODAL_YES_CANCEL_BUTTON).shouldBe true, "The Yes Cancel button should be present on modal"
    }

    def verifyCarReservationOnAssociatedProductModal() {
        TripManagement.validateCarRentalInfo(waitForElement(MODAL_CAR_ITINERARY_RENTAL_INFO), carReservationData)
        validateDatesFormat(waitForElement(MODAL_CAR_ITINERARY_DATES).getText(), ITINERARY_DATES, CAR_PRODUCT)
        validatePNR(waitForElement(MODAL_CAR_ITINERARY_PNR).getText(), scenarioState.getLastAirReservation().carReservation.confirmationNumber)
    }

    def verifyAssociatedAirOnAssociatedProductOnModal() {
        waitForElement(MODAL_ASSOCIATED_PRODUCT_TITLE).getText().shouldContain ASSOCIATED_ITEMS_VERBIAGE, "The associated items verbiage should be present"

        String departureStation = Station.valueOf(Station.class, scenarioState.getLastAirReservation().itineraryData.departureStation).cityName
        String arrivalStation = Station.valueOf(Station.class, scenarioState.getLastAirReservation().itineraryData.arrivalStation).cityName
        isElementWithTextPresent(MODAL_FLIGHT_ITINERARY, departureStation).shouldBe true, "The departure station should be present"
        isElementWithTextPresent(MODAL_FLIGHT_ITINERARY, arrivalStation).shouldBe true, "The arrival station should be present"

        String departureDate = scenarioState.getLastAirReservation().itineraryData.departureDate.format(DATE_FORMAT)
        String returnDate = scenarioState.getLastAirReservation().itineraryData.returnDate.format(DATE_FORMAT)
        isElementWithTextPresent(MODAL_FLIGHT_DATES, departureDate)
        isElementWithTextPresent(MODAL_FLIGHT_DATES, returnDate)

        String pnr = scenarioState.getLastAirReservation().adultPnr
        validatePNR(waitForElement(MODAL_ASSOCIATED_AIR_PNR).getText(), pnr)
    }

    def verifyElementRapidRewardsNumber(){
        waitForElement(RAPID_REWARD_NUMBER).getText().shouldContain flow.getUser().getRRNumber(),"Rapid Rewards Number is ${RAPID_REWARD_NUMBER}"
    }

    def clickAddRapidRewardsNumberLink(){
        waitForElement(ADD_RAPID_REWARDS_NUMBER_LINK).click()
    }

}