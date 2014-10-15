package util

import com.swacorp.dotcom.webscenarios.air.PerformanceWebDriverProvider
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.eclipse.jetty.server.Request
import org.eclipse.jetty.server.handler.AbstractHandler

class BeaconResponseHandler extends AbstractHandler {
    void handle(String target,
                Request baseRequest,
                HttpServletRequest request,
                HttpServletResponse response) {
        def beaconRequest = getBeaconRequest(request)
        def file = beaconRequest.getArchiveFile()
        file.write(beaconRequest.getContent())
    }

    private BeaconRequestBase getBeaconRequest(request) {
        def beaconRelativeUri = request.getRequestURI()
        if (beaconRelativeUri.startsWith(PerformanceWebDriverProvider.NETEXPORT_RELATIVE_URI))
            return new NetExportBeaconRequest(request)
        else if (beaconRelativeUri.startsWith(PerformanceWebDriverProvider.YSLOW_RELATIVE_URI))
            return new YSlowBeaconRequest(request)
    }
}
