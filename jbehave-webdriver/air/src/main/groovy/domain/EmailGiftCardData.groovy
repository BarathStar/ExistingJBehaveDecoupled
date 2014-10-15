/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package domain
/**
* This class Represents the required data for a gift card creation
* with email delivery selected
*/
class EmailGiftCardData extends GiftCardData {

    String recipientEmail
    String confirmEmail

    public EmailGiftCardData() {
        super()
    }

    public static EmailGiftCardData createEmailGiftCardData(){
        EmailGiftCardData data = new EmailGiftCardData()
        data.amount = 11
        data.quantity = 1
        data.emailTo = ""
        data.emailFrom = ""
        data.recipientEmail = "test@test.com"
        data.confirmEmail = "test@test.com"
        return data
    }

}
