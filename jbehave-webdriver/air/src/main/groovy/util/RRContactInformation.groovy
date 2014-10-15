package util

import groovy.transform.ToString
import org.openqa.selenium.WebElement
import static com.google.common.collect.Lists.newArrayList
import static com.google.common.collect.Maps.newHashMap

@ToString(includeFields = true)
class RRContactInformation {

    public String firstName
    public String preferredFirstName
    public String lastName
    public String middleName
    public String gender
    public String dateOfBirth
    public String email
    public String accountNumber
    public String phoneNumber
    public String ssn
    public String streetAddress
    public String city
    public String state
    public String zipCode
    public String country

    public String creditCardName
    public String creditCardType
    public String creditCardNumber
    public String creditCardExpirationDate
    public String creditCardFirstName
    public String creditCardLastName
    public String creditCardBillingAddress
    public String creditCardBillingCity
    public String creditCardBillingState
    public String creditCardBillingZipCode
    public String creditCardBillingCountry

    private final String GENDER = "Gender"
    private final String SSN = "Last 4 digits of SSN"
    private final String EMAIL = "Email"
    private final String STREET_ADDRESS= "Street Address"
    private final String CITY = "City"
    private final String STATE = "St./Prov./Reg."
    private final String ZIP_CODE = "ZIP/Postal Code"
    private final String COUNTRY = "Country"
    private final String PHONE_NUMBER = "Phone Number"
    private static final int FIRST_NAME = 1
    private static final int MIDDLE_NAME = 3
    private static final int LAST_NAME = 5
    private static final int PREFERRED_FIRST_NAME = 9
    private static final int DATE_OF_BIRTH = 11
    List<String> elementKeys = newArrayList(GENDER, SSN, EMAIL, STREET_ADDRESS, CITY, STATE , ZIP_CODE, COUNTRY, PHONE_NUMBER)

    public populateInformation( List<WebElement> elements, String accountNumber) {
        populateFullNameAndDateOfBirth(elements)
        Map<String,String> elementMap =  generateElementMap(elements,elementKeys)
        this.gender = elementMap.get(GENDER)
        this.ssn = elementMap.get(SSN)
        if(ssn.equalsIgnoreCase("Not on file")){
            ssn = ""
        }
        this.email =elementMap.get(EMAIL)
        this.streetAddress =elementMap.get(STREET_ADDRESS)
        this.city= elementMap.get(CITY)
        this.state= elementMap.get(STATE)
        this.zipCode= elementMap.get(ZIP_CODE)
        this.country= elementMap.get(COUNTRY)
        this.phoneNumber= elementMap.get(PHONE_NUMBER)
        this.accountNumber = accountNumber.split(" ")[2]
    }

    Map<String, String> generateElementMap(List<WebElement> elements,List<String> keys){
        Map<String,String> elementMap = newHashMap()
        String fieldText = ""
        int objIndex = 0
        for(WebElement obj:elements){
            if(keys.contains(obj.text)){
                if(obj.text.equals(COUNTRY) && elementMap.containsKey(COUNTRY)){
                    continue
                }
                if (obj.text.equals(STREET_ADDRESS)){
                    elementMap.put(EMAIL,elements.get(objIndex-4).text)

                    fieldText = elements.get(objIndex + 1).text
                    if (elements.get(objIndex + 2).equals("")){
                        fieldText += " " + elements.get(objIndex+3).text
                    }
                    elementMap.put(obj.text,fieldText)
                }
                else {
                    elementMap.put(obj.text,elements.get(objIndex+1).text)
                }
            }
            objIndex++
        }
        return elementMap
    }

    public populateCustomerInformation (List<WebElement> elements) {
        populateFullNameAndDateOfBirth(elements)
        this.ssn = elements.get(13).text
        if(ssn.equalsIgnoreCase("Not on file")){
            ssn = ""
        }
        this.gender = elements.get(15).text
        this.email = elements.get(20).text
        this.streetAddress = elements.get(23).text
        this.city= elements.get(25).text
        this.state= elements.get(27).text
        this.zipCode= elements.get(29).text
        this.country= elements.get(31).text
        this.phoneNumber= elements.get(37).text
    }

    private populateFullNameAndDateOfBirth(List<WebElement> elements) {
        this.firstName = elements.get(FIRST_NAME).text
        this.middleName = elements.get(MIDDLE_NAME).text
        this.lastName = elements.get(LAST_NAME).text
        this.preferredFirstName = elements.get(PREFERRED_FIRST_NAME).text
        this.dateOfBirth = elements.get(DATE_OF_BIRTH).text
    }

    public String getFullName() {
        return firstName + " " + lastName
    }

    public populateCCInformation(List<WebElement> webElements) {
        this.creditCardName = webElements[1].text
        this.creditCardType = webElements[3].text
        this.creditCardNumber = webElements[5].text
        this.creditCardExpirationDate = webElements[7].text
        this.creditCardFirstName = webElements[9].text
        this.creditCardLastName = webElements[11].text
    }
}
