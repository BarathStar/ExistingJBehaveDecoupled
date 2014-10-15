package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

public class SwabizBookTravelPage extends BasePage {

    private static final By SITE_MAP_LINK = By.cssSelector('a[id="site-map"]')

    private static final By POINTS_LINK = By.id("pointsLink")
    private static final By DOLLARS_LINK = By.id("dollarsLink")

    private static final By SENIOR_PASSENGER_COUNT = By.id("seniorPassengerCount")
    private static final By PROMO_CODE = By.id("promoCode")

    public SwabizBookTravelPage(WebDriverProvider driverProvider) {
        super(driverProvider, '');
    }

    def clickOnSiteMapLink() {
        waitForElement(SITE_MAP_LINK).click()
    }

    def clickOnShowFaresInPoints() {
        waitFor({isElementPresent(POINTS_LINK)})
        waitForElement(POINTS_LINK).click()
    }

    def clickOnShowFaresInDollars() {
        waitForElement(DOLLARS_LINK).click()
    }

    def seniorPassengerCountIsEnabled() {
        waitForElement(SENIOR_PASSENGER_COUNT).isEnabled().shouldBe true, "The Senior Passenger Count field is not enabled"
    }

    def seniorPassengerCountIsDisabled() {
        waitForElement(SENIOR_PASSENGER_COUNT).isEnabled().shouldBe false, "The Senior Passenger Count field is not disabled"
    }

    def promoCodeIsEnabled() {
        waitForElement(PROMO_CODE).isEnabled().shouldBe true, "The Promo Code field is not enabled"
    }

    def promoCodeIsDisabled() {
        waitForElement(PROMO_CODE).isEnabled().shouldBe false, "The Promo Code field is not disabled"
    }

}