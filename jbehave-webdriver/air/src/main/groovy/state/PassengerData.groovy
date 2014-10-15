package state

import domain.Passenger
import util.PassengerType
import static org.junit.Assert.assertFalse
import com.swacorp.dotcom.webscenarios.air.RRUser
import com.swacorp.dotcom.webscenarios.air.Data
import fixture.stubs.DynaStubsIntegration


class PassengerData {
    private static final MAX_NUMBER_OF_PASSENGER = 8
    private static final String PASSENGER_LAST_NAME = "Leapfrog"
    public static final String COMPOUND_PASSENGER_FIRST_NAME = "FOne FTwo"
    public static final String COMPOUND_PASSENGER_MIDDLE_NAME = "MOne MTwo"
    public static final String COMPOUND_PASSENGER_LAST_NAME = "LOne LTwo"
    public static final String VERY_LONG_CUSTOMER_FIRSTNAME = "HonathanGrapeHoboEatingTofurky"
    public static final String VERY_LONG_CUSTOMER_MIDDLENAME = "HonathanMiddlHoboEatingTofurky"
    public static final String VERY_LONG_CUSTOMER_LASTNAME = "LastNameIsLongWhenIKeepsTyping"
    public static final String AIRPORT_CHECKIN_FIRST_NAME = "Robert"
    public static final String AIRPORT_CHECKIN_MIDDLE_NAME = ""
    public static final String AIRPORT_CHECKIN_LAST_NAME = "Apricot"
    //start - sruthi
    public static final String INHIBITED_PASSENGER_FIRST_NAME = "Alex"
    public static final String INHIBITED_PASSENGER_LAST_NAME = "Apple"
    public static final Date INHIBITED_PASSENGER_DOB = new Date("01/15/1960")
    //end - sruthi
    public static final Date AIRPORT_CHECKIN_BIRTHDAY = new Date(61,1,15)
    private static final String GENDER_MALE = "Male"
    private static final String GENDER_FEMALE = "Female"
    private static final List<String> ADULT_NAMES = [
        "SamFifteen",
        "Rafael",
        "Cynthia",
        "Amanda",
        "Robert",
        "Camilia",
        "Jasmine",
        "George",
        "Raul",
        "Archie",
        "Sally"
    ]
    private static final List<String> SENIOR_NAMES = [
        "Gertrude",
        "Griffin",
        "Gerald",
        "Samantha",
        "Herb",
        "Colleen",
        "Jeffrey",
        "Jammal"
    ]
    private static final List<String> ADULT_GENDERS = [
        "Male",
        "Female",
        "Female",
        "Male",
        "Female",
        "Female",
        "Male",
        "Male",
        "Male",
        "Male",
        "Male",
        "Female"
    ]
    private static final List<String> SENIOR_GENDERS = [
        "Female",
        "Male",
        "Male",
        "Female",
        "Male",
        "Female",
        "Male",
        "Male"
    ]

    private static final List<String> RAPID_REWARDS_NUMBERS = [
        "500123002",
        "500123455",
        "500123422",
        "500123691",
        "500113375",
        "500123993",
        "586753090",
        "500000001"
    ]

    private static final List<String> RAPID_REWARDS_TYPES = [

    ]

    private  List<Passenger> passengers = new ArrayList<Passenger>()
    Data data
    Flow flow

    PassengerData(Flow flow) {
        this.flow = flow
    }

    List<Passenger> getAdults() {
        return passengers.grep({pax -> pax.isAdult()})
    }

    List<Passenger> getSeniors() {
        return passengers.grep({pax -> pax.isSenior()})

    }

    private String getAdultName() {
        return ADULT_NAMES.get(passengers.size())
    }

    public  List<Passenger> getPassengers () {
        return passengers.asImmutable()
    }

    void clearPassengers() {
        passengers.clear()
    }

    private String getSeniorName() {
        return SENIOR_NAMES.get(passengers.size())
    }

    private String getAdultGender() {
        return ADULT_GENDERS.get(passengers.size())
    }

