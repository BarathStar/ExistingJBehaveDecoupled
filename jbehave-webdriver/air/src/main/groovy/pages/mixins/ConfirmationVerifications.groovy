package pages.mixins

import com.swacorp.dotcom.webscenarios.air.Data
import domain.AirReservation
import domain.CarReservation
import domain.HotelReservation
import domain.Passenger
import org.joda.time.LocalDate
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import pages.elements.StopLogicInfo
import state.CarReservationData
import state.CurrentCarPNR
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.DollarToBigDecimalConverter
import util.HotelItineraryData
import util.ItineraryData

import static org.junit.Assert.fail

class ConfirmationVerifications {

    private static final By STOPS_TEXT_DEPARTURE_CONTAINER = By.cssSelector("#airItinerarydepart .stopInfo .stopCityMessage")
    private static final By STOPS_CITIES_LIST_DEPARTURE_CONTAINER = By.cssSelector("#airItinerarydepart .airItineraryFlightRouting .stopInfo .stopCityList")
    private static final By STOPS_COUNT_CONTAINER = By.cssSelector("#airItinerarydepart .departureDate .stops")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON = By.cssSelector("#airItinerarydepart .airItineraryFlightRouting .routingDetailsStops.routingDetailsSeparator")
    private static final By DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF = By.cssSelector("#airItinerarydepart td.segmentsCell.withRightBorder.lastSegmentCell.withDottedBorder.segmentInFlightWithStops")
    private static final By CONFIRMATION_TRIP_MANAGEMENT_TITLE_DETAILS = By.className("confirmation_trip_management_title_details")
    private static final By ADULT_CONFIRMATION_NUMBER = By.xpath("//td[@id='passenger_adult_0']/following-sibling::td[1]")
    private static final By SENIOR_CONFIRMATION_NUMBER = By.xpath("//td[@id='passenger_senior_0']/following-sibling::td[1]")
    private static final By SAME_PAX_CONFIRMATION_NUMBER = By.cssSelector("span.confirmation_number")
    private static final By TRIP_TOTAL = By.id("trip_grand_total_bottom")
    private static final By FIRST_ADULT_PASSENGER_NAME = By.id("passenger_adult_0")
    private static final By AIR_CONFIRMATION_NUMBER = By.className("confirmation_number")
    private static final By FIRST_SENIOR_PASSENGER_NAME = By.id("passenger_senior_0")
    private static final By ADULT_CONFIRMATION_ID = By.id("passenger_adult_0")
    private static final By CONFIRMATION_TOP_SUMMARY_AIR = By.id("confirmation_top_summary_air")
    private static final By CAR_CONFIRMATION_CONTAINER = By.id('car_confirmation')
    private static final By CAR_CONFIRMATION_NUMBER = By.className('confirmation_number')
    private static final By HOTEL_CONFIRMATION_CONTAINER = By.id('confirmation_itinerary_hotel_confirmation_number')
    private static final String HOTEL_CONFIRMATION_NUMBER = 'confirmation_number'
    private static final By HEADER_HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_header_hotel_confirmation_number")
    private static final By ITINERARY_HOTEL_CONFIRMATION_NUMBER = By.id("confirmation_itinerary_hotel_confirmation_number")
    private static final By RR_LINK = By.className("rapid-reward-link")

    //PassengerData passengerData

    def verifyStopsCitiesInfoOnDepartureAirItinerary() {
        String[] stopsCitiesCodes = itineraryData.getOutboundStopCitiesCodes()
        stopLogicInfo.verifyStopLogicInfo(STOPS_TEXT_DEPARTURE_CONTAINER, STOPS_CITIES_LIST_DEPARTURE_CONTAINER, stopsCitiesCodes)
    }

    def verifyStopsCountInfoOnDepartureAirItinerary() {
        int notConnectingStopsCount = itineraryData.getOutboundNumberOfNotConnectingStops()
        int connectingStopsCount = itineraryData.getOutboundNumberOfConnectingStops()
        stopLogicInfo.verifyStopLogicAndPlaneChangeCount(STOPS_COUNT_CONTAINER, notConnectingStopsCount, connectingStopsCount)
    }

