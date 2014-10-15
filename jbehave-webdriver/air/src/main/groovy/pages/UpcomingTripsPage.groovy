package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.CarReservationData
import util.HotelItineraryData
import util.ItineraryData
import util.TripManagement

import static org.junit.Assert.fail

class UpcomingTripsPage extends BasePage {

    ItineraryData itineraryData
    HotelItineraryData hotelItineraryData
    CarReservationData carReservationData

    private static final String PATH = "/account/travel/upcoming-trips"
    private static final By UPCOMING_TRIPS_CONTAINER = By.className("trip_information_trips_outer_container")
    private static final String NO_UPCOMING_TRIPS_MESSAGE = "There are no Upcoming Trips at this moment."
    private static final By FIRST_VIEW_DETAILS_LINK = By.id("viewTripDetails1")
    private static final By VIEW_CAR_DETAILS_LINK = By.id("viewTripDetails1_car1")
    private static final By TRIP_DETAILS_HOTEL1 = By.id("viewTripDetails1_hotel1")
    private static final By VIEW_FIRST_UPCOMING_TRIP_LINK = By.id("viewTripDetails1");
    private static final By ITINERARY_HOTEL_NAME = By.cssSelector(".itineraries_header_data p:not([class])")
    private static final By ITINERARY_HOTEL_DATES = By.className("hotel_itinerary_hotel_date")
    private static final By ITINERARY_HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_itinerary_hotel_confirmation_number")
    private static final By ITINERARY_AIR_CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By ITINERARY_HOTEL_TRIP_NAME = By.className("trip_itinerary_title")
    private static final By CANCEL_RESERVATION = By.xpath("//a[@title='Cancel Reservation']")
    private static final By VENDOR_SIZE_CITY_DATA_CAR = By.id("viewTripDetails1_car1")
    private static final By TRIP_INFORMATION_TRIP_PRODUCT_HEADER_DETAILS = By.cssSelector(".trip_information_trip_product_header_details")
    private static final By DATE_RANGE_TRIP_DETAILS = By.cssSelector(".trip_information_trip_product_header_details.air_upcoming_trip_container .dateRange.trip_information_trip_details_data")
    private static final String DATE_FORMAT_PATTERN = "MM/dd/yyyy"
    private static final By CAR_DATES = By.cssSelector(".trip_information_trip_product_header_details.car_upcoming_trip_container .trip_information_car_hotel_trip_dates")
    private static final By FLIGHT_RESERVATION_NAME = By.cssSelector(".trip_information_trip_product_header_details.air_upcoming_trip_container a")
    private static final By HOTEL_RESERVATION_NAME = By.cssSelector(".trip_information_trip_product_header_details.hotel_upcoming_trip_container a")
    private static final By HOTEL_DATES = By.cssSelector(".trip_information_trip_product_header_details.hotel_upcoming_trip_container .trip_information_car_hotel_trip_dates")
    private static final By FIRST_ADD_COMPANION_LINK = By.cssSelector(".addCompanion:nth-of-type(1)")
    private static final By FIRST_CHANGE_RESERVATION_LINK = By.cssSelector(".changeReservation:nth-of-type(1)")
    private static final By FIRST_CANCEL_RESERVATION_LINK = By.cssSelector(".cancelReservation:nth-of-type(1)")
    public final static String ITINERARY_DATES = ~/^\d\d\/\d\d\/\d\d\d\d – \d\d\/\d\d\/\d\d\d\d$/
    private static final String AIR_PRODUCT = "flight"
    private static final String CAR_PRODUCT = "car"
    private static final String HOTEL_PRODUCT = "hotel"
    private static final By HOTEL_CANCEL_RESERVATION_LINK = By.id("hotelCancelLink");
    private static final By CAR_HOTEL_CANCEL_RESERVATION_MODAL = By.id("simplemodal-container");
    private static final By CAR_CANCEL_RESERVATION_LINK = By.id("carCancelLink");
    private static final By CANCEL_CONFIRMATION_BUTTON = By.id("cancelReservationButton")

    UpcomingTripsPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def verifyNoUpcomingTripsMessage() {
        waitForElement(UPCOMING_TRIPS_CONTAINER).getText().shouldContain NO_UPCOMING_TRIPS_MESSAGE, "The No Upcoming Trips message is not present"
    }

    def clickOnViewAirDetailsLink() {
        waitForElement(FIRST_VIEW_DETAILS_LINK).click()
        verifyPage()
    }

    def clickFirstHotelReservationLink() {
        waitForElement(TRIP_DETAILS_HOTEL1).click()
    }

