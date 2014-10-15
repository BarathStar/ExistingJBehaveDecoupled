package util

import org.apache.commons.lang.StringUtils
import org.ho.yaml.Yaml;
import state.CarReservationData

class ItineraryData {

    public static final String AirTran = "FL"
    public static final String ONE_WAY_ITINERARY = "One Way"
    public static final String ROUND_TRIP_ITINERARY = "Round Trip"
    public static final String OPEN_JAW_ITINERARY = "Open Jaw"
    private static final int CITY_NAME_LOCATION = 0

    public itineraryType
    public Date departureDate
    public Date returnDate
    public departureStation = ""
    public arrivalStation = ""
    public returnStation = ""

    public arrivalCity = ""
    public departureCity = ""
    public returnCity = ""
    public departureTime
    public returnTime
    public outboundConnectingStation
    public inboundConnectingStation
    public returnConnectingStation
    public departingFlight_airlineName
    public departingFlight_carrierCode = "WN"
    public departingFlight_fareClass
    public seniorDepartingFlight_fareClass
    public Integer departingFlight_number
    public Integer departingConnectingFlight_number
    public connection_airlineName
    public outbound_connection_carrierCode
    public inbound_connection_carrierCode
    public connection_fareClass
    public arrivingFlight_airlineName
    public arrivingFlight_carrierCode = "WN"
    public arrivingFlight_fareClass
    public returningFlight_airlineName
    public returningFlight_carrierCode
    public returningFlight_fareClass
    public Integer returningFlight_number
    public Integer returningConnectingFlight_number
    public outboundRouting = "Nonstop"
    public inboundRouting = "Nonstop"
    public String equipmentCode = "733"
    public String outboundStopCities = ""
    public String inboundStopCities = ""


    public isWebOnly = false
    public boolean earlyBird = false
    public boolean earlyBirdPurchased = false
    public boolean isAltea = false
    public boolean luvVoucher = false;
    public boolean giftCard = false;
    public boolean hasTravelFunds = false;
    public boolean lessThan4HourLayover = false;
    public isValidForCheckin = "false"
    public isValidForWGA = false
    public boolean homePage = false;
    public boolean passengerOfSize=false
    public List<String> flightNumbers = []
    RandomTicketTypeGroup randomTicketTypeGroup
    public BigDecimal totalBaseFare
    public BigDecimal totalTaxes
    public BigDecimal totalCharge
    public BigDecimal segmentFee
    public BigDecimal passengerFacilityFee
    public BigDecimal securityFee
    public BigDecimal totalPerPassenger
    public String departureInternationalTaxId
    public String returnInternationalTaxId
    public String arrivalInternationalTaxId

    public int passengerCount
    public adultPassengerCount
    public seniorPassengerCount
    public BigDecimal dollarTotal
    public outboundDepartureTime
    public outboundArrivalTime
    public outboundConnectionDepartureTime
    public outboundConnectionArrivalTime
    public inboundDepartureTime
    public inboundArrivalTime
    public inboundConnectionDepartureTime
    public inboundConnectionArrivalTime
    public String connectionStation

    public List<Map> faresAvailability = []
    public CarReservationData carSpecification
    public HotelItineraryData hotelSpecification

    public outboundBaseFare = "400"
    public outboundDiscountBaseFare = "400"
    public outboundTaxes_USPFC = "0"
    public outboundTaxes_USSECURITY = "0"
    public outboundTaxes_USINTARR = "0"
    public outboundTaxes_USZP = "0"
    public outboundTaxes_USTRANSP = "0"
    public outboundTaxes_USAKHI = "0"

    public baseFareWannaGetAway = "0"
    public baseFareAnyTime = "0"
    public baseFareBusiness = "0"
    public randomizeBaseFare = false
    public promoCode
    public estimatedAccrualLoyaltyPoints = 0
    boolean pnrSource = false;
    boolean isMixedFare = false
    boolean withoutAirTrip = false
    public boolean points=false

    public boolean hasDisabilities = false;
    Date umDateOfBirth = null
    //sruthi
    public boolean inhibitedPassenger=false

    // This one via Dependency Injection.
    ItineraryData(RandomTicketTypeGroup randomTicketTypeGroup) {
        this.randomTicketTypeGroup = randomTicketTypeGroup
    }

    public ItineraryData(ItineraryData original) {
        setValues(original);
    }

