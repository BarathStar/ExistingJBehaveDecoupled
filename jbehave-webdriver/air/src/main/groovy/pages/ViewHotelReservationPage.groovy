/*Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import domain.AirReservation
import domain.HotelReservation
import state.CurrentHotelPNR

class ViewHotelReservationPage extends BasePage{

    private static final String TRIP_ASSOCIATED_PRODUCTS = 'trip_associated_products'
    private static final By CANCEL_AIR_RESERVATION_LINK = By.id("cancelReservationLink")
    private static final By CONFIRMATION_NUMBER = By.id("confirmationNumber")
    private static final By FIRST_NAME = By.id("firstName")
    private static final By LAST_NAME = By.id("lastName")
    private static final By LOOK_UP_DATE = By.id("lookupDate")
    private final static By SUBMIT_BUTTON = By.id("submitButton")

    ViewHotelReservationPage(WebDriverProvider driverProvider) {
        super(driverProvider, '/hotels/retrieve-hotel-reservation.html')
    }

    String getRelativePath() {
        return "/hotels/retrieve-hotel-reservation.html"
    }

    void verifyAssociatedItemsDisplayed() {
        isElementDisplayed(By.className(TRIP_ASSOCIATED_PRODUCTS)).shouldBe true, "The Associated Items should be visible"
    }

    void clickOnCancelAirProductLink() {
        waitForElement(CANCEL_AIR_RESERVATION_LINK).click()
        verifyPage()
    }

    void enterPnrAndSubmit(CurrentHotelPNR currentHotelPNR){
        String Url = getCurrentUrl()
        fillIn(CONFIRMATION_NUMBER, currentHotelPNR.pnr)
        fillIn(FIRST_NAME, currentHotelPNR.guestFirstName)
        fillIn(LAST_NAME, currentHotelPNR.guestLastName)
        fillIn(LOOK_UP_DATE,currentHotelPNR.getCheckInDateAsString())
        waitForElement(SUBMIT_BUTTON).click()
        waitForUrlToChangeOrOops(Url)
    }
}
