package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

class TogglePointsDotCom {

    private boolean togglePointsDotComOff

    @BeforeStories
    def pointsDotComOff() {
        String pointsDotComOffToggle = System.getProperty("POINTSDOTCOM_BUY_GIFT_TRANSFER_OFF")
        if (pointsDotComOffToggle != null) {
            new ToggleJmx().toggleOff('POINTSDOTCOM_BUY_GIFT_TRANSFER', 'WEB')
            new ToggleJmx().toggleOff('POINTSDOTCOM_BUY_GIFT_TRANSFER', 'SERVICE')
            togglePointsDotComOff = true;
        }
    }

    @AfterStories
    def toggle_on_if_off() {
        if (togglePointsDotComOff) {
            new ToggleJmx().toggleOn('POINTSDOTCOM_BUY_GIFT_TRANSFER', 'WEB')
            new ToggleJmx().toggleOn('POINTSDOTCOM_BUY_GIFT_TRANSFER', 'SERVICE')
        }
    }

}