package com.swacorp.dotcom.webscenarios.air

import static java.util.Calendar.YEAR
import fixtures.air.enums.CreditCardRejectedReason

public class CreditCardData {

    static CreditCard unacceptableCreditCard = new CreditCard(CreditCard.TYPE.MASTER_CARD, "5582 5086 1047 1549", 1, generateValidExpirationYear(), "123")

    static CreditCard getDinerClubCard = new CreditCard(CreditCard.TYPE.DINERS_CLUB, "3056 930902 5904", 12, generateValidExpirationYear(), "1212")

    static CreditCard getUATPCard = new CreditCard(CreditCard.TYPE.UATP, "1001 20517 010158", 12, generateValidExpirationYear(), "1212")

    static List<CreditCard> standardCreditCards = [
            new CreditCard(CreditCard.TYPE.VISA, "4111 1111 1111 1111", 3, generateValidExpirationYear(), "123"),
            new CreditCard(CreditCard.TYPE.VISA, "4211 5100 6885 0002", 1, generateValidExpirationYear(), "003")
    ]

    static final Map<CreditCardRejectedReason, CreditCard> rejectedCreditCard = [
            (CreditCardRejectedReason.INVALID_INPUT_DATA)                               : new CreditCard(CreditCard.TYPE.VISA, "4539 2728 1463 0684", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED_INVALID_CID)                             : new CreditCard(CreditCard.TYPE.VISA, "4556 9379 4842 0909", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED_INVALID_CARD_NUMBER)                     : new CreditCard(CreditCard.TYPE.VISA, "4532 4644 3407 7738", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED_INVALID_CARD_TYPE)                       : new CreditCard(CreditCard.TYPE.VISA, "4716 1655 9860 2231", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED_INVALID_AMOUNT)                          : new CreditCard(CreditCard.TYPE.VISA, "4556 1912 2133 0753", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED_INVALID_EXPIRATION_DATE)                 : new CreditCard(CreditCard.TYPE.VISA, "4485 2130 9539 0006", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.DECLINED)                                         : new CreditCard(CreditCard.TYPE.VISA, "4556 7122 4688 5628", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.ADDRESS_VERIFICATION_FAILED)                      : new CreditCard(CreditCard.TYPE.VISA, "4024 0071 1911 3396", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.ADDRESS_VERIFICATION_FAILED_INVALID_ADDR_LINE)    : new CreditCard(CreditCard.TYPE.VISA, "4556 3127 8399 2521", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.ADDRESS_VERIFICATION_FAILED_INVALID_POSTAL_CODE)  : new CreditCard(CreditCard.TYPE.VISA, "4716 6273 7559 0996", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.RETRY)                                            : new CreditCard(CreditCard.TYPE.VISA, "4024 0071 4407 5453", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.LINK_DOWN)                                        : new CreditCard(CreditCard.TYPE.VISA, "4485 1354 0942 9909", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.SAAS_DOWN)                                        : new CreditCard(CreditCard.TYPE.VISA, "4485 9245 4293 3046", 7, generateValidExpirationYear(), "123"),
            (CreditCardRejectedReason.OTHER_ERROR)                                      : new CreditCard(CreditCard.TYPE.VISA, "4916 2579 6159 5528", 7, generateValidExpirationYear(), "123")
    ]