    private String getSeniorGender() {
        return SENIOR_GENDERS.get(passengers.size())
    }

    private String getRapidRewardsNumber() {
        return RAPID_REWARDS_NUMBERS.get(passengers.size())
    }

    private String getRapidRewardsType() {
        return RAPID_REWARDS_TYPES.get(passengers.size())
    }
    //start - sruthi
    private Passenger getInhibitPassenger(String firstName, String lastName, String gender,Date dob) {
        if(!flow.hasCheckInPassengers) {
            assertMaxNumberOfPassengersHasNotBeenReached()
        }
        return new Passenger(firstName,lastName,gender,dob)
    }

    private Passenger getInhibitPassenger() {
        return  getInhibitPassenger(INHIBITED_PASSENGER_FIRST_NAME, INHIBITED_PASSENGER_LAST_NAME, GENDER_MALE,INHIBITED_PASSENGER_DOB)
    }

    public void addInhibitPassenger(){
        passengers.add(getInhibitPassenger())
    }

    public void addInhibitPassenger(String firstName, String lastName, String gender,Date dob){
        passengers.add(getInhibitPassenger(firstName, lastName, gender,dob)())
    }
    //end - sruthi
    public void addAdultPassenger(String firstName, String middleName, String lastName,String suffix, boolean isPOS = false){
           Passenger passenger = addPassenger()
           passenger.firstName = firstName
           passenger.middleName = middleName
           passenger.lastName = lastName
           passenger.suffix = suffix
           passenger.setPassengerOfSize(isPOS)
           passenger.beAdult()
    }

    // Children, youth, adolescents and adults all count as adults in bookings
    private Passenger addPassenger() {
        if(!flow.hasCheckInPassengers) {
            assertMaxNumberOfPassengersHasNotBeenReached()
        }
        Passenger passenger = new Passenger(getAdultName(), PASSENGER_LAST_NAME, getAdultGender())
        passengers.add(passenger)
        return passenger
    }

    private Passenger addPassengerWithRapidRewards() {
        if (!flow.hasCheckInPassengers) {
            assertMaxNumberOfPassengersHasNotBeenReached()
        }
        RRUser user = data.getUser("goodUser")
        Passenger passenger = new Passenger(user)

        if (DynaStubsIntegration.useDynaStubs()) {
            new DynaStubsIntegration().createRRMember(user)
        }
        flow.setUser(user)
        passengers.add(passenger)
        return passenger
    }

    public void addAdultPassenger(int adultPassengers = 1) {
        for (int i = 0; i < adultPassengers; i++) {
            if(flow.isRapidRewardsAfterPurchase || (flow.isLoggedIn && passengers.isEmpty())) {
                addLoggedInPassenger().beAdult()
            }
            else {
                addPassenger().beAdult()
            }
        }
    }

    public void addAdultPassengerWithRapidRewards(int adultPassengers = 1) {
        for (int i = 0; i < adultPassengers; i++) {
            if(flow.isLoggedIn && passengers.isEmpty()) {
                addLoggedInPassenger().beAdult()
            }
            else {
                addPassengerWithRapidRewards().beAdult()
            }
        }
    }

    public void addAdultLoggedInPassenger(String rrFirstName, String rrLastName, String rrGender, String rrNumber = "", String rrAccountType = "") {
        assertMaxNumberOfPassengersHasNotBeenReached()
        Passenger passenger = new Passenger(rrFirstName, rrLastName, rrGender, rrNumber, rrAccountType)
        passenger.beAdult()
        passengers.add(passenger)
    }

    public Passenger addLoggedInPassenger() {
        assertMaxNumberOfPassengersHasNotBeenReached()
        Passenger passenger = addRapidRewardsUser()
        return passenger
    }

