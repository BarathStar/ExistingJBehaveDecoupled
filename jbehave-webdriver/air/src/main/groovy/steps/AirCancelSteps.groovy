package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.ReservationToRetrieve
import domain.AirReservation
import domain.Passenger
import domain.Companion
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.AfterScenario
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.elements.RapidRewardsAccountBar
import state.Flow
import state.PassengerData
import state.SWAContextView
import state.ScenarioState
import util.CancelJmx
import pages.*

import org.jbehave.core.annotations.Then

class AirCancelSteps {

    CancelAirPage cancelAirPage
    CancelAirReservationPage cancelAirReservationPage
    CancelBoardingPassPage cancelBoardingPassPage
    NonRevLandingPage nonRevLandingPage
    NonRevSearchListingPage nonRevSearchListingPage
    NonRevRetrievedItineraryPage nonRevRetrievedItineraryPage
    NonRevCancelConfirmationPage nonRevCancelConfirmationPage
    SwaBizPage swaBizPage
    CancelAirReservationRetrievedPage cancelAirReservationRetrievedPage

    ScenarioState scenarioState
    PassengerData passengerData
    Data data
    Flow flow
    AccountSteps accountSteps
    RapidRewardsAccountBar rapidRewardsAccountBar

    private boolean returnFundsToCreditCard

    @Given("I am on the Cancel Air Reservation Page")
    @When("I am on the Cancel Air Reservation Page")
    void openChangeAirReservationPage() {
        //TODO change to transition from the home page when hover capability is available
        cancelAirReservationPage.open()
    }

    @When("I cancel the flight")
    def cancelFlight() {
        returnFundsToCreditCard = true
        cancelFlight(returnFundsToCreditCard)
    }

