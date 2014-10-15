/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

class SiteMapPage extends BasePage {

    private static final String PATH = '/html/customer-service/sitemap.html'
    private static final By VACATIONS_SECTION_TITLE = By.cssSelector('h3 a[href="/flight/vacationspackages.html"]')

    SiteMapPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH)
    }

    @Override
    String getRelativePath() {
        return PATH
    }

    def openSiteMapPage() {
        open()
        verifyPage()
    }

    def verifyVacationsSectionTitle() {
        String vacationSectionTitle = waitForElement(VACATIONS_SECTION_TITLE).getText()
        vacationSectionTitle.shouldBe "Vacations", "Vacations section title is different than 'Vacations'."
    }
}