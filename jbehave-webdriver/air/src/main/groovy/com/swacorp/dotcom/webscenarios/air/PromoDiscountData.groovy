package com.swacorp.dotcom.webscenarios.air

import fixtures.air.DiscountSpecification

public class PromoDiscountData {

    private Map<String, DiscountSpecification> discountMap = [
            ("SOUTHWEST") : new DiscountSpecification("12346", "SOUTHWEST", true, "1000", "Today", "60", false, "Enjoy the discount", "MULTI_USE", "ACTIVE", "SOUTHWEST", "DOLLARS", "PROMONOW", true),
            ("SWAPOINTS") : new DiscountSpecification("54321", "SWAPOINTS", true, "1000", "Today", "60", false, "Enjoy the discount for Points", "MULTI_USE", "ACTIVE", "SOUTHWEST", "POINTS", "PROMONOW", true),
            ("SWABIZ") : new DiscountSpecification("67863", "SWABIZ", true, "100", "Today", "60", false, "Discount for SWABIZ only", "MULTI_USE", "ACTIVE", "SWABIZ", "DOLLARS", "PROMONOW", false),
            ("BOTH") : new DiscountSpecification("798746", "BOTH", true, "10", "Today", "30", true, "Rapid Rewards discount for Southwest and SWABIZ", "MULTI_USE", "ACTIVE", "BOTH", "DOLLARS", "PROMONOW", true),
            ("WEBSITEWIDE") : new DiscountSpecification("87990287", "WEBSITEWIDE", true, "10000", "Today", "60", false, "A website wide discount has been applied :)", "WEB_SITE_WIDE", "ACTIVE", "SOUTHWEST", "BOTH", "87990287", false),
            ("WEBSITEWIDESWABIZ") : new DiscountSpecification("897465798", "WEBSITEWIDESWABIZ", true, "10000", "Today", "60", false, "A website wide discount has been applied :)", "WEB_SITE_WIDE", "ACTIVE", "SWABIZ", "BOTH", "897465798", false)
        ]

    DiscountSpecification getDiscount(String discount) {
        return discountMap.get(discount)
    }
}
