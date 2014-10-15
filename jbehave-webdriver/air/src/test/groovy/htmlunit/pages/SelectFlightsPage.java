package htmlunit.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SelectFlightsPage {
    WebDriver driver;

    public SelectFlightsPage(WebDriver webDriver) {
        driver = webDriver;
    }


    public void selectOutBoundFlight() {
        WebElement flight = selectValidFlight();
        Assert.assertNotNull("NO FLIGHTS WERE FOUND", flight);
        flight.click();
        driver.findElement(By.id("priceItinerarySubmit")).click();

        Assert.assertTrue(!driver.getPageSource().contains("id=\"errors\""));
    }

    public WebElement selectValidFlight() {
        List<WebElement> flightRows = driver.findElements(By.xpath("//input[contains(@id, 'Out')]"));

        for(WebElement flightRow : flightRows) {
            if (flightRow.getAttribute("type").equalsIgnoreCase("radio")) {
                return flightRow;
            }
        }
        return null;
    }

}
