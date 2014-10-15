package com.swacorp.dotcom.webscenarios.air;


import com.swacorp.selenium.grid.web.api.control.GridCommand;
import com.swacorp.selenium.grid.web.api.control.GridCommandConstants;
import com.swacorp.selenium.grid.web.api.control.GridCommandType;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.SessionId;
import pages.ArtifactIndex;
import state.SWAContextView;
import state.UserPageContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SWASeleniumWebAdminAPI {
    public static URL hubLocation;// initialized by SWARemoteWebDriver

    static {
        try {
            com.swacorp.dotcom.webscenarios.air.SWASeleniumWebAdminAPI.hubLocation = System.getProperty("REMOTE_WEBDRIVER_URL") != null ? new URL(System.getProperty("REMOTE_WEBDRIVER_URL")) : null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public SWASeleniumWebAdminAPI() {
    }

    public static GridCommand storeArtifact(File artifactSourceLocation) {
        GridCommand storeArtifact = GridCommandType.STORE_ARTIFACT.instantiateSessionCommand();
        Map<String,Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(GridCommandConstants.SOURCE_ARTIFACT, artifactSourceLocation);
        parameterMap.put(GridCommandConstants.S3_TARGET_KEY,"screenshots/");
        storeArtifact.addParameters(parameterMap);
        storeArtifact.init();
        storeArtifact.exec();
        return storeArtifact;
    }
    public static URL videoShutdownCommandURL() {
        URL result = null;
        try {
            UserPageContext pageContext = SWAContextView.retrievePageContext();
            String videoShutdownURL = new StringBuilder(createAdminUrl())
                    .append(GridCommandConstants.SESSION_SERVLET)
                    .append(GridCommandConstants.CMD_SHUTDOWN_VIDEO)
                    .append('&').append(GridCommandConstants.CMD_SESSION_HOST).append('=').append(pageContext.videoCaptureHost)
                    .append('&').append(GridCommandConstants.CAPTURE_PID).append('=').append(pageContext.videoCapturePid)
                    .append('&').append(GridCommandConstants.CAPTURE_PATH).append('=').append(pageContext.videoCaptureFilePath).toString();

            result = new URL(videoShutdownURL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static URL videoOffCommandURL() {
        URL result = null;
        try {
            UserPageContext pageContext = SWAContextView.retrievePageContext();
            String videoOffURL = new StringBuilder(createAdminUrl())
                    .append(GridCommandConstants.SESSION_SERVLET)
                    .append(GridCommandConstants.CMD_STOP_VIDEO)
                    .append('&').append(GridCommandConstants.CMD_SESSION_HOST).append('=').append(pageContext.videoCaptureHost)
                    .append('&').append(GridCommandConstants.CAPTURE_PID).append('=').append(pageContext.videoCapturePid).toString();

            result = new URL(videoOffURL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static URL sessionHostCommandURL(String sessXid) {
        URL result = null;
        String urlString = new StringBuilder(createAdminUrl())
                .append(GridCommandConstants.SESSION_SERVLET)
                .append(GridCommandConstants.CMD_SESSION_HOST)
                .append("&").append(GridCommandConstants.SESSION_XID).append("=").append(sessXid)
                .toString();
        try {
            result = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static URL videoOnCommandURL(String sessXid) {
        URL result = null;
        try {
            String urlString = new StringBuilder(createAdminUrl())
                    .append(GridCommandConstants.SESSION_SERVLET)
                    .append(GridCommandConstants.CMD_START_VIDEO)
                    .append("&").append(GridCommandConstants.SESSION_XID).append("=").append(sessXid)
                    .append("&").append(GridCommandConstants.CLIENT_THREAD).append("=").append(Thread.currentThread().getId())
                    .toString();
            result = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static URL videoSaveCommandURL() {
        URL result = null;
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        try {
            String urlString = new StringBuilder(createAdminUrl())
                    .append(GridCommandConstants.SESSION_SERVLET)
                    .append(GridCommandConstants.CMD_SAVE_VIDEO)
                    .append("&").append(GridCommandConstants.CAPTURE_PATH).append("=").append(pageContext.videoCaptureFilePath)
                    .toString();
            result = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static URL videoToHubCommandURL() {
        URL result = null;
        UserPageContext pageContext = SWAContextView.retrievePageContext();
        String urlString = urlString = new StringBuilder(createAdminUrl())
                .append(GridCommandConstants.SESSION_SERVLET)
                .append(GridCommandConstants.CMD_MOVE_VIDEO_TO_HUB)
                .append('&').append(GridCommandConstants.CMD_SESSION_HOST).append('=').append(pageContext.videoCaptureHost)
                .append("&").append(GridCommandConstants.CAPTURE_PATH).append("=").append(pageContext.videoCaptureFilePath)
                .toString();
        try {
            result = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static final String createAdminUrl(){
        return new StringBuilder("http://")
                .append(hubLocation.getHost())
                .append(hubLocation.getPort() != -1 ? ":" + hubLocation.getPort() : ":80")
                .append(GridCommandConstants.GRID_ADMIN_PATH).toString();
    }

    public static final StoryWatchDog.WebApiRemoteCommand createVideoShutdownCommand(final SWARemoteWebDriver webDriver) {



        StoryWatchDog.WebApiRemoteCommand videoShutdownCommand = new StoryWatchDog.WebApiRemoteCommand(SWASeleniumWebAdminAPI.videoShutdownCommandURL(), new StoryWatchDog.WebApiRemoteCommandResultHandler() {

            @Override
            public void handleResult(String resultString) {
                JSONObject resultObject = null;
                try {
                    resultObject = new JSONObject(resultString);

                    if (resultObject.has(GridCommandConstants.FILE_ACCESS_URL)) {
                        resultString = resultObject.get(GridCommandConstants.FILE_ACCESS_URL).toString();
                    } else if (resultObject.has(GridCommandConstants.CMD_ERROR)) {
                        resultString = resultObject.get(GridCommandConstants.CMD_ERROR).toString();
                    } else {
                        System.out.println(SWARemoteWebDriver.createMessageLabel()+" ################ save video result unknown ########################");
                    }
                    if (SWARemoteWebDriver.CMD_TRACE) {
                        SessionId sessionId = webDriver.getSessionId();
                        String logMessage = SWARemoteWebDriver.createMessageLabel().append(" Video available for session: ")
                                .append(sessionId != null ? sessionId.toString() : "ended").append(" at location : ").append(resultString).append(SWARemoteWebDriver.DELIM_END).toString();
                        webDriver.obtainStoryCommandLogger().writeLogMessage(logMessage);
                        webDriver.obtainStoryCommandLogger().newLine(); //extra blank will stand out in the middle of an in-process log
                        webDriver.obtainStoryCommandLogger().flush();
                        ArtifactIndex artifactIndex = webDriver.obtainStoryCommandLogger().artifactIndex;
                        artifactIndex.addcontentH1(artifactIndex.createContentLink(resultString, ArtifactIndex.videoLabel));
                        artifactIndex.addcontentH2(ArtifactIndex.screenShotHdrValue);
                        webDriver.obtainStoryCommandLogger().outputArtifactsIndexPage();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        return videoShutdownCommand;
    }
}