package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad
import state.Flow

class SwaBizHomePage extends BasePage{

    private static final String PATH = '/html/bizhome.html'
    private static final By ENROLL_NOW_BUTTON = By.linkText("Enroll Now")
    private static final By BOOK_TRAVEL = By.cssSelector('a[id="book_travel_active"]')
    Flow flow
    SwaBizPage swaBizPage

    SwaBizHomePage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def open() {
        flow.isSwabiz = true
        get(Domains.swabizDomain + PATH)
        WaitForPageToLoad
    }

    def clickOnEnrollNowButton(){
        waitForElement(ENROLL_NOW_BUTTON).click()
    }

    def clickOnBookTravel(){
        waitForElement(BOOK_TRAVEL).click();
    }

    def anonymousLogIn() {
        swaBizPage.enterCompanyId()
    }

}
