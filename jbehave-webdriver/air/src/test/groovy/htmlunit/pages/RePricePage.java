package htmlunit.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RePricePage {
    WebDriver driver;

    public RePricePage(WebDriver webDriver){
        driver = webDriver;
    }

    public void acceptPrice(){
        Assert.assertTrue(driver.findElement(By.id("new_price")).getAttribute("class").contains("current_step"));
        driver.findElement(By.className("submitButton")).click();

        org.junit.Assert.assertTrue(!driver.getPageSource().contains("id=\"errors\""));
    }
}
