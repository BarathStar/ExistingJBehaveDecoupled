/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.*/
package domain

/**
 * Represents a parent/guardian
 */
class Guardian {
    String firstName
    String lastName
    String relationship
    String contactAreaCode
    String contactPrefix
    String contactLineNumber
    String addressLine1
    String state
    String city
    String country
    String zipCode1
    String contactMethod

    public Guardian(String firstName, String lastName, String relationship, String contactAreaCode,
    String contactPrefix, String contactLineNumber, String addressLine1, String state, String city,
    String country, String zipCode1, String contactMethod) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.contactAreaCode = contactAreaCode;
        this.contactPrefix = contactPrefix;
        this.contactLineNumber = contactLineNumber;
        this.addressLine1 = addressLine1;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zipCode1 = zipCode1;
        this.contactMethod = contactMethod;
    }
    public Guardian () {
        super()
    }
}