	public Passenger addRapidRewardsUser() {
		RRUser rruser = flow.getUser()
		Passenger passenger = new Passenger(rruser.getRRNumber(),
				rruser.getRRFirstName(),
				rruser.getRRMiddleName(),
				rruser.getRRLastName(),
				rruser.getRRGender(),
				rruser.getRRDateOfBirth())

		passenger.setRapidRewardsType(rruser.getRRAccountType())
		passengers.add(passenger)
		return passenger
	}

    public Passenger addRRUserToPassengerList(RRUser rrUser) {
        assertMaxNumberOfPassengersHasNotBeenReached()
        Passenger passengerToAdd = new Passenger(rrUser.getRRNumber(),
                rrUser.getFirstName(),
                rrUser.getMiddleName(),
                rrUser.getLastName(),
                rrUser.getGender(),
                rrUser.getDateOfBirth())

        passengers.add(passengerToAdd)
        return passengerToAdd
    }

    public void addSeniorPassenger() {
        assertMaxNumberOfPassengersHasNotBeenReached()

        Passenger passenger = new Passenger(getSeniorName(), PASSENGER_LAST_NAME, getSeniorGender())
        passenger.beSenior()
        passengers.add(passenger)
    }

    public void addSeniorPassengers(int seniorPassengers) {
        for (int i = 0; i < seniorPassengers; i++) {
            if(flow.isRapidRewardsAfterPurchase || (flow.isLoggedIn && passengers.isEmpty())) {
                addLoggedInPassenger().beSenior()
            }
            else {
                addSeniorPassenger()
            }
        }
    }

    public void addPassengerOfSize() {
        // Note this makes two passengers

        assertMaxNumberOfPassengersHasNotBeenReached()

        final String firstName = getAdultName()
        final String lastName = PASSENGER_LAST_NAME
        final String gender = getAdultGender()

        Passenger primaryPassenger = new Passenger(firstName, lastName, gender)
        primaryPassenger.beAdult()
        passengers.add(primaryPassenger)

        Passenger extraSeatPassenger = new Passenger(firstName, "XS", lastName, gender)
        extraSeatPassenger.beAdult()
        passengers.add(extraSeatPassenger)

    }

    public void addYouthPassenger() {
        addPassenger().beYouth()
    }

    public void addChildPassenger() {
        addPassenger().beChild()
    }

    public void addYoungChildPassenger() {
        addPassenger().beYoungChild()
    }

    // Compound name = multi-part name ex: first name is "billy joe"
    public void addPassengerWithCompoundName(){
        assertMaxNumberOfPassengersHasNotBeenReached()

        Passenger passenger = new Passenger(COMPOUND_PASSENGER_FIRST_NAME.toUpperCase(),
                                            COMPOUND_PASSENGER_MIDDLE_NAME.toUpperCase(),
                                            COMPOUND_PASSENGER_LAST_NAME.toUpperCase(),
                                            GENDER_MALE)
        passenger.beAdult()
        passengers.add(passenger)
    }

    public void addPassengerWithLongName(){
        assertMaxNumberOfPassengersHasNotBeenReached()

        Passenger passenger = new Passenger(VERY_LONG_CUSTOMER_FIRSTNAME.toUpperCase(),
                                            VERY_LONG_CUSTOMER_MIDDLENAME.toUpperCase(),
                                            VERY_LONG_CUSTOMER_LASTNAME.toUpperCase(),
                                            GENDER_MALE)
        passenger.beAdult()

        if(passengers.size() >= 1){
            passengers.clear()
        }

        passengers.add(passenger)
    }

    Passenger getPassengerWithName(String firstName, String lastName){
        Passenger passenger = new Passenger(firstName,'', lastName, GENDER_MALE)
        passenger.beAdult()
        return passenger
    }

    void addPassengerWithName(String firstName, String lastName){
        passengers.add(getPassengerWithName(firstName, lastName))
    }

    void addPassengerWithBirthDay(Date birthDay) {
        Passenger passenger = new Passenger(COMPOUND_PASSENGER_FIRST_NAME,COMPOUND_PASSENGER_MIDDLE_NAME, COMPOUND_PASSENGER_LAST_NAME, GENDER_MALE, birthDay)
        passengers.add(passenger)
    }

