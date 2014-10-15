package htmlunit.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: developer
 * Date: 12/19/12
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReconcilePage {
    WebDriver driver;

    public ReconcilePage(WebDriver webDriver) {
        driver = webDriver;
    }

//    public void setFirstName(String firstName){
//        driver.findElement(By.id("firstName0")).sendKeys(firstName);
//    }
//
//    public void setLastName(String lastName){
//        driver.findElement(By.id("lastName0")).sendKeys(lastName);
//    }
//
//    public void setDobMonth(String monthIndexedByOne) {
//        Select select=new Select(driver.findElement(By.id("dobMonth0")));
//        select.selectByValue(monthIndexedByOne);
//    }
//
//    public void setDobDayOfMonth(String dayOfMonth) {
//        Select select=new Select(driver.findElement(By.id("dobDay0")));
//        select.selectByValue(dayOfMonth);
//    }
//
//    public void setDobYear(String dayOfYear) {
//        Select select=new Select(driver.findElement(By.id("dobYear0")));
//        select.selectByValue(dayOfYear);
//    }
//
//    public void setGender(String gender) {
//        Select select=new Select(driver.findElement(By.id("gender0")));
//        select.selectByValue(gender);
//    }

    public void setContactMethod(String contactMethod) {
        Select select = new Select(driver.findElement(By.id("contactMethod")));
        select.selectByValue(contactMethod);
    }

    public void setContactAreaCode(String number) {
        driver.findElement(By.id("contactAreaCode")).sendKeys(number);
    }

    public void setContactPrefix(String number) {
        driver.findElement(By.id("contactPrefix")).sendKeys(number);
    }

    public void setContactUsPhoneLineNumber(String number) {
        driver.findElement(By.id("usPhoneLineNumber")).sendKeys(number);
    }

    public void setEmailContactAddress(String address) {
        driver.findElement(By.id("emailContactAddress")).sendKeys(address);
    }

    public void isAPaymentRequired(String ccType,
                                   String ccNumber,
                                   String ccSecurityCode,
                                   String expirationMonthIndexByOne,
                                   String expirationYear,
                                   String firstName,
                                   String lastName,
                                   String address1,
                                   String city,
                                   String State,
                                   String Zip1,
                                   String country,
                                   String number,
                                   String number2,
                                   String number3) {
        try {
            if (driver.findElement(By.id("creditCard")).isDisplayed()) {

                Select select = new Select(driver.findElement(By.id("creditCardType")));
                select.selectByValue(ccType);

                driver.findElement(By.id("creditCardNumber")).sendKeys(ccNumber);

                new Select(driver.findElement(By.id("expirationMonth")))
                        .selectByValue(expirationMonthIndexByOne);

                new Select(driver.findElement(By.id("expirationYear")))
                        .selectByValue(expirationYear);

                driver.findElement(By.id("securityCode")).sendKeys(ccSecurityCode);

                driver.findElement(By.id("creditCardFirstName")).sendKeys(firstName);

                driver.findElement(By.id("creditCardLastName")).sendKeys(lastName);

                driver.findElement(By.id("creditCardAddress1")).sendKeys(address1);


                driver.findElement(By.id("creditCardCity")).sendKeys(city);

                new Select(driver.findElement(By.id("creditCardState")))
                        .selectByValue(State);

                driver.findElement(By.id("creditCardZip1")).sendKeys(Zip1);

                new Select(driver.findElement(By.id("creditCardCountry")))
                        .selectByValue(country);

                driver.findElement(By.id("billerAreaCode")).sendKeys(number);

                driver.findElement(By.id("billerPrefix")).sendKeys(number2);

                driver.findElement(By.id("billerUsPhoneLineNumber")).sendKeys(number3);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void setEmailConfirmationAddress(String emailConfirmationAddress) {
        driver.findElement(By.id("emailConfirmationAddress")).sendKeys(emailConfirmationAddress);
    }

    public void clickPurchase() {
        driver.findElement(By.id("visibleSubmitButton")).click();
        Assert.assertTrue(driver.getPageSource().contains("body"));
    }

}
