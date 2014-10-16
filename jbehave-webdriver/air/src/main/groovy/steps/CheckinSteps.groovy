package steps

import domain.Passenger
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Then
import domain.AirReservation
import pages.elements.RapidRewardsAccountBar
import state.Flow
import state.ScenarioState
import pages.*

import fixture.stubs.DynaStubsIntegration
import pages.elements.CreditCardSubForm
import pages.elements.BillingInfo
import org.jbehave.core.annotations.Aliases
import com.swacorp.dotcom.webscenarios.air.Data
import util.RRContactInformation

public class CheckinSteps {

    TravelDocumentsPage travelDocumentsPage
    ConfirmationPage confirmationPage
    ScenarioState scenarioState
    CheckinPage checkinPage
    NonRevLandingPage nonRevLandingPage
    NonRevSecurityDocumentViewPage nonRevSecurityDocumentViewPage
    AirTranSeatSelectPage airTranSeatSelectPage
    Flow flow
    Data data
    RapidRewardsSteps rapidRewardsSteps
    SelectFlightsToUpgradePage upgradePage
    NewPricePage newPricePage
    ReconcilePage reconcilePage
    CreditCardSubForm creditCardSubForm
    BillingInfo billingInfo
    GroupCheckinPage groupCheckinPage
    SwaBizPage swaBizPage
    BusinessSelectPage businessSelectPage
    HomePage homePage
    SearchFlightsPage searchFlightsPage
    ProductPage productPage
    UpgradeToBusinessSelectPage upgradeToBusinessSelectPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    RRContactInformation rrContactInformation
    RapidRewardsAccountBar rapidRewardsAccountBar
    MobileBoardingPassSteps mobileBoardingPassSteps

    @Given("I am on the check-in page")
    void openCheckinPage() {
        checkinPage.open()
    }

    @When("I open check-in page in another window")
    void openCheckinPageInAnotherWindow() {
        checkinPage.openCheckinPageInAnotherWindow()
    }

