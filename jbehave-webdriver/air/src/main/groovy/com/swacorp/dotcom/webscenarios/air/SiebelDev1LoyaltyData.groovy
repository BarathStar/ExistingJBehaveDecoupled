package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelDev1LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("500321334", "harry.potter@gmail.com", "12345");

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
    //TODO update correct RR number and name
    .put("goodUser", new RRUser("Jbehave", "test123", "501730865", "June", "Leapfrog", "Test", new Date(79, 9, 21), "Male", "500321334")).build();

    public APlusUser getAPlusUser() {
        return aPlusUser;
    }

    RRUser getUser(String user) {
        return rrUserMap.get(user)
    }

    Map<String, RRUser> getRrUserMap() {
        return rrUserMap
    }

}
