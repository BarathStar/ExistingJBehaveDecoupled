package util

import java.text.SimpleDateFormat
import builders.FlightNumberGenerator

class ItineraryDataBuilder {

    public static final String CARRIER_CODESHARE = "CodeShare"
    public static final String CARRIER_WNFLCODESHARE = "WNFLCodeShare"
    public static final String CARRIER_FLWNCODESHARE = "FLWNCodeShare"
    public static final String ROUTINGTYPE_NONSTOP = "Nonstop"

    public Integer departingFlight_number = FlightNumberGenerator.WN_OUTBOUND_FIRST_SEGMENT
    public Integer departingConnectingFlight_number = FlightNumberGenerator.WN_OUTBOUND_SECOND_SEGMENT
    public Integer returningFlight_number = FlightNumberGenerator.WN_INBOUND_FIRST_SEGMENT
    public Integer returningConnectingFlight_number = FlightNumberGenerator.WN_INBOUND_SECOND_SEGMENT
    public itineraryType = ""

    Date departureDate = null
    Date returnDate = null
    public departureTime = null
    public returnTime = null

    public departureStation = ""
    public arrivalStation = ""
    public returnStation = ""
    public outboundConnectingStation = ""
    public inboundConnectingStation = ""
    public returnConnectingStation = ""

    public departingFlight_airlineName = ""
    public departingFlight_carrierCode = ""
    public departingFlight_fareClass = "Anytime"

    public connection_airlineName = ""
    public outbound_connection_carrierCode = ""
    public inbound_connection_carrierCode = ""
    public connection_fareClass = "Anytime"

    public arrivingFlight_airlineName = ""
    public arrivingFlight_carrierCode = ""
    public arrivingFlight_fareClass = "Anytime"

    public returningFlight_airlineName = ""
    public returningFlight_carrierCode = ""
    public returningFlight_fareClass = "Anytime"
    public equipmentCode = "733"

    public inboundRouting = ROUTINGTYPE_NONSTOP
    public outboundRouting = ROUTINGTYPE_NONSTOP
    public isWebOnly = false
    public isValidForCheckin = "false"

    private String DATE_FORMAT = "yyyy/MM/dd"
    private String TIME_FORMAT = "HH:mm:ss.SSS"

    public List<String> flightNumbers = []
    RandomTicketTypeGroup randomTicketTypeGroup
    public List<Map> faresAvailability = []

    public outboundBaseFare = "400"
    public outboundDiscountBaseFare = "400"
    public outboundTaxes_USPFC = "0"
    public outboundTaxes_USSECURITY = "0"
    public outboundTaxes_USINTARR = "0"
    public outboundTaxes_USZP = "0"
    public outboundTaxes_USTRANSP = "0"
    public outboundTaxes_USAKHI = "0"
    private boolean isAltea

    public promoCode = ""

    ItineraryDataBuilder(RandomTicketTypeGroup randomTicketTypeGroup){
        this.randomTicketTypeGroup = randomTicketTypeGroup
    }

    ItineraryDataBuilder withValuesFrom(ItineraryData itineraryData){
        this.departingFlight_number = itineraryData.departingFlight_number
        this.departingConnectingFlight_number = itineraryData.departingConnectingFlight_number
        this.returningFlight_number = itineraryData.returningFlight_number
        this.returningConnectingFlight_number = itineraryData.returningConnectingFlight_number

        this.itineraryType = itineraryData.itineraryType

        this.departureDate = itineraryData.departureDate
        this.returnDate = itineraryData.returnDate

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

        this.inboundRouting = itineraryData.inboundRouting
        this.outboundRouting = itineraryData.outboundRouting
        this.isWebOnly = itineraryData.isWebOnly
        this.isAltea = itineraryData.isAltea

        this.flightNumbers = itineraryData.flightNumbers
        this.faresAvailability = itineraryData.faresAvailability

        this.isValidForCheckin = itineraryData.isValidForCheckin

        this.promoCode = itineraryData.promoCode

        return this
    }

    ItineraryDataBuilder withDepartingCarrier(String carrier) {
        this.departingFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getCarrierCode()
        this.departingFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier).getAirlineName()
        this.outbound_connection_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getOutboundConnectionCarrier()

