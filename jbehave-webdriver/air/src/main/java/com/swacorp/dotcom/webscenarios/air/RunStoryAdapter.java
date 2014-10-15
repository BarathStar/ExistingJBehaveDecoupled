package com.swacorp.dotcom.webscenarios.air;

/**
 * @author Eitan Suez
 *
 * Adapter for IntelliJBehave plugin's "Run Story" action.
 *
 * Example Usage:
 *   1. ensure that you're using idea and have the plugin installed
 *   2. configure the JBehave plugin with this class as the "main class for running stories"
 *   3. open a story file, e.g. adult_air_cancel.story
 *   4. select "Run -> Run Story" from the menu, or use the keyboard shortcut
 */
public class RunStoryAdapter
{
    /**
     * Extracts the story name and sets it as the "storyFilter" environment variable
     *   before running the AirStories suite.
     *
     * @param 'Run Story' passes the fully qualified path of the story being run as the single argument to main().
     *
     */
    public static void main(String[] args)
    {
        String storyPath = args[0];
        java.io.File storyPathFile = new java.io.File(storyPath);
        String storyName = storyPathFile.getName();
        String storyFilter = storyName.substring(0, storyName.indexOf(".story"));
        System.setProperty("storyFilter", storyFilter);

        try
        {
            new AirStories().run();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
}
