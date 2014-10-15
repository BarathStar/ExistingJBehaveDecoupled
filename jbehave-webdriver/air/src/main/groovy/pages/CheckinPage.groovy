package pages

import domain.AirReservation
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import pages.elements.StopLogicInfo
import state.Flow
import state.ScenarioState
import util.ItineraryData
import util.PageName
import util.RRContactInformation
import util.SelectFlightsPageData

import java.text.DateFormat
import java.text.SimpleDateFormat

import static com.thoughtworks.selenium.SeleneseTestBase.fail
import static util.Locators.BREADCRUMB_IDS
import domain.Passenger

class CheckinPage extends BasePage {

    private static final String PATH = "/flight/selectPrintDocument.html"
    private static final By UPDATE_RAPID_REWARDS_TEXT_FIELD = By.id("rrNumer")
    private static final By RAPID_REWARDS_UPDATE_BUTTON = By.className("submitButton")
    private static final By RAPID_REWARDS_UPDATE_INFO = By.className("RRNumberInfo")
    private static final By CONFIRMATION_NUMBER_FIELD = By.id("confirmationNumber")
    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By CANCEL_BUTTON = By.id("cancelButton")
    private static final By CHECKIN_BUTTON = By.id("printDocumentsButton")
    private static final By UPGRADE_TO_BUSINESS_SELECT_BUTTON = By.id("upgradeToBusinessSelect")
    private static final By UPDATE_OOPS_ELEMENT = By.id("errorFromAjaxCall")
    private static final By AIRTRAN_LINK = By.id("airTranLink")
    private static final By RAPID_REWARDS_HELP_LINK = By.id("checkin_account_login_form_field_rr_help_link")
    private static final By ADD_NUMBER_SIGNUP_LINK = By.className("checkInRRNumberButton")
    private static final By RAPID_REWARDS_MODAL = By.className("swa_feature_checkInRRNumber_modal")
    private static final By AIRTRAN_SEAT_SELECTION_BUTTON = By.id("seatSelectionButton")
    public static final String UPDATE_OOPS_XPATH = "//div[@id = 'errorFromAjaxCall' and not(@style = 'display:none')]"
    private static final String RAPID_REWARDS_NUMBER = "500321183"
    private static final String UPDATE_MESSAGE = "updated your reservation with your Rapid Rewards Number."
    private static final String UPDATE_OOPS_MESSAGE = "The Rapid Rewards Account Number(s) for the following Passenger(s) is/are invalid:"
    private static final String INVALID_RAPID_REWARDS_NUMBER = "12345"
    private static final By CHECK_IN_ONLINE_BREADCRUMB = By.className("swa_nav_globalNav_horizontal_span")
    private static final By LOOKUP_CONFIRMATION_LINK = By.cssSelector(".swa_feature_checkInOnline_lookupConfirmationNumber a")
    private static final By SELECT_PRINT_DOCUMENT_TITLE = By.id("page_title")
    private static final By RR_NUMBER_TEXT = By.className("swa_feature_checkIn_passenger_rapid_rewards_info")
    private static final By OUTBOUND_FLIGHT_DATE = By.className("swa_feature_checkIn_travelDate")
    private static final By OUTBOUND_DEPARTURE_TIME = By.cssSelector(".swa_tables_segmentItinerary_departRow_depart .swa_tables_segmentItinerary_time")
    private static final By OUTBOUND_ARRIVAL_TIMES = By.cssSelector(".swa_tables_segmentItinerary_arriveRow .swa_tables_segmentItinerary_time")
    private static final By OUTBOUND_CONNECTION_DEPARTURE_TIME = By.cssSelector(".swa_tables_segmentItinerary_departRow_change .swa_tables_segmentItinerary_time")
    private static final By OUTBOUND_DEPARTURE_CITY = By.cssSelector(".swa_tables_segmentItinerary_departRow_depart .swa_tables_segmentItinerary_station")
    private static final By OUTBOUND_ARRIVAL_CITIES = By.cssSelector(".swa_tables_segmentItinerary_arriveRow .swa_tables_segmentItinerary_station")
    private static final By OUTBOUND_CONNECTION_DEPARTURE_CITY = By.cssSelector(".swa_tables_segmentItinerary_departRow_change .swa_tables_segmentItinerary_station")
    private static final By OUTBOUND_FLIGHT_NUMBERS = By.cssSelector(".swa_tables_itinerary_depart .flightNumber")
    private static final By RR_NUMBER_DATA = By.className("RRNumberData")
    private static final String FOOTNOTE = "It’s not too late to get the seat you want. You could earn additional Rapid Rewards points per person when you upgrade."
    private static final By SPECIAL_POLICIES_CONTAINER = By.className("special_flight_policies")
    private static final By PASSENGER_FIRST_NAME = By.className("passenger_firstName")
    private static final By PASSENGER_LAST_NAME = By.className("passenger_lastName")
    private final title = "Southwest Airlines - Checkin Online and Print Boarding Passes"
    private static final By AIRTRAN_DISCLAIMER_ID = By.id("airtran_disclaimer")
    private static final By SEAT_SELECTION_UNAVAILABLE_MESSAGE_FIELD = By.className("seatSelectionUnavailableAlertMessage")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER = By.cssSelector(".swa_tables_segmentItinerary_departRow.swa_tables_segmentItinerary_departRow_change .swa_tables_segmentItinerary_route .swa_tables_segmentItinerary_routeDetail")
    private static final By CONTINUE_TO_GET_SECURITY_DOCUMENT = By.id("modalContinue");
    private static final By QUICK_AIR_CHECKIN = By.id("quick_air_checkin");
    private static final By SPECIAL_FLIGHT_POLICIES_LINKS = By.cssSelector(".special_flight_policies p a")

