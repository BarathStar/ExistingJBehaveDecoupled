package pages.elements.drawers

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

class ManageTravelDrawer {

    public static def BY_MY_TRAVEL_ID = By.id("right_nav_carhotel_tools_mytravel_header")

    WebElement element

    ManageTravelDrawer(WebElement element) {
        this.element = element
    }

    void expand() {
        element.click()
    }

    def hasSectionWithTitle(String sectionName) {
        element.findElements(By.className("myTravelHeaderTitle")).any { it.text.contains(sectionName) }
    }
}