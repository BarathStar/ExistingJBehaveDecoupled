package fixture.stubs

import org.joda.time.LocalDate
import org.joda.time.LocalTime

import domain.Passenger

import util.BookingCode
import util.ItineraryData
import static com.google.common.collect.Lists.newArrayList
import fixtures.air.OriginDestinationSpecification
import util.ItineraryDateFactory
import org.joda.time.DateTime
import org.apache.commons.lang.StringUtils

class ReservationSpecBuilder {

    private String origin
    private String arrival
    private String destination
    private LocalDate departureDate
    private String departingFlightCarrierCode
    private LocalDate returnDate

    private String departingFlightConnectingCarrierCode;
    private String arrivingFlightConnectingCarrierCode;

    private String arrivingFlightCarrierCode
    private LocalTime departureTime
    private LocalTime returnTime
    private DateTime departureDateTime
    private DateTime returnDateTime

    private boolean isNoShow
    private String tripName
    private boolean hasUnaccompaniedMinor
    private List<OriginDestinationSpecification> originDestinationSpecs = newArrayList()
    private boolean swabiz
    private String departingFareClass
    private String itineraryType
    private String arrivingFareClass
    private boolean isCanceled
    private boolean isEarlyBird
    private boolean isEarlyBirdPurchased
    private boolean isAltea
    private boolean tripManagementDown = false
    private boolean tripManagementDownForSenior = false
    private boolean outboundBoardingPassIssued = false
    private boolean inboundBoardingPassIssued = false
    private String creditCardNumber
    private String crossPnrNumber
    List passengers
    CreateCarSpecification carSpecification
    CreateHotelSpecification hotelSpecification
    ItineraryData itineraryData
    private String certificateNumber
    private boolean upcomingTripServiceDown = false
    private String secondCertificateNumber
    private boolean certificateForPromoCert = false
    private boolean myIdTravelMark = false
    private String inboundFlightNumber
    private boolean sodaAffectedMark = false
    private String pnrSource
    private boolean isGroupPNR = false
    private String paymentType = "dollars"
    private String outboundFirstSegmentRouteData = null
    private String outboundSecondSegmentRouteData = null
    private String inboundFirstSegmentRouteData = null
    private String inboundSecondSegmentRouteData = null
	private boolean isForUpgradeToBS = false;
    private boolean isWebOnly = false
    private String outboundFlightNumber
    private boolean isEligible = true;
    private boolean isMissingAirTranTicketNumber = false;
    private boolean airTrip = true;

    public ReservationSpecBuilder withItinerary(ItineraryData itineraryData) {
        this.origin = itineraryData.departureStation
        this.destination = itineraryData.arrivalStation
        if (itineraryData.departureDate == null) {
            itineraryData.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        }
        this.departureDate = new LocalDate(itineraryData.departureDate)

        if (itineraryData.departureTime == null) {
            itineraryData.departureTime = new LocalTime().plusHours(5)
            if (itineraryData.departureTime < new LocalTime()) {
                this.departureDate = this.departureDate.plusDays(1)
                itineraryData.departureDate = this.departureDate.toDate();
            }
        }
        this.departureTime = itineraryData.departureTime

        departingFlightCarrierCode = itineraryData.departingFlight_carrierCode
        this.departingFareClass = BookingCode.from(itineraryData.departingFlight_fareClass).bookingClassCode()
        this.itineraryType = itineraryData.itineraryType

        if (itineraryData.isRoundTrip() || itineraryData.isOpenJaw()) {
            createValidReturnDateTime(itineraryData)

            this.arrivingFlightCarrierCode = itineraryData.arrivingFlight_carrierCode
            this.arrivingFareClass = BookingCode.from(itineraryData.arrivingFlight_fareClass).bookingClassCode()

            if(itineraryData.isOpenJaw()) {
                this.origin = itineraryData.departureStation
                this.arrival = itineraryData.arrivalStation
                this.destination = itineraryData.returnStation
            }

        }

        if(itineraryData.outboundStopCities != null && !itineraryData.outboundStopCities.equals("")){
            setRouteData(itineraryData)
        }

        this.isEarlyBird = itineraryData.earlyBird
        this.isEarlyBirdPurchased = itineraryData.earlyBirdPurchased
        this.isAltea = itineraryData.isAltea

        this.departingFlightConnectingCarrierCode = itineraryData.outbound_connection_carrierCode
        this.arrivingFlightConnectingCarrierCode = itineraryData.inbound_connection_carrierCode

        if (itineraryData.outboundConnectingStation) {
            if (StringUtils.isBlank(itineraryData.outboundStopCities)) {
                this.outboundFirstSegmentRouteData = itineraryData.departureStation + "-" + itineraryData.outboundConnectingStation
                this.outboundSecondSegmentRouteData = itineraryData.outboundConnectingStation + "-" + itineraryData.arrivalStation
            }

            if (StringUtils.isBlank(departingFlightConnectingCarrierCode)) {
                departingFlightConnectingCarrierCode = departingFlightCarrierCode
            }
        }

        if (itineraryData.inboundConnectingStation) {
            this.inboundFirstSegmentRouteData = itineraryData.arrivalStation + "-" + itineraryData.inboundConnectingStation
            this.inboundSecondSegmentRouteData = itineraryData.inboundConnectingStation + "-" + itineraryData.departureStation
            if (StringUtils.isBlank(arrivingFlightConnectingCarrierCode)) {
                arrivingFlightConnectingCarrierCode = arrivingFlightCarrierCode
            }
        }

        if (itineraryData.returningFlight_number !=null && !itineraryData.returningFlight_number.toString().equals("")){
            this.inboundFlightNumber = itineraryData.returningFlight_number.toString()
        }

        if (itineraryData.departingFlight_number !=null && !itineraryData.departingFlight_number.toString().equals("")){
            this.outboundFlightNumber = itineraryData.departingFlight_number.toString()
        }

        this.isWebOnly = itineraryData.isWebOnly

        return this
    }

