package pages

import domain.AirReservation
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import state.Flow
import util.ItineraryData
import util.RRContactInformation
import util.SelectFlightsPageData

import java.text.DateFormat
import java.text.SimpleDateFormat

import static org.junit.Assert.fail
import state.ScenarioState

class TravelDocumentsPage extends BasePage {
    private static final By PRINT_BUTTON = By.id("printButton")
    private static final By BAR_CODE = By.className("barcode")
    private static final By DRINK_COUPON_BAR_CODE = By.xpath("//img[@class= 'drinkCouponBarCode']")
    private static final By SOUTHWEST_PNR = By.className("pnr")
    private static final By AIRTRAN_PNR = By.className("confirmationNumber")
    private static final By AIRTRAN_PASSENGER_FULL_NAME = By.className("passenger")
    private static final By PASSENGER_LAST_NAME = By.className("passengerLastName")
    private static final By PASSENGER_FIRST_NAME = By.className("passengerFirstName")
    private static final By SOUTHWEST_IMAGE = By.className("issued-valid-swa")
    private static final By MEMBER_STATUS = By.xpath("//img[@alt= 'A-List Preferred']")
    private static final By DOC_TYPE = By.className("docType")
    private static final By RR_NUMBER = By.className("rapidRewards")
    private static final By TSA_PRE = By.className("tsa-pre-text")
    private static final By MOBILE_BOARDING_PASS_DELIVERY_MESSAGE = By.id("mobileBoardingDeliveryMessage")
    private static final By MOBILE_BOARDING_PASS_DELIVERY_FAILURE_MESSAGE = By.id("mobileBoardingPassDeliveryFailureMessage")
    private static final By MOBILE_BOARDING_PASS_DELIVERY_SUCCESS_MESSAGE_EMAIL_PHONE = By.id("mobileBoardingDeliverySuccessMessageEmailPhone")
    private static final By MOBILE_BOARDING_PASS_DELIVERY_SUCCESS_MESSAGE_EMAIL = By.id("mobileBoardingDeliverySuccessMessageEmail")
    private static final By MOBILE_BOARDING_PASS_DELIVERY_SUCCESS_MESSAGE_PHONE = By.id("mobileBoardingDeliverySuccessMessagePhone")
    private static final By PASSENGER_NAME = By.className("name")
    private static final By FLIGHT_NUMBER = By.className("flight")
    private static final By FLIGHT_NUMBER_DIV = By.cssSelector("div")
    private static final By ITINERARIES = By.className("iten")
    private static final By BOARDING_INFORMATION = By.className("boardingPosition")
    private static final By BOARDING_INFORMATION_CONTAINER = By.cssSelector("p")
    private static final By BOARDING_GROUP = By.className("group")
    private static final By BOARDING_POSITION = By.className("position")
    private static final By BOARDING_TIME = By.id("boardingTimeValue")
    private static final By BOTTOM_PNR = By.className("bottom_pnr")
    private static final By PASSENGER_NAME_CONTAINER = By.className("fullPassengerName")
    private static final By PASSENGER_FULL_NAME = By.cssSelector("li")
    private static final By LINKS_CONTAINER = By.className("link")
    private static final By BOARDING_PASS_LINK = By.cssSelector("a")
    private static final By RETURN_BUTTON = By.id("returnToPreviousScreen")
    private static final By ALERT_MESSAGE = By.id("gateInformation")
    private static final By CONFIRMATION_NUMBER = By.className("pnr_value")
    private static final By SPECIAL_CONDITIONS = By.className("specialConditions")
    private static final By DISABILITIES_CWC = By.xpath("//div[3]//ul/li[text()='CWC']")
    private static final By DISABILITIES_POC = By.xpath("//div[3]//ul/li[text()='POC']")

    util.Navigation navigation
    Flow flow
    SelectFlightsPageData selectFlightsPageData
    ItineraryData itineraryData
    RRContactInformation rrContactInformation
    ScenarioState scenarioState

