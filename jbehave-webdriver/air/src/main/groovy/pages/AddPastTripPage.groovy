package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.joda.time.LocalDate
import org.openqa.selenium.By

class AddPastTripPage extends BasePage {

    private static final String PATH = "account/info/add-past-trip"
    private static final By CANCEL_LINK = By.id("cancel")
    private static final By FROM_WHERE_FIELD = By.id("originAirport0_displayed")
    private static final By TO_WHERE_FIELD = By.id("destinationAirport0_displayed")
    private static final By ROUND_TRIP_RADIUS_BUTTON = By.id("list0.roundTrip1")

    AddPastTripPage(WebDriverProvider driverProvider) {
        super(driverProvider,PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def verifyCancelLinkIsPresent() {
        verifyElementPresent("Cancel Link", CANCEL_LINK)
    }

    def verifyUpdateLinkIsPresent() {
        verifyElementPresent("Update Link", UPDATE_LINK)
    }

    def verifyFromWhere(String city) {
        waitForElement(FROM_WHERE_FIELD).getAttribute("value").shouldContain city, "The city should be ${city}"
    }

    def verifyToWhere(String city) {
        waitForElement(TO_WHERE_FIELD).getAttribute("value").shouldContain city, "The city should be ${city}"
    }


    def verifyUrlOriginAndDestinationParameters(String destination, String origin) {
        getCurrentUrl().shouldContain "origin=" + origin, "The origin should be ${origin}"
        getCurrentUrl().shouldContain "destination=" + destination, "The destination should be ${destination}"
    }

    def verifyUrlRoundTripParameter(boolean isRoundTrip){
        getCurrentUrl().shouldContain "roundTrip=" + isRoundTrip.toString(), "The roundTrip should be ${isRoundTrip.toString()}"
    }

    def verifyRoundTripRadiusButtonIsChecked(){
        waitForElement(ROUND_TRIP_RADIUS_BUTTON).getAttribute("checked").shouldContain "true", "The selected Trip Type is not Round Trip"
    }
}
