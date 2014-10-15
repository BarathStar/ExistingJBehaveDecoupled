package pages

import com.github.tanob.groobe.GrooBe
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AirTranSelectFlightsPage extends BasePage {

  public AirTranSelectFlightsPage(WebDriverProvider driverProvider) {
    super(driverProvider);
    GrooBe.activate()
  }

  def isDisplayed() {
    getCurrentUrl().contains("tickets.airtran.com/Select")
  }

  def verifyTripInformationHas(String origin, String destination) {
    getTripHeaders()[0].getText().shouldContain origin + " to " + destination
  }

  def verifyTripInformationHas(String origin, String destination, String returnStation) {
    verifyTripInformationHas(origin, destination)
    getTripHeaders()[1].getText().shouldContain destination + " to " + returnStation
  }

  private List getTripHeaders() {
    return waitForElements(By.id("flightmatrix").className("selecthead"))
  }
    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }
}
