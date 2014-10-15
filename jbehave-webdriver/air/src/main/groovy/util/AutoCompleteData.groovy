package util

class AutoCompleteData {

    private static final def NEW_YORK_AREA_AIRPORTS =
    "New York Area AirportsNew York (LaGuardia), NY - LGANewark, NJ - EWRLong Island, NY - ISP"

    private static final def ALL_NEW_YORK_AREA_AIRPORTS =
    "New York Area AirportsNew York (LaGuardia), NY - LGANewark, NJ - EWRLong Island, NY - ISPWhite Plains, NY - HPN"

    private static final def AIRPORT_NOT_FOUND =
    "No airport found"

    private static final def SAN_FRANCISCO_AREA_AIRPORTS =
    "San Francisco Area AirportsSan Francisco, CA - SFOSan Jose, CA - SJCOakland, CA - OAK"

    private static final def INVALID_ROUTE_WITH_DESTINATION =
    "Invalid route with departure airport"

    static final def AUTOCOMPLETE_FULL_CITY_TEXT = [
            "ATL": "Atlanta, GA - ATL"
    ]

    static final def AUTOCOMPLETE_DETAILS = [
            "AirportNotFound": AIRPORT_NOT_FOUND,
            "airport not found message": AIRPORT_NOT_FOUND,
            "No airport found": "No airport found",
            "AUS": "Austin, TX - AUS",
            "Invalid": "No airport found",
            "InvalidRoute" : INVALID_ROUTE_WITH_DESTINATION,
            "dal": "Dallas (Love Field), TX - DAL",
            "DAL": "Dallas (Love Field), TX - DAL",
            "Dallas": "Dallas (Love Field), TX - DAL",
            "DALLAS AREA": AIRPORT_NOT_FOUND,
            "Ft. Worth": AIRPORT_NOT_FOUND,
            "Ft.": "Ft. Myers, FL - RSWMiami Area AirportsFt. Lauderdale, FL - FLL",
            "HOU": "Houston (Hobby), TX - HOU",
            "ARU": "Aruba, AW - AUA",
            "San": "San Diego, CA - SANSan Antonio, TX - SAT" + SAN_FRANCISCO_AREA_AIRPORTS + "San Juan, PR - SJU",
            "San-Domestic": "San Diego, CA - SANSan Antonio, TX - SAT" + SAN_FRANCISCO_AREA_AIRPORTS,
            "BOS": "Boston Area AirportsBoston Logan, MA - BOSManchester, NH - MHTProvidence, RI - PVD",
            "New": "New Orleans, LA - MSY" + NEW_YORK_AREA_AIRPORTS,
            "MDWToNew": "New Orleans, LA - MSYNew York Area AirportsNew York (LaGuardia), NY - LGANewark, NJ - EWRLong Island, NY - ISP",
            "MSYToNew": NEW_YORK_AREA_AIRPORTS,
            "St.": "Minneapolis/St. Paul (Terminal 2), MN - MSPSt. Louis, MO - STL",
            "St_p": "Minneapolis/St. Paul, MN - MSP",
            "OakToMex": "Mexico City Area AirportsMexico City/D.F., MX - MEXMexico City",
            "MdwToMex": "Mexico City Area AirportsMexico City/D.F., MX - MEXMexico City/Toluca, MX - TLC",
            "MEX": "Mexico City Area AirportsMexico City/D.F., MX - MEXToluca/Mexico City, MX - TLCMexicali, MX - MXL",
            "TLC": "Mexico City Area AirportsToluca/Mexico City, MX - TLCMexico City/D.F., MX - MEX",
            "ElPaso": "El Paso, TX - ELP",
            "SanToLasVegas": "San Diego, CA - SANSan Antonio, TX - SATSan Francisco Area AirportsSan Francisco, CA - SFOSan Jose, CA - SJCOakland, CA - OAK",
            "AruToNew": "New York Area AirportsNew York (LaGuardia), NY - LGANewport News, VA - PHF",
            "DayToAtl": "Invalid route with departure airport",
            "LoveField": "Dallas (Love Field), TX - DAL",
            "LGA": "New York Area AirportsNew York (LaGuardia), NY - LGALong Island, NY - ISPNewark, NJ - EWRWhite Plains, NY - HPN",
            "EWR": "New York Area AirportsNew York (LaGuardia), NY - LGALong Island, NY - ISPNewark, NJ - EWRWhite Plains, NY - HPN",
            "ISP": "New York Area AirportsNew York (LaGuardia), NY - LGALong Island, NY - ISPNewark, NJ - EWRWhite Plains, NY - HPN",
            "LGAHPN": "New York Area AirportsNew York (LaGuardia), NY - LGALong Island, NY - ISPNewark, NJ - EWRWhite Plains, NY - HPN",
            "ACY": "Atlantic City, NJ - ACY",
            "CAK": "Cleveland Area AirportsAkron/Canton, OH - CAKCleveland, OH - CLE",
            "CLE": "Cleveland Area AirportsCleveland, OH - CLEAkron/Canton, OH - CAK",
            "ABE": "Allentown, PA - ABE",
            "ATL": "Atlanta, GA - ATLAtlantic City, NJ - ACY",
            "ATLCAR": "Atlanta, GA - ATL",
            "SRQ": "Tampa Bay Area AirportsSarasota/Bradenton, FL - SRQTampa Bay, FL - TPA",
            "TPA": "Tampa Bay Area AirportsTampa Bay, FL - TPASarasota/Bradenton, FL - SRQ",
            "DAY": "Cincinnati Area AirportsDayton, OH - DAY",
            "DCA": "Washington DC Area AirportsWashington (Reagan), DC - DCABaltimore/Washington, MD - BWIWashington (Dulles), DC - IAD",
            "PNS": "Northwest Florida Beaches Area AirportsPensacola, FL - PNSPanama City Beach, FL - ECP",
            "HPN": "New York Area AirportsWhite Plains, NY - HPNLong Island, NY - ISPNew York (LaGuardia), NY - LGANewark, NJ - EWR",
            "MIA": "Miami Area AirportsMiami, FL - MIAFt. Lauderdale, FL - FLL",
            "SJU": "San Juan, PR",
            "FLL": "Fort Lauderdale, FL",
            "ATLair": "Atlanta, GA",
            "CAN": "Cancun, Mexico",
            "CUN": "Cancun, MX - CUN",
            "MDW": "Chicago, IL - Midway",
            "MCO": "Orlando" ,
            "SEA": "Seattle/Tacoma, WA - SEA",
            "los": "Los Angeles Area AirportsLos Angeles, CA - LAXBurbank, CA - BUROntario/LA, CA - ONTOrange County, CA - SNACabo/Los Cabos, MX - SJDLos Mochis, MX - LMM",
            "sjd": "Cabo/Los Cabos, MX - SJD",
            "cab": "Cabo/Los Cabos, MX - SJD"
    ]

