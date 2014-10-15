/* Copyright (c) 2012, Southwest Airlines Co.  All rights reserved.*/
package pages.elements.forms

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebElement
import domain.RapidRewardsAccount
import static pages.BasePage.DELETE_EXISTING

/**
 * This class represents a Form for Email Updates to be used in Click 'N Save® and IAN Email Updates Page
 */
public class EmailUpdatesForm extends BasePage {

    private static final By FIRST_NAME = By.cssSelector("input#firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By EMAIL_ADDRESS = By.id("emailAddress")
    private static final By VERIFY_EMAIL_ADDRESS = By.id("verifyEmailAddress")
    private static final By RR_MEMBERSHIP_NUMBER = By.id("customerNumber")
    private static final By IAN_CNS_CHECKBOX = By.id("types1")
    private static final By SUBSCRIBE_BUTTON = By.id("submit")

    def EmailUpdatesForm(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    @Override
    public String getRelativePath() {
        return ''
    }

    public void fillFirstName(String firstName){
       fillInputField(FIRST_NAME, firstName)
    }

    public void fillLastName(String lastName){
       fillInputField(LAST_NAME, lastName)
    }

    public void fillEmailAddres(String emailAddress){
        fillInputField(EMAIL_ADDRESS, emailAddress)
    }

    public void fillVerifyEmailAddres(String emailAddress){
        fillInputField(VERIFY_EMAIL_ADDRESS, emailAddress)
    }

    public void fillRRMembershipNumber(String membershipNumber){
        fillInputField(RR_MEMBERSHIP_NUMBER, membershipNumber)
    }

    private void fillInputField(By fieldName, String fieldValue) {
        try {
            waitForElement(fieldName).sendKeys DELETE_EXISTING + fieldValue
        } catch(StaleElementReferenceException ex) {
            waitForElement(fieldName).sendKeys DELETE_EXISTING + fieldValue
        }
    }

    void selectIanCnsCheckbox() {
        WebElement checkBoxIan = waitForElement(IAN_CNS_CHECKBOX)
        if(!checkBoxIan?.isSelected()) {
            checkBoxIan.click()
        }
        waitForElement(IAN_CNS_CHECKBOX).click()
     }

    void clickSubscribeButton() {
        waitForElement(SUBSCRIBE_BUTTON).click()
    }
}