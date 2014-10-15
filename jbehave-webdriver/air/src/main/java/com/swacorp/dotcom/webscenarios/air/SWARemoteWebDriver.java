package com.swacorp.dotcom.webscenarios.air;

import com.swacorp.selenium.grid.web.api.control.GridCommand;
import com.swacorp.selenium.grid.web.api.control.GridCommandConstants;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.UnreachableBrowserException;
import state.SWAContextView;
import state.UserPageContext;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.*;

/**
 * Date: 5/1/12
 * Southwest customization of web driver for screen shots, command logging, video capture and session validation
 */
public class SWARemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

    private boolean sauceJobEnded = false;
    private String currentStory = SWAContextView.NO_STORY;

    public static final String TOKEN_NULL = "<NUL>";
    public static final String STORY_LOG  = "story.log";
    public static final String VID_CAP = "vid.cap";
    public static final String TRACE_LABEL  = "cmd.trace";
    public static final String TIME_LABEL   = "cmd.time ";
    public static final String MSG_LABEL    = "cmd.msg  ";
    public static final String DELIM_LEFT   = " [ ";
    public static final String DELIM_END = " ]\n";
    public static final String DELIM_RIGHT = " ]";
    public static final String DELIM_VALUE = "=";
    public static final Pattern titlePattern = Pattern.compile("<title>.*</title>");
    private static URL hubLocation;

    static {
        try {
            hubLocation = System.getProperty("REMOTE_WEBDRIVER_URL") != null ? new URL(System.getProperty("REMOTE_WEBDRIVER_URL")) : null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private static final boolean VIDEO_ON = System.getProperty(VID_CAP) != null;
    public static final boolean CMD_TRACE = System.getProperty(STORY_LOG) != null;
    private static final boolean CMD_TIME = CMD_TRACE || System.getProperty(TIME_LABEL) != null;

    private final com.swacorp.dotcom.webscenarios.air.SWASeleniumWebAdminAPI swaSeleniumWebAdminAPI = new com.swacorp.dotcom.webscenarios.air.SWASeleniumWebAdminAPI();

    private StoryCommandLogger storyLogger = null;      //this instance is here for use at shutdown

    static public String screenThreadLabel(){
        String threadName = ""+currentThread().getName();
        return new StringBuilder(threadName.contains("shutdown") ? "shutdown.thread." : "screen.thread.").append(currentThread().getId()).toString();
    }

    public static final StringBuilder createTraceLabel(){
        return createLabel(TRACE_LABEL);
    }
    public static final StringBuilder createMessageLabel(){
        return createLabel(MSG_LABEL);
    }

    private static final StringBuilder createTimeLabel(){
        return createLabel(TIME_LABEL);
    }

    private static StringBuilder createLabel(String tag) {
        return new StringBuilder(Long.toString(System.currentTimeMillis())).append(".").append(tag).append(DELIM_LEFT).append(screenThreadLabel());
    }

    public SWARemoteWebDriver(CommandExecutor commandExecutor, DesiredCapabilities desiredCapabilities) {
        super(commandExecutor, desiredCapabilities);
        final UncaughtExceptionHandler currentUncaughtException = Thread.currentThread().getUncaughtExceptionHandler();
        Thread.currentThread().setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            public void uncaughtException(Thread thread, Throwable throwable) {
                ensureVideoStoppedAndOutputArtifactIndex();
                currentUncaughtException.uncaughtException(thread, throwable);
            }
        });

    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        String base64 = execute(DriverCommand.SCREENSHOT).getValue().toString();
        return target.convertFromBase64Png(base64);
    }

    private static String setupScreenShotPath(UserPageContext currentPageContext){
        Integer currentSeq = currentPageContext.screenShotSeq;
        String currentStoryDir = currentPageContext.currentStory.substring(0, currentPageContext.currentStory.indexOf(".story"));
        String currentScenarioDir = currentPageContext.currentScenario.replace(' ', '_');
        String currentStepDir = currentPageContext.currentStep.replace(' ', '_');
        currentPageContext.screenShotSeq = ++currentSeq;
        return constructPath(currentStoryDir, currentScenarioDir, currentStepDir);
    }
    public static final void savePageSource(String htmlSource){
        String cleanedUpHtml = htmlSource.replaceAll("(?m)^\\s+","");
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        String filePath = setupScreenShotPath(pageContext);
        File pageSourcePath = new File(filePath);
        pageSourcePath.mkdirs();
        File pageSourceFile = new File(pageSourcePath, new StringBuilder(Long.toString(System.currentTimeMillis())).append("-").append(screenThreadLabel()).append("-").append(pageContext.currentStep).append("-page-source").append("-")
                .append(pageContext.screenShotSeq).append(".html").toString());
        try {
            FileWriter fileWriter = new FileWriter(pageSourceFile);
            fileWriter.write(cleanedUpHtml);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void snapScreen() {
        UserPageContext currentPageContext = SWAContextView.retrievePageContext();
        String screenShotPath = setupScreenShotPath(currentPageContext);
        File targetPath = new File(screenShotPath);
        targetPath.mkdirs();
        TakesScreenshot wd = (TakesScreenshot) currentPageContext.webDriverProvider.get();
        File targetScreenShotFile = wd.getScreenshotAs(OutputType.FILE);
        GridCommand result = SWASeleniumWebAdminAPI.storeArtifact(targetScreenShotFile);
        String screenshotLocation = (String) result.resultMap().get(GridCommandConstants.FILE_ACCESS_URL);
        SWARemoteWebDriver swaRemoteWebDriver = wd instanceof SWARemoteWebDriver ? (SWARemoteWebDriver)wd : null;
        if (swaRemoteWebDriver != null) {
            swaRemoteWebDriver.obtainStoryCommandLogger().writeLogMessage(new StringBuilder(SWARemoteWebDriver.createMessageLabel())
                    .append(" Screenshot captured at ")
                    .append(screenshotLocation).append(DELIM_END).toString());
            swaRemoteWebDriver.obtainStoryCommandLogger().artifactIndex.addImageGalleryItem(screenshotLocation);
        }
        targetScreenShotFile.delete();
    }

    protected static final String constructPath(String currentStoryDir, String currentScenarioDir, String currentStepDir) {
        String result = null;
        try {
            result = new File(new StringBuilder(".").append(File.separator).append("target")
                    .append(File.separator).append("jbehave")
                    .append(File.separator).append("screenshots")
                    .append(File.separator).append(currentStoryDir != null ? currentStoryDir : "")
                    .append(File.separator).append(currentScenarioDir != null ? currentScenarioDir : "")
                    .append(File.separator).append(currentStepDir != null ? currentStepDir : "")
                    .append(File.separator).toString()).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String getCurrentUrl() {
        String result = null;
        try {
            result = super.getCurrentUrl();
        } catch (WebDriverException e) {
            System.out.println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getCurrentUrl()");
            result = super.getCurrentUrl();
        }
        return result;
    }
    @Override
    protected void startSession(Capabilities desiredCapabilities) {
        try {
            super.startSession(desiredCapabilities);
            validateSession();
            if (CMD_TRACE) {
                obtainStoryCommandLogger().writeLogMessage(createMessageLabel().append(" Created Session: ").append(getSessionId()).append(" with capabilities : ").append(getCapabilities().toString()).append(DELIM_END).toString());
                obtainStoryCommandLogger().flush();
            }
        } catch (UnreachableBrowserException e) {
            System.out.println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" startSession() due to UnreachableBrowserException");
            retryStartSession(desiredCapabilities);
        } catch (UnsupportedOperationException e) {
            System.out.println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" startSession() due to UnsupportedOperationException");
            retryStartSession(desiredCapabilities);
        } catch (WebDriverException e) {
            System.out.println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" startSession() due to WebDriverException "+e.getMessage());
            retryStartSession(desiredCapabilities);
        }
        if (hubLocation != null && !VIDEO_ON) {
            logRemoteNodeIPAddress();
        }
        if (hubLocation != null && VIDEO_ON) {
            startVideoForSession();
        }
    }

    private void validateSession() {
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        try {
            if (StoryWatchDog.sessionAlreadyAllocated(getSessionId().toString())) {
                pageContext.owningSessionId = null;
                throw new WebDriverException(("Duplicate Session :" + getSessionId().toString() + " Already Allocated!!!!"));
            } else {
                pageContext.owningSessionId = getSessionId().toString();
            }
        } finally {
            SWAContextView.replacePageContext(pageContext);
        }
    }

    private void stopVideoForSession() {
        if(!VIDEO_ON) return;
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        StoryWatchDog.runCommandForPid(pageContext.videoCapturePid);
        pageContext.videoCaptureFilePath = null;
        pageContext.videoCapturePid = null;
        pageContext.videoCaptureHost = null;
        SWAContextView.replacePageContext(pageContext);
    }
    private void logRemoteNodeIPAddress() {
        String jsonString = null;
        try {
            URL sessionHost = com.swacorp.dotcom.webscenarios.air.SWASeleniumWebAdminAPI.sessionHostCommandURL(getSessionId().toString());
            if (sessionHost != null) {
                jsonString = runCommand(sessionHost);
                if (jsonString != null){
                    JSONObject commandResult = new JSONObject(jsonString);
                    obtainStoryCommandLogger().writeLogMessage(createMessageLabel().append(" Session started: ")
                            .append(getSessionId()).append(" at node : ").append(commandResult.get(GridCommandConstants.CMD_SESSION_HOST))
                            .append(":" + commandResult.get(GridCommandConstants.SESSION_PORT)).append(DELIM_END).toString());
                    obtainStoryCommandLogger().flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Unable to logRemoteNodeIPAddress : "+jsonString);
        }
    }
    private void startVideoForSession() {
        try {
            URL videoOn = com.swacorp.dotcom.webscenarios.air.SWASeleniumWebAdminAPI.videoOnCommandURL(getSessionId().toString());
            if (videoOn != null) {
                startVideo(videoOn);
                if (CMD_TRACE) {
                    obtainStoryCommandLogger().writeLogMessage(createMessageLabel().append(" Capturing video on session: ")
                            .append(getSessionId()).append(" at node : ").append(SWAContextView.retrievePageContext().videoCaptureHost)
                            .append(":" + SWAContextView.retrievePageContext().videoCapturePort).append(DELIM_END).toString());
                    obtainStoryCommandLogger().flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void startVideo(URL videoOn) throws IOException, JSONException {
        String jsonString = runCommand(videoOn);
        if (jsonString != null) {
            JSONObject commandResult = new JSONObject(jsonString);
            UserPageContext pageContext = SWAContextView.retrievePageContext();
            pageContext.videoCaptureFilePath = (String) commandResult.get(GridCommandConstants.CAPTURE_PATH);
            pageContext.videoCapturePid = (String) commandResult.get(GridCommandConstants.CAPTURE_PID);
            pageContext.videoCaptureHost = (String) commandResult.get(GridCommandConstants.CMD_SESSION_HOST);
            pageContext.videoCapturePort = Integer.toString((Integer)commandResult.get(GridCommandConstants.SESSION_PORT));
            StoryWatchDog.addCommand(pageContext.videoCapturePid, swaSeleniumWebAdminAPI.createVideoShutdownCommand(this));
        } else {
            System.out.println("######### UNKNOWN RESULT FOR VIDEO START ########");
        }
    }

    public static String runCommand(URL gridCommand) throws IOException {
        URLConnection hubConnection = gridCommand.openConnection();
        BufferedReader bufferedReader = new BufferedReader((new InputStreamReader((hubConnection.getInputStream()))));
        StringBuilder jsonStringBuilder = new StringBuilder(""+bufferedReader.readLine());
        String inputLine = null;
        while ((inputLine = bufferedReader.readLine()) != null) {
            jsonStringBuilder.append(inputLine);
        }
        bufferedReader.close();
        return jsonStringBuilder.toString();
    }

    private void retryStartSession(Capabilities desiredCapabilities) {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.startSession(desiredCapabilities);
        validateSession();
    }

    private String retrieveParameterString(Map<String, ?> parameters) {
        Set<String> parameterKeys = parameters.keySet();
        Iterator<String> keyIterator = parameterKeys.iterator();
        StringBuilder result = new StringBuilder("");
        result.append(DELIM_LEFT);

        while(keyIterator.hasNext()){
            String keyString = keyIterator.next();
            Object valueObject = parameters.get(keyString);
            String valueString = !(valueObject instanceof CharSequence[]) ? valueObject.toString() : retrieveStringFromCharSequenceArray((CharSequence[]) valueObject);
            result.append(keyString).append(DELIM_VALUE).append(valueString).append(" ");
        }
        return result.append(DELIM_RIGHT).toString();
    }

    private String retrieveResponseString(Response r) {
        StringBuilder result = new StringBuilder(DELIM_LEFT).append(" sess=").append(r.getSessionId()).append(" status=").append(r.getStatus()).append(" value=");
        StringBuilder valueStringBuilder = new StringBuilder(DELIM_LEFT);
        Object valueObject = r.getValue();
        if(valueObject != null){
           formatValueObject(valueStringBuilder, valueObject);
        } else {
           valueStringBuilder.append(TOKEN_NULL);
        }
        valueStringBuilder.append(DELIM_RIGHT);
        result.append(valueStringBuilder.toString());
        return result.append(DELIM_RIGHT).toString();
    }

    private void formatValueObject(StringBuilder valueStringBuilder, Object valueObject) {
        if (valueObject instanceof ArrayList) {
            formatListAsStringBuilder((ArrayList) valueObject, valueStringBuilder);
        } else if (valueObject instanceof RemoteWebElement){
            valueStringBuilder.append("webElement(s) : [").append(" id=").append(((RemoteWebElement) valueObject).getId());
        } else {// it is something other than a list
            valueStringBuilder.append(valueObject.toString());
        }
    }

    private void formatListAsStringBuilder(ArrayList valueObject, StringBuilder valueStringBuilder) {
        ArrayList valueList = (ArrayList) valueObject;
        if (!valueList.isEmpty() && valueList.get(0) instanceof RemoteWebElement) {
            List<RemoteWebElement> elements = valueList;
            valueStringBuilder.append("webElement(s) : [");
            for (RemoteWebElement e : elements) {
                valueStringBuilder.append(" id=").append(e.getId());
            }
        } else { // its a list of something other than web elements
            valueStringBuilder.append("List<").append(valueList.size() > 0 ? valueList.get(0).getClass().getName() : "empty").append(">(").append(valueList.size()).append(")");
        }
    }

    private String retrieveStringFromCharSequenceArray(CharSequence[] s){
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < s.length ; i++) {
            result.append(s[i].toString().replace(Keys.TAB, "<TAB>").replace(Keys.ESCAPE, "<ESC>").replace(Keys.DELETE, "<DEL>").replace(Keys.CONTROL, "<CTL>").replace(Keys.NULL,TOKEN_NULL));
        }
        return result.toString();
    }
    @Override
    protected Response execute(String driverCommand, Map<String, ?> parameters) {
        boolean storyOver = false;
        SessionId sessionId = getSessionId();
        String parameterString = parameters.isEmpty() ? "" : this.retrieveParameterString(parameters);
        if (sauceJobEnded) {
            throw new RemoteWebDriverProvider.SauceLabsJobHasEnded();
        }
        try {
            long start = System.currentTimeMillis();
            if(CMD_TRACE)
                obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTraceLabel().append(".").append(sessionId)
                        .append(" cmdExec ").append(" cmd=").append(driverCommand)
                        .append(parameterString).append(" on webdriver : ").append(System.identityHashCode(this))
                        .append(DELIM_END).toString());

            Response response = super.execute(driverCommand, parameters);

            if(CMD_TRACE && "screenshot".equals(driverCommand))//no need to display encoded screen string
                obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTraceLabel().append(".").append(sessionId).append(" cmdResp ")
                        .append(" response=<base64 encoded binary screen image>").append(DELIM_END).toString());
            else if(CMD_TRACE && "getPageSource".equals(driverCommand)) { //get rid of blank lines to reduce trace output
                String page=response.getValue().toString().replaceAll("(?m)^\\s+","");
                Matcher m = titlePattern.matcher(page);
                String pageTitle= m.find() ? m.group() : "page title not found";
                obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTraceLabel().append(".").append(sessionId).append(" cmdResp ")
                        .append(" title=").append(pageTitle).append(DELIM_END).toString());
            }
            else if(CMD_TRACE){//otherwise lets see what we got
                obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTraceLabel().append(".").append(sessionId).append(" cmdResp ")
                        .append(" response=").append(retrieveResponseString(response)).append(DELIM_END).toString());
            }
            if (CMD_TIME)
                obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTimeLabel().append(".").append(sessionId).append(" cmdTime ")
                        .append(" duration=").append(System.currentTimeMillis() - start)
                        .append(" milliseconds cmd=").append(driverCommand).append(DELIM_END).toString());

            if ("quit".equals(driverCommand)){
                storyOver = true;
                StoryWatchDog.sessionEnded(SWAContextView.retrievePageContext().owningSessionId);
             }
            obtainStoryCommandLogger().flush();
            return response;

        } catch (WebDriverException e) {
            obtainStoryCommandLogger().outputLogMessageWithScenarioSteps(createTraceLabel().append(".").append(sessionId).append(" cmdExcp ")
                    .append(" msg=").append(e.getMessage()).append(DELIM_END).toString());

            if (e.getMessage().contains("Job on Sauce is already complete")) {
                sauceJobEnded = true;
                throw new RemoteWebDriverProvider.SauceLabsJobHasEnded();
            }
            throw e;
        } catch (RuntimeException e) {
            storyOver = true;
            System.out.println("Caught RuntimeException running story...");
            throw e;
        } catch (Exception e){
            StoryWatchDog.sessionEnded(SWAContextView.retrievePageContext().owningSessionId);
            storyOver = true;
            System.out.println("Caught Exception running story...");
            throw new RuntimeException("wtf",e);
        } finally {
            if(CMD_TRACE || CMD_TIME){
                obtainStoryCommandLogger().flush();
            }
            if(VIDEO_ON && storyOver){
                System.out.println("Stopping video for story...");
                ensureVideoStoppedAndOutputArtifactIndex();
                resetLog();
            } else if(storyOver && CMD_TRACE) {
                obtainStoryCommandLogger().outputArtifactsIndexPage();
                resetLog();
            }
        }
    }

    private void resetLog() {
        this.storyLogger = null;
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        pageContext.storyCommandLogger = null;
        SWAContextView.replacePageContext(pageContext);
    }

    public void ensureVideoStoppedAndOutputArtifactIndex() {//TODO create general end of story processing
        if(SWAContextView.retrievePageContext().videoCapturePid != null){
            stopVideoForSession();
        }
    }

    public StoryCommandLogger obtainStoryCommandLogger() {
        if (this.storyLogger == null) {
            if (SWAContextView.retrievePageContext().storyCommandLogger == null) {
                storyLogger = new StoryCommandLogger();
                storyLogger.initialize();
                UserPageContext pageContext = SWAContextView.retrievePageContext();
                pageContext.storyCommandLogger = storyLogger;
                SWAContextView.replacePageContext(pageContext);
            } else {
                this.storyLogger = SWAContextView.retrievePageContext().storyCommandLogger;
            }

        }
        return storyLogger;
    }

}