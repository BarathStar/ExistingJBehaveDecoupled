/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package steps

import org.jbehave.core.annotations.When
import pages.AccompanyingAdultPage
import pages.PurchasePage

class AirAccompayingAdultPurchaseSteps {

    PurchasePage purchasePage
    AccompanyingAdultPage accompanyingAdultPage

    @When("I cancel the accompanying adult form")
    void clickCancelLinkOnAccompanyingForm() {
        accompanyingAdultPage.clickCancel()
    }

    @When("I attempt to retrieve an accompanying itinerary without entering a mandatory field")
    void clickContinueOnTheAccompanyingForm() {
        accompanyingAdultPage.clickRetrieveItinerary()
    }

    @When("I click on the continue button on the accompanying adult form")
    void clickContinueOnAccompayingAdultForm() {
        accompanyingAdultPage.clickContinue()
        purchasePage.verifyPage()
    }

    @When("I retrieve my booked flight on the Accompanying Traveler Form")
    void fillInAccompanyingAdultForm() {
        accompanyingAdultPage.fillForm()
    }
}
