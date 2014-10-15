package steps.conditional

/**
 * This class is created in order to put on maintenance mode the Enroll page.
 */

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

class ToggleRREnrollMaintenance {

    private boolean rrEnrollMaintenanceOn

    @BeforeStories
    def toggleRREnrollMaintenanceOn() {
        String maintenanceFeature = System.getProperty("RR_ENROLL_MAINTENANCE_ON")
        if (maintenanceFeature != null) {
            new ToggleJmx().maintenanceToggles("setRREnrollMaintenanceMode",true)
            rrEnrollMaintenanceOn = true;
        }
    }

    @AfterStories
    def toggleRREnrollMaintenanceOff() {
        if (rrEnrollMaintenanceOn) {
            new ToggleJmx().maintenanceToggles("setRREnrollMaintenanceMode",false)
        }
    }

}
