package util

class Locators {

    static final def ELEMENT_IDS = [
            "From": "originAirport_displayed",
            "To": "destinationAirport_displayed",
            "Return": "returnAirport_displayed",
            "FromOnJackpot": "area1_displayed",
            "FromOnVacations": "origin_displayed",
            "ToOnVacations": "destination_displayed",
            "originAirport": "originAirport_displayed",
            "destinationAirport": "destinationAirport_displayed",
            "flightNumber": "flightNumber",
            "homeAirport" : "homeAirport1_displayed",
            "homeAirport1" : "homeAirport1_displayed",
            "homeAirport2" : "homeAirport2_displayed",
            "homeAirport3" : "homeAirport3_displayed",
            "emailAirport": "emailAirport_displayed",
            "PickupLocation": "pickUpLocationText",
            "homeAirport1View": "homeAirport1",
            "homeAirport2View": "homeAirport2",
            "homeAirport3View": "homeAirport3"
    ]



    static final def personalInformationIds = ["familiarName", "redressNumber"]

    static final def accountEmailAddressIds = ["emailAddress0"]

    static final def accountInformation = [
            "personalInformation": personalInformationIds,
            "emailAddress": accountEmailAddressIds
    ]
    static final def car = [
            "Economy": "//td[@class='car-results-table-price-column   car-results-table-column-index-0']",
            "Compact": "//td[@class='car-results-table-price-column   car-results-table-column-index-1']",
            "Full-size": "//td[@class='car-results-table-price-column   car-results-table-column-index-3']",
            "Premium": "//td[@class='car-results-table-price-column   car-results-table-column-index-4']",
            "Sport-utility": "//td[@class='car-results-table-price-column   car-results-table-column-index-5']",
            "Luxury": "//td[@class='car-results-table-price-column   car-results-table-column-index-6']",
            "Minivan": "//td[@class='car-results-table-price-column   car-results-table-column-index-7']",
            "Convertible": "//td[@class='car-results-table-price-column   car-results-table-column-index-8']",
            "Fullsizevan": "//td[@class='car-results-table-price-column   car-results-table-column-index-9']",
            "Mid-size": "//td[contains(@class,'car-results-table-column-index-2')]"
    ]

    static final def searchWidgetData = ["Modify Search": "//*[@id = 'modifySearchTitleText']",
                                         "Additional Search Options": "//div[@id = 'carhotel_air_modify_search_widget_addl_opts']"
    ]

    static final def BREADCRUMB_IDS = [
            "ChangeTripPage": "change_trip",
            "SelectNewFlightPage": "select_new_flight",
            "NewPricePage": "new_price",
            "ReconcilePage": "reconcile",
            "ItineraryChangedPage": "itinerary_changed",
            "SearchFlightsPage": "plan_trip",
            "SelectFlightsPage": "select_flight",
            "PricePage": "price",
            "PurchasePage": "purchase",
            "ConfirmationPage": "booked",
            "ChangeTrip": "change_trip",
            "SelectPrintDocumentPage": "checkin_select_flight"
    ]

}
