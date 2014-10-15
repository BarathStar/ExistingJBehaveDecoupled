package pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import static com.google.common.collect.Sets.newHashSet
import state.Flow
import util.SelectFlightsPageData

class SelectFlightRow {

    private static final int MILLISECONDS_IN_THIRTY_SIX_HOURS = 129600000
    private static final By PRODUCT_PRICE = By.className("product_price")
    private static final By ORIGINAL_PRICE = By.className("original_price")
    private long MILLISECONDS_IN_DAY = 86400000
    private long MILLISECONDS_IN_TWO_AND_A_HALF_HOURS = 9000000
    private long MILLISECONDS_IN_FORTY_FIVE_MINUTES = 2700000
    private long MILLISECONDS_IN_TWENTY_FIVE_MINUTES = 1500000
    private long MILLISECONDS_IN_FOUR_HOURS = 14400000
    private static final String WEB_ONLY_LABEL_XPATH = "//div[@class='webonlyWrapper']"
    private static By STOP_CITIES_CONTAINER = By.cssSelector("div.additionalStopList")
    private static By FLIGHT_DETAIL_LINK = By.cssSelector("a.bugLinkRouting")
    private static By FLIGHT_CHANCE_PLANE_CITY = By.cssSelector("td.routing_column span.bugText")
    private static String CHANGE_PLANE_TEXT = "Change Planes "

    WebElement rowElement
    String rowNumber
    String rowDirection
    String radioButtonId
    String fareClassCode
    Flow flow
    SelectFlightsPageData selectFlightsPageData


    SelectFlightRow(WebElement flightRow, String direction, String fareClass, Flow flow, SelectFlightsPageData selectFlightsPageData) {
        rowElement = flightRow;
        rowNumber =  rowElement.getAttribute('id').split('_')[2]
        rowDirection = direction.equals("outbound") ? "Out" : "In"
        fareClassCode = fareClass
        radioButtonId = rowDirection + rowNumber + fareClassCode
        this.flow = flow
        this.selectFlightsPageData = selectFlightsPageData
        if(flow.isRapidRewardsPointsPurchaseOnly) {
            List<WebElement> pointsContainer = rowElement.findElements(PRODUCT_PRICE)
            String flightPoints
            if(fareClassCode.equals("A")) {
                flightPoints = pointsContainer[0].text
            } else if(fareClassCode.equals("B")) {
                flightPoints = pointsContainer[1].text
            } else {
                flightPoints = pointsContainer[2].text
            }
            if(rowDirection.equals("Out")) {
                selectFlightsPageData.outboundFlightPoints = flightPoints.replace(',','').toBigDecimal()
                selectFlightsPageData.totalOutboundInboundFlightPoints = selectFlightsPageData.outboundFlightPoints
            } else {
                selectFlightsPageData.inboundFlightPoints = flightPoints.replace(',','').toBigDecimal()
                selectFlightsPageData.totalOutboundInboundFlightPoints = selectFlightsPageData.outboundFlightPoints + selectFlightsPageData.inboundFlightPoints
            }
        }
    }

    public boolean hasAvailableFlightForFareClass() {
        try {
            getRadioButton()
            return true
        } catch (NoSuchElementException e) {
            return false
        }
    }

    public def selectAvailableFlight() {
        getRadioButton().click()
    }

    public void saveDateAndTimeDepartingFlightArrives() {
        flow.dateAndTimeDepartingFlightArrives = getDateAndTimeThisFlightArrives()
    }

    public void saveDateAndTimeFlightDeparts(){
        flow.dateAndTimeFlightDeparts = getDateAndTimeThisFlightLeaves()

    }

    public boolean isCorrectRouting(String routing=""){
        if (!routing)
            return true
        else
            return getRadioButton().getAttribute("title").contains(routing)
    }

