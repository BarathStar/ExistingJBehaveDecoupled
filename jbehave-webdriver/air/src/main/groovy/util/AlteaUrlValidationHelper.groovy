package util

import domain.AirReservation
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.openqa.selenium.By

import static org.apache.commons.lang.StringUtils.substringBefore
import static org.apache.commons.lang.StringUtils.substringBetween
import static org.apache.commons.lang.StringUtils.substringAfter

class AlteaUrlValidationHelper {
    static final By PROXY_RESPONSE = By.id("errorPageContainer")
    static final String SCHEMA = "schema"
    static final String DOMAIN = "domain"
    static final String PATH = "path"
    static final String QUERY_ELEMENT = "query"
    static final String VALUE_ELEMENT = "value"
    static final String KEY_ELEMENT = "key"

    static final String LANGUAGE = "LANGUAGE"
    static final String SITE = "SITE"
    static final String MAX_AIR_AVAIL_DAYS = "MAX_AIR_AVAIL_DAYS"
    static final String ENCT = "ENCT"
    static final String ENC = "ENC"
    static final String LASTNAME = "LASTNAME"
    static final String REC_LOC = "REC_LOC"
    static final String LANGUAGE_US = "US"
    static final String AES_ENCRYPTION = "2"
    static final String RETURN_URL = "RETURN_URL"

    static final String DEPARTURE_DATE = "DATE_1"
    static final String RETURN_DATE = "DATE_2"
    static final String TRIP_TYPE = "TRIP_TYPE"
    static final String DEPARTURE_CITY = "B_LOCATION_1"
    static final String ARRIVAL_CITY = "E_LOCATION_1"
    static final String RETURN_CITY = "E_LOCATION_2"
    static final String NUMBER_ADULTS = "NB_ADT"
    static final String NUMBER_SENIORS = "NB_YCD"

    static final String QUERY_STRING_CONTAINS =  "contains query string parameter: "
    static final String QUERY_STRING_PARM = "query string parameter: "

    // Common
    static final String SCHEME_ERROR_MSG = "scheme attribute"
    static final String DOMAIN_ERROR_MSG = "domain attribute"
    static final String PATH_ERROR_MSG = "path attribute"
    static final String PORT_ERROR_MSG = "port attribute"
    static final String LANGUAGE_ERROR_MSG = QUERY_STRING_PARM + LANGUAGE
    static final String SITE_ERROR_MSG = QUERY_STRING_PARM + SITE
    static final String MAX_AIR_AVAIL_DAYS_ERROR_MSG = QUERY_STRING_CONTAINS + MAX_AIR_AVAIL_DAYS
    static final String ENCT_ERROR_MSG = QUERY_STRING_PARM + ENCT
    static final String ENC_ERROR_MSG = QUERY_STRING_CONTAINS + ENC
    static final String LASTNAME_ERROR_MSG = QUERY_STRING_PARM + LASTNAME
    static final String REC_LOC_ERROR_MSG = QUERY_STRING_PARM + REC_LOC
    static final String RETURN_URL_ERROR_MSG = QUERY_STRING_CONTAINS + RETURN_URL

    // Availability
    static final String DEPARTURE_DATE_ERROR_MSG = QUERY_STRING_PARM + DEPARTURE_DATE
    static final String RETURN_DATE_ERROR_MSG = QUERY_STRING_PARM + RETURN_DATE
    static final String TRIP_TYPE_ERROR_MSG = QUERY_STRING_PARM + TRIP_TYPE
    static final String DEPARTURE_CITY_ERROR_MSG = QUERY_STRING_PARM + DEPARTURE_CITY
    static final String ARRIVAL_CITY_ERROR_MSG = QUERY_STRING_PARM + ARRIVAL_CITY
    static final String RETURN_CITY_ERROR_MSG = QUERY_STRING_PARM + RETURN_CITY
    static final String NUMBER_ADULTS_ERROR_MSG = QUERY_STRING_PARM + NUMBER_ADULTS
    static final String NUMBER_SENIORS_ERROR_MSG = QUERY_STRING_PARM + NUMBER_SENIORS

    static final String ALTEA_SCHEME = "https"
    static final String ALTEA_SITE = "ADCQADCQ"
    static final String ALTEA_DOMAIN = "local.swacorp.com"
    static final String ALTEA_PORT = "7001"
    static final String ALTEA_CHANGE_PATH = "/southwest/dyn/air/servicing/changeReservation"
    static final String ALTEA_VIEW_PATH = "/southwest/dyn/air/servicing/manageReservation"
    static final String ALTEA_CANCEL_PATH = "/southwest/dyn/air/servicing/cancel"
    static final String ALTEA_COMPANION_PATH = "/southwest/dyn/air/booking/companion"
    static final String ALTEA_AVAILABILITY_PATH = "/southwest/dyn/air/booking/availability"
    static final String ALTEA_EARLY_BIRD_PATH = "/southwest/dyn/air/servicing/earlyBirdCheckin"
    static final String ALTEA_ADD_RAPID_REWARDS_PATH = "/southwest/dyn/air/servicing/addRapidReward"
    static final String ALTEA_UPGRADE_PATH = "/southwest/dyn/air/servicing/upgrade"
    static final String ALTEA_CHECK_IN_PATH = "/southwest/dyn/air/servicing/checkin"
    static final String QUERY_PARAM_DE_LIMITER = "&"
    static final String QUERY_PARAM_VALUE_DE_LIMITER = "="
    static final String DOMAIN_BEGIN_DE_LIMITER = "://"
    static final String DOMAIN_END_DE_LIMITER = ":"
    static final String PORT_END_DE_LIMITER = "/"
    static final String QUERY_STRING_DE_LIMITER = "?"