    void addAirportCheckinRequiredPassenger() {
        assertMaxNumberOfPassengersHasNotBeenReached()
        Passenger passenger = new Passenger(AIRPORT_CHECKIN_FIRST_NAME, AIRPORT_CHECKIN_MIDDLE_NAME, AIRPORT_CHECKIN_LAST_NAME, GENDER_MALE, AIRPORT_CHECKIN_BIRTHDAY)
        passengers.add(passenger)
    }

    boolean isSenior(Passenger passenger) {
        passenger.isSenior()
    }

    boolean containsSeniorAndAdultPassengers() {
        return containsSeniorPassenger() && containsAdultPassenger()
    }

    boolean containsSeniorPassenger() {
        return getPassengers().any { it.isSenior() }
    }

    boolean containsAdultPassenger() {
        return getPassengers().any { !it.isSenior() }
    }

    private def assertMaxNumberOfPassengersHasNotBeenReached() {
        assertFalse("The booking already has " + MAX_NUMBER_OF_PASSENGER + " passengers, no more may be added.", passengers.size() == MAX_NUMBER_OF_PASSENGER)
    }

    public void addAdultPassengerOfSize(int adultPassengersOfSize) {
        adultPassengersOfSize.times { addPassengerOfSize() }
    }

    public void addAdultWithInvalidMiddleName() {
        assertMaxNumberOfPassengersHasNotBeenReached()

        final String firstName = getAdultName()
        final String invalidMiddleName = "@Danny@"
        final String lastName = PASSENGER_LAST_NAME
        final String gender = getAdultGender()

        Passenger primaryPassenger = new Passenger(firstName, invalidMiddleName, lastName, gender)
        primaryPassenger.beAdult()
        passengers.add(primaryPassenger)
    }

    void addPassengers(List passengerList) {
        for(PassengerType pax in passengerList) {
            switch (pax) {
                case PassengerType.ADULT:
                    addAdultPassenger()
                    break
                case PassengerType.INFANT:
                    addYoungChildPassenger()
                    break
                case PassengerType.MINOR:
                    addChildPassenger()
                    break
                case PassengerType.YT:
                    addYouthPassenger()
                    break
                case PassengerType.SENIOR:
                    addSeniorPassenger()
                    break
                default:
                    addAdultPassenger()
                    break
            }
        }
    }

    List<Passenger> getCheckedInPassengers() {
        return passengers.grep({pax -> pax.isSelectedForCheckin()})
    }

    List<Passenger> getNotCheckedInPassengers() {
        return passengers.grep({pax -> !pax.isSelectedForCheckin()})
    }

    Passenger getPassengersByName(String firstName) {
        return passengers.find({pax -> pax.getFirstName().equals(firstName)})
    }

    public void addPassengerWithAccountType(String rrFirstName, String rrLastName, String rrGender, String rrNumber = "", String rrAccountType = "") {
        assertMaxNumberOfPassengersHasNotBeenReached()
        Passenger passenger = new Passenger(rrFirstName, rrLastName, rrGender, rrNumber, rrAccountType)
        passenger.beAdult()
        passengers.add(passenger)
    }


    public void addSizePassenger(int adultPassengers = 1) {
        for (int i = 0; i < adultPassengers; i++) {
            int count=i%2
            if(count==0)
            {
                if (flow.isRapidRewardsAfterPurchase || (flow.isLoggedIn && passengers.isEmpty()))
                {
                    addLoggedInPassenger().beAdult()
                }
                else
                {
                    addPassenger().beAdult()
                }
                Passenger passenger=passengers.get(i)
                passenger.setMiddleName("")
                passengers.remove(i)
                passengers.add(passenger)
            }
            if(count!=0&&i>0)
            {
                Passenger passenger=passengers.get(i-1)
                passenger.setMiddleName("XS")
                passengers.add(passenger)
            }
            passengers.get(0).setMiddleName("")
        }
    }



}
