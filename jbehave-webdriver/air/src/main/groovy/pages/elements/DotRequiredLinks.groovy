package pages.elements

import pages.BasePage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

public class DotRequiredLinks extends BasePage {

    DotRequiredLinks(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    DotRequiredLinks(WebDriverProvider driverProvider, String pagePath) {
        super(driverProvider, pagePath)
    }

    private isAnyLinkDisplayed(String page) {
        List<WebElement> links = waitForElements(By.xpath("//a[contains(@href, '${page}')]"))
        return links.any() { WebElement link -> link.isDisplayed() }
    }

    private isAnyLinkWithTargetDisplayed(String page) {
        List<WebElement> links = waitForElements(By.xpath("//a[contains(@href, '${page}')]"))
        return links.any() { WebElement link -> link.isDisplayed() && link.getAttribute("target").length() > 0 }
    }

    def isAnyCheckedBagsLinkPresentWithTarget() {
        return isAnyLinkWithTargetDisplayed("checked-bags-pol.html")
    }

    def isAnyPointsCalculationLinkDisplayed() {
        return isAnyLinkWithTargetDisplayed("pop_totalItineraryPointsDetails.html")
    }

    def getAnyWhatYouSeeIsWhatYouPayLink() {
        return waitForElements(By.cssSelector("a")).find() {
            link -> link.isDisplayed() && link.getAttribute("href").contains("fullFareDisclosure.html")
        }
    }

    def clickWhatYouSeeWhatYouPayLink(){
        getAnyWhatYouSeeIsWhatYouPayLink().click()
    }



}