    // This one via util.ItineraryDataBuilder ...
    public ItineraryData(Integer departingFlight_number, Integer departingConnectingFlight_number, Integer returningFlight_number,
        Integer returningConnectingFlight_number, String itineraryType, Date departureDate, String departureTime, Date returnDate, String returnTime,
        String departureStation, String arrivalStation, String returnStation, String outboundConnectingStation,
        String inboundConnectingStation, String returnConnectingStation, String departingFlight_airlineName,
        String departingFlight_carrierCode, String departingFlight_fareClass, String connection_airlineName,
        String inbound_connection_carrierCode, String outbound_connection_carrierCode, String connection_fareClass,
        String arrivingFlight_airlineName, String arrivingFlight_carrierCode, String arrivingFlight_fareClass,
        String returningFlight_airlineName, String returningFlight_carrierCode, String returningFlight_fareClass,
        List<String> flightNumbers, String inboundRouting, String outboundRouting, boolean isWebOnly, boolean isAltea, List<Map> faresAvailability,
        String isValidForCheckin, String equipmentCode, String outboundBaseFare, String outboundDiscountBaseFare,
        String outboundTaxes_USPFC, String outboundTaxes_USSECURITY, String outboundTaxes_USINTARR,
        String outboundTaxes_USZP, String outboundTaxes_USTRANSP, String outboundTaxes_USAKHI, String promoCode) {

        this.departingFlight_number = departingFlight_number
        this.departingConnectingFlight_number = departingConnectingFlight_number
        this.returningFlight_number = returningFlight_number
        this.returningConnectingFlight_number = returningConnectingFlight_number
        this.itineraryType = itineraryType
        this.departureDate = departureDate
        this.departureTime = departureTime
        this.returnDate = returnDate
        this.returnTime = returnTime
        this.departureStation = departureStation
        this.arrivalStation = arrivalStation
        this.returnStation = returnStation
        this.outboundConnectingStation = outboundConnectingStation
        this.inboundConnectingStation = inboundConnectingStation
        this.returnConnectingStation = returnConnectingStation
        this.departingFlight_airlineName = departingFlight_airlineName
        this.departingFlight_carrierCode = departingFlight_carrierCode
        this.departingFlight_fareClass = departingFlight_fareClass
        this.connection_airlineName = connection_airlineName
        this.outbound_connection_carrierCode = outbound_connection_carrierCode
        this.inbound_connection_carrierCode = inbound_connection_carrierCode
        this.connection_fareClass = connection_fareClass
        this.arrivingFlight_airlineName = arrivingFlight_airlineName
        this.arrivingFlight_carrierCode = arrivingFlight_carrierCode
        this.arrivingFlight_fareClass = arrivingFlight_fareClass
        this.returningFlight_airlineName = returningFlight_airlineName
        this.returningFlight_carrierCode = returningFlight_carrierCode
        this.returningFlight_fareClass = returningFlight_fareClass
        this.flightNumbers = flightNumbers
        this.inboundRouting = inboundRouting
        this.outboundRouting = outboundRouting
        this.isWebOnly = isWebOnly
        this.isAltea = isAltea
        this.faresAvailability = faresAvailability
        this.isValidForCheckin = isValidForCheckin
        this.equipmentCode = equipmentCode
        this.outboundBaseFare = outboundBaseFare
        this.outboundDiscountBaseFare = outboundDiscountBaseFare
        this.outboundTaxes_USPFC = outboundTaxes_USPFC
        this.outboundTaxes_USSECURITY = outboundTaxes_USSECURITY
        this.outboundTaxes_USINTARR = outboundTaxes_USINTARR
        this.outboundTaxes_USZP = outboundTaxes_USZP
        this.outboundTaxes_USTRANSP = outboundTaxes_USTRANSP
        this.outboundTaxes_USAKHI = outboundTaxes_USAKHI
        this.promoCode = promoCode
    }

    public boolean isOneWay() {
        return itineraryType.equals(ONE_WAY_ITINERARY)
    }

    public boolean isRoundTrip() {
        return itineraryType.equals(ROUND_TRIP_ITINERARY)
    }

    public boolean isOpenJaw() {
        return itineraryType.equals(OPEN_JAW_ITINERARY)
    }

