package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.Given
import util.ToggleJmx
import org.jbehave.core.annotations.When
import state.NavitaireOnorOff
import org.jbehave.core.annotations.Then

class ToggleNavitaireServiceMaintenanceMode {

    private static boolean navitaireMaintenanceModeOn

    def NavitaireOnorOff navitaireOnorOff;

    @Given("Navitaire service maintenance is turned off")
    @When("Navitaire service maintenance is turned off")
    def navitaireMaintenanceModeToggleOff() {
        println( "turning Navitaire MX Mode OFF")
        new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'WEB')
        new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'SERVICE')
        navitaireMaintenanceModeOn = false
        navitaireOnorOff.state = false
    }


    @Given("Navitaire service maintenance is turned on")
    @When("Navitaire service maintenance is turned on")
    def navitaireMaintenanceModeToggleOn() {
        println( "turning Navitaire MX Mode ON")
        new ToggleJmx().toggleOn('NAVITAIRE_MAINTENANCE_MODE', 'WEB')
        new ToggleJmx().toggleOn('NAVITAIRE_MAINTENANCE_MODE', 'SERVICE')
        navitaireMaintenanceModeOn = true
        navitaireOnorOff.state = true
    }

    @AfterStories
    def toggle_off_if_on() {
        if (navitaireMaintenanceModeOn) {
            println( "turning Navitaire MX Mode OFF in Afterstories")

            new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'WEB')
            new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'SERVICE')
        }
    }

    @Then("Navitaire service maintenance is turned off")
    def toggleMxModeOffIfOn() {
        if (navitaireMaintenanceModeOn) {
            println( "turning Navitaire MX Mode OFF in THEN")
            new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'WEB')
            new ToggleJmx().toggleOff('NAVITAIRE_MAINTENANCE_MODE', 'SERVICE')
        }
    }
}
