package pages

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider

class AlreadyPurchasedTripPage extends BasePage {

    @Override
    String getRelativePath() {
        return "reservations/already-purchased-trip.html"
    }

    public AlreadyPurchasedTripPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    def verifyPage() {
        checkSomethingServed()

        def oopsMessages = ['You have already completed a purchase',
                            'Please choose an option from the following',
                            'View your confirmation again',
                            'Start a new flight search',
                            'Start a new hotel search',
                            'Start a new car search']
        verifyOopsMessages(oopsMessages)
    }
}
