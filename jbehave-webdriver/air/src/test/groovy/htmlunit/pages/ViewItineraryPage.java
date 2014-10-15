package htmlunit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class ViewItineraryPage {

    WebDriver driver;

    public ViewItineraryPage(WebDriver webDriver){
        driver = webDriver;
    }

    public void verifyItineraryOnViewItineraryPage(){
        driver.findElement(By.id(""));
    }

    public void openPage(String url) {
        driver.get(url);
        driver.findElement(By.id("globalnav_header_primary_link_air")).click();
        driver.findElement(By.xpath("//a[@href='/travel_center/retrieveItinerary.html?forceNewSession=yes']")).click();
    }

    public void enterFirstName(String firstName) {
        driver.findElement(By.id("confirmationNumberFirstName")).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(By.id("confirmationNumberLastName")).sendKeys(lastName);
    }

    public void enterPNR(String pnr) {
        driver.findElement(By.id("confirmationNumber")).sendKeys(pnr);
    }

    public void clickSubmit(){
        driver.findElement(By.id("pnrFriendlyLookup_check_form_submitButton")).click();

    }
}