    static def verifyLowFareSearchPage(String url, String origin, String destination, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_AVAILABILITY_PATH, isRapidRewards)

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmm")
        DateTime departureDate = dateTimeFormatter.parseDateTime(parameterMap.get(DEPARTURE_DATE))

        parameterMap.get(DEPARTURE_CITY).shouldBe origin, DEPARTURE_CITY_ERROR_MSG
        parameterMap.get(ARRIVAL_CITY).shouldBe destination, ARRIVAL_CITY_ERROR_MSG

        String tripType = parameterMap.get(TRIP_TYPE)
        String returnCity = parameterMap.get(RETURN_CITY)

        if (tripType.equals("R")) {
            parameterMap.get(RETURN_DATE)
            DateTime returnDate = dateTimeFormatter.parseDateTime(parameterMap.get(RETURN_DATE))
            returnDate.isAfter(departureDate).shouldBe true, RETURN_DATE_ERROR_MSG
            parameterMap.get(RETURN_CITY).shouldBe returnCity, RETURN_CITY_ERROR_MSG
        } else if (tripType.equals("O")) {
            parameterMap.get(RETURN_CITY).shouldBe "", RETURN_CITY_ERROR_MSG
        }

        int numberOfAdults = Integer.parseInt(parameterMap.get(NUMBER_ADULTS))
        numberOfAdults.shouldBeGreaterThan 0, NUMBER_ADULTS_ERROR_MSG
        parameterMap.get(NUMBER_SENIORS).shouldBe "0", NUMBER_SENIORS_ERROR_MSG
    }

    static def verifyCancelReservationLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_CANCEL_PATH, isRapidRewards)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyChangeReservationLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_CHANGE_PATH, isRapidRewards)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyViewReservationLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_VIEW_PATH, isRapidRewards)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyAddRRLink(String url, AirReservation airReservation, boolean isRapidRewards) {
            def parameterMap = commonLinkValidation(url, ALTEA_ADD_RAPID_REWARDS_PATH, isRapidRewards)

            parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
            parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyUpgradeReservationLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_UPGRADE_PATH, isRapidRewards)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyLoyaltyAddCompanionLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_COMPANION_PATH, true)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    static def verifyEarlyBirdButton(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_EARLY_BIRD_PATH, isRapidRewards)

        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
    }

    static def verifyCheckInLink(String url, AirReservation airReservation, boolean isRapidRewards) {
        def parameterMap = commonLinkValidation(url, ALTEA_CHECK_IN_PATH, isRapidRewards)

        parameterMap.get(LASTNAME).shouldContain airReservation.passengers.get(0).getSecureLastName(), LASTNAME_ERROR_MSG
        parameterMap.get(REC_LOC).shouldBe airReservation.adultPnr, REC_LOC_ERROR_MSG
    }

    public static def commonLinkValidation(String url, String expectedPath, boolean isRapidRewards) {
        validatePath(url, expectedPath)
        def parameterMap = parseQueryParameters(url)
        validateCommonParameters(parameterMap, isRapidRewards)

        parameterMap
    }

    private static def validatePath(String url, String expectedPath) {
        String scheme = substringBefore(url, DOMAIN_BEGIN_DE_LIMITER)
        String domain = substringBetween(url, DOMAIN_BEGIN_DE_LIMITER, DOMAIN_END_DE_LIMITER)
        String port = substringBetween(url, domain + DOMAIN_END_DE_LIMITER, PORT_END_DE_LIMITER)
        String path = substringBetween(url, port, QUERY_STRING_DE_LIMITER)
        scheme.shouldBe ALTEA_SCHEME, SCHEME_ERROR_MSG
        domain.shouldBe ALTEA_DOMAIN, DOMAIN_ERROR_MSG
        port.shouldBe ALTEA_PORT, PORT_ERROR_MSG
        path.shouldBe expectedPath, PATH_ERROR_MSG
    }

    private static def parseQueryParameters(String url) {
        String queryString = substringAfter(url, QUERY_STRING_DE_LIMITER);
        def parameterMap = new HashMap<String, String>()
        for (String queryParam : queryString.split(QUERY_PARAM_DE_LIMITER)) {
            parameterMap.put(substringBefore(queryParam, QUERY_PARAM_VALUE_DE_LIMITER), substringAfter(queryParam, QUERY_PARAM_VALUE_DE_LIMITER))
        }
        parameterMap
    }

    private static def validateCommonParameters(HashMap<String, String> parameterMap, boolean isRapidRewards) {
        parameterMap.get(LANGUAGE).shouldBe LANGUAGE_US, LANGUAGE_ERROR_MSG
        parameterMap.get(SITE).shouldBe ALTEA_SITE, SITE_ERROR_MSG
        parameterMap.containsKey(MAX_AIR_AVAIL_DAYS).shouldBe true, MAX_AIR_AVAIL_DAYS_ERROR_MSG

        if (isRapidRewards) {
            parameterMap.get(ENCT).shouldBe AES_ENCRYPTION, ENCT_ERROR_MSG
            parameterMap.containsKey(ENC).shouldBe true, ENC_ERROR_MSG
        }
    }
}
