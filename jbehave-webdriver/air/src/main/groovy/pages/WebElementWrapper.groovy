package pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.Point
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriverException

import org.openqa.selenium.Keys
import com.swacorp.dotcom.webscenarios.air.SWARemoteWebDriver

public class WebElementWrapper implements WebElement
{
    private WebElement webElement = null
    private WebElementWrapper()
    {

    }
    public WebElementWrapper(WebElement w)
    {
        this.webElement = w
    }
    def takeScreenShot() {
        BasePage.takeScreenShot()
    }
    void click() {
        this.takeScreenShot()
        try {
            webElement.click()
        } catch (WebDriverException e) {// yes I too have reservations about this one
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" click() id="+webElement.getAttribute("id")+" : Received WebDriverException - "+e.message)
            webElement.click()
        }
    }

    void submit() {
        this.takeScreenShot()
        try {
            webElement.submit()
        } catch (WebDriverException e) {// and this one
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" submit()")
            webElement.submit()
        }
    }

    void sendKeys(CharSequence... charSequences) {
        try {
            webElement.sendKeys(charSequences)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+"sendKeys("+charSequences+")")
            webElement.sendKeys(charSequences)
        } finally {
            if(charSequences.length > 1)
                this.takeScreenShot()
            else if (charSequences.length == 1 && (Keys.TAB != charSequences[0]))
                this.takeScreenShot()
            else
                return
        }

    }

    void clear() {
        try {
            webElement.clear()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" clear()")
            webElement.clear()
        }
    }

    String getTagName() {
        String result = null
        try {
            result = webElement.getTagName()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getTagName()")
            result = webElement.getTagName()
        }

        return result
    }

    String getAttribute(String s) {
        String result = null
        try {
            result = webElement.getAttribute(s)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getAttribute("+s+")")
            result = webElement.getAttribute(s)
        }
        return result
    }

    boolean isSelected() {
        boolean result
        try {
            result = webElement.isSelected()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" isSelected()")
            result = webElement.isSelected()
        }
        return result
    }

    boolean isEnabled() {
        boolean result
        try {
            result = webElement.isEnabled()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" isEnabled()")
            result = webElement.isEnabled()
        }
        return result
    }

    String getText() {
        String result
        try {
            result = webElement.getText()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getText()"+" id="+webElement.getAttribute("id")+" : Received WebDriverException - "+e.message)
            result = webElement.getText()
        }
        return result
    }

    List<WebElement> findElements(By by) {
        List<WebElement> unwrappedResult
        try {
            unwrappedResult = webElement.findElements(by)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" findElements("+by.toString()+")")
            unwrappedResult = webElement.findElements(by)
        }finally{
          return returnWrappedResult(unwrappedResult)
        }
    }

    List<WebElement> returnWrappedResult(List<WebElement> unwrappedResult) {
        if (unwrappedResult == null || unwrappedResult.isEmpty()) return unwrappedResult
        return wrapFindElementsResult(unwrappedResult)
    }

    List<WebElement> wrapFindElementsResult(List<WebElement> unwrappedResult) {
        List<WebElement> wrappedResult = new ArrayList<WebElement>()
        unwrappedResult.each { element ->
            wrappedResult.add(new WebElementWrapper(element))
        }
        return wrappedResult
    }

    WebElement findElement(By by) {
        WebElement result
        try {
            result = webElement.findElement(by)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" findElement("+by.toString()+")")
            result = webElement.findElement(by)
        }
        return result
    }

    boolean isDisplayed() {
        boolean result
        try {
            result = webElement.isDisplayed()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" isDisplayed()")
            result = webElement.isDisplayed()
        }
        return result
    }

    Point getLocation() {
        Point result
        try {
            result = webElement.getLocation()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getLocation()")
            result = webElement.getLocation()
        }
        return result
    }

    Dimension getSize() {
        Dimension result
        try {
            result = webElement.getSize()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING ####################"+" getSize()")
            result = webElement.getSize()
        }
        return result
    }

    String getCssValue(String s) {
        String result
        try {
            result = webElement.getCssValue(s)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel()+" #################### RETRYING #################### "+" getCssValue("+s+")")
            result = webElement.getCssValue(s)
        }
        return result
    }

}
