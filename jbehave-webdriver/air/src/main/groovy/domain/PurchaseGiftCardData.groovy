package domain

import com.swacorp.dotcom.webscenarios.air.CreditCard
import com.swacorp.dotcom.webscenarios.air.CreditCardData

class PurchaseGiftCardData extends GiftCardData{

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
    String email
    CreditCard creditCard
    String emailConfirmation

    public static createBillingGiftCardData(){
        PurchaseGiftCardData giftCardData = new PurchaseGiftCardData()
        giftCardData.firstName = "Sam"
        giftCardData.lastName = "Leapfrog"
        giftCardData.address1 = "2707 love road bv"
        giftCardData.address2 = ""
        giftCardData.state = "Texas"
        giftCardData.city = "Dallas"
        giftCardData.zipCode4 = "1234"
        giftCardData.zipCode5 = "11111"
        giftCardData.phoneAreaCode = "111"
        giftCardData.phonePrefix = "222"
        giftCardData.phoneLineNumber = "3333"
        giftCardData.email = "a@b.com"
        giftCardData.emailConfirmation = "a@b.com"
        giftCardData.creditCard = new CreditCard(CreditCard.TYPE.VISA, "4111 1111 1111 1111", 3, CreditCardData.generateValidExpirationYear(), "123")
        return giftCardData
    }

    static PurchaseGiftCardData createInvalidBillingGiftCardData() {
        PurchaseGiftCardData giftCardData = new PurchaseGiftCardData()
        giftCardData.firstName = "J#e"
        giftCardData.lastName = "B#r"
        giftCardData.address1 = "2707 love road bv"
        giftCardData.address2 = ""
        giftCardData.state = "Texas"
        giftCardData.city = "BBBB#BBB"
        giftCardData.zipCode4 = "23#3"
        giftCardData.zipCode5 = "23#35"
        giftCardData.phoneAreaCode = "111"
        giftCardData.phonePrefix = "222"
        giftCardData.phoneLineNumber = "3333"
        giftCardData.email = "a@b.com"
        giftCardData.emailConfirmation = "b@a.com"
        giftCardData.creditCard = new CreditCard(CreditCard.TYPE.VISA, "4111 1111 1111 1111", 3, CreditCardData.generateValidExpirationYear(), "#03")
        return giftCardData
    }
}
