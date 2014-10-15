package pages.elements

import com.swacorp.dotcom.webscenarios.air.Data
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.SortablePage
import state.Flow
import fixtures.air.ReservationSpecification
import util.CustomerInfoData
import util.ItineraryData
import steps.conditional.TogglePreferenceCenter

public class ContactInfo extends SortablePage {

    private static final By CONTACT_AREA_CODE = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-area-code") : By.id("contactAreaCode")
    private static final By CONTACT_PREFIX = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-prefix") : By.id("contactPrefix")
    private static final By CONTACT_LINE_NUMBER = TogglePreferenceCenter.isOn() ? By.id("js-us-phone-line-number") : By.id("usPhoneLineNumber")
    private static final String CONTACT_METHOD = TogglePreferenceCenter.isOn() ? "js-preferred-method-of-contact--contact-method" : "contactMethod"

    Data data
    Flow flow
    CustomerInfoData customerInfoData

    public ContactInfo(WebDriverProvider driverProvider) {
        super(driverProvider, SortablePage.CONTACT_INFO_ORDER)
    }

    String getRelativePath() {
        return ""
    }

    void fillForm() {
        if (flow.hasHotel) {
            waitForElement(CONTACT_AREA_CODE).sendKeys DELETE_EXISTING + "972"
            waitForElement(CONTACT_PREFIX).sendKeys DELETE_EXISTING + "312"
            waitForElement(CONTACT_LINE_NUMBER).sendKeys DELETE_EXISTING + "1111"
        }
        //verifying contact method field for account purchase point
        if (flow.hasAir && isElementDisplayed(By.id(CONTACT_METHOD))) {
            if (customerInfoData.formOfContact != "") {
                chooseInDropDownByValue(CONTACT_METHOD, customerInfoData.formOfContact)
                if (flow.isLoggedIn || flow.isRapidRewards) return
            }
            chooseInDropDownByValue(CONTACT_METHOD, "Call")
            waitForElement(CONTACT_AREA_CODE).sendKeys DELETE_EXISTING + "972"
            waitForElement(CONTACT_PREFIX).sendKeys DELETE_EXISTING + "312"
            waitForElement(CONTACT_LINE_NUMBER).sendKeys DELETE_EXISTING + "1111"
        }

    }

    void fillFormBasedOn(ReservationSpecification specification) {
        fillForm()
    }
}
