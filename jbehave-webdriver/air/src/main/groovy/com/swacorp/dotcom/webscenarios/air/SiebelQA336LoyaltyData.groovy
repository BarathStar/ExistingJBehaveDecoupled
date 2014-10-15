package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

public class SiebelQA336LoyaltyData extends Data {

    private final APlusUser aPlusUser = new APlusUser("608695743", "test@wnco.com", "12345");

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
            .put("AlistPreferred", new RRUser("ajax", "test123", "609324660", "ajax", "", "gorilla", new Date(79,0,1), "Male", "609324660"))
            .put("Alist", new RRUser("maxgoof", "test123", "609324693", "max", "", "goof", new Date(79,0,1), "Male", "609324693"))
            .put("goodUser", new RRUser("bashful", "test123", "609324785", "bashful", "", "dwarf", new Date(79,0,1), "Male", "609324785"))
            .put("goodUser1", new RRUser("benaligator", "test123", "609324796", "ben", "", "aligator", new Date(79,0,1), "Male", "609324796"))
            .put("goodUser2", new RRUser("binkie", "test123", "609324800", "binkie", "", "middlefoot", new Date(89,0,1), "Female", "609324800"))
            .put("goodUser3", new RRUser("bluefairy", "test123", "609324811", "blue", "", "fairy", new Date(89,0,1), "Female", "609324811"))
            .put("preferredName", new RRUser("jamesbarnes", "test123", "609425036", "james", "bucky", "", "barnes", new Date(90,0,1), "Male", "609425036"))
            .put("customer", new RRUser("captain", "test123", "609324833", "captain", "", "america", new Date(79,0,1), "Male", "609324833"))
            .put("RRCreditCard", new RRUser("bucky", "test123", "609324844", "bucky","", "bug", new Date(89,0,1), "Female", "609324844"))
            .put("RRSeniorCreditCard", new RRUser("chip", "test123", "609324892", "chip", "", "potts", new Date(40,0,1), "Male", "609324892"))
            .put("promoCertUser", new RRUser("cobra", "test123", "609324903", "cobra","", "bubbles", new Date(79,0,1), "Male", "609324903"))
            .put("RRMinorPoints", new RRUser("cody", "test123", "609324925", "cody", "", "fish", new Date(108,0,1), "Male", "609324925")).build();

    private Map <String, LuvVouchers> luvVouchersMap = new ImmutableMap.Builder()
            .put("25Dollar", new LuvVouchers("9100000014349427", "4576"))
            .put("500Thousand", new LuvVouchers("9100000014263206", "8041")).build();
    private Map <String, LuvVouchers> giftCardsMap = new ImmutableMap.Builder().put("300Dollar", new GiftCards("8999990000059152", "1539"))
            .put("500Thousand", new GiftCards("8999990000047116", "3760")).build();

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
