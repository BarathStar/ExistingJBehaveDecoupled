package steps.thenSteps

import pages.HowToEarnPage
import org.jbehave.core.annotations.Then

class HowToEarnThenStep {

    HowToEarnPage howToEarnPage

    @Then("I see the A List breadcrumb link on the Tiers & More page")
    def verifyAListBreadcrumbLink() {
        howToEarnPage.verifyAListBreadcrumbLink()
    }

    @Then("I see the A-List Preferred breadcrumb link on the Tiers & More page")
    def verifyAListPreferredBredcrumbLink() {
        howToEarnPage.verifyAListPreferredBredcrumbLink()
    }

    @Then('I see the Companion Pass breadcrumb link on the Tiers & More page')
    def verifyCompanionPassBredcrumbLink() {
        howToEarnPage.verifyCompanionPassBredcrumbLink()
    }

    @Then('I see A-List card is selected')
    def verifyAListBreadcrumbLinkIsSelected() {
        howToEarnPage.verifyAListBreadcrumbLinkIsSelected()
    }

    @Then('I see A-List Preferred card is selected')
    def verifyAListPreferredBreadcrumbLinkIsSelected() {
        howToEarnPage.verifyAListPreferredBreadcrumbLinkIsSelected()
    }

    @Then('I see Companion Pass card is selected')
    def verifyCompanionPassBreadcrumbLinkIsSelected() {
        howToEarnPage.verifyCompanionPassBreadcrumbLinkIsSelected()
    }

    @Then('I see the A-List Status title')
    def verifyTitleIsAListStatus() {
        howToEarnPage.verifyTitleIsAListStatus()
    }

    @Then('I see the A-List Program Rules subtitle')
    def verifySubTitleIsAListProgram() {
        howToEarnPage.verifySubTitleIsAListProgram()
    }

    @Then('I see the A-List Preferred Status title')
    def verifyTitleIsAListPreferredStatus() {
        howToEarnPage.verifyTitleIsAListPreferredStatus()
    }

    @Then('I see the Companion Pass Status title')
    def verifyTitleIsCompanionPassStatus() {
        howToEarnPage.verifyTitleIsCompanionPassStatus()
    }

    @Then('I see the A-List Preferred Program Rules subtitle')
    def verifySubTitleIsAListPreferredProgram() {
        howToEarnPage.verifySubTitleIsAListPreferredProgram()
    }

    @Then('I see the Companion Pass Program Rules subtitle')
    def verifySubTitleIsCompanionPassProgramRules() {
        howToEarnPage.verifySubTitleIsCompanionPassProgramRules()
    }

    @Then('I see the Tiers More Alist Preferred page')
    def verifyTiersMoreAlistPreferredPage() {
        howToEarnPage.verifyPage()
        howToEarnPage.verifyPageTitle('Tiers & More')
        howToEarnPage.verifyIamOnTheCorrectPage('/rapidrewards/tiers-more-alist-preferred')
    }

    @Then('I see the Tiers More Companion Pass page')
    def verifyTiersMoreCompanionPassPage() {
        howToEarnPage.verifyPage()
        howToEarnPage.verifyPageTitle('Tiers & More')
        howToEarnPage.verifyIamOnTheCorrectPage('/rapidrewards/tiers-more-companion-pass')
    }
}
