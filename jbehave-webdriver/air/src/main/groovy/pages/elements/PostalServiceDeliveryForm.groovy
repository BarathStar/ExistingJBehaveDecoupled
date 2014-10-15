/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static pages.BasePage.DELETE_EXISTING
import domain.PostalGiftCardData

/**
* This class represent the form for a gift card creation
* with postal delivery selected like usps or fedex
*/
class PostalServiceDeliveryForm extends CreateGiftCardForm {

    private static final String FIRST_NAME_ID = 'firstName'
    private static final String LAST_NAME_ID = 'lastName'
    private static final String ADDRESS_1_ID = 'address1'
    private static final String ADDRESS_2_ID = 'address2'
    private static final String PHONE_NUMBER_AREA_CODE_ID = 'areaCode'
    private static final String PHONE_NUMBER_PREFIX_ID = 'prefix'
    private static final String PHONE_NUMBER_LINE_NUMBER_ID = 'lineNumber'
    private static final String STATE_ID = 'state'
    private static final String CITY_ID = 'city'
    private static final String ZIP_CODE_5_ID = 'zipCode5'
    private static final String ZIP_CODE_4_ID = 'zipCode4'
    private static final String PERSONAL_MESSAGE_1_ID = "personalMessageMail1"
    private static final String PERSONAL_MESSAGE_2_ID = "personalMessageMail2"
    private static final String PERSONAL_MESSAGE_3_ID = "personalMessageMail3"
    private static final String PERSONAL_MESSAGE_4_ID = "personalMessageMail4"

    public PostalServiceDeliveryForm(WebElement theContainer) {
        super(theContainer)
    }

    private WebElement getFirstName(){
        form.findElement(By.id(FIRST_NAME_ID))
    }
    private WebElement getLastName(){
        form.findElement(By.id(LAST_NAME_ID))
    }
    private WebElement getAddress1(){
        form.findElement(By.id(ADDRESS_1_ID))
    }
    private WebElement getAddress2(){
        form.findElement(By.id(ADDRESS_2_ID))
    }
    private WebElement getPhoneAreaCode(){
        form.findElement(By.id(PHONE_NUMBER_AREA_CODE_ID))
    }
    private WebElement getPhonePrefix(){
        form.findElement(By.id(PHONE_NUMBER_PREFIX_ID))
    }
    private WebElement getPhoneLineNumber(){
        form.findElement(By.id(PHONE_NUMBER_LINE_NUMBER_ID))
    }
    private WebElement getState(){
        form.findElement(By.id(STATE_ID))
    }
    private WebElement getCity(){
        form.findElement(By.id(CITY_ID))
    }
    private WebElement getZipCode5(){
        form.findElement(By.id(ZIP_CODE_5_ID))
    }
    private WebElement getZipCode4(){
        form.findElement(By.id(ZIP_CODE_4_ID))
    }
    private WebElement getPersonalMessage1(){
        form.findElement(By.id(PERSONAL_MESSAGE_1_ID))
    }
    private WebElement getPersonalMessage2(){
        form.findElement(By.id(PERSONAL_MESSAGE_2_ID))
    }
    private WebElement getPersonalMessage3(){
        form.findElement(By.id(PERSONAL_MESSAGE_3_ID))
    }
    private WebElement getPersonalMessage4(){
        form.findElement(By.id(PERSONAL_MESSAGE_4_ID))
    }

    void fillForm(PostalGiftCardData postalGiftCardInfo){
        super.fillForm(postalGiftCardInfo)
        firstName.sendKeys DELETE_EXISTING + postalGiftCardInfo.firstName
        lastName.sendKeys DELETE_EXISTING + postalGiftCardInfo.lastName
        address1.sendKeys DELETE_EXISTING + postalGiftCardInfo.address1
        address2.sendKeys DELETE_EXISTING + postalGiftCardInfo.address2
        state.sendKeys DELETE_EXISTING + postalGiftCardInfo.state
        city.sendKeys DELETE_EXISTING + postalGiftCardInfo.city
        zipCode4.sendKeys DELETE_EXISTING + postalGiftCardInfo.zipCode4
        zipCode5.sendKeys DELETE_EXISTING + postalGiftCardInfo.zipCode5
        phoneAreaCode.sendKeys DELETE_EXISTING + postalGiftCardInfo.phoneAreaCode
        phonePrefix.sendKeys DELETE_EXISTING + postalGiftCardInfo.phonePrefix
        phoneLineNumber.sendKeys DELETE_EXISTING + postalGiftCardInfo.phoneLineNumber

        personalMessage1.sendKeys DELETE_EXISTING + postalGiftCardInfo.personalInfo1
        personalMessage2.sendKeys DELETE_EXISTING + postalGiftCardInfo.personalInfo2
        personalMessage3.sendKeys DELETE_EXISTING + postalGiftCardInfo.personalInfo3
        personalMessage4.sendKeys DELETE_EXISTING + postalGiftCardInfo.personalInfo4
    }
}