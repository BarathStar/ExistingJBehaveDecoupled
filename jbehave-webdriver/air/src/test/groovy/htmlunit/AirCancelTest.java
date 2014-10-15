package htmlunit;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import htmlunit.pages.*;
import org.junit.Ignore;

public class AirCancelTest {

    @Test
    public void airCancelTest() {

        AirBookingTest ab = new AirBookingTest();
        ab.searchAndFillPurchaseForm(false);

        new Confirmationpage(ab.driver).cancelFlight();

        CancelConfirmationPage cancelConfirmationPage = new CancelConfirmationPage(ab.driver);
        cancelConfirmationPage.verifyYouWantToCancel();
        cancelConfirmationPage.cancellationConfirmation();
    }

}