    public boolean isCorrectCarrier(List<String> airlineCodes) {
        Set<String> allAirlineCodes = newHashSet('WN', 'FL');

        Set<String> correctAirlineCodes = newHashSet(airlineCodes)
        Set<String> incorrectAirlineCodes = allAirlineCodes - correctAirlineCodes

        def radioButtonValue = getRadioButton().getAttribute("value")
        return (correctAirlineCodes.every { radioButtonValue.contains(',' + it + ',') }
        && ! incorrectAirlineCodes.any { radioButtonValue.contains(',' + it + ',') })
    }

    public boolean isWithinCheckinTimeLimit(String direction) {
        Date dateAndTimeThisFlightLeaves = getDateAndTimeThisFlightLeaves()

        if (direction.equals("outbound")) {
            return isGreaterThan2AndAHalfHours(dateAndTimeThisFlightLeaves) && isLessThan24Hours(dateAndTimeThisFlightLeaves)
        }
        else {
            return isReturnFlightFarEnoughFromDeparture()
        }
    }

    public earlyBirdEligibilityCheck(String direction) {
        Date dateAndTimeThisFlightLeaves = getDateAndTimeThisFlightLeaves()
        if (direction.equals("outbound")) {
            isGreaterThanThirtySixHours(dateAndTimeThisFlightLeaves) ?: (flow.isEarlyBirdEligibleOutBound = true)
        } else if (direction.equals("inbound")) {
            isGreaterThanThirtySixHours(dateAndTimeThisFlightLeaves) ?: (flow.isEarlyBirdEligibleInBound = true)
        }
    }


    public boolean checkIfValid(List<String> airlineCodes, String routing, def iswebOnly, String[] stopCitiesNames = null, String stopChangePlaneCity = null) {

        boolean isFlightRowValid = hasAvailableFlightForFareClass() && isCorrectCarrier(airlineCodes) && rowElement.isDisplayed() && isCorrectRouting(routing) && isCorrectStopCitiesList(routing, stopCitiesNames) && isCorrectStopChangePlane(routing, stopChangePlaneCity)
        if (isFlightRowValid && iswebOnly) {
            isFlightRowValid = isWebOnly()
        }
        if (isFlightRowValid && rowDirection == "In") {
            isFlightRowValid = isReturnFlightFarEnoughFromDeparture()
        }
        return isFlightRowValid
    }

    public boolean checkIfValidForPriceThough(List<String> airlineCodes, String routing, def iswebOnly, boolean isConnectionLessThanFourHours = false ) {
        boolean isFlightRowValid = hasAvailableFlightForFareClass()
        if (isFlightRowValid) {
            isFlightRowValid = (isFlightRowValid &&
                    isCorrectCarrier(airlineCodes) &&
                    rowElement.isDisplayed() &&
                    isCorrectRouting(routing) &&
                    isReturnFlightFarEnoughFromDeparture() &&
                    (isConnectionLessThanFourHours? isReturnFlightInLessThanFourHoursFromDeparture() : isReturnFlightWithInFourHoursFromDeparture()))
            if (isFlightRowValid && iswebOnly) {
                isFlightRowValid = isWebOnly()
            }
        }
        return isFlightRowValid
    }

    public boolean checkIfValidFlightWithLessThenTwentyFiveMinutesLayover(List<String> airlineCodes, String routing, def iswebOnly) {
        boolean isFlightRowValid = hasAvailableFlightForFareClass()
        if (isFlightRowValid) {
            isFlightRowValid = (isFlightRowValid &&
                    isCorrectCarrier(airlineCodes) &&
                    rowElement.isDisplayed() &&
                    isCorrectRouting(routing) &&
                    isReturnFlightLessThenTwentyFiveMinutesFromDeparture())
            if (isFlightRowValid && iswebOnly) {
                isFlightRowValid = isWebOnly()
            }
        }
        return isFlightRowValid
    }

    private boolean isGreaterThan2AndAHalfHours(Date date) {
        return (date.getTime() - new Date().getTime() >= MILLISECONDS_IN_TWO_AND_A_HALF_HOURS)
    }

    private boolean isGreaterThanThirtySixHours(Date date) {
        return (date.getTime() - new Date().getTime() >= MILLISECONDS_IN_THIRTY_SIX_HOURS)
    }