    private createValidReturnDateTime(ItineraryData itineraryData) {
        if (itineraryData.returnDate == null) {
            itineraryData.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(itineraryData.departureDate)
        }

        this.returnDate = new LocalDate(itineraryData.returnDate)

        if (itineraryData.returnTime == null) {
            itineraryData.returnTime = new LocalTime().plusHours(5)
            if (itineraryData.returnTime < new LocalTime()) {
                this.returnDate = this.returnDate.plusDays(1)
                itineraryData.returnDate = this.returnDate.toDate();
            }

        }
        this.returnTime = itineraryData.returnTime
    }

    ReservationSpecBuilder withCarSpecification(CreateCarSpecification carSpecification) {
        this.carSpecification = carSpecification
        return this
    }

    public ReservationSpecBuilder withDepartureTimeEligibleForCheckin(ItineraryData itineraryData) {

        departureDateTime = new DateTime().plusHours(3)
        departureDate = departureDateTime.toLocalDate()
        departureTime = departureDateTime.toLocalTime()
        itineraryData.departureDate = departureDateTime.toDate()
        itineraryData.departureTime = departureDateTime.toLocalTime()
        if (itineraryData.isRoundTrip()  || itineraryData.isOpenJaw()) {
            createValidReturnDateTime(itineraryData)
        }

        assert isDateTimeEligibleForCheckin(departureDateTime)
        return this
    }

    private boolean isDateTimeEligibleForCheckin(DateTime t){
        DateTime now = new DateTime();
        return t.isBefore(now.plusHours(24)) && t.isAfter(now.plusHours(1))
    }

    public ReservationSpecBuilder withReturnTimeEligibleForCheckin(ItineraryData itineraryData) {

        returnDateTime = new DateTime().plusHours(12)
        returnDate = returnDateTime.toLocalDate()
        returnTime = returnDateTime.toLocalTime()
        itineraryData.returnDate = returnDateTime.toDate()
        itineraryData.returnTime = returnDateTime.toLocalTime()

        assert isDateTimeEligibleForCheckin(returnDateTime)
        return this
    }

    public ReservationSpecBuilder withNoShowFlight() {
        this.isNoShow = true
        return this
    }

    public ReservationSpecBuilder withPastDepartureTime() {
        this.departureTime = new LocalTime().minusMinutes(20)
        return this
    }

    public ReservationSpecBuilder withTripName(String tripName) {
        this.tripName = tripName
        return this
    }

    public ReservationSpecBuilder withUnaccompaniedMinor() {
        this.hasUnaccompaniedMinor = true
        return this
    }

    public ReservationSpecBuilder withSwabiz() {
        this.swabiz = true
        return this
    }

    public ReservationSpecBuilder withOriginDestination(OriginDestinationSpecification ond) {
        this.originDestinationSpecs.add(ond);
        return this;
    }

