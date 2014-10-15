package fixture.stubs

import builders.FlightSegmentSpecificationBuilder
import builders.ProductSpecificationBuilder
import builders.Routing
import fixtures.air.FlightSegmentSpecification
import fixtures.air.ProductSpecification
import fixtures.air.OriginDestinationSpecification
import fixtures.air.enums.TaxType
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import util.ItineraryData
import util.FareClass
import static com.google.common.collect.Lists.newArrayList
import fixtures.air.PricePointSpecification
import builders.PricePointSpecificationBuilder

class OriginDestinationFactory {

    public OriginDestinationSpecification createOutboundOriginDestinationSpecification(ItineraryData itineraryData) {
        OriginDestinationSpecification originDestinationSpecification;
        Routing outboundRouting = Routing.from(itineraryData.outboundRouting)
        String firstSegmentDestination = (outboundRouting.isNonstop() || outboundRouting.isDirect()) ? itineraryData.arrivalStation : itineraryData.outboundConnectingStation;

        FareClass fareClass = FareClass.from(itineraryData.departingFlight_fareClass);

        String fareClassCode = null
        List<String> fareClasses = null
        String fareProduct = null
        String fareBasisCode = null
        String passengerTypeCode = null

        if (fareClass != null) {
            fareClassCode = fareClass.fareClassCode()
            fareClasses = fareClass.getFareClasses()
            fareProduct = fareClass.getFareProduct()
        }

        /* From FreedomAward Passenger */
        if(FareClass.FreedomAward.toString().equals(itineraryData.departingFlight_fareClass)) {
            fareBasisCode = "X"
            passengerTypeCode = "FFP"
        }

        /* From WGA WebOnly Passenger */
        if(itineraryData.isWebOnly) {
            passengerTypeCode = "NRF"
        }

        /* From Senior Passenger */
        if(FareClass.Senior.toString().equals(itineraryData.departingFlight_fareClass)) {
            fareBasisCode = "QCD"
            passengerTypeCode = "SRC"
        }

        PricePointSpecification pricePointSpecification = new PricePointSpecificationBuilder()
                .withTax(TaxType.US_INTERNATIONAL_ARRIVAL, itineraryData.outboundTaxes_USINTARR)
                .withTax(TaxType.PASSENGER_FACILITY_CHARGE, itineraryData.outboundTaxes_USPFC)
                .withTax(TaxType.SECURITY_FEE, itineraryData.outboundTaxes_USSECURITY)
                .withTax(TaxType.US_DOMESTIC, itineraryData.outboundTaxes_USTRANSP)
                .withTax(TaxType.US_INTERNATIONAL_FACILITIES, itineraryData.outboundTaxes_USAKHI)
                .withTax(TaxType.SEGMENT_FEE, itineraryData.outboundTaxes_USZP)
                .withBaseFare(itineraryData.outboundBaseFare)
                .withBaseFareWannaGetAway(itineraryData.baseFareWannaGetAway)
                .withBaseFareAnyTime(itineraryData.baseFareAnyTime)
                .withBaseFareBusiness(itineraryData.baseFareBusiness)
                .withDiscountBaseFare(itineraryData.outboundDiscountBaseFare)
                .withFareClasses(fareClasses)
                .withFareBasisCode(fareBasisCode)
                .withEstimatedAccrualLoyaltyPoints(itineraryData.estimatedAccrualLoyaltyPoints)
                .withPassengerTypeCode(passengerTypeCode)
                .build()

        ProductSpecification productSpecification = new ProductSpecificationBuilder()
                .withRandomizeBaseFarePrice(itineraryData.randomizeBaseFare)
                .withPricePoint(pricePointSpecification)
                .withFareProduct(fareProduct)
                .build()

        List<ProductSpecification> productSpecifications = newArrayList(productSpecification)

        FlightSegmentSpecification firstFlightSegment = new FlightSegmentSpecificationBuilder()
                                                            .withOrigin(itineraryData.departureStation)
                                                            .withDestination(firstSegmentDestination)
                                                            .withDepartureDate(new LocalDate(itineraryData.departureDate))
                                                            .withDepartureTime(new LocalTime(12,0))
                                                            .withCarrier(itineraryData.departingFlight_carrierCode)
                                                            .withFlightNumber(itineraryData.departingFlight_number)
                                                            .withEquipmentCode(itineraryData.equipmentCode)
                                                            .withFareClass(fareClassCode)
                                                            .build();
        String stopsCities = itineraryData.outboundStopCities
        setRoutingInfo(firstFlightSegment, outboundRouting, stopsCities)

        if (outboundRouting.isDirect()) {
            firstFlightSegment.setFlightNumber(FlightSegmentSpecificationBuilder.outboundDirectFlightByProtocol)
        }

        if(outboundRouting.hasPlaneChange()) {
            FlightSegmentSpecification outboundConnectionSegment = new FlightSegmentSpecificationBuilder()
                                                                            .withOrigin(itineraryData.outboundConnectingStation)
                                                                            .withDestination(itineraryData.arrivalStation)
                                                                            .withDepartureDate(new LocalDate(itineraryData.departureDate))
                                                                            .withDepartureTime(new LocalTime(12,0))
                                                                            .withCarrier(itineraryData.outbound_connection_carrierCode)
                                                                            .withFlightNumber(itineraryData.departingConnectingFlight_number ?: 321)
                                                                            .withEquipmentCode(itineraryData.equipmentCode)
                                                                            .withFareClass(fareClassCode)
                                                                            .build();
            originDestinationSpecification = new OriginDestinationSpecification(firstFlightSegment, outboundConnectionSegment)
        } else {
            originDestinationSpecification = new OriginDestinationSpecification(firstFlightSegment)
        }
        originDestinationSpecification.setProducts(productSpecifications)
        return originDestinationSpecification
    }

