package fixture.ding

import fixture.stubs.HttpPoster;
import util.ItineraryData
import static org.apache.commons.lang.StringUtils.substringBetween
import fixture.stubs.DynaStubsIntegration

class DingSession {

    public static final String DING_SESSION_PATH = "/cgi-bin/systray";
    public static final String OS = "WinXP";
    public static final String VERSION = "1.05";

    public String dingServiceURL;
    public String installID;
    public String checksum;
    public String headlineID;
    public String sessionID;

    public DingSession(String domain, String originStation, String arrivalStation) {
        if (DynaStubsIntegration.useDynaStubs()){
            installID = "1";
            checksum = "1";
            headlineID = "1";
        } else {
            dingServiceURL = domain + DING_SESSION_PATH;
            initializeInstallIdAndChecksum(originStation, arrivalStation);
            createSession();
            getHeadlines();

            if ("".equalsIgnoreCase(domain)) {
                throw new NullPointerException("Ding Server domain cannot be null")
            }
        }
        if ("".equalsIgnoreCase(installID) || "".equalsIgnoreCase(checksum)) {
            throw new NullPointerException("Could not create Ding InstallID")
        }
        if ("".equalsIgnoreCase(headlineID)) {
            throw new NullPointerException("Could not retrive Ding Headline")
        }
    }

    private void initializeInstallIdAndChecksum(String originStation, String arrivalStation) {
        String postResult = dingHttpPoster()
            .withParameter("action", "installConfirm")
            .withParameter("maxA", "10")
            .withParameter("maxB", "1000000")
            .withParameter("airport", originStation)
            .withParameter("airport", arrivalStation)
            .withParameter("zip", "75206")
            .postParameters();
        installID = getXmlTagValue(postResult, "installId");
        checksum = getXmlTagValue(postResult, "checksum")
    }

    private void createSession() {
        String postResult = dingHttpPoster()
            .withParameter("installId", installID)
            .withParameter("cksum", checksum)
            .withParameter("action", "createSession")
            .postParameters();
        sessionID = getXmlTagValue(postResult, "disc");
    }

    public void getHeadlines() {
        String result = dingHttpPoster()
            .withParameter("disc", sessionID)
            .withParameter("action", "getMessages")
            .postParameters();
        headlineID = substringBetween(result, "headline id=\"", "\"");
    }

    private HttpPoster dingHttpPoster() {
        return new HttpPoster().to(dingServiceURL)
            .withParameter("os", "WinXP")
            .withParameter("version", "1.05")
    }

    private String getXmlTagValue(String xmlText, String tagName) {
        return substringBetween(xmlText, "<" + tagName + ">", "</" + tagName + ">");
    }
}
