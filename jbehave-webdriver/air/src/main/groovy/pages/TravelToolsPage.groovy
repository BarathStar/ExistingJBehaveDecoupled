/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class TravelToolsPage extends BasePage {

    private static final String PAGE_RELATIVE_PATH = "/html/travel-tools/index.html"
    private final By TRAVEL_EXTRAS_LINK_XPATH = By.xpath("//a[@title = 'Travel Extras Footer Link']")
    private final By BOOK_AIR_LOG_INTO_SOUTHWEST_LINK = By.xpath("//a[@id = 'book-air-log-into-southwest']")
    private final By BOOK_CAR_LOG_INTO_SOUTHWEST_LINK = By.xpath("//a[@id = 'book-car-log-into-southwest']")
    private final By BOOK_HOTEL_LOG_INTO_SOUTHWEST_LINK = By.xpath("//a[@id = 'book-hotel-log-into-southwest']")
    private final By VACATIONS_SECTION_TITLE = By.cssSelector(".swa_module_travelTools_links h2:nth-of-type(5)")

    String getRelativePath() {
        return PAGE_RELATIVE_PATH
    }

    def TravelToolsPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider, PAGE_RELATIVE_PATH)
    }

    void clickTravelExtrasLink(){
        waitForElement(TRAVEL_EXTRAS_LINK_XPATH).click()
    }

    def void clickOnBookAirLogIntoMySouthwestLink() {
        waitForElement(BOOK_AIR_LOG_INTO_SOUTHWEST_LINK).click()
    }

    def void clickOnBookCarLogIntoMySouthwestLink() {
        waitForElement(BOOK_CAR_LOG_INTO_SOUTHWEST_LINK).click()
    }

    def void clickOnBookHotelLogIntoMySouthwestLink() {
        waitForElement(BOOK_HOTEL_LOG_INTO_SOUTHWEST_LINK).click()
    }

    def verifyVacationsSectionTitle() {
        String sectionTitle = waitForElement(VACATIONS_SECTION_TITLE).getText()
        sectionTitle.shouldBe "Vacations", "Section title is different than 'Vacations'."
    }
}
