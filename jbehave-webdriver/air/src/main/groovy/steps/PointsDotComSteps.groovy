package steps

import pages.MyRapidRewardsPage
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import pages.RapidRewardsOverviewPage
import pages.PointsDotcomBuyGiftTransferPage
import org.jbehave.core.annotations.Given
import pages.elements.GlobalNavigationHeader
import steps.conditional.ToggleGlobalNav

class PointsDotComSteps {
    MyRapidRewardsPage myRapidRewardsPage
    RapidRewardsOverviewPage rapidRewardsOverviewPage
    PointsDotcomBuyGiftTransferPage pointsDotcomBuyGiftTransferPage
    GlobalNavigationHeader globalNavigationHeader

    @When("I click on Buy, Gift & Transfer Points")
    def clickBuyAndTransferPoints() {
        myRapidRewardsPage.clickBuyAndTransferPoints()
    }

    @When("I go to the Rapid Rewards Overview Page")
    def clickRRGlobalNavigationHeader() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openRapidRewardsSubMenu()
            globalNavigationHeader.clickAboutRRLink()
        } else {
            myRapidRewardsPage.clickRRGlobalNavigationHeader()
        }
    }

    @When("I click on About tab")
    def clickAboutTab() {
        rapidRewardsOverviewPage.clickAboutTab()
    }

    @When("I select Buy, Gift & Transfer Points from Quick Links")
    def selectBuyAndTransferPointsFromQuickLinks() {
        myRapidRewardsPage.selectBuyAndTransferPointsFromQuickLinks()
    }

    @Then("I should see the About Rapid Rewards page")
    def verifyAboutRapidRewardsPage() {
        rapidRewardsOverviewPage.verifyBuyAndTransferPointsSection()
    }

    @When("I click on the Buy button")
    def clickBuyButton() {
        pointsDotcomBuyGiftTransferPage.clickBuyButton()
    }

    @When("I click on the Transfer button")
    def clickTransferButton() {
        pointsDotcomBuyGiftTransferPage.clickTransferButton()
    }

    @When("I click on the Gift button")
    def clickGiftButton() {
        pointsDotcomBuyGiftTransferPage.clickGiftButton()
    }

    @When("I click on the Claim button")
    def clickClaimButton() {
        pointsDotcomBuyGiftTransferPage.clickClaimButton()
    }

    @When("I click on the Buy, Gift & Transfer button")
    def clickPointsCenterButton() {
        rapidRewardsOverviewPage.clickPointsCenterButton()
    }

    @Then("I should see the points.com \$product page in the iframe")
    def verifyPointsDotcomPage(String product) {
        pointsDotcomBuyGiftTransferPage.verifyPointsDotcomIframe(product)
    }

    @Then("I should see the modal urging me to upgrade my membership to Rapid Rewards")
    def verifyUpgradeToRapidRewardsModal() {
        pointsDotcomBuyGiftTransferPage.verifyUpgradeToRapidRewardsModal()
    }

    @Given("I navigate directly to the points.com Claim page")
    def navigateDirectlyToThePointsDotcomClaimPage() {
        pointsDotcomBuyGiftTransferPage.open()
    }
}