    static final def ROUTE_DETAILS = [
            "DAL-AUS": "Austin, TX - AUS",
            "AUS-SAT": "Invalid route with departure airport"
    ]


    static final def InternationalAndDomesticTravelOnAirTran = [
            "ATL": "Atlanta, GA - ATL",
            "CUN": "Cancun, MX - CUN",
            "los": "Los Angeles Area AirportsLos Angeles, CA - LAXOrange County, CA - SNACabo/Los Cabos, MX - SJD",
            "sjd": "Cabo/Los Cabos, MX - SJD",
            "cab": "Cabo/Los Cabos, MX - SJD"
    ]
    static final def AIRTRAN_ONLY_MARKETS = [
            "ATL - AUA",
            "ATL - BDA",
            "ATL - CUN",
            "ATL - MBJ",
            "ATL - NAS",
            "ATL - PUJ",
            "ATL - SJU",
            "AUA - ATL",
            "AUA - MCO",
            "BDA - ATL",
            "BDA - BWI",
            "BWI - BDA",
            "BWI - CUN",
            "BWI - MBJ",
            "BWI - NAS",
            "BWI - SJU",
            "CUN - ATL",
            "CUN - BWI",
            "CUN - MKE",
            "IND - SJU",
            "MBJ - ATL",
            "MBJ - BWI",
            "MBJ - MCO",
            "MCO - AUA",
            "MCO - MBJ",
            "MCO - SJU",
            "MKE - CUN",
            "NAS - ATL",
            "NAS - BWI",
            "PIT - SJU",
            "PUJ - ATL",
            "SJU - ATL",
            "SJU - BWI",
            "SJU - IND",
            "SJU - MCO",
            "SJU - PIT",
            "SJU - TPA",
            "TPA - SJU"
    ]
    static final def AIRTRAN_ONLY_INTERNATIONAL_STATIONS = [
            "AUA , Aruba,NA",
            "BDA , Bermuda,BD",
            "MBJ , Montego Bay,JM",
            "PUJ , Punta Cana,DR"
    ]

    static String retrieveAirTranInternationalStation() {
        int maxValue = AIRTRAN_ONLY_INTERNATIONAL_STATIONS.size()
        int radomValue = (new Random()).nextInt(maxValue)
        println('index of international AirTran only: ' + radomValue)
        String station = AIRTRAN_ONLY_INTERNATIONAL_STATIONS.get(radomValue)
        return (station.substring(0, 3))
    }
}
