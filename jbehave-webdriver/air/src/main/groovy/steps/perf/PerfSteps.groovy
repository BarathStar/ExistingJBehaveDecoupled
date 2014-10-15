package steps.perf

import com.swacorp.dotcom.webscenarios.air.PerformanceWebDriverProvider
import org.jbehave.web.selenium.WebDriverProvider
import util.BeaconRequestBase
import util.BeaconResponseServer
import util.NetExportBeaconRequest
import util.YSlowBeaconRequest
import org.jbehave.core.annotations.*

class PerfSteps {
    private PerformanceWebDriverProvider performanceWebDriverProvider;
    private BeaconResponseServer beaconResponseServer;
    private static long TIMEOUT_MILLISECONDS = 2 * 60 * 1000;
    private static long ONE_SECOND_MILLISECONDS = 1 * 1000;

    PerfSteps(WebDriverProvider webDriverProvider) {
        this.performanceWebDriverProvider = webDriverProvider
        this.beaconResponseServer = new BeaconResponseServer(performanceWebDriverProvider.getPort());
    }

    @BeforeStories
    def startServer() {
        beaconResponseServer.start();
    }

    @AfterStories
    def stopServer() {
        beaconResponseServer.stop();
    }

    @AfterScenario
    def waitForLastPageNetExportDataToGetLoggedBeforeEndingBrowserSession() {
        if (System.getProperty("perf") != null){
        }
    }

    @When("I wait for yslow performance data json to be logged in \$filename")
    def iWaitForYSlowJsonToBeLogged(String filename) {
        File expectedFile = new File(BeaconRequestBase.getArchiveFolder(YSlowBeaconRequest.ARCHIVE_NAME), filename)
        long startTime = System.currentTimeMillis()
        while (!expectedFile.exists() &&
               (System.currentTimeMillis() - startTime) < TIMEOUT_MILLISECONDS) {
            println "Waiting for $filename to exist... (${(System.currentTimeMillis() - startTime) / 1000})"
            sleep(ONE_SECOND_MILLISECONDS)
        }
    }

    @When("I wait for netexport performance data json to be logged in '\$filename'")
    def iWaitForNetExportJsonToBeLogged(String filename) {
        File expectedFile = new File(BeaconRequestBase.getArchiveFolder(NetExportBeaconRequest.ARCHIVE_NAME), filename)
        long startTime = System.currentTimeMillis()
        while (!expectedFile.exists() &&
               (System.currentTimeMillis() - startTime) < TIMEOUT_MILLISECONDS) {
            println "Waiting for $filename to exist... (${(System.currentTimeMillis() - startTime) / 1000})"
            sleep(ONE_SECOND_MILLISECONDS)
        }
    }
}
