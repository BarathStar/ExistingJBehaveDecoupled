package  util
enum PassengerType {
    ADULT("adult", 18..64),
    INFANT("infant", 0..1),
    CHILD("child", 2..5),
    MINOR("minor", 12..17),
    YT("young traveler", 6..11),
    SENIOR("senior", 65..199)

    public final String passengerType
    public final Range ageRange

    PassengerType(String type, Range ageRange) {
        this.passengerType = type
        this.ageRange = ageRange
    }


    static PassengerType fromType(String type) {
        return PassengerType.values().find {
            it.passengerType == type
        }
    }
}

