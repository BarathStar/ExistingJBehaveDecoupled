package state

import org.jbehave.web.selenium.ContextView
import org.jbehave.web.selenium.WebDriverProvider
import org.jbehave.web.selenium.LocalFrameContextView
import org.jbehave.web.selenium.TypeWebDriverProvider
import com.swacorp.dotcom.webscenarios.air.AirStories
import org.openqa.selenium.WebDriver
import org.openqa.selenium.android.library.DriverProvider

public final class SWAContextView implements ContextView {
    private static final ThreadLocal<UserPageContext> currentPageContextThreadLocal = new ThreadLocal<UserPageContext>()
    public static final String NO_STORY = "None";
    public static final String NO_SCENARIO = "None";
    public static final String NO_STEP = "None";
    public static final String DEFAULT_SCENARIO = "currentScenario";
    public static final String DEFAULT_STEP = "currentStep";


    private static SWAContextView swaGridContextViewInstance
    private WebDriverProvider webDriverProvider
    private LocalFrameContextView localFrameContextView
    public boolean usingLocalScreen = false

    SWAContextView(){
        currentPageContextThreadLocal.set(new UserPageContext())
    }

    SWAContextView(WebDriverProvider w){
        this()
        webDriverProvider = w
    }

    def initialize() {
        swaGridContextViewInstance = this
        UserPageContext pageContext = retrievePageContext()
        pageContext.webDriverProvider = webDriverProvider
//        pageContext.swaGridContextView = swaGridContextViewInstance as ContextView
        replacePageContext(pageContext)
        if(usingLocalScreen){
          localFrameContextView = new LocalFrameContextView().sized(500, 100);
        }
    }

    @Override
    void show(String scenario, String step) {
        UserPageContext currentPageContext = retrievePageContext()
        currentPageContext.currentScenario = scenario != null ? scenario : DEFAULT_SCENARIO
        currentPageContext.currentStep = step != null ? step : DEFAULT_STEP
        replacePageContext(currentPageContext)
        if(usingLocalScreen){
           localFrameContextView.show(scenario,step)
        }
    }

    @Override
    void close() {
        UserPageContext currentPageContext = retrievePageContext()
        currentPageContext.contextClosed = Boolean.TRUE
        replacePageContext(currentPageContext)
        if(usingLocalScreen){
           localFrameContextView.close()
        }
    }

    public static final UserPageContext retrievePageContext() {
        UserPageContext currentPageContext = currentPageContextThreadLocal.get()
        if(null == currentPageContext){
            currentPageContext = new UserPageContext()//swagridContextViewInstance should never be null...
            currentPageContext.webDriverProvider = swaGridContextViewInstance != null ? swaGridContextViewInstance.webDriverProvider : new TypeWebDriverProvider(AirStories.DryRunWebDriver.class)
        }
        assert (currentPageContext.owningThreadId == Thread.currentThread().id) : "currentPageContext.owningThreadId != Thread.currentThread().id"
        return currentPageContext
    }
    public static final void replacePageContext(UserPageContext c) {
        currentPageContextThreadLocal.set(c)
    }
    public static final void removePageContext() {
        currentPageContextThreadLocal.remove()
    }
}
