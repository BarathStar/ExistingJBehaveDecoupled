package com.swacorp.dotcom.webscenarios.air

public abstract class SwaBizData {
    abstract Map<String, String> getSwabizDiscountCodeMap()

    public String getDiscountCode(String discountType) {
        return getSwabizDiscountCodeMap().get(discountType)
    }

}
