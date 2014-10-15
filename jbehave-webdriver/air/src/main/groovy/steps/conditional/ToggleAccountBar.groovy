package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

import util.ToggleJmx

class ToggleAccountBar {

    final def jmx = new ToggleJmx()
    final def toggleName = 'GLOBAL_ACCOUNT_BAR'

    private def accountBarWasOff = true
    private def restorePreviousState = false

    @BeforeStories
    def turnAccountBarOn() {
        String turnGABOn = System.getProperty("GLOBAL_ACCOUNT_BAR_ON")
        if (turnGABOn != null) {
            restorePreviousState = true
            accountBarWasOff = jmx.isOff(toggleName)
            jmx.toggleOn(toggleName, 'WEB')
        }
    }

    @AfterStories
    def restoreAccountBarState() {
        if (restorePreviousState && accountBarWasOff) {
            jmx.toggleOff(toggleName, 'WEB')
        }
    }
}
