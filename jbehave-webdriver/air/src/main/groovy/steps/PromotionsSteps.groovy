package steps;

import org.jbehave.core.annotations.When
import pages.PromotionsPage

public class PromotionsSteps {

    PromotionsPage promotionsPage

    @When("I access my account's Promotions Page")
    def openPromotionsPage() {
        promotionsPage.open()
        promotionsPage.verifyPage()
    }
}
