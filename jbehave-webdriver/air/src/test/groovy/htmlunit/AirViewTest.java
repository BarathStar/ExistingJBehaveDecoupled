package htmlunit;


import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import htmlunit.pages.*;
import htmlunit.pages.ViewItineraryPage;

import java.security.PublicKey;

public class AirViewTest {

    public String pnr = "";

    @Test
    public void airViewTest() {

        AirBookingTest ab = new AirBookingTest();
        ab.searchAndFillPurchaseForm(false);

        new Confirmationpage(ab.driver).verifyConfirmationNumber();

        ViewItineraryPage viewItineraryPage = new ViewItineraryPage(ab.driver);
        viewItineraryPage.openPage("https://local.swacorp.com");
        viewItineraryPage.enterFirstName("John");
        viewItineraryPage.enterLastName("Doe");
        viewItineraryPage.enterPNR(pnr);
        viewItineraryPage.clickSubmit();
    }

}