        if(carrier == CARRIER_CODESHARE || carrier == CARRIER_FLWNCODESHARE || carrier == CARRIER_WNFLCODESHARE) {
            this.outboundRouting = "1 stop"
            if (carrier == CARRIER_FLWNCODESHARE) {
                departingFlight_number = FlightNumberGenerator.generateFLFlightNumber(true, true)
            } else if (carrier == CARRIER_WNFLCODESHARE) {
                departingConnectingFlight_number = FlightNumberGenerator.generateFLFlightNumber(true, false)
            }
        }
        return this
    }

    ItineraryDataBuilder withArrivalCarrier(String carrier) {
        this.arrivingFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getArrivalConnectionCarrier()
        this.arrivingFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier).getOutboundConnectionAirlineName()
        this.inbound_connection_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getCarrierCode()

        if(carrier == CARRIER_CODESHARE || carrier == CARRIER_FLWNCODESHARE || carrier == CARRIER_WNFLCODESHARE) {
            this.inboundRouting = "1 stop"
            if (carrier == CARRIER_FLWNCODESHARE) {
                returningConnectingFlight_number = FlightNumberGenerator.generateFLFlightNumber(false, false)
            } else if (carrier == CARRIER_WNFLCODESHARE) {
                returningFlight_number = FlightNumberGenerator.generateFLFlightNumber(false, true)
            }
        }
        return this
    }

    ItineraryDataBuilder withReturningCarrier(String carrier) {
        this.returningFlight_carrierCode = randomTicketTypeGroup.getTicketType(carrier).getCarrierCode()
        this.returningFlight_airlineName = randomTicketTypeGroup.getTicketType(carrier).getAirlineName()
        return this
    }

    ItineraryDataBuilder withOneWayStations(String carrier) {
        this.departureStation = randomTicketTypeGroup.getTicketType(carrier).getDepartingStation()
        this.arrivalStation = randomTicketTypeGroup.getTicketType(carrier).getArrivingStation()
        this.outboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier).getOutboundConnectionStation()
        this.itineraryType = "One Way"
        return this
    }

    ItineraryDataBuilder withRoundTripStations(String carrier1, String carrier2) {
        this.departureStation = randomTicketTypeGroup.getTicketType(carrier1).getDepartingStation()
        this.arrivalStation = randomTicketTypeGroup.getTicketType(carrier2).getArrivingStation()
        this.outboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier1).getOutboundConnectionStation()
        this.inboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier2).getOutboundConnectionStation()
        this.itineraryType = "Round Trip"
        return this
    }

    ItineraryDataBuilder withOpenJawStations(String carrier1, String carrier2) {
        this.departureStation = randomTicketTypeGroup.getTicketType(carrier1).getDepartingStation()
        this.arrivalStation = randomTicketTypeGroup.getTicketType(carrier1).getArrivingStation()
        this.returnStation = randomTicketTypeGroup.getTicketType(carrier2).getReturningStation()
        this.outboundConnectingStation = randomTicketTypeGroup.getTicketType(carrier1).getOutboundConnectionStation()
        this.returnConnectingStation = randomTicketTypeGroup.getTicketType(carrier2).getReturnConnectionStation()
        this.itineraryType = "Open Jaw"
        return this
    }

    ItineraryDataBuilder withFareClass(String fare_class) {
        this.departingFlight_fareClass = fare_class
        this.arrivingFlight_fareClass = fare_class
        this.returningFlight_fareClass = fare_class
        return this
    }

    ItineraryDataBuilder withFaresAvailability(List<String> faresAvailabilityString) {
        List<Map> faresAvailabilityMapList = new ArrayList<Map>()

        faresAvailabilityString.each {
            String[] fareAvailibity = it.split(":")
            if(fareAvailibity.length == 2) {
                Map fareAvailabilityMap = new HashMap()
                fareAvailabilityMap.put("fareClass", fareAvailibity[0])
                fareAvailabilityMap.put("availability", fareAvailibity[1])
                faresAvailabilityMapList.add(fareAvailabilityMap)
            }
        }

        this.faresAvailability = faresAvailabilityMapList
        return this
    }

    ItineraryDataBuilder withDepartingFlightFareClass(String fare_class) {
        this.departingFlight_fareClass = fare_class
        return this
    }

    ItineraryDataBuilder withReturningFlightFareClass(String fare_class) {
        this.returningFlight_fareClass = fare_class
        return this
    }

    ItineraryDataBuilder withRoutingType(String routingType) {
        this.outboundRouting = routingType
        this.inboundRouting = routingType
        return this
    }

    ItineraryDataBuilder withEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode
        return this
    }

    ItineraryDataBuilder fromMap(Map jsonData) {
        this.itineraryType = jsonData.get("itineraryType")
        this.departureStation = jsonData.get("origin")
        this.arrivalStation = jsonData.get("destination")
        this.departureDate = new SimpleDateFormat(DATE_FORMAT).parse(jsonData.get("departureDate").toString(DATE_FORMAT))
        this.departureTime = jsonData.get("departureTime").toString(TIME_FORMAT)
        this.departingFlight_number = jsonData.get("outbound_flightNumber")
        this.departingConnectingFlight_number = jsonData.get("outboundConnecting_flightNumber")
        this.departingFlight_carrierCode = jsonData.get("departingFlightCarrierCode")
        if (this.itineraryType == "Round Trip" || this.itineraryType == "Open Jaw") {
            this.returnDate = new SimpleDateFormat(DATE_FORMAT).parse(jsonData.get("returnDate").toString(DATE_FORMAT))
            this.returnTime = jsonData.get("returnTime").toString(TIME_FORMAT)
            this.returningFlight_number = jsonData.get("inbound_flightNumber")
            this.returningConnectingFlight_number = jsonData.get("inboundConnecting_flightNumber")
            this.arrivingFlight_carrierCode = jsonData.get("arrivingFlightCarrierCode")
        }
        return this
    }

    ItineraryDataBuilder withFlightsDates(String departureDate, String returnDate = null) {
        ["departureDate":departureDate,"returnDate":returnDate].each{ k,v->
            if(v == 'today' || v == 'checkin'){
                this[k] = ItineraryDateFactory.chooseDayEligibleForDepartureCheckin()
            }else if(v == 'tomorrow'){
                this[k] = ItineraryDateFactory.getTomorrowDate()
            } else if(v == 'day-after' && k == 'returnDate'){
                this[k] = ItineraryDateFactory.getTomorrowDate(this.departureDate)
            } else if(v == 'early-bird'){
                this[k] = ItineraryDateFactory.getAnyAvailableDepartureDateForEarlyBird()
            }else{
                this[k] = null
            }
        }
        return this
    }

    ItineraryDataBuilder withFlightDatesFromToday(String departureDaysOut, String returnDaysOut = null) {
      departureDate = ItineraryDateFactory.getNumberDaysOutDate(new Integer(departureDaysOut))
      if(returnDaysOut) returnDate = ItineraryDateFactory.getNumberDaysOutDate(new Integer(returnDaysOut))
      return this
    }

    ItineraryDataBuilder withOutboundFares(String outboundBaseFare, String outboundDiscountBaseFare) {
        this.outboundBaseFare = outboundBaseFare
        this.outboundDiscountBaseFare = outboundDiscountBaseFare
        return this
    }

    ItineraryDataBuilder withOutboundDiscountBaseFare(String outboundDiscountBaseFare) {
        this.outboundDiscountBaseFare = outboundDiscountBaseFare
        return this
    }

    ItineraryDataBuilder withOutboundTaxesSecurity(String outboundTaxes_USSECURITY) {
        this.outboundTaxes_USSECURITY = outboundTaxes_USSECURITY
        return this
    }

    ItineraryDataBuilder withOutboundTaxesPassengerFacilityCharge(String outboundTaxes_USPFC) {
        this.outboundTaxes_USPFC = outboundTaxes_USPFC
        return this
    }

    ItineraryDataBuilder withOutboundTaxesInternationalArrival(String outboundTaxes_USINTARR) {
        this.outboundTaxes_USINTARR = outboundTaxes_USINTARR
        return this
    }

    ItineraryDataBuilder withOutboundSegmentFee(String outboundTaxes_USZP) {
        this.outboundTaxes_USZP = outboundTaxes_USZP
        return this
    }

    ItineraryDataBuilder withOutboundTaxesUsTransp(String outboundTaxes_USTRANSP) {
        this.outboundTaxes_USTRANSP = outboundTaxes_USTRANSP
        return this
    }

    ItineraryDataBuilder withOutboundTaxesInternationalTransp(String outboundTaxes_USAKHI) {
        this.outboundTaxes_USAKHI = outboundTaxes_USAKHI
        return this
    }

    ItineraryDataBuilder withAltea() {
        this.isAltea = true
        return this
    }

    ItineraryDataBuilder withPromoCode(String promoCode) {
        this.promoCode = promoCode;
        return this
    }

    ItineraryData build() {
        if (outboundRouting == ROUTINGTYPE_NONSTOP) {
            outboundConnectingStation = "";
        }
        if (inboundRouting == ROUTINGTYPE_NONSTOP) {
            inboundConnectingStation = "";
        }
        return new ItineraryData(
            departingFlight_number,
            departingConnectingFlight_number,
            returningFlight_number,
            returningConnectingFlight_number,
            itineraryType,
            departureDate,
            departureTime,
            returnDate,
            returnTime,
            departureStation,
            arrivalStation,
            returnStation,
            outboundConnectingStation,
            inboundConnectingStation,
            returnConnectingStation,
            departingFlight_airlineName,
            departingFlight_carrierCode,
            departingFlight_fareClass,
            connection_airlineName,
            inbound_connection_carrierCode,
            outbound_connection_carrierCode,
            connection_fareClass,
            arrivingFlight_airlineName,
            arrivingFlight_carrierCode,
            arrivingFlight_fareClass,
            returningFlight_airlineName,
            returningFlight_carrierCode,
            returningFlight_fareClass,
            flightNumbers,
            inboundRouting,
            outboundRouting,
            isWebOnly,
            isAltea,
            faresAvailability,
            isValidForCheckin,
            equipmentCode,
            outboundBaseFare,
            outboundDiscountBaseFare,
            outboundTaxes_USPFC,
            outboundTaxes_USSECURITY,
            outboundTaxes_USINTARR,
            outboundTaxes_USZP,
            outboundTaxes_USTRANSP,
            outboundTaxes_USAKHI,
            promoCode
        )
    }
}
