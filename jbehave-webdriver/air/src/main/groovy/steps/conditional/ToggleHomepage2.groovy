package steps.conditional

import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleHomepage2 {

    private static boolean toggleHomePage2On = true

    @BeforeStories
    def homepage2Toggle(){
        if (!System.getProperty("domainToTest", "").contains("ftst")) {
            switchOffHomepage2ToggleIfIndicatedBySystemProperty()
            switchOnHomepage2ToggleIfIndicatedBySystemProperty()
        }
    }

    def switchOnHomepage2ToggleIfIndicatedBySystemProperty() {
        if (isHomePage2OnViaSystemProperty()) {
            switchOn(true)
        }
    }

    def switchOffHomepage2ToggleIfIndicatedBySystemProperty() {
        if (isHomepage2OffViaSystemProperty()) {
            switchOn(false)
        }
    }

    private boolean isHomepage2OffViaSystemProperty() {
        String property = System.getProperty("HOMEPAGE_2_OFF")
        boolean isNull = (property == null)
        return !isNull
    }

    private boolean isHomePage2OnViaSystemProperty() {
        String property = System.getProperty("HOMEPAGE_2_ON")
        boolean isNull = (property == null)
        return !isNull
    }

    private void switchOn(boolean on) {
        if (on) {
            new ToggleJmx().toggleOn('HOMEPAGE_2', 'WEB')
            new ToggleJmx().toggleOn('HOMEPAGE_2', 'SERVICE')
            toggleHomePage2On = true
        } else {
            new ToggleJmx().toggleOff('HOMEPAGE_2', 'WEB')
            new ToggleJmx().toggleOff('HOMEPAGE_2', 'SERVICE')
            toggleHomePage2On = false
        }
    }

    static boolean isOn() {
        return toggleHomePage2On
    }
}
