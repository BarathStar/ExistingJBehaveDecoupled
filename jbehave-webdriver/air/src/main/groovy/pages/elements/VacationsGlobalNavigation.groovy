/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class VacationsGlobalNavigation {

    static final By BOOK_A_CRUISE_NAV_LINK = By.cssSelector('#globalnav_header_primary_link_vacations_hover_container a[title="Book a Cruise Primary Navigation Link"]')
    static final By SECOND_COLUMN_OF_LINKS = By.cssSelector('#globalnav_header_primary_link_vacations_hover_container td.globalnav_header_subnav_hover_container_layout_table_center ul:nth-of-type(2)')
    private WebElement container

    VacationsGlobalNavigation(WebElement theContainer){
        container=theContainer
    }
}
