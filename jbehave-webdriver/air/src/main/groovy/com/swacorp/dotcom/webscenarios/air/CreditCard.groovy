package com.swacorp.dotcom.webscenarios.air

import java.util.regex.Pattern

public class CreditCard {

    public static enum TYPE {
        VISA, DISCOVER_NETWORK, MASTER_CARD, AMERICAN_EXPRESS, UATP, DINERS_CLUB, REJECTED


    };

    final static Pattern creditCardNumberPattern = ~/\d{4} \d{4,6} \d{4,6}( \d{4})?/;

    final private String creditCardNumber;
    final private String[] creditCardNumberParts;
    final private int expirationMonth;
    final private int expirationYear
    final String securityCode;
    final TYPE type;


    public CreditCard(TYPE type,
                      String creditCardNumber,
                      int expirationMonth,
                      int expirationYear,
                      String securityCode) {
        if (!creditCardNumberPattern.matcher(creditCardNumber).matches()) {
            throw new IllegalArgumentException("$creditCardNumber is not a valid credit card number");
        }

        this.creditCardNumber = creditCardNumber.replaceAll(/ /, "");
        this.creditCardNumberParts = creditCardNumber.split(/ /);
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.type = type;
        this.securityCode = securityCode;
    }

    public String getWholeNumber() {
        return creditCardNumber;
    }

    public String getFirstFour() {
        return creditCardNumberParts[0];
    }

    public String getSecondFour() {
        return creditCardNumberParts[1];
    }

    public String getThirdFour() {
        return creditCardNumberParts[2];
    }

    public String getFourthFour() {
        return (creditCardNumberParts.length < 4) ? "" : creditCardNumberParts[3];
    }

    public String getExpirationMonth() {
        return Integer.toString(expirationMonth);
    }

    public String getExpirationYear() {
        return Integer.toString(expirationYear);
    }

    public String getFirstName() {
        return "Sam";
    }

    public String getLastName() {
        return "frog";
    }

    static TYPE findTYPE(String type){
        for(TYPE item: TYPE.values()){
            if (type.equals(item.name())){
                return item
            }
        }
    }
}
