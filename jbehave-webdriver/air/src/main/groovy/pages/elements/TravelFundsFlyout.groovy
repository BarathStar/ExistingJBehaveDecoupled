package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class TravelFundsFlyout extends FlyOut {


    /** theContainer parameter refers to the flyOut's main container */
    private static final By EXHCNAGED_TICKET_INFO = By.cssSelector("p")
    private static final By FUNDS_APPLIED_INFO = By.className("highlighter")
    private static final By TRAVEL_FUNDS_REMAINING = By.cssSelector("td")

    TravelFundsFlyout(WebElement theContainer) {
        super(theContainer)
    }

    def String getFirstLastName() {
        container.findElements(EXHCNAGED_TICKET_INFO)[0].text
    }

    def String getTravelFund() {
        container.findElements(EXHCNAGED_TICKET_INFO)[1].text
    }

    def String getExpiration() {
        container.findElements(EXHCNAGED_TICKET_INFO)[2].text
    }

    def String getTravelFundsAppliedTitle() {
        container.findElements(FUNDS_APPLIED_INFO)[0].text
    }

    def BigDecimal getTravelFundsAppliedAmount() {
        container.findElements(FUNDS_APPLIED_INFO)[1].text.replace('\\s','').replace(',','').replace('$','').replace('(','').replace(')','').toBigDecimal()
    }

    def String getTravelFundsRemainingTitle() {
        container.findElements(TRAVEL_FUNDS_REMAINING)[4].text
    }

    def BigDecimal getTravelFundsRemainingAmount() {
        container.findElements(TRAVEL_FUNDS_REMAINING )[5].text.replace('\\s','').replace(',','').replace('$','').toBigDecimal()
    }

    def BigDecimal getTravelFundsAmount() {
        String travelFundsAmount = container.findElements(TRAVEL_FUNDS_REMAINING)[1].text
        travelFundsAmount.replace('\\s','').replace('$','').toBigDecimal()
    }
}