    ScenarioState scenarioState
    Flow flow
    StopLogicInfo stopLogicInfo
    ItineraryData itineraryData
    SelectFlightsPageData selectFlightsPageData
    RRContactInformation rrContactInformation

    String getRelativePath() {
        return PATH
    }

    public CheckinPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def retrieveReservationToCheckin(String pnr, String pnr_firstName, String pnr_lastName) {
        waitForElement(CONFIRMATION_NUMBER_FIELD).sendKeys(DELETE_EXISTING + pnr)
        waitForElement(FIRST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_firstName)
        waitForElement(LAST_NAME_FIELD).sendKeys(DELETE_EXISTING + pnr_lastName)
        waitForElement(SUBMIT_BUTTON).click()
    }

    void verifyInvalidPnrOopsMessage() {
        shouldShowOopsMessage("We were unable to retrieve your reservation from our database. Verify the following:")
        String oopsText =  getOopsElement().getText()
        oopsText.shouldNotContain "We're sorry,", "We're sorry text is still present at the top of the Oops message."
        oopsText.shouldNotContain ", airtran.com,", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldContain "Travel Agencies cannot be accessed on southwest.com.", "Third bullet in the Oops message still contains the airtran.com text."
        oopsText.shouldNotContain "Travel Agencies cannot be accessed online", "Third bullet text at end was NOT removed/changed"
        waitForElement(AIRTRAN_LINK).getText().shouldEqual "airtran.com"
    }

    def verifyCheckinLandingPage(){
        getTitle().equalsIgnoreCase(title).shouldBe true, "wrong boarding pass landing page should be ${title}"
    }

     void clickUpgradeToBusinessSelect() {
        waitForElement(UPGRADE_TO_BUSINESS_SELECT_BUTTON).click()
     }

    def enterRapidRewardsNumber(){
        enterRapidRewardsNumber(RAPID_REWARDS_NUMBER)
    }

    def clickContinueToSecurityDocument() {
        waitForElement(CONTINUE_TO_GET_SECURITY_DOCUMENT).click()
    }

    def checkRapidRewardsUpdateMessage() {
        waitForElement(RAPID_REWARDS_UPDATE_INFO).getText().shouldContain UPDATE_MESSAGE, "The update text for adding Rapid Rewards to an itinerary on the check-in page did not display as expected."
    }

    def enterRapidRewardsNumber(String rapidRewardsEntry) {
        WebElement rapidRewardsModal = waitForElement(RAPID_REWARDS_MODAL)
        rapidRewardsModal.findElement(UPDATE_RAPID_REWARDS_TEXT_FIELD).click()
        rapidRewardsModal.findElement(UPDATE_RAPID_REWARDS_TEXT_FIELD).sendKeys(rapidRewardsEntry)
        rapidRewardsModal.findElement(RAPID_REWARDS_UPDATE_BUTTON).click()
        while(rapidRewardsModal.isDisplayed()) {
            sleep(10)
        }
    }

    def enterInvalidRapidRewardsNumber() {
        enterRapidRewardsNumber(INVALID_RAPID_REWARDS_NUMBER)
    }

    def verifyUpdateOopsMessage() {
        waitForElement(UPDATE_OOPS_ELEMENT).getText().shouldContain "The Rapid Rewards Account Number(s) for the following Passenger(s) is/are invalid:", "The Oops message '${UPDATE_OOPS_MESSAGE}' has not been found with the text"
    }

