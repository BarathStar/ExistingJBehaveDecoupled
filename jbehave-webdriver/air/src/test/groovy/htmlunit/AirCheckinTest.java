package htmlunit;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import htmlunit.pages.*;
import org.junit.Ignore;

public class AirCheckinTest {

    @Test
    public void airCheckinTest() {

        AirBookingTest ab = new AirBookingTest();
        ab.searchAndFillPurchaseForm(true);

        Confirmationpage confirmationpage = new Confirmationpage(ab.driver);
        confirmationpage.verifyConfirmationNumber();
        confirmationpage.clickCheckin();

        CheckInPage checkinPage = new CheckInPage(ab.driver);
        checkinPage.confirmCheckIn();
        checkinPage.viewBoardingPass();
    }

}
