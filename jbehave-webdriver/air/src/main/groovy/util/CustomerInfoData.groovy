package util

import org.openqa.selenium.By

class CustomerInfoData {

    private static final By CREDIT_CARD = By.id("creditCard")
    private static final By PAYPAL = By.id("payPalRadioButton")

    public formOfContact = ""
    public String formOfPayment

    By retrieveFormOfPaymentSelector() {
        formOfPayment.equalsIgnoreCase("paypal") ? PAYPAL
         : formOfPayment.equalsIgnoreCase("CC") ? CREDIT_CARD : null
    }

}
