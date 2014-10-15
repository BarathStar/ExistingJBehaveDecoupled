package fixture.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.OutputType
import org.apache.commons.io.FileUtils

class Browser {

	WebDriver driver

	String domain

	Browser(WebDriver driver, String domain) {
		this.driver = driver
		this.domain = domain
	}

	def goTo(Class page) {
		driver.get(domain + page.URL)
		return page.newInstance(driver)
	}

	def clearCookies() {
		driver.manage().deleteAllCookies()
	}

	def close() {
		driver.close()
	}

	def saveScreenshotTo(File file) {
		def screenshot = driver.getScreenshotAs(OutputType.FILE)
		FileUtils.copyFile(screenshot, file)
	}
}
