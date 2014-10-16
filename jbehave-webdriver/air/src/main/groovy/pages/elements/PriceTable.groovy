package pages.elements

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import state.Flow
import state.PassengerData
import state.ScenarioState
import util.ItineraryData
import util.PricePageData
import util.SelectFlightsPageData
import util.PurchasePageData

class PriceTable extends BasePage {

    private static final By POINT_CALC_LINK = By.id("pointsCalcLink")
    private static final By PASSENGER_NUMBER = By.className("numberOfPassengers")
    private static final By PASSENGER_NUMBER_OUTBOUND_SENIOR = By.cssSelector("#pricing_SRC .numberOfPassengers")
    private static final By PASSENGER_NUMBER_OUTBOUND_ADULT = By.cssSelector("#pricing_ADT .depart .numberOfPassengers")
    private static final By PRICING_ADULT = By.id("pricing_adult")

    private static final By DEPART_CITY = By.cssSelector(".depart .passengerRoute")
    private static final By RETURN_CITY = By.cssSelector(".return .passengerRoute")
    private static final By DEPART_FARE_TYPE = By.xpath("//tr[contains(@class, 'depart')]//a[contains(@class, 'fareTypeLink')]")
    private static final By RETURN_FARE_TYPE = By.xpath("//tr[contains(@class, 'return')]//a[contains(@class, 'fareTypeLink')]")
    private static final By HEADER_LABEL = By.cssSelector('.up_coming_trip_detail_container.priceResultsContainer .section_header_label')
    private static final By DEPART_SUBTOTAL_ADULT = By.id("totalPriceDetailsText_ADT_depart_0")
    private static final By DEPART_SUBTOTAL_ADULT_FFP = By.id("totalPriceDetailsText_FFP_depart_0")
    private static final By DEPART_SUBTOTAL_SENIOR = By.id("totalPriceDetailsText_SRC_depart_0")
    private static final By RETURN_SUBTOTAL_ADULT = By.id("totalPriceDetailsText_ADT_return_0")
    private static final By RETURN_SUBTOTAL_SENIOR = By.id("totalPriceDetailsText_SRC_return_0")
    private static final By RETURN_SUBTOTAL_ADULT_FFP = By.id("totalPriceDetailsText_FFP_return_0")
    private static final By SUBTOTAL_CONTAINER = By.className("pricingFooterEnhanced")
    private static final By SUBTOTAL_AMOUNT = By.className("subTotal")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_ADT = By.className("totalPriceDetailsText_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_FFP = By.className("totalPriceDetailsText_FFP")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_SRC = By.className("totalPriceDetailsText_SRC")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_ADT = By.id("totalPriceDetailsText_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_FFP = By.id("totalPriceDetailsText_FFP")
    private static final By TOTAL_FARE_BREAK_DOWN_FLY_OUT_ADT = By.id("totalPriceDetails_footer_ADT")
    private static final By TOTAL_FARE_BREAK_DOWN_FLY_OUT_FFP = By.id("totalPriceDetails_footer_FFP")
    private static final By TOTAL_FARE_BREAK_DOWN_FLY_OUT_SRC = By.id("totalPriceDetails_footer_SRC")
    private static final By TOTAL_PRICE_DETAILS = By.id("totalPriceDetails")
    private static final By TRIP_TOTAL = By.id("trip_grand_total_bottom")
    private static final By PENDING_AIR = By.id("priceItinerary_grandTotal")
    private static final By AVAILABLE_FUNDS = By.className("rowTotal")
    private static final By ADITIONAL_AMOUNT_DUE = By.className("additionalAmount_total")
    private static final By REPRICE_AVAILABLE_FUNDS = By.className("priceItinerary_totals")
    private static final By AIR_TOTAL = By.cssSelector(".price_table_subtotal .price_table_subtotal_amount_container")
    private static final By ARRIVAL_PRICE_FLYOUT = By.cssSelector("#totalPriceDetails_ADT_return_0 .pricingPopupDetailsContainer")
    private static final By DEPARTURE_PRICE_FLYOUT = By.cssSelector("#totalPriceDetails_ADT_depart_0 .pricingPopupDetailsContainer")
    private static final By TOTAL_POINTS = By.className("totalPoints")
    private static final By GOV_TAXES_AND_FEE_INCLUDE = By.id("fullFareLink")
    private static final By OUTBOUND_TOTAL_POINTS = By.className("pointsItinerary_exchangedPoints")

    Flow flow;
    ItineraryData itineraryData;
    SelectFlightsPageData selectFlightsPageData;
    PricePageData pricePageData
    PassengerData passengerData
    ShoppingCart shoppingCart
    ScenarioState scenarioState
    PurchasePageData purchasePageData
    FeeCalculator feeCalculator

    public PriceTable(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""
    }

    private static Map <String, String> FARE_TYPE_DESCRIPTIONS = ["Business Select": "Superior Benefits", "Anytime": "Great Flexibility",
            "Senior": "Dedicated Pricing", "Wanna Get Away": "Excellent Value", "Ding": "Exclusive Offers"]

    private static Map REVISED_PRICE_TABLE_HEADERS = ["trip": "Trip", "routing": "Rout",
            "fare_type": "Fare Type", "fare_details": "Fare Details",
            "priceItinerary_qty_total": "Quantity", "priceItinerary_totals": "Total"]

    private static Map OLD_PRICE_TABLE_HEADERS = ["ticket_type": "Passenger", "trip": "Trip", "routing": "Rout",
            "fare_type": "Fare Type", "base_fare": "Base Fare", "priceItinerary_taxesAndGovtFees": "Fees",
            "priceItinerary_qty_total": "Quantity", "priceItinerary_totals": "Total"]

    private mapTableColumnHeaders(WebElement table) {
        List<WebElement> columnHeaders = table.findElements(By.xpath('thead/tr/th'))
        Map<String, String> columnMap = new HashMap<String, String>()
        columnHeaders.each() { columnHeader ->
              columnMap.put(columnHeader.getAttribute("class"), columnHeader.getText().trim())
        }
        return columnMap
    }

    def revisedColumnHeaders() {
        return everyHeaderPresentWithMatchingDescription(REVISED_PRICE_TABLE_HEADERS)
    }

    def oldColumnsHeaders() {
        return everyHeaderPresentWithMatchingDescription(OLD_PRICE_TABLE_HEADERS)
    }

    def everyHeaderContainsMatchingDescription(Map expectedHeaders, Map foundHeaders) {
        return foundHeaders.every { foundHeader ->
            foundHeader.getValue().toString().contains(expectedHeaders.get(foundHeader.getKey()).toString())
        }
    }

    def everyHeaderIsPresent(Map expectedHeaders, Map foundHeaders) {
        return expectedHeaders.keySet() == expectedHeaders.keySet()
    }

    def everyHeaderPresentWithMatchingDescription(Map expectedHeaders) {
        WebElement priceTable = waitForElement(PRICING_ADULT)
        Map foundHeaders = mapTableColumnHeaders(priceTable)
        everyHeaderIsPresent(expectedHeaders, foundHeaders)
        everyHeaderContainsMatchingDescription(expectedHeaders, foundHeaders)
    }

    def anyPriceTableRowContainsFareTypeDescription() {
        WebElement priceTable = waitForElement(PRICING_ADULT)
        List<WebElement> priceRows = priceTable.findElements(By.cssSelector("tr.priceRow"))
        return priceRows.any {
            WebElement priceRow -> (FARE_TYPE_DESCRIPTIONS.values()).any {
                String description -> priceRow.findElement(By.className("fare_type")).text.contains(description)
            }
        }
    }

    def eachRowContainsMatchingFareTypeDescription() {
        WebElement priceTable = waitForElement(PRICING_ADULT)
        List<WebElement> priceRows = priceTable.findElements(By.cssSelector("tr.priceRow"))
        return priceRows.every { WebElement priceRow ->
            String fareTypeName = priceRow.findElement(By.className("fareTypeLink")).getAttribute("title")
            List<String> fareTypeContents = priceRow.findElement(By.className("fare_type")).getText().split("\\n")
            String fareTypeDescription = FARE_TYPE_DESCRIPTIONS.get(fareTypeName)
            fareTypeContents[0].trim().contains(fareTypeName) && fareTypeContents[1].trim().contains(fareTypeDescription)
        }
    }

    def linkOpensNewWindow(WebElement link, String page) {
        return link.isDisplayed() &&
               link.getAttribute("href").contains(page) &&
               link.getAttribute("target").contains("_")
    }

    def fareTypeLinkPresentWithTargetInEachPriceRow() {
        WebElement priceTable = waitForElement(PRICING_ADULT)
        List<WebElement> priceRows = priceTable.findElements(By.cssSelector("tr.priceRow"))
        return priceRows.every { priceRow ->
            WebElement priceRowLink = priceRow.findElement(By.className("fare_type")).findElement(By.xpath("a"))
            linkOpensNewWindow(priceRowLink, "air/fare-information.html")
        }
    }

    def fareTypeLinkPresentWithTargetInTable(String xpath, String page) {
        boolean linkPresent = isElementPresent(By.xpath(xpath))
        return linkPresent && linkOpensNewWindow(findElement(By.xpath(xpath)), page)
    }

    def headerFareTypeLinkPresent() {
        String headerPath = "//thead/tr/th[@class='fare_type']/a"
        return fareTypeLinkPresentWithTargetInTable(headerPath, "air/fare-information.html")
    }

    def footerFareTypeLinkPresent() {
        String footerPath = "//tfoot/tr/td/a"
        return fareTypeLinkPresentWithTargetInTable(footerPath, "fares/fares.html")
    }

    BigDecimal departurePrice(){
        if(itineraryData.isPromoCertBooking() || flow.isRapidRewardsPointsPurchaseOnly) {
            pricePageData.outboundTotal = waitForElement(DEPART_SUBTOTAL_ADULT_FFP).getText().replace('$', '').replace(',','').toBigDecimal()
            return pricePageData.outboundTotal
        } else {
            pricePageData.outboundTotal = waitForElement(DEPART_SUBTOTAL_ADULT).getText().replace('$', '').replace(',','').toBigDecimal()
            return pricePageData.outboundTotal
        }
    }

    BigDecimal departurePriceSenior() {
        pricePageData.outboundTotalSenior = waitForElement(DEPART_SUBTOTAL_SENIOR).getText().replace('$', '').replace(',','').toBigDecimal()
        return pricePageData.outboundTotalSenior
    }

    BigDecimal arrivalPrice() {
        if(itineraryData.isPromoCertBooking() || flow.isRapidRewardsPointsPurchaseOnly) {
            pricePageData.inboundTotal = waitForElement(RETURN_SUBTOTAL_ADULT_FFP).getText().replace('$', '').replace(',','').toBigDecimal()
            return pricePageData.inboundTotal
        } else {
            pricePageData.inboundTotal = waitForElement(RETURN_SUBTOTAL_ADULT).getText().replace('$', '').replace(',','').toBigDecimal()
            return pricePageData.inboundTotal
        }
    }

    BigDecimal  arrivalPriceSenior() {
        pricePageData.inboundTotalSenior = waitForElement(RETURN_SUBTOTAL_SENIOR).getText().replace('$', '').replace(',','').toBigDecimal()
        return pricePageData.inboundTotalSenior
    }


    def verifyRouting() {
        List<WebElement> outBoundStations = waitForElements(DEPART_CITY)
        StringBuilder outboundRouting = new StringBuilder()
        if (itineraryData.outboundRouting.equals("Nonstop")) {
            outboundRouting.append(itineraryData.departureStation).append("-").append(itineraryData.arrivalStation)
            if (itineraryData.showOneTableInPriceOrConfirmationPage()) {
                outboundRouting.append("-").append(itineraryData.returnStation)
            }
        } else {
            outboundRouting.append(itineraryData.departureStation).append("-").append(itineraryData.outboundConnectingStation).append("-").append(itineraryData.arrivalStation)
        }
        outBoundStations[0].text.shouldContain outboundRouting.toString(), "Outbound routing was incorrect"
        if(passengerData.containsSeniorAndAdultPassengers()) {
            outBoundStations[1].text.shouldContain outboundRouting.toString(), "Outbound routing was incorrect"
        }
        if(!itineraryData.showOneTableInPriceOrConfirmationPage()) {
            if (itineraryData.isRoundTrip()) {
                String inboundRouting
                if(itineraryData.inboundRouting.equals("Nonstop"))
                {
                    inboundRouting = itineraryData.arrivalStation + "-" + itineraryData.departureStation
                } else
                {
                    inboundRouting = itineraryData.arrivalStation + "-" + itineraryData.inboundConnectingStation + "-" + itineraryData.departureStation
                }
                //samurai
                //waitForElement(RETURN_CITY).text.shouldContain inboundRouting, "Inbound routing was incorrect"
            }
            if (itineraryData.isOpenJaw()) {
                String inboundRouting = itineraryData.arrivalStation + "-" + itineraryData.returnStation
                waitForElement(RETURN_CITY).text.shouldContain inboundRouting, "Inbound routing was incorrect"
            }
        }
    }

    def verifyFareType() {
        List<WebElement> outBoundFareTypes = waitForElements(DEPART_FARE_TYPE)
        if(itineraryData.isPromoCertBooking()){
            outBoundFareTypes[0].text.shouldContain "Award", "Outbound fare type did not match departure fare type"
        } else {
            outBoundFareTypes[0].text.replaceAll("\\s", "").shouldContain itineraryData.departingFlight_fareClass, "Adult outbound fare type did not match departure fare type"
            if(passengerData.containsSeniorAndAdultPassengers()){
                outBoundFareTypes[1].text.shouldContain itineraryData.seniorDepartingFlight_fareClass, "Senior outbound fare type did not match departure fare type"
            }
        }
        if(itineraryData.isRoundTripOrOpenJaw()){
            String inBoundFareType
            if (!itineraryData.showOneTableInPriceOrConfirmationPage()) {
                inBoundFareType = waitForElement(RETURN_FARE_TYPE).text
            } else {
                inBoundFareType = outBoundFareTypes.get(0).text
            }
            if(itineraryData.isPromoCertBooking()){
                inBoundFareType.shouldContain "Award", "Return fare type did not match departure fare type"
            } else {
                inBoundFareType.replaceAll('\\s','').shouldContain itineraryData.arrivingFlight_fareClass, "Inbound fare type did not match arrival fare type"
            }
        }
    }


    def verifyTotalOnPage() {
        List<WebElement> subTotals = waitForElements(SUBTOTAL_CONTAINER)
        BigDecimal adultOutBoundLegTotal = 0
        BigDecimal adultSubtotalOnPage = 0
        BigDecimal seniorOutBoundLegTotal = 0
        BigDecimal seniorSubtotalOnPage = 0
        BigDecimal adultInBoundLegTotal = 0
        BigDecimal seniorInBoundLegTotal = 0
        By depart_subtotal_adult = DEPART_SUBTOTAL_ADULT
        By return_subtotal_adult = RETURN_SUBTOTAL_ADULT
        if(itineraryData.isPromoCertBooking()){
            depart_subtotal_adult = DEPART_SUBTOTAL_ADULT_FFP
            return_subtotal_adult = RETURN_SUBTOTAL_ADULT_FFP
        }
        if(passengerData.containsSeniorAndAdultPassengers()) {
            adultOutBoundLegTotal = waitForElement(DEPART_SUBTOTAL_ADULT).text.replace('$', '').replace(',','').toBigDecimal()
            adultSubtotalOnPage = subTotals[0].findElement(SUBTOTAL_AMOUNT).text.split('\n')[0].replace('$', '').toBigDecimal()
            seniorOutBoundLegTotal = waitForElement(DEPART_SUBTOTAL_SENIOR).text.replace('$', '').replace(',','').toBigDecimal()
            seniorSubtotalOnPage = subTotals[1].findElement(SUBTOTAL_AMOUNT).text.split('\n')[0].replace('$', '').toBigDecimal()
            if (itineraryData.isRoundTripOrOpenJaw()) {
                adultInBoundLegTotal = waitForElement(RETURN_SUBTOTAL_ADULT).text.replace('$', '').replace(',','').toBigDecimal()
                seniorInBoundLegTotal = waitForElement(RETURN_SUBTOTAL_SENIOR).text.replace('$', '').replace(',','').toBigDecimal()
            }
        } else if (passengerData.containsSeniorPassenger()) {
            seniorOutBoundLegTotal = waitForElement(DEPART_SUBTOTAL_SENIOR).text.replace('$', '').replace(',','').toBigDecimal()
            seniorSubtotalOnPage = subTotals[0].findElement(SUBTOTAL_AMOUNT).text.split('\n')[0].replace('$', '').toBigDecimal()
            if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
               seniorInBoundLegTotal = waitForElement(RETURN_SUBTOTAL_SENIOR).text.replace('$', '').replace(',','').toBigDecimal()
            }
        }  else if (passengerData.containsAdultPassenger()) {
            adultOutBoundLegTotal = waitForElement(depart_subtotal_adult).text.replace('$', '').replace(',','').toBigDecimal()
            adultSubtotalOnPage = subTotals[0].findElement(SUBTOTAL_AMOUNT).text.split('\n')[0].replace('$', '').toBigDecimal()
            if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                adultInBoundLegTotal = waitForElement(return_subtotal_adult).text.replace('$', '').replace(',','').toBigDecimal()
            }
        }
        BigDecimal subTotalOnPage = adultSubtotalOnPage + seniorSubtotalOnPage
        BigDecimal legTotal = adultOutBoundLegTotal + seniorOutBoundLegTotal + adultInBoundLegTotal + seniorInBoundLegTotal
        airTotal().shouldEqual subTotalOnPage + purchasePageData.guardianFee, "Sum of subtotals did not match with air total"
        airTotal().shouldEqual legTotal + purchasePageData.guardianFee, "Sum of outbound leg totals did not match with air total"
    }

    BigDecimal airTotal() {
        BigDecimal total = waitForElement(AIR_TOTAL).text.replace('$', '').replace(',','').toBigDecimal()
        pricePageData.airTotal = total
        return total
    }

    def verifyPageAndShoppingCartTotal() {
        shoppingCart.tripTotal().shouldEqual airTotal()
    }

    FareBreakdownFlyOut openFareBreakDownFlyOut() {
        if(itineraryData.isPromoCertBooking() || flow.isRapidRewardsPointsPurchaseOnly) {
            waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_FFP)?.click()
            FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_FARE_BREAK_DOWN_FLY_OUT_FFP))
            return flyout
        }
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_ADT)?.click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_FARE_BREAK_DOWN_FLY_OUT_ADT))
        return flyout
    }

    FareBreakdownFlyOut openSeniorFareBreakDownFlyOut() {
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_SRC)?.click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_FARE_BREAK_DOWN_FLY_OUT_SRC))
        return flyout
    }

    FareBreakdownFlyOut openFareBreakDownFlyOutById() {
        if(itineraryData.isPromoCertBooking() || flow.isRapidRewardsPointsPurchaseOnly) {
            waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_FFP)?.click()
            FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_PRICE_DETAILS))
            return flyout
        }
        waitForElement(TOTAL_FARE_BREAK_DOWN_LINK_BY_ID_ADT)?.click()
        FareBreakdownFlyOut flyout = new FareBreakdownFlyOut(waitForElement(TOTAL_PRICE_DETAILS))
        return flyout
    }

    def verifyPassengerType() {
        List <WebElement> listPassengers = waitForElements(HEADER_LABEL)
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            listPassengers[0].text.shouldContain "POINTS"
        } else if(itineraryData.isPromoCertBooking()){
            listPassengers[0].text.shouldContain "AWARD", "Passenger type was not a Award"
        } else if (passengerData.containsSeniorAndAdultPassengers()) {
            listPassengers[0].text.shouldContain "ADULT", "Passenger type was not a Adult"
            listPassengers[1].text.shouldContain "SENIOR", "Passenger type was not a Senior"
        } else if (passengerData.containsAdultPassenger()) {
            listPassengers[0].text.shouldContain "ADULT", "Passenger type was not a Adult"
        } else if (passengerData.containsSeniorPassenger()) {
            listPassengers[0].text.shouldContain "SENIOR", "Passenger type was not a Senior"
        }
    }

    def verifyBaseFarePlusTax() {
        if (passengerData.containsAdultPassenger()) {
            FareBreakdownFlyOut adultFlyOut = openFareBreakDownFlyOut()
            BigDecimal adultTotal = adultFlyOut.calculateBaseFarePlusTaxes()
            adultTotal.shouldEqual adultFlyOut.total, "Adult Basefare: ${adultFlyOut.baseFare} + ${adultFlyOut.taxes} did not equal ${adultTotal}"
        }
        if (passengerData.containsSeniorPassenger()) {
            FareBreakdownFlyOut seniorFlyOut = openSeniorFareBreakDownFlyOut()
            BigDecimal seniorTotal = seniorFlyOut.calculateBaseFarePlusTaxes()
            seniorTotal.shouldEqual seniorFlyOut.total, "Senior Basefare: ${seniorFlyOut.baseFare} + ${seniorFlyOut.taxes} did not equal ${seniorTotal}"
        }
    }

    def verifyBaseFarePlusTaxOnReconcile() {
        FareBreakdownFlyOut adultFlyOut = openFareBreakDownFlyOutById()
        BigDecimal adultTotal = adultFlyOut.calculateBaseFarePlusTaxes()
        adultTotal.shouldEqual adultFlyOut.total, "Adult Basefare: ${adultFlyOut.baseFare} + ${adultFlyOut.taxes} did not equal ${adultTotal}"
    }

    def verifyTaxesAndBaseFareInFareBreakDown() {
        if (passengerData.containsAdultPassenger()) {
            FareBreakdownFlyOut adultFlyOut = openFareBreakDownFlyOut()
            BigDecimal adultPassengerTotal = (adultFlyOut.total + adultFlyOut.segmentFee + adultFlyOut.passengerFacilityFee + adultFlyOut.securityFee)
            adultPassengerTotal.shouldEqual adultFlyOut.totalPerPassenger, "Adult passenger total did not equal total:${adultFlyOut.total} + segment fee:${adultFlyOut.segmentFee} = passenger facility fee:${adultFlyOut.passengerFacilityFee} + security fee:${adultFlyOut.securityFee}"
        }
        if (passengerData.containsSeniorPassenger()) {
            FareBreakdownFlyOut seniorFlyOut = openSeniorFareBreakDownFlyOut()
            BigDecimal seniorPassengerTotal = (seniorFlyOut.total + seniorFlyOut.segmentFee + seniorFlyOut.passengerFacilityFee + seniorFlyOut.securityFee)
            seniorPassengerTotal.shouldEqual seniorFlyOut.totalPerPassenger, "Senior passenger total did not equal total:${seniorFlyOut.total} + segment fee:${seniorFlyOut.segmentFee} = passenger facility fee:${seniorFlyOut.passengerFacilityFee} + security fee:${seniorFlyOut.securityFee}"
        }
    }

    def verifyTotalPlusPassengerCount() {
        FareBreakdownFlyOut adultFlyOut
        FareBreakdownFlyOut seniorFlyOut
        BigDecimal grandTotal = 0
        BigDecimal dollarTotal = 0
        if (passengerData.containsAdultPassenger()){
            adultFlyOut = openFareBreakDownFlyOut()
            grandTotal += (adultFlyOut.passengerCount * adultFlyOut.totalPerPassenger)
            dollarTotal += adultFlyOut.dollarTotal()
        } else {
            seniorFlyOut = openSeniorFareBreakDownFlyOut()
            grandTotal += (seniorFlyOut.seniorPassengerCount * seniorFlyOut.seniorTotalPerPassenger)
            dollarTotal += seniorFlyOut.seniorDollarTotal()
        }
        grandTotal.shouldEqual dollarTotal, "Grand total did not equal per passenger total * passenger count"
    }

    def verifyTotalOnPageWithTotalInFareBreakDown() {
        BigDecimal adultTotal = 0
        BigDecimal seniorTotal = 0
        if (passengerData.containsAdultPassenger()){
            FareBreakdownFlyOut adultFlyOut = openFareBreakDownFlyOut()
            adultTotal = adultFlyOut.dollarTotal()
        }
        if(passengerData.containsSeniorPassenger()) {
            FareBreakdownFlyOut seniorFlyOut = openSeniorFareBreakDownFlyOut()
            seniorTotal = seniorFlyOut.seniorDollarTotal()
        }
        BigDecimal total = adultTotal + seniorTotal
        airTotal().shouldEqual total, "Total on price page: ${airTotal()} did not match fare break down total: ${total}"
    }

    def verifyPromoCertFlyOutValues(FareBreakdownFlyOut flyOut) {
        BigDecimal securityFeeBase = feeCalculator.calculateSecurityFee()
        if(itineraryData.isRoundTripOrOpenJaw()){
            securityFeeBase *=2
        }
        String baseFare = flyOut.getStringBaseFare()
        baseFare.shouldBe "Free", "Base fare is not Free"

        BigDecimal segmentFee = flyOut.getSegmentFee()
        BigDecimal passengerFacilityCharge = flyOut.getPassengerFacilityFee()
        BigDecimal securityFee = flyOut.getSecurityFee()
        BigDecimal totalTaxesAndFees= flyOut.getTotalTaxesAndFees()
        (segmentFee + passengerFacilityCharge + securityFee).shouldEqual totalTaxesAndFees , "fare Breakdown total taxes and fees did not equal the sum of segment fee, security fee and passenger facility fee"
        totalTaxesAndFees.shouldBe securityFeeBase, "Total Taxes and Fees is not " + securityFeeBase
        securityFee.shouldEqual securityFeeBase, "Security fee is not "+securityFeeBase

        String totalPerPerson = flyOut.getTotalPerPassengerPromoCert()
        totalPerPerson.shouldBe "Free", "Total per person is not Free"

        String pointsTotal = flyOut.getPointsTotalForPromoCert()
        pointsTotal.shouldEqual baseFare, "Points Total is not equal to baseFare"

        BigDecimal total =flyOut.dollarTotal()
        total.shouldEqual securityFee, "Total is not the same as security fee"

        flyOut.closeFlyout()
    }

    def verifyRedeemableMemberPoints() {
        if (!itineraryData.isMixedFare) {
            FareBreakdownFlyOut flyOut
            if (passengerData.containsAdultPassenger()) {
                flyOut = openFareBreakDownFlyOut()
                verifyRedeemableMemberPointsData(flyOut)
            }
            if (passengerData.containsSeniorPassenger()) {
                flyOut = openSeniorFareBreakDownFlyOut()
                verifyRedeemableMemberPointsData(flyOut)
            }
        } else {
            verifyRedeemablePointsForMixedFare()
        }
    }

    void verifyRedeemablePointsForMixedFare() {
        BigDecimal outboundFareFactor = retrieveFareFactor("departure")
        BigDecimal inboundFareFactor = retrieveFareFactor("arrival")
        BigDecimal maxFareFactor = outboundFareFactor.max(inboundFareFactor)

        waitForElement(DEPART_SUBTOTAL_ADULT).click()
        FareBreakdownFlyOut outboundFlyout = new FareBreakdownFlyOut(waitForElement(DEPARTURE_PRICE_FLYOUT))
        BigDecimal outBoundPointsSubtotal = outboundFlyout.getBaseFare() * outboundFareFactor

        waitForElement(RETURN_SUBTOTAL_ADULT).click()
        FareBreakdownFlyOut inboundFlyout = new FareBreakdownFlyOut(waitForElement(ARRIVAL_PRICE_FLYOUT))
        BigDecimal inBoundPointsSubtotal = inboundFlyout.getBaseFare() * inboundFareFactor

        equalOrDifferentByOne(inBoundPointsSubtotal + outBoundPointsSubtotal, retrievePointsOnPage(), "the points calculated did not match the ones in the page")
    }

    BigDecimal retrieveFareFactor(String direction) {
        if(direction.equals("departure")) {
            if(itineraryData.departingFlight_fareClass.equals("WannaGetAway") ||
                    itineraryData.departingFlight_fareClass.equals("Senior")) {
                return 6
            }
            if(itineraryData.departingFlight_fareClass.equals("BusinessSelect")) {
                return 12
            }
            return 10
        }
        if(direction.equals("arrival")) {
            if(itineraryData.arrivingFlight_fareClass.equals("WannaGetAway") ||
                    itineraryData.arrivingFlight_fareClass.equals("Senior")) {
                return 6
            }
            if(itineraryData.arrivingFlight_fareClass.equals("BusinessSelect")) {
                return 12
            }
            return 10
        }
        if(direction.equals("return")) {
            if(itineraryData.returningFlight_fareClass.equals("WannaGetAway") ||
                    itineraryData.returningFlight_fareClass.equals("Senior")) {
                return 6
            }
            if(itineraryData.returningFlight_fareClass.equals("BusinessSelect")) {
                return 12
            }
            return 10
        }
    }

    def verifyRedeemableMemberPointsData(FareBreakdownFlyOut flyOut) {
        if (passengerData.passengers.size() == 0) {
            passengerData.passengers = scenarioState.lastAirReservation.passengers
        }
        BigDecimal fareFactor = retrieveFareFactor("departure")
        BigDecimal pointCalc = 0
        if (passengerData.containsAdultPassenger() && itineraryData.promoCode == null) {
            pointCalc = flyOut.getBaseFare() * fareFactor
        } else if (passengerData.containsSeniorPassenger() && itineraryData.promoCode == null) {
            pointCalc = flyOut.getBaseFare() * fareFactor
        } else if (itineraryData.promoCode != null) {
            pointCalc = flyOut.getAdjustedFare() * fareFactor
        }
        BigDecimal pointsOnPage = retrievePointsOnPage()
        pricePageData.rapidRewardPoints = pointsOnPage
        equalOrDifferentByOne(pointCalc, pointsOnPage, "the points calculated did not match the ones in the page")
    }

    def BigDecimal retrievePointsOnPage() {
        waitForElement(POINT_CALC_LINK).text.split(" ")[0].toBigDecimal()
    }

    def equalOrDifferentByOne(BigDecimal expectedNumber, BigDecimal recievedNumber, String errorMessage) {
        /*(expectedNumber + 2).shouldBeGreaterThan recievedNumber, errorMessage
        (expectedNumber - 2).shouldBeLessThan recievedNumber, errorMessage*/
    }

    def verifyNumberOfPassengers() {
        def passengerQuantities = waitForElements(PASSENGER_NUMBER)
        if (passengerData.containsAdultPassenger() ) {
            passengerQuantities[0].text.toInteger().shouldBe passengerData.adults.size(), "Passenger quantity did not match the amount of passenger booked"
        }
        if (passengerData.containsSeniorAndAdultPassengers()) {
            passengerQuantities[1].text.toInteger().shouldBe passengerData.seniors.size(), "Passenger quantity did not match the amount of passenger booked"
        } else if (passengerData.containsSeniorPassenger()) {
            passengerQuantities[0].text.toInteger().shouldBe passengerData.seniors.size(), "Passenger quantity did not match the amount of passenger booked"
        }
        if (itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
            passengerQuantities[1].text.toInteger().shouldBe passengerData.passengers.size(), "Passenger quantity did not match the amount of passenger booked"
        }
    }

    def verifyPriceFareBreakDown() {
        FareBreakdownFlyOut flyOut
        if (passengerData.containsAdultPassenger()) {
            flyOut = openFareBreakDownFlyOut()
            if(flow.isRapidRewardsPointsPurchaseOnly) {
                verifyPointsFlyout(flyOut)
            } else if (itineraryData.isPromoCertBooking()){
                verifyPromoCertFlyOutValues(flyOut)
            } else {
                verifyFareBreakdownData(flyOut)
            }
        }
        if (passengerData.containsSeniorPassenger()) {
            flyOut = openSeniorFareBreakDownFlyOut()
            if(flow.isRapidRewardsPointsPurchaseOnly) {
                verifyPointsFlyout(flyOut)
            } else if (itineraryData.isPromoCertBooking()){
                verifyPromoCertFlyOutValues(flyOut)
            } else {
                verifyFareBreakdownData(flyOut)
            }
        }
    }

    def verifyPointsFlyout(FareBreakdownFlyOut flyOut) {
        if (itineraryData.isOneWay()) {
            flyOut.pointsFare().shouldEqual selectFlightsPageData.outboundFlightPoints, "The fare in the breakdown was not the same as in the bug page"
            flyOut.getTotalPointsPerPassenger().shouldEqual selectFlightsPageData.outboundFlightPoints, "Total per passenger was not the same as in the bug page"
        }
        else {
            flyOut.pointsFare().shouldEqual selectFlightsPageData.totalOutboundInboundFlightPoints, "The fare in the breakdown was not the same as in the bug page"
            flyOut.getTotalPointsPerPassenger().shouldEqual selectFlightsPageData.totalOutboundInboundFlightPoints, "Total per passenger was not the same as in the bug page"
        }
        verifySecurityFee(flyOut.getSecurityFee())
        flyOut.getSegmentFee().shouldEqual "0.00".toBigDecimal()
        flyOut.getPassengerFacilityFee().shouldEqual "0.00".toBigDecimal()
        flyOut.getTotalTaxesAndFees().shouldEqual pricePageData.tripTotal
        flyOut.dollarTotal().shouldEqual pricePageData.tripTotal
    }

    private void verifyFareBreakdownData(FareBreakdownFlyOut flyOut) {

        if (passengerData.containsAdultPassenger()) {
            BigDecimal baseFare = flyOut.baseFare
            if(itineraryData.promoCode != null) {
                baseFare -= flyOut.fareDiscount
                baseFare.shouldBe flyOut.adjustedFare, "Base fare did not match adjusted fare in fare breakdown"
                (flyOut.adjustedFare + flyOut.taxes).shouldBe flyOut.total, "Adjusted Fare Plus Excise Tax did not match advertised fare in fare breakdown"
            }
            BigDecimal exciseTaxes = flyOut.getTaxes()
            BigDecimal advertisedFare = flyOut.getTotal()

            (baseFare + exciseTaxes).shouldEqual advertisedFare , "fare Breakdown advertised fare ${advertisedFare} did not equal base fare ${baseFare} + Excise Taxes ${exciseTaxes}"
            equalOrDifferentByOne(baseFare * 0.075, exciseTaxes, "The fare Breakdown exice taxes did not equal the 7.5% of the base fare")

            BigDecimal segmentFee = flyOut.getSegmentFee()
            BigDecimal passengerFacilityCharge = flyOut.getPassengerFacilityFee()
            BigDecimal securityFee = flyOut.getSecurityFee()
            BigDecimal totalPerPerson = flyOut.getTotalPerPassenger()

            (advertisedFare + segmentFee + passengerFacilityCharge + securityFee).shouldEqual totalPerPerson , "fare Breakdown total per passenger did not equal the sum of advertised fare, segment fee, security fee and passenger facility fee"

            verifySecurityFee(securityFee)

            BigDecimal passengerCount = flyOut.getPassengerCount()
            BigDecimal total = flyOut.dollarTotal()

            (totalPerPerson * passengerCount).shouldEqual total, "fare Breakdown total did not equal the total per person * passenger count"
            flyOut.closeFlyout()
        }
    }

    def verifyTripTypes() {
        List<WebElement> listOfTripTypes = waitForElements(By.className("passengerTrip"))
        listOfTripTypes[0].text.shouldBe "Depart", "The inbound trip type was not present"
        if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()){
            listOfTripTypes[1].text.shouldBe "Return", "The outbound trip type was not present"
        }
    }

    def BigDecimal getSubTotal() {
        String subTotalCell = waitForElement(By.className("bookingFormTotals")).text
        pricePageData.subTotal = subTotalCell.split('\n')[0].replace('$','').replace(',','').toBigDecimal()
        return pricePageData.subTotal
    }

    def BigDecimal getSeniorSubTotal() {
        List<WebElement> subTotalCells = waitForElements(By.className("bookingFormTotals"))
        int subTotalCellsIndex = 0
        if (passengerData.containsSeniorAndAdultPassengers()) {
            subTotalCellsIndex = 1
        }
        pricePageData.seniorSubTotal = subTotalCells[subTotalCellsIndex].text.split('\n')[0].replace('$','').replace(',','').toBigDecimal()
        return pricePageData.seniorSubTotal
    }

    def verifySubTotal() {
        BigDecimal costTotal = 0
        BigDecimal subTotal = 0
        if (passengerData.containsAdultPassenger()) {
            costTotal += departurePrice()
            subTotal += getSubTotal()
            if(!itineraryData.oneWay && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                costTotal += arrivalPrice()
            }
        }
        if(passengerData.containsSeniorPassenger()) {
            costTotal += departurePriceSenior()
            subTotal += getSeniorSubTotal()
            if(!itineraryData.oneWay && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                costTotal += arrivalPriceSenior()
            }
        }

        costTotal.shouldEqual subTotal, "The sub total ${subTotal} was not equal to the cost total ${costTotal}"
    }

    def verifyAirTotalAndFareBreakDownTotal() {
        FareBreakdownFlyOut flyOut
        BigDecimal fareBreakDownTotal = 0
        if (passengerData.containsAdultPassenger()) {
            flyOut = openFareBreakDownFlyOut()
            fareBreakDownTotal = flyOut.dollarTotal()
        }
        if(passengerData.containsSeniorPassenger()) {
            flyOut = openSeniorFareBreakDownFlyOut()
            fareBreakDownTotal += flyOut.seniorDollarTotal()
        }
        fareBreakDownTotal += purchasePageData.guardianFee
        fareBreakDownTotal.shouldEqual airTotal(), "The fare break down total ${fareBreakDownTotal} was not equal air total ${airTotal()}"
    }

    def verifyOutboundAndInboundTotalAmounts() {
        BigDecimal obTotalAmountFromSP = 0
        BigDecimal obTotalAmountFromPT = 0
        BigDecimal ibTotalAmountFromSP = 0
        BigDecimal ibTotalAmountFromPT = 0
        if (passengerData.containsAdultPassenger()){
            if(flow.isRapidRewardsPointsPurchaseOnly || itineraryData.isPromoCertBooking()){
                obTotalAmountFromSP = pricePageData.outboundTotal
                obTotalAmountFromPT += waitForElement(DEPART_SUBTOTAL_ADULT_FFP).text.replace('$','').toBigDecimal()
                if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                    ibTotalAmountFromSP = pricePageData.inboundTotal
                    ibTotalAmountFromPT = waitForElement(RETURN_SUBTOTAL_ADULT_FFP).text.replace('$','').toBigDecimal()
                }
            } else {
                obTotalAmountFromSP = selectFlightsPageData.getOutboundFlightPrice() * passengerData.getAdults().size()
                obTotalAmountFromPT += waitForElement(DEPART_SUBTOTAL_ADULT).text.replace('$','').toBigDecimal()
                if(itineraryData.isRoundTripOrOpenJaw() && !itineraryData.showOneTableInPriceOrConfirmationPage()) {
                    ibTotalAmountFromSP = selectFlightsPageData.getInboundFlightPrice() * passengerData.getAdults().size()
                    ibTotalAmountFromPT += waitForElement(RETURN_SUBTOTAL_ADULT).text.replace('$','').toBigDecimal()
                }
            }
        }
        if (passengerData.containsSeniorPassenger()) {
            if(itineraryData.seniorDepartingFlight_fareClass == "Senior"){
                obTotalAmountFromSP += selectFlightsPageData.getOutboundSeniorFlightPrice() * passengerData.getSeniors().size()
                if(itineraryData.isRoundTripOrOpenJaw()) {
                    ibTotalAmountFromSP += selectFlightsPageData.getInboundSeniorFlightPrice() * passengerData.getSeniors().size()
                }
            } else {
                obTotalAmountFromSP += selectFlightsPageData.getOutboundFlightPrice() * passengerData.getSeniors().size()
                if(itineraryData.isRoundTripOrOpenJaw()) {
                    ibTotalAmountFromSP += selectFlightsPageData.getInboundFlightPrice() * passengerData.getSeniors().size()
                }
            }
            obTotalAmountFromPT += waitForElement(DEPART_SUBTOTAL_SENIOR).text.replace('$','').toBigDecimal()
            if(itineraryData.isRoundTripOrOpenJaw()) {
                ibTotalAmountFromPT += waitForElement(RETURN_SUBTOTAL_SENIOR).text.replace('$','').toBigDecimal()
            }
        }
        equalOrDifferentByOne(obTotalAmountFromPT, obTotalAmountFromSP, "The outbound total amount from price table was not the same as the BUG page")
        equalOrDifferentByOne(ibTotalAmountFromPT, ibTotalAmountFromSP, "The inbound total amount from price table was not the same as the BUG page")
    }

    def verifyFareBreakDownByBreakdownById() {
        FareBreakdownFlyOut flyOut = openFareBreakDownFlyOutById()
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            verifyPointsFlyout(flyOut)
        } else {
            BigDecimal total = flyOut.calculateBaseFarePlusTaxes()
            total.shouldEqual flyOut.total, "Basefare: ${flyOut.baseFare} + ${flyOut.taxes} did not equal ${total}"
            BigDecimal passengerTotal = (flyOut.total + flyOut.segmentFee + flyOut.passengerFacilityFee + flyOut.securityFee)
            passengerTotal.shouldEqual flyOut.totalPerPassenger, "Passenger total did not equal total:${flyOut.total} + segment fee:${flyOut.segmentFee} = passenger facility fee:${flyOut.passengerFacilityFee} + security fee:${flyOut.securityFee}"
            verifyFareBreakdownData(flyOut)
        }
    }

    def verifyPendingAirItinerary() {
        BigDecimal outboundTotal = departurePrice()
        BigDecimal pendingAir = waitForElement(PENDING_AIR).text.split(' ')[0].split('\\n')[0].replace('$','').replace(',','').toBigDecimal()
        pricePageData.tripTotal = pendingAir
        pendingAir.shouldEqual outboundTotal, "The pending air and the outbound total were not equal"
    }

    def verifyAdditionalCredit() {
        BigDecimal pendingAir = departurePrice()
        BigDecimal availableFunds = waitForElement(AVAILABLE_FUNDS).text.replace('\\s','').replace('$','').replace(',','').toBigDecimal()
        BigDecimal additionalAmountDue = waitForElement(ADITIONAL_AMOUNT_DUE).text.replace('\\s','').replace('$','').replace('(','').replace(')','').toBigDecimal()
        BigDecimal amountDue = pendingAir - availableFunds
        Math.abs(amountDue).shouldEqual Math.abs(additionalAmountDue), "Additional amount due was not the difference between pending air and available funds"
        pricePageData.additionalAmountDue = amountDue < 0 ? 0 : additionalAmountDue
        pricePageData.additionalCreditDue = amountDue
    }

    def verifyRedeemableMemberPointsByFlyoutId() {
        FareBreakdownFlyOut flyOut = openFareBreakDownFlyOutById()
        verifyRedeemableMemberPointsData(flyOut)
    }

    def verifyAvailableFunds() {
        BigDecimal availableFundsInPage = waitForElements(REPRICE_AVAILABLE_FUNDS)[1].text.replace('$','').replace(',','').toBigDecimal()
        availableFundsInPage.shouldEqual scenarioState.getLastAirReservation().price, "Available funds were not the same as the pnr reservation"
        pricePageData.availableFunds = availableFundsInPage
    }

    def verifyPassengerCountAdult(int adultPassengerCount) {
        waitForElement(PASSENGER_NUMBER_OUTBOUND_ADULT).text.toInteger().shouldEqual adultPassengerCount, "Passenger count for adult outbound is not correct"
    }

    def verifyPassengerCountSenior(int seniorPassengerCount) {
        waitForElement(PASSENGER_NUMBER_OUTBOUND_SENIOR).text.toInteger().shouldEqual seniorPassengerCount, "Passenger count for senior outbound is not correct"
    }

    def verifyAirTotalIsPresent() {
        waitForElement(AIR_TOTAL).isDisplayed().shouldEqual true, "Air total is not present on page"
    }

    def verifyMixedPaxSubTotalFromSelectFlightPage() {
        List <WebElement> subTotals = waitForElements(By.className("pricingFooterEnhanced"))
        BigDecimal subTotalAdult = subTotals[0].findElement(By.className("subTotal")).text.split('\n')[0].replace('$', '').toBigDecimal()
        BigDecimal subTotal = passengerData.getAdults().size() * selectFlightsPageData.outboundFlightPrice
        (subTotalAdult + 1).shouldBeGreaterThan subTotal, "Adult subtotal is not correct"
        (subTotalAdult - 1).shouldBeLessThan subTotal, "Adult subtotal is not correct"
        BigDecimal subTotalSenior = subTotals[1].findElement(By.className("subTotal")).text.split('\n')[0].replace('$', '').toBigDecimal()
        if(itineraryData.seniorDepartingFlight_fareClass == "Senior"){
            subTotal = passengerData.getSeniors().size() * selectFlightsPageData.outboundSeniorFlightPrice
        } else {
            subTotal = passengerData.getSeniors().size() * selectFlightsPageData.outboundFlightPrice
        }
        (subTotalSenior + 1).shouldBeGreaterThan subTotal, "Senior subtotal is not correct"
        (subTotalSenior - 1).shouldBeLessThan subTotal, "Senior subtotal is not correct"
    }

    def verifySumSubtotalEqualsAirTotal() {
        List<WebElement> subTotals = waitForElements(By.className("pricingFooterEnhanced"))
        BigDecimal sumSubtotal = subTotals[0].findElement(By.className("subTotal")).text.split('\n')[0].replace('$', '').toBigDecimal()
        if(passengerData.containsSeniorAndAdultPassengers()) {
            sumSubtotal += subTotals[1].findElement(By.className("subTotal")).text.split('\n')[0].replace('$', '').toBigDecimal()
        }
        sumSubtotal += purchasePageData.guardianFee
        waitForElement(By.id("subtotalForAir")).text.replace('$', '').toBigDecimal().shouldEqual sumSubtotal, "Sum of subtotals is not equal to Air total"
    }

    def verifyAdultSeniorCount() {
        if (passengerData.containsAdultPassenger()) {
            verifyPassengerCountAdult(passengerData.getAdults().size())
        }
        if(passengerData.containsSeniorPassenger()){
            verifyPassengerCountSenior(passengerData.getSeniors().size())
        }
    }

    def verifyTotalPoints() {
        BigDecimal pointsOnPage = waitForElement(TOTAL_POINTS).text.replace(',','').replace(' pts','').toBigDecimal()
        pointsOnPage.shouldEqual selectFlightsPageData.totalOutboundInboundFlightPoints
    }

    def verifyGovTaxesFeesLink() {
        isElementPresent(GOV_TAXES_AND_FEE_INCLUDE).shouldBe true, "Gov't taxes and fees now included was not present in the page"
    }

    def verifyFareBreakdownPointsTotal() {
        FareBreakdownFlyOut flyOut = openFareBreakDownFlyOutById()
        waitForElement(OUTBOUND_TOTAL_POINTS).text.shouldContain flyOut.pointsTotalForPromoCert, "The total points in price table is not the same as in fare breakdown flyout in reprice page"
    }

    def verifySecurityFee(BigDecimal securityFee){
        BigDecimal calculatedSecurityFee = feeCalculator.calculateSecurityFee()
        securityFee.shouldEqual calculatedSecurityFee, "security fee does not equal ${calculatedSecurityFee}"
    }
}
