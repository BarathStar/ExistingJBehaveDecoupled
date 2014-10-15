package steps

import java.util.List
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Named
import com.swacorp.dotcom.webscenarios.air.Data
import fixtures.air.*
import domain.AirReservation
import domain.GiftCard
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import pages.TravelFundsPage
import state.Flow
import state.ScenarioState
import util.FundErrorType;

class TravelFundsSteps {

    TravelFundsPage travelFundsPage
    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()
    ScenarioState scenarioState
    AirConfirmationSteps airConfirmationSteps
    AirCancelSteps airCancelSteps
    CommonSteps commonSteps
    Flow flow
    Data data

    @Given("I have \$count valid Giftcard(s)")
    def iHaveValidGiftCards(int count) {
        List<GiftCard> giftcards
        if (DynaStubsIntegration.useDynaStubs()) {
            giftcards = getVouchersOrGiftcards(count)
            dynaStubsIntegration.generateValidStoredValueCard(giftcards, StoredValueCardSpecification.GIFTCARD)
        } else {
            giftcards = data.getGiftCards(count)
        }
        scenarioState.addAllGiftCards(giftcards)
    }

    @Given("I have \$count valid LUV Voucher(s)")
    def iHaveValidLuvVouchers(int count) {
        List<GiftCard> vouchers
        if (DynaStubsIntegration.useDynaStubs()) {
            vouchers = getVouchersOrGiftcards(count)
            dynaStubsIntegration.generateValidStoredValueCard(vouchers, StoredValueCardSpecification.VOUCHER)

        } else {
			vouchers = data.getLuvVouchers(count)
        }
        scenarioState.addAllVouchers(vouchers)
    }

