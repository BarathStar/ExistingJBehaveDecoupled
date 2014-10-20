package com.swacorp.dotcom.webscenarios.air

import domain.Passenger
import fixtures.air.AccountSpecification
import org.joda.time.LocalDate
import util.RapidRewardsAccountFactory
import domain.RapidRewardsAccount

class RRUser {

    private final String accountName
    private final String password
    private  final String firstName
    private final String preferredFirstName
    private final String middleName = ""
    private final String lastName
    private final Date dateOfBirth
    private final String gender
    private final Integer points = 0
    private final Integer credits = 0
    private final Integer awards = 0
    private final String aPlusUser
    private final String accountType = ""
    private final String homeAirport = "LAX"
    private final String userName
    private String number
    private String email
    private String address
    private String city
    private String state
    private String zipCode
    private String phoneNumber
    private String country
    private String addressType
    private String phoneType
    private String redress
    private String addressTwo
    private String certificateNumber
    private String secondCertificateNumber
    private String stateCode
    private String countryCode
    private String companyId
    private LocalDate lastAccrualDate

    RRUser(String accountName, String password, String number, Integer points, Integer credits, Integer awards) {
        this.accountName = accountName
        this.number = number
        this.password = password
        this.points = points
        this.credits = credits
        this.awards = awards
    }

    RRUser(String accountName, String password, String number) {
        this.accountName = accountName
        this.password = password
        this.number = number
    }

    RRUser(String accountName, String password, String number, String firstName, String middleName, String lastName, Date dateOfBirth, String gender, String aPlusNumber) {
        this.accountName = accountName
        this.password = password
        this.number = number
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.dateOfBirth = dateOfBirth
        this.gender = gender
        this.aPlusUser = aPlusNumber
        this.points = 10000
        this.credits = 3
        this.awards = 5
        this.accountType = "Member"
    }

    RRUser(String accountName, String password, String number, String firstName, String preferredFirstName, String middleName, String lastName, Date dateOfBirth, String gender, String aPlusNumber) {
        this(accountName, password, number, firstName, middleName, lastName, dateOfBirth, gender, aPlusNumber)
        this.preferredFirstName = preferredFirstName
    }

    RRUser(String accountName, String password, String number, String firstName, String middleName, String lastName, Date dateOfBirth, String gender, String aPlusNumber, LocalDate lastAccrualDate) {
        this(accountName, password, number, firstName, middleName, lastName, dateOfBirth, gender, aPlusNumber)
        this.lastAccrualDate = lastAccrualDate
    }

    RRUser(String firstName, String lastName, String password, String memberNumber,
    Integer points, Integer credits, Integer awards, String aPlusUser) {
        this(firstName, password, memberNumber, points, credits, awards)
        this.firstName = firstName
        this.lastName = lastName
        this.aPlusUser = aPlusUser
    }

    RRUser(String accountType, String firstName, String lastName, String password, String memberNumber,
    Integer points, Integer credits, Integer awards, String aPlusUser, Date dateOfBirth, String gender) {
        this(firstName, lastName, password, memberNumber, points, credits, awards, aPlusUser)
        this.dateOfBirth = dateOfBirth
        this.gender = gender
        this.accountType = accountType
    }

    RRUser(RRUser rrUser, Passenger passenger ) {
        this.accountName = rrUser.firstName
        this.password = rrUser.password
        this.number = rrUser.number
        this.firstName = passenger.firstName
        this.middleName = passenger.middleName
        this.lastName = passenger.lastName
        this.dateOfBirth = passenger.dateOfBirth
        this.gender = passenger.gender
        this.points = rrUser.points
        this.credits = rrUser.credits
        this.awards = rrUser.awards
        this.aPlusUser = rrUser.aPlusUser
        this.accountType = rrUser.accountType
    }

    RRUser(AccountSpecification accountSpecification, String customerNumber, String password) {
        this.accountName = accountSpecification.firstName
        this.firstName = accountSpecification.firstName
        this.middleName = accountSpecification.middleName
        this.lastName = accountSpecification.lastName
        this.dateOfBirth = accountSpecification.dateOfBirth.toDateMidnight().toDate()
        this.gender = accountSpecification.gender
        this.number = customerNumber
        this.password = password
        this.userName = RapidRewardsAccountFactory.generateUsername()
        this.lastAccrualDate = accountSpecification.lastAccrualDate
    }

