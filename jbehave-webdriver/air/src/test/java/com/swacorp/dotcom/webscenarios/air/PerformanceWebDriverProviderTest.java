package com.swacorp.dotcom.webscenarios.air;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceWebDriverProviderTest {

    @Test
    public void testEscapeJsonStringForUseInProfile_shouldReplaceQuotesWithEscapedQuotes() {
        String inputJson = "{ \"custom\" : true }";
        String expectedOutput = "{ \\\"custom\\\" : true }";

        String actualOutput = PerformanceWebDriverProvider.escapeJsonStringForUseInProfile(inputJson);

        Assert.assertEquals(expectedOutput, actualOutput);
    }


}
