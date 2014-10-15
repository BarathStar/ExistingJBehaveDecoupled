package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

class RapidRewardsMallToggle {

    private boolean rapidRewardsMallToggle

    @BeforeStories
    def rrMallToggleOn() {
        def rrMallToggle = System.getProperty("RR_MALL_TOGGLE_ON")
        if (rrMallToggle != null) {
            new ToggleJmx().toggleOn('RAPID_REWARDS_MALL', 'WEB')
            new ToggleJmx().toggleOn('RAPID_REWARDS_MALL', 'SERVICE')
            rapidRewardsMallToggle = true
        }
    }

    @AfterStories
    def toggle_off_if_on() {
        if (rapidRewardsMallToggle) {
            new ToggleJmx().toggleOff('RAPID_REWARDS_MALL', 'WEB')
            new ToggleJmx().toggleOff('RAPID_REWARDS_MALL', 'SERVICE')
            rapidRewardsMallToggle = false
        }
    }

}
