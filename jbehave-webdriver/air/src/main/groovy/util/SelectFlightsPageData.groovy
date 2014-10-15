package util

import org.apache.commons.lang.StringUtils
import state.CarReservationData

class SelectFlightsPageData {



    public Date departureDate
    public Date returnDate
    public departureStation = ""
    public arrivalStation = ""
    public returnStation = ""

    public arrivalCity = ""
    public departureTime
    public returnTime
    public outboundConnectingStation
    public inboundConnectingStation
    public returnConnectingStation
    public departingFlight_airlineName
    public departingFlight_carrierCode
    public departingFlight_fareClass
    public departingFlight_number
    public departingConnectingFlight_number
    public connection_airlineName
    public outbound_connection_carrierCode
    public inbound_connection_carrierCode
    public connection_fareClass
    public arrivingFlight_airlineName
    public arrivingFlight_carrierCode
    public arrivingFlight_fareClass
    public returningFlight_airlineName
    public returningFlight_carrierCode
    public returningFlight_fareClass
    public returningFlightNumber
    public returningConnectingFlight_number
    public returningDepartureTime
    public returningArrivalTime
    public returningConnectionDepartureTime
    public returningConnectionArrivalTime


    public outboundRouting = "Nonstop"
    public inboundRouting = "Nonstop"



    public isWebOnly = false

    public boolean isAltea = false
    public isValidForCheckin = "false"




    public adultPassengerCount
    public seniorPassengerCount

    public outboundDepartureTime
    public outboundArrivalTime
    public outboundConnectionDepartureTime
    public outboundConnectionArrivalTime
    public inboundDepartureTime
    public inboundArrivalTime
    public inboundConnectionDepartureTime
    public inboundConnectionArrivalTime


    public CarReservationData carSpecification


    public promoCode
    public outboundTravelTime = ""
    public inboundTravelTime = ""


    BigDecimal outboundFlightPrice = 0
    BigDecimal outboundSeniorFlightPrice = 0
    BigDecimal inboundFlightPrice = 0
    BigDecimal inboundSeniorFlightPrice = 0
    String outboundSeniorPrice
    BigDecimal previousOutboundFlightPrice = 0

    public selectedOutboundFlightDetailsLink
    public selectedInboundFlightDetailsLink

    public outboundFlyoutRouting = ""
    public outboundFlyoutDeparure = ""
    public outboundFlyoutArrival = ""

    public inboundFlyoutRouting = ""
    public inboundFlyoutDeparure = ""
    public inboundFlyoutArrival = ""
    String outboundConnectingCity

    String inboundConnectingCity

    BigDecimal outboundFlightPoints = BigDecimal.ZERO
    BigDecimal inboundFlightPoints = BigDecimal.ZERO
    BigDecimal totalOutboundInboundFlightPoints = BigDecimal.ZERO
    BigDecimal previousOutboundFlightPoints = BigDecimal.ZERO

    boolean shouldShowUpsell = false
    public String inboundRow = ""
    public String outboundRow = ""
}
