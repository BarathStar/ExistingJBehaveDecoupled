package pages

import org.jbehave.web.selenium.WebDriverProvider

class BaggageAndOptionalFees extends BasePage {

    private static final String PATH = "/html/customer-service/travel-fees.html?clk=HOME-BOOKING-WIDGET-BAGGAGE-FEES"

    BaggageAndOptionalFees(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    @Override
    def open() {
        super.open()
        verifyPage()
    }

    def verifyBaggageAndOptionalFees(){
        shouldBeInPage(PATH)
    }
}