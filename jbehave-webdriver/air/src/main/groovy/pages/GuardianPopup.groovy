/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages

import domain.Guardian
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.ItineraryData

/**
 * Represents the Guardian PopUp for an Unaccompanied Minor or Young Traveler
 */
class GuardianPopup {

    private static final String DESCRIPTION_CLASS = "descTextOnGuardianPopup"
    private static final String TITLE_CSS = ".main_content p strong"
    private static final String GUARDIAN_INFO_CLASS = "guardianInfo"
    private static final String DEPART_CITY_ID = "oCity"
    private static final String ARRIVE_CITY_ID = "dCity"
    private static final String MONTH_CLASS = "month"
    private static final String DAY_CLASS = "day"
    private static final String UM_DESCRIPTION = "This information will facilitate the transfer of the Unaccompanied Minor(s) between the designated parties. Once you have completed this purchase, any changes to this information must be completed at the airport."
    private static final String YT_DESCRIPTION = "This is the parent/guardian information you have provided for the Young Traveler. Any changes to this information must be completed at the airport."

    private WebElement popup

    ItineraryData itineraryData
    GuardianAssigned guardianAssigned

    GuardianPopup(WebElement thePopup) {
        popup = thePopup
    }

    void verifyTitle() {
        WebElement title = popup.findElement(By.cssSelector(TITLE_CSS))
        String requieredTitle = "Parent/Guardian Contact Information for Passengers on this Itinerary:"
        title.getText().shouldBe requieredTitle, "The text: '$requieredTitle' not found"
    }

    void verifyYTDescription() {
        WebElement description = popup.findElement(By.className(DESCRIPTION_CLASS))
        description.getText().shouldBe YT_DESCRIPTION, "The text: ${YT_DESCRIPTION} not found"
    }

    void verifyUMDescription() {
        WebElement description = popup.findElement(By.className(DESCRIPTION_CLASS))
        description.getText().shouldBe UM_DESCRIPTION, "The text: ${UM_DESCRIPTION} not found"
    }

    void verifyItinerary() {
        WebElement departCity = popup.findElement(By.id(DEPART_CITY_ID))
        WebElement arrivalCity = popup.findElement(By.id(ARRIVE_CITY_ID))
        WebElement month = popup.findElement(By.className(MONTH_CLASS))
        WebElement day = popup.findElement(By.className(DAY_CLASS))
        String monthName = itineraryData.departureDate.format("MMM").toUpperCase()
        String dayNumber = itineraryData.departureDate.format("d")
        departCity.getText().shouldContain itineraryData.departureStation, "The departure station should be ${itineraryData.departureStation}"
        arrivalCity.getText().shouldContain itineraryData.arrivalStation, "The arrive station should be ${itineraryData.arrivalStation}"
        month.getText().shouldBe monthName, "The month should be $monthName"
        day.getText().shouldBe dayNumber, "The day should be $dayNumber"
    }

    void verifyYTGuardianInfo(Guardian guardian) {
        List<WebElement> guardians = popup.findElements(By.className(GUARDIAN_INFO_CLASS))
        GuardianAssigned assigned = new GuardianAssigned()
        assigned.verifyGuardianInfo(guardians.get(0), guardian)
    }

    void verifyUMGuardiansInfo(Guardian dropOffGuardian, Guardian pickUpGuardian, Guardian alternateGuardian) {
        List<WebElement> guardians = popup.findElements(By.className(GUARDIAN_INFO_CLASS))
        GuardianAssigned assigned = new GuardianAssigned()
        assigned.verifyGuardianInfo(guardians.get(0), dropOffGuardian)
        assigned.verifyGuardianInfo(guardians.get(1), pickUpGuardian)
        assigned.verifyGuardianInfo(guardians.get(2), alternateGuardian)
    }
}
