package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class ProductPage extends BasePage {
    private static final By LINK_BUSINESS_SELECT = By.xpath("//a[@id='Business Select']")

    public ProductPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/html/air/products/index.html');
    }

    @Override
    public String getRelativePath() {
        // TODO Auto-generated method stub
        return null;
    }

    def openProductPage() {
        open()
        verifyPage()
    }

    def clickOnBusinessSelectLink(){
        String title = getTitle()
        waitForElement(LINK_BUSINESS_SELECT).click()
        waitForPageTitleToChangeOrOops(title)
    }
}
