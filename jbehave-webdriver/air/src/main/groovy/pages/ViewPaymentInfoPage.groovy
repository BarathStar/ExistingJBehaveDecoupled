package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.InternalReferenceNumberForm
import pages.MyAccountPage

class ViewPaymentInfoPage extends BasePage {

    private static final By EDIT_LINK = By.className("editLink")
    private static final By CANCEL_LINK = By.id("cancel")
    private static final By SUBMIT_BUTTON = By.id("submitForm")
    private static final By CREDIT_CARD_FORM_CONTENT = By.className("formContent");
    private static final By PRIMARY_CREDIT_CARD_DELETE_CONTAINER = By.className("primary-card")
    private static final By PRIMARY_IRN_SET = By.xpath('//div[@class="numberedHeaderPrimary"]/img')
    private static final String CREDIT_CARD_DELETE = "delete"
    InternalReferenceNumberForm internalReferenceNumberForm
    MyAccountPage myAccountPage

    ViewPaymentInfoPage(WebDriverProvider driverProvider) {
        super(driverProvider, "account/info/view-payment-info")
    }

    @Override
    String getRelativePath() {
        return "account/info/view-payment-info"
    }

    def verifyEditLinkIsPresent() {
        isElementPresent(EDIT_LINK).shouldBe true, "The edit link should be present"
    }

    def verifyCancelLinkIsNotPresent() {
        isElementPresent(CANCEL_LINK).shouldBe false, "The cancel link should not be present"
    }

    def verifySubmitButtonIsNotPresent() {
        isElementPresent(SUBMIT_BUTTON).shouldBe false, "The Submit button should not be present"
    }

    def verifyIRNsOrderByAlphabeticalNumerical() {
        List listn =   internalReferenceNumberForm.getAllIRNs()
        String previus = " "
        boolean flag = true
        listn.eachWithIndex { it, i ->
           if(i != 0) {
                String current =  it.getText()
                if(current.compareTo(previus) < 0){
                  flag = false
                }
                previus = current
           }
        }
       flag.shouldBe true,"Error the IRN list is not displayed in aphabetical/numerical order "
    }

    def addOtherIRN(String name, String description, String position) {
        myAccountPage.clickOnAddEditLink("IRN")
        internalReferenceNumberForm.clickOnAddIRN()
        internalReferenceNumberForm.fillOtherIRN(name,description,position)
        myAccountPage.clickOnUpdateBillingInfo()
    }

    def verifyPrimaryCreditCard(){
        isElementPresent(PRIMARY_CREDIT_CARD_DELETE_CONTAINER).shouldBe true, "Primary credit card should be present"
    }

    def verifyNumberOfCreditCardsIs(int numberOfCards) {
        List<WebElement> creditCards = waitForElement(CREDIT_CARD_FORM_CONTENT).findElements(By.tagName("table"))
        (creditCards.size() == numberOfCards).shouldBe  true, "Should have " + numberOfCards + " credit cards"
    }


    def clickOnPrimaryCreditCardDeleteLink(){
        waitForElement((PRIMARY_CREDIT_CARD_DELETE_CONTAINER.className(CREDIT_CARD_DELETE))).click()
        webDriver().switchTo().alert().accept()
    }

    def deletePrimaryIRN(){
        myAccountPage.clickOnAddEditLink("IRN")
        internalReferenceNumberForm.fillOtherIRN('','','0')
        myAccountPage.clickOnUpdateBillingInfo()
    }
    
    def verifyPrimaryIRN(){
        checkPrimaryIRNIsSet().shouldBe true, "Primary IRN should be present"
    }

    def checkPrimaryIRNIsSet(){
        try {
            waitForElement(PRIMARY_IRN_SET)
            return true
        } catch(WebDriverException){
            return false
        }
    }
}
