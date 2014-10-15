package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelQA334LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("20178954320", "test@wnco.com", "12345");

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
            .put("Alist", new RRUser("danbrown", "test123", "20178972413", "dan", "", "brown", new Date(80, 2, 1), "Male", "20178972413"))
            .put("goodUser", new RRUser("davidkary", "test123", "20178969521", "David", "", "Kary", new Date(86, 3, 5), "Male", "20178969521"))
            .put("goodUser1", new RRUser("ronthomas", "test123", "20178969510", "Ron", "", "Thomas", new Date(85,8,7), "Male", "20178969510"))
            .put("goodUser2", new RRUser("agathachristie", "test123", "20178969462", "Agatha", "", "Chirstie", new Date(87,11,5), "Female", "20178969462"))
            .put("goodUser3", new RRUser("noraroberts", "test123", "20178969370", "Nora", "", "Roberts", new Date(87,4,6), "Female", "20178969370"))
            .put("customer", new RRUser("isabellahook", "test123", "502510901", "Isabella", "", "hook", new Date(79, 1, 1), "Female", "502510901"))
            .put("RRCreditCard", new RRUser("danhenry", "test123", "20179007796", "Dan","", "Henry", new Date(79, 1, 1), "Male", "20179007796"))
            .put("promoCertUser", new RRUser("winniepooh", "test123", "502365533", "winnie", "", "pooh", new Date(79, 1, 1), "Male", "20178954320")).build();

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
