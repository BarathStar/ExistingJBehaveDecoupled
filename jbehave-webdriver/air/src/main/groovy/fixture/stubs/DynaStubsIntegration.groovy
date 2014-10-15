package fixture.stubs

import com.swacorp.common.clcs.CertificateStatus
import com.swacorp.common.flightDepartureControl.mbp.MobileBoardingPassResponse.MB_STATUS
import com.swacorp.dotcom.webscenarios.air.RRUser
import com.swacorp.dotcom.webscenarios.air.config.Domains
import fixture.exception.MobileBoardingPassVendorNotSpecifiedException
import fixtures.car.CarSpecification
import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import org.eclipse.jetty.util.ajax.JSON
import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import state.CarReservationData
import state.ScenarioState
import util.DiscountData
import util.ItineraryData
import util.ResetPasswordData
import builders.*
import static com.google.common.collect.Lists.newArrayList
import domain.*
import fixtures.air.*
import fixtures.hotel.*
import static fixtures.air.AccountSpecification.AccountStatusTypeSpec.HELD
import static fixtures.air.AccountSpecification.AccountStatusTypeSpec.INACTIVE
import fixtures.loyalty.*
import org.jbehave.core.model.ExamplesTable
import org.apache.commons.lang.RandomStringUtils
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import state.SWAContextView
import util.HotelItineraryData
import fixtures.air.enums.CreditCardRejectedReason

class DynaStubsIntegration {

    public static final DYNA_STUBS_PORT = getDynaStubsPort()
    public static final String URL = "${Domains.expectationsDomain}:${DYNA_STUBS_PORT}/"
    public static final String SHOPPING_SCHEDULES_URL = URL + "shoppingSchedules"
    public static final String RESERVATIONS_URL = URL + "reservations"
    public static final String MEMBERS_URL = URL + "customer"
    public static final String FLIGHT_SCHEDULES_URL = URL + "flightSchedules"
    public static final String REWARD_ACTIVITY_URL = URL + "rewardActivity"
    public static final String CANCEL_CAR_URL = URL + "cancelCar"
    public static final String CANCEL_HOTEL_URL = URL + "cancelHotel"
    public static final String CAR_SCHEDULE_URL = URL + "carSchedules"
    public static final String HOTEL_RESERVATION_URL = URL + "hotelReservation"
    public static final String ASSOCIATE_CUSTOMER_TO_COMPANY = URL + "associateWithCompany"
    public static final String RESET_PASSWORD_URL = URL + "resetPassword"
    public static final String GENERATE_CUSTOMER = URL + "generateCustomer"
    public static final String RR_USER_PASSWORD = "test123"
    public static final String LOW_FARE_CALENDAR_URL = URL + "lowFareCalendar"
    public static final String DESIGNATE_COMPANION_PASS = URL + "designateCompanionPass"
    public static final String PAYMENT = URL + "payment"
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    public static final String PAST_TRIP_URL = URL + "pastTrip"
    private static final String PROMOTION = URL + "promotions"
    private static final String PROMOTIONAL_PRODUCT = URL + "promotionalProduct"
    private static final String GROUP_CHECK_IN = URL + "groupCheckIn"
    private static final String GIFT_CARD_PURCHASE = URL + "giftCardPurchase"
    private static final String SVCM_BALANCE = URL + "svcmBalance"
    private static final String TRAVEL_FUNDS = URL + "travelFunds"
    private static final String SELECT_RATE_HOTEL = URL + "selectRateHotel"
    private static final String VALIDATE_ADDRESS = URL + "validateAddress"
    private static final String STATION_LOCATION_URL = URL + "stationLocation"
    private static final String PRICING = URL + "pricing"
    private static final String ROUTES_CACHE = URL + "routesCache"
    private static final String DEPARTURE_CONTROL = URL + "departureControl"
    private static final String DISCOUNT_URL = URL + "discounts"
    private static final String DISCOUNT_REMOVE_URL = URL + "removeDiscount"
    private static final String MOBILE_BOARDING_PASS_URL = URL + "mbpResponse"

    ScenarioState scenarioState
    CarReservationData carReservationData

    public static boolean useDynaStubs() {
        return System.getProperty("DYNA_STUBS") != null
    }

    void prepareShoppingSchedules(ItineraryData itineraryData, boolean availableBusinessProduct=true) {
        if (useDynaStubs()) {
            ScheduleSpecification scheduleSpecification = createScheduleSpecificationFromItineraryData(itineraryData)
            java.util.Map<String,String> fareAvailability = itineraryData.faresAvailability[0]
            if (!itineraryData.faresAvailability.empty && fareAvailability.get("fareClass")=="W"){
                scheduleSpecification.setHasOutboundWannaGetAwayFares(false)
            }
			if (!itineraryData.faresAvailability.empty && fareAvailability.get("fareClass")=="K") {
                scheduleSpecification.setHasOutboundBusinessFares(Boolean.valueOf(fareAvailability.get("outBoundSold")) ? true : false)
				scheduleSpecification.setHasInboundBusinessFares(Boolean.valueOf(fareAvailability.get("inBoundSold")) ? true : false)
			}
            if(!availableBusinessProduct){
                scheduleSpecification.setBusinessProductAvailability(availableBusinessProduct)
            }
            postObject(SHOPPING_SCHEDULES_URL, scheduleSpecification)
        }
    }

    /**
     * Submit dynastubs expectations for the specified schedule, to both shopping and flight schedules, without generation of random itineraries.
     * See the ScheduleSpecificationBuilderTest for examples of the table format.
     * @param scheduleData examples table with schedule details
     * @param searchTerms groups of origin, destination, departureDate. Only two or three groups makes sense here. (3 or 6 varargs)
     */
    public void prepareSchedulesWithSpecificItineraries(ExamplesTable scheduleData, String... searchTerms)
    {
        prepareFlightSchedules(scheduleData, false, searchTerms)
        prepareShoppingSchedulesWithSpecificItineraries(scheduleData, false, searchTerms)
    }

    /**
     * Submit dynastubs expectations for the specified schedule for view flight schedules functionality, without generation of random itineraries.
     * See the ScheduleSpecificationBuilderTest for examples of the table format.
     * @param scheduleData examples table with schedule details
     * @param searchTerms groups of origin, destination, departureDate. Only two or three groups makes sense here. (3 or 6 varargs)
     */
    public void prepareFlightSchedulesWithSpecificItineraries(ExamplesTable scheduleData, String... searchTerms)
    {
        prepareFlightSchedules(scheduleData, false, searchTerms)
    }

    /**
     * Submit dynastubs expectations for the specified schedule, to both shopping and flight schedules, and also request generation of random itineraries for the first OD.
     * See the ScheduleSpecificationBuilderTest for examples of the table format.
     * @param scheduleData examples table with schedule details
     * @param withRandomItinerariesForOD If this is set to true, then there will be random schedules added for the first OD.
     */
    public void prepareSchedulesWithRandomItineraries(ExamplesTable scheduleData)
    {
        prepareFlightSchedules(scheduleData, true)
        prepareShoppingSchedules(scheduleData, true)
    }

