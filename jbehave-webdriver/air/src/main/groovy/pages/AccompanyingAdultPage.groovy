/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.ScenarioState
import util.ItineraryData
import domain.AirReservation

public class AccompanyingAdultPage extends BasePage {

    private static final String WINDOWS_TITLE_MSG = 'Southwest Airlines - Accompanying Adult Info'
    private static final By SUBMIT_BUTTON = By.id('retrieveItinerarySubmitButton')
    private static final By ACCOMPAYING_ADULT_FIRST_NAME_NAME = By.id('firstName')
    private static final By ACCOMPAYING_ADULT_LAST_NAME_NAME = By.id('lastName')
    private static final By ACCOMPAYING_ADULT_PNR = By.id('confirmationNumber')
    private static final By CANCEL_LINK = By.cssSelector(".cancelLink")
    private static final By FLIGHT_ITINERARY = By.id('flightItinerary')
    private static final By CONTINUE_BUTTON = By.id('continueButton')
    private static final By FLIGHT_DATES = By.id('flightDates')
    private static final By DEPARTURE_FLIGHT_ITINERARY = By.id('airItinerary0')
    private static final By RETURN_FLIGHT_ITINERARY = By.id('airItinerary1')
    private static final By PASSENGER_FIRST_NAME = By.cssSelector('.firstName')
    private static final By PASSENGER_LAST_NAME = By.cssSelector('.lastName')
    private static final By ROUTING_DETAILS_STOP = By.cssSelector('.routingDetailsStops')
    private static final By TRAVEL_DATE = By.cssSelector('.travelDateTime')
    private static final By FLIGHT_NUMBER = By.cssSelector('.flightNumberSeparator')
    private static final String DATE_FORMAT = "MMMMM d, yyyy"
    private static final By ACCOMPAYING_ADULT_NAME = By.className('passenger_row_name')
    private static final By FLIGHT_NUMBER_CR1 = By.className('flightNumber')

    ScenarioState scenarioState
    ItineraryData itineraryData

    String getRelativePath() {
        return "flight/accompanying-adult-info.html"
    }

    def AccompanyingAdultPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    void clickRetrieveItinerary() {
        waitForElement(SUBMIT_BUTTON).click()
        checkSomethingServed()
    }

    void clickContinue() {
        waitForElement(CONTINUE_BUTTON).click()
    }

    void clickCancel() {
        waitForElement(CANCEL_LINK).click()
        verifyPage()
    }

    void verifyAccompanyingTravelerFormTitle() {
        getTitle().shouldBe WINDOWS_TITLE_MSG, "The windows title should be ${WINDOWS_TITLE_MSG}"
    }

    void verifyAccompanygTravelerFormMandatoryFields() {
        def oopsMessages = [
            'The confirmation number was not entered',
            'The passenger\'s first name was not entered',
            'The passenger\'s last name was not entered'
        ]
        verifyOopsMessages(oopsMessages)
    }

    void verifyItineraryNotMatchCurrentItinerary() {
        shouldShowOopsMessage("The flight itinerary retrieved does not match the Unaccompanied Minor(s) itinerary. Please retrieve a new itinerary that matches the Unaccompanied Minor(s)")
    }

    void fillForm() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        waitForElement(ACCOMPAYING_ADULT_FIRST_NAME_NAME).sendKeys DELETE_EXISTING + airReservation.passengers.get(0).firstName
        waitForElement(ACCOMPAYING_ADULT_LAST_NAME_NAME).sendKeys DELETE_EXISTING + airReservation.passengers.get(0).lastName
        waitForElement(ACCOMPAYING_ADULT_PNR).sendKeys DELETE_EXISTING + airReservation.adultPnr
        clickRetrieveItinerary()
    }

    void verifyAccompayingItinerarySection() {
        verifyPage()
        WebElement departureFlightItinerary =  waitForElement(DEPARTURE_FLIGHT_ITINERARY)

        WebElement accompaying_adult_name =  waitForElement(ACCOMPAYING_ADULT_NAME)
        accompaying_adult_name.getText().toUpperCase().shouldContain scenarioState.getLastAirReservation().passengers.get(0).firstName.toUpperCase()
        accompaying_adult_name.getText().toUpperCase().shouldContain scenarioState.getLastAirReservation().passengers.get(0).lastName.toUpperCase()
        departureFlightItinerary.findElement(FLIGHT_NUMBER_CR1).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.departingFlight_number

        departureFlightItinerary.findElement(ROUTING_DETAILS_STOP).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.departureStation
        departureFlightItinerary.findElement(TRAVEL_DATE).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.departureDate.format(DATE_FORMAT)

        if (scenarioState.getLastAirReservation().itineraryData.itineraryType != "One Way") {
            WebElement returnFlightItinerary =  waitForElement(RETURN_FLIGHT_ITINERARY)
            returnFlightItinerary.findElement(ROUTING_DETAILS_STOP).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.returnStation
            returnFlightItinerary.findElement(TRAVEL_DATE).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.returnDate.format(DATE_FORMAT)

            returnFlightItinerary.findElement(FLIGHT_NUMBER_CR1).getText().shouldContain scenarioState.getLastAirReservation().itineraryData.returningFlight_number

        }
    }
}
