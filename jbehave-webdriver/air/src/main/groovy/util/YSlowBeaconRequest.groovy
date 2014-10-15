package util

import javax.servlet.http.HttpServletRequest
import org.eclipse.jetty.util.ajax.JSON

class YSlowBeaconRequest extends BeaconRequestBase {
    public static final String ARCHIVE_NAME = "performance"

    HttpServletRequest beaconRequest

    YSlowBeaconRequest(HttpServletRequest beaconRequest) {
        super(beaconRequest)
        this.beaconRequest = beaconRequest
    }


    static requestCounter = 0;
    private static String getNextCounter() {
        return Integer.toString(requestCounter++);
    }


    @Override
    public String getRequestedRelativePath() {
        String yslowJsonText = getContent()
        HashMap<String, Object> parsedJSON = JSON.parse(yslowJsonText)
        String encodedUrl = parsedJSON.get("u")
        String decodedUrl = new URLDecoder().decode(encodedUrl)
        URL url = new URL(decodedUrl);
        return url.getPath()
    }

    @Override
    public File getArchiveFile() {
        new File(getArchiveFolder(ARCHIVE_NAME), makeResultFilenameFromPagePath(
                getNextCounter() ,
                getRequestedRelativePath()))
    }
}
