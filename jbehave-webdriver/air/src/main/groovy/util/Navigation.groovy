package util

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

class Navigation {

  private WebDriverProvider webDriverProvider

  public Navigation(WebDriverProvider driverProvider) {
    webDriverProvider = driverProvider
  }

  def back() {
      webDriverProvider.get().navigate().back();
  }

}