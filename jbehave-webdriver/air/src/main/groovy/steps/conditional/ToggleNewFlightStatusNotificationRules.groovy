package steps.conditional

import org.jbehave.core.annotations.BeforeStories;
import util.ToggleJmx;

class ToggleNewFlightStatusNotificationRules {
    
    /*
     * If a test depends on the NEW_FLIGHT_STATUS_NOTIFICATION_RULES toggle, then the job who launches the
     * test should explicitly set either NEW_FLIGHT_STATUS_NOTIFICATION_RULES_ON or else NEW_FLIGHT_STATUS_NOTIFICATION_RULES_OFF
     * before executing the test.
     */
    @BeforeStories
    def checkIfShouldForceNewFlightStatusNotificationRulesToggle() {
        maybeSetNewFlightStatusNotificationRulesOn()
        maybeSetNewFlightStatusNotificationRulesOff()
    }

    def maybeSetNewFlightStatusNotificationRulesOn() {
        String mustBeOn = System.getProperty("NEW_FLIGHT_STATUS_NOTIFICATION_RULES_ON")
        if (mustBeOn != null) {
            new ToggleJmx().toggleOn('NEW_FLIGHT_STATUS_NOTIFICATION_RULES', 'WEB')
        }
    }

    def maybeSetNewFlightStatusNotificationRulesOff() {
        String mustBeOff = System.getProperty("NEW_FLIGHT_STATUS_NOTIFICATION_RULES_OFF")
        if (mustBeOff != null) {
            new ToggleJmx().toggleOff('NEW_FLIGHT_STATUS_NOTIFICATION_RULES', 'WEB')
        }
    }
}
