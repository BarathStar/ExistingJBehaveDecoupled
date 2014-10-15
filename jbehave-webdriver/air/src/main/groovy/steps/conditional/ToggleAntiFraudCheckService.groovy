package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

import util.ToggleJmx

class ToggleAntiFraudCheckService {

    private boolean antiFraudOff

    @BeforeStories
    def airTranOn() {
        String service = System.getProperty("ANTI_FRAUD_TOGGLE_SVC")
        if (service != null) {
            new ToggleJmx().toggleOff('ANTI_FRAUD_SERVICE', 'SERVICE')
            antiFraudOff = true;
        }
    }

    @AfterStories
    def toggle_off_if_on() {
        if (antiFraudOff) {
            new ToggleJmx().toggleOn('ANTI_FRAUD_SERVICE', 'SERVICE')
        }
    }
}