    @Given("I retrieve travel documents")
    @When("I retrieve travel documents")
    @Alias("I retrieve my reservation to checkin")
    def retrieveTravelDocuments() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs() || flow.isSwabiz) {
            checkinPage.open()
            checkinPage.verifyPage()
            checkinPage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
            groupCheckinPage.checkSomethingServed()
        }
        else {
            confirmationPage.flightCheckIn()
        }
    }

    @When("I retrieve Altea travel documents")
    @Alias("I retrieve my Altea reservation to checkin")
    def retrieveAlteaTravelDocuments() {
		AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs() || flow.isSwabiz) {
            checkinPage.open()
            checkinPage.verifyPage()
            checkinPage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
        }
        else {
            confirmationPage.flightCheckIn()
        }
    }

    @When("I retrieve my reservation through Stand Alone Path")
    def retrieveReservationToUpgradeToBusinessSelect(){
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        businessSelectPage.retrieveReservationToBusinessSelect(confirmationNumber, passenger.firstName, passenger.lastName)
    }

    @When("I attempt to retrieve an itinerary using an invalid PNR on the Check-in Page")
    def attemptToRetrieveReservationWithInvalidPnrOnCheckinPage() {
        checkinPage.retrieveReservationToCheckin("ABC123", "Sam", "Leapfrog")
    }

    @When("I checkin for my flight")
    def checkinForFlight() {
        retrieveTravelDocuments()
        selectPassengers()
    }

    @Given("I check the drink coupon checkbox")
    def checkDrinkCouponCheckbox() {
        checkinPage.selectPrintDrinkCouponsIfNotSelected()
    }

    @Given("I uncheck the drink coupon checkbox")
    def uncheckDrinkCouponCheckbox() {
        checkinPage.uncheckPrintDrinkCoupons()
    }

    @Given("I click checkin to create a boarding pass")
    @When("I click checkin to create a boarding pass")
    @Aliases(values = ["I click check in to view my boarding pass",
                        "I click check in to view my security document"])
    def clickCheckInToCreateBoardingPass() {
        String firstName
        String lastName
        if(flow.isRapidRewards) {
            firstName = rrContactInformation.firstName
            lastName = rrContactInformation.lastName
        } else {
            Passenger passenger = scenarioState.getLastAirReservation().getPassengers().get(0)
            firstName = passenger.firstName
            lastName = passenger.lastName
        }
        if (!DynaStubsIntegration.useDynaStubs()) {
            checkinPage.with {
                verifySelectPrintDocumentPage()
                verifyRapidRewardNumberText()
                if(!itineraryData.isPromoCertBooking()){
                    //verifyUpgradeToBusinessSelectButtonDisplayed()
                }
                verifyAdditionalDocumentation()
                //verifyDepartureAndArrivalCities()  //aruna
                verifyCancelButtonDisplayed()
                verifyDate()
                verifyPassengerName(firstName, lastName)
                verifyDepartureAndArrivalTime()
                verifyFlightNumber()
            }
            if (flow.isRapidRewards){
                rapidRewardsAccountBar.with {
                    verifyRRGreeting()
                    verifyLogOutLink()
                    verifyRRName()
                    verifyMyAccountLink()
                    verifyTier()
                    verifyRRacountNumber()
                }
                checkinPage.verifyRRAccountNumber()
            }
        }
        checkinPage.clickCheckinButton()
    }

    @When("I click checkin to create a nonrev security document")
    def clickCheckInToCreateNonRevSecurityDocument() {
        Passenger passenger
        String confirmationNumber
        (confirmationNumber, passenger) = getCurrentPnrAndPassenger()
        nonRevSecurityDocumentViewPage.verify(confirmationNumber, passenger.firstName,passenger.lastName)
        nonRevSecurityDocumentViewPage.clickCheckinButton()
    }

    @When("I choose cancel")
    def chooseCancel(){
        checkinPage.selectPassengerAndCancel()
    }

    @When("I choose to upgrade to business select")
    void upgradeToBusinessSelect() {
        clickUpgrade()
        upgradePage.selectOutBoundToChangeItinerary()
        upgradePage.continueToUpgrade()
        if (newPricePage.hasBoardingPass()) {
            newPricePage.deleteBoardingPass()
        }
        newPricePage.submit()
        reconcilePage.fillInPassengerContactInformation()
        creditCardSubForm.fillForm()
        billingInfo.fillForm()
        reconcilePage.enterEmail("tester@wnco.com")
        reconcilePage.submit()
    }

    @When("I click on upgrade to business select")
    @Given("I choose to upgrade to business select")
    void clickUpgrade() {
        checkinPage.verifySelectPrintDocumentPage()
        if (!DynaStubsIntegration.useDynaStubs()) {
            Passenger passenger = scenarioState.getLastAirReservation().getPassengers().get(0)
            checkinPage.with {
                verifyPassengerName(passenger.firstName, passenger.lastName)
                verifyRapidRewardNumberText()
                verifyDate()
                verifyDepartureAndArrivalTime()
                verifyDepartureAndArrivalCities()
                verifyFlightNumber()
                verifyCancelButtonDisplayed()
                verifyUpgradeToBusinessSelectButtonDisplayed()
            }
        }
        checkinPage.clickUpgradeToBusinessSelect()
    }

    @When("I click the Checkin button on the confirmation page")
    void clickCheckInButtonOnConfirmationPage(){
        confirmationPage.flightCheckIn()
    }

    @When("I enter my Rapid Rewards number on the Check In page")
    void enterRapidRewardsNumberOnCheckinPage(){
        if (DynaStubsIntegration.useDynaStubs()){
            rapidRewardsSteps.addRapidRewardsUserToScenario("Alist")
        }

        checkinPage.verifyPage()
        checkinPage.selectAddNumberSignup()
        checkinPage.enterRapidRewardsNumber()
    }

    @When("I enter an invalid Rapid Rewards number on the Check In page")
    void enterInvalidRapidRewardsNumberOnCheckinPage(){
        checkinPage.verifyPage()
        checkinPage.selectAddNumberSignup()
        checkinPage.enterInvalidRapidRewardsNumber()
    }

    @When("I click the help link")
    def clickHelpLink(){
        checkinPage.selectAddNumberSignup()
        checkinPage.clickHelpLink()
    }

    @When("I return to the checkin page")
    public void returnToCheckinPageFromBoardingPassesPrintPage(){
        travelDocumentsPage.returnToCheckinPageFromBoardingPassesPrintPage()
        checkinPage.verifyCheckinLandingPage()
    }

    @When("I click the seat selection button on the checkin page")
    public void selectSeatSelectionButtonOnCheckinPage(){
        checkinPage.clickSeatSelectionButtonOnCheckInPageOnly()
    }

    @Then("I see the seat selection unavailable message for the AirTran flight on the checkin page")
    def verifySeatSelectionErrorVerbiage() {
        checkinPage.verifySeatSelectionUnavailableVerbiage()
    }

    @Given("I click on the Check in button on the Confirmation Page")
    @When("I click on the Check in button on the Confirmation Page")
    @Aliases(values = ["I proceed to the new online checkin page",
                       "I click on the Check in button on the view reservations Page"])
    void clickOnCheckInForFlightButton() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs()) {
            checkinPage.open()
            checkinPage.verifyPage()
            checkinPage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
        } else {
            confirmationPage.clickOnCheckInForFlightButton()
        }
    }

    @Given("I perform a checkin online")
    @When("I perform a checkin online")
    void performCheckinOnline() {
        viewCheckinDetailsPage()
        clickCheckInToCreateBoardingPass()
    }

    @When("I attempt to view the checkin details for my flight")
    @Given("I attempt to view the checkin details for my flight")
    void viewCheckinDetailsPage() {
        String firstName
        String lastName
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)

        if(flow.isRapidRewards && !DynaStubsIntegration.useDynaStubs()) {
            firstName = rrContactInformation.firstName
            lastName = rrContactInformation.lastName
            rapidRewardsAccountBar.verifyRRGreeting()
            rapidRewardsAccountBar.verifyLogOutLink()
            rapidRewardsAccountBar.verifyRRName()
            rapidRewardsAccountBar.verifyMyAccountLink()
            rapidRewardsAccountBar.verifyTier()
            rapidRewardsAccountBar.verifyRRacountNumber()
        } else {
            firstName = passenger.firstName
            lastName = passenger.lastName
        }
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr

        if (DynaStubsIntegration.useDynaStubs()) {
            checkinPage.open()
        }
        checkinPage.verifyCheckinOnlinePage()
        checkinPage.verifyLookupConfirmationNumberLink()
        checkinPage.retrieveReservationToCheckin(confirmationNumber, firstName, lastName)
    }

    @When("I proceed to nonrev checkin page")
    void gotoNonRevCheckinPage() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs()) {
            nonRevLandingPage.open()
            nonRevLandingPage.gotoNonRevCheckinPage()
            nonRevLandingPage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)
        }
    }

    @When("I attempt to check in my Swabiz itinerary")
    public void attemptToRetrieveSwabizReservationToChange() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr

        swaBizPage.openCheckInTravelPage()
        checkinPage.retrieveReservationToCheckin(confirmationNumber, passenger.firstName, passenger.lastName)

    }

    @When("I click on Continue button")
    @Given("I click on Continue button on the select flight to upgrade")
    public void clickContinue() {
        upgradeToBusinessSelectPage.verifyBasicPage()
        upgradeToBusinessSelectPage.verifyUpgradeCheckbox()
        if(!DynaStubsIntegration.useDynaStubs()) {
            upgradeToBusinessSelectPage.verifyNameAccNumberAndConfirmationNumber()
            upgradeToBusinessSelectPage.verifyDate()
            upgradeToBusinessSelectPage.verifyDepartureAndArrivalTime()
            upgradeToBusinessSelectPage.verifyDepartureAndArrivalCities()
            upgradeToBusinessSelectPage.verifyFlightNumber()
            upgradeToBusinessSelectPage.verifyTravelTime()
            upgradeToBusinessSelectPage.verifyFareType()
            upgradeToBusinessSelectPage.verifyRoutingType()

        }
        upgradeToBusinessSelectPage.verifyContinueButton()
        upgradeToBusinessSelectPage.verifyCancelButton()
        upgradeToBusinessSelectPage.clickToContinue()
    }

    private List getCurrentPnrAndPassenger() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = airReservation.adultPnr
        [confirmationNumber, passenger]
    }

    @Then("I view the message for airtran disclaimer")
    def pageMessage() {
        checkinPage.verifyAirtranDisclaimerMessage()
    }

    @When("I check in from the check in page")
    def checkInFromCheckInPage() {
        openCheckinPage()
        retrieveTravelDocuments()
    }

    @When("I click continue with security document")
    def clickContinueWithSecurityDocument() {
       checkinPage.clickContinueToSecurityDocument()
    }

    @Then("I see Airport Checkin Required for the unverified senior")
    def iSeeAirportCheckinRequiredForTheUnverifiedSenior() {
        String firstName = flow.rrUser.firstName
        String lastName = flow.rrUser.lastName
        if (!checkinPage.verifyAirportCheckinRequiredText(firstName, lastName)) {
            fail "Unable to find passenger " + firstName + " " + lastName + " to verify Airport Checkin Required message"
        }
    }

    @When("Navataire Checkin is down")
    def navataireCheckinIsDown() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.setPartnerGDSAvailableFlag(confirmationNumber, false);
        }
    }

    @When("TSA PreCheck is on")
    def tsaPreCheckIsOn() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.setTsaPreCheckFlag(confirmationNumber, false, true);
        }
    }

    @When("TSA PreCheck is off")
    def tsaPreCheckIsOff() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        String confirmationNumber = passenger.isSenior() ? airReservation.seniorPnr : airReservation.adultPnr
        if (DynaStubsIntegration.useDynaStubs()) {
            dynaStubsIntegration.setTsaPreCheckFlag(confirmationNumber, false, false);
        }
    }

    @When("I select Assistant Animals link under the Additional Documentation required section")
    def clickOnAssistantAnimalsLink(){
        checkinPage.clickOnAssistantAnimalsLink()
    }

    @When("I checkin with MBP selected")
    def checkinWithMBPselected(){
        performCheckinOnline()
        mobileBoardingPassSteps.verifySubmitButtonIsPresent()
    }
}
