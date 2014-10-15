/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import pages.OutboundAndReturnDatesAndPopUp
import util.ItineraryData

import static pages.BasePage.DELETE_EXISTING

/**
 * This class represent a vacation package form
 */
class VacationPackageForm {

    private WebElement form

    private static final String ORIGIN_ID = 'area1_displayed'
    private static final String DESTINATION_ID = 'area2'
    private static final String DEPARTURE_DATE_ID = 'date1'
    private static final String DEPARTURE_TIME_ID = 'time1'
    private static final String RETURN_DATE_ID = 'date2'
    private static final String RETURN_TIME_ID = 'time2'
    private static final String NUMBER_OF_ROOMS_ID = 'num_rooms'
    private static final String NUMBER_OF_ADULTS_ID = 'adults'
    private static final String NUMBER_OF_CHILDREN_ID = 'kids'
    private static final String SEARCH_BUTTON_ID = 'swa_vacationsPackage_submit'


    VacationPackageForm(WebElement theContainer){
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getOrigin() {
        return form.findElement(By.id(ORIGIN_ID))
    }

    private WebElement getDestination() {
        return form.findElement(By.id(DESTINATION_ID))
    }

    private WebElement getDepartureDate() {
        return form.findElement(By.id(DEPARTURE_DATE_ID))
    }

    private WebElement getDepartureTime() {
        return form.findElement(By.id(DEPARTURE_TIME_ID))
    }

    private WebElement getReturnDate() {
        return form.findElement(By.id(RETURN_DATE_ID))
    }

    private WebElement getReturnTime() {
        return form.findElement(By.id(RETURN_TIME_ID))
    }

    private WebElement getNumberOfRooms() {
        return form.findElement(By.id(NUMBER_OF_ROOMS_ID))
    }

    private WebElement getNumberOfAdults() {
        return form.findElement(By.id(NUMBER_OF_ADULTS_ID))
    }

    private WebElement getNumberOfChildren() {
        return form.findElement(By.id(NUMBER_OF_CHILDREN_ID))
    }

    void fillInOriginAndDestination(ItineraryData itineraryData) {
        origin.sendKeys DELETE_EXISTING + itineraryData.departureStation
        new Select(destination).selectByValue(itineraryData.arrivalStation)
    }

    void fillInDepartAndReturnDates(OutboundAndReturnDatesAndPopUp calendarPopUp, Date vacationDepartureDate, Date vacationReturnDate) {
        calendarPopUp.typeInOutboundOrInboundDate(vacationDepartureDate.format("MM/dd/yyyy"), DEPARTURE_DATE_ID)
        calendarPopUp.typeInOutboundOrInboundDate(vacationReturnDate.format("MM/dd/yyyy"), RETURN_DATE_ID)
        returnDate.sendKeys(Keys.ESCAPE)
    }

    void clearDepartAndReturnDates(OutboundAndReturnDatesAndPopUp calendarPopUp) {
        calendarPopUp.typeInOutboundOrInboundDate("", DEPARTURE_DATE_ID, false)
        calendarPopUp.typeInOutboundOrInboundDate("", RETURN_DATE_ID, false)
        returnDate.sendKeys(Keys.ESCAPE)
    }

    void fillInDepartAndReturnTimes(String departTimeValue, String returnTimeValue) {
        new Select(departureTime).selectByValue(departTimeValue)
        new Select(returnTime).selectByValue(returnTimeValue)
    }

    void fillInRooms(int rooms) {
        new Select(numberOfRooms).selectByValue(rooms.toString())
    }

    void fillInAdults(int adults) {
        new Select(numberOfAdults).selectByValue(adults.toString())
    }

    void fillInChildren(int children) {
        new Select(numberOfChildren).selectByValue(children.toString())
    }

    def verifyFieldsAreNotNull() {
        origin.getAttribute("value").shouldNotBe null, "The origin should not be cleared"
        departureDate.getAttribute("value").shouldNotBe null, "The outbound date should not be cleared"
        returnDate.getAttribute("value").shouldNotBe null, "The return date should not be cleared"
    }

    def verifyFieldsHaveErrors(List<String> fields) {
        for (field in fields) {
            WebElement element = this."${field}"
            element.getAttribute("class").shouldContain "fieldError"
        }
    }

    def verifyFieldsDoNotHaveErrors(List<String> fields) {
        for (field in fields) {
            WebElement element = this."${field}"
            element.getAttribute("class").shouldNotContain "fieldError"
        }
    }

    static VacationPackageForm createVacationPackageForm(WebElement form) {
        return new VacationPackageForm(form)
    }
}