    def TravelDocumentsPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/retrieveCheckinDoc.html')
    }

    String getRelativePath() {
        return ""  //To change body of implemented methods use File | Settings | File Templates.
    }

    private verifyAirTranBoardingPassPnrNameAndBarCode(AirReservation airReservation, String passengerType = "adult") {
        Passenger passenger
        if (passengerType == "senior") {
            passenger = airReservation.getSeniorPassengers().get(0)
            waitForElement(AIRTRAN_PNR).getText().shouldBe airReservation.seniorPnr
        } else {
            passenger = airReservation.getAdultPassengers().get(0)
            waitForElement(AIRTRAN_PNR).getText().shouldBe airReservation.adultPnr
    }

        String expectedFirstName = passenger.firstName.toUpperCase()
        String expectedLastName = passenger.lastName.toUpperCase()

        String boardingPassFirstName = waitForElement(AIRTRAN_PASSENGER_FULL_NAME).getText().split()[0].toUpperCase()
        boardingPassFirstName.shouldBe expectedFirstName, "First name of passenger did not match on the boarding pass!"
        String boardingPassLastName = waitForElement(AIRTRAN_PASSENGER_FULL_NAME).getText().split()[1].toUpperCase()
        boardingPassLastName.shouldBe expectedLastName, "Last name of passenger did not match on the boarding pass!"

        waitForElement(BAR_CODE).isDisplayed().shouldBe true, "Bar code was not displayed on the boarding pass!"
    }


    private verifySouthWestBoardingPassPnrNameAndBarCode(AirReservation airReservation, String passengerType) {
        Passenger passenger = passengerHasBoardingPass(airReservation, passengerType, 0)

        verifyNameInBoardingPass(passenger)
        waitForElement(BAR_CODE).isDisplayed().shouldBe true
    }


    private verifySouthWestBoardingPassesPnrNameAndBarCode(AirReservation airReservation, String passengerType) {
        Passenger passenger

        for (int i; i< airReservation.getPassengers().size(); i++) {
            passenger = passengerHasBoardingPass(airReservation, passengerType, i)
            verifyNameInBoardingPass(passenger, i)
        }
        List<WebElement> barCodes = waitForElements(BAR_CODE, false)
        int expectedBoardingPasses = airReservation.getPassengers().size()
        if(flow.hasConnectionFlight) {
            expectedBoardingPasses = expectedBoardingPasses * 2
        }

        def today = new Date()
        today.plus(1)
        today.setHours(today.getHours()-1)
        if(!itineraryData.oneWay && itineraryData.returnDate.before(today.plus(1))) {
            expectedBoardingPasses = expectedBoardingPasses * 2
        }
        barCodes.size().shouldEqual expectedBoardingPasses, "There were missing bar codes in the boarding pass"
    }

    private Passenger passengerHasBoardingPass(AirReservation airReservation, String passengerType, int passengerIndex) {
        Passenger passenger
        if (passengerType == "senior") {
            passenger = airReservation.getSeniorPassengers().get(passengerIndex)
            waitForElement(SOUTHWEST_PNR).getText().replaceAll('\\n','').shouldBe "CONF.#" + airReservation.seniorPnr
            waitForElement(DOC_TYPE).getText().shouldBe "SECURITY DOCUMENT"
        } else {
            passenger = airReservation.getAdultPassengers().get(passengerIndex)
            List<WebElement> adultPnrs = waitForElements(SOUTHWEST_PNR)
            adultPnrs[0].text.replaceAll('\\n','').shouldBe "CONF.#" + airReservation.adultPnr, "Outbound boarding pass PNR number did not match the expected value"
            if (flow.hasConnectionFlight) {
                adultPnrs[1].text.replaceAll('\\n','').shouldBe "CONF.#" + airReservation.adultPnr, "Outbound connection boarding pass PNR number did not match the expected value"
            }
        }
        return passenger;
    }

    private verifyAListPreferredMemberStatus() {
        waitForElement(MEMBER_STATUS).isDisplayed().shouldBe true
    }

    private verifySouthWestSecurityDocumentPnrNameAndBarCode(AirReservation airReservation) {
        waitForElement(DOC_TYPE).getText().shouldBe "SECURITY DOCUMENT"
        verifyNameInSecurityDocument(airReservation.getAdultPassengers().get(0))
        waitForElement(BAR_CODE).isDisplayed().shouldBe true
    }

    def verifyMobileBoardingPassDeliveredMessage(boolean displayed) {
        isElementPresent(MOBILE_BOARDING_PASS_DELIVERY_MESSAGE, 10).shouldBe displayed
    }

    def verifyMobileBoardingPassDeliveryFailureMessage(boolean displayed) {
        isElementPresent(MOBILE_BOARDING_PASS_DELIVERY_FAILURE_MESSAGE, 10).shouldBe displayed
    }

    def verifyDrinkCouponBarCode(boolean displayed) {
        isElementPresent(DRINK_COUPON_BAR_CODE, 10).shouldBe displayed
    }

    private void verifyNameInBoardingPass(Passenger passenger, int index = 0) {
        String expectedFirstName
        String expectedLastName
        if (flow.isRapidRewards && !DynaStubsIntegration.useDynaStubs()) {
            expectedFirstName = rrContactInformation.firstName.toUpperCase()
            expectedLastName = rrContactInformation.lastName.toUpperCase()
        } else {
            expectedFirstName = passenger.firstName.toUpperCase()
            expectedLastName = passenger.lastName.toUpperCase()
        }
        List<WebElement> passengerName = waitForElements(PASSENGER_NAME)
        passengerName[index].findElement(PASSENGER_FIRST_NAME).getText().shouldBe expectedFirstName, "Outbound boarding pass first name did not match the expected value in view checkin document page"
        passengerName[index].findElement(PASSENGER_LAST_NAME).getText().shouldBe expectedLastName, "Outbound boarding pass last name did not match the expected value in view checkin document page"
        if (flow.hasConnectionFlight) {
            passengerName[index + 1].findElement(PASSENGER_FIRST_NAME).getText().shouldBe expectedFirstName, "Outbound connection boarding pass first name did not match the expected value in view checkin document page"
            passengerName[index + 1].findElement(PASSENGER_LAST_NAME).getText().shouldBe expectedLastName, "Outbound connection boarding pass last name did not match the expected value in view checkin document page"
        }
    }

    // todo - If we can change the style in Security Document, to match the styles used on Boarding pass
    // we will be able to get rid of this function and use the one above
    private void verifyNameInSecurityDocument(Passenger passenger) {
        String expectedFirstName = passenger.firstName.toUpperCase()
        String expectedLastName = passenger.lastName.toUpperCase()

        waitForElement(By.className("passenger_first_name")).getText().shouldBe expectedFirstName
        waitForElement(By.className("passenger_last_name")).getText().shouldBe expectedLastName
    }

    def verifySouthWestBoardingPass(AirReservation airReservation) {
        verifySouthWestBoardingPassPnrNameAndBarCode(airReservation, "adult")
    }

    def verifySouthWestBoardingPasses(AirReservation airReservation) {
        verifySouthWestBoardingPassesPnrNameAndBarCode(airReservation, "adult")
    }

    def verifyAirTranBoardingPass(AirReservation airReservation) {
        verifyAirTranBoardingPassPnrNameAndBarCode(airReservation, "adult")
    }

    def verifySouthWestSeniorBoardingPass(AirReservation airReservation) {
        verifySouthWestBoardingPassesPnrNameAndBarCode(airReservation, "senior")
    }

    def verifyAirTranSeniorBoardingPass(AirReservation airReservation) {
        verifyAirTranBoardingPassPnrNameAndBarCode(airReservation, "senior")
    }

    def verifySouthWestAListPreferredBoardingPass(AirReservation airReservation) {
        verifySouthWestBoardingPass(airReservation)
        verifyAListPreferredMemberStatus()
    }

    def verifyAirTranAListPreferredBoardingPass(AirReservation airReservation) {
        verifyAirTranBoardingPass(airReservation)
        verifyAListPreferredMemberStatus()
    }

    def verifySouthWestSecurityDocument(AirReservation airReservation) {
        verifySouthWestSecurityDocumentPnrNameAndBarCode(airReservation)
    }

    def verifyTravelDocumentsSouthWest(int numberOfDocuments, String travelDocuments) {
        def foundTravelDocuments = findTravelDocuments(travelDocuments).size()
        if (foundTravelDocuments >= numberOfDocuments) {
            southwestBoardingPassExists()
        } else {
            fail("did not find $numberOfDocuments $travelDocuments, it found $foundTravelDocuments")
        }
    }

    def verifyBoardingPassShells(int numberOfSouthwestShells, int numberOfAirtranShells) {
        def foundSouthwestShells = findSouthwestBoardingPassShells().size()
        def foundAirTranShells = findAirTranBoardingPassShells().size()
        if ((foundSouthwestShells != numberOfSouthwestShells) || (foundAirTranShells != numberOfAirtranShells)) {
            fail("did not find $numberOfSouthwestShells southwest and $numberOfAirtranShells boarding pass shells, it found $foundSouthwestShells southwest and $foundAirTranShells airtran shells.")
        }
    }

    void verifyBoardingPassForSouthWestAndAirTran() {
        verifyElementPresent("Southwest Boarding Pass", By.className('checkinDocument'))
        verifyElementPresent("AirTran Boarding Pass", By.id('boardingPassDocument'))
    }

    def printSecurityDocument() {
        if (findElement(By.xpath("//div[@class = 'checkinDocument esd']"))) {
            //it's a sd
            System.out.println("--> " + "Security Document ticket number " + waitForElement(By.xpath("//div[@class='checkinDocument esd']//p[@class='ticketNumber']")).getText())
        }
    }

    def printDrinkCoupon() {
        def drinkCoupon = waitForElement(By.xpath("//div[@class = 'drinkCoupon']"))
        if (drinkCoupon) {
            //it's a drink coupon
            System.out.println("--> " + "Drink coupon title " + waitForElement(By.xpath("//div[@class='drinkCoupon']/div[@class='drinkCouponTitle']")).getText())
            System.out.println("--> " + "Drink coupon subtitle " + waitForElement(By.xpath("//div[@class='drinkCoupon']/div[@class='drinkCouponSubTitle']")).getText())
            System.out.println("--> " + "Drink coupon info " + waitForElement(By.xpath("//div[@class='drinkCoupon']/div[@class='drinkCouponInfo1']")).getText())
            System.out.println("--> " + "Drink coupon pnr info " + waitForElement(By.xpath("//div[@class='drinkCoupon']/div[@class='drinkCouponPnrInfo']")).getText())
        }
    }

    def verifyInfantSpecialConditionSouthwest() {
       verifySpecialCondition("IN")
    }

    def verifyInfantSpecialConditionAirTran() {
        verifySpecialCondition("INFT")
    }

    private def verifySpecialCondition(String specialCondition) {
        List<WebElement> specialConditions = findElements(SPECIAL_CONDITIONS)
        specialConditions.each() {
            String text = it.getText();
            it.getText().shouldBe specialCondition
        }
    }

    private WebElement getCheckBox(passengerIndex) {
        By checkBox = By.xpath("//*[@id='passenger_" + passengerIndex + "']//input[1]")
        return waitForElement(checkBox)
    }

    private List<WebElement> findTravelDocuments(String travelDocument) {
        List<WebElement> travelDocuments = waitForElements(By.className("docType"))
        travelDocuments.findAll { travelDoc ->
            travelDoc.text.equals(travelDocument.toUpperCase())
        }
        return travelDocuments
    }

    private List<WebElement> findAirTranBoardingPassShells() {
        def List<WebElement> boardingPassShells = waitForElements(By.id("boardingPassNotIssued"))
        return boardingPassShells.findAll {
            it.getAttribute("innerHTML").contains("AirTran boarding pass unavailable at this time")
        }
    }

    private List<WebElement> findSouthwestBoardingPassShells() {
        def List<WebElement> boardingPassShells = waitForElements(By.id("boardingPassNotIssued"))
        return boardingPassShells.findAll {
            it.getAttribute("innerHTML").contains("Southwest boarding pass unavailable at this time")
        }
    }

    private southwestBoardingPassExists(){
        waitForElement(SOUTHWEST_IMAGE).getAttribute("alt")
                .shouldEqual "Issued and valid only on Southwest Airlines", "Did not find Southwest BoardingPass"
    }

    public void returnToCheckinPageFromBoardingPassesPrintPage() {
        waitForElement(By.id("returnToPreviousScreen")).click()
    }

    def isRRNumberDisplayed(String rrNumber){
        waitForElement(RR_NUMBER).getText().contains(rrNumber).shouldBe true, "The RR Number is different or null"
    }

    def isTSAPreCheckDisplayed(){
        waitForElement(TSA_PRE).getText().contains("TSA PRE").shouldBe true, "The TSA PRE text is different or null"
    }

    def isNotTSAPreCheckDisplayed(){
        waitForElement(TSA_PRE).getText().contains("TSA PRE").shouldBe false, "The TSA PRE text is visible but should not be"
    }

    def verifyPrintButton() {
        isElementPresent(PRINT_BUTTON).shouldBe true, "Print button was not present in the view checkin document page"
    }

    def verifyCheckinDocumentHeading() {
        takeScreenShot()
        List<WebElement> checkinDocumentHeadings = waitForElements(By.className("checkinDocumentMessage"))
        List<Passenger> passengers = scenarioState.getLastAirReservation().getPassengers()
        for (int i = 0; i < passengers.size(); i++){
            checkinDocumentHeadings[i].text.shouldBe "Southwest Airlines Boarding Pass", "Outbound boarding pass header was not match the excepted one in view checkin document page"
        }
        if (flow.hasConnectionFlight) {
            checkinDocumentHeadings[1].text.shouldBe "Southwest Airlines Boarding Pass", "Outbound connection boarding pass header was not match the excepted one in view checkin document page"
        }

    }

    def verifyBoardingPassDocType() {
        List<WebElement> docTypes = waitForElements(DOC_TYPE)
        for (int i = 0; i < scenarioState.getLastAirReservation().getPassengers().size(); i++){
            docTypes[i].text.shouldBe "BOARDING PASS", "Outbound boarding pass Doc type was not match the expected one in view checkin document page"
        }
        if (flow.hasConnectionFlight) {
            docTypes[1].text.shouldBe "BOARDING PASS", "Outbound connection boarding pass Doc type was not match the expected one in view checkin document page"
        }
    }

    def verifyFlightNumbers() {
        List<WebElement> flightNumbers = waitForElements(FLIGHT_NUMBER)
        List<Passenger> passengers = scenarioState.getLastAirReservation().getPassengers()
        for (int i = 0; i < passengers.size(); i++) {
            flightNumbers[i].text.replaceAll('\\n','').shouldBe "FLIGHT" + selectFlightsPageData.departingFlight_number, "Outbound flight number did not match flight number from the Bug page in view checkin document page"
        }
        if (flow.hasConnectionFlight) {
            flightNumbers[1].text.replaceAll('\\n','').shouldBe "FLIGHT" +  selectFlightsPageData.departingConnectingFlight_number.replaceAll("#", ""), "Outbound connection flight number did not match flight number from the Bug page in view checkin document page"
        }
    }

    def verifyDates() {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd")
        List<WebElement> outboundDepartDates = waitForElements(By.className("depart_date"))
        for (int i = 0; i < scenarioState.getLastAirReservation().passengers.size(); i++) {
            outboundDepartDates[0].text.shouldBe dateFormat.format(itineraryData.departureDate).toUpperCase(), "Outbound flight date did not match the expected value in view checkin document page"
        }
        if (flow.hasConnectionFlight) {
            outboundDepartDates[1].text.shouldBe dateFormat.format(itineraryData.departureDate).toUpperCase(), "Outbound connection flight date did not match the expected value in view checkin document page"
        }
    }

    def verifyAirportDetails() {
        List<WebElement> itineraries = waitForElements(ITINERARIES)
        String outboundDepartCity
        String outboundArriveCity
        String outboundDepartTime
        for (int i = 0; i < scenarioState.getLastAirReservation().passengers.size(); i++) {
            WebElement outboundDepartItinerary = itineraries[i]
            outboundDepartItinerary.findElement(FLIGHT_NUMBER_DIV).text.shouldBe selectFlightsPageData.departingFlight_number, "Outbound airport details flight number did not match the expected value in view checkin document page"
            List<String> outboundDepartAirportDetails = itineraries[i].findElement(By.className("airport")).text.split("\n")
            outboundDepartCity = outboundDepartAirportDetails[0]
            outboundArriveCity = outboundDepartAirportDetails[1]
            outboundDepartTime = outboundDepartAirportDetails[2]
            itineraryData.departureCity.toUpperCase().shouldContain outboundDepartCity, "Outbound airport details departure city did not match flight city on Bug Page in view checkin document page"
            outboundDepartTime.replaceAll("\\s", "").replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundDepartureTime.toUpperCase(), "Outbound flight departure time did not match flight time on Bug Page in view checkin document page"
            if (itineraryData.isPromoCertBooking()){
                outboundDepartTime.shouldContain "X", "Fare type did not match the expected value"
            } else {
                outboundDepartTime.shouldContain "Y", "Fare type did not match the expected value"
            }
            if(!flow.hasConnectionFlight) {
                outboundArriveCity.shouldContain itineraryData.arrivalCity.split(",")[0].toUpperCase(), "Outbound airport details arrive city did not match flight city on Bug Page in view checkin document page"
            }
        }

        if (flow.hasConnectionFlight) {
            selectFlightsPageData.outboundConnectingCity.toUpperCase().shouldContain outboundArriveCity, "Outbound connection airport details arrive city did not match flight city on Bug Page in view checkin document page"

            WebElement outboundConnectionItinerary = itineraries[1]
            outboundConnectionItinerary.findElement(FLIGHT_NUMBER_DIV).text.shouldBe selectFlightsPageData.departingConnectingFlight_number.replace("#",""), "Outbound connection airport details flight number did not match the expected value in view checkin document page"
            List<String> outboundConnectionAirportDetails = outboundConnectionItinerary.findElement(By.className("airport")).text.split("\n")
            String outboundConnectionDepartCity = outboundConnectionAirportDetails[0]
            String outboundConnectionArriveCity = outboundConnectionAirportDetails[1]
            String outboundConnectionDepartTime = outboundConnectionAirportDetails[2]
            selectFlightsPageData.outboundConnectingCity.toUpperCase().shouldContain outboundConnectionDepartCity, "Outbound connection airport details city did not match flight city on Bug Page in view checkin document page"
            itineraryData.arrivalCity.toUpperCase().shouldContain outboundConnectionArriveCity, "Outbound connection airport details arrive city did not match flight city on Bug Page in view checkin document page"
            outboundConnectionDepartTime.replaceAll("\\s", "").replaceFirst(/^0+/, "").shouldBe selectFlightsPageData.outboundConnectionDepartureTime.toUpperCase(), "Outbound connection flight arrive time did not match flight time on Bug Page in view checkin document page"

            outboundConnectionItinerary = itineraries[2]
            outboundConnectionItinerary.findElement(FLIGHT_NUMBER_DIV).text.shouldBe selectFlightsPageData.departingConnectingFlight_number.replace("#",""), "Outbound connection airport details flight number did not match the expected value in view checkin document page"
            outboundConnectionAirportDetails = outboundConnectionItinerary.findElement(By.className("airport")).text.split("\n")
            outboundConnectionDepartCity = outboundConnectionAirportDetails[0]
            outboundConnectionArriveCity = outboundConnectionAirportDetails[1]
            outboundConnectionDepartTime = outboundConnectionAirportDetails[2]
            selectFlightsPageData.outboundConnectingCity.toUpperCase().shouldContain outboundConnectionDepartCity, "Outbound connection airport details city did not match flight city on Bug Page in view checkin document page"
            itineraryData.arrivalCity.split(",")[0].toUpperCase().shouldBe outboundConnectionArriveCity, "Outbound connection airport details arrive city did not match flight city on Bug Page in view checkin document page"
            outboundConnectionDepartTime.replaceAll("\\s", "").replaceFirst(/^0+/, "").shouldContain selectFlightsPageData.outboundConnectionDepartureTime.toUpperCase(), "Outbound connection flight arrive time did not match flight time on Bug Page in view checkin document page"
            outboundConnectionDepartTime.shouldContain "Y", "Fare type did not match the expected value in view checkin document page"
        }
    }

    def verifyBoardingGroupAndPosition() {
        List<WebElement> boardingInformation = waitForElements(BOARDING_INFORMATION)
        for (int i = 0; i < scenarioState.getLastAirReservation().passengers.size(); i++) {
            List<WebElement> outboundDepartBoardingInformation = boardingInformation[i].findElements(BOARDING_INFORMATION_CONTAINER)
            String outboundDepartBoardingGroup = outboundDepartBoardingInformation[0].text
            String outboundDepartBoardingPosition = outboundDepartBoardingInformation[1].text
            outboundDepartBoardingGroup.replace('\n','').shouldBe "BoardingGroup", "Boarding group was not displayed in view checkin document page"
            outboundDepartBoardingPosition.replace('\n','').shouldBe "BoardingPosition", "Boarding position was not displayed in view checkin document page"
            verifyElementPresent("Boarding Group",boardingInformation[i].findElement(BOARDING_GROUP))
            verifyElementPresent("Boarding Position",boardingInformation[i].findElement(BOARDING_POSITION))
        }

        if(flow.hasConnectionFlight) {
            List<WebElement> outboundConnectionDepartBoardingInformation = boardingInformation[1].findElements(BOARDING_INFORMATION_CONTAINER)
            String outboundConnectionDepartBoardingGroup = outboundConnectionDepartBoardingInformation[0].text
            String outboundConnectionDepartBoardingPosition = outboundConnectionDepartBoardingInformation[1].text
            outboundConnectionDepartBoardingGroup.replace('\n','').shouldBe "BoardingGroup", "Boarding group was not displayed in view checkin document page"
            outboundConnectionDepartBoardingPosition.replace('\n','').shouldBe "BoardingPosition", "Boarding position was not displayed in view checkin document page"
            verifyElementPresent("Boarding Group",boardingInformation[1].findElement(BOARDING_GROUP))
            verifyElementPresent("Boarding Position",boardingInformation[1].findElement(BOARDING_POSITION))
        }
    }

    def verifyBottomBoardingPass(AirReservation airReservation) {
        List<WebElement> pnrNumbers = waitForElements(BOTTOM_PNR)
        String outboundDepartPnr = pnrNumbers[0].text

        List<WebElement> passengerName = waitForElements(PASSENGER_NAME_CONTAINER)
        List<WebElement> outboundDepartPassengerName = passengerName[0].findElements(PASSENGER_FULL_NAME)
        String outboundDepartLastName = outboundDepartPassengerName[0].text
        String outboundDepartFirstName = outboundDepartPassengerName[1].text
        String outboundDepartMiddleName = outboundDepartPassengerName[2].text

        String expectedFirstName
        String expectedLastName
        String expectedMiddleName
        Passenger passenger

        if (flow.isRapidRewards) {
            expectedFirstName = rrContactInformation.firstName.toUpperCase()
            expectedLastName = rrContactInformation.lastName.toUpperCase()
            expectedMiddleName = rrContactInformation.middleName.toUpperCase()
        } else {
            passenger = airReservation.adultPassengers[0]
            expectedFirstName = passenger.firstName.toUpperCase()
            expectedLastName = passenger.lastName.toUpperCase()
            expectedMiddleName = passenger.middleName.toUpperCase()
        }

        if (airReservation.containsAdultPassenger()) {
            outboundDepartPnr.shouldBe airReservation.adultPnr, "Adult PNR did not match the expected value in view checkin document page"
            outboundDepartLastName.shouldContain expectedLastName, "Passenger last name did not match the expected value in view checkin document page"
            outboundDepartFirstName.shouldContain expectedFirstName, "Passenger first name did not match the expected value in view checkin document page"
            outboundDepartMiddleName.shouldContain expectedMiddleName, "Passenger middle name did not match the expected value in view checkin document page"
            if(airReservation.passengers.size() > 1) {
                for (int i = 1 ; i < airReservation.passengers.size(); i++) {
                    String outboundConnectionDepartPnr = pnrNumbers[i].text
                    outboundConnectionDepartPnr.shouldBe airReservation.adultPnr, "Adult PNR did not match the expected value in view checkin document page"

                    List<WebElement> outboundConnectionDepartPassengerName = passengerName[i].findElements(PASSENGER_FULL_NAME)

                    passenger = airReservation.adultPassengers[i]
                    expectedFirstName = passenger.firstName.toUpperCase()
                    expectedLastName = passenger.lastName.toUpperCase()
                    expectedMiddleName = passenger.middleName.toUpperCase()

                    String outboundConnectionDepartLastName = outboundConnectionDepartPassengerName[0].text
                    String outboundConnectionDepartFirstName = outboundConnectionDepartPassengerName[1].text
                    String outboundConnectionDepartMiddleName = outboundConnectionDepartPassengerName[2].text
                    outboundConnectionDepartLastName.shouldContain expectedLastName, "Passenger last name did not match the expected value in view checkin document page"
                    outboundConnectionDepartFirstName.shouldContain expectedFirstName, "Passenger first name did not match the expected value in view checkin document page"
                    outboundConnectionDepartMiddleName.shouldContain expectedMiddleName, "Passenger middle name did not match the expected value in view checkin document page"
                }

            }
            if (flow.hasConnectionFlight) {
                String outboundConnectionDepartPnr = pnrNumbers[1].text
                outboundConnectionDepartPnr.shouldBe airReservation.adultPnr, "Adult PNR did not match the expected value in view checkin document page"

                List<WebElement> outboundConnectionDepartPassengerName = passengerName[1].findElements(PASSENGER_FULL_NAME)
                String outboundConnectionDepartLastName = outboundConnectionDepartPassengerName[0].text
                String outboundConnectionDepartFirstName = outboundConnectionDepartPassengerName[1].text
                String outboundConnectionDepartMiddleName = outboundConnectionDepartPassengerName[2].text
                outboundConnectionDepartLastName.shouldContain expectedLastName, "Passenger last name did not match the expected value in view checkin document page"
                outboundConnectionDepartFirstName.shouldContain expectedFirstName, "Passenger first name did not match the expected value in view checkin document page"
                outboundConnectionDepartMiddleName.shouldContain expectedMiddleName, "Passenger middle name did not match the expected value in view checkin document page"
            }
        }
    }

    def verifyBottomLinks() {
        List<WebElement> links = waitForElements(LINKS_CONTAINER)
        List<WebElement> outboundDepartLinks = links[0].findElements(BOARDING_PASS_LINK)
        verifyElementPresent("Notices and Other Important Information link", outboundDepartLinks[0])
        verifyElementPresent("Oversell Situations link", outboundDepartLinks[1])

        if(flow.hasConnectionFlight) {
            List<WebElement> outboundConnectionDepartLinks = links[1].findElements(BOARDING_PASS_LINK)
            verifyElementPresent("Notices and Other Important Information link", outboundConnectionDepartLinks[0])
            verifyElementPresent("Oversell Situations link", outboundConnectionDepartLinks[1])
        }
    }

    def verifyReturnToPreviousScreen() {
        isElementPresent(RETURN_BUTTON).shouldBe true, "Return to previous screen button was not present"
    }

    def verifyGateInformationAlertMessage() {
        List<WebElement> alertMessages = waitForElements(ALERT_MESSAGE)
        if(flow.hasConnectionFlight) {
            alertMessages.size().shouldEqual scenarioState.getLastAirReservation().passengers.size() * 2, "There were no as many alert messages as bording passes in view checkin document page"
        } else {
            alertMessages.size().shouldEqual scenarioState.getLastAirReservation().passengers.size(), "There were no as many alert messages as bording passes in view checkin document page"
        }
    }

    def verifyConfirmationNumber(AirReservation airReservation) {
        waitForElement(CONFIRMATION_NUMBER).text.shouldBe airReservation.adultPnr, "Confirmation number did not match the expected value in view checkin document page"
    }

//    def verifyBoardingTimePresent() {
//        waitForElement(BOARDING_TIME).shouldNotBe null, "Boarding Time was not found in the Boarding Pass"
//    }
    
    def verifyRRSpecialConditions() {
        waitForElement(SPECIAL_CONDITIONS).text.shouldContain "RR", "RR text was not present in the special condition in view checkin document page"
    }

    // r code
    def verifyDisabilitiesCodeCWC()
    {
        waitForElement(DISABILITIES_CWC).text.shouldContain "CWC", "CWC text was not present in the boarding pass"
    }
        // r code
    def verifyDisabilitiesCodePOC()
    {
        waitForElement(DISABILITIES_POC).text.shouldContain "POC", "POC text was not present in the boarding pass"
    }

}
