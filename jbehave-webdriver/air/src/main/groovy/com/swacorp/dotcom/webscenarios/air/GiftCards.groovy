package com.swacorp.dotcom.webscenarios.air

class GiftCards {

    private final String cardNumber;
    private final String securityCode;

    public GiftCards(String cardNumber, String securityCode) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }
}