    private boolean isLessThan24Hours(Date date) {
        return (date.getTime() - new Date().getTime() <= MILLISECONDS_IN_DAY)
    }

    public boolean isReturnFlightFarEnoughFromDeparture() {
        Date dateAndTimeReturningFlightLeaves = getDateAndTimeThisFlightLeaves()
        return (dateAndTimeReturningFlightLeaves.getTime() - flow.dateAndTimeDepartingFlightArrives.getTime() >= MILLISECONDS_IN_FORTY_FIVE_MINUTES)
    }

    public boolean isReturnFlightLessThenTwentyFiveMinutesFromDeparture() {
        Date dateAndTimeReturningFlightLeaves = getDateAndTimeThisFlightLeaves()
        return (dateAndTimeReturningFlightLeaves.getTime() - flow.dateAndTimeDepartingFlightArrives.getTime() > 0
        && dateAndTimeReturningFlightLeaves.getTime() - flow.dateAndTimeDepartingFlightArrives.getTime() <= MILLISECONDS_IN_TWENTY_FIVE_MINUTES)
    }

    public boolean isReturnFlightWithInFourHoursFromDeparture() {
        Date dateAndTimeReturningFlightLeaves = getDateAndTimeThisFlightLeaves()
        return (dateAndTimeReturningFlightLeaves.getTime() - flow.dateAndTimeDepartingFlightArrives.getTime() <= MILLISECONDS_IN_FOUR_HOURS)
    }

    public boolean isReturnFlightInLessThanFourHoursFromDeparture() {
        Date dateAndTimeReturningFlightLeaves = getDateAndTimeThisFlightLeaves()
        return (dateAndTimeReturningFlightLeaves.getTime() - flow.dateAndTimeDepartingFlightArrives.getTime() < MILLISECONDS_IN_FOUR_HOURS)
    }

    public Date getDateAndTimeThisFlightLeaves() {
        def rowDate = getRadioButton().getAttribute('value').split(",")[0]
        def times = rowElement.findElements(By.className("time"))
        def meridians = rowElement.findElements(By.className("indicator"))
        def inboundDepartureTime = times[0].getText()
        String inboundDepartureMeridian = meridians[0].getText()
        return formatDate(rowDate, inboundDepartureTime, inboundDepartureMeridian)
    }

    private Date getDateAndTimeThisFlightArrives() {
        def rowDate = getRadioButton().getAttribute('value').split(",")[0]
        def times = rowElement.findElements(By.className("time"))
        def meridians = rowElement.findElements(By.className("indicator"))
        def inboundArrivalTime = times[1].getText()
        String inboundArrivalMeridian = meridians[1].getText()
        return formatDate(rowDate, inboundArrivalTime, inboundArrivalMeridian)
    }

    public String getFlightOutBoundPrice(Boolean isSeniorFare = false){
        if(isSeniorFare){
            return selectFlightsPageData.outboundSeniorFlightPrice = getFlightPrice()
        } else {
            return selectFlightsPageData.outboundFlightPrice = getFlightPrice()
        }
    }

    public String getFlightInBoundPrice(Boolean isSeniorFare = false){
        if(isSeniorFare){
            return selectFlightsPageData.inboundSeniorFlightPrice = getFlightPrice()
        } else {
            return selectFlightsPageData.inboundFlightPrice = getFlightPrice()
        }
    }

    public void saveFlightRowInformation() {
        if (rowDirection == "Out") {
            selectFlightsPageData.outboundRow = rowDirection + rowNumber
        } else {
            selectFlightsPageData.inboundRow = rowDirection + rowNumber
        }
    }

    private int getFlightPrice() {
        String flightPriceWithDollarSymbol = getRadioButton().getAttribute("title").split(' ')[3]
        String flightPrice = flightPriceWithDollarSymbol.replace('$', '');
        flightPrice.toInteger()
    }

    private WebElement getRadioButton() {
        return rowElement.findElement(By.id(radioButtonId))
    }

    public boolean isWebOnly() {
        WebElement element = rowElement.findElement(By.id(radioButtonId + 'Container')).findElement(By.xpath(WEB_ONLY_LABEL_XPATH))
        return (element!= null && element.isDisplayed())
    }

