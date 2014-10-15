package pages

import domain.AirReservation
import domain.HotelReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent
import pages.elements.AirPriceContent;
import pages.elements.ChangeAirReservationModal
import pages.mixins.ConfirmationVerifications
import pages.mixins.PricingTableVerifications
import state.Flow
import state.ScenarioState
import util.BookingCode
import util.ItineraryData
import util.PageName
import util.RRContactInformation
import pages.mixins.*
import util.SelectFlightsPageData

@Mixin([PricingTableVerifications.class])
class SelectFlightsToChangePage extends BasePage {

    private static final String SUBMIT_BUTTON_XPATH = "//div[@class='continueButtonContainer']"

    private static final By SUBMIT_BUTTON_NAME = By.className("submitButton")
    private static final By SENIOR_FARE_CHECKBOX = By.id("senior1")
    private static final By ADD_EARLY_BIRD_BUTTON = By.xpath("//a[@title='Add EarlyBird Check-In']")
    private static final By FARE_PRODUCT_TYPE = By.className("fareProductName")
    private static final By CHANGE_AIR_RESERVATION_MODAL = By.id("trip_products_associated_with_air_being_cancelled_modal")
    private static final String ASSOCIATED_PRODUCTS_TITLE = "Items associated with this Trip"
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By POPUP_CONTENT_CONTAINER_CLASS = By.cssSelector(".popup_content_container")
    private static final By RR_NUMER = By.className("passenger_row_rr_number")
    private static final By AIR_HEADER = By.className("travel_type_air")
    private static final By PASSENGER_NAME = By.className("passenger_row_name")
    private static final By CONFIRMATION_NUMER = By.className("confirmation_number")
    private static final By CANCEL_CHANGE_BUTTON = By.cssSelector(".continueButtonGroupContainer a")
    private static final By FLIGHT_LOGO = By.cssSelector(".flightLogo img")

    Flow flow
    ScenarioState scenarioState
    RRContactInformation rrAccountData
    ItineraryData itineraryData
    AirPriceContent airPriceContent
    SelectFlightsPageData selectFlightsPageData

    String getRelativePath() {
        return "flight/view-reservation-to-change.html"
    }

