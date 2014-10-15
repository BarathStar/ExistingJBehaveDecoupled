package pages.elements

import fixtures.air.ReservationSpecification
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.SortablePage
import state.Flow
import static util.TripManagement.DEFAULT_TRIP
import static util.TripManagement.NO_TRIP

/**
 * Adds a trip or default name on purchase page
 *
 */
class TripNameForm extends SortablePage {

    private By tripName = By.id("tripName")
    private By nameThisTrip = By.id("nameThisTrip")

    Flow flow

    public TripNameForm(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.TRIP_NAME_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {
        if(flow.tripName) {
            switch(flow.tripName) {
                case DEFAULT_TRIP: createDefaultTrip()
                                   break
                case NO_TRIP: createNoTrip()
                                   break
                default: createTripWithName()
                                   break
            }
        }
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }

    void createDefaultTrip() {
        WebElement nameThisTrip = waitForElement(nameThisTrip);
        if(!nameThisTrip?.isSelected()){
            nameThisTrip.click()
        }
    }

    void createNoTrip() {
        WebElement nameThisTrip = waitForElement(nameThisTrip);
        if(nameThisTrip?.isSelected()) {
            nameThisTrip.click()
        }
    }

    void createTripWithName() {
        WebElement nameThisTrip = waitForElement(nameThisTrip);
        if(!nameThisTrip?.isSelected()) {
            nameThisTrip.click()
        }

        WebElement tripName = waitForElement(tripName);
        if(tripName?.isDisplayed()) {
            tripName.sendKeys DELETE_EXISTING + flow.tripName
        }
    }

}