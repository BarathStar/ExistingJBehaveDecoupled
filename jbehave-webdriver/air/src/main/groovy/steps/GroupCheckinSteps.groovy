package steps

import pages.*
import state.Flow
import state.PassengerData
import org.jbehave.core.annotations.When

public class GroupCheckinSteps {

    GroupCheckinPage groupCheckinPage
    PassengerData passengerData
    Flow flow

    @When("I select some passengers available for checkin")
    def selectPassengersAvailableForCheckin() {
        groupCheckinPage.selectSomePassengersAvailableForCheckin(3)
    }

    @When("I confirm to start the checkin process")
    def confirmStartCheckinProcess() {
        groupCheckinPage.clickOnContinueButton()
        groupCheckinPage.checkSomethingServed()
    }

    @When("I select all the passengers available for checkin except for one")
    def selectAllPassengersAvailableForCheckinExceptLastOne() {
        groupCheckinPage.selectAllPassengersAvailableForCheckinExceptForOne(flow.exludedPassengerForCheckin)
    }

    @When("I select all the passengers available for checkin")
    def selectAllPassengersAvailableForCheckIn() {
        groupCheckinPage.selectAllPassengersAvailableForCheckIn()
    }
}