    public OriginDestinationSpecification createInboundOriginDestinationSpecification(ItineraryData itineraryData) {
        OriginDestinationSpecification originDestinationSpecification;
        List<FlightSegmentSpecification> flightSegments = newArrayList();
        Routing inboundRouting = Routing.from(itineraryData.inboundRouting);
        String firstSegmentDestination = (inboundRouting.isNonstop() || inboundRouting.isDirect()) ? getInboundDestinationStation(itineraryData) : getInboundConnectionStation(itineraryData);
        Integer flightNumber = itineraryData.returningFlight_number ?: 1234;

        FareClass fareClass = FareClass.from(itineraryData.arrivingFlight_fareClass);

        String fareClassCode = null
        List<String> fareClasses = null
        String fareProduct = null
        String fareBasisCode = null
        String passengerTypeCode = null

        if (fareClass != null) {
            fareClassCode = fareClass.fareClassCode()
            fareClasses = fareClass.getFareClasses()
            fareProduct = fareClass.getFareProduct()
        }

        /* From FreedomAward Passenger */
        if(FareClass.FreedomAward.toString().equals(itineraryData.arrivingFlight_fareClass)) {
            fareBasisCode = "X"
            passengerTypeCode = "FFP"
        }

        /* From WGA WebOnly Passenger */
        if(itineraryData.isWebOnly) {
            passengerTypeCode = "NRF"
        }

        /* From Senior Passenger */
        if(FareClass.Senior.toString().equals(itineraryData.arrivingFlight_fareClass)) {
            fareBasisCode = "QCD"
            passengerTypeCode = "SRC"
        }

        PricePointSpecification pricePointSpecification = new PricePointSpecificationBuilder()
                .withTax(TaxType.US_INTERNATIONAL_ARRIVAL, itineraryData.outboundTaxes_USINTARR)
                .withTax(TaxType.PASSENGER_FACILITY_CHARGE, itineraryData.outboundTaxes_USPFC)
                .withTax(TaxType.SECURITY_FEE, itineraryData.outboundTaxes_USSECURITY)
                .withTax(TaxType.US_DOMESTIC, itineraryData.outboundTaxes_USTRANSP)
                .withTax(TaxType.US_INTERNATIONAL_FACILITIES, itineraryData.outboundTaxes_USAKHI)
                .withTax(TaxType.SEGMENT_FEE, itineraryData.outboundTaxes_USZP)
                .withBaseFare(itineraryData.outboundBaseFare)
                .withBaseFareWannaGetAway(itineraryData.baseFareWannaGetAway)
                .withBaseFareAnyTime(itineraryData.baseFareAnyTime)
                .withBaseFareBusiness(itineraryData.baseFareBusiness)
                .withDiscountBaseFare(itineraryData.outboundDiscountBaseFare)
                .withEstimatedAccrualLoyaltyPoints(itineraryData.estimatedAccrualLoyaltyPoints)
                .withFareClasses(fareClasses)
                .withFareBasisCode(fareBasisCode)
                .withPassengerTypeCode(passengerTypeCode)
                .build()

        ProductSpecification productSpecification = new ProductSpecificationBuilder()
                .withRandomizeBaseFarePrice(itineraryData.randomizeBaseFare)
                .withPricePoint(pricePointSpecification)
                .withFareProduct(fareProduct)
                .build()

        List<ProductSpecification> productSpecifications = newArrayList(productSpecification)

        FlightSegmentSpecification firstFlightSegment = new FlightSegmentSpecificationBuilder()
                                                            .withOrigin(itineraryData.arrivalStation)
                                                            .withDestination(firstSegmentDestination)
                                                            .withDepartureDate(new LocalDate(itineraryData.returnDate))
                                                            .withDepartureTime(new LocalTime(12,0))
                                                            .withCarrier(itineraryData.arrivingFlight_carrierCode)
                                                            .withFlightNumber(flightNumber)
                                                            .withEquipmentCode(itineraryData.equipmentCode)
                                                            .withFareClass(fareClassCode)
                                                            .build();
        if (inboundRouting.isDirect()) {
            firstFlightSegment.setFlightNumber(FlightSegmentSpecificationBuilder.inboundDirectFlightByProtocol)
        }

        String stopsCities = itineraryData.inboundStopCities
        setRoutingInfo(firstFlightSegment, inboundRouting, stopsCities)

        flightSegments.add(firstFlightSegment);

        if(inboundRouting.hasPlaneChange()) {
            Integer connectionFlightNumber = itineraryData.returningConnectingFlight_number ?: 4321;
            FlightSegmentSpecification inboundConnectionSegment = new FlightSegmentSpecificationBuilder()
                                                                            .withOrigin(getInboundConnectionStation(itineraryData))
                                                                            .withDestination(getInboundDestinationStation(itineraryData))
                                                                            .withDepartureDate(new LocalDate(itineraryData.returnDate))
                                                                            .withDepartureTime(new LocalTime(12,0))
                                                                            .withCarrier(itineraryData.inbound_connection_carrierCode)
                                                                            .withFlightNumber(connectionFlightNumber)
                                                                            .withEquipmentCode(itineraryData.equipmentCode)
                                                                            .withFareClass(fareClassCode)
                                                                            .build();

            originDestinationSpecification = new OriginDestinationSpecification(firstFlightSegment, inboundConnectionSegment);
        } else {
            originDestinationSpecification = new OriginDestinationSpecification(firstFlightSegment);
        }
        originDestinationSpecification.setProducts(productSpecifications)
        return originDestinationSpecification
    }

