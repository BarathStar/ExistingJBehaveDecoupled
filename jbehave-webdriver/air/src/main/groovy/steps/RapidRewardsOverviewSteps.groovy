package steps

import pages.RapidRewardsOverviewPage
import org.jbehave.core.annotations.When

class RapidRewardsOverviewSteps {

    RapidRewardsOverviewPage rapidRewardsOverviewPage

    @When("I access How to Earn page")
    def clickOnTheHowToEarnLink() {
        rapidRewardsOverviewPage.clickOnTheHowToEarnLink()
    }

    @When("I select A-List on the Earn When You Fly section")
    def clickOnTheAListLink() {
        rapidRewardsOverviewPage.clickOnTheAListLink()
    }

    @When("I select A-List Preferred on the Earn When You Fly section")
    def clickOnTheAListPreferredLink() {
        rapidRewardsOverviewPage.clickOnTheAListPreferredLink()
    }

    @When("I access Earn With Partners page")
    def clickOnTheEarnWithPartnersLink() {
        rapidRewardsOverviewPage.clickOnTheEarnWithPartnersLink()
    }

    @When("I access Promotions page")
    def clickOnThePromotionsLink() {
        rapidRewardsOverviewPage.clickOnThePromotionsLink()
    }

}
