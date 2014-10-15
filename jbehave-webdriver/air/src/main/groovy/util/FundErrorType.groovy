package util

/**
 * Created with IntelliJ IDEA.
 * User: developer
 * Date: 9/21/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
public enum FundErrorType {

    NOT_FOUND("travelFund.error.notFound"),
    EXPIRED("travelFund.error.expired"),
    NOT_FOUND_OR_EXPIRED("travelFund.error.expiredOrNotFound"),
    EXPIRED_BEFORE_ITINERARY_COMPLETE("travelFund.error.expirationDate.invalid"),
    SYSTEM_ERROR("travelFund.error.systemError"),
    NO_MATCH("travelFund.error.noMatch"),
    INVALID_CVV("travelFund.error.invalidCvv"),
    INVALID_FUND("travelFund.error.invalidFund"),
    CANCELLED("travelFund.error.cancelled");

    private final String code;

    FundErrorType(String code) {
    this.code = code;
    }

    static FundErrorType from(String fundErrorType) {
        return FundErrorType.values().find {
            it.name() == fundErrorType
        }
    }

    public String fundErrorTypeCode() {
        return code
    }

}