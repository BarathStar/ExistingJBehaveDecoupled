package fixture

import fixture.data.Account
import fixture.pages.Browser
import fixture.pages.CreateAccountPage

class AccountCreator {

	Browser browser

	def createAccount(username) {
		def usedUsername = username + (System.currentTimeMillis() % 100000)

		logout()

		def createAccountPage = browser.goTo(CreateAccountPage)
		def accountCreatedPage = createAccountPage.createAccount(usedUsername)

		return new Account(username: usedUsername, password: "test123", rrNumber: accountCreatedPage.accountNumber)
	}

	private def logout() {
		browser.clearCookies()
	}
}
