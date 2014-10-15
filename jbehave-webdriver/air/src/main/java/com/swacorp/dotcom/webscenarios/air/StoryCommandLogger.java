package com.swacorp.dotcom.webscenarios.air;

import pages.ArtifactIndex;
import state.SWAContextView;
import state.UserPageContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: developer
 * Date: 7/3/12
 * Time: 1:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoryCommandLogger {


    String currentStory    = SWAContextView.NO_STORY;
    ArtifactIndex artifactIndex = null;
    File currentStoryLog = null;
    String currentStoryLogPath = null;
    String currentScenario = SWAContextView.NO_SCENARIO;
    String currentStep = SWAContextView.NO_STEP;

    private BufferedWriter currentStoryLogWriter = null;
    private FileWriter fstream = null;

    public StoryCommandLogger(){/* nothing here yet */ }


    public void initialize() {
        try {
            if (SWAContextView.NO_STORY.equals(currentStory) || !currentStory.equals(SWAContextView.retrievePageContext().currentStory)) {
                changeStory();
                String currentStoryDir = currentStory.contains(".story")
                        ? currentStory.substring(0,currentStory.indexOf(".story")) : currentStory;
                currentStoryLogPath = SWARemoteWebDriver.constructPath(currentStoryDir, null, null);
                currentStoryLog = new File(currentStoryLogPath);
                currentStoryLog.mkdirs();
                currentStoryLog = new File(currentStoryLogPath + "/webdriver_command-" + SWARemoteWebDriver.screenThreadLabel() + ".log");
            }
            fstream = new FileWriter(currentStoryLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStoryLogWriter = new BufferedWriter(fstream);
        writeLogMessage(System.currentTimeMillis() + ".story.log [ " + SWARemoteWebDriver.screenThreadLabel() + " " + "Story : " + currentStory + " ]\n");
        flush();
    }

    private void changeStory() {
        try {
            if (currentStoryLogWriter != null) {
                currentStoryLogWriter.flush();
                currentStoryLogWriter.close();
            }
            UserPageContext currentContextView = SWAContextView.retrievePageContext();
            currentStory = currentContextView.currentStory;
            artifactIndex = new ArtifactIndex(currentStory);
            SWAContextView.replacePageContext(currentContextView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputArtifactsIndexPage() {
        try {
            artifactIndex.addStoryLogLink(currentStoryLog.getCanonicalPath(), "story log for ");
            File artifactIndexPage = new File(currentStoryLogPath + "/artifact-index.html");
            FileWriter fileWriter = new FileWriter(artifactIndexPage);
            BufferedWriter pageWriter = new BufferedWriter(fileWriter);
            System.out.println(new StringBuilder(SWARemoteWebDriver.screenThreadLabel()).append(" ").append(artifactIndexPage.getCanonicalPath()).toString());
            pageWriter.write(artifactIndex.toString());
            pageWriter.flush();
            pageWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputLogMessageWithScenarioSteps(String logMessage){
        logScenariosAndSteps();
        writeLogMessage(logMessage);
    }

    public void writeLogMessage(String logMessage) {
        try {
            synchronized (currentStoryLogWriter){
                currentStoryLogWriter.write(logMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logScenariosAndSteps() {
        if (!currentStep.equals(SWAContextView.retrievePageContext().currentStep)) {
            currentStep = SWAContextView.retrievePageContext().currentStep;
            writeLogMessage(System.currentTimeMillis() + ".story.log [ " + SWARemoteWebDriver.screenThreadLabel() + "        Step : " + currentStep + " ]\n");
        }
        if (!currentScenario.equals(SWAContextView.retrievePageContext().currentScenario)) {
            currentScenario = SWAContextView.retrievePageContext().currentScenario;
            writeLogMessage(System.currentTimeMillis() + ".story.log [ " + SWARemoteWebDriver.screenThreadLabel() + "    Scenario : " + currentScenario + " ]\n");
        }
    }
    public void delete() {
        this.currentStoryLog.deleteOnExit();
    }
    public void flush() {
        try {
            currentStoryLogWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void newLine() {
        try {
            currentStoryLogWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