    static List<CreditCard> randomCardList = [
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5105 1051 0510 5100", 3, generateValidExpirationYear(), "123"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5555 5555 5555 4444", 3, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5547 8994 3355 5475", 8, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5271 1902 4264 3112", 2, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5135 2992 5664 0694", 9, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5547 8994 3355 5475", 11, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5173 5828 1523 9055", 8, generateValidExpirationYear(), "303"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5584 3989 2997 3080", 3, generateValidExpirationYear(), "808"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5161 5203 5310 4301", 9, generateValidExpirationYear(), "159"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5440 9314 4309 4530", 3, generateValidExpirationYear(), "159"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5263 0673 9394 7775", 2, generateValidExpirationYear(), "445"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5379 1006 5464 9284", 8, generateValidExpirationYear(), "759"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5555 5555 5555 4444", 1, generateValidExpirationYear(), "998"),
            new CreditCard(CreditCard.TYPE.MASTER_CARD, "5446 8823 7512 9691", 11, generateValidExpirationYear(), "816"),
            new CreditCard(CreditCard.TYPE.VISA, "4024 0071 3553 2710", 5, generateValidExpirationYear(), "018"),
            new CreditCard(CreditCard.TYPE.VISA, "4532 2541 3758 3730", 4, generateValidExpirationYear(), "018"),
            new CreditCard(CreditCard.TYPE.VISA, "4716 2046 3895 0696", 4, generateValidExpirationYear(), "818"),
            new CreditCard(CreditCard.TYPE.VISA, "4012 8888 8888 1881", 3, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.VISA, "4929 3910 4626 7988", 7, generateValidExpirationYear(), "878"),
            new CreditCard(CreditCard.TYPE.VISA, "4556 4746 7090 6442", 3, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.VISA, "4916 6542 1162 7608", 3, generateValidExpirationYear(), "315"),
            new CreditCard(CreditCard.TYPE.VISA, "4485 9833 5624 2217", 4, generateValidExpirationYear(), "357"),
            new CreditCard(CreditCard.TYPE.VISA, "4005 5500 0000 0019", 9, generateValidExpirationYear(), "335"),
            new CreditCard(CreditCard.TYPE.VISA, "4539 0820 3939 6288", 8, generateValidExpirationYear(), "135"),
            new CreditCard(CreditCard.TYPE.VISA, "4539 3902 4313 2435", 4, generateValidExpirationYear(), "335"),
            new CreditCard(CreditCard.TYPE.VISA, "4716 0814 6730 5766", 5, generateValidExpirationYear(), "153"),
            new CreditCard(CreditCard.TYPE.AMERICAN_EXPRESS, "3732 353878 81007", 12, generateValidExpirationYear(), "1212"),
            new CreditCard(CreditCard.TYPE.AMERICAN_EXPRESS, "3788 888888 51005", 12, generateValidExpirationYear(), "1212"),
            new CreditCard(CreditCard.TYPE.DISCOVER_NETWORK, "6011 0089 1450 6008", 12, generateValidExpirationYear(), "121")
    ]

    static List<CreditCard> randomCardListOnlyVisa = [
            new CreditCard(CreditCard.TYPE.VISA, "4024 0071 3553 2710", 5, generateValidExpirationYear(), "018"),
            new CreditCard(CreditCard.TYPE.VISA, "4532 2541 3758 3730", 4, generateValidExpirationYear(), "018"),
            new CreditCard(CreditCard.TYPE.VISA, "4716 2046 3895 0696", 4, generateValidExpirationYear(), "818"),
            new CreditCard(CreditCard.TYPE.VISA, "4012 8888 8888 1881", 3, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.VISA, "4929 3910 4626 7988", 7, generateValidExpirationYear(), "878"),
            new CreditCard(CreditCard.TYPE.VISA, "4556 4746 7090 6442", 3, generateValidExpirationYear(), "003"),
            new CreditCard(CreditCard.TYPE.VISA, "4916 6542 1162 7608", 3, generateValidExpirationYear(), "315"),
            new CreditCard(CreditCard.TYPE.VISA, "4485 9833 5624 2217", 4, generateValidExpirationYear(), "357"),
            new CreditCard(CreditCard.TYPE.VISA, "4005 5500 0000 0019", 9, generateValidExpirationYear(), "335"),
            new CreditCard(CreditCard.TYPE.VISA, "4539 0820 3939 6288", 8, generateValidExpirationYear(), "135"),
            new CreditCard(CreditCard.TYPE.VISA, "4539 3902 4313 2435", 4, generateValidExpirationYear(), "335"),
            new CreditCard(CreditCard.TYPE.VISA, "4716 0814 6730 5766", 5, generateValidExpirationYear(), "153")
    ]

    private static int generateValidExpirationYear() {
        Date currentDate = new Date()
        return currentDate[YEAR] + 4
    }

    static CreditCard selectCardByNumber(List<CreditCard> creditCards, String ccNumber) {
        for (CreditCard card : creditCards) {
            if(ccNumber == card.wholeNumber){
                return card
            }
        }
        return null
    }

    static CreditCard selectCard(List<CreditCard> creditCards) {
        int cardToSelect = new Random().nextInt(creditCards.size());
        return creditCards.get(cardToSelect);
    }

    static CreditCard getRejectedCreditCardByReason(String reason) {
        CreditCardRejectedReason cardRejectedReason = CreditCardRejectedReason.getCreditCardRejectedReason(reason)
        return  rejectedCreditCard.get(cardRejectedReason)
    }
}