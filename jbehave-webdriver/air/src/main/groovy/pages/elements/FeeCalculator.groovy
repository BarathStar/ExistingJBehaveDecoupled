/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import util.ItineraryData
import state.PassengerData
import util.CommonConstants

/**
* This class calculates fees
*/
class FeeCalculator {

    ItineraryData itineraryData
    PassengerData passengerData

    public BigDecimal calculateGuardianCharge() {
        int numberOfPassengers = passengerData.passengers.size()
        String itinerary = itineraryData.itineraryType
        BigDecimal totalFee
        if(itinerary == "Round Trip") {
            totalFee = numberOfPassengers * CommonConstants.UM_FEE * 2
        }else if (itinerary == "One Way") {
            totalFee = numberOfPassengers * CommonConstants.UM_FEE
        }
        return totalFee
    }

    public BigDecimal calculateSecurityFee() {
        BigDecimal totalFee =  CommonConstants.SECURITY_FEE
        if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.isOpenJawAndLessThan4HourLayover()) {
            totalFee = CommonConstants.SECURITY_FEE * 2
        }
        return totalFee
    }
}
