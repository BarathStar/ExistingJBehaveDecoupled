package steps.conditional

import org.jbehave.core.annotations.BeforeStories
import org.jbehave.core.annotations.AfterStories
import state.FailOnAirTranContentOnOrOff

/**
 * Determines if it has been requested that a scenario fail when AirTran content is found in a page and applies the
 * necessary configuration. This is requested by initializing the FAIL_ON_AIRTRAN_CONTENT environmental variable.
 */
class ToggleFailOnAirTranContent {

    def FailOnAirTranContentOnOrOff failOnAirTranContentOnOrOff
    private def failOnAirTranContent = false

    @BeforeStories
    def toggleOnIfRequested() {
      if (System.getProperty("FAIL_ON_AIRTRAN_CONTENT") != null) {
          failOnAirTranContent = true;
          failOnAirTranContentOnOrOff.state = true;
      }
    }

    @AfterStories
    def ensureToggleIsOff() {
      if (failOnAirTranContent) {
        failOnAirTranContent = false
        failOnAirTranContentOnOrOff.state = false;
      }
    }

}
