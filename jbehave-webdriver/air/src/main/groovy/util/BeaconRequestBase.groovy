package util

import org.jbehave.core.io.CodeLocations
import javax.servlet.http.HttpServletRequest

abstract class BeaconRequestBase {
    public abstract String getRequestedRelativePath()
    public abstract File getArchiveFile()

    private HttpServletRequest request;
    private String content = null; // lazy loaded from request in getContent

    protected BeaconRequestBase(HttpServletRequest request) {
        this.request = request;
    }

    public static def getArchiveFolder(String archiveName) {
        File targetDirectory = getTopLevelArchiveDirectory()
        File performanceResultDirectory = new File(targetDirectory, archiveName)
        performanceResultDirectory.mkdir()
        performanceResultDirectory
    }

    private static def getTopLevelArchiveDirectory() {
        URL url = CodeLocations.codeLocationFromClass(BeaconRequestBase.class)
        File classesDirectory = new File(url.getPath())
        classesDirectory.getParentFile()
    }

    protected static String makeResultFilenameFromPagePath(String prefix, String pathFromUrl) {
        final String filenamepart

        if (pathFromUrl == "/") {
            filenamepart = "homepage"
        } else {
            // Trim leading slash
            def pathFromUrlNoLeadingSlash = pathFromUrl.replaceFirst(/\//, "").replaceFirst(/\/$/, "").replaceFirst(/\.html(.*)$/, ".html")
            // Replace slashes with dots
            filenamepart = pathFromUrlNoLeadingSlash.replaceAll(/\//, ".")
        }
        return "$prefix-${filenamepart}.json"
    }

    protected String getRequestParameter(String name) {
        return request.getParameter("url")
    }

    protected String getContent() {
        if (content  == null)
            content = request.getReader().text;
        return content
    }

}