    public String getInboundDestinationStation(ItineraryData itineraryData) {
        if (itineraryData.isRoundTrip()) {
            return itineraryData.departureStation
        } else {
            return itineraryData.returnStation
        }
    }

    public String getInboundConnectionStation(ItineraryData itineraryData) {
        if (itineraryData.isRoundTrip()) {
            return itineraryData.inboundConnectingStation
        } else {
            return itineraryData.returnConnectingStation
        }

    }

    private void setRoutingInfo(FlightSegmentSpecification firstFlightSegment,
                                Routing routing, String stopsCitiesCodes){
        firstFlightSegment.setStopCities(stopsCitiesCodes)
        switch (routing){
            case Routing.ONE_STOP_DIRECT:
                firstFlightSegment.setStopCount(1)
                break
            case Routing.ONE_STOP_CONNECTING:
                firstFlightSegment.setStopCount(0)
                break
            case Routing.TWO_STOPS_DIRECT:
                firstFlightSegment.setStopCount(2)
                break
            case Routing.TWO_STOPS_CONNECTING:
                firstFlightSegment.setStopCount(1)
                break
            case Routing.THREE_STOPS_DIRECT:
                firstFlightSegment.setStopCount(3)
                break
            case Routing.THREE_STOPS_CONNECTING:
                firstFlightSegment.setStopCount(2)
                break
        }
    }
}
