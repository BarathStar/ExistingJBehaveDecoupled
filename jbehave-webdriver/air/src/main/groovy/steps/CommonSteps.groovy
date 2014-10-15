package steps

import builders.RelativeDateTimeParser
import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration
import org.ho.yaml.Yaml
import org.jbehave.core.model.ExamplesTable
import org.joda.time.DateTime
import org.joda.time.LocalTime
import pages.elements.AutoCompleteWidget
import pages.elements.DotRequiredLinks
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import pages.elements.HomePageFlightSearchForm
import state.CarReservationData
import state.Flow
import state.PassengerData
import steps.conditional.ToggleGlobalNav
import static org.junit.Assert.fail
import org.jbehave.core.annotations.*
import pages.*
import util.*
import static util.Locators.ELEMENT_IDS

public class CommonSteps {
    public static final String MODAL_SUBMIT_BUTTON_ID = "tripSearchModalSubmit"

    BasePage basePage
    SearchFlightsPage searchFlightsPage
    FlightStatusPage flightStatusPage
    ViewReservationPage viewReservationPage
    ItineraryData itineraryData
    PricePage pricePage
    HelpPage helpPage
    HomePage homePage
    Flow flow
    FlightSearchForm flightSearchForm
    DotRequiredLinks dotRequiredLinks
    Data data
    DiscountData discountData
    PurchasePage purchasePage
    ReconcilePage reconcilePage
    RandomTicketTypeGroup randomTicketTypeGroup
    PassengerData passengerData
    HotelItineraryData hotelItineraryData
    InternationalSearchFlightsPage internationalSearchFlightsPage
    HomePageFlightSearchForm homePageFlightSearchForm
    AutoCompleteWidget autoCompletePage
    CarReservationData carItineraryData
    MyAccountPage myAccountPage
    SelectFlightsPage selectFlightsPage
    SelectNewFlightsPage selectNewFlightsPage
    CancelAirPage cancelAirPage
    CheckinPage checkinPage
    AutoCompleteWidget autoCompleteWidget
    ConfirmationPage confirmationPage
	EarlyBirdConfirmationPage earlyBirdConfirmationPage
    ItineraryChangeConfirmationPage itineraryChangeConfirmationPage
    CancelAirReservationPage cancelAirReservationPage
    ChangeAirReservationPage changeAirReservationPage
    TripDetailsPage tripDetailsPage
	UpgradeToBusinessSelectPage upgradeToBusinessSelectPage
    GlobalNavigationHeader globalNavigationHeader

    @Given("I have been redirected to the Airtran flight page")
    def airTranContinueButton() {
        autoCompletePage.airTranModalContinueButton()
    }

    @Given("I have a trip named \$tripName")
    def setupTripName(String tripName) {
        flow.tripName = tripName
    }

    @Given("I am flying from \$departureStation to \$arrivalStation")
    def setupDepartureAndArrivalStations(String departureStation, String arrivalStation) {
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
    }

    @Given("I am flying from \$departureStation to \$arrivalStation with \$carrier and \$carrier2")
    def setupStationsAndCarriers(String departureStation, String arrivalStation, String carrier, String carrier2) {
        itineraryData.departingFlight_carrierCode = carrier
        itineraryData.arrivingFlight_carrierCode = carrier2
        setupDepartureAndArrivalStations(departureStation, arrivalStation)
    }

    @Given("I am flying from \$departureStation to \$arrivalStation to \$returnStation")
    def setupDepartureArrivalAndReturnStations(String departureStation, String arrivalStation, String returnStation) {
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
        itineraryData.returnStation = returnStation
    }


