package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When

import com.swacorp.dotcom.webscenarios.air.RRUser
import fixture.stubs.DynaStubsIntegration
import static org.junit.Assert.fail
import pages.*
import state.Flow

class GiftCardLandingSteps {

    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    GiftCardHomePage giftCardHomePage
    GiftCardSecurityPage giftCardSecurityPage
    GiftCardFAQsPage giftCardFAQsPage
    GiftCardTermAndConditionsPage giftCardTermAndConditionsPage
    CreateGiftCardPage createGiftCardPage
    Flow flow

    @Given("I am on the Gift Card Landing page and the services are down")
    def openGiftCardLandingPageServicesDown() {
        if (DynaStubsIntegration.useDynaStubs()) {
            flow.purchaseGiftCardData = dynaStubsIntegration.generateGiftCardPurchaseWithServiceDown()
        }
        giftCardHomePage.open()
    }

    @Given("I am on the Gift Card Landing page")
    def openGiftCardLandingPage() {
        giftCardHomePage.open()
    }

    @When("I click on the \$link link on the giftcard landing page")
    def selectGiftCardLinks(String link) {
        switch (link) {
            case "security":
                giftCardHomePage.clickOnSecurityLink()
                giftCardSecurityPage.verifyPage()
                break
            case "FAQs":
                giftCardHomePage.clickOnFAQsLink()
                giftCardFAQsPage.verifyPage()
                break
            case "term and conditions":
                giftCardHomePage.clickOnTermsAndConditionsLink()
                giftCardTermAndConditionsPage.verifyPage()
                break
            default:
                fail("Message did not return ${link}")
        }
    }

    @When("I click on the continue button on the Gift Card Landing page")
    def clickContinue() {
        giftCardHomePage.verifyPage()
        giftCardHomePage.clickOnContinueButton()
        createGiftCardPage.verifyPage()
    }
}
