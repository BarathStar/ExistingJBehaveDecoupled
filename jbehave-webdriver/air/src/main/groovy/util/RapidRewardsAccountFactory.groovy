/*
 * Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.
 */
package util

import domain.RapidRewardsAccount
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatterBuilder;

class RapidRewardsAccountFactory {

    static final Random randomGenerator = new Random()

    static RapidRewardsAccount createRapidRewardsAccount(String username = null, String password = null, String filename = null) {
        RapidRewardsAccount rr = DatasetManager.loadAccount(filename)

        if(username == null && password == null){
            rr.username = generateUsername()
            rr.password = generatePassword()
            rr.firstName = generateFirstName()
            rr.lastName = generateLastName()
            rr.email = generateEmail()
        }
        else{
            rr.username = username
            rr.password = password
        }

        rr.address = generateAddress()
        generatePhoneNumber(rr)
        rr.zipCode = generateZipcode()

        rr.birthDay = generateBirthDay()
        rr.birthMonth = generateBirthMonth()
        rr.birthYear = generateBirthYear()

        rr.city = generateCity()
        rr.state = generateState()
        rr.country = generateCountry()

        rr.gender = generateGender()

        rr.addressType = generateAddressType()
        rr.phoneType = generatePhoneType()

        rr.stateCode = generateStateCode()
        rr.countryCode = generateCountryCode()

        return rr
    }

    static RapidRewardsAccount createRapidRewardsAccountForCert() {

        RapidRewardsAccount core25Account = new RapidRewardsAccount();
        core25Account.username = createUsernameForCert()
        core25Account.password = generatePassword()
        core25Account.firstName = generateFirstName()
        core25Account.lastName = generateLastName()
        core25Account.email = generateEmail()
        core25Account.address = generateAddress()
        generatePhoneNumber(core25Account)
        core25Account.zipCode = generateZipcode()
        core25Account
        core25Account.birthDay = generateBirthDay()
        core25Account.birthMonth = generateBirthMonth()
        core25Account.birthYear = generateBirthYear()
        core25Account
        core25Account.city = generateCity()
        core25Account.state = generateState()
        core25Account.country = generateCountry()
        core25Account
        core25Account.gender = generateGender()
        core25Account
        core25Account.addressType = generateAddressType()
        core25Account.phoneType = generatePhoneType()
        core25Account
        core25Account.stateCode = generateStateCode()
        core25Account.countryCode = generateCountryCode()

        return core25Account



    }

    private static String createUsernameForCert() {
        def formatter = new DateTimeFormatterBuilder().appendPattern("YY_MM_dd_HH_mm").toFormatter()
        return ("core_" + new DateTime().toString(formatter))
    }

    static String generatePhoneType() {
        return "Home"
    }

    static String generateAddressType() {
        return "HOME"
    }

    static String generateCity() {
        return "City"
    }

    static String generateAddress() {
        return randomGenerator.nextInt(9999).toString() + " southwest drive"
    }
    static String generatePhoneNumber(RapidRewardsAccount rapidRewardsAccount) {
        rapidRewardsAccount.areaCode = "333"
        rapidRewardsAccount.prefixNumber = "333"
        rapidRewardsAccount.lineNumber = (randomGenerator.nextInt(999) + 1000).toString()
        rapidRewardsAccount.phoneNumber = rapidRewardsAccount.areaCode + " " + rapidRewardsAccount.prefixNumber + " " + rapidRewardsAccount.lineNumber
    }
    static String generateUsername() {
        return "User"+ StringHelper.getRandomString(8) + randomGenerator.nextInt(99999).toString()
    }
    static String generateFirstName() {
        return "mike" + StringHelper.getRandomString(5)
    }
    static String generateLastName() {
        return "warrior" + StringHelper.getRandomString(5)
    }
    static String generatePassword() {
        return "test123"
    }
    static String generateEmail() {
        return "test" + StringHelper.getRandomString(5) + "@wnco.com"
    }
    static String generateZipcode() {
        return (randomGenerator.nextInt(9999) + 90000).toString()
    }
    static int generateBirthDay(){
        return (randomGenerator.nextInt(27) + 1)
    }
    static int generateBirthMonth(){
        return 10 //randomGenerator.nextInt(11)
    }
    static int generateBirthYear(){
        return (randomGenerator.nextInt(10) + 80)
    }
    static String generateState(){
        return "Texas"
    }
    static String generateCountry(){
        return "UNITED STATES OF AMERICA"
    }
    static String generateGender(){
        return "Male"
    }

    static String generateStateCode(){
        return "TX"
    }

    static String generateCountryCode(){
        return "US"
    }
}
