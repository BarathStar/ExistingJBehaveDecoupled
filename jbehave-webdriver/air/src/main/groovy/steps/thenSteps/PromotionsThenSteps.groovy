package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.PromotionsPage

class PromotionsThenSteps {

    PromotionsPage promotionsPage

    @Then("I should not see a message indicating that Promotional Certificates can not be retrieved on Promotions page")
    def verifyMessageAboutPromotionalCertificatesIsNotPresent() {
        promotionsPage.verifyMessageAboutPromotionalCertificatesIsNotPresent()
    }

    @Then("I see a message indicating that Promotions can not be retrieved on Promotions page")
    def verifyMessageAboutPromotionsIsPresent() {
        promotionsPage.verifyMessageAboutPromotionsIsPresent()
    }

    @Then("I see a message indicating that Promotional Certificates can not be retrieved on Promotions page")
    def verifyMessageAboutPromotionalCertificatesIsPresent() {
        promotionsPage.verifyMessageAboutPromotionalCertificatesIsPresent()
    }

    @Then("I see Rapid Rewards Promotions page")
    def verifyRapidRewardsPromotionsPage() {
        promotionsPage.verifyCurrentPageLocation()
    }

    @Then("I should not see a message indicating that Promotions can not be retrieved on Promotions page")
    def verifyMessageAboutPromotionsIsNotPresent() {
        promotionsPage.verifyMessageAboutPromotionsIsNotPresent()
    }
}