    def selectPassengerAndCancel(){
           selectCheckBoxIfNotSelected()
           waitForElement(CANCEL_BUTTON).click()
       }

    def selectCheckBoxIfNotSelected() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        for (int i = 0; i < airReservation.passengers().size(); i++) {
            WebElement checkbox = waitForElement(By.id("checkinPassengers[${i}].selected1"))
            if (!checkbox.isSelected() && checkbox.isDisplayed()) {
                checkbox.click()
            }
        }
    }

    def verifyAirportCheckinRequiredText(String firstName, String lastName) {
        WebElement passengerinfo = waitForElement(By.className("swa_tables_itinerary_firstSegment"))
        List<WebElement> passengerContainers = passengerinfo.findElements(By.className("swa_feature_checkIn_passenger_container"))
        boolean verifiedPassenger = false

        passengerContainers.each() {
            String elementFirstName = it.findElement(By.className("passenger_firstName")).text.trim()
            String elementLastName = it.findElement(By.className("passenger_lastName")).text.trim()

            if (elementFirstName.equalsIgnoreCase(firstName) && elementLastName.equalsIgnoreCase(lastName)) {
                it.findElement(By.className("swa_feature_checkIn_passenger_documentType")).text.trim().shouldBe "Airport Checkin Required", "Misssing Airport Checkin Required message for " + firstName + " " + lastName
                verifiedPassenger = true
            }

        }
        return verifiedPassenger
    }

    def selectPrintDrinkCouponsIfNotSelected() {
        WebElement checkbox = waitForElement(By.id("printDrinkCoupons1"))
        checkbox.click()
        if (!checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def uncheckPrintDrinkCoupons() {
        WebElement checkbox = waitForElement(By.id("printDrinkCoupons1"))
        checkbox.click()
        if (checkbox.isSelected() && checkbox.isDisplayed()) {
            checkbox.click()
        }
    }

    def clickCheckinButton() {
        waitForElement(CHECKIN_BUTTON).click()
        WaitForPageToLoad
    }

    def clickHelpLink() {
        waitForElement(RAPID_REWARDS_MODAL).findElement(RAPID_REWARDS_HELP_LINK).click()
    }

    def verifyHelpPopupBox() {
        waitForElement(By.id("popup_help_box")).isDisplayed().shouldBe true, "Help popup box is not displayed"
    }

    void verifyPassengerName(String passengerFirstName, String passengerLastName) {
        List<WebElement> firstNamesOnPage = waitForElements(PASSENGER_FIRST_NAME)
        List<WebElement> lastNamesOnPage = waitForElements(PASSENGER_LAST_NAME)
        firstNamesOnPage[0].text.toUpperCase().shouldContain passengerFirstName.toUpperCase(), "Expected passenger name to be $passengerFirstName in checkin page"
        lastNamesOnPage[0].text.toUpperCase().shouldContain passengerLastName.toUpperCase(), "Expected passenger name to be $passengerLastName in checkin page"
        List<Passenger> passengers = scenarioState.getLastAirReservation().getPassengers()
        if (passengers.size()>1) {
            for (int i=1; i < passengers.size(); i++) {
                firstNamesOnPage[i].text.toUpperCase().shouldContain passengers.get(i).firstName.toUpperCase(), "Expected passenger name to be $passengerFirstName in checkin page"
                lastNamesOnPage[i].text.toUpperCase().shouldContain passengers.get(i).lastName.toUpperCase(), "Expected passenger name to be $passengerLastName in checkin page"
            }
        }
    }

    void verifyWCMSeatSelectionMessageOnCheckinPage() {
        waitForElement(By.className("seat_selection_before_checkIn_test")).text.shouldContain "You must choose seats before checking in. Once checked in a seat is assigned if not picked.",
                "Did not find WCM Seat Selectiong Message On Checkin Page"


    }

    def selectAddNumberSignup() {
        waitForElement(ADD_NUMBER_SIGNUP_LINK).click()
        WebElement modal = waitForElement(RAPID_REWARDS_MODAL)
        if (!modal.getAttribute("style").contains("block")) {
            fail "Rapid Reward Modal was not found "
        }
    }

    def verifyUpgradeToBusinessSelectButtonNotDisplayed() {
        verifyElementNotPresent("Upgrade to BS Button", UPGRADE_TO_BUSINESS_SELECT_BUTTON)
    }

    def verifyAirtranDisclaimerMessage(){
        waitForElement(AIRTRAN_DISCLAIMER_ID)
    }

    def verifySystemDownForAirTranFlightsCheckin() {
        shouldShowOopsMessage("Checkin is currently unavailable for AirTran operated flights because our system to retrieve AirTran itineraries is down")
    }

    def verifyCheckinButtonIsDisabled() {
        WebElement checkinButton = waitForElement(CHECKIN_BUTTON)
        if (checkinButton.isEnabled()) {
            fail "Checkin Button should be disabled"
        }
    }

    def verifyAirtranSeatSelectionButtonVisible() {
        isElementDisplayed(AIRTRAN_SEAT_SELECTION_BUTTON).shouldBe true, "No AirTran Seat Selection Button found"
    }

    def verifyAirtranSeatSelectionButtonNotDisplayed() {
        isElementDisplayed(AIRTRAN_SEAT_SELECTION_BUTTON).shouldBe false, "AirTran Seat Selection Button found"
    }

    def verifyChangePlaneInfoOnDepartureAirItineraryOnCheckInPage(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER, changePlaneCityCode)
    }

    def clickSeatSelectionButtonOnCheckInPageOnly() {
        waitForElement( AIRTRAN_SEAT_SELECTION_BUTTON ).click()
    }

    def verifySeatSelectionUnavailableVerbiage(String outBoundInBound) {
        sleep 3000
        WebElement element = waitForElement(SEAT_SELECTION_UNAVAILABLE_MESSAGE_FIELD)
        element.text.shouldContain "Seat selection unavailable"
        element.getAttribute('style').shouldNotContain "none"
    }

    def verifyOopsMessageUnableToCheckin() {
        def oopsMessage = [
                "Unable to check-in using this confirmation number. Please attempt check-in using the corresponding confirmation number."
        ]
        verifyOopsMessages(oopsMessage)
    }

    def verifyReservationMayHaveBeenCancelledOopsMessage() {
        def oopsMessage = [
                "Your trip cannot be found in our system and may have been cancelled. If this is the first time you have seen this message, please go back and try again. If you continue to have difficulties, please contact a Southwest Airlines Customer Representative for assistance at 1-800-I-FLY-SWA (1-800-435-9792)."
        ]
        verifyOopsMessages(oopsMessage)
    }

    def openCheckinPageInAnotherWindow() {
        flow.windowBefore = webDriver().getWindowHandle()
        String href = waitForElement(QUICK_AIR_CHECKIN).getAttribute("href")
        openInAnotherWindow(href)
        switchToOpenWindow(flow.windowBefore,'windowNewName')
    }

    def verifyCheckinOnlinePage() {
        verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify Checkin online page) ---- checking for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify Checkin online page) ---- checking for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.CHECKIN_ONLINE)
        waitForElement(CHECK_IN_ONLINE_BREADCRUMB).text.shouldBe "Check In Online", "Check In Online' breadcrumb was not present"
    }

    def verifyLookupConfirmationNumberLink() {
        isElementPresent(LOOKUP_CONFIRMATION_LINK).shouldBe true, "Lookup Confirmation Number Link was not present in the Checkin online page"
    }

    def verifySelectPrintDocumentPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify select print document page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify select print document purchase page) ---- waiting for fault injection"
        }
        System.getProperty("domainToTest") ? getCurrentUrl().shouldContain (System.getProperty("domainToTest")) : getCurrentUrl().shouldContain ("local.swacorp.com")
        verifyPageBreadcrumb(BREADCRUMB_IDS["SelectPrintDocumentPage"])
        pageVerificationErrorWrapper(CHECKIN_BUTTON, PageName.SELECT_PRINT_DOCUMENT)
        waitForElement(SELECT_PRINT_DOCUMENT_TITLE).text.shouldBe "Online Checkin", "Select Print Document page title did not match the expected value"
    }

    def verifyRapidRewardNumberText() {
        List<WebElement> rrText = waitForElements(RR_NUMBER_TEXT)
        List<WebElement> signupLink = waitForElements(ADD_NUMBER_SIGNUP_LINK, false)
        List<Passenger> passengers = scenarioState.getLastAirReservation().getPassengers()
        for (int i=0; i < passengers.size(); i++) {
            rrText[i].text.shouldContain "Rapid Rewards #", "Rapid Rewards # text was not present in checkin page"
            if(!flow.isRapidRewards) {
                signupLink[i].text.shouldContain "Add Number/Signup", "Add number/signup link was not present in checkin page"
            }
        }
    }

    def verifyDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy")
        waitForElement(OUTBOUND_FLIGHT_DATE).text.shouldBe dateFormat.format(itineraryData.departureDate), "Outbound flight date did not match the expected value in checkin page"
    }

    def verifyDepartureAndArrivalTime() {
        String outboundDepartureTime = waitForElement(OUTBOUND_DEPARTURE_TIME).text
        List<WebElement> outboundArrivalTimes = waitForElements(OUTBOUND_ARRIVAL_TIMES)
        outboundDepartureTime.replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundDepartureTime.toUpperCase(), "Outbound flight departure time did not match flight time on Bug Page"
        outboundArrivalTimes[0].text.replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundArrivalTime.toUpperCase(), "Outbound flight arrival time did not match flight time on Bug Page"
        if (itineraryData.outboundConnectingStation) {
            String outboundConnectionDepartureTime = waitForElement(OUTBOUND_CONNECTION_DEPARTURE_TIME).text
            outboundConnectionDepartureTime.replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundConnectionDepartureTime.toUpperCase(), "Outbound connection flight departure time did not match flight time on Bug Page"
            outboundArrivalTimes[1].text.replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundConnectionArrivalTime.toUpperCase(), "Outbound connection flight arrival time did not match flight time on Bug Page"
        }
    }

    def verifyDepartureAndArrivalCities() {
        String outboundDepartureCity = waitForElement(OUTBOUND_DEPARTURE_CITY).text
        List<WebElement> outboundArrivalCities = waitForElements(OUTBOUND_ARRIVAL_CITIES)
        outboundDepartureCity.shouldContain itineraryData.departureCity, "Outbound flight departure city did not match flight city on Bug Page"
        if (itineraryData.outboundConnectingStation) {
            String outboundConnectionDepartureCity = waitForElement(OUTBOUND_CONNECTION_DEPARTURE_CITY).text
            outboundConnectionDepartureCity.shouldContain itineraryData.outboundConnectingStation, "Outbound connection flight departure city did not match flight city on Bug Page"
            outboundArrivalCities[1].text.shouldContain itineraryData.returnCity, "Outbound connection flight arrival city did not match flight city on Bug Page"
        } else {
            outboundArrivalCities[0].text.shouldContain itineraryData.returnCity, "Outbound flight arrival city did not match flight City on Bug Page"
        }
    }

    def verifyFlightNumber() {
        List<WebElement> outboundFlightNumbers = waitForElements(OUTBOUND_FLIGHT_NUMBERS)
        outboundFlightNumbers[0].text.shouldContain selectFlightsPageData.departingFlight_number, "Outbound flight number did not match flight number from the Bug page"
        if (flow.hasConnectionFlight) {
            outboundFlightNumbers[1].text.shouldContain selectFlightsPageData.departingConnectingFlight_number.replace("#", ""), "Outbound connection flight number did not match flight number from the Bug page"
        }
    }

    def verifyUpgradeToBusinessSelectButtonDisplayed() {
        waitForElement(UPGRADE_TO_BUSINESS_SELECT_BUTTON, false).shouldNotBe null, "Upgrade to business select button was not displayed"
    }

    def verifyCancelButtonDisplayed() {
        waitForElement(CANCEL_BUTTON, false).shouldNotBe null, "Cancel button was not displayed"
    }

    def clickOnAssistantAnimalsLink(){
        List<WebElement> specialPoliciesLinks = waitForElements(SPECIAL_FLIGHT_POLICIES_LINKS)
        if(specialPoliciesLinks!=null && specialPoliciesLinks.size() > 2){
            WebElement assistantAnimalsLink = specialPoliciesLinks.get(2)
            assistantAnimalsLink.click()
        }else{
            fail("The element Assistant Animal on Chechin Page is not display " + SPECIAL_FLIGHT_POLICIES_LINKS)
        }
    }

    def verifyRRAccountNumber() {
        waitForElement(RR_NUMBER_DATA).text.shouldContain rrContactInformation.accountNumber, "RR account number did not match the expected value"
    }


    def verifyAdditionalDocumentation() {
        waitForElement(SPECIAL_POLICIES_CONTAINER).text.split('\n')[0].shouldBe "Additional documentation required for:", "'Additional documentation required for:' was not displayed in checkin page"
        List<WebElement> specialFlightPolicies = waitForElements(SPECIAL_FLIGHT_POLICIES_LINKS)
        String infantLink = specialFlightPolicies[0].text
        String portableOxygenLink = specialFlightPolicies[1].text
        String animalAssistance = specialFlightPolicies[2].text
        infantLink.shouldBe "Infants 0-2 years old","'Infants 0-2 years old' was not present in checkin page"
        portableOxygenLink.shouldBe "Portable Oxygen Concentrators","'Portable Oxygen Concentrators' was not present in checkin page"
        animalAssistance.shouldBe "Assistance Animals","'Assistance Animals' was not present in checkin page"
    }
}


