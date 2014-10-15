package com.swacorp.dotcom.webscenarios.air

public class Route {
    private String from
    private String to

    def getFrom() {
        return from
    }

    def getTo() {
        return to
    }

    Route(String from, String to) {
        this.from = from
        this.to = to
    }
}
