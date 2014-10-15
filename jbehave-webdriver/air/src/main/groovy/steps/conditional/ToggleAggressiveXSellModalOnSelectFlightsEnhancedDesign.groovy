
/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package steps.conditional

import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx

class ToggleAggressiveXSellModalOnSelectFlightsEnhancedDesign {
    /*
     * If a test depends on the AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN toggle, then the job who launches the
     * test should explicitly set either AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN_ON or else
     * AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN_OFF before executing the test.
     */

    @BeforeStories
    def checkIfShouldForceAggressiveXSellModalEnhancedDesignToggle() {
        maybeSetAggressiveXSellModalEnhancedDesignOff()
        maybeSetAggressiveXSellModalEnhancedDesignOn()
    }

    def maybeSetAggressiveXSellModalEnhancedDesignOn() {
        String mustBeOn = System.getProperty("AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN_ON")
        if (mustBeOn != null) {
            new ToggleJmx().toggleOn('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN', 'WEB')
        }
    }

    def maybeSetAggressiveXSellModalEnhancedDesignOff() {
        String mustBeOff = System.getProperty("AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN_OFF")
        if (mustBeOff != null) {
            new ToggleJmx().toggleOff('AGGRESSIVE_XSELL_MODAL_ON_SELECT_FLIGHTS_ENHANCED_DESIGN', 'WEB')
        }
    }

}
