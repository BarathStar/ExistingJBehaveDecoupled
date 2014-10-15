package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleCSPPD {

    private boolean dingCSPPDToggle = false;

    @BeforeStories
    def toggleDingCSPPDOn() {
        String CSPPDToggle = System.getProperty("DING_FROM_CS_PPD")
        if (CSPPDToggle != null) {
            ToggleJmx toggleJmx = new ToggleJmx();
            if (toggleJmx.isOff('DING_FROM_CS_PPD')){
                new ToggleJmx().toggleOn('DING_FROM_CS_PPD', 'WEB')
                new ToggleJmx().toggleOn('DING_FROM_CS_PPD', 'SERVICE')
                dingCSPPDToggle = true;
            }
        }
    }

    @AfterStories
    def toggleDingCSPPDOffIfOn() {
        if (dingCSPPDToggle) {
            new ToggleJmx().toggleOff('DING_FROM_CS_PPD', 'WEB')
            new ToggleJmx().toggleOff('DING_FROM_CS_PPD', 'SERVICE')
        }
    }
}
