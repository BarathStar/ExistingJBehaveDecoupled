/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages

import domain.Guardian
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

/**
* Represents an Guardian assigned for an Unaccompanied Minor or Young Traveler
*/
class GuardianAssigned {

    private static final String FIRST_NAME_ID = "first_name"
    private static final String LAST_NAME_ID = "last_name"
    private static final String RELATIONSHIP_ID = "relationship"
    private static final String PHONE_ID = "phone"
    private static final String ADDRESS_ID = "address"
    private static final String CITY_ID = "city"
    private static final String STATE_ID = "state"
    private static final String ZIP_ID = "zip"

    void verifyGuardianInfo(WebElement guardianContainer, Guardian guardian) {
        WebElement firstName = guardianContainer.findElement(By.id(FIRST_NAME_ID))
        WebElement lastName = guardianContainer.findElement(By.id(LAST_NAME_ID))
        WebElement relationship = guardianContainer.findElement(By.id(RELATIONSHIP_ID))
        WebElement phone = guardianContainer.findElement(By.id(PHONE_ID))
        WebElement addressLine = guardianContainer.findElement(By.id(ADDRESS_ID))
        WebElement city = guardianContainer.findElement(By.id(CITY_ID))
        WebElement state = guardianContainer.findElement(By.id(STATE_ID))
        WebElement zipCode = guardianContainer.findElement(By.id(ZIP_ID))
        String phoneNumber = "(${guardian.contactAreaCode}) ${guardian.contactPrefix}-${guardian.contactLineNumber}"
        firstName.getText().shouldBe guardian.firstName, "The first name of the guardian should be ${guardian.firstName}"
        lastName.getText().shouldBe guardian.lastName, "The last name of the guardian should be ${guardian.lastName}"
        relationship.getText().shouldBe guardian.relationship, "The relationship of the guardian should be ${guardian.relationship}"
        phone.getText().shouldBe phoneNumber, "The phone area code of the guardian should be $phoneNumber"
        addressLine.getText().shouldBe guardian.addressLine1, "The address of the guardian should be ${guardian.addressLine1}"
        city.getText().shouldBe guardian.city, "The city of the guardian should be ${guardian.city}"
        state.getText().shouldBe guardian.state, "The state of the guardian should be ${guardian.state}"
        zipCode.getText().shouldBe guardian.zipCode1, "The zip code of the guardian should be ${guardian.zipCode1}"
    }
}