    // Set EVERY field here.
    public void setValues(ItineraryData itineraryData) {
        this.departingFlight_number = itineraryData.departingFlight_number
        this.departingConnectingFlight_number = itineraryData.departingConnectingFlight_number
        this.returningFlight_number = itineraryData.returningFlight_number
        this.returningConnectingFlight_number = itineraryData.returningConnectingFlight_number
        this.itineraryType = itineraryData.itineraryType
        this.departureDate = itineraryData.departureDate
        this.departureTime = itineraryData.departureTime
        this.returnDate = itineraryData.returnDate
        this.departureTime = itineraryData.returnTime
        this.departureStation = itineraryData.departureStation
        this.arrivalStation = itineraryData.arrivalStation
        this.returnStation = itineraryData.returnStation
        this.outboundConnectingStation = itineraryData.outboundConnectingStation
        this.inboundConnectingStation = itineraryData.inboundConnectingStation
        this.returnConnectingStation = itineraryData.returnConnectingStation
        this.departingFlight_airlineName = itineraryData.departingFlight_airlineName
        this.departingFlight_carrierCode = itineraryData.departingFlight_carrierCode
        this.departingFlight_fareClass = itineraryData.departingFlight_fareClass
        this.connection_airlineName = itineraryData.connection_airlineName
        this.outbound_connection_carrierCode = itineraryData.outbound_connection_carrierCode
        this.inbound_connection_carrierCode = itineraryData.inbound_connection_carrierCode
        this.connection_fareClass = itineraryData.connection_fareClass
        this.arrivingFlight_airlineName = itineraryData.arrivingFlight_airlineName
        this.arrivingFlight_carrierCode = itineraryData.arrivingFlight_carrierCode
        this.arrivingFlight_fareClass = itineraryData.arrivingFlight_fareClass
        this.returningFlight_airlineName = itineraryData.returningFlight_airlineName
        this.returningFlight_carrierCode = itineraryData.returningFlight_carrierCode
        this.returningFlight_fareClass = itineraryData.returningFlight_fareClass
        this.flightNumbers = itineraryData.flightNumbers
        this.faresAvailability = itineraryData.faresAvailability
        this.outboundRouting = itineraryData.outboundRouting
        this.inboundRouting = itineraryData.inboundRouting
        this.isWebOnly = itineraryData.isWebOnly
        this.isAltea = itineraryData.isAltea
        this.totalBaseFare = itineraryData.totalBaseFare
        this.totalTaxes = itineraryData.totalTaxes
        this.totalCharge = itineraryData.totalCharge
        this.segmentFee = itineraryData.segmentFee
        this.passengerFacilityFee = itineraryData.passengerFacilityFee
        this.securityFee = itineraryData.securityFee
        this.totalPerPassenger = itineraryData.totalPerPassenger
        this.passengerCount = itineraryData.passengerCount
        this.adultPassengerCount = itineraryData.adultPassengerCount
        this.seniorPassengerCount = itineraryData.seniorPassengerCount
        this.dollarTotal = itineraryData.dollarTotal
        this.isValidForCheckin = itineraryData.isValidForCheckin
        this.faresAvailability = itineraryData.faresAvailability
        this.equipmentCode = itineraryData.equipmentCode
        this.connectionStation =itineraryData.connectionStation
        this.outboundBaseFare = itineraryData.outboundBaseFare
        this.outboundDiscountBaseFare = itineraryData.outboundDiscountBaseFare
        this.outboundTaxes_USPFC = itineraryData.outboundTaxes_USPFC
        this.outboundTaxes_USSECURITY = itineraryData.outboundTaxes_USSECURITY
        this.outboundTaxes_USINTARR = itineraryData.outboundTaxes_USINTARR
        this.outboundTaxes_USZP = itineraryData.outboundTaxes_USZP
        this.outboundTaxes_USTRANSP = itineraryData.outboundTaxes_USTRANSP
        this.outboundTaxes_USAKHI = itineraryData.outboundTaxes_USAKHI
        this.promoCode = itineraryData.promoCode
    }

