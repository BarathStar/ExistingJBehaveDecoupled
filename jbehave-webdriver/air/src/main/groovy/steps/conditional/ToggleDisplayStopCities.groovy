package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

/**
 * This class is created in order to toggle the new implementation for the stop cities info
  */
class ToggleDisplayStopCities {

    private boolean displayStopCitiesToggleOn

    @BeforeStories
    def displayStopCitiesToggleOn() {
        String service = System.getProperty("DISPLAY_STOP_CITIES")
        if (service != null) {
            new ToggleJmx().toggleOn('DISPLAY_STOP_CITIES', 'WEB')
            new ToggleJmx().toggleOn('DISPLAY_STOP_CITIES', 'SERVICE')
            displayStopCitiesToggleOn = true;
        }
    }

    @AfterStories
    def toggle_off_if_on() {
        if (displayStopCitiesToggleOn) {
            new ToggleJmx().toggleOff('DISPLAY_STOP_CITIES', 'WEB')
            new ToggleJmx().toggleOff('DISPLAY_STOP_CITIES', 'SERVICE')
        }
    }
}