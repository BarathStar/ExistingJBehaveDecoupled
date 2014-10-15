package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

import state.Flow
import state.PassengerData
import static java.util.Calendar.YEAR
import pages.SortablePage
import fixtures.air.ReservationSpecification
import fixture.stubs.DynaStubsIntegration
import domain.Passenger
import util.ItineraryData

public class PassengerInfo extends SortablePage {

    private static final By HOTEL_GUEST_FIRST_NAME = By.id("hotelGuestFirstName")
    private static final By HOTEL_GUEST_LAST_NAME = By.id("hotelGuestLastName")
    private static final By WHO_IS_FLYING = By.id("whoIsFlyingFootnote")
    private static final String RECONCILE_PASSENGER_INFO_TEXT = "* Passenger details may not be modified with the Change Itinerary feature"
    private static final By FIRST_NAME = By.id("firstName0")
    private static final By LAST_NAME = By.id("lastName0")
    private static final String CONTINUE_BUTTON = "swa_purchase_continueButton_passenger"

    Data data
    Flow flow
    PassengerData passengerData
    ContactInfo contactInfo
    ItineraryData itineraryData

    String firstNameElement
    String middleNameElement
    String lastNameElement

    public PassengerInfo(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.PASSENGER_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {

        if (flow.hasHotel && !flow.isAirChange) {
            if (passengerData.getPassengers() && passengerData.getPassengers().size() > 0) {
                fillIn(HOTEL_GUEST_FIRST_NAME, passengerData.getPassengers().get(0).firstName)
                fillIn(HOTEL_GUEST_LAST_NAME, passengerData.getPassengers().get(0).lastName)
            } else {
                fillIn(HOTEL_GUEST_FIRST_NAME, data.firstName)
                fillIn(HOTEL_GUEST_LAST_NAME, data.lastName)
            }
        }

        if (flow.hasAir && !flow.isAirChange) {
            boolean avoidPassengerInfoOnReconcilePage = waitForElement(WHO_IS_FLYING).getText().contains(RECONCILE_PASSENGER_INFO_TEXT)
            // CTD tests had been trying to fill in passenger information on Reconcile page.
            if (avoidPassengerInfoOnReconcilePage) {
                return
            }

            if (passengerData.getPassengers().isEmpty()) {
                fillIn(FIRST_NAME, data.firstName)
                fillIn(LAST_NAME, data.lastName)
                fillGender("Male")
                Date birthDay = new Date()
                birthDay[YEAR] = birthDay[YEAR] - 34
                fillInDob(birthDay)
            } else if (!flow.isAirChange) {
                int i = 0

                passengerData.getPassengers().each { passenger ->
                    if (!passenger.isRapidRewadsUser() || !flow.isLoggedIn) {
                        fillPassengerIfRapidRewardsIsNotLoggedIn(i, passenger)
                    }
                    i++
                }
            }
        }
    }

    private def fillPassengerIfRapidRewardsIsNotLoggedIn(int i, Passenger passenger) {
        firstNameElement = "firstName" + i.toString()
        fillIn(By.id(firstNameElement), passenger.firstName)
        middleNameElement = "middleName" + i.toString()
        fillIn(By.id(middleNameElement), passenger.middleName)

        lastNameElement = "lastName" + i.toString()
        fillIn(By.id(lastNameElement), passenger.lastName)
        fillSuffix(passenger.suffix, i)
        Date passengerDateOfBirth = passenger.getDateOfBirth()
        if (flow.isUM) {
            Passenger umPassenger = new Passenger()
            umPassenger.beChild()
            passengerDateOfBirth = umPassenger.getDateOfBirth()
            itineraryData.umDateOfBirth = passengerDateOfBirth
        }
        fillInDob(passengerDateOfBirth, i)
        fillGender(passenger.gender, i)
    }

    void fillFormBasedOn(ReservationSpecification specification) {
        def passengers = flow.isLoggedIn ? specification.allPassengersExcludeFirst : specification.allPassengers
        int index = flow.isLoggedIn ? 1 : 0
        passengers.each { passenger ->
            fillIn(By.id("firstName" + index), passenger.firstName)
            fillIn(By.id("middleName" + index), passenger.middleName)
            fillIn(By.id("lastName" + index), passenger.lastName)
            fillInDob(passenger.dateOfBirth.toDateMidnight().toDate(), index)
            fillGender(passenger.gender, index)
            index++
        }
    }

    public void fillGender(String gender, def elementIndex = 0) {
        chooseInDropDownByValue("gender" + elementIndex.toString(), gender)
    }

    public void fillInDob(Date date, def elementIndex = 0) {
        chooseInDropDownByValue("dobMonth" + elementIndex.toString(), date.getCalendarDate().getMonth().toString())
        // Let the selection of the month, from above, complete the loading of the days available to select - this may need to be tweaked
        sleep(10)
        chooseInDropDownByValue("dobDay" + elementIndex.toString(), date.getCalendarDate().getDayOfMonth().toString())
        sleep(5)
        chooseInDropDownByValue("dobYear" + elementIndex.toString(), date.getCalendarDate().getYear().toString())
    }

    @Deprecated
    def fillInDobAndGender() {
        chooseInDropDownByValue("dobMonth0", "10")
        chooseInDropDownByValue("dobDay0", "12")
        chooseInDropDownByValue("dobYear0", "1970")
        chooseInDropDownByValue("gender0", "Male")
    }

    public void fillSuffix(String suffix = "", def elementIndex = 0) {
        chooseInDropDownByText("suffix" + elementIndex.toString(), suffix)
    }

}
