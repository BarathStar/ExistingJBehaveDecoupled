package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import javax.management.InstanceNotFoundException
import org.jbehave.core.annotations.BeforeStories

class ToggleACS {

    private boolean acsTogglesOn

    @BeforeStories
    def acsToggleOn() {
        try {
            String acsToggle = System.getProperty("ACS_TOGGLE")
            if(acsToggle != null) {
                new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS', 'WEB')
                new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS', 'SVC')
                new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_DRAWERS', 'WEB')
                new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_DRAWERS', 'SVC')
                acsTogglesOn = true
            }
        }
        catch(InstanceNotFoundException jmxInstanceNotFound) {
            // OK - fail quietly
        }
    }

    @AfterStories
    def toggleOffIfOn() {
        if (acsTogglesOn) {
            new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS', 'WEB')
            new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS', 'SVC')
            new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_DRAWERS', 'WEB')
            new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_DRAWERS', 'SVC')
            acsTogglesOn = false
        }
    }
}