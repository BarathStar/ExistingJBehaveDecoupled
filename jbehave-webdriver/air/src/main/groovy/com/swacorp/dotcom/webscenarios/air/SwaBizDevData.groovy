package com.swacorp.dotcom.webscenarios.air

import com.google.common.collect.ImmutableMap

class SwaBizDevData extends SwaBizData {
    private Map<String, String> promoCodeMap = new ImmutableMap.Builder()
    .put("SB_all_promo_dollarsOff", "AUTODLROFF")
    .put("SB_all_promo_percentOff", "ATOPCTOFFA")
    .put("SB_company_dollarsOff_all", "99722980")
    .put("SB_company_dollarsOff_spec", "99719686")
    .put("SB_company_percentOff_all", "99720902")
    .put("SB_company_percentOff_spec", "99718986")
    .put("SB_company_no_discount", "99728661")
    .build();

    Map<String, String> getSwabizDiscountCodeMap() {
        return promoCodeMap;
    }
}