    public ReservationSpecBuilder withCanceled() {
        this.isCanceled = true
        return this
    }

    public ReservationSpecBuilder withTripManagementDown() {
        this.tripManagementDown = true
        return this
    }

    public ReservationSpecBuilder withTripManagementDownForSenior() {
        this.tripManagementDownForSenior = true
        return this
    }

    public ReservationSpecBuilder withPassengers(List passengersList) {
        if(!passengersList) {
            passengersList = new ArrayList()
            Passenger passenger = createMockPassenger()
            passengersList.add(passenger)
        }

        this.passengers = passengersList
        return this
    }

    public Passenger createMockPassenger() {
        Passenger passenger = new Passenger("Teddy", "", "Bear", "Male")

        if (hasUnaccompaniedMinor) {
            passenger.beChild()
        } else if (departingFareClass == "Q") {
            passenger.beSenior()
        } else {
            passenger.beAdult()
        }
        passenger
    }

    public ReservationSpecBuilder withCreditCardNumber(String creditCardNumber){
        this.creditCardNumber = creditCardNumber
        return this
	}

    ReservationSpecBuilder withHotelSpecification(CreateHotelSpecification createHotelSpecification) {
        this.hotelSpecification = createHotelSpecification
        return this
    }

    public ReservationSpecBuilder withOutboundBoardingPassIssued() {
        this.outboundBoardingPassIssued = true
        return this
    }

    public ReservationSpecBuilder withInboundBoardingPassIssued() {
        this.inboundBoardingPassIssued = true
        return this
    }

    public ReservationSpecBuilder withCrossPnrNumber(String crossPnrNumber) {
        this.crossPnrNumber = crossPnrNumber
        return this
    }

    public ReservationSpecBuilder withCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber
        return this
    }

    public ReservationSpecBuilder withUpcomingTripServiceDown() {
        this.upcomingTripServiceDown = true
        return this
    }

    public ReservationSpecBuilder withSecondCertificateNumber(String secondCertificateNumber) {
        this.secondCertificateNumber = secondCertificateNumber
        return this
    }

    public ReservationSpecBuilder withMyIdTravelMark(boolean myIdTravelMark) {
        this.myIdTravelMark = myIdTravelMark
        return this
    }

    public ReservationSpecBuilder withInboundFlightNumber(String inboundFlightNumber) {
        this.inboundFlightNumber = inboundFlightNumber
        return this
    }

    public ReservationSpecBuilder withSodaAffectedMark(boolean sodaAffectedMark) {
        this.sodaAffectedMark = sodaAffectedMark
        return this
    }

    public ReservationSpecBuilder withPnrSource(String pnrSource){
        this.pnrSource = pnrSource
        return this
    }

    public ReservationSpecBuilder withGroupPNR(boolean isGroupPNR){
        this.isGroupPNR = isGroupPNR
        return this
    }

    public ReservationSpecBuilder withCertificateForPromoCert(boolean isCertificateForPromoCert) {
        this.certificateForPromoCert = isCertificateForPromoCert
        return this
    }

    public ReservationSpecBuilder withPaymentType(String paymentType){
        this.paymentType = paymentType
        return this
    }

    public ReservationSpecBuilder withUpgradeToBS() {
        this.isForUpgradeToBS = true
        return this
    }

    public ReservationSpecBuilder withMissingAirTranTicketNumber() {
        this.isMissingAirTranTicketNumber = true;
        return this
    }

    public ReservationSpecBuilder withEligibility(String eligibility) {
        this.isEligible = eligibility.toBoolean()
        return this
    }

    private setRouteData(ItineraryData itineraryData){
        if(itineraryData.outboundRouting.equals("1 stop No Plane Change") || itineraryData.outboundRouting.equals("2 stops No Plane Change")){
            outboundFirstSegmentRouteData = origin + "-" + itineraryData.outboundStopCities + "-" + destination
        }

        if(itineraryData.outboundRouting.equals("2 stops Change Planes")){
            outboundFirstSegmentRouteData = origin + "-" + itineraryData.outboundStopCities + "-" + itineraryData.outboundConnectingStation
            outboundSecondSegmentRouteData = itineraryData.outboundConnectingStation + "-" + destination
        }
    }

    public ReservationSpecBuilder withoutAirTrip(){
        this.airTrip = false
        return this
    }
}
