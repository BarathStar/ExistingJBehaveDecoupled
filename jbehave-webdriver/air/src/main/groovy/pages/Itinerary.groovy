package pages

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import steps.conditional.ToggleCR1SeatSelection

public class Itinerary extends BasePage {

    private static WCM_CONTENT = [
            "pricing_note_only_fl": [
                    "This itinerary is operated by AirTran.",
                    "What you need to know to travel:"
            ],
            "pricing_note_only_fl_seat_selection": [
                    "AirTran has assigned seating, and you have the option to select an assigned seat. You may select a seat for the AirTran-operated portion of your itinerary upon confirmation of your flight(s) or anytime prior to checking in.",
                    "Once you check in, you will not be able to change your seat until arrival at the airport."
            ],

            "pricing_note_only_wn": [
                    "This itinerary is operated by Southwest Airlines.",
                    "What you need to know to travel:",
                    "Southwest Airlines does not have assigned seats, so you can choose your seat when you board the plane. You will be assigned a boarding position based on your checkin time. The earlier you check in, within 24 hours of your flight, the earlier you get to board."
            ],

            "pricing_note_mixed_flwn": [
                    "This itinerary is operated by both Southwest Airlines and AirTran.",
                    "What you need to know to travel:",
                    "Southwest Airlines does not have assigned seats, so you can choose your seat when you board the plane. You will be assigned a boarding position based on your checkin time. The earlier you check in, within 24 hours of your flight, the earlier you get to board.",
                    "If you are scheduled to change airlines when you change planes, don't worry - we'll transfer your checked bags for you. If you need to check a bag or print a boarding pass, be sure to go to the ticket counter or kiosk of the airline operating the first portion of your trip.",
            ],
            "pricing_note_mixed_flwn_seat_selection": [
                    "AirTran has assigned seating, and you have the option to select an assigned seat. You may select a seat for the AirTran-operated portion of your itinerary upon confirmation of your flight(s) or anytime prior to checking in.",
                    "Once you check in, you will not be able to change your seat until arrival at the airport.",
                    "Southwest Airlines does not have assigned seats, so you can choose your seat when you board the plane. You will be assigned a boarding position based on your checkin time. The earlier you check in, within 24 hours of your flight, the earlier you get to board.",
                    "If you are scheduled to change airlines when you change planes, don't worry - we’ll transfer your checked bags for you. If you need to check a bag or print a boarding pass, be sure to go to the ticket counter or kiosk of the airline operating the first portion of your trip."
            ],

            "pricing_note_mixed_other": [
                    "This itinerary is operated by both Southwest Airlines and AirTran.",
                    "What you need to know to travel:",
                    "Southwest Airlines does not have assigned seats, so you can choose your seat when you board the plane. You will be assigned a boarding position based on your checkin time. The earlier you check in, within 24 hours of your flight, the earlier you get to board.",
                    "If you need to check a bag or print out a boarding pass, be sure to go to the ticket counter or kiosk of the airline operating the first portion of your trip."
            ],
            "pricing_note_mixed_other_seat_selection": [
                    "AirTran has assigned seating, and you have the option to select an assigned seat. You may select a seat for the AirTran-operated portion of your itinerary upon confirmation of your flight(s) or any time prior to checking in.",
                    "Once you check in, you will not be able to change your seat until arrival at the airport.",
                    "Southwest Airlines does not have assigned seats, so you can choose your seat when you board the plane. You will be assigned a boarding position based on your checkin time. The earlier you check in, within 24 hours of your flight, the earlier you get to board.",
                    "If you need to check a bag or print out a boarding pass, be sure to go to the ticket counter or kiosk of the airline operating the first portion of your trip."
            ]
    ]

    public final static String OPERATED_BY_AIRTRAN =  "Operated by AirTran"

    public Itinerary(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def verifyFlightLogoPresentInBothRows(String airlineDepart, String airlineReturn) {
        verifyDepartFlightLogoPresent(airlineDepart)
        verifyReturnFlightLogoPresent(airlineReturn)
    }

    def verifyDepartFlightLogoPresent(String airlineDepart) {
        verifyElementPresent("${airlineDepart.toUpperCase()} Flight Logo in Depart row",
                By.xpath("//table[@id='airItinerarydepart']//td[@class='flightLogo']/img[contains(@src,'logo_${airlineDepart.toLowerCase()}_air_flight_details')]"))
    }

    def verifyReturnFlightLogoPresent(String airlineReturn) {
        verifyElementPresent("${airlineReturn.toUpperCase()} Flight Logo in Return row",
                By.xpath("//table[@id='airItineraryreturn']//td[@class='flightLogo']/img[contains(@src,'logo_${airlineReturn.toLowerCase()}_air_flight_details')]"))
    }


    def verifyAirlineNamePresentInDepartArriveText(String airlineNameDepart, String airlineNameReturn) {
        verifyAirlineNamePresentInDepartText(airlineNameDepart)
        verifyAirlineNamePresentInArriveText(airlineNameReturn)
    }

    def verifyAirlineNamePresentInDepartText(String airlineNameDepart) {
        verifyTextPresent(airlineNameDepart,
                By.xpath("//table[@id='airItinerarydepart']//td[contains(@class,'routingDetailsStops') and contains(text(), '')]"))
    }

    def verifyAirlineNamePresentInArriveText(String airlineNameReturn) {
        verifyTextPresent(airlineNameReturn,
                By.xpath("//table[@id='airItineraryreturn']//td[contains(@class,'routingDetailsStops') and contains(text(), '')]"))
    }

    def verifyAppropriateOperatingFlightInformationInBothRows(String airlineCodeDepart, String airlineCodeReturn) {
        verifyAppropriateOperatingFlightInformationInDepart(airlineCodeDepart)
        verifyAppropriateOperatingFlightInformationInReturn(airlineCodeReturn)
    }

    def verifyAppropriateOperatingFlightInformationInDepart(String airlineCodeDepart) {
        if (airlineCodeDepart.toUpperCase().equals("FL")) {
            waitForElement(By.id('airItinerarydepart')).getText().shouldContain(OPERATED_BY_AIRTRAN)
        }
    }

    def verifyAppropriateOperatingFlightInformationInReturn(String airlineCodeReturn) {
        if (airlineCodeReturn.toUpperCase().equals("FL")) {
            waitForElement(By.id('airItineraryreturn')).getText().shouldContain(OPERATED_BY_AIRTRAN)
        }
    }

    def verifyTravelGuidelineWCMContent(String wcmContentType) {
        if (ToggleCR1SeatSelection.isOn()) {
            verifyItineraryNotesPresent(WCM_CONTENT[wcmContentType + "_seat_selection"])
        } else {
            verifyItineraryNotesPresent(WCM_CONTENT[wcmContentType])
        }
    }

    private def verifyItineraryNotesPresent(def lines) {
       lines.each() { line ->
            verifyTextPresent(line, By.className("itineraryNote"))
       }
    }

}