    def clickOnCancelReservation() {
        waitForElement(CANCEL_RESERVATION).click()
    }

    def clickOnViewCarDetailsLink() {
        waitForElement(VIEW_CAR_DETAILS_LINK).click()
        verifyPage()
    }

    def clickOnFirstAddCompanionLink() {
        waitForElement(FIRST_ADD_COMPANION_LINK).click()
    }

    def verifyISeeDefaultTripNameUnderTripDetailsSection() {
        waitForElement(ITINERARY_HOTEL_TRIP_NAME).getText().matches(TripManagement.DEFAULT_TRIP_NAME).shouldBe true, 'The trip name should be: MM/dd/yy - Arrival City Name'
    }

    def verifyISeeHotelNameUnderTripDetailsSection() {
        TripManagement.validateHotelReservationName(waitForElement(ITINERARY_HOTEL_NAME).getText(), hotelItineraryData)
    }

    def verifyISeeHotelCheckInCheckOutDatesUnderTripDetailsSection(Date checkInDate, Date checkOutDate) {
        String dates = checkInDate.format(DATE_FORMAT_PATTERN) + " - " + checkOutDate.format(DATE_FORMAT_PATTERN)
        waitForElement(ITINERARY_HOTEL_DATES).getText().shouldContain dates, "Check in and check out dates should be in format: ${DATE_FORMAT_PATTERN} - ${DATE_FORMAT_PATTERN}"
    }

    def verifyISeeHotelConfirmationNumberUnderTripDetailsSection(String confirmationNumber) {
        waitForElement(ITINERARY_HOTEL_CONFIRMATION_NUMBER).getText().shouldBe confirmationNumber, "The hotel confirmation number should be ${confirmationNumber}"
    }

    private int countOfElementsFoundForConfirmationNumber(String confirmationNumber) {
        int i = 0
        List elements = waitForElements(ITINERARY_AIR_CONFIRMATION_NUMBER)
        for (WebElement element: elements) {
            if (element.getText().trim().equalsIgnoreCase(confirmationNumber)) {
                i++;
            }
        }
        return i
    }

    def clickViewDetailFlightForPNRWithCities(String arrivalCity, String departureCity) {
        List elements = waitForElements(FIRST_VIEW_DETAILS_LINK)
        WebElement linkFlightWithCities
        for (WebElement element: elements) {
            if (element.getText().contains(arrivalCity) && element.getText().contains(departureCity)) {
                linkFlightWithCities = element
            }
        }
        if(linkFlightWithCities!=null){
            linkFlightWithCities.click()
        }else{
            fail "The link with departure Station" + departureCity + " and arrival Station: " + arrivalCity  + "was not found."
        }

    }

    def verifyISeeAirConfirmationNumberUnderTripDetailsSection(String confirmationNumber) {
        int i = countOfElementsFoundForConfirmationNumber(confirmationNumber)
        if (i == 0) {
            fail "The confirmation number " + confirmationNumber + " not found."
        }
    }

    def verifyIDoNotSeeAirConfirmationNumberUnderTripDetailsSection(String confirmationNumber) {
        int i = countOfElementsFoundForConfirmationNumber(confirmationNumber)
        if (i > 0) {
            fail "The confirmation number " + confirmationNumber + " found."
        }
    }

    def verifyProductsOrder(String[] productOrder) {
        List productsList = waitForElements(TRIP_INFORMATION_TRIP_PRODUCT_HEADER_DETAILS)
        int i = 0
        for (WebElement element: productsList) {
            if (!element.getAttribute("class").contains(productOrder[i] + "_upcoming_trip_container")) {
                fail "Products Order not expected. Expected: " + productOrder
            }
            i++
        }
    }

    def verifyDataCarReservation() {
        TripManagement.validateCarRentalInfo(waitForElement(VENDOR_SIZE_CITY_DATA_CAR), carReservationData)
        String car = waitForElement(CAR_DATES).getText()
        validateDatesFormat(waitForElement(CAR_DATES).getText().replace("-", "–"), ITINERARY_DATES, CAR_PRODUCT)
    }

    def verifyDataHotelReservation() {
        String hotelReservationName = waitForElement(HOTEL_RESERVATION_NAME).getText()
        TripManagement.validateHotelReservationName(hotelReservationName, hotelItineraryData)
        String hotel = waitForElement(HOTEL_DATES).getText()
        validateDatesFormat(waitForElement(HOTEL_DATES).getText().replace("-", "–"), ITINERARY_DATES, HOTEL_PRODUCT)
    }

