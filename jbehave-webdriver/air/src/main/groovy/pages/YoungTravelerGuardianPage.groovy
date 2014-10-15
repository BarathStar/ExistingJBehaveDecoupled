/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages

import domain.Guardian
import org.jbehave.web.selenium.WebDriverProvider
import pages.elements.GuardianForm
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

class YoungTravelerGuardianPage extends GuardianPage {
    
    private static final String WINDOWS_TITLE_MSG = 'Southwest Airlines - Unaccompanied Young Traveler Parent/Guardian'
    private static final By YT_POLICIES_MESSAGE =  By.className("swa_errors_informationalMessage")

    private WebDriverProvider provider

    def YoungTravelerGuardianPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
        this.provider = webDriverProvider
    }

    String getRelativePath() {
        return "flight/ytDetails.html"
    }

    void fillForm(Guardian guardian) {
        GuardianForm guardianForm = GuardianForm.createYoungTravelerForm(provider)
        guardianForm.fillForm(guardian)
    }

    void changeContactMethod(Guardian guardian) {
        GuardianForm guardianForm = GuardianForm.createYoungTravelerForm(provider)
        guardianForm.changeContactMethod(guardian)
    }

    void verifyInfoPreviouslyEnteredCorrectOnGuardianForm(Guardian guardian) {
        GuardianForm guardianForm = GuardianForm.createYoungTravelerForm(provider)
        guardianForm.verifyInfo(guardian)
    }
    
    void verifyYtGuardianFormTitle() {
        getTitle().shouldBe WINDOWS_TITLE_MSG, "The windows title should be ${WINDOWS_TITLE_MSG}"
    }

    void verifyYtPoliciesMessageIsShown(){
        isElementPresent(YT_POLICIES_MESSAGE).shouldBe true , "The young traveler policies message should be shown"
    }

    void verifyOopsMandatoryFields() {
        def oopsMessages = [
            'The first name of the parent/guardian was not entered.',
            'The last name of the parent/guardian was not entered.',
            'The relationship between the Young Traveler and the parent/guardian was not entered.',
            'The phone number of the parent/guardian was not entered completely.',
            'The address of the parent/guardian was not entered.',
            'The city of the parent/guardian was not entered.',
            'The state of the parent/guardian was not entered.',
            'The zip code of the parent/guardian was not entered.',
            'The preferred method of contact of the parent/guardian was not selected.'
        ]
        verifyOopsMessages(oopsMessages)
    }
}
