// Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.

package domain

import com.swacorp.dotcom.webscenarios.air.RRUser

/*
 * Represents a rapid rewards user account
 * loading default set of data from yml file
 * and generating random values for some of the attributes
 */

class RapidRewardsAccount{
    String username
    String password
    String firstName
    String middleName
    String lastName
    String email
    String accountNumberField
    String zipCode
    String securityAnswer
    String securityAnswer2
    int birthMonth
    int birthDay
    int birthYear
    String gender
    String address
    String city
    String state
    String phoneNumber
    String areaCode
    String prefixNumber
    String lineNumber
    String userType="Member"
    String newEmail
    String country
    String addressType
    String phoneType
    String stateCode
    String countryCode

    public RapidRewardsAccount(){
        super()
    }

    public RapidRewardsAccount(RRUser rrUser){
        super()
        this.firstName = rrUser.getRRFirstName()
        this.lastName = rrUser.getRRLastName()
        this.accountNumberField = rrUser.getRRNumber()
        this.password = rrUser.getRRPassword()
        this.gender = rrUser.getRRGender()
        this.securityAnswer = 'nyc'
        this.securityAnswer2 = 'lakers'
        this.zipCode = '75235'
        this.email = 'aaa.sss@wnco.com'
        this.newEmail = 'thisisanewemail@gmail.com'
        this.username = 'SchufyPoofy'
    }

    public static RapidRewardsAccount createRapidRewardsUser() {
        RapidRewardsAccount regularMember = new RapidRewardsAccount()
        regularMember.firstName = 'EvericFifteen'
        regularMember.lastName = 'Leapfrog'
        regularMember.accountNumberField = '500321334'
        regularMember.securityAnswer = 'nyc'
        regularMember.securityAnswer2 = 'lakers'
        regularMember.zipCode = '75235'
        regularMember.email = 'aaa.sss@wnco.com'
        regularMember.newEmail = 'thisisanewemail@gmail.com'
        regularMember.username = 'SchufyPoofy'
        return regularMember
    }

    public void setEmail(String email){
        this.email=email
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName
    }

    public void setLastName(String lastName) {
        this.lastName = lastName
    }
}