    public void prepareDiscount(DiscountData discountData){
                DiscountSpecificationBuilder builder = new DiscountSpecificationBuilder();
                DiscountSpecification discountSpecification= builder.withId(discountData.id)
                                                               .withCode(discountData.code)
                                                               .withUnlimited(discountData.isUnlimited)
                                                               .withUsageMaxLimit(discountData.usageMaxLimit)
                                                               .withSaleBeginDateTime(discountData.saleBeginDateTime)
                                                               .withSaleEndDateTime(discountData.saleEndDateTime)
                                                               .withRestrictRapidReward(discountData.isRestrictRapidReward)
                                                               .withMessageText(discountData.messageText)
                                                               .withType(discountData.type)
                                                               .withState(discountData.state)
                                                               .withWebSite(discountData.webSite)
                                                               .withSupportedFareType(discountData.supportedFareType)
                                                               .withCat25Mapping(discountData.cat25Mapping)
                                                               .withRoundTripRestriction(discountData.isRoundTripRestriction).build()
                postObject(DISCOUNT_URL,discountSpecification)
    }

    public void prepareDiscount(DiscountSpecification discountSpecification) {
        postObject(DISCOUNT_URL, discountSpecification)
    }

    public void removeDiscount(DiscountSpecification discountSpecification) {
        postObject(DISCOUNT_REMOVE_URL, discountSpecification)
    }

    public int prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(LocalDate referenceDate, ExamplesTable flightData, boolean withRandomItinerariesForOD, String origin, String destination, String dayInWeek) {
        DateTime referenceTime = referenceDate.toDateTimeAtStartOfDay();
        RelativeDateTimeParser dateTimesParser = new RelativeDateTimeParser(referenceTime);
        LocalDate startDate = dateTimesParser.parseRelativeDateTime(dayInWeek).toLocalDate();

        LocalDate scheduleStartDate = startDate.withDayOfWeek(DateTimeConstants.MONDAY);

        int days = Days.daysBetween(referenceDate, scheduleStartDate).getDays();
        String startString = "+" + days + "d";
        String toEndString = "+6d";

        prepareFlightSchedulesForDateRange(flightData, withRandomItinerariesForOD, origin, destination, startString, toEndString)
        return days;
    }

    public int prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(ExamplesTable flightData, boolean withRandomItinerariesForOD, String origin, String destination, String dayInWeek) {
        return prepareFlightSchedulesForWeekStartingMondayOnOrBeforeDay(LocalDate.now(), flightData,
                withRandomItinerariesForOD, origin, destination, dayInWeek);
    }

    public void prepareFlightSchedulesForDateRange(ExamplesTable flightData, boolean withRandomItinerariesForOD, String origin, String destination, String startDate, String endDate) {
        if (useDynaStubs()) {
            ScheduleSpecificationBuilder ssb = new ScheduleSpecificationBuilder();
            ssb.withRandomItinerariesForOD(withRandomItinerariesForOD);
            ssb.applyCommands(flightData.getRows());
            ssb.withScheduleSearchTerms(origin, destination, startDate, endDate);
            ScheduleSpecification scheduleSpecification = ssb.build();

            postObject(FLIGHT_SCHEDULES_URL, scheduleSpecification)
        }
    }

    /**
     * Submit dynastubs expectations for the specified flight schedule, keyed to the specified search terms.
     * See the ScheduleSpecificationBuilderTest for examples of how to do this.
     *
     * @param flightData examples table with schedule details
     * @param withRandomItinerariesForOD If this is set to true, then there will be random schedules added for the first OD.
     * @param searchTerms search terms, in groups of 3: origin, destination, departureDate
     */
    public void prepareFlightSchedules(ExamplesTable flightData, boolean withRandomItinerariesForOD, String... searchTerms) {
        if (useDynaStubs()) {
            ScheduleSpecificationBuilder ssb = new ScheduleSpecificationBuilder();
            ssb.withRandomItinerariesForOD(withRandomItinerariesForOD);
            ssb.applyCommands(flightData.getRows());

            if ((searchTerms.length % 3) != 0) {
                  throw new RuntimeException("search terms must contain groups of origin, destination, date. "
                          + searchTerms.length + " values can not be correct in this case.");
            }

            for (int searchTermBase=0; searchTermBase<searchTerms.length; searchTermBase+=3) {
                String origin = searchTerms[searchTermBase+0]
                String destination = searchTerms[searchTermBase+1]
                String departureDate = searchTerms[searchTermBase+2]
                ssb.withScheduleSearchTerms(origin, destination, departureDate);
            }

            ScheduleSpecification scheduleSpecification = ssb.build();

            postObject(FLIGHT_SCHEDULES_URL, scheduleSpecification)
        }
    }

    /**
     * Submit dynastubs expectations for the specified flight schedule.
     * See the ScheduleSpecificationBuilderTest for examples of how to do this.
     * @param flightData examples table with schedule details
     * @param withRandomItinerariesForOD If this is set to true, then there will be random schedules added for the first OD.
     */
    public void prepareFlightSchedules(boolean withRandomItinerariesForOD, ExamplesTable flightData) {
        if (useDynaStubs()) {
            prepareFlightSchedules(flightData, withRandomItinerariesForOD, new String[0])
        }
    }

    /**
     * Submit dynastubs expectations for the specified shopping schedule.
     * See the ScheduleSpecificationBuilderTest for examples of how to do this.
     *
     * @param flightData examples table with schedule details
     * @param withRandomItinerariesForOD If this is set to true, then there will be random schedules added for the first OD.
     * @param searchTerms search terms in groups of 3: origin, destination, departure date
     */
    public void prepareShoppingSchedulesWithSpecificItineraries(ExamplesTable flightData, boolean withRandomItinerariesForOD = false, String... searchTerms) {
        if (useDynaStubs()) {
            ScheduleSpecificationBuilder ssb = new ScheduleSpecificationBuilder();
            ssb.withRandomItinerariesForOD(withRandomItinerariesForOD);
            ssb.applyCommands(flightData.getRows());

            for (int termBase=0;termBase<searchTerms.length; termBase+=3) {
                String origin = searchTerms[termBase+0]
                String destination = searchTerms[termBase+1]
                String departureDate = searchTerms[termBase+2]
                ssb.withScheduleSearchTerms(origin,destination,departureDate);
            }

            ScheduleSpecification scheduleSpecification = ssb.build();

            postObject(SHOPPING_SCHEDULES_URL, scheduleSpecification)
        }
    }

    void prepareShoppingSchedules(ReservationSpecification reservationSpecification) {
        if (useDynaStubs()) {
            ScheduleSpecification scheduleSpecification = new ScheduleSpecification([reservationSpecification.outbound, reservationSpecification.inbound])
            postObject(SHOPPING_SCHEDULES_URL, scheduleSpecification)
        }
    }

    void prepareWeeklyOrDailyFlightSchedules(ItineraryData itineraryData) {
        if (useDynaStubs()) {
            ScheduleSpecification scheduleSpecification = createScheduleSpecificationFromItineraryData(itineraryData)
            postObject(FLIGHT_SCHEDULES_URL, scheduleSpecification)
        }
    }

    void prepareWeeklyFlightSchedules(itineraryData) {
        if (useDynaStubs()) {
            ScheduleSpecification scheduleSpecification = createScheduleSpecificationFromItineraryData(itineraryData)
            DateTime searDate = scheduleSpecification.getFirstDepartureDateTime()
            LocalDate bgDate = searDate.withDayOfWeek(DateTimeConstants.MONDAY).toLocalDate()
            LocalDate endDate = searDate.withDayOfWeek(DateTimeConstants.SUNDAY).toLocalDate()
            scheduleSpecification.setScheduleSearchKey(scheduleSpecification.getFirstOrigin(),scheduleSpecification.getLastDestination(),bgDate,endDate)
            postObject(FLIGHT_SCHEDULES_URL, scheduleSpecification)
        }
    }

