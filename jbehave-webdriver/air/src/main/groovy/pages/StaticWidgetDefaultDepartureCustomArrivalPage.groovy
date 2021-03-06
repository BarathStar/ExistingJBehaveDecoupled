package pages

import org.jbehave.web.selenium.WebDriverProvider

class StaticWidgetDefaultDepartureCustomArrivalPage extends BasePage {


    private static final String RELATIVE_PATH = "air_ctd_08.html"
    private static final String PATH = "/html/app_test/booking_widgets/"

    def StaticWidgetDefaultDepartureCustomArrivalPage(WebDriverProvider driverProvider) {
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
