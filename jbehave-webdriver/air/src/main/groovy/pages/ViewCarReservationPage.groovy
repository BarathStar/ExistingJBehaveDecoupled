package pages

import domain.CarReservation
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class ViewCarReservationPage extends BasePage{

    private static final By TRIP_ASSOCIATED_PRODUCTS = By.className('trip_associated_products')
    private static final By CANCEL_AIR_RESERVATION_LINK = By.id('cancelReservationLink')
    private static final By TRIP_DETAILS_CONTAINER = By.className("trip_details_container")
    private static final By CONFIRMATION_NUMBER = By.cssSelector("span.confirmation_number")
    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By RETRIEVE_CAR_RESERVATION_CONFIRMATION_NUMBER_FIELD = By.id("confirmationNumber")
    private static final By PICK_UP_DATE_CALENDAR = By.id("lookupDate")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By CAR_CANCEL_LINK = By.id("carCancelLink")

    ViewCarReservationPage(WebDriverProvider driverProvider) {
        super(driverProvider,'/car-rentals/retrieve-car-reservation.html')
    }

    String getRelativePath() {
        return "/car-rentals/retrieve-car-reservation.html"
    }

    def fillInCarRetrievalByReservationNumber(CarReservation carReservation) {
        fillIn(RETRIEVE_CAR_RESERVATION_CONFIRMATION_NUMBER_FIELD, carReservation.confirmationNumber,true)
        fillIn(PICK_UP_DATE_CALENDAR, carReservation.getPickUpDateAsString(),true)
        fillIn(FIRST_NAME_FIELD, carReservation.renterFirstName,true)
        fillIn(LAST_NAME_FIELD,carReservation.renterLastName,true)
    }

    void submitRetrieveCarForm() {
        waitForElement(SUBMIT_BUTTON).click()
        verifyPage()
    }

    void verifyAssociatedItemsDisplayed() {
        isElementDisplayed(TRIP_ASSOCIATED_PRODUCTS).shouldBe true, "The Associated Items should be visible"
    }

    void clickOnCancelAirProductLink() {
        WebElement detailsContainer = waitForElement(TRIP_DETAILS_CONTAINER)
        WebElement cancelReservationLink = detailsContainer.findElement(CANCEL_AIR_RESERVATION_LINK)
        cancelReservationLink.click()
        verifyPage()
    }

    void viewCarReservation(){
        waitForElement(CONFIRMATION_NUMBER).getText().shouldNotBe null, "Car PNR was Not Found"
    }

    void clickOnCancelCarProductLink() {
        waitForElement(CAR_CANCEL_LINK).click()
    }

    void verifyCurrentPageAndTitle(){
        title.shouldContain 'Southwest Airlines - Car Reservation', "The title of the current page doesn't match with the expected one"
    }
}
