package htmlunit.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckInPage {
    WebDriver driver;

    public CheckInPage(WebDriver webDriver){
        driver = webDriver;
    }

    public void confirmCheckIn(){
        Assert.assertTrue(driver.getPageSource().contains("body"));
        driver.findElement(By.id("printDocumentsButton")).click();
    }

    public void viewBoardingPass() {
        driver.findElement(By.className("checkinDocument"));
    }
}