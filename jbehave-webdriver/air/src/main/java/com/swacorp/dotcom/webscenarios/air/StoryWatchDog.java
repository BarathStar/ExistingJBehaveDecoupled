package com.swacorp.dotcom.webscenarios.air;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import static com.swacorp.dotcom.webscenarios.air.SWARemoteWebDriver.*;

/**
 * Watchdog class for SWARemoteWebDriver to ensure remote admin commands are terminated
 * specifically we don't want to leave running instances of ffmpeg on remote nodes
 * the commands stored in the remoteCommands map are kills for those running instances
 */
public final class StoryWatchDog  {

    private static final String storyTimeoutInSecs = System.getProperty("storyTimeoutInSecs");

    private static final Map<String,WebApiRemoteCommand> remoteCommands = new ConcurrentSkipListMap<String,WebApiRemoteCommand>();
    private static final Set<String> allocatedSessions = new ConcurrentSkipListSet<String>();

    private static StoryWatchDog watchDogInstance = null;

    private StoryWatchDog() {/* not available */ }

    public static boolean sessionAlreadyAllocated(String sessionId){
        return !allocatedSessions.add(sessionId);
    }
    public static void sessionEnded(String sessionId){
        allocatedSessions.remove(sessionId);
    }

    public static void createShutDownHook() {

        if(StoryWatchDog.watchDogInstance == null){
            StoryWatchDog.watchDogInstance = new StoryWatchDog();
            Runtime.getRuntime().addShutdownHook(new Thread() {
               @Override
               public void run() {
                   currentThread().setName("shutdown");
                   cleanUp();
               }
            });

        }
    }

    public static void cleanUp() {
        if(StoryWatchDog.remoteCommands.size() > 0){
            System.out.println(SWARemoteWebDriver.createMessageLabel()+" Cleanup running "+SWARemoteWebDriver.DELIM_END);
            cleanUpRemoteCommands(StoryWatchDog.remoteCommands);
            System.out.println(SWARemoteWebDriver.createMessageLabel()+" Cleanup finished "+SWARemoteWebDriver.DELIM_END);
        }
    }

    private static final void cleanUpRemoteCommands(final Map<String,WebApiRemoteCommand> inFlightCommands) {
        Set<String> commandKeys = inFlightCommands.keySet();
        System.out.println(SWARemoteWebDriver.createMessageLabel()+" Cleaning up : "+commandKeys.size()+" commands "+SWARemoteWebDriver.DELIM_END);
        for(String key : commandKeys){
            try {
                WebApiRemoteCommand command = inFlightCommands.remove(key);
                String result = runCommand(command.webApiCommand);
                if (command.resultHandler != null){
                    command.resultHandler.handleResult(result);
                } else {
                    System.out.println(SWARemoteWebDriver.createTraceLabel()+" ################ result handler NULL ########################");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void runCommandForPid(String pid){
        if (pid == null) {
            System.out.println(SWARemoteWebDriver.createTraceLabel()
                    + " #### Null PID detected for remote command run request StoryWatchDog.runCommandForPid()");
            return;
        }
        try {
            if (remoteCommands.containsKey(pid)) {
                WebApiRemoteCommand command = StoryWatchDog.remoteCommands.remove(pid);
                String result = runCommand(command.webApiCommand);
                if (command.resultHandler != null) {
                    command.resultHandler.handleResult(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addCommand(String pid, WebApiRemoteCommand command) {
        remoteCommands.put(pid, command);
    }

    public static void addCommand(String pid, URL command) {
        remoteCommands.put(pid, new WebApiRemoteCommand(command, null));
    }

    public static void removeCommand(String pid) {
        remoteCommands.remove(pid);
    }

    /**
     * Strategy pojo to provide result handling if required
     */
    public static class WebApiRemoteCommandResultHandler {
        public void handleResult(String s){
            //no default implementation
        }
    }

    /**
     * command pojo to contain a remote command and its result handler
     */
    public static final class WebApiRemoteCommand {
        public final URL webApiCommand;
        public final WebApiRemoteCommandResultHandler resultHandler;
        public WebApiRemoteCommand(URL commandUrl, WebApiRemoteCommandResultHandler resultHandler){
            this.webApiCommand = commandUrl;
            this.resultHandler = resultHandler;
        }
    }
}
