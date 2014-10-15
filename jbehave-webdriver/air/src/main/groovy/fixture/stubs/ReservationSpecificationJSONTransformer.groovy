package fixture.stubs

import domain.Passenger
import org.joda.time.LocalDate
import fixtures.air.OriginDestinationSpecification

class ReservationSpecificationJSONTransformer {

    ReservationSpecBuilder builder
    private final String DEFAULT_BUILDER_FLT_NUMBER = "123"

    public Map<String, String> toJSON(ReservationSpecBuilder builder) {
        this.builder = builder;

        if (!builder.originDestinationSpecs.isEmpty()) {
            initializeReservation(builder.originDestinationSpecs)
        }

        if(DEFAULT_BUILDER_FLT_NUMBER.equals(builder.outboundFlightNumber)){
            builder.outboundFlightNumber = null
        }

        List paxs = new ArrayList()
        for(Passenger passenger in builder.passengers) {
            Map paxinfo = [ firstName: passenger.getFirstName(),
                            lastName: passenger.getLastName(),
                            middleName: passenger.getMiddleName(),
                            suffix: passenger.getSuffix(),
                            gender: passenger.getGender(),
                            dateOfBirth:  new LocalDate(passenger.getDateOfBirth()),
                            hasInfant : passenger.isHasInfant().toString(),
                            rrAccountNumber: passenger.getRapidRewardsNumber(),
                            rrAccountType: passenger.getRapidRewardsType(),
                            isPassengerOfSize: passenger.isPassengerOfSize().toString()
                          ]
            paxs.add(paxinfo)
        }
        Map json = [
            origin: builder.origin,
            returnStation: builder.arrival,
            destination: builder.destination,
            departureDate: builder.departureDate,
            passengers : paxs,
            departingFlightCarrierCode: builder.departingFlightCarrierCode,
            arrivingFlightCarrierCode: builder.arrivingFlightCarrierCode,
            returnDate: builder.returnDate,
            departureTime: builder.departureTime,
            returnTime: builder.returnTime,
            noShowed: builder.isNoShow,
            tripName: builder.tripName,
            unaccompaniedMinor: builder.hasUnaccompaniedMinor,
            SWABiz: builder.swabiz,
            outboundFareClass: builder.departingFareClass,
            inboundFareClass: builder.arrivingFareClass,
            itineraryType: builder.itineraryType,
            canceled: builder.isCanceled,
            earlyBird: builder.isEarlyBird,
            isAltea: builder.isAltea,
            creditCardNumber : builder.creditCardNumber,
            tripManagementDown : builder.tripManagementDown,
            tripManagementDownForSenior : builder.tripManagementDownForSenior,
            outboundBoardingPassIssued: builder.outboundBoardingPassIssued,
            inboundBoardingPassIssued : builder.inboundBoardingPassIssued,
            earlyBirdPurchased: builder.isEarlyBirdPurchased,
            crossPnrNumber : builder.crossPnrNumber,
            certificateNumber : builder.certificateNumber,
            upcomingTripServiceDown : builder.upcomingTripServiceDown,
            secondCertificateNumber : builder.secondCertificateNumber,
            myIdTravelMark : builder.myIdTravelMark,
            inboundFlightNumber : builder.inboundFlightNumber,
            outboundFlightNumber : builder.outboundFlightNumber,
            sodaAffectedMark : builder.sodaAffectedMark,
            pnrSource : builder.pnrSource,
            isGroupPNR : builder.isGroupPNR,
            certificateForPromoCert : builder.certificateForPromoCert,
            paymentType : builder.paymentType,
            outboundFirstSegmentRouteData : builder.outboundFirstSegmentRouteData,
            outboundSecondSegmentRouteData : builder.outboundSecondSegmentRouteData,
            inboundFirstSegmentRouteData:  builder.inboundFirstSegmentRouteData,
            inboundSecondSegmentRouteData:  builder.inboundSecondSegmentRouteData,
            departingFlightConnectingCarrierCode:  builder.departingFlightConnectingCarrierCode,
            arrivingFlightConnectingCarrierCode:  builder.arrivingFlightConnectingCarrierCode,
            isForUpgradeToBS : builder.isForUpgradeToBS,
            isWebOnly: builder.isWebOnly,
            eligibility: builder.isEligible,
            isMissingAirTranTicketNumber: builder.isMissingAirTranTicketNumber,
            airTrip: builder.airTrip
        ]
        if (builder.carSpecification) {
            json.carSpecification = builder.carSpecification.toJSON()
        }
        if (builder.hotelSpecification) {
            json.hotelSpecification = builder.hotelSpecification.toJSON()
        }

        return  json
    }

    private initializeReservation(List<OriginDestinationSpecification> onds) {
        initializeOutbound(onds)
        initializeInbound(onds)
    }

    private initializeInbound(List<OriginDestinationSpecification> onds) {
        if (onds.size() > 1) {
            builder.returnDate = onds[1].getDepartureDate()
            builder.arrivingFlightCarrierCode = onds[1].getCarrierCode()
        }
    }

    private initializeOutbound(List<OriginDestinationSpecification> onds) {
            builder.origin = onds[0].getOrigin()
            builder.destination = onds[0].getDestination()
            builder.departureDate = onds[0].getDepartureDate()
            builder.departureTime = onds[0].getDepartureTime()
            builder.departingFlightCarrierCode = onds[0].getCarrierCode()
    }
}