    public void changeOneWayFlightCarrier(String carrier) {

        this.departureStation = randomTicketTypeGroup.getTicketType(carrier).getDepartingStation()
        this.arrivalStation = randomTicketTypeGroup.getTicketType(carrier).getArrivingStation()
        this.outboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier).getOutboundConnectionStation()
        this.departingFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier).getAirlineName()
        this.departingFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getCarrierCode()
        this.outbound_connection_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getOutboundConnectionCarrier()
    }

    public void changeRoundTripFlightCarrier(String carrier1, String carrier2) {
        this.departureStation = randomTicketTypeGroup.getTicketType(carrier1) getDepartingStation()
        this.arrivalStation = randomTicketTypeGroup.getTicketType(carrier2).getArrivingStation()
        this.outboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier1) getOutboundConnectionStation()
        this.inboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier2).getOutboundConnectionStation()
        this.departingFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier1).getAirlineName()
        this.arrivingFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier2).getAirlineName()
        this.departingFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier1).getCarrierCode()
        this.arrivingFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier2).getOutboundConnectionCarrier()
        this.outbound_connection_carrierCode = randomTicketTypeGroup.getTicketType(carrier1).getOutboundConnectionCarrier()
        this.inbound_connection_carrierCode = randomTicketTypeGroup.getTicketType(carrier2).getCarrierCode()
    }

    public boolean isMultiCarrierItinerary() {
        inbound_connection_carrierCode != arrivingFlight_carrierCode  ||
        outbound_connection_carrierCode != departingFlight_carrierCode
    }

    boolean hasCarReservation() {
        return carSpecification
    }

    boolean hasHotelReservation() {
        return hotelSpecification
    }

    public Integer getTotalBasePriceRounded() {
        return Math.ceil(this.getTotalBasePrice()).toInteger()
    }

    public Double getTotalBasePrice() {
        return this.getBaseFare() + this.getExcisesTaxes()  + this.getAdditionalTaxes()
    }

    private Double getBaseFare() {
        return new Double(this.outboundBaseFare)
    }

    private Double getExcisesTaxes() {
        Double usIntArr = new Double(this.outboundTaxes_USINTARR)
        Double usUstransp = new Double(this.outboundTaxes_USTRANSP)
        Double usAkhi = new Double(this.outboundTaxes_USAKHI)
        return usIntArr + usUstransp + usAkhi
    }

    private Double getAdditionalTaxes() {
        Double usSecurity = new Double(this.outboundTaxes_USSECURITY)
        Double usPfc = new Double(this.outboundTaxes_USPFC)
        Double usZp = new Double(this.outboundTaxes_USZP)
        return usSecurity + usPfc + usZp
    }

    public String[] getOutboundStopCitiesCodes() {
        return (!this.outboundStopCities.equals(""))?this.outboundStopCities.split("-"):null
    }

    public String[] getInboundStopCitiesCodes() {
        return (!this.inboundStopCities.equals(""))?this.inboundStopCities.split("-"):null
    }

    public int getOutboundNumberOfNotConnectingStops() {
        return (StringUtils.isNotBlank(this.outboundStopCities))?this.outboundStopCities.split("-").size():0
    }

    public int getOutboundNumberOfConnectingStops() {
        return (StringUtils.isNotBlank(this.outboundConnectingStation))?this.outboundConnectingStation.split("-").size():0
    }

    public boolean hasOutboundStopCities() {
        return (this.outboundStopCities != null) && (this.outboundStopCities.trim().length() > 0)
    }

    public void verifyValidDates() {
        if (departureDate == null) {
            this.departureDate = ItineraryDateFactory.getAnyAvailableDepartureDate()
        }
        if (returnDate == null) {
            this.returnDate = ItineraryDateFactory.getAnyAvailableReturnDateAfter(this.departureDate)
        }
    }

    public Long getEstimatedAccrualLoyaltyPoints() {
        return this.estimatedAccrualLoyaltyPoints.toLong();
    }

    public boolean isRoundTripOrOpenJaw() {
        return (itineraryType == "Round Trip" || itineraryType == "Open Jaw")
    }

    public boolean isPromoCertBooking() {
        return (departingFlight_fareClass.equalsIgnoreCase("StandardAward") || departingFlight_fareClass.equalsIgnoreCase("PromoCert"))
    }

    public boolean isOutboundAndInboundFareTheSame() {
        return (departingFlight_fareClass == arrivingFlight_fareClass || departingFlight_fareClass == returningFlight_fareClass)
    }

    public boolean showOneTableInPriceOrConfirmationPage(boolean isInConfirmationPage = false) {
        if (isInConfirmationPage) {
            return lessThan4HourLayover
        } else {
            return isOutboundAndInboundFareTheSame() && lessThan4HourLayover
        }
    }

    public String getDefaultTripName() {
        StringBuilder tripName = new StringBuilder()
        tripName.append(departureDate.format("MM/dd/yy"))
        tripName.append(" - ")
        if(showOneTableInPriceOrConfirmationPage()) {
            tripName.append(splitCity(returnCity))
        } else {
            tripName.append(splitCity(arrivalCity))
        }
        return tripName.toString()
    }

    private String splitCity(def cityState) {
        return cityState.split(",")[CITY_NAME_LOCATION].split("\\(")[CITY_NAME_LOCATION]
    }

    def Map yamlItineraryData() {
        String currentLocation = System.getProperty("user.dir")
        Map yaml = (Map) Yaml.load(new File(currentLocation + System.getProperty("java.io.yaml.home", "/src/main/resources/data/") + "qa/ItineraryData.yaml"))
        return yaml
    }

    public boolean isOpenJawAndLessThan4HourLayover(){
        return (openJaw && lessThan4HourLayover)
    }
}
