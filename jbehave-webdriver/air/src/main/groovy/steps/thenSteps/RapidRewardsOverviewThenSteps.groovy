package steps.thenSteps

import pages.RapidRewardsOverviewPage
import org.jbehave.core.annotations.Then

class RapidRewardsOverviewThenSteps {

    RapidRewardsOverviewPage rapidRewardsOverviewPage

    @Then("I see the Earn With Our Partners breadcrumb link on the How to Earn page")
    def verifyEarnWithOurPartnersBreadcrumb() {
        rapidRewardsOverviewPage.verifyEarnWithOurPartnersBreadcrumb()
    }

    @Then("I see the A-List link on the Earn When You Fly section is not selected")
    def verifyAListLinkIsNotSelected() {
        rapidRewardsOverviewPage.verifyAListLinkIsNotSelected()
    }

    @Then("I see the A-List Preferred link on the Earn When You Fly section is not selected")
    def verifyAListPreferredLinkIsNotSelected() {
        rapidRewardsOverviewPage.verifyAListPreferredLinkIsNotSelected()
    }

    @Then("I see Earn Points When You Fly is selected")
    def verifyEarnPointsWhenYouFlyLinkIsSelected() {
        rapidRewardsOverviewPage.verifyEarnPointsWhenYouFlyLinkIsSelected()
    }

    @Then("I see the Base link on the Earn When You Fly section is not selected")
    def verifyBaseLinkIsNotSelected() {
        rapidRewardsOverviewPage.verifyBaseLinkIsNotSelected()
    }

    @Then("I see the Earn Points When You Fly breadcrumb link on the Earn with Partners page")
    def verifyEarnPointsWhenYouFlyBreadcrumbIsPresent() {
        rapidRewardsOverviewPage.verifyEarnPointsWhenYouFlyBreadcrumbIsPresent()
    }

    @Then("I see Earn With Our Partners is selected")
    def verifyEarnWithOurPartnersLinkIsSelected() {
        rapidRewardsOverviewPage.verifyEarnWithOurPartnersLinkIsSelected()
    }

    @Then("I should not see the Base link")
    def verifyBaseLinkIsNotPresent() {
        rapidRewardsOverviewPage.verifyBaseLinkIsNotPresent()
    }

    @Then("I should not see the A-List link")
    def verifyAListLinkIsNotPresent() {
        rapidRewardsOverviewPage.verifyAListLinkIsNotPresent()
    }

    @Then("I should not see the A-List Preferred link")
    def verifyAListPreferredLinkIsNotPresent() {
        rapidRewardsOverviewPage.verifyAListPreferredLinkIsNotPresent()
    }

    @Then("I see the Promotions breadcrumb link on the Promotions page")
    def verifyPromotionsBreadcrumbIsPresent() {
        rapidRewardsOverviewPage.verifyPromotionsBreadcrumbIsPresent()
    }

    @Then("I see the Rapid Rewards Promotions section on the Promotions page")
    def verifyRapidRewardsPromotionsSection() {
        rapidRewardsOverviewPage.verifyRapidRewardsPromotionsSection()
    }
}