    def verifyDataAirReservation() {
        String flightReservationName = waitForElement(FLIGHT_RESERVATION_NAME).getText()
        TripManagement.validateFlightReservationName(flightReservationName, itineraryData)
        String air = waitForElement(DATE_RANGE_TRIP_DETAILS).getText()
        validateDatesFormat(waitForElement(DATE_RANGE_TRIP_DETAILS).getText(), ITINERARY_DATES, AIR_PRODUCT)
    }

    def clickOnFirstChangeReservationLink() {
        waitForElement(FIRST_CHANGE_RESERVATION_LINK).click()
    }

    def clickOnFirstUpcomingTripLink() {
        waitForElement(VIEW_FIRST_UPCOMING_TRIP_LINK).click()
    }

    def clickOnFirstCancelReservationLink() {
        waitForElement(FIRST_CANCEL_RESERVATION_LINK).click()
    }

    def getFirstChangeReservationLink() {
        waitForElement(FIRST_CHANGE_RESERVATION_LINK)
    }

    def getFirstAddCompanionLink() {
        waitForElement(FIRST_ADD_COMPANION_LINK)
    }

    def getFirstCancelLink() {
        waitForElement(FIRST_CANCEL_RESERVATION_LINK)
    }

    def clickOnHotelCancelReservationLink() {
        waitForElement(HOTEL_CANCEL_RESERVATION_LINK).click()
    }

    def verifyHotelCancellationModal() {
        isElementDisplayed(CAR_HOTEL_CANCEL_RESERVATION_MODAL)
    }

    def clickOnCarCancelReservationLink() {
        waitForElement(CAR_CANCEL_RESERVATION_LINK).click()
    }

    def verifyCarCancellationModal() {
        isElementDisplayed(CAR_HOTEL_CANCEL_RESERVATION_MODAL)
    }

    def verifyStandAloneCarCancellationModal() {
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "Please Confirm Your Cancellation").shouldBe true, "Please Confirm Your Cancellation label should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "Things come up, and we understand that.\nSo, we'll cancel your reservation — free of charge. Just say the word.").shouldBe true, "The cancel confirmation message should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "No, Do Not Cancel").shouldBe true, "The No button should be present"
        waitForElement(CANCEL_CONFIRMATION_BUTTON).getAttribute("alt").shouldBe "Yes, Cancel My Car", "The Yes button should be present"
    }

    def verifyStandAloneHotelCancellationModal() {
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "Please Confirm Your Cancellation").shouldBe true, "Please Confirm Your Cancellation label should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "We understand, plans change. That's why you'll never see a cancellation fee from us, and it's why we want to be as upfront as we can about your hotel's cancellation policy.").shouldBe true, "The cancel confirmation message should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "No, Do Not Cancel").shouldBe true, "The No button should be present"
        waitForElement(CANCEL_CONFIRMATION_BUTTON).getAttribute("alt").shouldBe "Yes, Cancel My Hotel", "The Yes button should be present"
    }

    def verifyMultipleProductHotelCancellationModal(ItineraryData itineraryData) {
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "Cancel Hotel Reservation").shouldBe true, "Please Confirm Your Cancellation label should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "This cancellation will be for your Hotel reservation only. Please don't forget that there are other products attached to this trip.").shouldBe true, "The cancel confirmation message should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "No, Do Not Cancel").shouldBe true, "The No button should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, itineraryData.carSpecification.carVendor.name() + " - " + itineraryData.carSpecification.carType.name()).shouldBe true, "The associated car should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, itineraryData.arrivalStation).shouldBe true, "The associated air should be present"
        waitForElement(CANCEL_CONFIRMATION_BUTTON).getAttribute("alt").shouldBe "Yes, Cancel My Hotel", "The Yes button should be present"
    }

    def verifyMultipleProductCarCancellationModal(ItineraryData itineraryData) {
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "Cancel Car Reservation").shouldBe true, "Please Confirm Your Cancellation label should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "This cancellation will be for your Car reservation only. Please don't forget that there are other products attached to this trip.").shouldBe true, "The cancel confirmation message should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, "No, Do Not Cancel").shouldBe true, "The No button should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, itineraryData.hotelSpecification.hotelName + " - " + itineraryData.hotelSpecification.destination).shouldBe true, "The associated hotel should be present"
        isElementWithTextPresent(CAR_HOTEL_CANCEL_RESERVATION_MODAL, itineraryData.arrivalStation).shouldBe true, "The associated air should be present"
        waitForElement(CANCEL_CONFIRMATION_BUTTON).getAttribute("alt").shouldBe "Yes, Cancel My Car", "The Yes button should be present"
    }
}