    private Date formatDate(String date, String time, String meridian){
        String dateAsString =  new Date().format(date) + " " + time.trim() + " " + meridian
        return Date.parse("yyyy M d h:m a", dateAsString)
    }

    public WebElement returnCurrentFlightDetailLink(){
        WebElement flightDetails = this.rowElement.findElement(FLIGHT_DETAIL_LINK)
        selectFlightsPageData.outboundRouting = flightDetails.text
        return flightDetails
    }

    private boolean isCorrectStopCitiesList(String routing, String[] stopsCities){
        for (String stop : stopsCities) {
            if (routing.equals("Nonstop") || stop.equals("")){
                return true
            }else {
                WebElement stopCitiesContainer = this.rowElement.findElement(STOP_CITIES_CONTAINER)
                if (!stopCitiesContainer.getAttribute("innerHTML").contains(stop)) {
                    return false
                }
            }
        }
        return true
    }

    private boolean isCorrectStopChangePlane(String routing, String stopChangePlaneCity){
        if (routing.equals("Nonstop") ||
            stopChangePlaneCity == null || !stopChangePlaneCity.trim().equals("")){
            return true
        }else {
            WebElement stopChangePlaneCityContainer = this.rowElement.findElement(FLIGHT_CHANCE_PLANE_CITY)
            String routingText = stopChangePlaneCityContainer.getText()
            String connectingCity = routingText.substring(routingText.length()-3, routingText.length())
            return stopChangePlaneCityContainer.getText().contains(CHANGE_PLANE_TEXT + connectingCity)
        }
    }

    def getFlightNumber() {
        String getFlightNumber = rowElement.findElement(By.className("bugLinkText")).text
        boolean isFlightRowValid = hasAvailableFlightForFareClass()
        if (isFlightRowValid) {
            if (isFlightRowValid && rowDirection == "Out") {
                selectFlightsPageData.departingFlight_number = getFlightNumber
            } else if (isFlightRowValid && rowDirection == "In") {
                selectFlightsPageData.returningFlightNumber = getFlightNumber
            }
        }
    }

    def getDepartureAndArrivalTimes() {
        final int DEPARTURE = 0
        final int ARRIVAL = 1
        boolean isFlightRowValid = hasAvailableFlightForFareClass()
        if (isFlightRowValid) {
            if (isFlightRowValid && rowDirection == "Out") {
                selectFlightsPageData.outboundDepartureTime = rowElement.findElements(By.className("time"))[DEPARTURE].text
                selectFlightsPageData.outboundArrivalTime = rowElement.findElements(By.className("time"))[ARRIVAL].text
            } else if (isFlightRowValid && rowDirection == "In") {
                selectFlightsPageData.inboundDepartureTime = rowElement.findElements(By.className("time"))[DEPARTURE].text
                selectFlightsPageData.inboundArrivalTime = rowElement.findElements(By.className("time"))[ARRIVAL].text
            }
        }
    }

    def getTravelTime() {
        boolean isFlightRowValid = hasAvailableFlightForFareClass()
        if (isFlightRowValid) {
            if (isFlightRowValid && rowDirection == "Out") {
                selectFlightsPageData.outboundTravelTime = rowElement.findElement(By.className("duration")).text

            } else if (isFlightRowValid && rowDirection == "In") {
                selectFlightsPageData.inboundTravelTime = rowElement.findElement(By.className("duration")).text
            }
        }
    }

    def getSeniorPrice() {
       selectFlightsPageData.outboundSeniorPrice = rowElement.findElements(By.cssSelector(".price_column"))[2].text
    }

    def verifyDiscountedFare() {
       List<WebElement> discountedFare = this.rowElement.findElements(PRODUCT_PRICE)
       List<WebElement> originalPrice = this.rowElement.findElements(ORIGINAL_PRICE)
       discountedFare.size().shouldBe 3, "All the discounted fare were not present in the bug page"
       originalPrice.size().shouldBe 3, "All the original price were not present in the bug page"
    }
}
