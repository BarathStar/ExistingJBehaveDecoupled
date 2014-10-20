package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelQA432LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("502481906", "test@wnco.com", "12345");
    private static final String PASSWORD = "test123"

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
            .put("ageVerifiedSenior", new RRUser("Richard",PASSWORD,"600594945","Richard","","Parker", new Date(23,06,40), "Male", "600594945"))
            .put("Alist", new RRUser("shaunpollock", "test123", "502481906", "Shaun", "", "Pollock", new Date(84,4,5), "Male", "502481906"))
            .put("goodUser", new RRUser("rickyponting", "test123", "502481884", "Ricky", "", "Ponting", new Date(84,2,5), "Male", "502481884"))
            .put("goodUser1", new RRUser("brunomars", "test123", "502481943", "Bruno", "", "Mars", new Date(84,7,5), "Male", "502481943"))
            .put("goodUser2", new RRUser("katyperry", "test123", "502481976", "Katy", "", "Perry", new Date(84,8,5), "Female", "502481976"))
            .put("goodUser3", new RRUser("taylorswift", "test123", "502482013", "Taylor", "", "Swift", new Date(84,9,5), "Female", "502482013"))
            .put("customer", new RRUser("mitchelljohnson", "test123", "502481910", "Mitchell", "", "Johnson", new Date(84,5,5), "Male", "502481910"))
            .put("RRCreditCard", new RRUser("daviswarner", "test123", "502481895", "Davis","", "Warner", new Date(84,3,5), "Male", "502481895"))
            .put("promoCertUser", new RRUser("albiemorkel", "test123", "502481921", "Albie", "", "Morkel", new Date(84,6,5), "Male", "502481921")).build();

    private Map <String, LuvVouchers> luvVouchersMap = new ImmutableMap.Builder().put("25Dollar", new LuvVouchers("9100000014349427", "4576")).build();
    private Map <String, LuvVouchers> giftCardsMap = new ImmutableMap.Builder().put("300Dollar", new GiftCards("8999990000059152", "1539")).build();

    public APlusUser getAPlusUser() {
        return aPlusUser;
    }

    RRUser getUser(String user) {
        return rrUserMap.get(user)
    }

    LuvVouchers getLuvVoucher(String luvVoucher) {
        return luvVouchersMap.get(luvVoucher)
    }

    GiftCards getGiftCard(String giftCard) {
        return giftCardsMap.get(giftCard)
    }

    Map<String, RRUser> getRrUserMap() {
        return rrUserMap
    }
}
