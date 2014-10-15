package htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import htmlunit.pages.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.security.GeneralSecurityException;


public class AirBookingTest {
    WebDriver driver;

    public AirBookingTest() {
        driver = new HtmlUnitDriver() {
            protected WebClient modifyWebClient(final WebClient client) {
                try {
                    client.setUseInsecureSSL(true);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                return client;
            }
        };
    }

    @Test
    public void airBookingTest() {

        searchAndFillPurchaseForm(false);

        Confirmationpage confirmationpage = new Confirmationpage(driver);
        confirmationpage.verifyConfirmationNumber();
    }

    public void searchAndFillPurchaseForm(boolean checkIn) {
        HomePage homePage = new HomePage(driver);


        homePage.openPage(getDotcomDomain());
        System.out.println(getDotcomDomain());
        System.out.println(driver.getCurrentUrl());
        homePage.selectItineraryType("oneway");
        homePage.fillInAirportsJSOff("DAL", "HOU");
        if (checkIn) {
            homePage.fillInForCheckinFlightDates();
        } else {
            homePage.fillInFlightDates();
        }
        homePage.clickSearchForFlights();

        SelectFlightsPage selectFlightsPage = new SelectFlightsPage(driver);
        selectFlightsPage.selectOutBoundFlight();

        PricePage pricePage = new PricePage(driver);
        pricePage.acceptPrice();

        PurchasePage purchasePage = new PurchasePage(driver);
        purchasePage.setFirstName("John");
        purchasePage.setLastName("Doe");

        purchasePage.setDobMonth("1");
        purchasePage.setDobDayOfMonth("1");
        purchasePage.setDobYear("1970");

        purchasePage.setGender("Male");
        purchasePage.setContactMethod("Call");
        purchasePage.setContactAreaCode("817");
        purchasePage.setContactPrefix("911");
        purchasePage.setContactUsPhoneLineNumber("1234");
        purchasePage.setEmailContactAddress("john.doe@wnco.com");

        purchasePage.setCreditCardType("VISA");
        purchasePage.setCreditCardNumber("4024007135532710");
        purchasePage.setSecurityCode("003");
        purchasePage.setExpirationMonth("1");
        purchasePage.setExpirationYear("2013");

        purchasePage.setCreditCardFirstName("John");
        purchasePage.setCreditCardLastName("Doe");
        purchasePage.setCreditCardAddress1("123 Easy Street");
        purchasePage.setCreditCardCity("Dallas");
        purchasePage.setCreditCardState("TX");
        purchasePage.setCreditCardZip1("75062");
        purchasePage.setCreditCardCountry("US");
        purchasePage.setBillerAreaCode("817");
        purchasePage.setBillerPrefix("911");
        purchasePage.setBillerUsPhoneLineNumber("1234");

        purchasePage.setEmailConfirmationAddress("john.doe@wnco.com");

        purchasePage.clickPurchase();
    }

    public static String getDotcomDomain() {
        return "http://" + System.getProperty("domainToTest", "local.swacorp.com");
    }

}
