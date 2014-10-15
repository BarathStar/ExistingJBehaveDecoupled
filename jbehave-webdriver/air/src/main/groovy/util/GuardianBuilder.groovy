package util

import domain.Guardian

class GuardianBuilder {

    public firstName = "Peppermint"
    public lastName = "Patty"
    public relationship = "Friend"
    public contactAreaCode = "554"
    public contactPrefix = "444"
    public contactLineNumber = "5554"
    public addressLine1 = "Schroeder 251"
    public state = "TX"
    public city = "Dallas"
    public country = "US"
    public zipCode1 = "54215"
    public contactMethod = "Call"

    GuardianBuilder(){
    }

    GuardianBuilder withFirstName(String firstName) {
        this.firstName = firstName
        return this
    }

    GuardianBuilder withLastName(String lastName) {
        this.lastName = lastName
        return this
    }

    GuardianBuilder withRelationship(String relationship) {
        this.relationship = relationship
        return this
    }

    GuardianBuilder withContactAreaCode(String contactAreaCode) {
        this.contactAreaCode = contactAreaCode
        return this
    }

    GuardianBuilder withContactPrefix(String contactPrefix) {
        this.contactPrefix = contactPrefix
        return this
    }

    GuardianBuilder withContactLineNumber(String contactLineNumber) {
        this.contactLineNumber = contactLineNumber
        return this
    }

    GuardianBuilder withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1
        return this
    }

    GuardianBuilder withState(String state) {
        this.state = state
        return this
    }

    GuardianBuilder withCity(String city) {
        this.city = city
        return this
    }

    GuardianBuilder withCountry(String country) {
        this.country = country
        return this
    }

    GuardianBuilder withZipCode1(String zipCode1) {
        this.zipCode1 = zipCode1
        return this
    }

    GuardianBuilder withContactMethod(String contactMethod) {
        this.contactMethod = contactMethod
        return this
    }

    Guardian build() {
        return new Guardian(
        firstName,
        lastName,
        relationship,
        contactAreaCode,
        contactPrefix,
        contactLineNumber,
        addressLine1,
        state,
        city,
        country,
        zipCode1,
        contactMethod
        )
    }
}
