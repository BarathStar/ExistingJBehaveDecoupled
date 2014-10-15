package com.swacorp.dotcom.webscenarios.air;

import org.jbehave.web.selenium.DelegatingWebDriverProvider;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PerformanceWebDriverProvider extends DelegatingWebDriverProvider {
    public static final String YSLOW_RELATIVE_URI = "/yslow";
    public static final String NETEXPORT_RELATIVE_URI = "/netexport";


    private static final int MAX_ALLOWED_JS_ON_A_PAGE = 15;
    private static final String RULE_SET_NAME = "performanceRuleSet";
    private static final String CUSTOM_RULE_SET_JSON = escapeJsonStringForUseInProfile(
            "{ \"custom\" : true," +
                    "  \"id\" : \"" + RULE_SET_NAME + "\"," +
                    "  \"name\" : \"" + RULE_SET_NAME + "\"," +
                    "  \"rules\" : { " +
                    "      \"ycsstop\" : {  }," +
                    "      \"ydupes\" : {  }," +
                    "      \"yemptysrc\" : {  }," +
                    "      \"yexpressions\" : {  }," +
                    "      \"yexternal\" : {  }," +
                    "      \"yimgnoscale\" : {  }," +
                    "      \"ymincookie\" : {  }," +
                    "      \"ymindom\" : {  }," +
                    "      \"yno404\" : {  }," +
                    "      \"ynofilter\" : {  }," +
                    "      \"ynumreq\" : { " +
                    "        \"max_js\" : " + MAX_ALLOWED_JS_ON_A_PAGE +
                    "      }," +
                    "      \"yredirects\" : {  }" +
                    "    }," +
                    "  \"weights\" : { " +
                    "      \"ycsstop\" : 4," +
                    "      \"ynumreq\" : 8 " +
                    "    }" +
                    "}");

    private final int port;

    PerformanceWebDriverProvider() {
        String beaconListenerPortPropertyValue = System.getProperty("beaconListenerPort");
        if (beaconListenerPortPropertyValue != null) {
            this.port = Integer.parseInt(beaconListenerPortPropertyValue);
        } else {
            this.port = 4567;
        }
    }

    public void initialize() {

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        configureYSlow(firefoxProfile);
        configureNetexport(firefoxProfile);

        delegate.set(new FirefoxDriver(firefoxProfile));
    }

    private void configureNetexport(FirefoxProfile firefoxProfile) {

        firefoxProfile.setPreference("browser.startup.homepage_override.mstone", "ignore");

        if (System.getProperty("perf").contains("netexport")) {

            if (System.getProperty("perf").contains("firebugVisible")) {

                firefoxProfile.setPreference("extensions.firebug.alwaysOpenTraceConsole", true);
                firefoxProfile.setPreference("extensions.firebug.DBG_NETEXPORT",true);
            } else {
                firefoxProfile.setPreference("extensions.firebug.previousPlacement", 3); // This hides the panel during the tests
            }

            firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.7X.0b2");
            firefoxProfile.setPreference("extensions.firebug.allPagesActivation", "on");
            firefoxProfile.setPreference("extensions.firebug.net.enableSites", true);
            firefoxProfile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
            firefoxProfile.setPreference("extensions.firebug.netexport.autoExportToFile", false);
            firefoxProfile.setPreference("extensions.firebug.netexport.autoExportToServer", true);
            firefoxProfile.setPreference("extensions.firebug.netexport.beaconServerURL", getNetexportServerURL());
            firefoxProfile.setPreference("extensions.firebug.netexport.sendToConfirmation", false);
            firefoxProfile.setPreference("extensions.firebug.netexport.showPreview", false);
            firefoxProfile.setPreference("extensions.firebug.netexport.pageLoadedTimeout","15000");

            try {
                firefoxProfile.addExtension(AirStories.class, "/firebug-1.7X.0b2-fx.xpi");
            } catch (IOException e) {
                throw new RuntimeException("Couldn't find Firebug", e);
            }

            try {
                firefoxProfile.addExtension(AirStories.class, "/netExport-0.8b12.xpi");
            } catch (IOException e) {
                throw new RuntimeException("Could not load NetExport addon", e);
            }
        }
    }

    private void configureYSlow(FirefoxProfile firefoxProfile) {
        if (System.getProperty("perf").contains("yslow")) {
            firefoxProfile.setPreference("extensions.yslow.autorun", true);
            firefoxProfile.setPreference("extensions.yslow.optinBeacon", true);
            firefoxProfile.setPreference("extensions.yslow.beaconUrl", getYSlowServerURL());
            firefoxProfile.setPreference("extensions.yslow.beaconInfo", "all");
            firefoxProfile.setPreference("extensions.yslow.customRuleset." + RULE_SET_NAME, CUSTOM_RULE_SET_JSON);
            firefoxProfile.setPreference("extensions.yslow.defaultRuleset", RULE_SET_NAME);

            try {
                firefoxProfile.addExtension(AirStories.class, "/yslow-2.1.0-fx.xpi");
            } catch (IOException e) {
                throw new RuntimeException("Could not load YSlow addon", e);
            }
        }
    }

    private String getYSlowServerURL() {
        try {
            return new URL("http", "localhost", getPort(), YSLOW_RELATIVE_URI).toExternalForm();
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Could not create a url string for the yslow beacon server", e);
        }
    }

    private String getNetexportServerURL() {
        try {
            return new URL("http", "localhost", getPort(), NETEXPORT_RELATIVE_URI).toExternalForm();
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Could not create a url string for the net export beacon server", e);
        }
    }

    public int getPort() {
        return port;
    }

    protected static String escapeJsonStringForUseInProfile(String json) {
        return json.replaceAll("\"", "\\\\\"");
    }

}
