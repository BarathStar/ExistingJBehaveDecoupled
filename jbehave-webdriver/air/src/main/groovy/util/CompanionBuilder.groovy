package util

import domain.Companion

class CompanionBuilder {

    String firstName = "Luke"
    String lastName = "Skywalker"
    String streetAddressLineOne = "Schroeder 251"
    String city = "Dallas"
    String state = "TX"
    String zipCode = "54215"
    String country = "US"
    String email = "test123@test123.com"
    String gender = "Male"
    String monthOfBirth = "5"
    String dayOfBirth = "21"
    String yearOfBirth = "1985"
    String phoneNumber = "5544445554"

    CompanionBuilder(){
    }

    CompanionBuilder withFirstName(String firstName) {
        this.firstName = firstName
        return this
    }

    CompanionBuilder withLastName(String lastName) {
        this.lastName = lastName
        return this
    }

    CompanionBuilder withStreetAddressLineOne(String streetAddressLineOne) {
        this.streetAddressLineOne = streetAddressLineOne
        return this
    }

    CompanionBuilder withCity(String city) {
        this.city = city
        return this
    }

    CompanionBuilder withState(String state) {
        this.state = state
        return this
    }

    CompanionBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode
        return this
    }

    CompanionBuilder withCountry(String country) {
        this.country = country
        return this
    }

    CompanionBuilder withEmail(String email) {
        this.email = email
        return this
    }

    CompanionBuilder withGender(String gender) {
        this.gender = gender
        return this
    }

    CompanionBuilder withMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth
        return this
    }

    CompanionBuilder withDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth
        return this
    }

    CompanionBuilder withYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth
        return this
    }

    CompanionBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber
        return this
    }

    Companion build() {
        return new Companion(
                firstName,
                lastName,
                streetAddressLineOne,
                city,
                state,
                zipCode,
                country,
                email,
                gender,
                monthOfBirth,
                dayOfBirth,
                yearOfBirth,
                phoneNumber
        )
    }

}
