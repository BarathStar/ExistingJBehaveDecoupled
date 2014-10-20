package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap
import org.joda.time.LocalDate

public class SiebelDevLoyaltyData extends Data {

    private static final String PASSWORD = "test123"

    private Map<String, RRUser> rrUserMap = new ImmutableMap.Builder()
       .put("ageVerifiedSenior", new RRUser("Richard",PASSWORD,"600594945","Richard","","Parker", new Date(23,06,40), "Male", "600594945"))
        .put("noAPlusCredits", new RRUser("Clark", "Kent", "carter1", "44", 1000, 0, 0, "44"))
       .put("noDebitRapidRewardsAccount", new RRUser("Sheldon", "Cooper", "carter1", "73", 1000, 0, 0, "73"))
       .put("awardsOnly", new RRUser("Amy", "Farrah Fowler", "carter1", "52", 0, 0, 3, "52"))
       .put("creditsOnly", new RRUser("John", "Cooper", "carter1", "51", 0, 3, 0, "51"))
       .put("pointsOnly", new RRUser("Rodger", "Rabbit", "carter1", "50", 1000, 0, 0, "50"))
       .put("Alist", new RRUser("Alist", "SamFifteen", "Leapfrog", PASSWORD, "500321183",50000, 0, 0 , "", new Date(80, 0, 1), "Male"))
       .put("AlistPreferred", new RRUser("AlistPreferred", "SamFifteen", "Leapfrog", PASSWORD, "500321183",350000, 0, 0 , "", new Date(80, 0, 1), "Male"))
       .put("invalid", new RRUser("feafea", "carter1", "11"))
       .put("promoUser", new RRUser("MyName", PASSWORD, "500306660"))
       .put("noCreditCardUser", new RRUser("tespod82", PASSWORD, "500411951"))
       .put("withAwards", new RRUser("testpod8", PASSWORD, "500411962", "Gcpodeighttwo", "","Podeight", new Date(85, 05, 27), "Male", "500411962"))
       .put("goodUser", new RRUser("Jbehave", PASSWORD, "500321334", "EvericFifteen", "Rivas", "Leapfrog", new Date(80, 0, 1), "Male", "500321334"))
       .put("goodUserWithNoActivityInLast30Days", new RRUser("Jbehave", PASSWORD, "500321335", "EvericFifteen", "Rivas", "Leapfrog", new Date(80, 0, 1), "Male", "500321335", new LocalDate().minusDays(30)))
       .put("accountExpiredUser", new RRUser("Jbehave", PASSWORD, "500321338", "EvericFifteen", "Rivas", "Leapfrog", new Date(80, 0, 1), "Male", "500321338", new LocalDate().minusMonths(24)))
       .put("withAwardsAndCredits", new RRUser("sally777",PASSWORD,"500411936","GCpodeightone","","podeight", new Date(85,10,26), "Male", "500411936")).build()

    private Map <String, LuvVouchers> luvVouchersMap = new ImmutableMap.Builder()
            .put("25Dollar", new LuvVouchers("9100000014349427", "4576"))
            .put("500Thousand", new LuvVouchers("9100000014263206", "8041")).build();

    public APlusUser getAPlusUser() {
        return super.getAPlusUser();
    }

    RRUser getUser(String user) {
        return rrUserMap.get(user)
    }

    LuvVouchers getLuvVoucher(String luvVoucher) {
        return luvVouchersMap.get(luvVoucher)
    }

    Map<String, RRUser> getRrUserMap() {
        return rrUserMap
    }

}
