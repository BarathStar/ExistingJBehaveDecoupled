package htmlunit.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PricePage {
    WebDriver driver;

    public PricePage(WebDriver webDriver){
        driver = webDriver;
    }

    public void acceptPrice(){
        Assert.assertTrue(driver.findElement(By.className("current_step")).getText().contains("Price"));
        driver.findElement(By.id("priceSubmitButton")).click();

        org.junit.Assert.assertTrue(!driver.getPageSource().contains("id=\"errors\""));
    }
}
