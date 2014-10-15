/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider

import org.openqa.selenium.By

import domain.RapidRewardsAccount

/**
 * This class represents a Security Question page for GetHealthy project
 */

public class SecurityQuestionsPage extends BasePage {

    private static final By SECURITY_QUESTION = By.id("securityAnswer")
    private static final By SECURITY_QUESTION_TWO = By.id("securityAnswer2")
    private static final By SUBMIT_BUTTON = By.id("submit")

    public SecurityQuestionsPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/resetPassQuestions')
    }

    String getRelativePath() {
        return "/account/resetPassQuestions"
    }

    void clickSubmit() {
        waitForElement(SUBMIT_BUTTON).click()
    }

    void fillForm(RapidRewardsAccount member) {
        waitForElement(SECURITY_QUESTION).sendKeys DELETE_EXISTING + member.securityAnswer
        waitForElement(SECURITY_QUESTION_TWO).sendKeys DELETE_EXISTING + member.securityAnswer2
    }
}