    public verifyBasicPage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify change air reservation page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify change air reservation page) ---- checking for fault injection"
        }
        pageVerificationErrorWrapper(SUBMIT_BUTTON_NAME, PageName.SELECT_FLIGHTS_TO_CHANGE)
    }

    public selectFlightsToChangeAndContinue(ItineraryData itineraryData) {
        selectFlightsPageData.previousOutboundFlightPoints = selectFlightsPageData.outboundFlightPoints
        selectOriginDestinationsToChange(itineraryData.isOneWay())
        clickSeniorFareButtonIfSeniorFareOrPax(itineraryData.departingFlight_fareClass, itineraryData.arrivingFlight_fareClass, itineraryData.seniorPassengerCount)
        continueToChangeItinerary()
    }
    public selectOriginationAndDestinationCheckBox(ItineraryData itineraryData) {
        selectOriginDestinationsToChange(itineraryData.isOneWay())

    }

    public checkForMixedPNRsPopUp(ItineraryData itineraryData) {
        int seniorCount = itineraryData.seniorPassengerCount != null ? itineraryData.seniorPassengerCount.toInteger() : 0
        int adultCount = itineraryData.adultPassengerCount != null ? itineraryData.adultPassengerCount.toInteger() : 0
        if (seniorCount > 0 && adultCount > 0) {
            WebElement popUpContentContainer = waitForElement(POPUP_CONTENT_CONTAINER_CLASS)
            popUpContentContainer.findElement(By.id("continueCancelation")).click()
        }
    }


    def SelectFlightsToChangePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, "flight/view-reservation-to-change.html")
    }

    def selectOriginDestinationsToChange(boolean isOneWay) {
        selectOutBoundToChangeItinerary()
        if (!isOneWay) {
            selectInBoundToChangeItinerary()
        }
    }

    def selectOutBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("outbound1")
    }

    def selectInBoundToChangeItinerary() {
        selectCheckBoxifNotSelected("inbound1")
    }

    def continueToChangeItinerary() {

        WebElement submitButton = getChangeFlightPageSubmitButton()
        submitButton.click()
        verifyPage()
        flow.isAirChange = true
    }
    public  WebElement getChangeFlightPageSubmitButton() {
        WebElement submitButton = waitForElement(SUBMIT_BUTTON_NAME)
        if(!submitButton.isDisplayed()){
            submitButton = waitForElement(By.xpath(SUBMIT_BUTTON_XPATH), false).findElement(By.tagName("input"))
        }

        return submitButton
    }

    private def selectCheckBoxifNotSelected(String fld) {
        def checkbox = waitForElement(By.id(fld))
        if (!checkbox.isSelected()) {
            checkbox.click()
        }
    }

    def clickSeniorFareButtonIfSeniorFareOrPax(String outboundFareClass, String inboundFareClass, String seniorPaxCount) {

        int seniorCount = seniorPaxCount != null ? seniorPaxCount.toInteger() : 0
        if ("Senior".equals(outboundFareClass) || "Senior".equals(inboundFareClass || seniorCount > 0)) {
            waitForElement(SENIOR_FARE_CHECKBOX).click()
        }
    }

    def verifyIfSeniorFare(String fareClass) {
        if ("Senior".equals(fareClass)) {
            verifySeniorFareLink()
        }
    }

    def selectItineraryToChange(outbound, inbound) {
        if (outbound) {
            selectOutBoundToChangeItinerary()
        }
        else if (inbound) {
            selectInBoundToChangeItinerary()
        }
    }

    void verifyEarlyBirdUpsellNotPresent() {
        verifyElementNotPresent("Early Bird Checkin Button", ADD_EARLY_BIRD_BUTTON)
    }

    void verifyCheckBoxToSelectOutBoundToChangeIsNotVisible() {
        verifyElementNotPresent("Outbound Checkbox", By.id("outbound1"))
    }

    void verifyCheckBoxToSelecInBoundToChangeIsVisible() {
        verifyElementPresent("Inbound Checkbox", By.id("inbound1"))
    }

    def verifySeniorFareLink() {
        waitForElement(FARE_PRODUCT_TYPE).getText().shouldContain "Senior"
    }

    def verifyPageAndTitle(String expectedTitle = 'Southwest Airlines - Change Itinerary') {
        super.verifyPage()
        super.verifyPageTitle(expectedTitle)
    }

    def verifyAirInfoOnAirChangeModal() {
        AirReservation reservation = scenarioState.getLastAirReservation()
        ItineraryData reservationItinerary = reservation.getItineraryData()
        ChangeAirReservationModal modal = new ChangeAirReservationModal(waitForElement(CHANGE_AIR_RESERVATION_MODAL))
        modal.getAirFlightItinerary().shouldContain reservationItinerary.departureStation
        modal.getAirFlightItinerary().shouldContain reservationItinerary.arrivalStation
        modal.getAirFlightDates().shouldBe reservationItinerary.departureDate.format(DATE_FORMAT)
        modal.getAirConfirmationNumber().shouldBe reservation.getAdultPnr()
    }

    def verifyAssociatedHotelProductOnAirChangeModal() {
        ChangeAirReservationModal modal = new ChangeAirReservationModal(waitForElement(CHANGE_AIR_RESERVATION_MODAL))
        modal.getAssociatedProductsTitle().shouldBe ASSOCIATED_PRODUCTS_TITLE
        HotelReservation reservation = scenarioState.getLastAirReservation().hotelReservation
        String reservationDates = reservation.getCheckInDateAsString()+" - "+reservation.getCheckOutDateAsString()
        modal.getHotelName().shouldContain reservation.hotelName
        modal.getHotelDates().shouldBe reservationDates
        modal.getHotelConfirmationNumber().shouldBe reservation.confirmationNumber
    }

    def verifyAssociatedCarProductIsNotPresentOnAirChangeModal(){
        ChangeAirReservationModal modal = new ChangeAirReservationModal(waitForElement(CHANGE_AIR_RESERVATION_MODAL))
        isElementPresent(modal.CAR_PRODUCT).shouldBe false, "The car associated product should not be present"
    }


    def verifyRRnumber() {
        waitForElement(RR_NUMER).text.shouldContain rrAccountData.accountNumber, "The rr number in page was incorrect"
    }

    def verifyAirHeader() {
        isElementPresent(AIR_HEADER).shouldBe true, "The air header was not present"
    }

    def verifyPassengerName() {
        String nameOnPage = waitForElement(PASSENGER_NAME).text.toLowerCase()
        String firstName
        String lastName
        if(flow.isRapidRewards && !DynaStubsIntegration.useDynaStubs()) {
            firstName = rrAccountData.firstName
            lastName = rrAccountData.lastName
        } else {
            Passenger passenger  = scenarioState.getLastAirReservation().getPassengers().get(0)
            firstName = passenger.firstName
            lastName = passenger.lastName
        }
        nameOnPage.shouldContain firstName.toLowerCase(), "The first name was incorrect"
        nameOnPage.shouldContain lastName.toLowerCase(), "The last name was incorrect"
    }

    def verifyConfirmationNumber() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        waitForElement(CONFIRMATION_NUMER).text.shouldContain airReservation.adultPnr, "The confirmation number was not correct"
    }

    def verifySeniorFareIsNotChecked() {
        waitForElement(SENIOR_FARE_CHECKBOX).getAttribute("checked").shouldBe null, "Show senior fare checkbox was checked by default"
    }

    def verifyCancelChangeLinkIsPresent() {
        isElementPresent(CANCEL_CHANGE_BUTTON).shouldBe true, "Cancel changes link was not present"
    }

    def verifyFlightLogo() {
        isElementPresent(FLIGHT_LOGO).shouldBe true, "Flight logo was not present"
    }
}
