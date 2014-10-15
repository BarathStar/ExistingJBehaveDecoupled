package htmlunit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;


public class CancelConfirmationPage {
    WebDriver driver;

    public CancelConfirmationPage(WebDriver webDriver){
        driver = webDriver;
    }

    public void verifyYouWantToCancel(){
        driver.findElement(By.className("confirmCancellationSubmitButton")).click();
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

    public void cancellationConfirmation() {
        Assert.assertTrue(driver.findElement(By.id("sw_content")).getText().contains("Your reservation has been cancelled."));
    }

}
