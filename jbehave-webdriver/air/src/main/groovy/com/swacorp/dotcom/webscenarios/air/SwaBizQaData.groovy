package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

class SwaBizQaData extends SwaBizData {
    private Map<String, String> promoCodeMap = new ImmutableMap.Builder()
    .put("SB_all_promo_dollarsOff", "AUTODLROFF")
    .put("SB_all_promo_percentOff", "ATOPCTOFFA")
    .put("SB_company_dollarsOff_all", "99681761")
    .put("SB_company_dollarsOff_spec", "99698336")
    .put("SB_company_percentOff_all", "99698491")
    .put("SB_company_percentOff_spec", "99698955")
    .put("SB_company_no_discount", "99681761")
    .build();

    Map<String, String> getSwabizDiscountCodeMap() {
        return promoCodeMap;
    }
}
