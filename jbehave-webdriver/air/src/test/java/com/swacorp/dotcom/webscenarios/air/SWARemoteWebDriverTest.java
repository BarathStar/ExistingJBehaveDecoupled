package com.swacorp.dotcom.webscenarios.air;

import org.jbehave.web.selenium.TypeWebDriverProvider;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.ArtifactIndex;
import state.SWAContextView;
import state.UserPageContext;

import static org.mockito.Mockito.mock;
import static state.SWAContextView.replacePageContext;
import static state.SWAContextView.retrievePageContext;

public class SWARemoteWebDriverTest {

    String propertyString = System.setProperty("dry-run", "true");

    @Test
    public void artifactIndexTest() {

        final UserPageContext userPageContext = SWAContextView.retrievePageContext();
        final String anchorHref = "http://google.com";
        final String anchorText = "google";
        final String closingBodyTag = "</body>";
        final String h1Tag = "<h1>";

        userPageContext.currentStory = "testStory";
        userPageContext.currentScenario = "testScenario";
        userPageContext.currentStep = "testStep";

        replacePageContext(userPageContext);

        ArtifactIndex basicHtmlPage = new ArtifactIndex(userPageContext.currentStory);
        basicHtmlPage.addContentLink(anchorHref, anchorText);

        String pageData = basicHtmlPage.toString();

        Assert.assertTrue("Title should be story", pageData.contains(userPageContext.currentStory));
        Assert.assertTrue("h1 tag missing "+h1Tag, pageData.contains(h1Tag));
        Assert.assertTrue("anchor href should be "+anchorHref, pageData.contains(anchorHref));
        Assert.assertTrue("anchor text should be "+anchorText, pageData.contains(anchorText));
        Assert.assertTrue("Closing body tag missing "+closingBodyTag, pageData.contains(closingBodyTag));

        replacePageContext(null);

    }

    private SWARemoteWebDriver createWebDriver(final CommandExecutor commandExecutor, final DesiredCapabilities desiredCapabilities) {
        return new SWARemoteWebDriver(commandExecutor, desiredCapabilities) {
            @Override
            protected void startSession(Capabilities desiredCapabilities) {
            }
        };
    }

}
