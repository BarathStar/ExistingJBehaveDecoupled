package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class AddressValidationReviewPage extends BasePage{

    private static final By CONFIRM_BUTTON =  By.id("createButton")
    private static final By EDIT_BUTTON =  By.id("editButtonReview")
    private static final By PAGE_TITLE = By.id("page_title")
    private static final String REVIEW_PAGE_TITLE = "Review and Create Account"

    public AddressValidationReviewPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/account/enroll/review-address-validation')
    }

    def open() {
        super.open()
        checkSomethingServed()
    }

    def clickConfirm(){
        waitForElement(CONFIRM_BUTTON).click()
    }

    def clickEdit(){
        waitForElement(EDIT_BUTTON).click()
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    boolean isCurrentPage() {
        isElementWithTextPresent(PAGE_TITLE, REVIEW_PAGE_TITLE)
    }

    def verifyOnCurrentPage() {
        isCurrentPage().shouldBe true, "You should be in the review page"
    }


}
