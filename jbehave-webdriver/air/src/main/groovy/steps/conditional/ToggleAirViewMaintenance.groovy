package steps.conditional

import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories


class ToggleAirViewMaintenance {
    private boolean airViewMaintenanceOn

    @BeforeStories
    def toggleAirViewMaintenanceOn() {
        String maintenanceFeature = System.getProperty("AIR_VIEW_MAINTENANCE_ON")
        if (maintenanceFeature != null) {
            new ToggleJmx().maintenanceToggles("setAirViewMaintenanceMode",true)
            airViewMaintenanceOn = true;
        }
    }

    @AfterStories
    def toggleRREditMaintenanceOff() {
        if (airViewMaintenanceOn) {
            new ToggleJmx().maintenanceToggles("setAirViewMaintenanceMode",false)
        }
    }
}
