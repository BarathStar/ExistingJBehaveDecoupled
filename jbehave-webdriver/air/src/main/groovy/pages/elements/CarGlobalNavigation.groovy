package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement


class CarGlobalNavigation {

    private static final By BOOK_A_SHUTTLE_LINK = By.cssSelector('#globalnav_header_primary_link_car_hover_container a[href*="southwest.iseatz.com"]')
    private WebElement container

    CarGlobalNavigation(WebElement theContainer){
        container=theContainer
    }
}
