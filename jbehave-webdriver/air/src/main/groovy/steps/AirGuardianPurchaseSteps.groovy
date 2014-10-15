/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package steps

import org.jbehave.core.annotations.*

import pages.PurchasePage
import pages.UnaccompaniedMinorGuardianPage
import pages.YoungTravelerGuardianPage
import state.Flow
import util.DatasetManager
import util.GuardianBuilder
import domain.Guardian
import pages.PricePage
import pages.elements.ShoppingCart
import util.PricePageData
import fixture.stubs.DynaStubsIntegration
import util.PurchasePageData

class AirGuardianPurchaseSteps {

    YoungTravelerGuardianPage youngTravelerGuardianPage
    UnaccompaniedMinorGuardianPage unaccompaniedMinorGuardianPage
    PricePage pricePage
    PurchasePage purchasePage
    ShoppingCart shoppingCart
    PricePageData pricePageData
    Flow flow
    PurchasePageData purchasePageData

    @When("I complete the UM Guardian information for the one-way itinerary")
    void fillDepartingUMGuardianForm() {
        if (!DynaStubsIntegration.useDynaStubs()) {
            unaccompaniedMinorGuardianPage.with {
                verifyBasicPage()
                verifyTitle()
                verifySubtitle()
                verifyName()
                verifyChargesAndTotal()
                verifyParentGuardianContactsInformationArePresent()
                verifyDepartureAndArrivalTime()
                verifyDepartureAndArrivalCities()
                verifyFlightNumber()
                verifyTravelTime()
                verifyRoutingType()
                verifyFareType()
            }
            pricePage.verifyDate()
            if(flow.isRapidRewardsPointsPurchaseOnly) {
                shoppingCart.verifyAirWidgetIsCollapsed()
                shoppingCart.verifySaveButton()
            }
            else {
                shoppingCart.verifyAirInShoppingCartIsCollapsed()
                shoppingCart.verifyTripTotal()
            }
            shoppingCart.with{
                verifyShoppingCartIsExpanded()
                modifyAndRemoveLinks()
                expandAirShoppingCart()
                verifySaveTripButton()
                verifyDate()
                verifyFlightNumber()
                verifyDepartureAndArrivalCities()
                verifyDepartureAndArrivalTime()
                verifyOutboundAndInboundTotals()
                verifyTotalCostBreakdown()
            }
        }
        Guardian dropOffGuardian = DatasetManager.loadGuardian('DropOffGuardian')
        Guardian pickUpGuardian = DatasetManager.loadGuardian('PickUpGuardian')
        Guardian alternateGuardian = DatasetManager.loadGuardian('Guardian')
        unaccompaniedMinorGuardianPage.with {
            fillDepartingDropOffGuardian(dropOffGuardian)
            fillDepartingPickUpGuardian(pickUpGuardian)
            fillDepartingAlternateGuardian(alternateGuardian)
        }
    }

    @When("I cancel the UM Guardian form")
    void clickCancelLinkOnUMGuardianForm() {
        unaccompaniedMinorGuardianPage.clickCancel()
    }

    @When("I complete the YT Parent/Guardian Information with \$contactMethod notification method")
    void fillGuardianFormWithContactMethodAndContinue(String contactMethod) {
        flow.guardianWithContactMethod = new GuardianBuilder().withContactMethod(contactMethod).build()
        youngTravelerGuardianPage.fillForm(flow.guardianWithContactMethod)
        youngTravelerGuardianPage.clickContinue()
        youngTravelerGuardianPage.verifyPage()
    }

    @When("I change the YT Parent/Guardian Information with \$contactMethod notification method")
    void changeGuardianFormContactMethodAndContinue(String contactMethod) {
        flow.guardianWithContactMethod = new GuardianBuilder().withContactMethod(contactMethod).build()
        youngTravelerGuardianPage.changeContactMethod(flow.guardianWithContactMethod)
        youngTravelerGuardianPage.clickContinue()
        youngTravelerGuardianPage.verifyPage()
    }

    @When("I complete the YT guardian information")
    void fillYoungGuardianFormAndContinue() {
        flow.guardianWithContactMethod = new GuardianBuilder().build()
        youngTravelerGuardianPage.fillForm(flow.guardianWithContactMethod)
        youngTravelerGuardianPage.clickContinue()
        youngTravelerGuardianPage.verifyPage()
    }

    @When("I continue from the UM Guardian page to the Non-Editable Purchase page")
    @Alias("I attempt to save the UM Parent/Guardian information")
    void clickContinueOnTheUMGuardianForm() {
        unaccompaniedMinorGuardianPage.clickContinue()
    }

    @When("I choose to prepopulate the fields for the Returning Flight based on the Departing Flight")
    void clickOnPrepopulateCheckbox() {
        unaccompaniedMinorGuardianPage.clickPrepopulateCheckbox()
    }

    @When("I complete the UM Guardian information for the round-trip itinerary")
    void fillUMGuardianFormForARoundTrip() {
        unaccompaniedMinorGuardianPage.verifyPage()
        fillDepartingUMGuardianForm()
        clickOnPrepopulateCheckbox()
        Guardian guardian = DatasetManager.loadGuardian('AlternateGuardian')
        unaccompaniedMinorGuardianPage.fillReturningAlternateGuardian(guardian)
        clickContinueOnTheUMGuardianForm()
        purchasePage.verifyPage()
    }

    @When("I cancel the YT Parent/Guardian form")
    void clickOnCancelLink() {
        youngTravelerGuardianPage.clickCancel()
    }

    @When("I attempt to save the YT Parent/Guardian without entering the mandatory fields")
    void clickContinueOnTheYTGuardianForm() {
        youngTravelerGuardianPage.clickContinue()
    }

    @When("I complete the UM Parent/Guardian Information with the same primary and alternate pick-up person for the flight")
    void fillUMGuardianFormSamePrimaryAndAlternatePerson() {
        unaccompaniedMinorGuardianPage.verifyPage()
        Guardian dropOffGuardian = DatasetManager.loadGuardian('DropOffGuardian')
        Guardian pickUpGuardian = DatasetManager.loadGuardian('PickUpGuardian')
        Guardian alternateGuardian = pickUpGuardian
        unaccompaniedMinorGuardianPage.fillDepartingDropOffGuardian(dropOffGuardian)
        unaccompaniedMinorGuardianPage.fillDepartingPickUpGuardian(pickUpGuardian)
        unaccompaniedMinorGuardianPage.fillDepartingAlternateGuardian(alternateGuardian)
    }
}
