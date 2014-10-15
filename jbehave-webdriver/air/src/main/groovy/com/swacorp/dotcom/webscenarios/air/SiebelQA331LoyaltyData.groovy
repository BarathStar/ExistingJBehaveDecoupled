package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelQA331LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("502436874", "test@wnco.com", "test123");

        private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
                .put("Alist", new RRUser("Reenie", "test123", "502436874", "Reenie", "", "Sean", new Date(79, 1, 1), "Female", "502436874"))
                .put("goodUser", new RRUser("Romelie", "test123", "502436852", "Romelie", "", "Escobar", new Date(79, 01, 01), "Female", "502436874"))
                .put("customer", new RRUser("tintin", "test123", "502446744", "Tin", "", "Tin", new Date(79, 01, 01), "Male", "502436874"))
                .put("RRCreditCard", new RRUser("October", "test123", "502466274", "October", "", "Tester", new Date(80, 1, 1), "Male", "502466274"))
                .put("promoCertUser", new RRUser("shalinijbehave", "test123", "502365533", "Shalini", "", "Jbehave", new Date(61, 06, 16), "Male", "502365533")).build();

    private Map <String, LuvVouchers> luvVouchersMap = new ImmutableMap.Builder().put("25Dollar", new LuvVouchers("9100000014348312", "4362")).build();
    private Map <String, LuvVouchers> giftCardsMap = new ImmutableMap.Builder().put("300Dollar", new GiftCards("8999990000059228", "1314")).build();

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
