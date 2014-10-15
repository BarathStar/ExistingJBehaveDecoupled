package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

import util.ToggleJmx
import javax.management.InstanceNotFoundException

class ToggleAntiFraudCheck {

    private boolean antiFraudOff


    @BeforeStories
    def antiFraudOff() {
        try {
            String antiFraudToggle = System.getProperty("ANTI_FRAUD_OFF")
            if (antiFraudToggle != null) {
                new ToggleJmx().toggleOff('ANTI_FRAUD_SERVICE', 'WEB')
                new ToggleJmx().toggleOff('ANTI_FRAUD_SERVICE', 'SVC')
                antiFraudOff = true;
            }
        } catch (InstanceNotFoundException jmxInstanceNotFound) {
            // OK - fail quietly
        }
    }

    @AfterStories
    def toggleOffIfOn() {
        if (antiFraudOff) {
            new ToggleJmx().toggleOn('ANTI_FRAUD_SERVICE', 'WEB')
            new ToggleJmx().toggleOff('ANTI_FRAUD_SERVICE', 'SVC')
        }
    }

}
