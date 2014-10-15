package state

import com.swacorp.dotcom.webscenarios.air.StoryCommandLogger
import org.jbehave.web.selenium.ContextView
import org.jbehave.web.selenium.WebDriverProvider
import pages.ArtifactIndex

public final class UserPageContext {

    public final long owningThreadId = Thread.currentThread().id

//    public ContextView swaGridContextView = null
    public WebDriverProvider webDriverProvider

    public Integer screenShotSeq = 0
    public String currentStory = SWAContextView.NO_STORY
    public String currentScenario = SWAContextView.NO_SCENARIO
    public String currentStep =  SWAContextView.NO_STEP
    public String currentTitle = ""
    public Boolean contextClosed = Boolean.FALSE

    public StoryCommandLogger storyCommandLogger = null;

    public String videoCapturePid      = null;
    public String videoCaptureURL = null;
    public String videoCaptureFilePath = null;
    public String videoCaptureHost = null;
    public String owningSessionId = null; //used to ensure we do not wind up with duplicate sessions

    public String videoCapturePort ;
    public Stack<Boolean> isGivenStory = new Stack<Boolean>()
}
