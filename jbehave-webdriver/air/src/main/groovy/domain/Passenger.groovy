package domain

import static java.util.Calendar.YEAR
import static java.util.Calendar.DAY_OF_MONTH
import com.swacorp.dotcom.webscenarios.air.RRUser

class Passenger {
    String firstName
    String middleName = ""
    String lastName
    String suffix = ""
    Date dateOfBirth
    String gender
    String rapidRewardsNumber = ""
    String rapidRewardsType = ""
    boolean selectedForCheckin = false
    boolean hasInfant = false;
    boolean isPassengerOfSize = false;

    public Passenger() {
        super();
    }

    Passenger(String firstName , String lastName, String gender, Date dateOfBirth) {
        this(firstName, lastName, gender)
        this.dateOfBirth = dateOfBirth
    }

    Passenger(String firstName, String middleName , String lastName, String gender, Date dateOfBirth) {
        this(firstName, middleName, lastName, gender)
        this.dateOfBirth = dateOfBirth
    }

    Passenger(String rapidRewardsNumber, String firstName, String middleName , String lastName, String gender, Date dateOfBirth) {
        this(firstName, middleName, lastName, gender)
        this.rapidRewardsNumber = rapidRewardsNumber
        this.dateOfBirth = dateOfBirth
    }

    Passenger(String firstName, String middleName , String lastName, String gender) {
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.gender = gender
    }

    Passenger(String firstName, String lastName, String gender) {
        this.lastName = lastName
        this.firstName = firstName
        this.gender = gender
    }

    Passenger(String firstName, String lastName, String gender, String rapidRewardsNumber, String rapidRewardsType) {
        this(firstName, lastName, gender)
        this.rapidRewardsNumber = rapidRewardsNumber
        this.rapidRewardsType = rapidRewardsType
    }

    Passenger(RRUser rrUser) {
        this.lastName = rrUser.lastName;
        this.firstName = rrUser.firstName;
        this.middleName = rrUser.middleName;
        this.dateOfBirth = rrUser.dateOfBirth;
        this.gender = rrUser.gender;
        this.rapidRewardsNumber = rrUser.number;
        this.rapidRewardsType = rrUser.accountType;
        this.selectedForCheckin = true;
    }

    String getFirstName() {
        return firstName;
    }

    String getMiddleName() {
        return middleName;
    }

    String getLastName() {
        return lastName;
    }

//    NOTE: Dynastubs doesn't differentiate between lastName and secureLastName but getSecureLastName() is supported to
//    capture the requirements for use of secureLastName when appropriate.
    String getSecureLastName() {
        return lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    String getRapidRewardsType() {
       return rapidRewardsType
    }

    String setRapidRewardsType(String rapidRewardsType) {
       this.rapidRewardsType = rapidRewardsType
    }

    void beAdult() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 34
        dateOfBirth = birthDate
    }

    void beSenior() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 66
        dateOfBirth = birthDate
    }

    void beYouth() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 14
        dateOfBirth = birthDate
    }

    void beYoungChild() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 3
        dateOfBirth = birthDate
    }

    void beChild() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 9
        dateOfBirth = birthDate
    }

    boolean isSenior() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 65
        birthDate[DAY_OF_MONTH] = birthDate[DAY_OF_MONTH] - 1
        return dateOfBirth.before(birthDate)
    }

    boolean isAdult() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 18
        birthDate[DAY_OF_MONTH] = birthDate[DAY_OF_MONTH] - 1
        return !isSenior() && dateOfBirth.before(birthDate)
    }

    boolean isYouth() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 12
        birthDate[DAY_OF_MONTH] = birthDate[DAY_OF_MONTH] - 1
        return !isAdult() && !dateOfBirth.after(birthDate)
    }

    boolean isChild() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 12
        return !isYoungChild() && dateOfBirth.before(birthDate)
    }

    boolean isYoungChild() {
        Date maxBirthDate = new Date()
        maxBirthDate[YEAR] = maxBirthDate[YEAR] - 2
        Date minBirthDate = new Date()
        minBirthDate [YEAR] = minBirthDate[YEAR] - 5
        return dateOfBirth.after(minBirthDate) && dateOfBirth.before(maxBirthDate)
    }

    boolean mustBeAccompanied() {
        Date birthDate = new Date()
        birthDate[YEAR] = birthDate[YEAR] - 12
        return dateOfBirth.after(birthDate)
    }

    boolean isRapidRewadsUser() {
        return !rapidRewardsNumber.isAllWhitespace() && !rapidRewardsType.isAllWhitespace()
    }

    String toString() {
        return firstName + " " + (middleName.isEmpty() ?  lastName
        : middleName + " " + lastName)
    }

    boolean equals(Passenger passenger) {
        this.toString() == passenger.toString()
    }

    int compareTo(Passenger passenger) {
        this.toString()compareTo(passenger.toString())
    }

    void setSelectedForCheckin(boolean selectedForCheckin) {
        this.selectedForCheckin = selectedForCheckin
    }

    boolean isSelectedForCheckin() {
        return this.selectedForCheckin
    }

    void setHasInfant( boolean hasInfant ) {
        this.hasInfant = hasInfant
    }

    boolean isHasInfant() {
        return hasInfant;
    }

    boolean isPassengerOfSize() {
        return isPassengerOfSize
    }

    void setPassengerOfSize(boolean isPOS) {
        this.isPassengerOfSize = isPOS
    }
}
