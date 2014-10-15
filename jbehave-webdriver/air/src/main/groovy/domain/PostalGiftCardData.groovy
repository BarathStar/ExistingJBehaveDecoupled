/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package domain
/**
* This class Represents the required data for a gift card creation
* with postal delivery selected like usps and fedex
*/
class PostalGiftCardData extends GiftCardData {

    String firstName
    String lastName
    String address1
    String address2
    String state
    String city
    String zipCode4
    String zipCode5
    String phoneAreaCode
    String phonePrefix
    String phoneLineNumber
    String personalInfo1
    String personalInfo2
    String personalInfo3
    String personalInfo4

    public PostalGiftCardData() {
        super()
    }

    public static PostalGiftCardData createPostalGiftCardData( ){
        PostalGiftCardData data = new PostalGiftCardData()
        data.amount = 11
        data.quantity = 1
        data.emailTo = ""
        data.emailFrom = ""
        data.firstName = "Sam"
        data.lastName = "Leapfrog"
        data.address1 = "2707 love road bv"
        data.address2 = ""
        data.state = "TX"
        data.city = "Dallas"
        data.zipCode4 = ""
        data.zipCode5 = "11111"
        data.phoneAreaCode = "111"
        data.phonePrefix = "222"
        data.phoneLineNumber = "3333"
        return data
    }
}
