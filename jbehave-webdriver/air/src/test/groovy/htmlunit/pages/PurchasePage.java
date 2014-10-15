package htmlunit.pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class PurchasePage {
    WebDriver driver;
    public PurchasePage(WebDriver webDriver) {
        driver=webDriver;
    }

    public void setFirstName(String firstName){
        driver.findElement(By.id("firstName0")).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(By.id("lastName0")).sendKeys(lastName);
    }

    public void setDobMonth(String monthIndexedByOne) {
        Select select=new Select(driver.findElement(By.id("dobMonth0")));
        select.selectByValue(monthIndexedByOne);
    }

    public void setDobDayOfMonth(String dayOfMonth) {
        Select select=new Select(driver.findElement(By.id("dobDay0")));
        select.selectByValue(dayOfMonth);
    }

    public void setDobYear(String dayOfYear) {
        Select select=new Select(driver.findElement(By.id("dobYear0")));
        select.selectByValue(dayOfYear);
    }

    public void setGender(String gender) {
        Select select=new Select(driver.findElement(By.id("gender0")));
        select.selectByValue(gender);
    }

    public void setContactMethod(String contactMethod) {
        Select select=new Select(driver.findElement(By.id("contactMethod")));
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

    public void setCreditCardType(String ccType) {
        Select select=new Select(driver.findElement(By.id("creditCardType")));
        select.selectByValue(ccType);
    }

    public void setCreditCardNumber(String ccNumber) {
        driver.findElement(By.id("creditCardNumber")).sendKeys(ccNumber);
    }

    public void setExpirationMonth(String expirationMonthIndexByOne) {
        Select select=new Select(driver.findElement(By.id("expirationMonth")));
        select.selectByValue(expirationMonthIndexByOne);
    }

    public void setExpirationYear(String expirationYear) {
        Select select=new Select(driver.findElement(By.id("expirationYear")));
        select.selectByValue(expirationYear);
    }

    public void setSecurityCode(String ccSecurityCode) {
        driver.findElement(By.id("securityCode")).sendKeys(ccSecurityCode);
    }

    public void setCreditCardFirstName(String firstName) {
        driver.findElement(By.id("creditCardFirstName")).sendKeys(firstName);
    }

    public void setCreditCardLastName(String lastName) {
        driver.findElement(By.id("creditCardLastName")).sendKeys(lastName);
    }

    public void setCreditCardAddress1(String address1) {
        driver.findElement(By.id("creditCardAddress1")).sendKeys(address1);
    }

    public void setCreditCardAddress2(String address2) {
        driver.findElement(By.id("creditCardAddress2")).sendKeys(address2);
    }

    public void setCreditCardCity(String city) {
        driver.findElement(By.id("creditCardCity")).sendKeys(city);
    }

    public void setCreditCardState(String state) {
        Select select=new Select(driver.findElement(By.id("creditCardState")));
        select.selectByValue(state);
    }

    public void setCreditCardZip1(String Zip1) {
        driver.findElement(By.id("creditCardZip1")).sendKeys(Zip1);
    }

    public void setCreditCardZip2(String Zip2) {
        driver.findElement(By.id("creditCardZip1")).sendKeys(Zip2);
    }

    public void setCreditCardCountry(String country) {
        new Select(driver.findElement(By.id("creditCardCountry")))
                .selectByValue(country);
    }

    public void setBillerAreaCode(String number) {
        driver.findElement(By.id("billerAreaCode")).sendKeys(number);
    }

    public void setBillerPrefix(String number) {
        driver.findElement(By.id("billerPrefix")).sendKeys(number);
    }

    public void setBillerUsPhoneLineNumber(String number) {
        driver.findElement(By.id("billerUsPhoneLineNumber")).sendKeys(number);
    }

    public void setEmailConfirmationAddress(String emailConfirmationAddress){
        driver.findElement(By.id("emailConfirmationAddress")).sendKeys(emailConfirmationAddress);
    }

    public void clickPurchase(){
        driver.findElement(By.id("visibleSubmitButton")).click();
    }

}
