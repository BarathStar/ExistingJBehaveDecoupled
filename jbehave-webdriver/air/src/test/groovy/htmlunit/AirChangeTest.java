package htmlunit;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import htmlunit.pages.*;
import org.junit.Ignore;

public class AirChangeTest {

    @Test
    public void airChangeTest() {

        AirBookingTest ab = new AirBookingTest();
        ab.searchAndFillPurchaseForm(false);

        Confirmationpage confirmationpage = new Confirmationpage(ab.driver);
        confirmationpage.verifyConfirmationNumber();
        confirmationpage.changeFlight();

        SelectFlightsChangePage selectFlightsChangePage = new SelectFlightsChangePage(ab.driver);
        selectFlightsChangePage.selectFlightsToChange();
        selectFlightsChangePage.fillInFlightDates();
        selectFlightsChangePage.clickSearchNewFlights();

        new SelectFlightsPage(ab.driver).selectOutBoundFlight();


        RePricePage rePricePage = new RePricePage(ab.driver);
        rePricePage.acceptPrice();

        ReconcilePage reconcilePage = new ReconcilePage(ab.driver);
        reconcilePage.isAPaymentRequired(
                "VISA",
                "4024007135532710",
                "003",
                "1",
                "2013",
                "John",
                "Doe",
                "123 Easy Street",
                "Dallas",
                "TX",
                "75062",
                "US",
                "817",
                "911",
                "1234"
        );
        reconcilePage.setContactMethod("Call");
        reconcilePage.setContactAreaCode("817");
        reconcilePage.setContactPrefix("911");
        reconcilePage.setContactUsPhoneLineNumber("1234");
        reconcilePage.setEmailContactAddress("john.doe@wnco.com");
        reconcilePage.setEmailConfirmationAddress("john.doe@wnco.com");
        reconcilePage.clickPurchase();

        confirmationpage.changedItineraryConfirmation();


    }

}
