package steps

import org.openqa.selenium.By
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import pages.EarlyBirdPurchasePage
import pages.EarlyBirdFAQsPage

class EarlyBirdFAQSteps {

    private static final TOPIC_SELECTOR = By.id("topic_selector")
    private static final GO_BUTTON = By.className("submit-button")

    EarlyBirdPurchasePage earlyBirdPurchasePage
    EarlyBirdFAQsPage earlyBirdFAQsPage

    @When("I click the learn more about earlybird checkin link")
    def clickEarlyBirdLink() {
        earlyBirdPurchasePage.clickEarlyBirdLink()
    }

    @Then("I will see the earlybird faqs page")
    void verifyEarlyBirdFaqPage() {
       earlyBirdFAQsPage.verifyFaqText("EarlyBird Check-In")
    }

    @When("I change the topic to advertised fare")
    def changeTopicToAdvertisedFare() {
        earlyBirdFAQsPage.selectFromDropDownByIndex(TOPIC_SELECTOR, 1)
    }

    @When("I click the Go button")
    void clickGoButton() {
        earlyBirdFAQsPage.clickGoButton()
    }

    @Then("The faq topic displays as advertised fare")
    def verifyAdvertisedFare() {
        earlyBirdFAQsPage.verifyFaqText("Advertised Fare")
    }

    @When("I change the topic to earlybird checkin")
    def changeTopicToEarlyBirdCheckin() {
        earlyBirdFAQsPage.selectFromDropDownByIndex(TOPIC_SELECTOR, 9)
    }

}