package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.GiftCardHomePage
import pages.GiftCardSecurityPage
import pages.GiftCardFAQsPage
import pages.GiftCardTermAndConditionsPage
import static org.junit.Assert.fail

class GiftCardHomeThenSteps {

    GiftCardHomePage giftCardHomePage
    GiftCardSecurityPage giftCardSecurityPage
    GiftCardFAQsPage giftCardFAQsPage
    GiftCardTermAndConditionsPage giftCardTermAndConditionsPage

    @Then("I see the links Security, FAQs and Terms and Conditions on the giftcard landing page")
    def verifyTheFooterLinksOnGiftCardPage() {
        giftCardHomePage.verifyLocationAndFooterLinks()
    }

    @Then ("I see the options of the breadcrumb on the giftcard landing page")
    def verifyTheBreadcrumOptionsByDefault(){
        giftCardHomePage.verifyPage()
        giftCardHomePage.verifyTheBreadcrumByDefault()
    }

    @Then ("I see three tabs and the Buy a soutwest giftcard tab selected by default on the giftcard landing page")
    def verifyTheTabsOptionsByDefault() {
        giftCardHomePage.verifyTheTabsByDefault()
    }

    @Then ("I see the Southwest Gift Cards home page")
    def verifyGiftCardHomePage(){
        giftCardHomePage.verifyOnSouthWestGiftCardHomePage()
    }

    @Then ("I see the \$link giftcard page")
    def verifyGiftCardLinksPages(String link) {
        switch (link) {
            case "security":
                giftCardSecurityPage.verifyTitleGiftCardSSecurityPage()
                giftCardSecurityPage.close()
                break
            case "FAQs":
                giftCardFAQsPage.verifyTitleGiftCardFAQsPage()
                giftCardFAQsPage.close()
                break
            case "term and conditions":
                giftCardTermAndConditionsPage.verifyTitleGiftCardTermAndConditionsPage()
                giftCardTermAndConditionsPage.close()
                break
            default:
                fail("Message did not return ${link}")
        }
        giftCardHomePage.switchToWindow("Southwest Gift Cards")
    }
}
