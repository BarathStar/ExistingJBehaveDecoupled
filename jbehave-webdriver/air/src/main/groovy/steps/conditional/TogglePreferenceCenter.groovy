package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class TogglePreferenceCenter {

    private static boolean preferenceCenterToggle = false;

    @BeforeStories
    def preferenceCenterToggleOn() {
        if (!System.getProperty("domainToTest", "").contains("ftst")) {
            ToggleJmx toggle = new ToggleJmx();
            preferenceCenterToggle = true
            if (toggle.isOff('PREFERENCE_CENTER')) {
                new ToggleJmx().toggleOn('PREFERENCE_CENTER', 'WEB')
                new ToggleJmx().toggleOn('PREFERENCE_CENTER', 'SERVICE')
            }
        }
    }

    @AfterStories
    def preferenceCenterToggleOff() {
        if (preferenceCenterToggle) {
            preferenceCenterToggle = false
            new ToggleJmx().toggleOff('PREFERENCE_CENTER', 'WEB')
            new ToggleJmx().toggleOff('PREFERENCE_CENTER', 'SERVICE')
        }
    }

    static boolean isOn() {
        return preferenceCenterToggle;
    }
}