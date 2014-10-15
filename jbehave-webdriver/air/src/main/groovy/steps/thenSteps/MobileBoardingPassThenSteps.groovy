/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */
package steps.thenSteps

import org.jbehave.core.annotations.Then
import org.jbehave.core.model.ExamplesTable
import pages.MBPConfirmationPage
import pages.MBPDeliveryOptionsPage
import util.ItineraryData

class MobileBoardingPassThenSteps {

    MBPConfirmationPage mbpConfirmationPage
    MBPDeliveryOptionsPage mbpDeliveryOptionsPage
    ItineraryData itineraryData

    @Then("I see the MBP Confirmation Page")
    void verifyConfirmationPage() {
        mbpConfirmationPage.verifyPage()
    }

    @Then("I see the different segments with the corresponding flight numbers")
    void verifyAreDifferentFlightNumbers() {
        mbpDeliveryOptionsPage.verifyAreDifferentFlightNumbers(itineraryData.departingFlight_number, itineraryData.returningFlight_number)
    }
}

