package state

import org.apache.commons.lang.RandomStringUtils

class CompanyData {

    String companyName
    String address
    String city
    int stateIndex
    String zipCode
    String phoneNumber
    String dunnsNumber
    String firstName
    String lastName
    String email
    int pwdQuestionIndex
    String pwdAnswer
    String password
    int numberEmployeeIndex
    int anualSalesIndex
    int numberTravelersIndex
    int industryTypeIndex

    def generateRandomCompany() {
        companyName = RandomStringUtils.randomAlphabetic(8)
        address = "ADRESS123"
        city = "CITY"
        stateIndex = 1
        zipCode = "900000000"
        phoneNumber = RandomStringUtils.randomNumeric(10)
        dunnsNumber = "9"+RandomStringUtils.randomNumeric(8)

        firstName = "Teddy"
        lastName = "Bear"
        email = "mail@mail.com";

        pwdQuestionIndex = 1
        pwdAnswer = "pepe"
        password = "test123"

        numberEmployeeIndex = 1
        anualSalesIndex = 1
        numberTravelersIndex = 1
        industryTypeIndex = 1
    }
}
