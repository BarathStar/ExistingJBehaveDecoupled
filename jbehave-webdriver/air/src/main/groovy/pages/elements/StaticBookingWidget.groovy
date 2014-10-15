package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage

class StaticBookingWidget extends BasePage {

    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    private static final By OUTBOUND_DATE = By.name("outboundDateString")
    private static final By RETURN_DATE = By.name("returnDateString")
    private static final String DATE_FORMAT = "MM/dd/yyyy"
    private static final By SEARCH_BUTTON = By.name("book_now")
    private static final By ONE_WAY_RADIO_BUTTON = By.xpath(".//*[@id='sw_content']/div[1]/div[2]/div/form/div[1]/div[1]/div/div[2]/label/input")

    public StaticBookingWidget(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    def fillBookingOutboundDate(Date tomorrow) {
        fillIn(OUTBOUND_DATE, tomorrow.format(DATE_FORMAT))
    }

    def fillBookingReturnDate(Date tomorrow) {
        fillIn(RETURN_DATE, tomorrow.format(DATE_FORMAT))
    }

    def clickOnSearchButtonAndValidatePage() {
        String pageTitle = getTitle()
        clickOnSearchButton()
        waitForPageTitleToChangeOrOops(pageTitle)
        selectFlightsPage.verifyCurrentPageLocation()
    }

    def clickOnSearchButton() {
        waitForElement(SEARCH_BUTTON).click()
    }

    def verifyOneWayRadioButtonIsChecked() {
        waitForElement(ONE_WAY_RADIO_BUTTON).getAttribute("checked").shouldBe "true", "The one way radio button should be checked."
    }

    def verifyReturnDateIsDisabled() {
        waitForElement(RETURN_DATE).isEnabled().shouldBe false, 'The return station input field should be disabled and not available for input.'
    }

    def verifyWatermarkOnOutboundDate() {
        waitForElement(OUTBOUND_DATE).getAttribute("value").shouldBe "Depart", "The outbound date should contain a watermark."
    }

    def verifySelectFlightsPage(){
        checkSomethingServed()
    }

}
