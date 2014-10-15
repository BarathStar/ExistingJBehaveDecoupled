package steps.thenSteps

import fixture.stubs.DynaStubsIntegration
import pages.TravelDocumentsPage
import domain.AirReservation
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import util.ItineraryData
import state.ScenarioState
import pages.SWASearchPage
import state.Flow

class TravelDocumentsThenSteps {
    TravelDocumentsPage travelDocumentsPage
    ItineraryData itineraryData
    ScenarioState scenarioState
    Flow flow
    SWASearchPage swaSearchPage

    @When("I view my boarding pass")
    @Then("I view my boarding pass")
    @Alias("I view and verify my boarding pass")
    def printTravelDocuments() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        itineraryData = airReservation.itineraryData
        travelDocumentsPage.with {
            if (!itineraryData.departingFlight_carrierCode.toString().equalsIgnoreCase("FL")) {
                verifyPrintButton()
                verifyCheckinDocumentHeading()
                verifyBoardingPassDocType()
                verifySouthWestBoardingPasses(airReservation)
                if (!DynaStubsIntegration.useDynaStubs()) {
                    verifyGateInformationAlertMessage()
                    verifyFlightNumbers()
                    //verifyAirportDetails()     //aruna
                    verifyBottomBoardingPass(airReservation)
                    verifyDates()
                    verifyConfirmationNumber(airReservation)
                }
//                verifyBoardingTimePresent()
                verifyBoardingGroupAndPosition()
                verifyBottomLinks()
                verifyReturnToPreviousScreen()
                if (flow.isRapidRewards) {
                    isRRNumberDisplayed(flow.getRrUser().number)
                    verifyRRSpecialConditions()
                }
            } else {
                verifyAirTranBoardingPass(airReservation)
            }
        }
        //r code
        if(swaSearchPage.itineraryData.hasDisabilities)
        {

            travelDocumentsPage.verifyDisabilitiesCodeCWC()
            travelDocumentsPage.verifyDisabilitiesCodePOC()
        }
    }

    @Then("I view my security document")
    def viewSecurityDocument() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (!itineraryData.departingFlight_carrierCode.toString().equalsIgnoreCase("FL")) {
            travelDocumentsPage.verifySouthWestSecurityDocument(airReservation)
        } else {
            // Not sure what AirTran Security Document would look like.
            // it is out of Scope for my current task
            travelDocumentsPage.verifyAirTranSeniorBoardingPass(airReservation)
        }
    }

    @Then("I view my senior boarding pass")
    def viewBoardingPass() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (!itineraryData.departingFlight_carrierCode.toString().equalsIgnoreCase("FL")) {
            travelDocumentsPage.verifySouthWestSeniorBoardingPass(airReservation)
        } else {
            travelDocumentsPage.verifyAirTranSeniorBoardingPass(airReservation)
        }
    }

     @Then("I view my A List Preferred boarding pass")
    def viewBoardingPassWithAListPreferredText() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (!itineraryData.departingFlight_carrierCode.toString().equalsIgnoreCase("FL")) {
            travelDocumentsPage.verifySouthWestAListPreferredBoardingPass(airReservation)
            travelDocumentsPage.verifyAL
        } else {
            travelDocumentsPage.verifyAirTranAListPreferredBoardingPass(airReservation)
        }
    }

    @Then("I view \$numberOfDocuments southwest \$travel_documents")
    void verifyTravelDocuments(int numberOfDocuments, String travel_documents) {
        travelDocumentsPage.verifyTravelDocumentsSouthWest(numberOfDocuments, travel_documents)
    }

    @Then("I view \$numberOfSouthwestShells Southwest boarding pass shells and \$numberOfAirtranShells AirTran boarding pass shells")
    void verifyBoardingPassShells(int numberOfSouthwestShells, int numberOfAirtranShells) {
        travelDocumentsPage.verifyBoardingPassShells(numberOfSouthwestShells, numberOfAirtranShells)
    }

    @Then("I view boarding passes for Southwest and AirTran")
    void verifyBoardingPassForSouthWestAndAirTran() {
        travelDocumentsPage.verifyBoardingPassForSouthWestAndAirTran()
    }

    @Then("I will be asked for details of the flight and passengers to check in")
    def checkCheckInPage() {
        travelDocumentsPage.verifyPage()
    }

    @Then("I should receive my drink coupon")
    def shouldReceiveMyDrinkCoupon() {
        travelDocumentsPage.verifyDrinkCouponBarCode(true)
    }

    @Then("I should not receive a drink coupon")
    def shouldNotReceiveMyDrinkCoupon() {
        travelDocumentsPage.verifyDrinkCouponBarCode(false)
    }

    @Then("I see IN in the special conditions section of the boarding pass")
    def iSeeINInTheSpecialConditionsSectionOfTheBoardingPass() {
        travelDocumentsPage.verifyInfantSpecialConditionSouthwest()
    }

    @Then("I see INFT in the special conditions section of the boarding pass")
    def iSeeINFTInTheSpecialConditionsSectionOfTheBoardingPass() {
        travelDocumentsPage.verifyInfantSpecialConditionAirTran()
    }

    @Then("I see my Rapid Reward Number in my boarding pass")
    def iSeeRRNumberInBoardingPass(){
        travelDocumentsPage.isRRNumberDisplayed(flow.getRrUser().number)
    }

    @Then("I view my boarding pass(es) with the MBP Delivery Confirmation Message")
    def viewBoardingPassesWithMobileDeliveryConfirmationMessage() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        travelDocumentsPage.verifySouthWestBoardingPasses(airReservation)
        travelDocumentsPage.verifyMobileBoardingPassDeliveredMessage(true)
    }

    @Then("I view my boarding pass AND I see the Failure message on the top of the page")
    def viewBoardingPassWithMobileDeliveryFailureMessage() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        travelDocumentsPage.verifySouthWestBoardingPasses(airReservation)
        travelDocumentsPage.verifyMobileBoardingPassDeliveryFailureMessage(true)
    }

    @Then("I view TSA PreCheck on my boarding pass")
    @Alias("I view TSA PreCheck on my security document")
    def iSeeTSAPreCheckOnBoardingPass(){
        travelDocumentsPage.isTSAPreCheckDisplayed()
    }

    @Then("I do not see TSA PreCheck on my boarding pass")
    @Alias("I do not see TSA PreCheck on my security document")
    def iDoNotSeeTSAPreCheckOnBoardingPass(){
        travelDocumentsPage.isNotTSAPreCheckDisplayed()
    }
}
