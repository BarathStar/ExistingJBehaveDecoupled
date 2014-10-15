package steps.conditional

import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

/***
 * This class is intended to check and toggle the GLOBAL_NAV feature
 */
class ToggleGlobalNav {

    private static boolean toggleGlobalNavOn = true

    @BeforeStories
    def globalNavToggle() {
        if (!System.getProperty("domainToTest", "").contains("ftst")) {
            toggleGlobalNavOn()
            toggleGlobalNavOff()
        }
    }

    def toggleGlobalNavOn() {
        String mustBeOn = System.getProperty("GLOBAL_NAV_ON")
        if (mustBeOn != null) {
            new ToggleJmx().toggleOn('GLOBAL_NAV', 'WEB')
            new ToggleJmx().toggleOn('GLOBAL_NAV', 'SERVICE')
            toggleGlobalNavOn = true
        }
    }

    def toggleGlobalNavOff() {
        String mustBeOff = System.getProperty("GLOBAL_NAV_OFF")
        if (mustBeOff != null) {
            new ToggleJmx().toggleOff('GLOBAL_NAV', 'WEB')
            new ToggleJmx().toggleOff('GLOBAL_NAV', 'SERVICE')
            toggleGlobalNavOn = false
        }
    }

    static boolean isOn() {
        return toggleGlobalNavOn
    }
}
