/*
* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.
*/
package pages.elements

import fixtures.air.ReservationSpecification
import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.BasePage
import util.Station

public class StopLogicInfo extends BasePage {

    private static final String STOP_LOGIC_TEXT = "Stops:"
    private static final String CHANGE_TEXT = "Change"
    private static final String NONSTOP_TEXT = "Nonstop"
    private static final String ONE_STOP_TEXT = "stop"
    private static final String MANY_STOP_TEXT = "stops"
    private static final String INCLUDES_TEXT = "includes"
    private static final String PLANE_CHANGE_TEXT = "plane change"
    private static final String NO_PLANE_CHANGE_TEXT = "no plane change"
    private static final String COMMA_AND_SPACE = ", "
    private static final String SPACE = " "

    public StopLogicInfo(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    String getRelativePath() {
        return ""
    }

    def verifyStopLogicAndPlaneChangeCount(By stopsCountContainer, int notConnectingStopsCount, int connectingStopsCount){
        String stopCountInfoText = buildStopsCountText(notConnectingStopsCount, connectingStopsCount)
        verifyTextPresent(stopCountInfoText, stopsCountContainer)
    }

    private String buildStopsCountText(int notConnectingStopsCount, int connectingStopsCount) {
        StringBuilder stopCountText = new StringBuilder()

        int stopsCount = notConnectingStopsCount + connectingStopsCount
        if (stopsCount == 0) {
            stopCountText.append(NONSTOP_TEXT)
            return stopCountText.toString()
        } else if (stopsCount == 1) {
            stopCountText.append(stopsCount.toString()).append(SPACE).append(ONE_STOP_TEXT).append(COMMA_AND_SPACE)
        } else {
            stopCountText.append(stopsCount.toString()).append(SPACE).append(MANY_STOP_TEXT).append(COMMA_AND_SPACE)
        }

        if (connectingStopsCount > 0) {
            stopCountText.append(INCLUDES_TEXT).append(SPACE).append(connectingStopsCount.toString()).append(SPACE).append(PLANE_CHANGE_TEXT)
        } else {
            stopCountText.append(NO_PLANE_CHANGE_TEXT)
        }

        return stopCountText.toString()
    }
}