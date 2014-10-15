package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class SavedTripsPage extends BasePage {

    public SavedTripsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/travel/saved-trips');
    }

    String getRelativePath() {
        return "/account/travel/saved-trips";

    }

    def deleteAllSavedTrips() {
        WebElement deleteLink = findElement(By.className("delete"))

        while (deleteLink != null) {
            deleteLink.click()
            sleep(10)
            Alert alert = webDriver().switchTo().alert()
            alert.accept()
            deleteLink = waitForElement(By.className("delete"), false)
        }
    }

    void verifyNoSavedTrips() {
        waitForElement(By.className("trip_list")).text.shouldContain "Not quite ready to purchase your tickets?", "Ding! Ding! Trips on tap!!!!"

    }
}
