package util

public class ChaseSimulatorUrlCreator {
    private Map<?, ?> environmentProperties;
    private static final CHASE_RESPONSE_URL = "/cgi-bin/chaseInstantCCResponse"
    private static final CONSTANT_URL_VALUES = "FULACCT=4211510068850002&FIRST=Leap&MI=A&LAST=Frog&CADRLN=2702+Love+Field+Drive&CATTN=&PHNUM=1111111111&CITY=Dallas&STATE=TX&E_MAIL=invalidemailaddress%40wnco.com"
    private static final CONSTANT_URL_VALUES_ENCRYPTION_FAILED = "FULACCT=4211510068850003&FIRST=Leap&MI=A&LAST=Frog&CADRLN=2702+Love+Field+Drive&CATTN=&PHNUM=1111111111&CITY=Dallas&STATE=TX&E_MAIL=invalidemailaddress%40wnco.com"
    private static final AMPERSAND = "&"
    private static final EQUALS = "="
    private static final CLICK_ID = "CLICK_ID"
    private static final APPROVAL_CODE = "CODE"
    private static final EXP_DATE = "EXPDATE"
    private static final ZIP_CODE = "ZIP"

    private static final CREDIT_LINE  = "CRLINE"
    private static final TMP_CREDIT_LINE = "TEMP_CRLINE"


    public static String createApprovedUrl(String sessionId, String approvedPriceString, String approvalStatus, String zipCode, boolean encryptionFailed){
        String expDateString = generateExpDate()
         return new StringBuilder(CHASE_RESPONSE_URL)
				.append("?").append(CLICK_ID).append(EQUALS).append(sessionId)
				.append(AMPERSAND).append(APPROVAL_CODE).append(EQUALS).append(approvalStatus)
				.append(AMPERSAND).append(EXP_DATE).append(EQUALS).append(expDateString)
                .append(AMPERSAND).append((encryptionFailed)?CONSTANT_URL_VALUES_ENCRYPTION_FAILED:CONSTANT_URL_VALUES)
                .append(AMPERSAND).append(CREDIT_LINE).append(EQUALS).append(approvedPriceString)
				.append(AMPERSAND).append(ZIP_CODE).append(EQUALS).append(zipCode)
				.append(AMPERSAND).append(TMP_CREDIT_LINE).append(EQUALS).append(approvedPriceString).toString();
    }

    private static def generateExpDate() {
        Date expDate = new Date()
        expDate[1] = expDate[1] + 12
        return expDate.format('yyyyMMdd')
    }
}
