/*
 * Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.
 */

package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By

class CustomersDisabilitiesPage extends BasePage {

    private static final String PATH = "/html/customer-service/unique-travel-needs/"
    private static final String CUSTOMERS_DISABILITIES_TITLE = "Customers with Disabilities"

    private static final By ASSISTANT_ANIMALS_SECTION = By.cssSelector("#unique_travel_needs_allergies_disabilities_pol_tab_list_tab_10 h2")
    private static final String ASSISTANCE_ANIMALS_TEXT = "Trained Assistance Animals"

    public CustomersDisabilitiesPage(WebDriverProvider driverProvider) {
        super(driverProvider, PATH);
    }

    def verifyAssistantAnimalsSection() {
        switchToWindow(CUSTOMERS_DISABILITIES_TITLE,6500)
        verifyPageTitle(CUSTOMERS_DISABILITIES_TITLE)
        waitForElement(ASSISTANT_ANIMALS_SECTION).getText().shouldContain ASSISTANCE_ANIMALS_TEXT, "The Assistant Animals section was not found using unique_travel_needs_allergies_disabilities_pol_tab_list_tab_10 id element"
    }

}
