package steps.conditional

import org.jbehave.core.annotations.AfterScenario
import org.jbehave.core.annotations.AfterStory
import org.jbehave.core.annotations.BeforeStory
import org.openqa.selenium.WebDriverException
import org.jbehave.core.annotations.BeforeScenario
import org.jbehave.web.selenium.WebDriverProvider
import state.SWAContextView

import java.util.concurrent.TimeUnit

class ClearSessionEachScenario {

  private WebDriverProvider webDriverProvider

  public ClearSessionEachScenario(WebDriverProvider driverProvider) {
    webDriverProvider = driverProvider
  }

    @BeforeStory(uponGivenStory = true)
    def emptyCartNotReally() {
        try {
            def manage = webDriverProvider.get().manage()
            String implicitWait = System.getProperty("implicit_wait");
            if (implicitWait == null) {
                implicitWait = "0";
            }
            manage.timeouts().implicitlyWait(Integer.parseInt(implicitWait), TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            System.out.println("Webriver exception caught in emptyCart() : " + e.getMessage())
        } catch (Exception e) {
            System.out.println("Unknown exception caught in emptyCart() : " + e.getMessage())
        }
    }

    @AfterStory(uponGivenStory = false)
    def emptyCart() {
        try {
            def manage = webDriverProvider.get().manage()
            String implicitWait = System.getProperty("implicit_wait");
            if (implicitWait == null) {
                implicitWait = "0";
            }
            manage.timeouts().implicitlyWait(Integer.parseInt(implicitWait), TimeUnit.SECONDS);
            manage.deleteAllCookies();
        } catch (WebDriverException e) {
            System.out.println("Webriver exception caught in emptyCart() : " + e.getMessage())
        } catch (Exception e) {
            System.out.println("Unknown exception caught in emptyCart() : " + e.getMessage())
        }

    }


}


