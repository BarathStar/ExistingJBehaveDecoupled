/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import pages.BasePage
import steps.conditional.ToggleGlobalNav

/**
 * Facade to the different implementations of GlobalNavFooter accessors
 */
class GlobalNavigationFooter extends BasePage {

    private GlobalNavigationFooterNew globalNavigationFooterNew;
    private GlobalNavigationFooterOld globalNavigationFooterOld;

    GlobalNavigationFooter(WebDriverProvider driverProvider) {
        super(driverProvider)

        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew = new GlobalNavigationFooterNew(driverProvider)
        } else {
            globalNavigationFooterOld = new GlobalNavigationFooterOld(driverProvider)
        }
    }

    @Override
    public String getRelativePath() {
        return ""
    }

    void ensureThatLinksAreExpandedOnFooter() {
            globalNavigationFooterOld.ensureThatLinksAreExpandedOnFooter()
    }

    void clickOnSouthwestGiftCardLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnSouthwestGiftCardLink()
        } else {
            globalNavigationFooterOld.clickOnSouthwestGiftCardLink()
        }
    }

    void clickOnSupplierInformationLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnSupplierInformationLink()
        } else {
            globalNavigationFooterOld.clickOnSupplierInformationLink()
        }
    }

    void clickOnNeedHelpLogginLink() {
        globalNavigationFooterOld.clickOnNeedHelpLogginLink()
    }

    void clickOnCareerLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnCareerLink()
        } else {
            globalNavigationFooterOld.clickOnCareerLink()
        }
    }

    void clickOnWhyFlyLink() {
        globalNavigationFooterOld.clickOnWhyFlyLink()
    }

    def verifyEnrollLinkIsPresent() {
        globalNavigationFooterOld.verifyEnrollLinkIsPresent()
    }

    def verifyEnrollLinkIsNotPresent() {
        globalNavigationFooterOld.verifyEnrollLinkIsNotPresent()
    }

    def clickSouthWestMerchandiseLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickSouthWestMerchandiseLink()
        } else {
            globalNavigationFooterOld.clickSouthWestMerchandiseLink()
        }
    }

    def clickOnLostAndFoundLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnLostAndFoundLink()
        } else {
            globalNavigationFooterOld.clickOnLostAndFoundLink()
        }
    }

    def clickOnAdvertiseWithSouthwestLinkFooter() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnAdvertiseWithSouthwestLinkFooter()
        } else {
            globalNavigationFooterOld.clickOnAdvertiseWithSouthwestLinkFooter()
        }
    }

    def clickOnSpecialAssistanceLink() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.clickOnSpecialAssistanceLink()
        } else {
            globalNavigationFooterOld.clickOnSpecialAssistanceLink()
        }
    }

    def verifySpecialAssistancePage() {
        if(ToggleGlobalNav.isOn()) {
            globalNavigationFooterNew.verifySpecialAssistancePage()
        } else {
            globalNavigationFooterOld.verifySpecialAssistancePage()
        }
    }

    def clickNewFooterContactsLinks() {
        globalNavigationFooterNew.clickFooterContactsLinks()
    }

    def clickNewFooterPartOneGeneralLinks() {
        globalNavigationFooterNew.clickFooterPartOneGeneralLinks()
    }

    def clickNewFooterPartTwoGeneralLinks() {
        globalNavigationFooterNew.clickFooterPartTwoGeneralLinks()
    }

    def validateNoNewFooterContactsLinksErrors() {
        globalNavigationFooterNew.validateNoFooterContactsLinksErrors()
    }

    def validateNoNewFooterPartOneGeneralLinksErrors() {
        globalNavigationFooterNew.validateNoFooterPartOneGeneralLinksErrors()
    }

    def validateNoNewFooterPartTwoGeneralLinksErrors() {
        globalNavigationFooterNew.validateNoFooterPartTwoGeneralLinksErrors()
    }

    def clickOnInfantFlyingLink() {
        globalNavigationFooterNew.clickOnInfantFlyingLink()
    }

    def clickAirportInformationLink() {
        globalNavigationFooterNew.clickAirportInformationLink()
    }

    def verifyInfantFlyingPage() {
        globalNavigationFooterNew.verifyInfantFlyingPage()
    }
}