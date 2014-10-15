/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package domain

class GiftCard {
    String number
    String securityCode
    static final int LENGTH_16_DIGITS = 16
    static final int LENGTH_4_DIGITS = 4
    static final int LENGTH_3_DIGITS = 3

    public GiftCard (String number, String securityCode){
        this.number = number
        this.securityCode = securityCode
    }

    public static GiftCard getInexistentGiftCard(){
        return new GiftCard ("1234567812345678","1234")
    }

    public static GiftCard getInvalidGiftCard(){
        return new GiftCard ("123","123")
    }

    public static GiftCard generateValidGiftCard(){
        return new GiftCard(generateRandomNumber(LENGTH_16_DIGITS).toString(),generateRandomNumber(LENGTH_4_DIGITS).toString())
    }

    public static GiftCard getGiftCardInexistentOrInvalid(boolean fillCardNumber,
        boolean fillSecurityCode, boolean fillWithValidLenght){

        String cardNumber = ""
        String securityCode = ""

        if(fillCardNumber){
            if(fillWithValidLenght){
                cardNumber = generateRandomNumber(LENGTH_16_DIGITS).toString()
            }else{
                cardNumber = generateRandomNumber(LENGTH_3_DIGITS).toString()
            }
        }

        if (fillSecurityCode){
            if(fillWithValidLenght){
                securityCode = generateRandomNumber(LENGTH_4_DIGITS).toString()
            }else{
                securityCode = generateRandomNumber(LENGTH_3_DIGITS).toString()
            }
        }

        return new GiftCard (cardNumber, securityCode)
    }

    private static Long generateRandomNumber(int length){
        Long number = 0
        int diff = length

        while (diff != 0){
            number = new Long((long)(Math.random() * Math.pow(10,length)))
            diff = length - number.toString().length()
        }
        return number
    }
}