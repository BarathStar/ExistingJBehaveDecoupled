package util

import static com.google.common.collect.Lists.newArrayList
import com.swacorp.common.FareProductType

public enum FareClass {

    BusinessSelect("A", newArrayList("K"), "BIZSELECT"),
    Anytime("B", newArrayList("Y"), "ANYTIME"),
    WannaGetAway("C", newArrayList("L","B","Q","H","W","R","O","M","S","N","T"), "WANNAGETAWAY"),
    Ding("D", newArrayList("N"),""),
    Senior("S", newArrayList("Q"),"SENIOR"),
    StandardAward("V", newArrayList("V"),""),
    FreedomAward("X", newArrayList("X"),""),
    PromoCert("V", newArrayList("V","X","Y","T"),"")

    public static List<String> FARE_CLASS_NAMES = ["Business Select", "Anytime", "Senior", "Wanna Get Away", "Standard Award", "Ding"]
    private String fareClass
    private List<String> fareClasses
    private String fareProductType;

    private FareClass(String fareClass, List<String> fareClasses, String fareProductType) {
        this.fareClass = fareClass
        this.fareClasses = fareClasses
        this.fareProductType = fareProductType
    }

    static FareClass from(String fareType) {
        return FareClass.values().find {
            it.name() == fareType
        }
    }

    public String fareClassCode() {
        return fareClass
    }

    public List<String> getFareClasses() {
        return fareClasses;
    }

    public String getFareProduct() {
        return this.fareProductType.toString();
    }
}
