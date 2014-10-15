package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class ExchangedTicketFlyout extends FlyOut {


    /** theContainer parameter refers to the flyOut's main container */
    private static final By EXHCNAGED_TICKET_INFO = By.cssSelector("p")
    private static final By FUNDS_APPLIED_INFO = By.className("highlighter")
    private static final By EXCHANGED_TICKET_BALANCE = By.cssSelector("td")

    ExchangedTicketFlyout(WebElement theContainer) {
        super(theContainer)
    }

    def String getTitle() {
        container.findElements(EXHCNAGED_TICKET_INFO)[0].text
    }

    def String getFlyoutSubtitle() {
        container.findElements(EXHCNAGED_TICKET_INFO)[1].text
    }

    def String getTravelFund() {
        container.findElements(EXHCNAGED_TICKET_INFO)[2].text
    }

    def String getExpiration(boolean isExchangedTicket = true) {
        int index = 2
        if (isExchangedTicket) {
             index = 3
        }
        container.findElements(EXHCNAGED_TICKET_INFO)[index].text
    }

    def BigDecimal getTotalAmount() {
        container.findElements(EXCHANGED_TICKET_BALANCE)[1].text.replace('\\s','').replace(',','').replace('$','').toBigDecimal()
    }

    def String getTravelFundsAppliedTitle() {
        container.findElements(FUNDS_APPLIED_INFO)[0].text
    }

    def BigDecimal getTravelFundsAppliedAmount() {
        container.findElements(FUNDS_APPLIED_INFO)[1].text.replace('\\s','').replace(',','').replace('$','').replace('(','').replace(')','').toBigDecimal()
    }

    def String getTravelFundsRemainingTitle() {
        container.findElements(EXCHANGED_TICKET_BALANCE)[4].text
    }

    def BigDecimal getTravelFundsRemainingAmount() {
        container.findElements(EXCHANGED_TICKET_BALANCE)[5].text.replace('\\s','').replace(',','').replace('$','').toBigDecimal()
    }

}
