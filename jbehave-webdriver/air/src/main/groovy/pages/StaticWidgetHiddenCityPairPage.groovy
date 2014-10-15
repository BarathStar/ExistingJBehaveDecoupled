package pages

import org.jbehave.web.selenium.WebDriverProvider

class StaticWidgetHiddenCityPairPage extends BasePage {

    private static final String PATH = "/html/app_test/booking_widgets/"
    private static final String RELATIVE_PATH = "air_ctd_03.html"

    def StaticWidgetHiddenCityPairPage(WebDriverProvider driverProvider) {
          super(driverProvider, PATH + RELATIVE_PATH)
    }

    def openPage() {
        super.open()
        verifyPage()
    }

    String getRelativePath() {
        return PATH
    }

}
