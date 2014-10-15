/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.DollarToBigDecimalConverter
import util.PointsToNumberConverter

/**
 * This class represents a Pricing flyOut
 */
class FareBreakdownFlyOut extends FlyOut {
    private static final By BASE_FARE = By.id('baseFare')
    private static final By ADJUSTED_FARE = By.id('adjustedFare')
    private static final By TAXES = By.id('exciseTaxes')
    private static final By ADVERTISED_FARE = By.id('advertisedFare')
    private static final By SEGMENT_FEE = By.id('segmentFee')
    private static final By PASSENGER_FACILITY_FEE = By.id('passengerFacilityFee')
    private static final By SECURITY_FEE = By.id('securityFee')
    private static final By TOTAL_PER_PASSENGER = By.id('totalPerPassenger')
    private static final By PASSENGER_COUNT = By.id('passengerCount')
    private static final By DOLLAR_TOTAL = By.id('dollarTotal')
    private static final By FARE_DISCOUNT = By.id('fareDiscount')
    private static final By TOTAL_TAXES_AND_FEES = By.id("totalTaxesAndFees")
    private static final By POINTS_TOTAL =  By.id("pointsTotal")
    private static final By SENIOR_BASE_FARE = By.cssSelector('#totalPriceDetails_footer_SRC #baseFare')
    private static final By SENIOR_TAXES = By.cssSelector('#totalPriceDetails_footer_SRC #exciseTaxes')
    private static final By SENIOR_ADVERTISED_FARE = By.cssSelector('#totalPriceDetails_footer_SRC #advertisedFare')
    private static final By SENIOR_SEGMENT_FEE = By.cssSelector('#totalPriceDetails_footer_SRC #segmentFee')
    private static final By SENIOR_PASSENGER_FACILITY_FEE = By.cssSelector('#totalPriceDetails_footer_SRC #passengerFacilityFee')
    private static final By SENIOR_SECURITY_FEE = By.cssSelector('#totalPriceDetails_footer_SRC #securityFee')
    private static final By SENIOR_TOTAL_PER_PASSENGER = By.cssSelector('#totalPriceDetails_footer_SRC #totalPerPassenger')
    private static final By SENIOR_PASSENGER_COUNT = By.cssSelector('#totalPriceDetails_footer_SRC #passengerCount')
    private static final By SENIOR_DOLLAR_TOTAL = By.cssSelector('#totalPriceDetails_footer_SRC #dollarTotal')
    private static final By SENIOR_FARE_DISCOUNT = By.cssSelector('#totalPriceDetails_footer_SRC #fareDiscount')
    private static final By SENIOR_TOTAL_TAXES_AND_FEES = By.cssSelector('#totalPriceDetails_footer_SRC #totalTaxesAndFees')
    private static final By SENIOR_POINTS_TOTAL =  By.cssSelector('#totalPriceDetails_footer_SRC #pointsTotal')

    FareBreakdownFlyOut(WebElement theContainer) {
        super(theContainer)
    }

    BigDecimal getBaseFare() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(BASE_FARE))
    }

    String getStringBaseFare(){
        return container.findElement(ADVERTISED_FARE).text
    }

    int getPointsBaseFare() {
        return PointsToNumberConverter.toInteger(container.findElement(BASE_FARE))
    }

    BigDecimal getAdjustedFare() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(ADJUSTED_FARE))
    }

    int getPointsAdjustedFare() {
        return PointsToNumberConverter.toInteger(container.findElement(ADJUSTED_FARE))
    }

    BigDecimal getTaxes() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(TAXES))
    }

    BigDecimal getTotal() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(ADVERTISED_FARE))
    }

    public def getTotalForPromoCertPnr() {
        return container.findElement(ADVERTISED_FARE)
    }

    public def getTotalPerPassengerPromoCert() {
        return container.findElement(TOTAL_PER_PASSENGER).text
    }

    public BigDecimal getTotalTaxesAndFees() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(TOTAL_TAXES_AND_FEES))
    }

    public def getPointsTotalForPromoCert() {
        return container.findElement(POINTS_TOTAL).text
    }

    BigDecimal getSegmentFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SEGMENT_FEE))
    }

    BigDecimal getPassengerFacilityFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(PASSENGER_FACILITY_FEE))
    }

    BigDecimal getSecurityFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SECURITY_FEE))
    }

    BigDecimal getFareDiscount() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(FARE_DISCOUNT))
    }

    int getPointsFareDiscount() {
        return PointsToNumberConverter.toInteger(container.findElement(FARE_DISCOUNT))
    }

    BigDecimal getTotalPerPassenger() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(TOTAL_PER_PASSENGER))
    }

    int getPassengerCount() {
        def String paxCount = container.findElement(PASSENGER_COUNT).text
        def tokens = paxCount.split(/ /)
        tokens.length.shouldBe 3, "Not a valid pax count string: $paxCount"
        return tokens[1].toInteger();
    }

    BigDecimal dollarTotal() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(DOLLAR_TOTAL))
    }

    BigDecimal calculateBaseFarePlusTaxes() {
        return getBaseFare() + getTaxes()
    }

    BigDecimal calculateSeniorBaseFarePlusTaxes() {
        return getSeniorBaseFare() + getSeniorTaxes()
    }

    BigDecimal getSeniorBaseFare() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_BASE_FARE))
    }

    BigDecimal getSeniorTaxes() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_TAXES))
    }

    BigDecimal getSeniorTotal() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_ADVERTISED_FARE))
    }

    BigDecimal getSeniorSegmentFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_SEGMENT_FEE))
    }

    BigDecimal getSeniorPassengerFacilityFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_PASSENGER_FACILITY_FEE))
    }

    BigDecimal getSeniorSecurityFee() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_SECURITY_FEE))
    }

    BigDecimal getSeniorTotalPerPassenger() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(TOTAL_PER_PASSENGER))
    }

    int getSeniorPassengerCount() {
        def String paxCount = container.findElement(SENIOR_PASSENGER_COUNT).text
        def tokens = paxCount.split(/ /)
        tokens.length.shouldBe 3, "Not a valid pax count string: $paxCount"
        return tokens[1].toInteger();
    }

    BigDecimal seniorDollarTotal() {
        return DollarToBigDecimalConverter.toBigDecimal(container.findElement(SENIOR_DOLLAR_TOTAL))
    }

    BigDecimal pointsFare() {
        container.findElement(ADVERTISED_FARE).text.replace(',', '').replace(' pts', '').toBigDecimal()
    }

    def getTotalPointsPerPassenger() {
        container.findElement(TOTAL_PER_PASSENGER).text.replace(',', '').replace(' pts', '').toBigDecimal()
    }
}
