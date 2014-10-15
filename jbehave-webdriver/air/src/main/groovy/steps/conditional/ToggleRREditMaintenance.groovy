package steps.conditional

/**
 * This class is created in order to put in maintenance mode the RR Account edit feature.
 */

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleRREditMaintenance {

    private boolean rrEditMaintenanceOn

    @BeforeStories
    def toggleRREditMaintenanceOn() {
        String maintenanceFeature = System.getProperty("RR_EDIT_MAINTENANCE_ON")
        if (maintenanceFeature != null) {
            new ToggleJmx().maintenanceToggles("setRREditMaintenanceMode",true)
            rrEditMaintenanceOn = true;
        }
    }

    @AfterStories
    def toggleRREditMaintenanceOff() {
        if (rrEditMaintenanceOn) {
            new ToggleJmx().maintenanceToggles("setRREditMaintenanceMode",false)
        }
    }

}