    @Given("I have a valid Travel Fund")
    def haveTravelFund() {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation airReservation = createTravelFundAirReservation()
            dynaStubsIntegration.generateTravelFunds(airReservation)
            scenarioState.add(airReservation)
        }
        else {
            commonSteps.setupRoundTripCarrierFlight("Southwest", "Southwest")
            airConfirmationSteps.bookAFlight()
            airCancelSteps.cancelFlightAndHoldFunds()
        }
    }

	private AirReservation createTravelFundAirReservation() {
		AirReservation airReservation = new AirReservation()
		airReservation.adultPnr = "PNR295"

        Passenger pax = new Passenger("Samfifteen",'',"Leapfrog","Male")
        pax.beAdult()
        airReservation.setPassengers([pax])
		return airReservation
	}

    private List<GiftCard> getVouchersOrGiftcards(int count) {
        List<GiftCard> cards = new ArrayList<GiftCard>()
        count.times {
            cards.add(GiftCard.generateValidGiftCard())
        }
        return cards
    }

    @Given("I have \$amount amount travel funds balance with \$refundable refundable amount and \$nonRefundable nonrefundable amount")
    def haveTravelFundBalance(@Named("amount") int amount, @Named("refundable") int refundable, @Named("nonRefundable") int nonRefundable) {
        if (DynaStubsIntegration.useDynaStubs()) {
            AirReservation airReservation = createTravelFundAirReservation()
            airReservation.amount = amount
            airReservation.refundableAmount = refundable
            airReservation.nonRefundableAmount = nonRefundable
            dynaStubsIntegration.generateTravelFunds(airReservation)
            scenarioState.add(airReservation)
        }
    }

    @When("I confirm to Check the Travel Funds")
    def clickOnCheckNowButton() {
        travelFundsPage.clickOnCheckNowButton()
        travelFundsPage.checkSomethingServed()
    }

    @When("I fill in the confirmation number and security codes with invalid length and leave empty the rest of the fields")
    def fillConfirmationNumberAndSecurityCodeWithInvalidLength() {
        travelFundsPage.fillConfirmationNumberAndSecurityCodeWithInvalidLength()
    }

    @When("I fill in the confirmation number and security codes and leave empty the rest of the fields")
    def fillConfirmationNumberAndSecurityCodeWith() {
        travelFundsPage.fillAllTravelFundsConfirmationNumber("111111")
        travelFundsPage.fillAllLUVVoucherSecurityCode("1111")
        travelFundsPage.fillAllGiftCardSecurityCode("1111")
    }

    @When("I fill in the Passenger's name fields and fill in the Voucher and Card number with invalid length")
    def fillPassengerAndVoucherAndCardWithInvalidLength() {
        travelFundsPage.fillAllTravelFundsConfirmationNumber()
        travelFundsPage.fillAllLUVVoucherSecurityCode()
        travelFundsPage.fillAllGiftCardSecurityCode()
        travelFundsPage.fillAllFirstNameFields("A")
        travelFundsPage.fillAllLastNameFields("A")
        travelFundsPage.fillAllVoucherNumberFields("1")
        travelFundsPage.fillAllCardNumberFields("1")
    }

    @When("I fill in the Passenger's name fields and fill in the Voucher and Card number")
    def fillPassengerAndVoucherAndCard() {
        travelFundsPage.fillAllTravelFundsConfirmationNumber()
        travelFundsPage.fillAllLUVVoucherSecurityCode()
        travelFundsPage.fillAllGiftCardSecurityCode()
        travelFundsPage.fillAllFirstNameFields("Samfifteen")
        travelFundsPage.fillAllLastNameFields("Leapfrog")
        travelFundsPage.fillAllVoucherNumberFields("1111111111111111")
        travelFundsPage.fillAllCardNumberFields("1111111111111111")

    }

    @When("I fill in all the fields with funds which do no exist")
    def fillAllFields() {
        travelFundsPage.fillAllTravelFundsConfirmationNumber("111111")
        travelFundsPage.fillAllLUVVoucherSecurityCode("1111")
        travelFundsPage.fillAllGiftCardSecurityCode("1111")
        travelFundsPage.fillAllFirstNameFields("Samfifteen")
        travelFundsPage.fillAllLastNameFields("Leapfrog")
        travelFundsPage.fillAllVoucherNumberFields("1111111111111111")
        travelFundsPage.fillAllCardNumberFields("1111111111111111")
    }

    @When("I fill in giftcard's fields with valid data")
    def fillAllGiftCardFields() {
        scenarioState.getGiftcards().eachWithIndex {  giftcard, i ->
            travelFundsPage.fillGiftCardNumberFieldByIndex((i+1),giftcard.getNumber())
            travelFundsPage.fillGiftCardCodeFieldByIndex((i+1), giftcard.getSecurityCode())
        }
    }

    @When("I fill in travel fund's with invalid PNR \$errorType")
    def fillInvalidPNRField(@Named("errorType") String setError) {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        FundErrorType errorType = FundErrorType.from(setError)
        airReservation.adultPnr = "PNR296"
        airReservation.errorTypePnr = errorType
        dynaStubsIntegration.generateTravelFundsWithError(airReservation)
        scenarioState.add(airReservation)
        travelFundsPage.fillInTravelFundForm(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When("I fill in voucher's fields with valid data")
    def fillAllVoucherFields() {
        scenarioState.getVouchers().eachWithIndex { voucher, i ->
            travelFundsPage.fillVoucherNumberFieldByIndex((i+1),voucher.getNumber())
            travelFundsPage.fillVocuherCodeFieldByIndex((i+1), voucher.getSecurityCode())
        }
    }

    @When("I fill in travel fund's fields with valid data")
    def fillTravelFundsFields() {
        AirReservation airReservation = scenarioState.getLastAirReservation()
        Passenger passenger = airReservation.getPassengers().get(0)
        travelFundsPage.fillInTravelFundForm(airReservation.adultPnr, passenger.firstName, passenger.lastName)
    }

    @When("I click on the Check Southwest LUV Voucher link")
    def clickCheckLuvVoucherLink() {
        travelFundsPage.clickOnLUVLink()
    }

    @When("I click on the Check southwestgiftcard link")
    def clickCheckGiftCardLink() {
        travelFundsPage.clickOnGiftCardLink()
    }

    @When("I add Travel Funds, LUV vouchers and Giftcards \$clicks times")
    def addTravelFundsVouchersAndGiftCards(int clicks) {
        travelFundsPage.addTravelFundsLUVsAndGiftCards(clicks)
    }

}