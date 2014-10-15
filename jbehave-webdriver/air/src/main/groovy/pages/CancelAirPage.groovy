package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CancelAirPage extends BasePage {

    private static final By AWARD_DETAILS_HEADING = By.xpath("//tr[@id='awardDetails']//td[@class='heading']")
    private static final By AWARD_DETAILS_COUNT = By.xpath("//tr[@id='awardDetails']//td[@class='cancelTravelFundsCenter']")
    private static final By AWARD_DETAILS_TEXT = By.xpath("//tr[@id='awardDetails']/td//label")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By FIRST_NAME = By.xpath("//input[@id='firstName']")
    private static final By LAST_NAME = By.xpath("//input[@id='lastName']")

    CancelAirReservationPage cancelAirReservationPage

    def CancelAirPage (WebDriverProvider webDriverProvider) {
        super(webDriverProvider, '/flight/cancel-reservation.html')
    }

    String getRelativePath() {
        return "flight/cancel-reservation.html"
    }

    def verifyTitleCancelAir(){
        cancelAirReservationPage.verifyTitleCancelAirReservation()
    }

    def verifyInformationForAwardsOnTravelFundsSection(String firstName, String lastName, String awardsCount) {
        waitForElement(AWARD_DETAILS_HEADING).getText().shouldBe "Award(s)", "The expected heading is not the right one"
        waitForElement(AWARD_DETAILS_COUNT).getText().shouldBe awardsCount, "The number of award coupons is not the expected: ${awardsCount}"
        String current = waitForElement(AWARD_DETAILS_TEXT).getText()
        String expected = "Award coupon(s) will be returned to ${firstName} ${lastName}'s Rapid Rewards account."
        current.toUpperCase().shouldContain expected.toUpperCase(), "The awards coupon details are not the expected"
    }

    def verifyFormIsEmpty() {
        waitForElement(CONFIRMATION_NUMBER).getAttribute('value').shouldBe "", "The confirmationNumber should be empty"
        waitForElement(FIRST_NAME).getAttribute('value').isEmpty().shouldBe true, "The firstName should be empty"
        waitForElement(LAST_NAME).getAttribute('value').isEmpty().shouldBe true, "The lastName should be empty"
    }
}