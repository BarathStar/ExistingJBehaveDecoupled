package steps

import builders.ReservationSpecificationBuilder
import fixture.stubs.DynaStubsIntegration
import fixtures.air.enums.DirectionType
import fixtures.air.enums.FareType
import fixtures.air.enums.ItineraryType
import fixtures.air.enums.PaymentType
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.AfterStories
import static fixtures.air.enums.DirectionType.INBOUND
import static fixtures.air.enums.DirectionType.OUTBOUND
import static java.lang.Integer.parseInt
import state.Flow
import pages.SearchFlightsPage
import com.swacorp.dotcom.webscenarios.air.AirStories

import static org.junit.Assert.fail

class AirBookingDataSetupSteps {

    private static final String LOADER_TAG = "loader:"
    private static final String TRIGGER_METHOD_TAG = "trigger method:"
    private static final char BYTEMAN_RULE_DELIMETER = '#'

    Flow flow
    SearchFlightsPage searchFlightsPage

    ReservationSpecificationBuilder reservationBuilder

    final DynaStubsIntegration dynaStubsIntegration = new DynaStubsIntegration()

    @Given("I am a senior")
    def iAmASenior() {
        reservationBuilder.decreaseAdultPassengerCount()
        reservationBuilder.withSeniorPassengerCount(1)
    }

    @Given("I want to fly a \$itineraryType flight")
    def chooseItineraryType(String itineraryType) {
        reservationBuilder.withItineraryType(ItineraryType.byLabel(itineraryType))
    }

    @Given("I want to pay for my flight with \$paymentType")
    def choosePaymentType(String paymentType) {
        reservationBuilder.withPaymentType(PaymentType.byLabel(paymentType))
    }

    @Given("I want to use an available \$fareType fare")
    def chooseFareType(String fareType) {
        reservationBuilder.withFareType(OUTBOUND, FareType.byLabel(fareType))
        reservationBuilder.withFareType(INBOUND, FareType.byLabel(fareType))
    }

    @Given("I want to use an available \$fareType fare for my \$direction flight")
    def chooseFareType(String fareType, String direction) {
        reservationBuilder.withFareType(DirectionType.fromString(direction), FareType.byLabel(fareType))
    }

    @Given("I want to fly with a friend")
    def flyWithAFriend() {
        reservationBuilder.increaseAdultPassengerCount()
    }

    @Given("I want to fly with \$number friends")
    def flyWithFriends(String number) {
        reservationBuilder.increaseAdultPassengerCountBy(parseInt(number))
    }

    @Given("I am injecting a \$faultPath fault")
    void faultToInject(String faultPath) {
        if (AirStories.FAULT_TESTING) {
            List<String> faultPathList = new ArrayList<String>();
            faultPathList.add(faultPath)
            AirStories.faultSubmit.addRulesFromFiles(faultPathList)

            if (!verifyRuleLoaded(faultPath)) {
                fail "Byteman rule not loaded successfully: " + faultPath
            }

            flow.isFaultInjected = true
            flow.completePurchase = false
        }
    }

    boolean verifyRuleLoaded(String faultPath) {
        String bmRules = AirStories.faultSubmit.listAllRules()
        // check to make sure Byteman hasn't changed their output format, then verify rule loaded
        if (bmRules.contains(Character.toString(BYTEMAN_RULE_DELIMETER))) {
            Collection<String> ruleList = bmRules.tokenize(BYTEMAN_RULE_DELIMETER)

            for (String bmRule: ruleList) {
                if (bmRule.contains(faultPath) && bmRule.contains(LOADER_TAG) && bmRule.contains(TRIGGER_METHOD_TAG)) {
                    return true;
                }
            }
        } else {
            fail "Unable to verify Byteman rule properly loaded: " + faultPath
        }

        return false;
    }

    @Then("I unload all byteman rules")
    void unloadRules() {
        System.out.println("thread." + Thread.currentThread().getId() + " Story run with injected byteman rules: ");
        try {
            System.out.println("thread." + Thread.currentThread().getId() + " " + AirStories.faultSubmit.listAllRules());
        } catch (Exception e) {
            e.printStackTrace()
        }

        AirStories.faultSubmit.deleteAllRules()
    }
}
