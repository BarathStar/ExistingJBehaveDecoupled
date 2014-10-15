package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelQA2LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("505385683", "harry.potter@gmail.com", "test123");

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
    //TODO update correct RR number and name
            .put("goodUser", new RRUser("Jbehave", "test123", "501730865", "June", "Leapfrog", "Test", new Date(79, 9, 21), "Male", "500321334"))
            .put("promoCertUser", new RRUser("Qaaautotest", "test123", "505385650", "Qaaautotest", "", "Tester", new Date(75, 07, 05), "Male", "501798216")).build();

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