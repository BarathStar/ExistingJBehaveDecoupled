package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import fixtures.air.ReservationSpecification
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.SortablePage
import state.Flow

public class DriverInfo extends SortablePage {

    public static final By CAR_DRIVER_FIRST_NAME = By.id("carDriverFirstName")
    public static final By CAR_DRIVER_LAST_NAME = By.id("carDriverLastName")

    Data data
    Flow flow

    public DriverInfo(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.DRIVER_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {

        if (!flow.isCarReservationPresent || !isElementDisplayed(CAR_DRIVER_FIRST_NAME)) {
            return
        }
        waitForElement(CAR_DRIVER_FIRST_NAME).sendKeys DELETE_EXISTING + data.firstName
        waitForElement(CAR_DRIVER_LAST_NAME).sendKeys DELETE_EXISTING + data.lastName
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }
}
