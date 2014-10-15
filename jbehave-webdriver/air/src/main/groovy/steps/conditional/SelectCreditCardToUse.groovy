package steps.conditional

import state.CreditCardToUse
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.BeforeStory
import state.Flow

class SelectCreditCardToUse {

    CreditCardToUse creditCardToUse
    Flow flow

    @BeforeStory
    void selectDefaultCreditCard() {
        creditCardToUse.acceptable = true
    }

    @Given('my credit card is unacceptable')
    void selectUnacceptableCreditCard() {
        flow.completePurchase = false
        creditCardToUse.acceptable = false
    }


}