    @When("I cancel the companion reservation")
    def cancelCompanionReservation() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Companion companion = flow.getCompanion()
        cancelReservation(airReservation.getCompanionPnr(), companion.getFirstName(), companion.getLastName(), true)
    }

    @When("I attempt to cancel my Swabiz itinerary")
    def cancelSwabizFlight() {
        returnFundsToCreditCard = true
        cancelFlight(returnFundsToCreditCard, true)
    }

    @When("I cancel the flight and hold funds")
    def cancelFlightAndHoldFunds() {
        returnFundsToCreditCard = false
        cancelFlight(returnFundsToCreditCard)
    }

    @When("I confirm the air cancellation")
    void confirmAirCancellation()   {
        cancelAirReservationRetrievedPage.verifyPage()
        if (flow.isRapidRewards) {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                verifyTier()
                verifyRRacountNumber()
                saveAvailablePoints()
            }
            cancelAirReservationRetrievedPage.verifyRRNumber()
        }
        cancelAirReservationRetrievedPage.with {
            if (passengerData.containsAdultPassenger()) {
                String AdultPnr = scenarioState.getLastAirReservation().getAdultPnr()
                verifyConfirmationNumberInPageTitle(AdultPnr)
                verifyConfirmationNumberOnItineraryTable(AdultPnr)
            }
            verifyFlightItinerary()
            verifyFlightDate()
            if (!DynaStubsIntegration.useDynaStubs()) {
                verifyPassengerName()
                verifyDepartureAndArrivalTime()
                verifyFlightNumber()
                verifyTravelTime()
                verifyBillingDetails()
                verifyTravelFunds()
                verifyCancelButtonIsPresent()
                verifyDoNotCancelButtonIsPresent()
                if(flow.isRapidRewardsPointsPurchaseOnly) {
                    verifyPointsRefundDetail()
                }
                verifyFareType()
                verifyRoutingType()
            }
            verifyDate()
            verifyDepartureAndArrivalCities()
            finishCancelingFlight()
        }
    }

    @When("I choose to return funds and continue")
    void returnFundsAndContinue() {
        cancelAirReservationPage.verifyPage()
        cancelAirReservationPage.requestForARefund()
        cancelAirReservationPage.clickConfirmCancellation()
        cancelAirReservationPage.checkSomethingServed()
    }

    @When('I continue with the product cancellation')
    void continueWithProductCancellation()  {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        cancelAirReservationPage.clickContinueCancellation()
        airReservation.adultPnrCanceled = true
        airReservation.seniorPnrCanceled = true
    }

    @When("I retrieve the Air reservation to cancel it")
    @Alias("I retrieve my air reservation to cancel it without being logged-in")
    void retrieveAirReservationOnAirCancellationPage() {
        accountSteps.logOutIfLoggedIn()
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        cancelAirReservationPage.open()
        cancelAirReservationPage.verifyPage()
        cancelAirReservationPage.submitReservationToCancel(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When('I decide not to cancel the Air product associated to my trip on the Cancel Air Reservation Page')
    void doNotCancelTheFlight() {
        cancelAirReservationPage.clickDoNotCancelButton()
    }

    def cancelFlight(boolean returnFunds, boolean withOutVerify = false) {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        flow.canceledPNR = airReservation.containsSeniorPassenger() ? airReservation.seniorPnr : airReservation.adultPnr

        ReservationToRetrieve reservation = new ReservationToRetrieve().getAdultOrSeniorReservation(airReservation)
        String firstName
        String lastName

        if (flow.isLoggedIn) {
            firstName = flow.rrUser.RRFirstName
            lastName = flow.rrUser.RRLastName
        } else {
            firstName = reservation.getPassengerFirstName()
            lastName = reservation.getPassengerLastName()
        }
        if(withOutVerify){
            cancelReservationWithoutVerify(reservation.getRecordLocator(), firstName, lastName)
        }else{
            cancelReservation(reservation.getRecordLocator(), firstName, lastName, returnFunds)
        }
        airReservation.adultPnrCanceled = airReservation.containsAdultPassenger() ? true : false
        airReservation.seniorPnrCanceled = airReservation.containsSeniorPassenger() ? true : false
    }


    private void cancelReservation(String pnr, String firstName, String lastName, boolean returnFunds) {

        AirReservation airReservation = scenarioState.getLastAirReservation()
        flow.canceledPNR = airReservation.adultPnr
        if (!flow.isSwabiz) {
            cancelAirReservationPage.open()
        } else {
            swaBizPage.openCancelTravelPage()
        }
        cancelAirReservationPage.submitReservationToCancel(pnr, firstName, lastName)
        cancelAirReservationPage.verifyPage()

        if (cancelBoardingPassPage.isOnCancelBoardingPassPage()) {
            cancelBoardingPassPage.cancelBoardingPass()
        }
        if(returnFunds){
            cancelAirReservationPage.requestForARefund()
        }

        cancelAirReservationPage.finishCancelingFlight()
    }

    boolean cannotCancelPnr() {
        return SWAContextView.retrievePageContext().isGivenStory.peek() || System.getProperty("domainToTest","").contains("ftst")
    }

    @AfterScenario
    def afterScenario() {
        if(cannotCancelPnr()) return

        // Previous Cancel has already done its job - just leave
        if (DynaStubsIntegration.useDynaStubs()) {
            println("Running with Dyna Stubs. Cancellation is not required for the PNR" )
            return
        }

        if (scenarioState.airReservations.isEmpty()) {
            println("No PNR created in story." )
            return
        } else {
            // if there is more than one airReservation just go ahead and try to cancel all of them
            if (scenarioState.airReservations.size() == 1) {
                AirReservation airReservation = scenarioState.getLastAirReservation()
                if (airReservation.adultPnrCanceled || airReservation.seniorPnrCanceled) {
                    println "PNR(S) previously returned to SAAS - most likely through Cancel flow."
                    return
                }
                else if ((airReservation.adultPnr == null || airReservation.adultPnr.isEmpty()) &&
                (airReservation.seniorPnr == null || airReservation.seniorPnr.isEmpty())) {
                    println("No PNR created in story." )
                    return
                }
            }
        }

        try {
            if (System.getProperty("dontReturnPNRsToSaas") == null) {
                def startTime = new Date().getDateTimeString()
                returnPNRsViaJMXCancel()
                def endTime = new Date().getDateTimeString()
                println "PNR(s) returned to SAAS from ${startTime} to ${endTime}"
            }
            else {
                printCurrentPNR()
            }
        }
        catch (Throwable t) {

            println "****************************************************************"
            println "****************************************************************"
            println "PNR(s) **NOT** returned to SAAS, reason " + t.getMessage()
            println "****************************************************************"
            println "****************************************************************"
        } finally {
            passengerData.clearPassengers()
        }
    }

    def printCurrentPNR() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        if (airReservation == null) {
            println("<===***== Current PNR  =  is NULL!!!      ===***==>")
            return
        }
        Passenger passenger
        if (airReservation.containsAdultPassenger()) {
            passenger = airReservation.getAdultPassengers().get(0)
            println("<======== Current Adult PNR  = ${airReservation.adultPnr}       ========>")
            println("<======== Current Last Name  = ${passenger.lastName}  ========>")
            println("<======== Current First Name = ${passenger.firstName} ========>")
            println("<==================================================>")
        }
        if (airReservation.containsSeniorPassenger()) {
            passenger = airReservation.getSeniorPassengers().get(0)
            println("<======== Current Senior PNR = ${airReservation.seniorPnr}       ========>")
            println("<======== Current Last Name  = ${passenger.lastName}  ========>")
            println("<======== Current First Name = ${passenger.firstName} ========>")
        }
        if (airReservation && !(airReservation.containsAdultPassenger() || airReservation.containsSeniorPassenger())) {
            println "Problem with air reservation object - the PNR information has been erroneously cleared!"
        }
    }

    def returnPNRsViaJMXCancel() {
        def cancelJmx = new CancelJmx()
        def airReservationsList = scenarioState.airReservations
        def threadName = Thread.currentThread().getName();
        airReservationsList.each { airReservation ->
            if (airReservation.adultPnr) {
                def adultResult = cancelJmx.cancel(airReservation.adultPnr)
                println "adultResult  [${threadName}] = ${adultResult}"
            }
            if (airReservation.seniorPnr) {
                def seniorResult = cancelJmx.cancel(airReservation.seniorPnr)
                println "seniorResult [${threadName}] = ${seniorResult}"
            }
            airReservation.clearPnr()
        }
    }

    @When("I retrieve the Air reservation by providing the \$firstOrLast name's vowels changed")
    @Alias("I retrieve the Air reservation by providing the \$firstOrLast of first name")
    void retrieveAirReservationOnAirCancellationPageWithWrongName(String firstOrLast) {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getAdultPassengers().get(0)
        String firstName = passenger.firstName
        String lastName = passenger.lastName
        switch (firstOrLast) {
            case "first":
                firstName = "JYSH"
                break
            case "last":
                lastName = "EVNYN"
                break
            case "first letter":
                firstName = "J"
        }
        cancelAirReservationPage.submitReservationToCancel(airReservation.adultPnr, firstName , lastName)
    }


    @When("I proceed to nonrev retrieve/cancel air itinerary page")
    void proceedToNonRevAirCancel() {
        nonRevLandingPage.open()
        nonRevLandingPage.gotoRetrieveCancelItineraryPage()
    }

    @When("I retrieve nonrev itinerary")
    void fillNonRevSearchListingPageAndSubmit() {
        Passenger passenger
        String confirmationNumber
        (confirmationNumber, passenger) = getCurrentPnrAndPassenger()
        nonRevSearchListingPage.fillFormAndSubmit(confirmationNumber, passenger.firstName, passenger.lastName)
    }

    @When("I click the nonrev cancel button")
    void verifyNonRevDetailAndProceedToConfirmationPage() {
        Passenger passenger
        String confirmationNumber
        (confirmationNumber, passenger) = getCurrentPnrAndPassenger()

        nonRevRetrievedItineraryPage.verify(confirmationNumber, passenger.firstName, passenger.lastName)
        nonRevRetrievedItineraryPage.cancelReservation()
    }

    @Then("I verify that the nonrev canceled page is displayed")
    void verifyNonRevAirCancelPage() {
        Passenger passenger
        String confirmationNumber

        (confirmationNumber, passenger) = getCurrentPnrAndPassenger()
        nonRevCancelConfirmationPage.verifyPage(confirmationNumber, passenger)
    }

    private List getCurrentPnrAndPassenger() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = airReservation.adultPnr
        [
            confirmationNumber,
            passenger
        ]
    }

    private void cancelReservationWithoutVerify(String pnr, String firstName, String lastName) {

        AirReservation airReservation = scenarioState.getLastAirReservation()
        flow.canceledPNR = airReservation.adultPnr
        if (!flow.isSwabiz) {
            cancelAirReservationPage.open()
        } else {
            swaBizPage.openCancelTravelPage()
        }
        cancelAirReservationPage.submitReservationToCancel(pnr, firstName, lastName, false)

        if (cancelBoardingPassPage.isOnCancelBoardingPassPage()) {
            cancelBoardingPassPage.cancelBoardingPass()
        }
    }

    @When("I attempt to cancel the flight")
    def attemptCancelFlight() {
        returnFundsToCreditCard = true
        cancelFlight (returnFundsToCreditCard, true)
    }

    @Then("I view the message for airtran disclaimer")
    def pageMessage() {
        cancelAirReservationPage.verifyAirtranDisclaimerMessage()
    }

    @Then("I verify that the Cancel Reservation form is empty")
    void verifyCancelReservationForm() {
        cancelAirPage.verifyFormIsEmpty()
    }

    @When("I attempt to cancel my air reservation")
    void retrieveAirReservationToCancel() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        String firstName
        String lastName
        cancelAirReservationPage.verifyBasicPage()
        if (flow.isRapidRewards) {
            firstName = flow.rrUser.RRFirstName
            lastName = flow.rrUser.RRLastName
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()
        } else {
            Passenger passenger = airReservation.getAdultPassengers().get(0)
            firstName = passenger.firstName
            lastName = passenger.lastName
        }
        cancelAirReservationPage.verifyLookUpConfirmationLink()
        cancelAirReservationPage.submitReservationToCancel(airReservation.adultPnr, firstName, lastName)
    }
}
