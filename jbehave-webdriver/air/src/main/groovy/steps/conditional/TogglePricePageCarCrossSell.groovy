/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package steps.conditional

import org.jbehave.core.annotations.BeforeStories

import util.ToggleJmx

class TogglePricePageCarCrossSell {

	/*
	 * If a test depends on the PRICE_PAGE_CAR_CROSS_SELL toggle, then the job who launches the
	 * test should explicitly set either PRICE_PAGE_CAR_CROSS_SELL_ON or else PRICE_PAGE_CAR_CROSS_SELL_OFF
	 * before executing the test.
	 */
	@BeforeStories
	def checkIfShouldForcePricePageCarCrossSellToggle() {
		maybeSetPricePageCarCrossSellOff()
        maybeSetPricePageCarCrossSellOn()
	}

	def maybeSetPricePageCarCrossSellOn() {
		String mustBeOn = System.getProperty("PRICE_PAGE_CAR_CROSS_SELL_ON")
		if (mustBeOn != null) {
			new ToggleJmx().toggleOn('PRICE_PAGE_CAR_CROSS_SELL', 'WEB')
		}
	}

	def maybeSetPricePageCarCrossSellOff() {
		String mustBeOff = System.getProperty("PRICE_PAGE_CAR_CROSS_SELL_OFF")
		if (mustBeOff != null) {
			new ToggleJmx().toggleOff('PRICE_PAGE_CAR_CROSS_SELL', 'WEB')
		}
	}

}
