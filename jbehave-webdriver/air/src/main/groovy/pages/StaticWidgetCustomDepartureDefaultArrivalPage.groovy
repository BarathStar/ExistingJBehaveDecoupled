package pages

import org.jbehave.web.selenium.WebDriverProvider

class StaticWidgetCustomDepartureDefaultArrivalPage extends BasePage {


    private static final String RELATIVE_PATH = "air_ctd_07.html"
    private static final String PATH = "/html/app_test/booking_widgets/"

    def StaticWidgetCustomDepartureDefaultArrivalPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH + RELATIVE_PATH)
    }

    def openPage() {
        super.open()
        verifyPage()
    }

    String getRelativePath() {
        return RELATIVE_PATH
    }

}
