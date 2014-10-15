package util

import org.eclipse.jetty.server.Server

class BeaconResponseServer {
    private Server server;
    private int requestCounter;
    private BeaconResponseHandler handler;

    public BeaconResponseServer(int port) {
        server = new Server(port);
        handler = new BeaconResponseHandler();
    }

    public void start() {
        server.setHandler(handler);
        server.start();
    }

    def stop() {
        server.stop();
    }

}