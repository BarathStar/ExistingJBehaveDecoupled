package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx
/**
 * This class is created in order to Toggle ON the Change Email Flow for Rapid Rewards Get Healthy project.
 */
class ToggleRRGetHealthyChangeEmailFlow {

    private boolean changeEmailFlowOn
    
    @BeforeStories
    def changeEmailFlow() {
        String service = System.getProperty("RR_CHANGE_EMAIL")
        if (service != null) {
            new ToggleJmx().toggleOn('RAPID_REWARDS_CHANGE_EMAIL', 'WEB')
            changeEmailFlowOn = true;
        }
    }

    @AfterStories
    def toggleEmail() {
        if (changeEmailFlowOn) {
            new ToggleJmx().toggleOff('RAPID_REWARDS_CHANGE_EMAIL', 'WEB')
        }
    }
}