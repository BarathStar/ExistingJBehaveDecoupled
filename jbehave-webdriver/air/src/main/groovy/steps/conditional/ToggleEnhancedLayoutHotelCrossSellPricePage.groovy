/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package steps.conditional

import org.jbehave.core.annotations.BeforeStories;
import util.ToggleJmx

class ToggleEnhancedLayoutHotelCrossSellPricePage {

     /*
     * If a test depends on the ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE toggle, then the job who launches the
     * test should explicitly set either ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE_ON or else ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE_OFF
     * before executing the test.
     */

    @BeforeStories
    def checkIfShouldForcePricePageHotelCrossSellToggle() {
        maybeSetPricePageHotelCrossSellOff()
        maybeSetPricePageHotelCrossSellOn()
    }

    def maybeSetPricePageHotelCrossSellOn() {
        String mustBeOn = System.getProperty("ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE_ON")
        if (mustBeOn != null) {
            new ToggleJmx().toggleOn('ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE', 'WEB')
        }
    }

    def maybeSetPricePageHotelCrossSellOff() {
        String mustBeOff = System.getProperty("ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE_OFF")
        if (mustBeOff != null) {
            new ToggleJmx().toggleOff('ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE', 'WEB')
        }
    }
}
