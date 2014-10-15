package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx
import state.LastActivityDateOnOrOff


class ToggleLastActivityDate {

    private boolean lastActivityDateOn

    LastActivityDateOnOrOff lastActivityDateToggle

    @BeforeStories
    def lastActivityDateOn() {
        String lastActivityDateToggle = System.getProperty("LAST_ACTIVITY_DATE_ON")
        if (lastActivityDateToggle != null) {
            new ToggleJmx().toggleOn('LAST_ACTIVITY_DATE', 'WEB')
            lastActivityDateOn = true;
            this.lastActivityDateToggle.state = true
        }
    }

    @AfterStories
    def toggle_off_if_on() {
        if (lastActivityDateOn) {
            new ToggleJmx().toggleOff('LAST_ACTIVITY_DATE', 'WEB')
            lastActivityDateToggle.state == false;
        }
    }

}
