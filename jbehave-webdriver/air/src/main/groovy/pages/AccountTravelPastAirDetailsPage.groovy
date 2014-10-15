package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AccountTravelPastAirDetailsPage extends  BasePage{
    private static final String PATH = "/account/travel/past-air-details"
    private static final By PAST_TRIP_TICKETS = By.className("ticket")

    AccountTravelPastAirDetailsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def verifyNumberOfPresentTickets(int numberOfTickets) {
        waitForElements(PAST_TRIP_TICKETS).size().shouldBe numberOfTickets, "Number of expected tickets shoud be ${numberOfTickets}"
    }
}
