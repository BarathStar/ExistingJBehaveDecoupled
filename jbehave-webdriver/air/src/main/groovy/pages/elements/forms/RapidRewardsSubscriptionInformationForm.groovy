package pages.elements.forms

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import static pages.BasePage.DELETE_EXISTING
import org.jbehave.web.selenium.WebDriverProvider
import pages.BasePage

class RapidRewardsSubscriptionInformationForm extends BasePage {

    private static final By FIRST_NAME_FIELD = By.id("firstName")
    private static final By LAST_NAME_FIELD = By.id("lastName")
    private static final By CUSTOMER_NUMBER_FIELD = By.id("customerNumber")
    private static final By FIRST_EMAIL_ADDRESS_FIELD = By.id("emailAddress0")
    private static final By SECOND_EMAIL_ADDRESS_FIELD = By.id("emailAddress1")
    private static final By THIRD_EMAIL_ADDRESS_FIELD = By.id("emailAddress2")
    private static final By FIRST_CHECKBOX_RR_EMAIL_UPDATE = By.id("emailSubscriptions0.receiveRapidRewardsUpdate1")
    private static final By SECOND_CHECKBOX_RR_EMAIL_UPDATE = By.id("emailSubscriptions1.receiveRapidRewardsUpdate1")
    private static final By THIRD_CHECKBOX_RR_EMAIL_UPDATE = By.id("emailSubscriptions2.receiveRapidRewardsUpdate1")
    private static final By FIRST_CHECKBOX_RR_REPORT = By.id("emailSubscriptions0.receiveRapidRewardsReport1")
    private static final By SECOND_CHECKBOX_RR_REPORT = By.id("emailSubscriptions1.receiveRapidRewardsReport1")
    private static final By THIRD_CHECKBOX_RR_REPORT = By.id("emailSubscriptions2.receiveRapidRewardsReport1")

    def RapidRewardsSubscriptionInformationForm(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    @Override
    public String getRelativePath() {
        return ''
    }

    public void fillFirstNameField(String firstName){
        waitForElement(FIRST_NAME_FIELD).sendKeys DELETE_EXISTING + firstName
    }

    public void fillLastNameField(String lastName){
        waitForElement(LAST_NAME_FIELD).sendKeys DELETE_EXISTING + lastName
    }

    public void fillCustomerNumberField(String customerName){
        waitForElement(CUSTOMER_NUMBER_FIELD).sendKeys DELETE_EXISTING + customerName
    }

    public void fillFirstEmailAddressField(String emailAddress){
        waitForElement(FIRST_EMAIL_ADDRESS_FIELD).sendKeys DELETE_EXISTING + emailAddress
    }

    public void fillSecondEmailAddressField(String emailAddress){
        waitForElement(SECOND_EMAIL_ADDRESS_FIELD).sendKeys DELETE_EXISTING + emailAddress
    }

    public void fillThirdEmailAddressField(String emailAddress){
        waitForElement(THIRD_EMAIL_ADDRESS_FIELD).sendKeys DELETE_EXISTING + emailAddress
    }

    public void selectFirstBoxRapidRewardsEmailUpdate(){
        selectCheckBoxElement(FIRST_CHECKBOX_RR_EMAIL_UPDATE)
    }

    public void selectSecondBoxRapidRewardsEmailUpdate(){
        selectCheckBoxElement(SECOND_CHECKBOX_RR_EMAIL_UPDATE)
    }

    public void selectThirdBoxRapidRewardsEmailUpdate(){
        selectCheckBoxElement(THIRD_CHECKBOX_RR_EMAIL_UPDATE)
    }

    public void selectFirstBoxRapidRewardsReport(){
        selectCheckBoxElement(FIRST_CHECKBOX_RR_REPORT)
    }

    public void selectSecondBoxRapidRewardsReport(){
        selectCheckBoxElement(SECOND_CHECKBOX_RR_REPORT)
    }

    public void selectThirdBoxRapidRewardsReport(){
        selectCheckBoxElement(THIRD_CHECKBOX_RR_REPORT)
    }

    private void selectCheckBoxElement(By checkBoxId){
        WebElement checkBoxRapidRewardsReport = waitForElement(checkBoxId)
        if(!checkBoxRapidRewardsReport?.isSelected()) {
            checkBoxRapidRewardsReport.click()
        }
    }

}