    def verifyChangePlaneInfoOnDepartureAirItinerary(){
        String changePlaneCityCode = itineraryData.outboundConnectingStation
        try {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_ON, changePlaneCityCode)
        } catch (Exception e) {
            stopLogicInfo.verifyChangePlaneInfo(DEPARTURE_CHANGE_PLANE_INFO_CONTAINER_AIRTRAN_OFF, changePlaneCityCode)
        }
    }

    void verifyNewTripNameExists(String tripName) {
        waitForElement(By.id("confirmation_trip_name")).getText().shouldContain tripName, "fail $tripName not found"
    }

    void verifyConfirmationTitleDetailsOnPage() {
        waitForElement(CONFIRMATION_TRIP_MANAGEMENT_TITLE_DETAILS).getText().shouldBe 'New purchases added to your trip.', 'Expected title not found'
    }

    def viewDetailsLinkShouldBeDisplayed() {
        isViewDetailsLinkPresent().shouldBe true, "View details link was expected"
    }

    boolean isViewDetailsLinkPresent() {
        return isElementDisplayed(By.id("confirmation_top_summary_view_details"))
    }

    void verifyPNRs() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        airReservation.shouldNotBe null, "airReservation is null, check to make sure the previous steps stored the PNR information correctly"

        if (airReservation.containsAdultPassenger()) {
            ArrayList<String> adultPassengerFullName = getAdultPNRInformation()
            airReservation.adultPnr.shouldNotBe null, "Adult passenger PNR is null."
            Passenger passenger = airReservation.passengers.get(0)
            verifyName(adultPassengerFullName, passenger.firstName).shouldBe true, "Actual Adult passenger first name does not match expected name."
            verifyName(adultPassengerFullName, passenger.lastName).shouldBe true, "Actual Adult passenger last name does not match expected name."
            verifyAdultPnr()
        }

        if (airReservation.containsSeniorPassenger()) {
            ArrayList<String> seniorPassengerFullName = getSeniorPNRInformation()
            airReservation.seniorPnr.shouldNotBe null, "Senior passenger PNR is null."
            Passenger passenger = airReservation.getSeniorPassengers().get(0)
            verifyName(seniorPassengerFullName, passenger.firstName).shouldBe true, "Actual Senior passenger first name does not match expected name."
            verifyName(seniorPassengerFullName, passenger.lastName).shouldBe true, "Actual Senior passenger last name does not match expected name."
            verifySeniorPnr()
        }
        else {
            storeCurrentPNR()
            storeTripTotalPrice(airReservation)
        }
    }


    private storeAirPnr(AirReservation airReservation) {
        if (passengerData.containsSeniorAndAdultPassengers()){
            airReservation.adultPnr = waitForElement(ADULT_CONFIRMATION_NUMBER).getText()
            airReservation.seniorPnr = waitForElement(SENIOR_CONFIRMATION_NUMBER).getText()
        }
        else if(passengerData.containsAdultPassenger()) {
            airReservation.adultPnr = waitForElement(SAME_PAX_CONFIRMATION_NUMBER).getText()
        }
        else if (passengerData.containsSeniorPassenger()) {
            airReservation.seniorPnr = waitForElement(SAME_PAX_CONFIRMATION_NUMBER).getText()
        } else if (flow.getCompanion() != null) {
            airReservation.companionPnr = waitForElement(SAME_PAX_CONFIRMATION_NUMBER).getText()
        }
    }

    private storeTripTotalPrice(AirReservation airReservation){
        BigDecimal tripTotal = DollarToBigDecimalConverter.toBigDecimal(waitForElement(TRIP_TOTAL))
        airReservation.setPrice(tripTotal)
    }

    private ArrayList<String> getAdultPNRInformation(){
        def text
        WebElement wait = waitForElement(FIRST_ADULT_PASSENGER_NAME)
        try {
            text = wait.getText()
        } catch (WebDriverException we) {
            sleep(10000)
            text = wait.getText()
        }
        ArrayList<String> passengerFullName = text.trim().split()
        return passengerFullName
    }

    private void verifySeniorPnr() {
        if (scenarioState.getLastAirReservation().containsAdultPassenger()) {
            WebElement seniorPnr
            try {
                seniorPnr = waitForElement(SENIOR_CONFIRMATION_NUMBER).findElement(By.xpath("..")).findElement(AIR_CONFIRMATION_NUMBER)
            } catch (WebDriverException wde) {
                fail("Did not get an Senior PNR for the purchase.")
            }
            seniorPnr.shouldNotBe null, "Did not get a Senior PNR for the purchase."
            seniorPnr.getText().size().shouldBeEqual 6, "Senior PNR is invalid: ${seniorPnr.getText()}."
        }
        else {
            verifyNewAirConfirmationPresent()
        }
    }

    private ArrayList<String> getSeniorPNRInformation(){
        ArrayList<String> passengerFullName = waitForElement(FIRST_SENIOR_PASSENGER_NAME).getText().trim().split()
        return passengerFullName
    }

    private boolean verifyName(ArrayList<String> fullName, String partialName) {
        return fullName.any {name -> name.equalsIgnoreCase(partialName)}
    }

    private void verifyAdultPnr() {
        if (scenarioState.getLastAirReservation().containsSeniorPassenger()) {
            WebElement adultPnr
            try {
                adultPnr = waitForElement(ADULT_CONFIRMATION_ID).findElement(By.xpath("..")).findElement(AIR_CONFIRMATION_NUMBER)
            } catch (WebDriverException wde) {
                fail("Did not get an Adult PNR for the purchase.")
            }
            adultPnr.shouldNotBe null, "Did not get an Adult PNR for the purchase."
            adultPnr.getText().size().shouldBeEqual 6, "Adult PNR is invalid: ${adultPnr.getText()}."
        }
        else {
            verifyNewAirConfirmationPresent()
        }
    }

    void verifyNewAirConfirmationPresent() {
        List<WebElement> confirmationNumbers = waitForElement(CONFIRMATION_TOP_SUMMARY_AIR).findElements(AIR_CONFIRMATION_NUMBER)
        if(passengerData.containsSeniorAndAdultPassengers()){
            confirmationNumbers[0].shouldNotBe null, "Did not find Senior Air Confirmation"
            confirmationNumbers[1].shouldNotBe null, "Did not find Adult Air Confirmation"
        } else {
            confirmationNumbers[0].shouldNotBe null, "Did not find Air Confirmation"
        }
    }

    def storeCurrentPNR() {
        AirReservation airReservation = new AirReservation()
        if(flow.hasAir) {
            storeAirPnr(airReservation)
            storeTripTotalPrice(airReservation)
        }
        if(flow.hasHotel) {
            storeHotelPNR(airReservation)
        }
        if(flow.isCarReservationPresent) {
            storeCarPNR(airReservation)
        }
        airReservation.creditCard = flow.userCreditCard
        airReservation.passengers = new ArrayList(passengerData.passengers)
        passengerData.clearPassengers()
        airReservation.itineraryData = new ItineraryData(itineraryData)
        scenarioState.add airReservation
    }


    void storeCarPNR(AirReservation airReservation) {
        WebElement confirmationContainer = waitForElement(CAR_CONFIRMATION_CONTAINER)
        WebElement carPnrElement = confirmationContainer.findElement(CAR_CONFIRMATION_NUMBER)

        Map responseAsMap = new HashMap()
        responseAsMap.put('renterFirstName', data.firstName)
        responseAsMap.put('renterLastName', data.lastName)
        responseAsMap.put('pickUpStation', carItineraryData.pickUpStation)
        responseAsMap.put('dorpOffStation', carItineraryData.dropOffStation)
        responseAsMap.put('pickUpDate',new LocalDate(carItineraryData.pickUpDate))
        responseAsMap.put('dropOffDate',new LocalDate(carItineraryData.dropOffDate))
        responseAsMap.put('agencyName', carItineraryData.carVendor.name())
        responseAsMap.put('vehicleType', carItineraryData.carType.name())
        if(airReservation == null){
            airReservation = new AirReservation()
        }
        airReservation.carReservation = new CarReservation(responseAsMap, carPnrElement.getText().trim())
    }

    void storeHotelPNR(AirReservation airReservation) {
        headerHotelConfirmationNumber().isAllWhitespace().shouldBe false, "Hotel Confirmation number is blank"

        Map responseMap = new HashMap()
        responseMap.put("checkInDate",new LocalDate(hotelItineraryData.checkInDate))
        responseMap.put("checkOutDate",new LocalDate(hotelItineraryData.checkOutDate))
        responseMap.put("hotelName",hotelItineraryData.hotelName)
        if (passengerData.passengers.size() != 0) {
            responseMap.put("customerFirstName",passengerData.passengers.get(0).firstName)
            responseMap.put("customerLastName",passengerData.passengers.get(0).lastName)
        } else {
            responseMap.put("customerFirstName",data.firstName)
            responseMap.put("customerLastName",data.lastName)
        }
        responseMap.put("destination",hotelItineraryData.destination)
        responseMap.put("billingFirstName",hotelItineraryData.billingFirstName)
        responseMap.put("billingLastName",hotelItineraryData.billingLastName)
        String hotelPNR = waitForElement(HOTEL_CONFIRMATION_CONTAINER).getText().trim()
        airReservation.hotelReservation = new HotelReservation(responseMap,hotelPNR)
    }

    def headerHotelConfirmationNumber() {
        waitForElement(HEADER_HOTEL_CONFIRMATION_NUMBER).getText().trim()
    }

    def confirmCar() {
        currentCarPNR.pnr != null && (currentCarPNR.pnr.trim()).size() > 0
    }

    def confirmHotel() {
        AirReservation airReservation = new AirReservation()
        storeHotelPNR(airReservation)
        scenarioState.add airReservation
        headerHotelConfirmationNumber().shouldContain itineraryHotelConfirmationNumber()
    }

    def itineraryHotelConfirmationNumber() {
        waitForElement(ITINERARY_HOTEL_CONFIRMATION_NUMBER).getText().trim()
    }

    public void verifyCarrierInformation() {
        if (itineraryData.isOneWay()) {
            itinerary.verifyDepartFlightLogoPresent(itineraryData.departingFlight_carrierCode)
            itinerary.verifyAppropriateOperatingFlightInformationInDepart(itineraryData.departingFlight_carrierCode)
            itinerary.verifyAirlineNamePresentInDepartText(itineraryData.departingFlight_airlineName)
        }
        else {
            def inBoundCarrierCode = null
            def inBoundAirlineName = null
            if (itineraryData.isRoundTrip()) {
                inBoundCarrierCode = itineraryData.arrivingFlight_carrierCode
                inBoundAirlineName = itineraryData.arrivingFlight_airlineName
            }
            else if (itineraryData.isOpenJaw()) {
                inBoundCarrierCode = itineraryData.returningFlight_carrierCode
                inBoundAirlineName = itineraryData.returningFlight_airlineName
            }
            itinerary.verifyFlightLogoPresentInBothRows(itineraryData.departingFlight_carrierCode, inBoundCarrierCode)
            itinerary.verifyAppropriateOperatingFlightInformationInBothRows(itineraryData.departingFlight_carrierCode, inBoundCarrierCode)
            itinerary.verifyAirlineNamePresentInDepartArriveText(itineraryData.departingFlight_airlineName, inBoundAirlineName)
        }
    }

    def verifyPassengerName() {
        verifyAdultPassengerName()
        String seniorPassengerName
        if (passengerData.containsSeniorAndAdultPassengers()){
            seniorPassengerName = waitForElement(FIRST_SENIOR_PASSENGER_NAME).text
            String fullName = passengerData.passengers[1].getFirstName().toUpperCase() + " " + passengerData.passengers[1].getLastName().toUpperCase()
            seniorPassengerName.toUpperCase().shouldBe fullName,"Senior Passenger Full name did not match"
        } else if (passengerData.containsSeniorPassenger()) {
            seniorPassengerName = waitForElement(FIRST_SENIOR_PASSENGER_NAME).text
            String fullName = passengerData.passengers[0].getFirstName().toUpperCase() + " " + passengerData.passengers[0].getLastName().toUpperCase()
            seniorPassengerName.toUpperCase().shouldBe fullName,"Senior Passenger Full name did not match"
        }
    }

    public verifyAdultPassengerName() {
        for (int i = 0; i < passengerData.adults.size(); i++) {
            String adultPassengerName = waitForElement(By.id("passenger_adult_${i}")).text.split("\n")[0]
            if (flow.isRapidRewards || flow.isCustomer && i == 0) {
                String rrFullName = rrContactInformation.firstName.toUpperCase() + " " + rrContactInformation.lastName.toUpperCase()
                adultPassengerName.toUpperCase().shouldBe rrFullName, "Adult Passenger Full name did not match with rapid reward contact information"
            } else {
                String fullName = passengerData.passengers[i].getFirstName().toUpperCase() + " " + passengerData.passengers[i].getLastName().toUpperCase()
                adultPassengerName.toUpperCase().shouldContain fullName, "Adult Passenger Full name did not match"
            }
        }
    }

    public verifyAdultRRLinkPresent() {
        if (!flow.isRapidRewards) {
            waitForElements(RR_LINK, false).size() shouldEqual passengerData.adults.size(), "Adult Add Rapid Rewards Number link missing in the view reservation page"
        }
    }

    public verifySeniorRRLinkPresent() {
        if (!flow.isRapidRewards) {
            waitForElements(RR_LINK, false).size() shouldEqual passengerData.seniors.size(), "Adult Add Rapid Rewards Number link missing in the view reservation page"
        }
    }
}
