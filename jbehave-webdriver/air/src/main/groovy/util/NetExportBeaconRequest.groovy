package util

import javax.servlet.http.HttpServletRequest

class NetExportBeaconRequest extends BeaconRequestBase {
    public static final String ARCHIVE_NAME = "netexport"

    NetExportBeaconRequest(HttpServletRequest beaconRequest) {
        super(beaconRequest)
    }

    @Override
    public String getRequestedRelativePath() {
        return getRequestParameter("url")
    }

    @Override
    public File getArchiveFile() {
        String timestamp = Long.toString(System.currentTimeMillis())
        new File(getArchiveFolder(ARCHIVE_NAME), makeResultFilenameFromPagePath(timestamp, getRequestedRelativePath()))
    }
}
