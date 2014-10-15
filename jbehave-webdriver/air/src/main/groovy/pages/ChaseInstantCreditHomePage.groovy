package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.WebElement

public class ChaseInstantCreditHomePage extends BasePage {

	private static final String CHASE_INSTANT_CREDIT_URL_FROM_PURCHASE = 'https://creditcardsccstage.f9dev.com/a1/southwest/icpp4'
	private static final String CHASE_INSTANT_CREDIT_URL_FROM_PRICE = 'https://creditcardsccstage.f9dev.com/a1/southwest/icpp4'

	public ChaseInstantCreditHomePage(WebDriverProvider driverProvider) {
		super(driverProvider)
	}

	def verifyChaseInstantCreditHomePageFromPurchaseUrl() {
		shouldBeInPage(CHASE_INSTANT_CREDIT_URL_FROM_PURCHASE)
	}

	def verifyChaseInstantCreditHomePageFromPriceUrl() {
		shouldBeInPage(CHASE_INSTANT_CREDIT_URL_FROM_PRICE)
	}
}
