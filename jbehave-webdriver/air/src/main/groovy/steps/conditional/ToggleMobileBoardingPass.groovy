/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */
package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleMobileBoardingPass {
    private boolean mobileBoardingPassToggleOn

    @BeforeStories
    def mobileBoardingPassToggleOn() {
        String service = System.getProperty("MOBILE_BOARDING_PASS")
        if (service != null) {
            new ToggleJmx().toggleOn('MOBILE_BOARDING_PASS', 'WEB')
            new ToggleJmx().toggleOn('MOBILE_BOARDING_PASS', 'SERVICE')
            mobileBoardingPassToggleOn = true;
        }
    }

    @AfterStories
    def toggle_off_if_on() {
        if (mobileBoardingPassToggleOn) {
            new ToggleJmx().toggleOff('MOBILE_BOARDING_PASS', 'WEB')
            new ToggleJmx().toggleOff('MOBILE_BOARDING_PASS', 'SERVICE')
        }
    }
}
