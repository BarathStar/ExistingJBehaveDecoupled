package domain

/**
 * Represents a companion passenger
 */
class Companion {

    String firstName
    String lastName
    String streetAddressLineOne
    String city
    String state
    String zipCode
    String country
    String email
    String gender
    String monthOfBirth
    String dayOfBirth
    String yearOfBirth
    String phoneNumber

    Companion(String firstName, String lastName, String streetAddressLineOne, String city, String state, String zipCode, String country, String email, String gender, String monthOfBirth, String dayOfBirth, String yearOfBirth, String phoneNumber) {
        this.firstName = firstName
        this.lastName = lastName
        this.streetAddressLineOne = streetAddressLineOne
        this.city = city
        this.state = state
        this.zipCode = zipCode
        this.country = country
        this.email = email
        this.gender = gender
        this.monthOfBirth = monthOfBirth
        this.dayOfBirth = dayOfBirth
        this.yearOfBirth = yearOfBirth
        this.phoneNumber = phoneNumber
    }

    public Companion() {
        super()
    }
}
