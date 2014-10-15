/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.support.ui.Select
import static pages.BasePage.DELETE_EXISTING
import util.ItineraryData
import pages.OutboundAndReturnDatesAndPopUp

/**
 * This class represent a vacation form
 */
class VacationForm {

    private WebElement form

    private static final String VACATIONS_FORM_ORIGIN_ID = 'origin_displayed'
    private static final String VACATIONS_FORM_DESTINATION_ID = 'destination_displayed'
    private static final String VACATIONS_FORM_DEPARTURE_DATE_ID = 'outboundDate'
    private static final String VACATIONS_FORM_RETURN_DATE_ID = 'returnDate'
    private static final String VACATIONS_FORM_NUMBER_OF_ADULTS_ID = 'gsNumberOfTravelers'
    private static final String VACATIONS_FORM_NUMBER_OF_CHILDREN_ID = 'numberOfKids'
    private static final String VACATIONS_FORM_SEARCH_BUTTON_ID = 'swa_vacationsLanding_submit'
    private static final String VACATIONS_FORM_PROMO_CODE_ID = 'gsPromotionCode'
    private static final String VACATIONS_FORM_CHILDREN_AGE_1_ID = 'gsAge1'
    private static final String VACATIONS_FORM_CHILDREN_AGE_2_ID = 'gsAge2'
    private static final String VACATIONS_FORM_CHILDREN_AGE_3_ID = 'gsAge3'
    private static final String VACATIONS_FORM_CHILDREN_AGE_4_ID = 'gsAge4'

    VacationForm(WebElement theContainer){
        form = theContainer
        form?.isEnabled().shouldBe true, "Form is not enabled on the page"
    }

    private WebElement getOrigin() {
        return form.findElement(By.id(VACATIONS_FORM_ORIGIN_ID))
    }

    private WebElement getDestination() {
        return form.findElement(By.id(VACATIONS_FORM_DESTINATION_ID))
    }

    private WebElement getDepartureDate() {
        return form.findElement(By.id(VACATIONS_FORM_DEPARTURE_DATE_ID))
    }

    private WebElement getReturnDate() {
        return form.findElement(By.id(VACATIONS_FORM_RETURN_DATE_ID))
    }

    private WebElement getNumberOfAdults() {
        return form.findElement(By.id(VACATIONS_FORM_NUMBER_OF_ADULTS_ID))
    }

    private WebElement getNumberOfChildren() {
        return form.findElement(By.id(VACATIONS_FORM_NUMBER_OF_CHILDREN_ID))
    }

    private WebElement getPromoCode() {
        return form.findElement(By.id(VACATIONS_FORM_PROMO_CODE_ID))
    }

    private WebElement getChildrenAge1() {
        return form.findElement(By.id(VACATIONS_FORM_CHILDREN_AGE_1_ID))
    }

    private WebElement getChildrenAge2() {
        return form.findElement(By.id(VACATIONS_FORM_CHILDREN_AGE_2_ID))
    }

    private WebElement getChildrenAge3() {
        return form.findElement(By.id(VACATIONS_FORM_CHILDREN_AGE_3_ID))
    }

    private WebElement getChildrenAge4() {
        return form.findElement(By.id(VACATIONS_FORM_CHILDREN_AGE_4_ID))
    }

    void fillInOriginAndDestination(ItineraryData itineraryData) {
        origin.sendKeys DELETE_EXISTING + itineraryData.departureStation
        destination.sendKeys DELETE_EXISTING + itineraryData.arrivalStation
    }

    void fillInDepartAndReturnDates(OutboundAndReturnDatesAndPopUp calendarPopUp, Date vacationDepartureDate, Date vacationReturnDate) {
        calendarPopUp.typeInOutboundOrInboundDate(vacationDepartureDate.format("MM/dd/yyyy"), VACATIONS_FORM_DEPARTURE_DATE_ID)
        calendarPopUp.typeInOutboundOrInboundDate(vacationReturnDate.format("MM/dd/yyyy"), VACATIONS_FORM_RETURN_DATE_ID)
        returnDate.sendKeys(Keys.ESCAPE)
    }

    void clearDepartAndReturnDates(OutboundAndReturnDatesAndPopUp calendarPopUp) {
        calendarPopUp.typeInOutboundOrInboundDate("", VACATIONS_FORM_DEPARTURE_DATE_ID, false)
        calendarPopUp.typeInOutboundOrInboundDate("", VACATIONS_FORM_RETURN_DATE_ID, false)
        returnDate.sendKeys(Keys.ESCAPE)
    }

    void fillInAdult(int amountOfAdults) {
        new Select(numberOfAdults).selectByValue(amountOfAdults.toString())
    }

    void fillInChildren(int amountOfChildren) {
        new Select(numberOfChildren).selectByValue(amountOfChildren.toString())
    }

    void fillInPromoCode(String code) {
        promoCode.sendKeys DELETE_EXISTING + code
    }

    void fillInChildrenAge1(int age) {
        new Select(childrenAge1).selectByValue(age.toString())
    }

    void fillInChildrenAge2(int age) {
        new Select(childrenAge2).selectByValue(age.toString())
    }

    void fillInChildrenAge3(int age) {
        new Select(childrenAge3).selectByValue(age.toString())
    }

    void fillInChildrenAge4(int age) {
        new Select(childrenAge4).selectByValue(age.toString())
    }

    def verifyFieldsAreNotNull() {
        origin.getAttribute("value").shouldNotBe null, "The origin should not be cleared"
        destination.getAttribute("value").shouldNotBe null, "The destination should not be cleared"
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

    static VacationForm createVacationForm(WebElement form) {
        return new VacationForm(form)
    }
}
