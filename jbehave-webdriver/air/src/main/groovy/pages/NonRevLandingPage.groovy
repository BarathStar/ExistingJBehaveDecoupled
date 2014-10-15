package pages

import domain.AirReservation
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow
import state.ScenarioState

import static com.thoughtworks.selenium.SeleneseTestBase.fail

class NonRevLandingPage extends BasePage {

    ScenarioState scenarioState
    Flow flow

    private static final String PATH = "/non-rev/landing.html?nonRevId=e007"
    private static final By GET_SECURITY_DOCUMENT_BUTTON = By.linkText("Get Security Document")
    private static final By RETRIEVE_CANCEL_ITINERARY_BUTTON = By.linkText("Retrieve / Cancel Itinerary")

    private static final By CONFIRMATION_NUMBER_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passengerConfirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passenger_firstName")
    private static final By LAST_NAME_FIELD = By.id("swa_feature_nonRev_securityDocument_form_passengerLastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By CANCEL_BUTTON = By.id("cancelButton")
    private static final By CHECKIN_BUTTON = By.id("printDocumentsButton")
    private static final By UPDATE_OOPS_ELEMENT = By.id("errorFromAjaxCall")
    private static final By RAPID_REWARDS_HELP_LINK = By.id("checkin_account_login_form_field_rr_help_link")
    private static final By RAPID_REWARDS_MODAL = By.className("swa_feature_checkInRRNumber_modal")
    private static final By ORIGIN_STATION_INPUT = By.id("originAirport_displayed")
    private static final By DESTINATION_STATION_INPUT = By.id("destinationAirport_displayed")

    private static final String UPDATE_OOPS_MESSAGE = "The Rapid Rewards Account Number(s) for the following Passenger(s) is/are invalid:"
    private final title = "Southwest Airlines - Checkin Online and Print Boarding Passes"

    String getRelativePath() {
        return PATH
    }

    public NonRevLandingPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def gotoRetrieveCancelItineraryPage() {
        waitForElement(RETRIEVE_CANCEL_ITINERARY_BUTTON).click()
        String pageTitle = waitForElement(By.className("swa_page_header_title")).text
        pageTitle.shouldEqual("Retrieve/Cancel Nonrevenue Listing")
    }

    def gotoNonRevCheckinPage() {
        waitForElement(GET_SECURITY_DOCUMENT_BUTTON).click()
        String pageTitle = waitForElement(By.className("swa_page_header_title")).text
        pageTitle.shouldEqual("Security Document")
    }


    def retrieveReservationToCheckin(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON).click()
    }

    def verifyCheckinLandingPage(){
        getTitle().equalsIgnoreCase(title).shouldBe true, "wrong boarding pass landing page should be ${title}"
    }

    def verifyUpdateOopsMessage() {
        waitForElement(UPDATE_OOPS_ELEMENT).getText().shouldContain "The Rapid Rewards Account Number(s) for the following Passenger(s) is/are invalid:", "The Oops message '${UPDATE_OOPS_MESSAGE}' has not been found with the text"
    }

    def selectPassengerAndCancel(){
           selectCheckBoxIfNotSelected()
           waitForElement(CANCEL_BUTTON).click()
       }

    def clickCheckinButton() {
        waitForElement(CHECKIN_BUTTON).click()
        WaitForPageToLoad
    }

    def clickHelpLink() {
        waitForElement(RAPID_REWARDS_MODAL).findElement(RAPID_REWARDS_HELP_LINK).click()
    }

    def fillOriginStation(String originStation){
        WebElement input = waitForElement(ORIGIN_STATION_INPUT)
        input.sendKeys DELETE_EXISTING + originStation
        input.sendKeys TAB

    }

    def fillDestinationStation(String destinationStation){
        WebElement input = waitForElement(DESTINATION_STATION_INPUT)
        input.sendKeys DELETE_EXISTING + destinationStation
        input.sendKeys TAB
    }

    def verifyValidOriginStation(String station){
        waitForElement(ORIGIN_STATION_INPUT).getAttribute("value").shouldContain station, "The origin aiport should be San Juan , PR - SJU"
    }


}
