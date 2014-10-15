package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleExternalURLRedirect {

    private static final TOGGLE_NAME = 'DISABLE_EXTERNAL_REDIRECTION'

    private boolean disableExternalRedirectOn

    @BeforeStories
    def disableExternalRedirectOn() {
        String toggleRequest = System.getProperty(TOGGLE_NAME)
        if (toggleRequest != null) {
            new ToggleJmx().toggleOn(TOGGLE_NAME, 'WEB')
            disableExternalRedirectOn = true
        }
    }

    @AfterStories
    def toggleOffIfOn() {
        if (disableExternalRedirectOn) {
            try {
                new ToggleJmx().toggleOff(TOGGLE_NAME, 'WEB')
            } catch (Exception e) {
                // a failure here should taint an otherwise successful test.
            }
        }
    }

}
