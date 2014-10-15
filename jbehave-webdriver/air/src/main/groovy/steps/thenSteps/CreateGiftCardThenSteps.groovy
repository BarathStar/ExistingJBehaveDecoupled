package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.elements.FlyOut
import pages.CreateGiftCardPage
import org.jbehave.core.annotations.Aliases

class CreateGiftCardThenSteps {

    CreateGiftCardPage createGiftCardPage

    @Then("I see opened the up to 12 hours flyOut on the Create a Card Page")
    def verifyUpTo12FlyOut() {
        FlyOut flyout = createGiftCardPage.openUpTo12HoursFlyOut()
        flyout.getTitle().shouldBe "Up to 12 hours", "The flyOut title should be 'Up to 12 hours'"
        flyout.getContentText().shouldStartWith "Due to processing, the southwestgiftcard recipient’ s e-mail could be generated up to 12 hours after the order is placed", "The flyout content text is not the expected one"
    }

    @Then("I see opened the 4-7 business days flyOut on the Create a Card Page")
    def verifyBusinessDaysFlyOut() {
        FlyOut flyout = createGiftCardPage.openBusinessDaysFlyOut()
        flyout.getTitle().shouldBe "4-7 business days", "The flyOut title should be '4-7 business days'"
        flyout.getContentText().shouldStartWith "USPS orders will typically arrive within 4-7 business days.", "The flyout content text is not the expected one"
    }

    @Then("I see opened the standard overnight flyOut on the Create a Card Page")
    def verifyStandardOvernightFlyOut() {
        FlyOut flyout = createGiftCardPage.openStandardOvernightFlyOut()
        flyout.getTitle().shouldBe "Standard Overnight", "The flyOut title should be 'Standard Overnight'"
        flyout.getContentText().shouldStartWith "Federal Express orders placed before 12:00pm CST will be delivered the next business day. No Saturday delivery.", "The flyout content text is not the expected one"
    }

    @Then("I see the Oops messages about the mandatory fields for the email delivery method")
    void verifyMandatoryOopsForEmailDeliveryMethod() {
        createGiftCardPage.verifyMandatoryFieldsForEmailDelivery()
    }

    @Then("I see the Oops messages about the mandatory fields for the postal delivery method")
	@Aliases(values = ["I see the Oops messages about the mandatory fields for the usps delivery method",
		"I see the Oops messages about the mandatory fields for the federal express delivery method"])
    void verifyMandatoryOopsForUspsDeliveryMethod() {
        createGiftCardPage.verifyMandatoryFieldsForUspsOrFedexDelivery()
    }
}
