package steps;

import org.junit.Test
import pages.NonRevListPassengersPage

import org.junit.Assert
import org.junit.Ignore

public class NonRevListPassengersStepsTest {

    class StubNonRevListPassengersPage extends NonRevListPassengersPage {
        int addShareItineraryEmailInvocationCount = 0;
        List<Integer> enterShareItineraryEmailInvocationParameters = [];

        StubNonRevListPassengersPage() {
            super(null)
        }

        @Override
        int getCurrentShareItineraryEmailCount() {
            return 2;
        }

        @Override
        void addShareItineraryEmail() {
            addShareItineraryEmailInvocationCount += 1
        }

        @Override
        void enterShareItineraryEmail(int zeroBasedPosition) {
            enterShareItineraryEmailInvocationParameters.add(new Integer(zeroBasedPosition))
        }

        @Override
        void fillContactInformation() {}
    }

}
