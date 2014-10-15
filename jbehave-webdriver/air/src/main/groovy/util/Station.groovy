package util

import static org.apache.commons.lang.StringUtils.isNotBlank

public enum Station {
    DAL("Dallas", "TX", "Love Field", "Dallas"),
    MDW("Chicago", "IL", "Midway", "Chicago"),
    LGA("New York", "NY", "LaGuardia", "New York"),
    TUL("Tulsa", "OK", "", "Tulsa"),
    DEN("Denver", "CO", "", "Denver"),
    ARU("Aruba", "Aruba", "", "Aruba"),
    BDA("Bermuda", "Bermuda", "", "Bermuda"),
    ACY("Atlantic City", "NJ", "", "Atlantic City"),
    SJU("San Juan", "PR", "", "San Juan"),
    PHX("Phoenix", "AZ", "", "Phoenix"),
    HOU("Houston", "TX", "Hobby", "Houston"),
    DTW("Detroit", "MI", "", "Detroit"),
    LAS("Las Vegas", "NV", "", "Las Vegas"),
    SMF("Sacramento", "CA", "", "Sacramento"),
    SFO("San Francisco", "CA", "", "San Francisco"),
    ABQ("Albuquerque", "NM", "", "Albuquerque"),
    LAX("Los Angeles", "CA", "", "Los Angeles"),
    SAT("San Antonio", "TX", "", "San Antonio"),
    BOS("Boston Logan", "MA", "", "Boston"),
    BWI("Baltimore/Washington", "MD", "", "Baltimore"),
    FLL("Fort Lauderdale", "FL", "", "Ft. Lauderdale"),
    MCO("Orlando", "FL", "", "Orlando"),
    STL("St. Louis", "MO", "", "St. Louis"),
    ATL("Atlanta", "GA", "", "Atlanta"),
    AUS("Austin", "TX", "", "Austin"),
    JAX("Jacksonville", "FL", "", "Jacksonville"),
    GRR("Grand Rapids", "MI", "", "Grand Rapids"),
    MKE("Milwaukee", "WI", "", "Milwaukee"),
    PWM("Portland", "ME", "", "Portland"),
    ELP("El Paso", "TX", "", "El Paso"),
    LIT("Little Rock", "AR", "", "Little Rock"),
    AMA("Amarillo", "TX", "", "Amarillo"),
    MCI("Kansas City", "MO", "", "Kansas City"),
	OKC("Oklahoma City", "OK", "", "Kansas City"),
    BNA("Nashville", "TN", "", "Nashville"),
    MEX("Mexico City", "Mexico", "", "Mexico City")

    private String city
    private String state
    private String airportName
    private String crossSellCity

    private Station(String city, String state, String airportName, String crossSellCity) {
        this.city = city
        this.state = state
        this.airportName = airportName
        this.crossSellCity = crossSellCity
    }

    public String cityStateAirportCode() {
        return city + ", " + state + " (" + this.name() + ")"
    }

    public String airTranStationName() {
        return city + ", " + state + (isNotBlank(airportName) ? (" - " + airportName) : "")
    }

    public String getCityName() {
        return city
    }

    public String getCrossSellCityName() {
        return crossSellCity
    }

    public String getCityFormat() {
        return city + (isNotBlank(airportName) ? (" (" + airportName + "), ") : ", ") + state + " - " + this.name()
    }
}
