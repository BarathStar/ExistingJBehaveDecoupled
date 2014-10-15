package pages.mixins

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.FareBreakdownFlyOut
import util.ItineraryData

class PricingTableVerifications {

    private static final By TOTAL_FARE_BREAK_DOWN_LINK_ADT = By.className("totalPriceDetailsText_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_FFP = By.className("totalPriceDetailsText_FFP")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_ADT = By.id("totalPriceDetailsText_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_FFP = By.id("totalPriceDetailsText_FFP")
    private static final By TOTAL_FARE_BREAK_DOWN_FLY_OUT_ADT = By.id("totalPriceDetails_footer_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_FLY_OUT_FFP = By.id("totalPriceDetails_footer_FFP")
    private static final By TOTAL_PRICE_DETAILS = By.id("totalPriceDetails")
    private static final By DEPART_ROW_STOPS_COUNT_CONTAINER = By.cssSelector("#airItinerarydepart .stops")
    private static final By OUTBOUND_FARE_TYPE = By.cssSelector("#airItinerarydepart .fareProductName")
    private static final By INBOUND_FARE_TYPE = By.cssSelector("#airItineraryreturn .fareProductName")
    private static final By OUTBOUND_ROUTING = By.cssSelector("#airItinerarydepart .stops")
    private static final By INBOUD_ROUTING = By.cssSelector("#airItineraryreturn .stops")
    private static final By EARLY_BIRD_BUTTON = By.className("earlybirdCheckIn_button")
    private static final By FLIGHT_TIMES = By.cssSelector("#airItinerarydepart .nowrap")
    private static final By STRONG = By.tagName("strong")
    private static final String PROMOCERT_FARETYPE = "FreedomAward"
    private static final int DEPARTURE_TIME_LOCATION = 0
    private static final int ARRIVAL_TIME_LOCATION = 1
    private static final int CONNECTING_DEPARTURE_TIME_LOCATION = 2
    private static final int CONNECTING_ARRIVAL_TIME_LOCATION = 3

    def verifyDate() {
        List<String> ddateBreakDown = itineraryData.departureDate.toString().split(" ")
        List<String> airPriceContentDepartureDate = airPriceContent.travelDepartDate().split(" ")
        airPriceContentDepartureDate[0].shouldContain ddateBreakDown[0], "Departure day was not correct"
        airPriceContentDepartureDate[1].shouldContain ddateBreakDown[1], "Departure month was not correct"
        airPriceContentDepartureDate[2].replace(',','').toInteger().shouldEqual ddateBreakDown[2].replace(',','').toInteger(), "Departure date was not correct"
        airPriceContentDepartureDate[3].shouldContain ddateBreakDown[5], "Departure year was not correct"
        if (itineraryData.isRoundTripOrOpenJaw()) {
            List<String> rdateBreakDown = itineraryData.returnDate.toString().split(" ")
            List<String> airPriceContentReturnDate = airPriceContent.travelReturnDate().split(" ")
            airPriceContentReturnDate[0].shouldContain rdateBreakDown[0], "Departure day was not correct"
            airPriceContentReturnDate[1].shouldContain rdateBreakDown[1], "Departure month was not correct"
            airPriceContentReturnDate[2].replace(',','').toInteger().shouldEqual rdateBreakDown[2].replace(',','').toInteger(), "Departure date was not correct"
            airPriceContentReturnDate[3].shouldContain rdateBreakDown[5], "Departure year was not correct"
        }
    }

    def verifyDepartureAndArrivalTime() {
        List<WebElement> outboundTimes = airPriceContent.outBoundTimes()
        String outboundDepartureTime = selectFlightsPageData.outboundDepartureTime.replaceAll("\\s", "").toUpperCase()
        String outboundArrivalTime = selectFlightsPageData.outboundArrivalTime.replaceAll("\\s", "").toUpperCase()
        outboundTimes[DEPARTURE_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain outboundDepartureTime, "Outbound flight departure time did not match flight time on Bug Page"
        outboundTimes[ARRIVAL_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain outboundArrivalTime, "Outbound flight arrival time did not match flight time on Bug Page"
        if(flow.hasConnectionFlight) {
            String outboundConnectionDepartureTime = selectFlightsPageData.outboundConnectionDepartureTime.replaceAll("\\s", "").toUpperCase()
            String outboundConnectionArrivalTime = selectFlightsPageData.outboundConnectionArrivalTime.replaceAll("\\s", "").toUpperCase()
            outboundTimes[CONNECTING_DEPARTURE_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain outboundConnectionDepartureTime, "Outbound connection flight depart time did not match flight time on Bug Page"
            outboundTimes[CONNECTING_ARRIVAL_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain outboundConnectionArrivalTime, "Outbound connection flight arrival time did not match flight time on Bug Page"
        }
        if (itineraryData.isRoundTripOrOpenJaw()) {
            List<WebElement> inboundTimes
            if(itineraryData.lessThan4HourLayover) {
                inboundTimes = outboundTimes.subList(2,4)
            } else {
                inboundTimes = airPriceContent.inBoundTimes()
            }
            String inboundDepartureTime = selectFlightsPageData.inboundDepartureTime.replaceAll("\\s", "").toUpperCase()
            String inboundArrivalTime = selectFlightsPageData.inboundArrivalTime.replaceAll("\\s", "").toUpperCase()
            inboundTimes[DEPARTURE_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain inboundDepartureTime, "Inbound flight departure time did not match flight time on Bug Page"
            inboundTimes[ARRIVAL_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain inboundArrivalTime, "Inbound flight arrival time did not match flight time on Bug Page"
            if(flow.hasInboundConnectionFlight) {
                String inboundConnectionDepartureTime = selectFlightsPageData.inboundConnectionDepartureTime.replaceAll("\\s", "").toUpperCase()
                String inboundConnectionArrivalTime = selectFlightsPageData.inboundConnectionArrivalTime.replaceAll("\\s", "").toUpperCase()
                inboundTimes[CONNECTING_DEPARTURE_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain inboundConnectionDepartureTime, "Inbound connection flight depart time did not match flight time on Bug Page"
                inboundTimes[CONNECTING_ARRIVAL_TIME_LOCATION].text.replaceAll("\\s", "").shouldContain inboundConnectionArrivalTime, "Inbound connection flight arrival time did not match flight time on Bug Page"
            }
        }
    }

    def verifyTravelTime(boolean inConfirmationPage = false) {
        if (itineraryData.showOneTableInPriceOrConfirmationPage(inConfirmationPage)){
            airPriceContent.departureTravelTime().replaceAll("\\s", "").replaceFirst("TravelTime", "").shouldContain getTotalTravelTime(), "Outbound Travel time did not match travel time from the Bug page"
        }
        else {
            selectFlightsPageData.outboundTravelTime.replaceAll("\\s", "").shouldContain airPriceContent.departureTravelTime().replaceAll("\\s", "").replaceFirst("TravelTime", ""), "Outbound Travel time did not match travel time from the Bug page"
            if (itineraryData.isRoundTripOrOpenJaw()) {
                selectFlightsPageData.inboundTravelTime.replaceAll("\\s", "").shouldContain airPriceContent.returnTravelTime().replaceAll("\\s", "").replaceFirst("TravelTime", ""), "Inbound Travel time did not match travel time from the Bug page"
            }
        }
    }

    def getTotalTravelTime() {
        List<WebElement> times = waitForElements(FLIGHT_TIMES)
        List<String> outBoundTravelTime = selectFlightsPageData.outboundTravelTime.split(" ")
        List<String> inBoundTravelTime = selectFlightsPageData.inboundTravelTime.split(" ")
        BigDecimal totalHours = new BigDecimal(outBoundTravelTime[0].substring(0,outBoundTravelTime[0].size()-1)) + new BigDecimal(inBoundTravelTime[0].substring(0,inBoundTravelTime[0].size()-1))
        BigDecimal totalMinutes = new BigDecimal(outBoundTravelTime[1].substring(0,outBoundTravelTime[1].size()-1)) + new BigDecimal(inBoundTravelTime[1].substring(0,inBoundTravelTime[1].size()-1))
        if (totalMinutes > 59){
            totalMinutes -= 60
            totalHours += 1
        }
        List<String> outBoundArrivalTime = times[1].text.trim().split(" ")
        BigDecimal outBoundArrivalTimeHours = new BigDecimal(outBoundArrivalTime[0].split(":")[0])
        BigDecimal outBoundArrivalTimeMinutes = new BigDecimal(outBoundArrivalTime[0].split(":")[1])
        if(outBoundArrivalTime[1].contains("PM") && outBoundArrivalTimeHours != 12){
            outBoundArrivalTimeHours += 12
        }

        List<String> inBoundDepartTime = times[2].text.trim().split(" ")
        BigDecimal inBoundDepartTimeHours = new BigDecimal(inBoundDepartTime[0].split(":")[0])
        BigDecimal inBoundDepartTimeMinutes = new BigDecimal(inBoundDepartTime[0].split(":")[1])
        if(inBoundDepartTime[1].contains("PM") && inBoundDepartTimeHours != 12){
            inBoundDepartTimeHours += 12
        }

        if(inBoundDepartTimeMinutes < outBoundArrivalTimeMinutes){
            inBoundDepartTimeHours -= 1
            inBoundDepartTimeMinutes += 60
        }

        totalMinutes += (inBoundDepartTimeMinutes - outBoundArrivalTimeMinutes)
        totalHours += (inBoundDepartTimeHours - outBoundArrivalTimeHours)

        if(totalMinutes > 59){
            totalMinutes -= 60
            totalHours += 1
        }
        String totalTime
        if (totalMinutes.toInteger() >= 10){
            totalTime = totalHours.toString() + "h" + totalMinutes.toString() + "m"
        }
        else {
            totalTime = totalHours.toString() + "h0" + totalMinutes.toString() + "m"
        }

        return totalTime

    }

    def verifyFlightNumber() {
        List<WebElement> outboundFlightNumbers = airPriceContent.outboundFlightNumbers()
        outboundFlightNumbers[0].findElement(STRONG).text.shouldContain selectFlightsPageData.departingFlight_number, "Outbound flight number did not match flight number from the Bug page"
        if (flow.hasConnectionFlight){
            outboundFlightNumbers[1].findElement(STRONG).text.shouldContain selectFlightsPageData.departingConnectingFlight_number, "Outbound connection flight number did not match flight number from the Bug page"
        }
        if (itineraryData.isRoundTripOrOpenJaw()) {
            List<WebElement> inboundFlightNumbers
            if(itineraryData.lessThan4HourLayover && outboundFlightNumbers.size() == 2){
                inboundFlightNumbers = outboundFlightNumbers.subList(1,outboundFlightNumbers.size())
            } else if (itineraryData.lessThan4HourLayover && outboundFlightNumbers.size() > 2){
                inboundFlightNumbers = outboundFlightNumbers.subList(2,outboundFlightNumbers.size())
            } else {
                inboundFlightNumbers = airPriceContent.inboundFlightNumbers()
            }
            inboundFlightNumbers[0].findElement(STRONG).text.shouldContain selectFlightsPageData.returningFlightNumber, "Inbound flight number did not match flight number from the Bug page"
            if (flow.hasInboundConnectionFlight){
                inboundFlightNumbers[1].findElement(STRONG).text.shouldContain selectFlightsPageData.returningConnectingFlight_number, "Inbound connection flight number did not match flight number from the Bug page"
            }
        }
    }

    def verifyDepartureAndArrivalCities() {
        if(flow.hasConnectionFlight) {
            List<WebElement> outboundCities = airPriceContent.outBoundCities()
            outboundCities[0].text.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
            outboundCities[1].text.shouldContain itineraryData.outboundConnectingStation, "Arrival station did not match arrival station from the Bug page"
            outboundCities[2].text.shouldContain itineraryData.outboundConnectingStation, "Connection departure station did not match connection departure station from the Bug page"
            outboundCities[3].text.shouldContain itineraryData.arrivalStation, "Connection arrival station did not match connection arrival station from the Bug page"
        } else if (itineraryData.isOpenJaw() && itineraryData.lessThan4HourLayover){
            airPriceContent.departureOutBoundStation().shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
            airPriceContent.outBoundCities()[1].text.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the Bug page"
        } else {
            airPriceContent.departureOutBoundStation().shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
            airPriceContent.departureInBoundStation().shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the Bug page"
        }
        if (itineraryData.isRoundTrip()) {
            if(flow.hasInboundConnectionFlight) {
                List<WebElement> inboundCities = airPriceContent.inBoundCities()
                inboundCities[0].text.shouldContain itineraryData.arrivalStation, "Departure station did not match departure station from the Bug page"
                inboundCities[1].text.shouldContain itineraryData.inboundConnectingStation, "Arrival station did not match arrival station from the Bug page"
                inboundCities[2].text.shouldContain itineraryData.inboundConnectingStation, "Connection departure station did not match connection departure station from the Bug page"
                inboundCities[3].text.shouldContain itineraryData.departureStation, "Connection arrival station did not match connection arrival station from the Bug page"
            } else {
                airPriceContent.returnOutBoundStation().shouldContain itineraryData.arrivalStation, "Return departure station did not match departure station from the Bug page"
                airPriceContent.returnInBoundStation().shouldContain itineraryData.departureStation, "Arrival departure station did not match arrival station from the Bug page"
            }
        } else if (itineraryData.isOpenJaw() && itineraryData.lessThan4HourLayover){
            WebElement inboundCities
            try{
                inboundCities = airPriceContent.inBoundCities()[1]
            } catch(Exception e){
                inboundCities =  airPriceContent.outBoundCities()[3]
            }
            inboundCities.text.shouldContain itineraryData.returnStation, "Arrival station did not match arrival station from the Bug page"
        } else if (itineraryData.isOpenJaw()) {
            airPriceContent.returnInBoundStation().shouldContain itineraryData.returnStation, "Return station did not match return station from the Bug page"
        }
    }

    def verifyFareType(boolean inConfirmationPage = false) {
        if (itineraryData.departingFlight_fareClass.equals("PromoCert")){
            waitForElement(OUTBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe PROMOCERT_FARETYPE, "Fare type did not match the selected one for outbound"
        } else {
            waitForElement(OUTBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.departingFlight_fareClass, "Fare type did not match the selected one for outbound"
        }
        if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage(inConfirmationPage)) {
            if (itineraryData.departingFlight_fareClass.equals("PromoCert")){
                waitForElement(INBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe PROMOCERT_FARETYPE, "Fare type did not match the selected one for inbound"
            } else {
                waitForElement(INBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.arrivingFlight_fareClass, "Fare type did not match the selected one for inbound"
            }
        }
    }

    def verifyRoutingType() {
        waitForElement(OUTBOUND_ROUTING)?.text.shouldContain selectFlightsPageData.outboundFlyoutRouting, "Routing type did not match the selected one for outbound"
        if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.lessThan4HourLayover) {
            waitForElement(INBOUD_ROUTING)?.text.shouldContain selectFlightsPageData.inboundFlyoutRouting, "Routing type did not match the selected one for inbound"
        }
    }

    FareBreakdownFlyOut openFareBreakDownFlyOut() {
        if(itineraryData.isPromoCertBooking()) {
            waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_FFP)?.click()
            FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_FARE_BREAK_DOWN_FLY_OUT_FFP))
            return flyout
        }
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_ADT)?.click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_FARE_BREAK_DOWN_FLY_OUT_ADT))
        return flyout
    }

    FareBreakdownFlyOut openFareBreakDownFlyOutById() {
        if(itineraryData.isPromoCertBooking()) {
            waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_FFP)?.click()
            FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_PRICE_DETAILS))
            return flyout
        }
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_ADT)?.click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_PRICE_DETAILS))
        return flyout

    }

    void verifyAddEarlyBirdCheckInButtons() {
        verifyAddEarlyBirdCheckInButtonDisplayed()
        if (passengerData.containsSeniorAndAdultPassengers()){
            verifyAddEarlyBirdCheckInButtonDisplayed(1)

        }
    }

    public verifyAddEarlyBirdCheckInButtonDisplayed(int i = 0) {
        flow.isEarlyBirdEligibleInBound ||
                flow.isEarlyBirdEligibleOutBound ||
                (flow.user ? !flow.user.accountType.contains("Alist") : false) ||
                flow.isUM ||
                (itineraryData.departingFlight_fareClass == "BusinessSelect")?: verifyElementPresent("Earlybird Upsell button", waitForElements(EARLY_BIRD_BUTTON)[i])
    }
}