    @Given("I am flying a \$fare_class round-trip \$carrier1 \$carrier2 flight departing \$departureDate and returning \$returnDate")
    def setupDepartureDates(String fare_class, String carrier1, String carrier2, String departureDate, String returnDate) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withRoundTripStations(carrier1, carrier2).withFlightsDates(departureDate, returnDate).withFareClass(fare_class).build())
    }

    @Given("I am flying a one-way \$carrier flight")
    def setupOneWayCarrierFlight(String carrier) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).
                withOneWayStations(carrier).build())
    }

    @Given("I am flying a one-way \$carrier flight with Altea")
    def setupOneWayCarrierFlightWithAltea(String carrier) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).
                withOneWayStations(carrier).withAltea().build())
    }

    @Given("I am flying a round-trip \$carrier1 \$carrier2 flight")
    def setupRoundTripCarrierFlight(String carrier1, String carrier2) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withRoundTripStations(carrier1, carrier2).build())
    }

    @Given("I am flying a round-trip \$carrier1 \$carrier2 flight using promo code \$promoCode")
    def setupRoundTripCarrierFlight(String carrier1, String carrier2, String promoCode) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withRoundTripStations(carrier1, carrier2).withPromoCode(promoCode).withOutboundDiscountBaseFare("0").build())
    }

    @Given("I am flying an open-jaw \$carrier1 \$carrier2 flight")
    def setupOpenJawCarrierFlight(String carrier1, String carrier2) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withReturningCarrier(carrier2).withOpenJawStations(carrier1, carrier2).build())
    }

    @Given("I am flying an open-jaw \$carrier1 \$carrier2 flight with \$routingType segments")
    def setupOpenJawCarrierFlight(String carrier1, String carrier2, String routingType) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withReturningCarrier(carrier2).withRoutingType(routingType).withOpenJawStations(carrier1, carrier2).build())
    }

    @Given("I am departing \$departureDays days from now and returning \$returnDays days from now")
    def setDepartAndReturnDateTo(String departureDays, String returnDays) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withValuesFrom(itineraryData).withFlightDatesFromToday(departureDays, returnDays).build())
    }

    @Given("I am flying a \$fare_class round-trip \$carrier1 \$carrier2 flight")
    def setupRoundTripFareClassCarrierFlight(String fare_class, String carrier1, String carrier2) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withRoundTripStations(carrier1, carrier2).withFareClass(fare_class).build())
    }

    @Given("I am flying a \$fare_class one-way \$carrier flight")
    def setupOneWayFareClassCarrierFlight(String fare_class, String carrier) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withOneWayStations(carrier).withFareClass(fare_class).build())
    }

    @Given("I am flying a one-way \$carrier flight with a \$routingType segment")
    def setupOneWayConnectingFlight(String carrier, String routingType) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withOneWayStations(carrier).withRoutingType(routingType).build())
    }

    @Given("I am flying a round-trip \$carrier flight with \$routingType segments")
    def setupRoundTripConnectingFlight(String carrier, String routingType) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withRoundTripStations(carrier, carrier).withRoutingType(routingType).build())
    }

    @Given("I am flying a round-trip \$carrier flight with Altea")
    def setupRoundTripConnectingFlightWithAltea(String carrier) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withRoundTripStations(carrier, carrier)
        .withAltea().build())
    }

    @Given("I am flying a round-trip \$carrier flight with Altea today")
    def setupRoundTripConnectingFlightForTodayWithAltea(String carrier) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withRoundTripStations(carrier, carrier)
        .withAltea().withFlightsDates("today", "tomorrow").build())
    }

    @Given("I am flying a round-trip \$carrier flight with \$routingType segments departing \$departureDate and returning \$returnDate")
    def setupRoundTripConnectingFlightWithDepartureAndReturnDate(String carrier, String routingType, String departureDate, String returnDate) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withFlightsDates(departureDate, returnDate).withRoundTripStations(carrier, carrier).withRoutingType(routingType).build())
    }

    @Given("I am flying a round-trip \$carrier flight EB eligible with \$routingType segments")
    def setupRoundTripEBEligibleConnectingFlight(String carrier, String routingType) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier).withArrivalCarrier(carrier).withRoundTripStations(carrier, carrier).withFlightsDates("early-bird").withRoutingType(routingType).build())
    }

    @Given("I am flying a round-trip international flight")
    def setupRoundTripCarrierInternationalFlight() {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("International").withArrivalCarrier("International").withRoundTripStations("International", "International").build())
    }

    @Given("I am flying a open-jaw international flight")
    def setupOpenJawCarrierInternationalFlight() {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("International").withArrivalCarrier("International").withReturningCarrier("International").withOpenJawStations("International", "International").build())
    }

    @Given("I am flying an international one-way flight")
    def setupOneWayCarrierInternationalFlight() {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier("International").withOneWayStations("International").build())
    }

    @Given("I have markets and flights available for \$itinerary_type with \$fareClass fare class")
    def loadItineraryAndSetFareClass(@Named("itinerary_type") String itinerary_type, @Named("fareClass") String fareClass) {
        flow.hasAir = true
        loadItineraryData(itinerary_type)
        itineraryData.arrivingFlight_fareClass = fareClass
        itineraryData.returningFlight_fareClass = fareClass
        itineraryData.departingFlight_fareClass = fareClass
    }

    @Given("I have markets and flights available for <itineraryType>")
    def loadItineraryData(@Named("itineraryType") String itineraryType) {
        flow.hasAir = true
        String file_name = determineFileName(itineraryType)
        InputStream itineraryDataStream = this.getClass().getResourceAsStream("/data/${file_name}.yml")
        if (itineraryDataStream == null)
            throw new FileNotFoundException("Unable to find /data/${file_name}.yml on the classpath")
        //itineraryData = new ItineraryDataBuilder().build()
        itineraryData.setValues(Yaml.loadType(itineraryDataStream, ItineraryData))
    }

    @Given("I want to fly from from \$departureStation to \$arrivalStation on day of \$departureAt")
    def loadItineraryDepartureDate(String departureStation, String arrivalStation, String departureAt) {
        RelativeDateTimeParser dtparser = new RelativeDateTimeParser();
        DateTime departureDateTime = dtparser.parseRelativeDateTime(departureAt);
        Class<?> c = itineraryData.getClass();

        Date departureDate = departureDateTime.toDate();
        c.getDeclaredField("departureStation").set(itineraryData, departureStation)
        c.getDeclaredField("departureDate").set(itineraryData, departureDateTime.toDate())
        c.getDeclaredField("departureTime").set(itineraryData, departureDateTime.toLocalTime())
        c.getDeclaredField("arrivalStation").set(itineraryData, arrivalStation)
        flow.hasAir = true

    }

    @Given("I have the following itinerary data: \$itineraryTable")
    @Aliases(values = ["I am traveling as a: \$itineraryTable",
    "air itinerary data as follows: \$itineraryTable",
    "I have selected the following itinerary data: \$itineraryTable",
    "I am changing the following itinerary data to: \$itineraryTable"])
    def loadItineraryTable(ExamplesTable itineraryTable) {

        Class<?> c = itineraryData.getClass();
        List<Map<String, String>> rows = itineraryTable.getRows()

        rows.each {
            try {
                String field = it.getAt("Field")
                String value = it.getAt("Value")
                if(field == "departureMonth" || field == "returnMonth") {
                    RelativeDateTimeParser relativeDateTimeParser = new RelativeDateTimeParser(DateTime.now());

                    if (relativeDateTimeParser.doesItMatchesWithRelativeTimeParserFormat(value)) {
                        DateTime date = relativeDateTimeParser.parseRelativeDateTime(value)
                        if(field == "departureMonth") {

                            c.getDeclaredField("departureDate").set(itineraryData, date.toDate())
                        }
                        if(field == "returnMonth") {

                            c.getDeclaredField("returnDate").set(itineraryData, date.toDate())
                        }
                    }

                }
                else if (field == "departureDate" || field == "returnDate") {
                    if (value.matches("^[+]\\d+")) {
                        Date date = new Date().plus(value.substring(1).toInteger())
                        if(flow.forcePlusDayToSearch ){
                            date = date.plus(1)
                            itineraryData.departureDate = date
                        }
                        c.getDeclaredField(field).set(itineraryData, date)
                    }
                } else if (field == "departureTime" || field == "returnTime") {
                    if (value.matches("^[+]\\d+")) {
                        LocalTime time = new LocalTime().plusHours(value.substring(1).toInteger())
                        c.getDeclaredField(field).set(itineraryData, time)
                    }else if(value.matches("^\\d{2}[:]\\d{2}")){
                        LocalTime time = LocalTime.parse(value);
                        c.getDeclaredField(field).set(itineraryData,time)
                    }
                } else if (field == "departureTimeInMinutes" || field == "returnTimeInMinutes") {
                    if (value.matches("^[+]\\d+")) {
                        LocalTime time = new LocalTime().plusMinutes(value.substring(1).toInteger())
                        c.getDeclaredField("departureTime").set(itineraryData, time)
                    }
                } else if(field == "departingFlight_number"){
                    itineraryData.departingFlight_number = Integer.valueOf(value)
                }else if(field == "returningFlight_number"){
                    itineraryData.returningFlight_number = Integer.valueOf(value)
                } else if(field == "isAltea"){
                    itineraryData.isAltea = "true".equals(value) ? true : false;
                } else {
                    c.getDeclaredField(field).set(itineraryData, (value.equals("true") || value.equals("false"))?(boolean)value:value)
                }

                if (field == "departureStation") {
                    flow.hasAir = true
                }
            }
            catch (NoSuchFieldException x) {
                x.printStackTrace()
            }
            catch (IllegalAccessException x) {
                x.printStackTrace();
            }
        }
        if (itineraryData.departureStation == "ANY" && itineraryData.arrivalStation == "ANY") {
            chooseRoutesAndStationsIfNotSpecified()
        }
    }

    @Given("The following schedule is available: \$scheduleData")
    def loadAirSchedulesIntoDynaStubs(ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        dynaStubsIntegration.prepareSchedulesWithRandomItineraries(scheduleData)
    }

    @Given("Only the following one way schedule is available from \$origin to \$destination on \$departureDate: \$scheduleData")
    def loadExclusiveAirSchedulesIntoDynaStubsOneWay(String origin, String destination, String departureDate, ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        if(departureDate.equals("+0d")){
            List<Map<String, String>> rows = scheduleData.getRows()
            rows.each {
                try {
                    String field = it.getAt("departure")
                    DateTime referenceTime = DateTime.now()
                    RelativeDateTimeParser relativeDate = new RelativeDateTimeParser(referenceTime)

                    DateTime upTimeSchedule = relativeDate.parseRelativeDateTime(field)

                    if(!upTimeSchedule.getDayOfMonth().equals(referenceTime.getDayOfMonth())) {
                        departureDate = "+1d"
                        flow.forcePlusDayToSearch = true
                    }
                }
                catch (NoSuchFieldException x) {
                    x.printStackTrace()
                }
                catch (IllegalAccessException x) {
                    x.printStackTrace();
                }
            }
        }
        dynaStubsIntegration.prepareSchedulesWithSpecificItineraries(scheduleData, origin, destination, departureDate)
    }

    @Given("Only the following one way flight schedule is available from \$origin to \$destination on \$departureDate: \$scheduleData")
    def loadExclusiveFlightScheduleIntoDynaStubsOneWay(String origin, String destination, String departureDate, ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        dynaStubsIntegration.prepareFlightSchedulesWithSpecificItineraries(scheduleData, origin, destination, departureDate)
    }

    @Given("Only the following weekly schedule is available from \$origin to \$destination on week starting Monday for day \$dayInWeek: \$scheduleData")
    def loadExclusiveAirSchedulesForWeekStartingMondayOfDay(String origin, String destination, String dayInWeek, ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        dynaStubsIntegration.prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(scheduleData,false,origin,destination,dayInWeek);

    }

    @Given("Only the following open jaw schedule is available from \$origin to \$destination to \$returnStation on \$departureDate and \$returnDate: \$scheduleData")
    def loadExclusiveAirSchedulesIntoDynaStubsOpenJaw(String origin, String destination, String returnStation, String departureDate, String returnDate,ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        dynaStubsIntegration.prepareSchedulesWithSpecificItineraries(scheduleData, origin, destination, departureDate, destination, returnStation, returnDate)
    }

    @Given("Only the following round trip schedule is available from \$origin to \$destination on \$departureDate and \$returnDate: \$scheduleData")
    def loadExclusiveAirSchedulesIntoDynaStubsRoundTrip(String origin, String destination, String departureDate, String returnDate, ExamplesTable scheduleData) {
        DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration();
        dynaStubsIntegration.prepareSchedulesWithSpecificItineraries(scheduleData, origin, destination, departureDate, destination, origin, returnDate)
    }

    @Given("I have the following car itinerary data: \$carItineraryTable")
    @When("I change my car itinerary to: \$carItineraryTable")
    def loadCarItineraryTable(ExamplesTable carItineraryTable) {
        List<Map<String, String>> rows = carItineraryTable.getRows()

        rows.each {
            def value = it.getAt("Value")
            String field = it.getAt("Field")

            if (field == "pickUpDate" || field == "dropOffDate") {
                if (value.matches("^[+|-]\\d+")) {
                    value = new GroovyExpression("new Date() $value").execute()
                }
            } else if (field == "carType") {
                value = CarRentalType.valueOf(value)
            } else if (field == "carVendor") {
                value = CarVendor.valueOf(value)
            }

            carItineraryData."${field}" = value
        }
        flow.isCarReservationPresent = true
    }

    @Given("I have the following hotel itinerary data: \$hotelItineraryTable")
    def loadHotelItineraryTable(ExamplesTable hotelItineraryTable) {
        Class <?> c = hotelItineraryData.getClass()
        List<Map<String, String>> rows = hotelItineraryTable.getRows()
        rows.each {
            String value = it.getAt("Value")
            String field = it.getAt("Field")

            if (field == "checkInDate" || field == "checkOutDate") {
                if (value.matches("^[+|-]\\d+")) {
                    Date date = new Date().plus(value.substring(1).toInteger())
                    c.getDeclaredField(field).set(hotelItineraryData, date)
                }
            }
            else if(field=="hotel"){
                hotelItineraryData.hotels.add(value)
            }else{
                c.getDeclaredField(field).set(hotelItineraryData, value)
            }
        }
        flow.hasHotel = true
    }

    @Given("I am: \$itineraryTable")
    void loadPassengersTable(ExamplesTable passengersTable) {
        List<Map<String, String>> rows = passengersTable.getRows()

        rows.each {
            String value = it.getAt("Value")
            int amongOfPassengers = value.toInteger()

            switch (it.getAt("Field")) {
                case "adultPassengerCount": amongOfPassengers.times { passengerData.addAdultPassenger() }
                    break
                case "seniorPassengerCount": amongOfPassengers.times { passengerData.addSeniorPassenger() }
                    break
                case "adultCheckInPassengerCount": flow.hasCheckInPassengers = true
                                                   amongOfPassengers.times { passengerData.addAdultPassenger() }
                    break
            }
        }
    }

    @Given("I am on the <page> page")
    @When("I am on the <page> page")
    def visitSomePage(@Named("page") String page) {
        switch (page) {
            case "Search Flight":
                searchFlightsPage.open()
                break

            case "Help":
                helpPage.open()
                break

            case "Home":
                homePage.open()
                break

            case 'View Itinerary':
            case 'View Reservation':
                viewReservationPage.open()
                break
            case "Create Account":
                myAccountPage.open()
                break
        }
    }

    @Given("I expect that booking a flight will fail")
    void disableFlagToExpectSuccessfulBooking() {
        flow.completePurchase = false;
    }

    @Given("I am a customer with an unique name")
    def createCustomerWithUniqueName() {
        String firstName = 'Jhon' + data.getLastNameForHotel()
        String lastName = 'Man' + data.getLastNameForHotel()
        flightSearchForm.selectAnAdultPassengerWithName(firstName, lastName)
    }

    @Given("I am flying a one-way \$carrier with \$routingType segment \$fare_class flight eligible for checkin from an origin airport that has Fly By lane access for both Ticket Counter and Security Checkpoint")
    def setupFlightWithItineraryTypeRoutingTypeFareClassDepartureAndArrivalStationBothFlyBy(String carrier, String routingType, String fare_class) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withRoutingType(routingType).withFareClass(fare_class).withOneWayStations(carrier).withDepartingCarrier(carrier).withFlightsDates("checkin").build())
    }

    @Given("I am flying a one-way \$carrier flight from an origin airport that has FlyBy available where Wanna Get Away fare is sold out")
    def setupFlightWithItineraryTypeDepartureStationBothFlyBy(String carrier) {
        flow.hasAir = true
        List<String> faresAvailabilityString = ["W:0"]
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withOneWayStations(carrier).withDepartingCarrier(carrier).withFlightsDates("tomorrow").withFaresAvailability(faresAvailabilityString).build())
    }

	@Given("BS is sold out for the outbound flight")
	def setupFlightWithItineraryTypeOutBoundSoldOutBusinessClass() {
		setupFlightWithItineraryTypeSoldOutBusinessClass(false,true)
	}

	@Given("BS is sold out for the inbound flight")
	def setupFlightWithItineraryTypeInBoundSoldOutBusinessClass() {
		setupFlightWithItineraryTypeSoldOutBusinessClass(true,false)
	}

	def setupFlightWithItineraryTypeSoldOutBusinessClass(Boolean inboundSoldOut = false,Boolean outboundSoldOut = false){
		java.util.Map<String,String> fareAvailabilityMap = new java.util.HashMap<String,String>()
		flow.hasAir = true
		fareAvailabilityMap.put("fareClass", "K")
		fareAvailabilityMap.put("availability", "0")
		fareAvailabilityMap.put("outBoundSold", outboundSoldOut.toString())
		fareAvailabilityMap.put("inBoundSold", inboundSoldOut.toString())
		itineraryData.faresAvailability.add(fareAvailabilityMap)
	}

    @Given("I am flying a one-way \$carrier flight leaving \$departureDays days from today from an origin airport that has FlyBy available")
    def setupFlightWithItineraryTypeDaysOutDepartureStationBothFlyBy(String carrier, String departureDays) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withOneWayStations(carrier).withDepartingCarrier(carrier).withFlightDatesFromToday(departureDays).build())
    }

    @Given("I am flying a round-trip \$carrier1 \$carrier2 with \$routingType segment \$fare_class flight eligible for checkin from an origin airport that has Fly By lane access for Security Checkpoint only")
    def setupFlightWithItineraryTypeRoutingTypeFareClassDepartureAndArrivalStationSCFlyBy(String carrier1, String carrier2, String routingType, String fare_class) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withRoutingType(routingType).withFareClass(fare_class).withRoundTripStations(carrier1, carrier2).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withFlightsDates("checkin").build())
        itineraryData.departureStation = "AUS"
        itineraryData.arrivalStation = "DAL"
    }

    @Given("I am flying a \$itineraryType with \$routingType segment \$fare_class flight from an origin airport that does not have Fly By lane access at all")
    def setupFlightWithItineraryTypeRoutingTypeFareClassDepartureAndArrivalStationNoFlyBy(String itineraryType, String routingType, String fare_class) {
        flow.hasAir = true
        String carrier = "Southwest"
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withRoutingType(routingType).withFareClass(fare_class).withDepartingCarrier(carrier).withArrivalCarrier(carrier).build())
        itineraryData.itineraryType = itineraryType
        itineraryData.departureStation = "PBI"
        itineraryData.arrivalStation = "TPA"
    }

    @Given("I am flying a round-trip \$carrier1 \$carrier2 flight from \$departureStation to \$arrivalStation")
    def setupRoundTripCarrierFlightDepartureAndArrivalStations(String carrier1, String carrier2, String departureStation, String arrivalStation) {
        flow.hasAir = true
        itineraryData.setValues(new ItineraryDataBuilder(randomTicketTypeGroup).withDepartingCarrier(carrier1).withArrivalCarrier(carrier2).withRoundTripStations(carrier1, carrier2).build())
        itineraryData.departureStation = departureStation
        itineraryData.arrivalStation = arrivalStation
    }

    @Given("I am flying a round-trip \$carrier1 \$carrier2 flight where the cities do not match the adult itinerary")
    def setupNoMatchingRoundTripFlight(String carrier1, String carrier2) {
        setupRoundTripCarrierFlightDepartureAndArrivalStations(carrier1, carrier2, "OKC", "MCI")
    }

    @When("I enter \$input in the \$field field")
    def enterDataInField(@Named("input") String inputData, @Named("field") String field) {
        //For auto complete data
        autoCompleteWidget.fillInAutoCompleteData(ELEMENT_IDS.get(field), inputData)
    }

    @When("I enter \$input in the \$field field and tab")
    def enterDataInFieldAndTab(@Named("input") String inputData, @Named("field") String field) {
        //For auto complete data
        autoCompleteWidget.fillInAutoCompleteData(ELEMENT_IDS.get(field), inputData, true)
    }


    @When("\$input fields are empty")
    @Alias("\$input field is empty")
    def clearFromToAndReturnField(@Named("input") List<String> list) {
        searchFlightsPage.clearFields(list)
    }

    @When("I click on checkout button")
    def checkOut() {
        searchFlightsPage.clickCheckoutButton()
    }

    @When("I am searching flights from \$fromPage")
    def searchFlightFrom(String fromPage){
        switch (fromPage) {
                case "home page":
                    homePage.open()
                    homePageFlightSearchForm.fillInHomePageSearchDataAndSubmit(itineraryData,false)
                    break
                case ['flight search page','plan trip page','search flight page']:
                    searchFlightsPage.open()
                    flightSearchForm.searchForFlight(itineraryData, itineraryData.departureDate, itineraryData.returnDate, "submitButton", false)
                    break
                case ['marketing page','international page']:
                    internationalSearchFlightsPage.open()
                    internationalSearchFlightsPage.searchForInternationalFlight(itineraryData)
                    break
                case ['snapshot page']:
                    myAccountPage.clickBookATrip()
                    flightSearchForm.searchForFlight(itineraryData, itineraryData.departureDate, itineraryData.returnDate, MODAL_SUBMIT_BUTTON_ID, false)
                    break
                default:
                fail("Story line did not have proper page name, expected page: " + fromPage)
                    break
            }

    }

    private def chooseRoutesAndStationsIfNotSpecified() {
        String departingCarrierCode = itineraryData.departingFlight_carrierCode
        String arrivingFlightCarrierCode = itineraryData.arrivingFlight_carrierCode
        String carrierRouteType = departingCarrierCode + "_" + arrivingFlightCarrierCode
        itineraryData.departureStation = randomTicketTypeGroup.getTicketType(carrierRouteType).getDepartingStation()
        itineraryData.arrivalStation = randomTicketTypeGroup.getTicketType(carrierRouteType).getArrivingStation()
    }

    private String determineFileName(String itineraryType) {
        return itineraryType.replace("Southwest", "WN").replace("AirTran", "FL").replace("-", "_") + "_Itinerary"
    }

    @Then("I see an Oops messages from \$fromPage indicating that \$message")
    def seeOopsMessage(String fromPage, String message){

        switch (fromPage) {
                case "home page":
                    homePage.shouldShowOopsMessage(message)
                    break
                case ['flight search page','plan trip page','search flight page']:
                    searchFlightsPage.shouldShowOopsMessage(message)
                    break
                case ['marketing page','international page']:
                    internationalSearchFlightsPage.shouldShowOopsMessage(message)
                    break
                case ['snapshot page']:
                    flightSearchForm.shouldShowOopsMessage(message)
                    break
                case ['select flight page']:
                    selectFlightsPage.shouldShowOopsMessage(message)
                    break
                case ['select new flights page']:
                    selectNewFlightsPage.shouldShowOopsMessage(message)
                    break
                case ['cancel air page']:
                    cancelAirPage.shouldShowOopsMessage(message)
                    break
                case ['check in page']:
                    checkinPage.shouldShowOopsMessage(message)
                    break
                default:
                fail("Story line did not have proper page name, expected page: " + fromPage)
                    break
            }

    }

    @Given("I have adult passengers data as follows: \$passengerTable")
    def loadPassengerTable(ExamplesTable passengerTable){
        passengerData.clearPassengers()
        List<Map<String,String>> rows = passengerTable.getRows()
        rows.each {
             passengerData.addAdultPassenger(it.firstName,it.middleName,it.lastName,it.suffix,it.isPOS.toBoolean()?:false)
        }
    }

    @When("I want to book a round-trip \$carrier1 \$carrier2 flight")
    def setupRoundTripCarrierFlightCleanCarExpectations(String carrier1, String carrier2) {
        flow.isCarReservationPresent = false
        itineraryData.carSpecification = null
        carItineraryData = null
        setupRoundTripCarrierFlight(carrier1, carrier2)
    }

    @Given("The following routes are available: \$routesData")
    def loadRoutes(ExamplesTable routesData) {
        new DynaStubsIntegration().prepareRoutesCache(routesData)
        new ToggleJmx().reloadRoutesCacheForServiceTier()
        new ToggleJmx().reloadRoutesCacheForWebTier()
    }

    @Given("The following one way routes are available: \$routesData")
    def loadOneWayRoutes(ExamplesTable routesData) {
        boolean isRoundTrip = false
        new DynaStubsIntegration().prepareRoutesCache(routesData,isRoundTrip)
        new ToggleJmx().reloadRoutesCacheForServiceTier()
        new ToggleJmx().reloadRoutesCacheForWebTier()
    }

    @Then("I should see the departure change plane info in the \$page")
    def shouldSeeDepartureChangePlaneInfo(String page){
        switch (page) {
            case ["Select Flights page","Select New Flights page"]:
                selectFlightsPage.verifyChangePlaneInfo()
                break
            case ["Pricing Page","New Pricing Page"]:
                pricePage.verifyChangePlaneInfoOnDepartureAirItinerary()
                break
            case "Confirmation page":
                confirmationPage.verifyChangePlaneInfoOnDepartureAirItinerary()
                break
            case ['Itinerary Change Confirmation page']:
                itineraryChangeConfirmationPage.verifyChangePlaneInfoOnDepartureAirItinerary()
                break
            case ['Check-in page']:
                checkinPage.verifyChangePlaneInfoOnDepartureAirItineraryOnCheckInPage()
                break
            case ['Cancel Air Reservation page']:
                cancelAirReservationPage.verifyChangePlaneInfoOnDepartureAirItineraryInCancelReservationPage()
                break
            case ['Change Air Reservation page']:
                changeAirReservationPage.verifyChangePlaneInfoOnDepartureAirItineraryInChangeReservationPage()
                break
            case "View Reservation Page":
                viewReservationPage.verifyChangePlaneInfoOnDepartureAirItinerary()
                break
            case "Early Bird page":
                earlyBirdConfirmationPage.verifyChangePlaneInfoOnDepartureAirItineraryInEarlyBirdPage()
                break
            case ['Upgrade to BS page']:
                upgradeToBusinessSelectPage.verifyStopsCitiesInfoOnDepartureAirItineraryOnBsPage()
                break
            default:
                fail("Story line did not have proper page name, expected page: " + page)
                break
        }
    }

    @When ("I change the toggle named: \$toggle to value: \$value")
    def changeToggleValueAndRefresh(String toggle, String value){
        if(value=="false"){
            new ToggleJmx().toggleOff(toggle,"WEB")
            new ToggleJmx().toggleOff(toggle,"SERV")
        }else{
            new ToggleJmx().toggleOn(toggle,"WEB")
            new ToggleJmx().toggleOn(toggle,"SERV")
        }
    }

    @When("I click on the Share Your Itinerary section")
    def clickShareYourItinerary() {
        purchasePage.clickShareYourItinerary()
    }

    @Then("I see the buddy name being displayed")
    def verifyShareYourItineraryData() {
        purchasePage.verifyShareYourItinerary()
    }

    @Then("I see How can we contact you section in purchase page")
    def VerifyHowCanWeContactYouSection(){
        purchasePage.VerifyHowCanWeContactYouSection()
    }

    @When ("I set up the toggle named: \$operation to value: \$value")
    @Given("I set up the toggle named: \$operation to value: \$value")
    def setUpMaintenanceMode(String operation, String value ){
        if(value=="false"){
            new ToggleJmx().maintenanceToggles(operation,false)
        }else{
            new ToggleJmx().maintenanceToggles(operation,true)
        }
    }

    @When("I verify cookie named \$cookie it is present in browser")
    def getACookieAndTemporaryStorage(String cookieName) {
        flow.cookie = basePage.getCookie(cookieName)
    }

    @Then("I shouldn't get cookie with javascript named \$cookie")
    def verifyIfCanAccessCookieWithJavascript(String cookieName) {
        if(flow.cookie.getName().equals(cookieName)) {
            def cookie = basePage.getCookieWithJavascript(cookieName)
            cookie.equals(null).shouldBe true, "Cookie $cookieName can access with javascript"
        }
    }

    @Given("I have a Discount like: \$dicount")
    def  givenDiscount(ExamplesTable discount){
         List<Map<String, String>> rows = discount.getRows()
         rows.each {
                   discountData."${it.Field}" = it.Value
         }
         new DynaStubsIntegration().prepareDiscount(discountData)
    }

    @When("I click on Air tab")
    def selectAirTab() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnBookAFlight()
        } else {
            globalNavigationHeader.clickOnAirLinkMenu()
        }
    }

    @When('I see the notification \$notificationMsg')
    def verifyNotificationDisplayed(String notificationMsg) {
        basePage.verifyNotificationServed(notificationMsg)
    }

    @Then('I do not see the notification \$notificationMsg')
    def verifyNotificationNotDisplayed(String notificationMsg) {
        basePage.verifyNotificationNotServed(notificationMsg)
    }

    @Given("the fare class Business Select is unavailable")
    def setupFlightWithItineraryTypeDepartureStationBSUnavailable() {
        flightSearchForm.businessSelectFareIsUnavailable(itineraryData)
    }

    @Given("I am flying an Early Bird eligible southwest airtran round-trip flight")
    def createEarlyBirdSouthwestAirtranRoundTrip() {
        Map yaml = itineraryData.yamlItineraryData()
        itineraryData.departureStation = yaml.codeShare.departAirport
        itineraryData.arrivalStation= yaml.codeShare.arriveAirport
        itineraryData.departingFlight_carrierCode = yaml.codeShare.southwestCarrier
        itineraryData.arrivingFlight_carrierCode = yaml.codeShare.airtranCarrier
        itineraryData.departingFlight_fareClass = "Anytime"
        itineraryData.arrivingFlight_fareClass = "Anytime"
        itineraryData.outboundRouting = "Nonstop"
        itineraryData.inboundRouting = "Nonstop"
        itineraryData.itineraryType = itineraryData.ROUND_TRIP_ITINERARY
        itineraryData.homePage = true

        itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDateForEarlyBird()
        itineraryData.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate)

        flow.hasAir = true
    }
}
