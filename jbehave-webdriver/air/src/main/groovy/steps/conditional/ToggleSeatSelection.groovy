package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import util.ToggleJmx
import org.jbehave.core.annotations.BeforeStories

class ToggleCR1SeatSelection {

    private static boolean seatSelectionButtonToggle = false
    private static boolean originalToggle = false
    public static final String TOGGLE_NAME = 'CR1_SEAT_SELECTION'
    public static final String TOGGLE_NAME_OFF = 'CR1_SEAT_SELECTION_OFF'

    public static boolean isOn() {
        return System.getProperty(TOGGLE_NAME) != null
    }

    public static boolean isOff() {
        return System.getProperty(TOGGLE_NAME_OFF) != null
    }


    @BeforeStories
    def seatSelectionOn() {
        if (isOn()){
            cr1SeatSelectionToggleOn()
        } else if (isOff()) {
            cr1SeatSelectionToggleOff()
        }
    }

    @Given("CR1 Seat Selection is turned off")
    @When("CR1 Seat Selection is turned off")
    def cr1SeatSelectionToggleOff() {
        originalToggle = new ToggleJmx().isOn(TOGGLE_NAME)
        new ToggleJmx().toggleOff(TOGGLE_NAME, 'WEB')
        new ToggleJmx().toggleOff(TOGGLE_NAME, 'SERVICE')
        println "On Thread: " + Thread.currentThread().getId() + ", Toggle Off " + TOGGLE_NAME + ". On by default: " + originalToggle
        seatSelectionButtonToggle = true
    }


    @Given("CR1 Seat Selection is turned on")
    @When("CR1 Seat Selection is turned on")
    def cr1SeatSelectionToggleOn() {
        originalToggle = new ToggleJmx().isOn(TOGGLE_NAME)
        new ToggleJmx().toggleOn(TOGGLE_NAME, 'WEB')
        new ToggleJmx().toggleOn(TOGGLE_NAME, 'SERVICE')
        println "On Thread: " + Thread.currentThread().getId() + ", Toggle On " + TOGGLE_NAME + ". On by default: " + originalToggle
        seatSelectionButtonToggle = true
    }

    @AfterStories
    def toggle_on_if_on() {
        if (seatSelectionButtonToggle) {
            println "On Thread: " + Thread.currentThread().getId() + ", Toggle " + TOGGLE_NAME + " return to default " + (originalToggle ? "On" : "Off")
            if (originalToggle) {
                new ToggleJmx().toggleOn(TOGGLE_NAME, 'WEB')
                new ToggleJmx().toggleOn(TOGGLE_NAME, 'SERVICE')
            } else {
                new ToggleJmx().toggleOff(TOGGLE_NAME, 'WEB')
                new ToggleJmx().toggleOff(TOGGLE_NAME, 'SERVICE')
            }
        }
    }
}
