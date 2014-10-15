package steps.thenSteps

import pages.ChaseInstantCreditHomePage
import org.jbehave.core.annotations.Then

class ChaseInstantCreditThenSteps {

	ChaseInstantCreditHomePage chaseInstantCreditPage

	@Then("I see Chase Instant Credit page from the purchase page")
	public void shouldSeeFromPurchaseChaseInstantCreditPage() {
		  chaseInstantCreditPage.verifyChaseInstantCreditHomePageFromPurchaseUrl()
	}

	@Then("I see Chase Instant Credit page from the price page")
	public void shouldSeeFromPriceChaseInstantCreditPage() {
		  chaseInstantCreditPage.verifyChaseInstantCreditHomePageFromPriceUrl()
	}
}