    RRUser(RapidRewardsAccount account) {
        this.firstName = account.getFirstName()
        this.lastName = account.getLastName()
        this.dateOfBirth = new Date()
        this.dateOfBirth.setDate(account.getBirthDay())
        this.dateOfBirth.setMonth(account.getBirthMonth())
        this.dateOfBirth.setYear(account.getBirthYear())
        this.email = account.getEmail()
        this.address = account.getAddress()
        this.city = account.getCity()
        this.state = account.getState()
        this.stateCode = account.getStateCode()
        this.countryCode = account.getCountryCode()
        this.zipCode = account.getZipCode()
        this.phoneNumber = account.getPhoneNumber()
        this.gender = account.getGender()
        this.accountName = account.getUsername()
        this.password = account.getPassword()
        this.country = account.getCountry()
        this.addressType = account.getAddressType()
        this.phoneType = account.getPhoneType()
    }

    RRUser(String companyId){
        this.companyId = companyId
    }

    def setAccountNumber(String number) {
        this.number=number
    }

    String getRRAccountName() {
        return accountName
    }

    String getRRNumber(){
        return number
    }

    String getRRPassword(){
        return password
    }

    String getRRFirstName() {
        return firstName
    }

    String getRRMiddleName() {
        return middleName
    }

    String getRRLastName() {
        return lastName
    }

    Date getRRDateOfBirth() {
        return dateOfBirth
    }

    String getRRGender() {
        return gender
    }

    Integer getPoints() {
        return points
    }

    Integer getCredits() {
        return credits
    }

    Integer getAwards() {
        return awards
    }

    String getRRAccountType() {
        return accountType
    }

    String getUserName() {
        return userName
    }

    String getHomeAirport() {
        return homeAirport
    }

    String getEmail() {
        return email
    }

    String getPhoneNumber() {
        return phoneNumber
    }

    String getZipCode() {
        return zipCode
    }

    String getState() {
        return state
    }

    String getAccountName() {
        return accountName
    }

    String getNumber() {
        return number
    }

    String getPassword() {
        return password
    }

    String getFirstName() {
        return firstName
    }

    String getMiddleName() {
        return middleName
    }

    String getLastName() {
        return lastName
    }

    Date getDateOfBirth() {
        return dateOfBirth
    }

    String getGender() {
        return gender
    }

    String getAPlusUser() {
        return aPlusUser
    }

    String getAccountType() {
        return accountType
    }

    String getAddress() {
        return address
    }

    String getCity() {
        return city
    }

    String getCountry() {
        return country
    }

    String getAddressType() {
        return addressType
    }

    String getPhoneType() {
        return phoneType
    }

    String getRedress() {
        return redress
    }

    String getAddressTwo() {
        return addressTwo
    }

    def setRedress(String theRedress) {
        this.redress = theRedress
    }

    def setEmail(String theEmail) {
        this.email = theEmail
    }

    def setAddress(String theAddress) {
        this.address = theAddress
    }

    def setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo
    }

    def setCity(String theCity) {
        this.city = theCity
    }

    def setState(String theState) {
        this.state = theState
    }

    def setZipCode(String theZipCode) {
        this.zipCode = theZipCode
    }

    def setAddressType(String theAddressType) {
        this.addressType = theAddressType
    }

    def setCountry(String theCountry) {
        this.country = theCountry
    }

    def setNumber(String number) {
        this.number = number
    }

    def setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber
    }

    def getCertificateNumber() {
        return this.certificateNumber
    }

    def getSecondCertificateNumber() {
        return this.secondCertificateNumber
    }

    def getStateCode() {
        return this.stateCode
    }

    def setSecondCertificateNumber(String secondCertificateNumber) {
        this.secondCertificateNumber = secondCertificateNumber
    }

    def setStateCode(String stateCode) {
        this.stateCode = stateCode
    }

    String getCountryCode() {
        return countryCode
    }

    void setCountryCode(String countryCode) {
        this.countryCode = countryCode
    }


    void setCompanyId(String companyId){
        this.companyId = companyId
    }
    String getCompanyId(){
        return companyId
    }

    LocalDate getLastAccrualDate() {
        return lastAccrualDate
    }

    void setLastAccrualDate(LocalDate lastAccrualDate) {
        this.lastAccrualDate = lastAccrualDate
    }

    static getValidRRAccountNumberRandom() {
        Random rnd = new Random(System.currentTimeMillis())
        String accountNumber = rnd.nextInt(999999999).toString()
        Long checksum = (Long.parseLong(accountNumber.substring(0, accountNumber.length() - 1)) % 7)
        return accountNumber.substring(0, accountNumber.length() - 1) + checksum.toString()
    }
}
