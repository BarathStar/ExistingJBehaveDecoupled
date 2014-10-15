package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.ItineraryData
import util.SelectFlightsPageData


class RepricePage extends BasePage {

    SelectFlightsPageData selectFlightsPageData
    ItineraryData itineraryData

    private static final By OUTBOUND_PRICE = By.xpath("//tr[@class='tableRowEven']/td[@class='priceDataText'][5]")
    private static final By INBOUND_PRICE =  By.xpath("//tr[@class='tableRowOdd']/td[@class='priceDataText'][5]")

    public RepricePage(WebDriverProvider driverProvider, SelectFlightsPageData sfpd) {
        super(driverProvider);
        this.selectFlightsPageData = sfpd
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    def isRepricingPage() {
        def errorWrapper = getOopsElement()
        if(errorWrapper) {
            return errorWrapper.text.contains("We are unable to secure the price for the flight you selected.")
        }
        return false
    }

     def clickContinue() {
         String title = getTitle()
         checkNoOops()
         clickOnOneOfThese(By.name("priceItinerary"))  //TODO Verify Page Load - this may need to move to steps class(es)
         waitForPageTitleToChangeOrOops(title)
    }

    public init() {
        WebElement outboundPriceContainer = waitForElement(OUTBOUND_PRICE)
        BigDecimal outboundPrice = outboundPriceContainer.text.replace('$', '').toBigDecimal()
        selectFlightsPageData.outboundFlightPrice = outboundPrice
        if (itineraryData.isRoundTripOrOpenJaw()) {
            WebElement inboundPriceContainer =  findElement(INBOUND_PRICE)
            BigDecimal inboundPrice = inboundPriceContainer.text.replace('$','').split(" ")[0].toBigDecimal()
            selectFlightsPageData.inboundFlightPrice = inboundPrice
        }
    }

}
