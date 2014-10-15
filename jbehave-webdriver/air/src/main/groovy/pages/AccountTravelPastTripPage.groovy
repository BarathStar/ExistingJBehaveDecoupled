package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AccountTravelPastTripPage extends BasePage {

    private static final String PATH = "/account/travel/past-trips"
    private static final By FIRST_PAST_FLIGHT_AVAILABLE= By.cssSelector(".trip_information_trip_header a")

    AccountTravelPastTripPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def clickOnFirstPastTripAvailable(){
        waitForElement(FIRST_PAST_FLIGHT_AVAILABLE).click()
        verifyPage()
    }
}
