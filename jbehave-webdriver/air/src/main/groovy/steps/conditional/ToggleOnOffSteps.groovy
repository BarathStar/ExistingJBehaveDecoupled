package steps.conditional

import org.jbehave.core.annotations.AfterScenario
import org.jbehave.core.annotations.Given

import util.ToggleJmx
import org.jbehave.core.annotations.BeforeStories

class ToggleOnOffSteps {

    private boolean odiCheckinOn
    private boolean aggressiveConfirmationOn
    private boolean aggressiveCarSearch
    private boolean testAndTargetOff
    private boolean postLoyaltyToggleOnByDefaullt
    private boolean rrSignUpOnPurchaseAndConfirmation
    private boolean rrSignUpOnConfirmation
    private boolean homepage2

    private boolean defaultRRSignUpOnPurchaseAndConfirmation
    private boolean defaultRRSignUpOnConfirmation
    private boolean defaultHomepage2

    @Given("The Trip Management toggle is Off")
    def tripManagmentToggleOff() {
        if (new ToggleJmx().isOn("TRIP_MANAGEMENT", 'WEB')) {
            new ToggleJmx().toggleOff("TRIP_MANAGEMENT", 'WEB')
        }
    }

    @Given("ODI Checkin On")
    def odiCheckinOn() {
        new ToggleJmx().toggleOn('ODI_ITINERARY_CHECKIN', 'WEB')
        odiCheckinOn = true;
    }

    @Given("Aggressive car search page On")
    def aggressiveCarSearchOn() {
        new ToggleJmx().toggleOn('AGGRESSIVE_CONFIRMATION_PAGE_CAR_SEARCH', 'WEB')
        aggressiveCarSearch = true;
    }


    @Given("the Test and Target toggle is Off")
    def testAndTargetOff() {
        new ToggleJmx().toggleOff('TEST_AND_TARGET', 'WEB')
        testAndTargetOff = true;
    }

    @Given("the RR Sign Up on Purchase Page and Confirmation Page toggle is \$value")
    def rrSignUpOnPurchaseAndConfirmationToggle(String value) {
        defaultRRSignUpOnPurchaseAndConfirmation = new ToggleJmx().isOn('RR_SIGN_UP_ON_PURCHASE_AND_CONFIRMATION', 'WEB')
        rrSignUpOnPurchaseAndConfirmation = defaultRRSignUpOnPurchaseAndConfirmation;

        if(value == "ON" && rrSignUpOnPurchaseAndConfirmation != true) {
            new ToggleJmx().toggleOn("RR_SIGN_UP_ON_PURCHASE_AND_CONFIRMATION", "WEB")
            rrSignUpOnPurchaseAndConfirmation = true;
        }
        else if(value == "OFF" && rrSignUpOnPurchaseAndConfirmation != false) {
            new ToggleJmx().toggleOff("RR_SIGN_UP_ON_PURCHASE_AND_CONFIRMATION", "WEB")
            rrSignUpOnPurchaseAndConfirmation = false;
        }
    }

    @Given("the RR Sign Up on Confirmation Page toggle is \$value")
    def rrSignUpOnConfirmationToggle(String value) {
        defaultRRSignUpOnConfirmation = new ToggleJmx().isOn('RR_SIGN_UP_ON_CONFIRMATION', 'WEB')
        rrSignUpOnConfirmation = defaultRRSignUpOnConfirmation;

        if(value == "ON" && rrSignUpOnConfirmation != true) {
            new ToggleJmx().toggleOn("RR_SIGN_UP_ON_CONFIRMATION", "WEB")
            rrSignUpOnConfirmation = true;
        }
        else if(value == "OFF" && rrSignUpOnConfirmation != false) {
            new ToggleJmx().toggleOff("RR_SIGN_UP_ON_CONFIRMATION", "WEB")
           rrSignUpOnConfirmation = false;
        }
    }

    @Given("The Homepage2 toggle is \$value")
    def homepage2(String value) {
        defaultHomepage2 = new ToggleJmx().isOn('HOMEPAGE_2', 'WEB')
        homepage2 = defaultHomepage2;

        if(value == "ON" && homepage2 != true) {
            new ToggleJmx().toggleOn("HOMEPAGE_2", "WEB")
            homepage2 = true;
        }
        else if(value == "OFF" && homepage2 != false) {
            new ToggleJmx().toggleOff("HOMEPAGE_2", "WEB")
            homepage2 = false;
        }
    }

    @AfterScenario
    def toggle_off_if_on() {
        if (odiCheckinOn) {
            new ToggleJmx().toggleOff('ODI_ITINERARY_CHECKIN', 'WEB')
        }
        if (aggressiveCarSearch) {
            new ToggleJmx().toggleOff('AGGRESSIVE_CONFIRMATION_PAGE_CAR_SEARCH', 'WEB')
        }
        if (postLoyaltyToggleOnByDefaullt) {
            new ToggleJmx().toggleOff('POST_LOYALTY_LAYOUT', 'WEB')
        }
        if (testAndTargetOff) {
            new ToggleJmx().toggleOn('TEST_AND_TARGET', 'WEB')
        }
        if(rrSignUpOnPurchaseAndConfirmation != defaultRRSignUpOnPurchaseAndConfirmation) {
            if(rrSignUpOnPurchaseAndConfirmation) {
                new ToggleJmx().toggleOff("RR_SIGN_UP_ON_PURCHASE_AND_CONFIRMATION","WEB")
            }
            else {
                new ToggleJmx().toggleOn("RR_SIGN_UP_ON_PURCHASE_AND_CONFIRMATION","WEB")
            }
        }
        if(rrSignUpOnConfirmation != defaultRRSignUpOnConfirmation) {
            if(rrSignUpOnConfirmation) {
                new ToggleJmx().toggleOff("RR_SIGN_UP_ON_CONFIRMATION", "WEB")
            }
            else {
                new ToggleJmx().toggleOn("RR_SIGN_UP_ON_CONFIRMATION", "WEB")
            }
        }
        if(homepage2 != defaultHomepage2) {
            if(homepage2) {
                new ToggleJmx().toggleOff("HOMEPAGE_2", "WEB")
            }
            else {
                new ToggleJmx().toggleOn("HOMEPAGE_2", "WEB")
            }
        }
    }
}
