package state

import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.Guardian
import domain.PurchaseGiftCardData
import fixtures.loyalty.PastTripSummarySpecification
import domain.Companion

class Flow {

    boolean hasAir = false
    boolean hasHotel = false
    boolean isSwabiz = false
    boolean isAirChange = false
    boolean isLoggedIn = false
    boolean isEarlyBirdCheckInPurchase = false
    boolean isBuyerEqualsFlyer = false
    boolean isRapidRewardsPointsPurchaseOnly = false
    boolean isCarReservationPresent = false
    boolean isRapidRewards = false
    boolean isCustomer = false
    boolean completePurchase = true
    boolean isTravelFundApplied = false
    boolean hasAirportCheckinRequiredPassenger = false
    boolean isFaultInjected = false
    boolean forcePlusDayToSearch = false
    boolean verifyConfirmationNumber=true
    boolean homePage = false
    boolean isEarlyBirdEligibleOutBound
    boolean isEarlyBirdEligibleInBound
    boolean isUpgradingFromCheckin = false

    def cookie
    def canceledPNR

    String tripName = null
    String dreamTripName = null
    boolean alteaDreamTrip = false

    String userLoggedInFirstName = null
    String userLoggedInLastName = null
    String userLoggedInGender = null
    String userLoggedInBirthDay = null
    String userLoggedInBirthMonth = null
    String userLoggedInBirthYear = null
    String userLoggedInEmail = null
    String userLoggedInRapidRewardsNumber = null
    String userLoggedInRapidRewardsAccountType = null
    Date dateAndTimeDepartingFlightArrives = null
    Date dateAndTimeFlightDeparts = null
    PurchaseGiftCardData purchaseGiftCardData
    String primaryIRNSelected = null
    String pickUpLocationCar
    BigDecimal flightPriceOutBound = 0
    BigDecimal flightPriceInBound = 0

    String storedCreditCardValue
    Map<String, String> securityCreditCardCode = new HashMap<String, String>()

    boolean isRewardsTransfer = false
    boolean useStandardCreditCard = false
    boolean useSavedCreditCard = false
    boolean purchaseWithEarlyBird = false
    boolean addToExistingTrip = false
    boolean hasConnectionFlight = false
    boolean hasInboundConnectionFlight = false

    boolean checkForRepricePage = false

    String userCreditCard = null

    Guardian guardianWithContactMethod
    RRUser rrUser

    PastTripSummarySpecification pastTrip

    boolean isTripManagementDatabaseDown = false
    boolean isTripManagementDownForSenior = false
    boolean hasGeneratedUser = false

    boolean isUpcomingTripUnavailable = false
    boolean hasCheckInPassengers = false
    int rapidRewardsPoints = 0
    String[] passengersWithCheckinErrors
    String exludedPassengerForCheckin = "George"
    boolean purchaseWithPoints = false
    String windowBefore = null
    int addMorePromoCodes = 0

    Companion companion
    boolean isUM = false
    Boolean isRapidRewardsAfterPurchase = false

    def setUser(RRUser user) {
        rrUser = user
    }

    RRUser getUser() {
        return rrUser
    }

    def setCompanion(Companion companion) {
        this.companion = companion;
    }

    Companion getCompanion() {
        return this.companion;
    }

    PastTripSummarySpecification getPastTrip() {
        return pastTrip
    }

    void setPastTrip(PastTripSummarySpecification pastTrip) {
        this.pastTrip = pastTrip
    }

    void setCreditCardSecurityCode(String creditCard, String code) {
        this.securityCreditCardCode.put(creditCard, code)
    }

    String getCreditCardSecurityCode(String creditCard) {
        return this.securityCreditCardCode.get(creditCard)
    }
}
