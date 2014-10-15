package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

import util.ToggleJmx
import javax.management.InstanceNotFoundException

class ToggleDot {

    private boolean dotTogglesOn


    @BeforeStories
    def dotToggleOn() {
        try {
            String dotToggle = System.getProperty("DOT_TOGGLE")
            if (dotToggle != null) {
                new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_DRAWERS', 'WEB')
                new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_DRAWERS', 'SVC')
                dotTogglesOn = true;
            }
        } catch (InstanceNotFoundException jmxInstanceNotFound) {
            // OK - fail quietly
        }
    }

    @AfterStories
    def toggleOffIfOn() {
        if (dotTogglesOn) {
            new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_DRAWERS', 'WEB')
            new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_DRAWERS', 'SVC')
        }
    }
}