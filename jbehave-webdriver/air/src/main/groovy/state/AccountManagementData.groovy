package state

import java.util.Map
import com.google.common.collect.ImmutableMap
import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest

class AccountManagementData {

    static final String CNS_LIST = 'CNS'
    static final String IAN_LIST = 'IAN'
    static final String RREU_LIST = 'RREU'
    static final String RRRP_LIST = 'RRRP'

    private static final String CNS_EMAIL = "cns@wnco.com"
    private static final String CNS_TWICE_EMAIL = "cnstwice@wnco.com"
    private static final String RREU_EMAIL = "rreu@wnco.com"
    private static final String RRRP_EMAIL = "rrrp@wnco.com"
    private static final String RRRP_EMAIL_TWICE = "rrrptwice@wnco.com"
    private static final String IAN_EMAIL = "ian@wnco.com"
    private static final String RREU_RRRP_EMAIL = "rreurrrp@wnco.com"

    String number
    String emailAddress
    String secondEmailAddress
    String thirdEmailAddress

    String subscribeList
    String token

    private Map<String, String> subscriptionMap = new ImmutableMap.Builder()
    .put("Click 'N Save", "CNS")
    .put("In a Nutshell", "IAN")
    .put("Rapid Rewards Email Update", "RREU")
    .put("Rapid Rewards Report", "RRRP").build();

    private Map<String, Map<String, String>> tokenMap = new ImmutableMap.Builder()
    .put(CNS_EMAIL, ["CNS" : "75A828A22C91D28B22E6890E40E370519F72AD7D"])
    .put(CNS_TWICE_EMAIL, ["CNS" : "126D9CF7CE90C9AF19C090EB4E1972B5102BF669"])
    .put(RREU_EMAIL, ["RREU" : "27D57D3E8F6298A81B121FB57991E289CB1B82E1"])
    .put(RRRP_EMAIL, ["RRRP" : "D86D222DA28E77DE06D0B3163E8CF5A9A7CE5663"])
    .put(IAN_EMAIL, ["IAN" : "1E96ADA3EE2960CF3631AA78294F8B97B3AF3CF2"])
    .put(RRRP_EMAIL_TWICE, ["RRRP" : "C8C426D4768076FD6CB774545136E5F680AD974C"])
    .put(RREU_RRRP_EMAIL, ["RREU" : "C31392FF1BC9800FC4E7B1635E45908736CBA38B",
                           "RRRP" : "B7548A6FAE6AEEAA66E16AAC6E02D63F28AE7483"]).build();

    public String setAccountData(String list, String email) {
        emailAddress = email
        subscribeList = subscriptionMap.get(list)
        token = tokenMap.get(email).get(subscribeList)
    }

    public String generateEmailAddress(){
        Random rng = new Random()
        String name = "abcdefghijklmnopqrstuvwxyz"
        char[] text = new char[4];
        for (int i = 0; i < 4; i++)
        {
            text[i] = name.charAt(rng.nextInt(name.length()));
        }
        emailAddress = text.toString() + "@wnco.com";
        return emailAddress
    }

    public String generateEmailAndToken(String listName){
        generateEmailAddress()
        token = generateToken(listName)
        return emailAddress
    }

    private String generateToken(String listName){
        String algorithm = "SHA-1"
        String key = "!#\$8197hjskfhakfyvnf9875897584k1u847n08IHJHKYgb78579183458"
        String utf8Encoding = "UTF-8"

        subscribeList = subscriptionMap.get(listName)
        byte[] bytes = MessageDigest.getInstance(algorithm).digest((emailAddress+subscribeList+key).getBytes(utf8Encoding))
        return new String(Hex.encodeHex(bytes)).toUpperCase()
    }
}