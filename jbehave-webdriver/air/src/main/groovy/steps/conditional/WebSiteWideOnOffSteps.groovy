package steps.conditional

import org.jbehave.core.annotations.AfterStory
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When

import fixture.stubs.DynaStubsIntegration
import com.swacorp.dotcom.webscenarios.air.PromoDiscountData
import util.ItineraryData


class WebSiteWideOnOffSteps {


    private boolean webSiteWideEnabled
    private String webSiteWidePromoCode

    PromoDiscountData discountData
    ItineraryData itineraryData




    @Given("Website wide promo \$promoCode is enabled")
    @When("Website wide promo \$promoCode is enabled")
    def enableWebSiteWidePromo(String promoCode) {
        new DynaStubsIntegration().prepareDiscount(discountData.getDiscount(promoCode))
        webSiteWideEnabled = true;
        webSiteWidePromoCode = promoCode;
    }



    @AfterStory
    def disable_website_wide_if_enabled() {
        if(webSiteWideEnabled){
            new DynaStubsIntegration().removeDiscount(discountData.getDiscount(webSiteWidePromoCode))
            webSiteWideEnabled = false
        }

    }
}
