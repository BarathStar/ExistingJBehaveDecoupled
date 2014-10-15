package htmlunit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;


public class SelectFlightsChangePage {

    WebDriver driver;
    HomePage homePage = new HomePage(driver);

    public SelectFlightsChangePage(WebDriver webDriver) {
        driver = webDriver;
    }

    public void selectFlightsToChange() {
        driver.findElement(By.id("outbound1")).click();
        driver.findElement(By.className("submitButton")).click();

    }

    public void fillInFlightDates() {
        driver.findElement(By.id("outboundDate")).clear();
        driver.findElement(By.id("outboundDate")).sendKeys(homePage.jsOffFormatDeparture);
    }

    public void clickSearchNewFlights() {
        Assert.assertTrue(driver.findElement(By.id("change_trip")).getAttribute("class").contains("current_step"));
        driver.findElement(By.id("selectNewFlightBtn")).click();
    }

}
