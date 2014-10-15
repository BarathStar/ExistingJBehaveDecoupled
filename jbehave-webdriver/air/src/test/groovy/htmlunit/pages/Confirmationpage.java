package htmlunit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import htmlunit.AirViewTest;
import org.junit.Assert;

public final class Confirmationpage {
    WebDriver driver;

    String pnr;

    public Confirmationpage(WebDriver webDriver) {
        driver = webDriver;
        pnr = driver.findElement(By.className("itineraries_header_confirmation")).findElement(By.className("confirmation_number")).getText();
    }

    public void verifyConfirmationNumber() {
        new AirViewTest().pnr = pnr;
    }

    public void cancelFlight() {
        driver.findElement(By.id("cancelReservationLink")).click();
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

    public void clickCheckin() {
        driver.findElement(By.id("checkInForFlightButton")).click();
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

    public void changeFlight() {
        driver.findElement(By.id("changeReservationLink")).click();
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

    public void changedItineraryConfirmation() {
        Assert.assertTrue(driver.findElement(By.id("itinerary_changed")).getAttribute("class").contains("current_step"));
    }
}