    void prepareLowFareCalendar(String departureStation, String arrivalStation, String returnStation, Date departureDate, Date returnDate) {
            if (useDynaStubs()) {
                    GregorianCalendar startDate = new GregorianCalendar()
                    GregorianCalendar endDate = new GregorianCalendar()
                    startDate.add(GregorianCalendar.MONTH,2)
                    startDate.add(GregorianCalendar.DAY_OF_MONTH, -(startDate.get(GregorianCalendar.DAY_OF_MONTH) - 1))
                    endDate.add(GregorianCalendar.MONTH,2)
                    endDate.add(GregorianCalendar.DAY_OF_MONTH, endDate.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) - endDate.get(GregorianCalendar.DAY_OF_MONTH))

                LowFareCalendarSpecification lowFareCalendarSpecification =
                    new LowFareCalendarSpecification(
                            departureStation,
                            arrivalStation,
                            (returnStation != null && returnStation != "") ? returnStation : departureStation,
                            (departureDate != null) ? departureDate.format(DATE_FORMAT) : startDate.time.format(DATE_FORMAT),
                            (returnDate != null) ? returnDate.format(DATE_FORMAT) : endDate.time.format(DATE_FORMAT)
                    )

                postObject(LOW_FARE_CALENDAR_URL, lowFareCalendarSpecification)
            }
    }

    void prepareLowFareCalendar(ItineraryData itineraryData) {
        if (useDynaStubs()) {
            String departureStation = itineraryData.departureStation
            String arrivalStation = itineraryData.arrivalStation
            String returnStation = itineraryData.returnStation
            Date departureDate = itineraryData.departureDate
            Date returnDate = itineraryData.returnDate

            if(itineraryData.itineraryType.equals(ItineraryData.ONE_WAY_ITINERARY)) {
                prepareLowFareCalendar(departureStation, arrivalStation, returnStation, departureDate, departureDate)
            } else {
                prepareLowFareCalendar(departureStation, arrivalStation, returnStation, departureDate, returnDate)
            }
        }
    }

    void prepareLowFareCalendar(String origin, String destination) {
        if (useDynaStubs()) {
            prepareLowFareCalendar(origin, destination, null, null, null)
        }
    }

    void prepareResetPassword(final ResetPasswordData resetPasswordData) {
        if (useDynaStubs()) {
            ResetPasswordSpecification resetPasswordSpecification = new ResetPasswordSpecification()
            resetPasswordSpecification.setTokenState(resetPasswordData.tokenStatus)

            String generatedToken
            generatedToken = (String) postObject(RESET_PASSWORD_URL, resetPasswordSpecification)
            resetPasswordData.token = generatedToken;
        }
    }

    void prepareCarSchedules(CarReservationData carReservationData) {
        if (useDynaStubs()) {
            CarSpecification carSpecification = createCarSpecificationFromReservationData(carReservationData)
            postObject(CAR_SCHEDULE_URL, carSpecification)
        }
    }

    AirReservation createNoShowedReservation() {
        FlightSegmentSpecification flightSegmentWithPastDeparture = new FlightSegmentSpecificationBuilder().withDepartureDate(new LocalDate().minusDays(2)).build();
        FlightSegmentSpecification flightSegmentWithFutureDeparture = new FlightSegmentSpecificationBuilder().build();
        ReservationSpecBuilder reservationSpecification = new ReservationSpecBuilder().withOriginDestination(new OriginDestinationSpecification(flightSegmentWithPastDeparture)).withOriginDestination(new OriginDestinationSpecification(flightSegmentWithFutureDeparture)).withNoShowFlight().withPassengers()

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)
        Map reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        reservationSpec.putAll(reservationInfoAsMap)
        return new AirReservation(reservationSpec, reservationSpecification.getPassengers())
    }

    AirReservation createNoShowedReservationForFlightInProgress() {

        FlightSegmentSpecification flightSegmentInProgress = new FlightSegmentSpecificationBuilder().withDepartureDate(new LocalDate()).withDepartureTime(new LocalTime().minusMinutes(30)).withArrivalDate(new LocalDate()).withArrivalTime(new LocalTime().plusHours(2)).build();
        FlightSegmentSpecification flightSegmentWithFutureDeparture = new FlightSegmentSpecificationBuilder().build();
        ReservationSpecBuilder reservationSpecification =
        new ReservationSpecBuilder().withOriginDestination(new OriginDestinationSpecification(flightSegmentInProgress)).withOriginDestination(new OriginDestinationSpecification(flightSegmentWithFutureDeparture)).withNoShowFlight().withPassengers()

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)
        Map reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        reservationSpec.putAll(reservationInfoAsMap)
        return new AirReservation(reservationSpec, reservationSpecification.getPassengers())
    }

    AirReservation createSwabizReservationForFlightInProgress() {

        FlightSegmentSpecification flightSegmentInProgress = new FlightSegmentSpecificationBuilder().withDepartureDate(new LocalDate()).withDepartureTime(new LocalTime().minusMinutes(30)).withArrivalDate(new LocalDate()).withArrivalTime(new LocalTime().plusHours(2)).build();
        FlightSegmentSpecification flightSegmentWithFutureDeparture = new FlightSegmentSpecificationBuilder().build();
        ReservationSpecBuilder reservationSpecification =
        new ReservationSpecBuilder().withOriginDestination(new OriginDestinationSpecification(flightSegmentInProgress)).withOriginDestination(new OriginDestinationSpecification(flightSegmentWithFutureDeparture)).withSwabiz().withPassengers()

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)
        Map reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        reservationSpec.putAll(reservationInfoAsMap)
        return new AirReservation(reservationSpec, reservationSpecification.getPassengers())
    }

    AirReservation createAirReservation(ItineraryData itineraryData, Map params, List<Passenger> passengers = null) {

        ReservationSpecBuilder reservationSpecification = new ReservationSpecBuilder().withItinerary(itineraryData)

        if (params?.upgradeToBS == true ) {
            reservationSpecification.withUpgradeToBS()
        }
        if (params?.eligibleForCheckIn || itineraryData.isValidForCheckin.toBoolean()) {
            reservationSpecification.withDepartureTimeEligibleForCheckin(itineraryData);
        }
        if (params?.eligibility) {
            reservationSpecification.withEligibility(params.eligibility)
        }

        if (params?.eligibleForReturnCheckIn) {
            reservationSpecification.withReturnTimeEligibleForCheckin(itineraryData);
        }

        if (params?.noShowed) {
            reservationSpecification.withNoShowFlight();
        }

        if (params?.tripName) {
            reservationSpecification.withTripName(params.tripName)
        }

        if (params?.unaccompaniedMinor) {
            reservationSpecification.withUnaccompaniedMinor();
        }

        if (params?.swabiz) {
            reservationSpecification.withSwabiz()
        }

        if (params?.canceled) {
            reservationSpecification.withCanceled()
        }

        if (params?.creditCardNumber) {
            reservationSpecification.withCreditCardNumber(params.creditCardNumber)
        }

        if (params?.tripManagementDatabaseDown == true && (!itineraryData.hasCarReservation() || !itineraryData.hasHotelReservation())) {
            reservationSpecification.withTripManagementDown()
        }

        if (params?.tripManagementDownForSenior ==  true) {
            reservationSpecification.withTripManagementDownForSenior()
        }

        if (params?.upcomingTripServiceDown == true && (!itineraryData.hasCarReservation() || !itineraryData.hasHotelReservation())) {
            reservationSpecification.withUpcomingTripServiceDown()
        }

        if (params?.outboundBoardingPassIssued) {
            reservationSpecification.withOutboundBoardingPassIssued()
        }

        if (params?.inboundBoardingPassIssued) {
            reservationSpecification.withInboundBoardingPassIssued()
        }

        if(params?.missingAirTranTicketNumber) {
            reservationSpecification.withMissingAirTranTicketNumber()
        }

        if (params?.crossPnrNumber) {
            reservationSpecification.withCrossPnrNumber(params.crossPnrNumber)
        }

        if (params?.certificateNumber) {
            reservationSpecification.withCertificateNumber(params.certificateNumber)
        }

        if (params?.secondCertificateNumber) {
            reservationSpecification.withSecondCertificateNumber(params.secondCertificateNumber)
        }

        if (params?.certificateForPromoCert) {
            reservationSpecification.withCertificateForPromoCert(params.certificateForPromoCert)
        }

         if (params?.paymentType) {
            reservationSpecification.withPaymentType(params.paymentType)
        }

        if(params?.myIdTravelMark) {
            reservationSpecification.withMyIdTravelMark(params.myIdTravelMark)
        }

        if(params?.inboundFlightNumber) {
            reservationSpecification.withInboundFlightNumber(params.inboundFlightNumber)
        }

        if(params?.sodaAffectedMark) {
            reservationSpecification.withSodaAffectedMark(params.sodaAffectedMark)
        }

        if(params?.pnrSource){
            itineraryData.pnrSource = true
            reservationSpecification.withPnrSource(params.pnrSource)
        }

        if(params?.isGroupPNR){
            reservationSpecification.withGroupPNR(params.isGroupPNR)
        }

        if (params?.rapidRewardsAccountNumber) {
            if (passengers == null || passengers.isEmpty()) {
                Passenger passenger = reservationSpecification.createMockPassenger()
                passenger.setRapidRewardsNumber(params.rapidRewardsAccountNumber)
                passenger.setRapidRewardsType(params.rapidRewardsAccountType)

                passengers = new ArrayList()
                passengers.add(passenger)
            }
            else {
                passengers.each() {
                    it.setRapidRewardsNumber(params.rapidRewardsAccountNumber)
                    it.setRapidRewardsType(params.rapidRewardsAccountType)
                }
            }
        }

        reservationSpecification = reservationSpecification.withPassengers(passengers)

        String accountNumber = getLoggedInPassengerAccountNumber(passengers)

        if (itineraryData.hasCarReservation()) {
            CreateCarSpecification createCarSpecification = new CreateCarSpecification().withCarItinerary(itineraryData.carSpecification).withDriver(passengers.get(0)).withAccountNumber(accountNumber)

            if (params?.tripManagementDatabaseDown == true) {
                createCarSpecification.shouldNotPersist()
            }

            if (itineraryData.withoutAirTrip){
                reservationSpecification.withoutAirTrip()
            }

            reservationSpecification.withCarSpecification(createCarSpecification)
        }

        if (itineraryData.hasHotelReservation()) {
            CreateHotelSpecification createHotelSpecification = new CreateHotelSpecification().withHotelItinerary(itineraryData.hotelSpecification).withCustomer(passengers.get(0)).withAccountNumber(accountNumber)

            if (params?.tripManagementDatabaseDown == true) {
                createHotelSpecification.shouldNotPersist()
            }
            reservationSpecification.withHotelSpecification(createHotelSpecification)
        }

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)
        Map reservationInfoAsMap = new HashMap()
        try {
        reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        } catch(IllegalStateException e) {
              System.out.println("JSON parse Error - Illegal state parsing : "+reservationInfoAsJson)
              e.printStackTrace()
        } catch(GroovyCastException e) {
              System.out.println("JSON parse Error - GroovyCastException parsing : "+reservationInfoAsJson)
              e.printStackTrace()
        }

        reservationSpec.putAll(reservationInfoAsMap)
        return new AirReservation(reservationSpec, reservationSpecification.getPassengers())
    }

    void createRRMember(RRUser rrUser) {
        def jsonData = [
                firstName: rrUser.getRRFirstName(),
                lastName: rrUser.getRRLastName(),
                number: rrUser.getRRNumber(),
                password: rrUser.getRRPassword(),
                points: rrUser.getPoints(),
                credits: rrUser.getCredits(),
                awards: rrUser.getAwards(),
                userType: rrUser.getRRAccountType(),
                dateOfBirth: rrUser.getDateOfBirth(),
                lastAccrualDate: rrUser.getLastAccrualDate()
        ]
        post(MEMBERS_URL, jsonData)
    }

    RRUser createRRMember(RRUser rrUser, Passenger passenger) {
        RRUser dynaStubsRRUser = new RRUser(rrUser, passenger)
        new DynaStubsIntegration().createRRMember(dynaStubsRRUser)
        return dynaStubsRRUser
    }

    void associateCustomerToCompany(String customerId, String companyId) {
        postObject(ASSOCIATE_CUSTOMER_TO_COMPANY, new CompanyAssociationSpecification(customerId, companyId))
    }

    void createRewardActivity(RRUser rrUser) {

        if (DynaStubsIntegration.useDynaStubs() == false) {
            return;
        }

        def postingDateTime = new LocalDateTime()

        RewardsActivitySpecification specification = new RewardsActivitySpecification();
        specification.setCustomerNumber(rrUser.getRRNumber());

        RewardsActivitySpecification.Record creditTransfer = new RewardsActivitySpecification.Record();
        creditTransfer.setAccruedCredits(2);
        creditTransfer.setCategory("APLUS");
        creditTransfer.setPostingDate(postingDateTime);
        creditTransfer.setTotalPoints(0);
        creditTransfer.setTransferTypeName("TransferAccrualCredits");
        specification.addActivityRecords(creditTransfer);

        RewardsActivitySpecification.Record pointReversal = new RewardsActivitySpecification.Record();
        pointReversal.setAccruedCredits(0);
        pointReversal.setCategory("APLUS");
        pointReversal.setPostingDate(postingDateTime);
        pointReversal.setTotalPoints(300);
        pointReversal.setTransferTypeName("TransferReversalPoints");
        specification.addActivityRecords(pointReversal);

        RewardsActivitySpecification.Record awardReversal = new RewardsActivitySpecification.Record();
        awardReversal.setAccruedCredits(8);
        awardReversal.setCategory("APLUS");
        awardReversal.setPostingDate(postingDateTime);
        awardReversal.setTotalPoints(0);
        awardReversal.setCertificateSubType("RR1.0 Transitional-STND");
        awardReversal.setCertificateNumber("12345");
        awardReversal.setTransferTypeName("TransferReversalAward");
        specification.addActivityRecords(awardReversal);

        postObject(REWARD_ACTIVITY_URL, specification);

    }

    void cancelCarReservation(CarReservation carReservation) {
        if (DynaStubsIntegration.useDynaStubs()) {
            def jsonData = [
                    pickUpDate: carReservation.pickUpDate,
                    renterFirstName: carReservation.renterFirstName,
                    renterLastName: carReservation.renterLastName,
                    confirmationNumber: carReservation.confirmationNumber
            ]
            post(CANCEL_CAR_URL, jsonData)
        }
    }

    void cancelHotelReservation(HotelReservation hotelReservation) {
        if (DynaStubsIntegration.useDynaStubs()) {
            def jsonData = [
                    checkInDate: hotelReservation.checkInDate,
                    customerFirstName: hotelReservation.customerFirstName,
                    customerLastName: hotelReservation.customerLastName,
                    confirmationNumber: hotelReservation.confirmationNumber
            ]
            post(CANCEL_HOTEL_URL, jsonData)
        }
    }

    private def getPostingType(url) {
        switch (url) {
            case SHOPPING_SCHEDULES_URL:
                return "shopping"
            case RESERVATIONS_URL:
                return "reservations"
            case MEMBERS_URL:
                return "members"
            case FLIGHT_SCHEDULES_URL:
                return "schedules"
            case REWARD_ACTIVITY_URL:
                return "rewards"
            case CANCEL_CAR_URL:
                return "cancelCar"
            case CANCEL_HOTEL_URL:
                return "cancelHotel"
            case CAR_SCHEDULE_URL:
                return "carSchedule"
            case GENERATE_CUSTOMER:
                return "generateCustomer"
            case DEPARTURE_CONTROL:
                return "departureControl"
            case PAYMENT:
                return "payment"
            case DESIGNATE_COMPANION_PASS:
                return "companionPass"
            default: return "unknown"
        }
    }

    private def post(url, jsonData) {
        def jsonDataAsString = JSON.toString(jsonData)
        String postingType = getPostingType(url)

        println "On Thread: " + Thread.currentThread().getId() + ", Posting Dyna Stubs $postingType expectation: " + jsonDataAsString
        def result = new HttpPoster().to(url).withObject(jsonDataAsString).postObject()
        if (result.startsWith("Oops")) {
            throw new RuntimeException("Error communicating with DynaStubs: " + result)
        }
        println "On Thread: " + Thread.currentThread().getId() + ", Result of Dyna Stubs $postingType expectation: " + result
        return result
    }

    private def postObject(url, specification) {
        String postingType = getPostingType(url)

        // If a specification implements SpecificationSource, set the source story name on it,
        // to help track data conflicts in dynastubs
        if (specification instanceof SpecificationSource)
        {
            SpecificationSource specAsSourceProvider = (SpecificationSource) specification;
            String sourceName = SWAContextView.retrievePageContext().currentStory;
            specAsSourceProvider.setSpecificationSource(sourceName)
        }

        println "On Thread: " + Thread.currentThread().getId() + ", Posting Dyna Stubs $postingType object expectation"
        if (specification != null) {
            println "On Thread: " + Thread.currentThread().getId() + ", Specification data: " + specification.toString()
        }
        def result = new HttpPoster().to(url).withObject(specification).postObject()
        if (result.startsWith("Oops")) {
            throw new RuntimeException("Error communicating with DynaStubs: " + result)
        }
        println "On Thread: " + Thread.currentThread().getId() + ", Result of Dyna Stubs $postingType object expectation: " + result
        return result
    }

    private static String getDynaStubsPort() {
        String propertyName = "DYNA_STUBS_PORT"
        String port = System.getProperty(propertyName)
        if (port == null) {
            port = System.getenv().get(propertyName)
        }
        return isNotNull(port) ? port : "4567"
    }

    private static boolean isNotNull(Object value) {
        return value != null;
    }

    private ScheduleSpecification createScheduleSpecificationFromItineraryData(ItineraryData itineraryData) {
        OriginDestinationFactory factory = new OriginDestinationFactory()
        List<OriginDestinationSpecification> specifications = newArrayList();
        OriginDestinationSpecification outboundONDSpecification = factory.createOutboundOriginDestinationSpecification(itineraryData)
        specifications.add(outboundONDSpecification);

        if (!itineraryData.isOneWay() && (itineraryData.itineraryType != null)) {
            OriginDestinationSpecification inboundONDSpecification = factory.createInboundOriginDestinationSpecification(itineraryData)
            specifications.add(inboundONDSpecification);
        }

        ScheduleSpecification scheduleSpecification = new ScheduleSpecification(specifications)

        scheduleSpecification.setWithStopCityInfo(itineraryData.hasOutboundStopCities());

        return scheduleSpecification
    }


    private CarSpecification createCarSpecificationFromReservationData(CarReservationData reservationData){
        return  new CarSpecificationBuilder().withPickUpLocation(reservationData.pickUpStation)
                                    .withDropOffLocation(reservationData.dropOffStation)
                                    .withPickUpDate(new LocalDate(reservationData.pickUpDate))
                                    .withDropOffDate(new LocalDate(reservationData.dropOffDate))
                                    .withCarrierCode(reservationData.carVendor.vendorCode)
                                    .withAgencyName(reservationData.agentName)
                                    .withPromoCode(reservationData.promoCode)
                                    .withRentalDeskLocation(reservationData.rentalDeskLocation)
                                    .withCarType(reservationData.carType.toString())
                                    .withPickUpTime(reservationData.getPickUpTime())
                                    .withMessageError(reservationData.messageError)
                                    .build();
    }

    RRUser generateRRUser() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().build()
        return createCustomer(accountSpecification)
    }

    RRUser generateCustomer() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withAccountTypeSpec(AccountSpecification.AccountTypeSpec.CUSTOMER).build()
        return createCustomer(accountSpecification)
    }

     RRUser generateRRUserWithPromotionalCertificates() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().build()
        RRUser rrUser = createCustomer(accountSpecification)
        rrUser.setCertificateNumber(RandomStringUtils.randomNumeric(27))
        rrUser.setSecondCertificateNumber(RandomStringUtils.randomNumeric(27))
        return rrUser
    }

    RRUser createCustomer(AccountSpecification accountSpecification) {
        String customerNumber = postObject(GENERATE_CUSTOMER, accountSpecification)
        return new RRUser(accountSpecification, customerNumber, RR_USER_PASSWORD)
    }

    RRUser generateRRUserWithoutUserNameAndSecurityQuestion() {
        AccountSpecification customerSpecification = new AccountSpecificationBuilder().withUserName(null).withSecurityQuestion(null).withSecurityAnswer(null).withSecurityQuestionTwo(null).withSecurityAnswerTwo(null).build()

        return createCustomer(customerSpecification)
    }

    RRUser generateCustomerWithoutUserNameAndSecurityQuestion() {
        AccountSpecification customerSpecification = new AccountSpecificationBuilder().withAccountTypeSpec(AccountSpecification.AccountTypeSpec.CUSTOMER).withUserName(null).withSecurityQuestion(null).withSecurityAnswer(null).withSecurityQuestionTwo(null).withSecurityAnswerTwo(null).build()

        return createCustomer(customerSpecification)
    }

    RRUser generateRRUserWithAccountHeld() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withAccountStatusTypeSpec(HELD).build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithAccountInactive() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withAccountStatusTypeSpec(INACTIVE).build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithCompanionPassAchieved() {
        AccountSpecification customerSpecification = new AccountSpecificationBuilder().withCompanionPassAchieved().build()

        return createCustomer(customerSpecification)
    }

    PastTripSummarySpecification addPastTrip(String customerNumber, ItineraryData itineraryData) {
        if (itineraryData.isRoundTrip()) {
            return addRoundTripPastTrip(customerNumber, itineraryData)
        } else if (itineraryData.isOpenJaw()) {
            return addOpenJawPastTrip(customerNumber, itineraryData)
        } else if (itineraryData.isOneWay()) {
            return addOneWayPastTrip(customerNumber, itineraryData)
        }
    }

    def generateSelectHotelSpecification(String hotelId) {
        SelectHotelSpecification hotelSpecification = new SelectHotelSpecification(hotelId);
        postObject(SELECT_RATE_HOTEL, hotelSpecification)
    }

    def generateSelectedHotelSpecification(String locationId, String hotelName, String hotelLocation, String hotelState, Date checkInDate, Date checkOutDate) {
        SelectHotelSpecification hotelSpecification = new SelectHotelSpecification(locationId, hotelName, hotelLocation, hotelState, new LocalDate(checkInDate), new LocalDate(checkOutDate));
        postObject(SELECT_RATE_HOTEL, hotelSpecification)
    }

    def generateSelectedHotelSpecification(HotelItineraryData hotelData) {
        String locationId=hotelData.locationId
        Date checkInDate= hotelData.checkInDate
        Date checkOutDate = hotelData.checkOutDate
        boolean thereIsNotHotelsForLocation=false;
        if (hotelData.thereIsNotHotelsForLocation!=null){
            thereIsNotHotelsForLocation=hotelData.thereIsNotHotelsForLocation.equalsIgnoreCase("true")?true:false
        }
        List<String> hotels= hotelData.hotels
        String city= hotelData.city
        SelectHotelSpecification hotelSpecification = new SelectHotelSpecification(locationId, new LocalDate(checkInDate), new LocalDate(checkOutDate),thereIsNotHotelsForLocation,hotels,city);
        postObject(SELECT_RATE_HOTEL, hotelSpecification)
    }

    def generateValidateAddressResponseSpecification (String line1, String line2, String city, String zipCode, String firstName, String middleName,
                                                      String lastName, String state, String country, String addressType, boolean cleanse){

        ValidateAddressResponseSpecification validateAddressSpecification = new ValidateAddressResponseSpecification(line1, line2, city, zipCode,
                firstName, middleName, lastName, state, country, addressType,cleanse)

        postObject(VALIDATE_ADDRESS,validateAddressSpecification)

    }



    def addUpcomingTrip(String customerNumber, ItineraryData itineraryData) {
        Passenger passenger = new ReservationSpecBuilder().createMockPassenger();
        passenger.setRapidRewardsNumber(customerNumber)
        passenger.setRapidRewardsType("MEMBER")
        createAirReservation(itineraryData, [:], [passenger])
    }

    def addOpenJawPastTrip(String customerNumber, ItineraryData itineraryData) {
        PastTripSummarySpecification pastTripSummarySpecification = new PastTripSummarySpecificationBuilder()
                                                                    .withDepartureDate(new LocalDate(itineraryData.departureDate))
                                                                    .withReturnDate(new LocalDate(itineraryData.returnDate))
                                                                    .withOriginCity(itineraryData.departureStation)
                                                                    .withDestinationCity(itineraryData.arrivalStation)
                                                                    .withCustomerNumber(customerNumber)
                                                                    .withItineraryType(itineraryData.itineraryType)
                                                                    .withFareBasisCode(itineraryData.departingFlight_fareClass)
                                                                    .withFlightRouting(itineraryData.outboundRouting)
                                                                    .withArrivalStation(itineraryData.returnStation)
                                                                    .withConnectionStation(itineraryData.connectionStation)
                                                                    .build()

        postObject(PAST_TRIP_URL, pastTripSummarySpecification)
        return pastTripSummarySpecification
    }

    def addRoundTripPastTrip(String customerNumber, ItineraryData itineraryData) {
        PastTripSummarySpecification pastTripSummarySpecification = new PastTripSummarySpecificationBuilder()
                                                                    .withDepartureDate(new LocalDate(itineraryData.departureDate))
                                                                    .withReturnDate(new LocalDate(itineraryData.returnDate))
                                                                    .withOriginCity(itineraryData.departureStation)
                                                                    .withDestinationCity(itineraryData.arrivalStation)
                                                                    .withCustomerNumber(customerNumber)
                                                                    .withItineraryType(itineraryData.itineraryType)
                                                                    .withFareBasisCode(itineraryData.departingFlight_fareClass)
                                                                    .withFlightRouting(itineraryData.outboundRouting)
                                                                    .withConnectionStation(itineraryData.connectionStation)
                                                                    .build()

        postObject(PAST_TRIP_URL, pastTripSummarySpecification)
        return pastTripSummarySpecification
    }

    def addOneWayPastTrip(String customerNumber, ItineraryData itineraryData) {
        PastTripSummarySpecification pastTripSummarySpecification = new PastTripSummarySpecificationBuilder()
                                                                    .withDepartureDate(new LocalDate(itineraryData.departureDate))
                                                                    .withOriginCity(itineraryData.departureStation)
                                                                    .withDestinationCity(itineraryData.arrivalStation)
                                                                    .withCustomerNumber(customerNumber)
                                                                    .withItineraryType(itineraryData.itineraryType)
                                                                    .withFareBasisCode(itineraryData.departingFlight_fareClass)
                                                                    .withFlightRouting(itineraryData.outboundRouting)
                                                                    .withConnectionStation(itineraryData.connectionStation)
                                                                    .build()
        postObject(PAST_TRIP_URL, pastTripSummarySpecification)
        return pastTripSummarySpecification
    }

    String designateCompanionPassForAPassenger(String customerNumber, Passenger passenger) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                    .withCustomerNumber(customerNumber)
                                                    .withFirstName(passenger.getFirstName())
                                                    .withLastName(passenger.getLastName())
                                                    .withGender(passenger.getGender())
                                                    .build()
        return postObject(DESIGNATE_COMPANION_PASS, accountSpecification)
    }

    String designateCompanionPass(String customerNumber, Companion companion) {

        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                    .withCustomerNumber(customerNumber)
                                                    .withFirstName(companion.getFirstName())
                                                    .withLastName(companion.getLastName())
                                                    .withGender(companion.getGender())
                                                    .build()
        return postObject(DESIGNATE_COMPANION_PASS, accountSpecification)
    }

    String designateCompanionPass(String customerNumber) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                    .withCustomerNumber(customerNumber)
                                                    .build()
        return postObject(DESIGNATE_COMPANION_PASS, accountSpecification)
    }

    RRUser generateRRUserWithAwards(int awards) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                        .withAwards(awards)
                                                        .build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithPointsAndDreamTrip(int redeemablePoints, String dreamTripName) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                        .withRedeemablePoints(redeemablePoints)
                                                        .withDreamTripName(dreamTripName)
                                                        .build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithPointsAndAlteaDreamTrip(int redeemablePoints, String dreamTripName) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                        .withRedeemablePoints(redeemablePoints)
                                                        .withDreamTripName(dreamTripName)
                                                        .withAlteaDreamTrip(true)
                                                        .build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithPoints(int redeemablePoints) {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                        .withRedeemablePoints(redeemablePoints)
                                                        .build()
        return createCustomer(accountSpecification)
    }

    private String getLoggedInPassengerAccountNumber(List<Passenger> passengers) {
        Passenger passenger = passengers?.find { it.isRapidRewadsUser() != null }
        return passenger?.getRapidRewardsNumber() ?: ""
    }

    String createCarReservation(ItineraryData itineraryData, Map params, List<Passenger> passengers = null) {
        ReservationSpecBuilder reservationSpecification = new ReservationSpecBuilder()

        String accountNumber = getLoggedInPassengerAccountNumber(passengers)

        if (params?.tripName) {
            reservationSpecification.withTripName(params.tripName)
        }

        CreateCarSpecification createCarSpecification = new CreateCarSpecification()
                .withCarItinerary(itineraryData.carSpecification)
                .withDriver(passengers.get(0))
                .withAccountNumber(accountNumber)

        reservationSpecification.withCarSpecification(createCarSpecification)

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)

        Map reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        reservationSpec.putAll(reservationInfoAsMap)

        return reservationSpec.carConfirmationNumber
    }

    RRUser generateRRUserWithChaseCreditCard() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder()
                                                        .withChaseCreditCardEnrolled()
                                                        .build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithMultipleAddresses() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withNumberOfAddresses(2).build()
        return createCustomer(accountSpecification)
    }

    RRUser generateRRUserWithStatusPending() {
        AccountSpecification accountSpecification = new AccountSpecificationBuilder().withQualifyingPeriodEndDate(new LocalDate().minusYears(2)).build()
        return createCustomer(accountSpecification)
    }

    def generatePromotion(String customerNumber) {
        PromotionSpecification promotionSpecification = new PromotionSpecificationBuilder()
                                                            .withCustomerNumber(customerNumber)
                                                            .build()
        postObject(PROMOTION, promotionSpecification)
    }

    def updatePromotionService(String customerNumber, boolean serviceStatus) {
        PromotionSpecification promotionSpecification = new PromotionSpecificationBuilder()
                                                            .withCustomerNumber(customerNumber)
                                                            .withClcsPromotionsServiceEnabled(serviceStatus)
                                                            .build()
        postObject(PROMOTION,promotionSpecification)
    }

    def generatePromotionalProduct(String customerNumber) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    def generatePromotionalProductWithTwoPromotionalCertificates(String customerNumber) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .withCertificateCounts(2)
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    def generatePromotionalProductWithTwoXAndVPromotionalCertificates(String customerNumber, List<String> certificateNumbers) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .withCertificateNumbers(certificateNumbers)
                                                                              .withCertificateCounts(2)
                                                                              .withXAndVAllowableBookingCode(true)
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    def generatePromotionalProductWithXAndVPromotionalCertificates(String customerNumber, List<String> certificateNumbers,
                           List<LocalDateTime> expirationDates, List<CertificateStatus> certificateStatuses = new ArrayList<>()) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                .withCustomerNumber(customerNumber)
                .withCertificateNumbers(certificateNumbers)
                .withCertificateCounts(certificateNumbers.size())
                .withExpirationDates(expirationDates)
                .withXAndVAllowableBookingCode(true)
                .withCertificateStatuses(certificateStatuses)
                .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    def generatePromotionalProductWithTwoXAndVPromotionalCertificatesAndUnAvailableDates(String customerNumber, List<String> certificateNumbers, Date unAvailableDate) {
        List<LocalDate> unAvailableDates = new ArrayList<LocalDate>()
        unAvailableDates.add(LocalDate.fromDateFields(unAvailableDate))
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .withCertificateNumbers(certificateNumbers)
                                                                              .withCertificateCounts(2)
                                                                              .withUnAvailableDates(unAvailableDates)
                                                                              .withXAndVAllowableBookingCode(true)
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    String generatePromotialProductWithCertificateCategory(String customerNumber, String certificateCategory) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                            .withCustomerNumber(customerNumber)
                                                                            .withSpecialCertificateCategory(certificateCategory)
                                                                            .build();
        String certificationNumber = postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
        return certificationNumber
    }

    def updatePromotionalProduct(String customerNumber, boolean serviceStatus){
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .withClcsPromotionalServiceEnabled(serviceStatus)
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    def generateReissuableStandardAward(String customerNumber) {
        PromotionalProductSpecification promotionalProductSpecification = new PromotionalProductSpecificationBuilder()
                                                                              .withCustomerNumber(customerNumber)
                                                                              .withReissuableStandardAward()
                                                                              .build();
        postObject(PROMOTIONAL_PRODUCT, promotionalProductSpecification)
    }

    String createHotelReservation(ItineraryData itineraryData, Map params, List<Passenger> passengers = null) {
        ReservationSpecBuilder reservationSpecification = new ReservationSpecBuilder()

        String accountNumber = getLoggedInPassengerAccountNumber(passengers)

        if (params?.tripName) {
            reservationSpecification.withTripName(params.tripName)
        }

        CreateHotelSpecification hotelSpecification = new CreateHotelSpecification()
                .withHotelItinerary(itineraryData.hotelSpecification)
                .withCustomer(passengers.get(0))
                .withAccountNumber(accountNumber)

        reservationSpecification.withHotelSpecification(hotelSpecification)

        ReservationSpecificationJSONTransformer transformer = new ReservationSpecificationJSONTransformer();
        def reservationSpec = transformer.toJSON(reservationSpecification);

        String reservationInfoAsJson = post(RESERVATIONS_URL, reservationSpec)

        Map reservationInfoAsMap = ((Map) new JSON().parse(reservationInfoAsJson))
        reservationSpec.putAll(reservationInfoAsMap)

        return reservationSpec.hotelConfirmationNumber
    }

    def generatePayment(String recordLocator, int points) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder()
                                                    .withFopType(PaymentSpecificationBuilder.P_POINT_CERTIFICATE)
                                                    .withPoints(points)
                                                    .withRecordLocator(recordLocator)
                                                    .build();
        postObject(PAYMENT, paymentSpecification)
    }

    def generatePaymentByPromotionalCertificate(String recordLocator, int awardsInUse, String accountNumber) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder()
                .withFopType(PaymentSpecificationBuilder.P_PROMOTIONAL_CERTIFICATE).
                withRecordLocator(recordLocator).
                withAccountNumber(accountNumber).build();
        postObject(PAYMENT, paymentSpecification)
    }


    def generatePaymentByPromotionalCertificateWithUnAvailableDate(String recordLocator, int awardsInUse, String accountNumber, Date unAvailableDate) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder()
                .withFopType(PaymentSpecificationBuilder.P_PROMOTIONAL_CERTIFICATE)
                .withExceptionDates(newArrayList(unAvailableDate))
                .withRecordLocator(recordLocator)
                .withAccountNumber(accountNumber).build();
        postObject(PAYMENT, paymentSpecification)
    }

    def createCompanionFormOfPayment(String accountNumber) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder()
                                                        .withFopType(PaymentSpecificationBuilder.P_COMPANION_CERTIFICATE)
                                                        .withAccountNumber(accountNumber)
                                                        .build();
        postObject(PAYMENT, paymentSpecification);
    }

    String[] updateGroupCheckInReservationForPassengersThatRequireAirportCheckIn(String pnr, int passengers, boolean serviceAvailable, boolean airportCheckInRequired) {
        GroupCheckInSpecification checkInSpecification = new GroupCheckInSpecification(pnr, passengers, serviceAvailable, airportCheckInRequired);
        return postObject(GROUP_CHECK_IN, checkInSpecification).split(" - ")
    }

    String[] updateGroupCheckInReservationWhenBoardingPassOnLineIsNotAllow(String pnr, int passengersWithErrors, boolean serviceAvailable, boolean airportCheckInRequired,Integer[] noOnLineBoarding) {
        GroupCheckInSpecification checkInSpecification = new GroupCheckInSpecification(pnr, passengersWithErrors, serviceAvailable, airportCheckInRequired,noOnLineBoarding);
        return postObject(GROUP_CHECK_IN,checkInSpecification).split(" - ")
    }

    def setPartnerGDSAvailableFlag(String pnr, boolean partnerGDSAvailable) {
        DepartureControlSpecification departureContorlSpecification = new DepartureControlSpecification(pnr, partnerGDSAvailable);
        postObject(DEPARTURE_CONTROL, departureContorlSpecification);
    }

    def setTsaPreCheckFlag(String pnr, boolean partnerGDSAvailable, boolean tsaPreCheck) {
        DepartureControlSpecification departureControlSpecification = new DepartureControlSpecification(pnr, partnerGDSAvailable, tsaPreCheck);
        postObject(DEPARTURE_CONTROL, departureControlSpecification);
    }

    def generatePaymentByAwards(String recordLocator, int awardsInUse) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder().
                withFopType(PaymentSpecificationBuilder.P_TRANSITION_CERTIFICATE).
                withAwardsInUse(awardsInUse).
                withRecordLocator(recordLocator).build();
        postObject(PAYMENT, paymentSpecification)
    }

    def generatePaymentByAwards(String accountNumber, String certificationNumber) {
        PaymentSpecification paymentSpecification = new PaymentSpecificationBuilder().
            withFopType(PaymentSpecificationBuilder.P_TRANSITION_CERTIFICATE).
            withAccountNumber(accountNumber).
            withCertificationNumber(certificationNumber).build()
        postObject(PAYMENT, paymentSpecification)
    }




    def generateValidStoredValueCard(List<GiftCard> giftcards, String cardType) {
        List storedValueCards = new ArrayList();
        giftcards.each {
            StoredValueCardSpecification storedValueCardSpecification= new StoredValueCardSpecificationBuilder()
                                                    .withCardNumber(it.getNumber())
                                                    .withSecurityCode(it.getSecurityCode())
                                                    .withCardType(cardType)
                                                    .build()
            storedValueCards.add(storedValueCardSpecification);
        }
        postObject(SVCM_BALANCE, storedValueCards)
    }
    def generateRejectedCreditCard(String number, String code, String type, String state, CreditCardRejectedReason reason = null) {
        List storedValueCards = new ArrayList();
        StoredValueCardSpecification storedValueCardSpecification= new StoredValueCardSpecificationBuilder()
                    .withCardNumber(number)
                    .withSecurityCode(code)
                    .withCardType(type)
                    .withState(state)
                    .withReason(reason)
                    .build()
        storedValueCards.add(storedValueCardSpecification);
        postObject(SVCM_BALANCE, storedValueCards)
    }

    PurchaseGiftCardData generateGiftCardPurchaseWithServiceDown() {
        GiftCardPurchaseSpecification giftCardPurchaseSpecification = new GiftCardPurchaseSpecificationBuilder()
                                                    .withRandomFirstName()
                                                    .withRandomLastName()
                                                    .build();
        postObject(GIFT_CARD_PURCHASE, giftCardPurchaseSpecification)

        PurchaseGiftCardData purchaseGiftCardData = PurchaseGiftCardData.createBillingGiftCardData()
        purchaseGiftCardData.firstName = giftCardPurchaseSpecification.getFirstName()
        purchaseGiftCardData.lastName = giftCardPurchaseSpecification.getLastName()
        return purchaseGiftCardData
    }

    def generateTravelFunds(AirReservation airReservation) {
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        TravelFundsSpecification travelFundsSpecification = new TravelFundsSpecificationBuilder()
                                                        .withRecordLocator(airReservation.adultPnr)
                                                        .withFirstName(passenger.firstName)
                                                        .withLastName(passenger.lastName)
                                                        .withBalance(airReservation.amount)
                                                        .withRefundableAmount(airReservation.refundableAmount)
                                                        .withNonRefundableAmount(airReservation.nonRefundableAmount)
                                                        .build()
        postObject(TRAVEL_FUNDS, travelFundsSpecification)
    }

    def generateTravelFundsWithError(AirReservation airReservation) {
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        TravelFundsSpecification travelFundsSpecification = new TravelFundsSpecificationBuilder()
            .withRecordLocator(airReservation.adultPnr)
            .withFirstName(passenger.firstName)
            .withLastName(passenger.lastName)
            .withError(airReservation.errorTypePnr.fundErrorTypeCode())
            .build()
        postObject(TRAVEL_FUNDS, travelFundsSpecification)
    }

    /**
     * The minimumConnectionTimes value is a comma-separated list of carrier:minutes pairs. For example,
     * "WN:25,FL:60"
     * @param stationId StationIdCode
     * @param minimumConnectionTimes example: "WN:25,FL:60"
     */
    String setStationLocationMinimumConnectionTime(String stationId, String minimumConnectionTimes)
    {
        StationLocationSpecification specification = new StationLocationSpecification();
        specification.setMinConnectionTimes(minimumConnectionTimes)

        postObject(STATION_LOCATION_URL, specification);
    }

    String createHotelReservation(String hotelId, String marketSearchKeyword, int numberOfAdults, int numberOfRooms) {

        LocalDate postingDateTime = new LocalDate()

        SelectHotelSpecification specification = new SelectHotelSpecification(hotelId, marketSearchKeyword);
        specification.setCheckInDate(postingDateTime)
        specification.setCheckOutDate(postingDateTime.plusDays(2))
        specification.setAdults(numberOfAdults)
        specification.setChildren(0)
        specification.setNumberOfRooms(numberOfRooms)


        postObject(HOTEL_RESERVATION_URL, specification);
    }

    def retrievePriceOopsMessageSanJuanRestriction() {
        PriceSpecification priceSpecification = new PriceSpecificationBuilder()
                .withSanJuanRestriction()
                .build()
        postObject(PRICING, priceSpecification)
    }

    public void prepareRoutesCache(ExamplesTable routesData,boolean isRoundTrip = true) {
        if (useDynaStubs()) {
            RoutesCacheSpecificationBuilder  routesCacheSpecificationBuilder = new RoutesCacheSpecificationBuilder().withTripDirection(isRoundTrip);
            routesCacheSpecificationBuilder.applyFieldValues(routesData.getRows());
            RoutesCacheSpecification routesCacheSpecification = routesCacheSpecificationBuilder.build();

            postObject(ROUTES_CACHE, routesCacheSpecification);
        }
    }

    def generateMobileBoardingPassVendorResponseSpecification(String vendor, String status, String recordLocator) {
        if (useDynaStubs()) {
            switch (vendor) {
                case "NCR":
                    MobileBoardingPassNCRVendorSpecification specification =
                        new MobileBoardingPassNCRVendorSpecificationBuilder()
                                .withRecordLocator(recordLocator)
                                .withStatus(MB_STATUS.valueOf(status.toUpperCase()))
                                .build()
                    postObject(MOBILE_BOARDING_PASS_URL, specification)
                break;
                default:
                    throw new MobileBoardingPassVendorNotSpecifiedException("No Vendor Specified")
            }
        }
    }
}
