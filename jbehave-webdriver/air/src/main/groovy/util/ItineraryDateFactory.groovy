package util

import org.joda.time.LocalDate

class ItineraryDateFactory {

    private static final int ONE_DAY = 1
    private static final Date dateScheduleDataStarts = new Date()
    // NOTE: the dateScheduleDataExpires must be:
    //          (a) the same or less than the default award certificate expiration date as defined in the service tier
    //              as PromotionalProductSpecification.certificateExpirationDate, currently offset 6 months from today.
    //          (b) before the last bookable date, currently offset 125 days from today when using dynastubs. With live
    //              services, the last bookable date will depend on actual loaded schedules.
    //       For testing purposes, a last bookable date of 60 days from today is being assumed as this meets both
    //       criteria (a) and (b) above.
    private static final Date dateScheduleDataExpires = new LocalDate(dateScheduleDataStarts).plusDays(30).toDate()
    private static final Date lastAvailableDepartureDate = new LocalDate(dateScheduleDataExpires).minusDays(10).toDate()
    private static final Date lastAvailableReturnDate = new LocalDate(dateScheduleDataExpires).minusDays(1).toDate()
    private static final TimeZone CST_TIMEZONE = TimeZone.getTimeZone("America/Chicago");
    private static final int ONE_PM = 13
    private static final int SIX_PM = 18
    private static Random rand = new Random();

    static Map getRoundTripDates() {
        def map = [:]
        Date departureDate = getAnyAvailableDepartureDate()
        Date returnDate = getAnyAvailableReturnDateAfter(departureDate)
        if (departureDate > returnDate) {
            departureDate = returnDate
        }
        map.put("departureDate", departureDate)
        map.put("returnDate", returnDate)
        return map
    }

    static Date getAnyAvailableDepartureDate() {
        Date departureDate = pickRandomDateBetween(dateScheduleDataStarts, lastAvailableDepartureDate)
        return departureDate
    }

    static Date getWannaGetAwayDepartureDate() {
        Date departureDate = pickRandomDateBetween(dateScheduleDataStarts.plus(10), lastAvailableDepartureDate)
        return  departureDate;
    }

    public static Date getAnyAvailableReturnDateAfter(Date departureDate) {
        Date returnDate = pickRandomDateBetween(departureDate, lastAvailableReturnDate)
        return returnDate
    }

    private static Date pickRandomDateBetween(Date startingDate, Date endingDate) {
        int daysOut = endingDate - startingDate
        Date pickedDate = (daysOut > 0) ? startingDate.plus(Math.max(rand.nextInt(daysOut), 1)): startingDate

        // avoid DST and the day before DST
        if (isDaylightTimeTransition(pickedDate) || isDaylightTimeTransition(pickedDate.plus(ONE_DAY))) {
            if (daysOut < 2) {
                pickedDate = startingDate
            } else {
                pickedDate = pickRandomDateBetween(startingDate.plus(ONE_DAY), endingDate.minus(ONE_DAY))
            }
        }

        return pickedDate;
    }

    private static boolean isDaylightTimeTransition(Date date) {
        return CST_TIMEZONE.inDaylightTime(date) != CST_TIMEZONE.inDaylightTime(date.plus(ONE_DAY));
    }

    public static Date getAnyAvailableDepartureDateForEarlyBird() {
        Date departureDate = pickRandomDateBetween(dateScheduleDataStarts, lastAvailableDepartureDate)
        return departureDate
    }

    static Date chooseDayEligibleForReturnCheckin() {
        return chooseDayEligibleForCheckin(ONE_PM)
    }

    static Date chooseDayEligibleForDepartureCheckin() {
        return chooseDayEligibleForCheckin(SIX_PM)
    }

    static Date chooseDayEligibleForCheckin(int cutOffTime) {
        def today = new Date()
        if (today.hours < cutOffTime) {
            return today
        } else {
            return today.plus(ONE_DAY)
        }
    }

    static Date getTomorrowDate(Date departureDate = new Date()) {
        return  departureDate.plus(ONE_DAY)
    }

    static Date getNumberDaysOutDate(Integer numberDaysOut) {
        Date date = new Date();
        return date.plus(numberDaysOut)
    }

    public static Date getPreviousDate(Date aFlightDate) {
        return aFlightDate.minus(ONE_DAY)
    }
}
