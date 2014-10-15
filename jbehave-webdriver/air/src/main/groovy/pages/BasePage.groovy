package pages

import com.github.tanob.groobe.GrooBe
import com.google.common.base.Predicate
import com.swacorp.dotcom.webscenarios.air.AirStories
import com.swacorp.dotcom.webscenarios.air.SWARemoteWebDriver
import com.swacorp.dotcom.webscenarios.air.config.Domains
import fixture.stubs.DynaStubsIntegration
import junit.framework.AssertionFailedError
import org.jbehave.web.selenium.WebDriverPage
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.*
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import state.Flow
import state.SWAContextView
import state.UserPageContext
import util.Station
import java.text.SimpleDateFormat
import static org.junit.Assert.fail
import pages.mixins.*

@Mixin(NotificationsVerification.class)
public class BasePage extends WebDriverPage {

    protected static final String SERVICE = 'SERVICE'
    protected static final String WEB = 'WEB'

    static Boolean LOCAL_SCREENSHOTS = !AirStories.FAULT_TESTING ? System.getProperty("local.screenshots") != null : false
    static Boolean PAGE_SOURCE_CAPTURE = System.getProperty("page.cap") != null
    static String DELETE_EXISTING = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE
    static String CLEAR_FIELD = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE + Keys.TAB
    static Integer DEFAULT_WAIT_LIMIT = 45
    static Integer DEFAULT_AJAX_WAIT_LIMIT = 60
    static Integer DEFAULT_INTERVAL = 0.5
    static Integer DEFAULT_WAIT_RETRY = 1000
    static Integer DEFAULT_RETRY = 5
    static Integer DEFAULT_WAIT_LIMIT_FOR_ELEMENT_THAT_MAY_EXIST = 10
    static Integer OOPS_MAXIMUM_WAIT = 10
    static Integer DEFAULT_SLEEP_TIME = 0
    static String DEFAULT_OOPS_WAIT = System.getProperty("Oops_Wait")
    static String TAB = Keys.TAB
    static String ENTER = Keys.ENTER
    static String ESCAPE = Keys.ESCAPE

    static final By OOPS_ID = By.id("error_wrapper")
    static final By OOPS_CLASS = By.className("oopsError_list");
    static final By MAINTENANCE_OOPS_ID = By.id("maintenance_wrapper")
    static final By CURRENT_STEP = By.xpath("//td[@class='current_step']")
    static final By BREADCRUMB_CONTAINER = By.id("breadcrumbContainer")
    static final By APPLIED_TRAVEL_FUNDS_WRAPPER = By.className("appliedTravelFundsWrapper")
    static final By AIR_COLLAPSE_BUTTON = By.id("expand_collapse_widget_inside_price_air_widget_content")
    static final By BREADCRUMB = By.id("sw_breadcrumb")
    static final String STOP_LOGIC_TEXT = "Stops:"
    static final String CHANGE_TEXT = "Change"
    static final By COPYRIGHT_YEAR = By.cssSelector(".footer_copyright_year")
    private static final int TIME_TO_WAIT_FOR_PAGE_LOAD = 45

    BasePage(WebDriverProvider driverProvider) {
        super(driverProvider)
        GrooBe.activate()
        PageFactory.initElements(new AjaxElementLocatorFactory(driverProvider.get(), 20), this)
        LOCAL_SCREENSHOTS = LOCAL_SCREENSHOTS && driverProvider.get() instanceof SWARemoteWebDriver
    }

    def methodMissing(String name, args) {
        if (name.startsWith("getFlow")) {
            return new Flow()       /* TODO rework flow architecture to eliminate the need for this - many derived pages do not have flow */
        }
        fail "methodMissing: ${name}(${args})"
    }

    protected String pagePath
    protected String domain

    BasePage(WebDriverProvider driverProvider, String pagePath, String domain = Domains.dotcomDomain) {
        this(driverProvider)
        this.pagePath = pagePath
        this.domain = domain
    }

    WebDriver webDriver() {
        return super.getDriverProvider().get()
    }

    def open() {
        String urlToOpen = this.domain.concat(this.pagePath)
        System.out.println(SWARemoteWebDriver.screenThreadLabel() + " Opening page: " + urlToOpen
                + " for : " + SWAContextView.retrievePageContext().currentStory + " " + SWAContextView.retrievePageContext().currentStep)

        get(urlToOpen)
    }

    void openInAnotherWindow(String url) {
        String javaScript = "window.open('$url','windowNewName')"
        executeScript(javaScript)
    }

    void comeBackToBeforeWindow(Object windowBefore) {
        switchToOpenWindow(webDriver().getWindowHandle(), windowBefore)
    }

    void openUrl(String myUrl) {
        get(myUrl)
    }

    void openWithArgs(String[] args, path = pagePath) {
        StringBuilder url = new StringBuilder()
        url.append("$domain$path")
        if ((args != null) && (args.length > 0)) {
            url.append("?")
            for (int i = 0; i < args.length; i++) {
                url.append(args[i])
                if (i + 1 < args.length) url.append("&")
            }
        }
        System.out.println(SWARemoteWebDriver.screenThreadLabel() + "Opening page: " + url.toString())
        get(url.toString())
    }

    String getRelativePath() {return "/"  } /*derived pages should return their path */

    void verifyCurrentPageLocation() {
        URLDecoder.decode(getCurrentUrl())?.shouldContain getRelativePath(), "Path verification for '" + getRelativePath() + "' did not match the current page that is open."
    }

    void clickOnOneOfThese(By... someBys) {
        Boolean clicked = someBys.any { byElement ->
            WebElement button = waitForElement(byElement, false)
            if (button && button.isEnabled() && button.isDisplayed()) {
                button.click()
                return true
            } else {
                return false
            }
        }

        clicked.shouldBe true, "None of the locators were present to click on"
    }

    def verifyPage() {
        checkSomethingServed()
        if(!getFlow().isFaultInjected) {
            checkNoOops()
        }
        verifyJavaScriptDone()
    }

    void verifyPageBreadcrumb(String pageName) {
        getBreadCrumb(pageName).getAttribute("class").shouldContain "current_step"
    }

    void clickPageBreadcrumb(String pageName) {
        WebElement element = waitForElement(By.xpath("//a[@title='$pageName']"))
        element.click()
    }

    WebElement getBreadCrumb(String pageName) {
        return waitForElement(BREADCRUMB_CONTAINER).findElement(By.id(pageName))
    }

    void shouldShowOopsMessage(String message, By errorSelector = OOPS_ID) {
        String expectedOopsMessage = getExpectedOopsMessage(errorSelector)
        expectedOopsMessage.shouldContain message, "The Oops message '$message' has not been found within the text '$expectedOopsMessage'"
    }

    void shouldShowOopsMaintenanceMessage(String message, By errorSelector = OOPS_ID) {
        String expectedOopsMessage = getExpectedOopsMaintenanceMessage()
        expectedOopsMessage.shouldContain message, "The Oops message '$message' has not been found within the text '$expectedOopsMessage'"
    }

    void shouldNotShowOopsMessage(String message) {
        getExpectedOopsMessage().shouldNotContain message, "The Oops message '$message' should not be found within the text" + getExpectedOopsMessage() + "and it was."
    }

    void shouldNotShowOopsMessages(List<String> oopsMessage) {
        for (msg in oopsMessage) {
            shouldNotShowOopsMessage(msg)
        }
    }

    void verifyOopsMessages(List<String> oopsMessage) {
        String expectedOopsMessages = getExpectedOopsMessage()
        for (msg in oopsMessage) {
            expectedOopsMessages.shouldContain msg, "The Oops message '$msg' has not been found within the text" + expectedOopsMessages
        }
    }

    void shouldBeInPage(String page) {
        getCurrentUrl().shouldContain page
    }

    void checkNoOops() {
        WebElement oopsElement = getOopsElement()
        if (oopsElement != null && oopsElement.isDisplayed()) {
            if (oopsElement.getText().indexOf("We are unable to secure the price for the flight you selected.") > -1) {
                return
            }
            if (DynaStubsIntegration.useDynaStubs()) {
                if (oopsElement.getText().indexOf("We Were Unable to Confirm the Reservation(s) for the Senior Passengers.") > -1) {
                    return
                }
            }
            fail "An oops message was found: ${oopsElement.getText()}"
        }
    }

    boolean noOopsMessageFound() {
        try {
            return findElement(OOPS_ID) == null;
        } catch (WebDriverException e) {
            return true
        }
    }

    WebElement getOopsElement() {
        return waitForElement(OOPS_ID, false, DEFAULT_WAIT_LIMIT_FOR_ELEMENT_THAT_MAY_EXIST)
    }

    String getExpectedOopsMessage(By errorSelector = OOPS_ID) {
        WebElement oopsElement = waitForElement(errorSelector, false)
        if (oopsElement == null){
            oopsElement = waitForElement(OOPS_CLASS,false)
        }
        oopsElement.shouldNotBe null, "Oops element was not found"
        oopsElement.getText().shouldNotBe null, "Oops element text was nuKeysll"
        return oopsElement.getText()
    }

    String getExpectedOopsMaintenanceMessage(By errorSelector = MAINTENANCE_OOPS_ID) {
        WebElement oopsElement = waitForElement(errorSelector, false, 10)
        oopsElement.shouldNotBe null, "Oops element was not found"
        oopsElement.getText().shouldNotBe null, "Oops element text was nuKeysll"
        return oopsElement.getText()
    }

    void checkSomethingServed() {
        try {
            waitForElement(By.tagName("body"), true, 60)
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " waitForElement(By.tagName(\"body\"))")
            waitForElement(By.tagName("body"), true, 30)
        }
        WebElement titleTag = null;
        String titleText = null;
        try {
            titleTag = waitForElement(By.tagName("title"))
            titleText = titleTag.text
        } catch (StaleElementReferenceException e) {
            titleTag = waitForElement(By.tagName("title"))
            titleText = titleTag.text
        }
//        switchToWindow(titleText,45) // selenium code actually also does a switch to try recovering from docElement null
        UserPageContext pageContext = SWAContextView.retrievePageContext()
        pageContext.currentTitle = titleText
        SWAContextView.replacePageContext(pageContext)
        capturePageSource()
        takeScreenShot()
    }

    void capturePageSource() {
        if (!PAGE_SOURCE_CAPTURE) return
        String page
        try {
            page = getPageSource()
        } catch (WebDriverException e) {
            println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " getPageSource()")
            page = getPageSource()
        }
        SWARemoteWebDriver.savePageSource(page)
    }

    void shownInPage(String toBeShown) {
        String page = getPageSource()
        if (!page.contains(toBeShown)) {
            fail("'$toBeShown' not show in page")
        }
    }

    static void takeScreenShot() {
        if (LOCAL_SCREENSHOTS) {
            try {
                SWARemoteWebDriver.snapScreen()
            } catch (WebDriverException e) {
                System.out.println("Continuing with story but screen shot failed... " + e.getMessage())

                StringBuilder messageBuilder = new StringBuilder()
                messageBuilder.append(SWARemoteWebDriver.createMessageLabel())
                messageBuilder.append(" Screenshot capture failed ")
                messageBuilder.append(e.getMessage())

                SWARemoteWebDriver swaRemoteWebDriver = ((SWARemoteWebDriver) (SWAContextView.retrievePageContext().webDriverProvider.get()))
                swaRemoteWebDriver.obtainStoryCommandLogger().writeLogMessage(messageBuilder.toString())
            }
        }
    }

    void notShownInPage(String toBeShown) {
        String page = getPageSource()
        if (page.contains(toBeShown)) {
            fail("'$toBeShown' not show in page")
        }
    }

    void selectFromDropDownByIndex(By element, int index) {
        WebElement dropDown = waitForElement(element)
        Select select = new Select(dropDown)
        try {
            select.selectByIndex(index)
        } catch (WebDriverException) {
            println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " selectByIndex(" + index + ")")
            select.selectByIndex(index)
        }
    }

    protected chooseInDropDownByValue(String elementId, String selection) {
        WebElement element = waitForElement(By.id(elementId))
        Select select = new Select(element)
        try {
            select.selectByValue(selection)
        } catch (WebDriverException) {
            println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " selectByValue(" + selection + ")")
            select.selectByValue(selection)
        }
    }

    protected chooseInDropDownByValue(By element, String selection) {
        WebElement dropdown = waitForElement(element)
        Select select = new Select(dropdown)
        try {
            select.selectByValue(selection)
        } catch (WebDriverException) {
            println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + "selectByValue(" + selection + ")")
            select.selectByValue(selection)
        }
    }

    protected chooseInDropDownByText(String elementId, String selection) {
        WebElement element = waitForElement(By.id(elementId))
        (new Select(element)).selectByVisibleText(selection)
    }

    void selectFromDropDownRandom(By element, String exceptionValue = null) {
        WebElement dropDown = waitForElement(element)
        List<WebElement> elements = dropDown.findElements(By.tagName("option"))

        if (exceptionValue != null) {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getAttribute("value") == exceptionValue) {
                    elements.remove(i)
                    break
                }
            }
        }

        Random random = new Random()
        int randomInt = random.nextInt(elements.size())
        chooseInDropDownByValue(element, elements.get(randomInt).getAttribute("value"))
    }

    void fillIn(By byElement, String userInput, boolean doTab = true) {
        if (userInput?.size() != 0) {
            String inputResult = fillInWithoutVerification(byElement, userInput, doTab)
            String failString = "Webdriver sendKeys FAILED!!!  inputResult= '$inputResult'  userInput = '$userInput'"
            inputResult.shouldNotBe null, failString
            inputResult.size().shouldBeGreaterThan 0, failString
            inputResult.shouldContain userInput, failString
        }
    }

    String fillInWithoutVerification(By byElement, String userInput, boolean doTab = true, int sleepTime = DEFAULT_SLEEP_TIME) {
        WebElement inputElement = waitForElement(byElement)
        inputElement.sendKeys(DELETE_EXISTING + userInput)
        if (sleepTime > 0) {
            Thread.sleep(sleepTime)
        }
        if (doTab) {
            inputElement.sendKeys(Keys.TAB) //tab selects the autocomplete list item
        }
        return inputElement.getAttribute("value")
    }

    void attemptFillIn(By byElement, String userInput, boolean doTab = true) {
        if (userInput?.size() != 0) {
            WebElement inputElement = waitForElement(byElement)
            inputElement.sendKeys(DELETE_EXISTING + userInput)
            if (doTab) {
                inputElement.sendKeys(Keys.TAB) //tab selects the autocomplete list item
            }
        }
    }

    void verifyInField(String fieldId, String expectedValue) {
        waitForElement(By.id(fieldId)).getAttribute("value").shouldContain expectedValue, "value in field did not match the expected value"
    }

    Calendar formatDate(String inDate, String datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat()
        Calendar newCal = formatDate(sdf, inDate, datePattern)
        return newCal
    }

    Calendar formatDate(SimpleDateFormat sdf, String inDate, String datePattern) {
        sdf.applyPattern(datePattern)
        Calendar newCal = Calendar.getInstance()
        Date processDate = sdf.parse(inDate)
        newCal.setTime(processDate)
        return newCal
    }

    String getmmddyyyyDateFormat(Calendar calendar) {
        String lookupMM = (calendar.cdate.month).toString() + "/"
        String lookupDD = calendar.get(Calendar.DAY_OF_MONTH).toString() + "/"
        String lookupYYYY = calendar.get(Calendar.YEAR).toString()
        return (lookupMM + lookupDD + lookupYYYY)
    }

    void verifyTextPresent(String textToFind, By elementToFind) {
        String textToSearch = findElement(elementToFind).getText()
        if (!textToSearch.contains(textToFind)) {
            fail "Text '${textToFind}' not present in searched string '${textToSearch}'"
        }
    }

    void selectLinkInParent(String parentId, String childTag, String linkText) {
        //Since there is no id for the link that we need to click on we are going to the parent and looping through the available links and retrieving it by getting the text.
        WebElement li = waitForElement(By.id(parentId))
        List<org.openqa.selenium.WebElement> symbolz = li.findElements(By.tagName(childTag))
        for (symbol in symbolz) {
            if (symbol.getText().equals(linkText)) {
                WebElement element = symbol;
                if (element != null) {
                    element.click();
                    break;
                }
            }
        }
    }

    void pressEscapeInField(By field) {
        waitForElement(field).sendKeys(Keys.ESCAPE)
    }

    void pressTabInField(By field) {
        waitForElement(field).sendKeys(Keys.TAB)
    }

    boolean isElementPresent(By by, int timeout = DEFAULT_WAIT_LIMIT) {
        WebElement wElem = waitForElement(by, false, timeout)
        return !wElem.equals(null) && wElem.isDisplayed()
    }

    boolean isElementPresentOnEarlyBirdPurchase(By by) {
        return !waitForElement(by, false, 30, 500).equals(null) &&
                waitForElement(by, false, 30, 500).isDisplayed()
    }

    boolean isElementWithTextPresent(By by, String text) {
        return isElementPresent(by) &&
                findElements(by).any { it.text.contains(text).booleanValue() }
    }

    boolean isElementDisplayed(By by) {
        WebElement element = waitForElement(by, false)
        return (element != null && element.isDisplayed())
    }

    void verifyElementPresent(String elementName, By element) {
        waitForElement(element, false, DEFAULT_WAIT_LIMIT, DEFAULT_WAIT_RETRY, elementName).shouldNotBe null, elementName + " was not found."
    }

    void verifyElementPresent(String elementName, WebElement element) {
        element?.isEnabled().shouldBe true, "${elementName} is not enabled on the page"
        element.shouldNotBe null, "${elementName} is not on the page"
    }

    void verifyElementNotPresent(String elementName, By element) {
        waitForElement(element, false, 0, DEFAULT_WAIT_RETRY, elementName).shouldBe null, elementName + " was  found."
    }

    void verifyIfElementIsEnabled(By element, String elementText, boolean isEnable = true) {
        String expectedValue = "enabled"
        String actualValue = "disabled"
        if (!isEnable) {
            expectedValue = "disabled"
            actualValue = "enabled"
        }
        waitForElement(element).isEnabled().shouldBe isEnable, "Element '" + elementText + "' is " + actualValue + " and should be " + expectedValue
    }

    WebElement findLinkInSection(WebElement section, String linkTitle) {
        List<WebElement> anchors = section.findElements(org.openqa.selenium.By.tagName("a"))
        WebElement matchingAnchor = null

        if (!anchors.isEmpty()) {
            matchingAnchor = anchors.find { it.getText().trim().equalsIgnoreCase(linkTitle) }
        }

        matchingAnchor.shouldNotBe null, "anchor ${linkTitle} not found"

        return matchingAnchor;
    }

    void waitForPageTitleToChangeOrOops(String previousPageTitle, long waitTimeInMS = 100, long maxWaitTimeInMS = 100000) {
        long start = System.currentTimeMillis()
        while (getTitle().equals(previousPageTitle) && noOopsMessageFound()) {
            sleep waitTimeInMS
            if (System.currentTimeMillis() - start > maxWaitTimeInMS) {
                fail "Page Title did not change pages within 100 seconds, is still: '$previousPageTitle'"
            }
        }
    }

    void waitForUrlToChangeOrOops(String previousUrl, long waitTimeInMS = 100, long maxWaitTimeInMS = 100000) {
        long start = System.currentTimeMillis()
        while (getCurrentUrl().equals(previousUrl) && getOopsElement() == null) {
            sleep waitTimeInMS
            if (System.currentTimeMillis() - start > maxWaitTimeInMS) {
                fail "Page Title did not change pages within 100 seconds, is still: '$previousUrl'"
            }
        }

    }

    void waitForOops() {
        long start = System.currentTimeMillis()
        while (getOopsElement() == null) {
            sleep 100
            if (System.currentTimeMillis() - start > 100000) {
                fail "Oops Message did not display within 100 seconds'"
            }
        }
    }

    boolean shouldTravelFundApplied() {
        boolean travelFundApplied = false
        if (waitForElement(APPLIED_TRAVEL_FUNDS_WRAPPER, false, 2)) {
            String tFund = waitForElement(By.id("applyTravelFundsTotalAddonsCost"), false)?.getText()
            tFund = tFund?.find(/\d+.\d\d/)
            travelFundApplied = tFund.equals("0.00")
        }

        return travelFundApplied
    }

    boolean verifyPassengerCount(int passengerCount) {
        return (0..8).contains(passengerCount)
    }

    void ensureCheckboxIsChecked(By by) {
        WebElement element = waitForElement(by)
        String checkboxChecked = element.getAttribute("checked")
        if (!"true".equalsIgnoreCase(checkboxChecked)) {
            element.click()
        }
    }

    boolean isCheckboxChecked(By by) {
        WebElement element = waitForElement(by)
        String checkboxChecked = element.getAttribute("checked")
        return "true".equalsIgnoreCase(checkboxChecked)
    }

    boolean isOptionInDropDown(String elementId, String selection) {
        WebElement element = waitForElement(By.id(elementId))
        Select sel = new Select(element)
        List<WebElement> options = sel.getOptions() ?: []
        for (o in options) {
            WebElement option = new WebElementWrapper(o)
            if (option.getText().equals(selection)) {
                return true
            }
        }
        return false
    }

    boolean isOptionInDropDownSelected(By containerId = null, String elementId, String selection) {
        WebElement element
        if (containerId != null) {
            element = waitForElement(containerId).findElement(By.id(elementId))
        } else {
            element = waitForElement(By.id(elementId))
        }
        Select sel = new Select(element)
        List<WebElement> options = sel.getOptions() ?: []
        for (o in options) {
            WebElement option = new WebElementWrapper(o)
            if (option.getText().equals(selection) && option.isSelected()) {
                return true
            }
        }
        return false
    }

    boolean isOptionInDropDownSelectedByValue(String elementId, String selection, By containerId = null) {
        WebElement element
        if (containerId != null) {
            element = waitForElement(containerId).findElement(By.id(elementId))
        } else {
            element = waitForElement(By.id(elementId))
        }
        Select sel = new Select(element)
        List<WebElement> options = sel.allSelectedOptions
        for (o in options) {
            WebElement option = new WebElementWrapper(o)
            if (option.getAttribute("value").equalsIgnoreCase(selection)) {
                return true
            }
        }
        return false
    }

    boolean isOptionInDropDownSelectedByValue(By byElement, String selection) {
        WebElement element = waitForElement(byElement)
        Select sel = new Select(element)
        List<WebElement> options = sel.allSelectedOptions
        for (o in options) {
            WebElement option = new WebElementWrapper(o)
            if (option.getAttribute("value").equalsIgnoreCase(selection)) {
                return true
            }
        }
        return false
    }

    boolean checkIfElementExists(By by) {
        return waitForElement(by, false, DEFAULT_WAIT_LIMIT_FOR_ELEMENT_THAT_MAY_EXIST) != null
    }

    WebElement waitForElementWithText(By byElement, String textToSearchFor) {
        waitForElementWithText(byElement, true, DEFAULT_WAIT_LIMIT, DEFAULT_WAIT_RETRY, null, textToSearchFor);
    }

    WebElement waitForElementDisplayed(By byElement, boolean throwExceptions = true, int waitLimit = DEFAULT_WAIT_LIMIT, int retryInterval = DEFAULT_WAIT_RETRY, String elementName = null) {
        return waitForElement(byElement, throwExceptions, waitLimit, retryInterval, elementName, true)
    }

    WebElement waitForElement(By byElement, boolean throwExceptions = true, int waitLimit = DEFAULT_WAIT_LIMIT, int retryInterval = DEFAULT_WAIT_RETRY, String elementName = null, boolean ensureDisplayed = false) {
        List<WebElement> resultElement = new ArrayList<WebElement>()
        int timeout = waitLimit
        int retry = retryInterval
        long start = System.currentTimeMillis()
        WebDriverWait wdw = new WebDriverWait(this.getDriverProvider().get(), timeout, retry)
        try {
            wdw.until(new Predicate<WebDriver>() {
                boolean apply(WebDriver wd) {
                    List<WebElement> elements
                    try {
                        elements = wd.findElements(byElement)
                    } catch (WebDriverException w) {
                        println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " findElements(" + byElement.toString() + ")")
                        elements = wd.findElements(byElement)
                    }
                    if (elements.size() > 0 && (!ensureDisplayed || elements.get(0).isDisplayed())) {
                        resultElement.add(0, elements.get(0))
                    }
                    return resultElement.size() > 0
                }
            })
        } catch (InterruptedException ie) {
            if (throwExceptions) {
                throw new WebDriverException(ie.getMessage() + " Interruption - Element '" + byElement + "' not found in " + timeout + " seconds")
            }
        } catch (WebDriverException wde) {
            if (throwExceptions) {
                throw new WebDriverException(wde.getMessage() + " JBehave - Element '" + byElement + "' failed lookup through Selenium.")
            }
        } catch (TimeoutException te) {
            if (throwExceptions) {
                PAGE_SOURCE_CAPTURE=true
                capturePageSource()
                PAGE_SOURCE_CAPTURE=false
                throw new WebDriverException("Timeout - Element '" + byElement + "' not found in configured " + timeout + " seconds, actual: " + (System.currentTimeMillis() - start) + "millis." )
            }
        }
        return resultElement[0] != null ? (WebElement) (new WebElementWrapper(resultElement[0])) : resultElement[0]
    }

    List<WebElement> waitForElements(By byElement, boolean handleFail = true, Double waitLimit = DEFAULT_WAIT_LIMIT, int retryInterval = DEFAULT_WAIT_RETRY, String elementName = null) {
        List<WebElement> resultElement = new ArrayList<WebElement>()
        int timeout = (int) waitLimit
        int retry = (int) retryInterval
        WebDriverWait wdw = new WebDriverWait(this.getDriverProvider().get(), timeout, retry)
        try {
            wdw.until(new Predicate<WebDriver>() {
                boolean apply(WebDriver wd) {
                    List<WebElement> elements = wd.findElements(byElement)
                    if (elements.size() > 0) {
                        resultElement = elements
                        return true
                    } else return false
                }
            })
        } catch (InterruptedException ie) {
            if (handleFail) {
                throw new WebDriverException(ie.getMessage() + " Interruption - Element '" + byElement + "' not found in " + timeout + " seconds")
            }
        } catch (TimeoutException te) {
            if (handleFail) {
                PAGE_SOURCE_CAPTURE=true
                capturePageSource()
                PAGE_SOURCE_CAPTURE=false
                throw new WebDriverException("Timeout - Element '" + byElement + "' not found in " + timeout + " seconds")
            }
        }
        List<WebElement> wrappedElements = new ArrayList<WebElement>();
        resultElement.each { element ->
            wrappedElements.add(new WebElementWrapper(element))
        }
        return wrappedElements
    }

    void setValue(elementId, value) {
        WebElement element = waitForElement(By.id(elementId))
        if (element.tagName.toLowerCase() == "select") {
            new Select(element).selectByValue(value)
        } else {
            element.clear()
            element.sendKeys(value)
        }
    }

    void switchToOpenWindow(String winHandleBefore, String title) {

        if (webDriver().getWindowHandles().empty) {
            fail "There is a problem with the page: '" + title + "'"
        }
        //Switch to new window opened
        for (String winHandle : webDriver().getWindowHandles()) {

            if (!winHandleBefore.equals(winHandle)) {
                webDriver().switchTo().window(winHandle)
            }

        }
    }

    void switchToWindow(String title, long timeOut = 0) {
        boolean isWindowOpen = false
        long start = System.currentTimeMillis()
        while (!isWindowOpen) {
            isWindowOpen = windowsSwitching(title)
            if (System.currentTimeMillis() - start > timeOut && !isWindowOpen) {
                throw new AssertionFailedError("New window '${title}' not found within ${timeOut} milliseconds'");
            }
            sleep(500)
        }
    }

    boolean windowsSwitching(String title) {
        boolean result = false
        WebDriver webDriver = this.getDriverProvider().get()
        Set<String> windowHandles = getWindowHandles()
        if (windowHandles.empty) {
            fail("####### No Windows Found ########")
        }
        if (windowHandles.size() > 1) {
            result = switchToWindowWithTitle(windowHandles, webDriver, title)
        } else {
            webDriver.switchTo().window(windowHandles.iterator().next())
            result = true
        }
        return result
    }

    boolean switchToWindowWithTitle(Set<String> windowHandles, WebDriver webDriver, String title) {
        boolean result = false
        for (handle in windowHandles) {
            try {
                if (webDriver.switchTo().window(handle).getTitle().contains(title)) {
                    result = true
                }
            } catch (WebDriverException e) {
                println(SWARemoteWebDriver.screenThreadLabel() + " #################### RETRYING ####################" + " switchToWindow()")
                if (webDriver.switchTo().window(handle).getTitle().contains(title)) {
                    result = true
                }
            }
            if (result == true) break
        }
        return result
    }

    void waitForSelectedBreadcrumbs(String expected) {
        waitForElement(CURRENT_STEP).getText().shouldBe expected, "breadcrumbs' current step is wrong, should have been " + expected
    }

    protected String extractFlightNumberFromDivElement(WebElement flight) {
        WebElement airCollapseButton = waitForElement(AIR_COLLAPSE_BUTTON)
        boolean shouldCollapse
        if ("expand".equals(airCollapseButton.getAttribute("title"))) {
            airCollapseButton.click()
            shouldCollapse = true
        }

        List<WebElement> flightDivs = flight.findElements(By.tagName("div"))
        if (flightDivs.isEmpty()) {
            flightDivs = flight.findElements(By.tagName("strong"))
        }
        String number = null
        for (WebElement flightDiv in flightDivs) {
            if (flightDiv.getText().contains("#")) {
                int index = flightDiv.getText().indexOf("#");
                number = flightDiv.getText().substring(index).trim()
            }
        }

        if (shouldCollapse) {
            airCollapseButton.click()
        }

        return number
    }

    boolean waitFor(Double timeoutSecs = DEFAULT_WAIT_LIMIT, Double intervalSecs = 0.1, handleFail = false, Closure condition) {
        double loops = Math.ceil(timeoutSecs / intervalSecs)
        boolean pass = false
        int loopIndex = 0
        String exceptionMessage

        while (!pass && loopIndex++ < loops) {
            Thread.sleep((intervalSecs * 1000) as long)
            try {   //just catch do not need to throw inside loop    we are catching  out side the loop
                pass = condition.call()
                pass = (pass != null && pass != false)
            } catch (Exception e) {
                exceptionMessage = e.getMessage()
            }
            finally {
                exceptionMessage = "   Unknown exception "
            }
        }
        if (loopIndex >= loops) {
            if (handleFail) {

                throw new AssertionError("======= waitFor Condition did not pass in $timeoutSecs seconds  ${exceptionMessage} ======")
            }
        }
        return pass
    }

    boolean isJavaScriptsDoneLoading() {
        JavascriptExecutor executor = (JavascriptExecutor) this.getDriverProvider().get()
        return executor.executeScript("return (typeof window.CJS === 'undefined' || window.CJS.done === true)")

    }

    void clickBrowserBackButton() {
        this.driverProvider.get().navigate().back()
    }

    void switchAndAcceptAlert() {
        Alert alert = getAlert()
        if (alert != null) {
            alert.accept()
        }
    }

    Alert getAlert() {
        try {
            return driverProvider.get().switchTo().alert()
        } catch (NoAlertPresentException ex) {
            return null;
        }
    }

    void clickBrowserFreshButton() {
        webDriver().navigate().refresh()
        webDriver().switchTo().alert().accept()
    }

    void verifyPageTitle(String titleExpected) {
        verifyPage()
        title.shouldBe titleExpected, "The title of the current page doesn't match with the expected one"
    }

    void validateDatesFormat(String flightDates, String pattern, String productType) {
        flightDates.trim().matches(pattern).shouldBe true, "Date $flightDates for $productType doesn't match the expected pattern: $pattern"
    }

    void verifyBreadcrumb(String[] breadcrumb) {
        WebElement parent = waitForElement(BREADCRUMB)
        for (int i = 0; i < breadcrumb.size(); i++) {
            String title = breadcrumb[i].trim().replace("'", "")
            if (i == breadcrumb.size() - 1) {
                parent.findElement(By.cssSelector("span:not([class])")).text.shouldBeEqual title,
                        "Not found breadcrumb " + title
            } else {
                parent.findElement(By.xpath("//a[@title ='" + title + "']")).shouldNotBe null,
                        "Not found breadcrumb " + title
            }
        }
    }

    void verifyBreadcrumbNoLink(String[] breadcrumb) {
        WebElement parent = waitForElement(BREADCRUMB)
        List childs = parent.findElements(By.tagName("a"))
        childs.addAll(parent.findElements(By.tagName("span")))
        for (int i = 0; i < breadcrumb.size(); i++) {
            String title = breadcrumb[i].trim().replace("'", "")
            childs.get(i).getText().shouldBeEqual title,
                    "Not found breadcrumb " + title
        }
    }

    void clearTextById(String id) {
        WebElement toClear = waitForElement(By.id(id))
        clearText(toClear)
    }

    void clearText(WebElement element) {
        element.sendKeys(DELETE_EXISTING)
    }

    void performClickAction(By by, int retry = DEFAULT_RETRY) {
        for (int i = 0; i < retry; i++) {
            try {
                waitForElement(by).click()
            } catch (StaleElementReferenceException ex) {
                println(SWARemoteWebDriver.screenThreadLabel() + " ########### NOT FOUND IN CACHE - RETRYING ############ " + by.toString())
                continue
            }
            break
        }
    }


    void fillInWithAutocomplete(By byElement, String userInput, Integer numOfChars, boolean doTab = true, Double waitLimit = DEFAULT_WAIT_LIMIT, int retryInterval = DEFAULT_WAIT_RETRY, int sleepTime = DEFAULT_SLEEP_TIME) {
        int timeout = (int) waitLimit
        int retry = (int) retryInterval
        long start = System.currentTimeMillis()
        WebDriverWait wdw = new WebDriverWait(this.getDriverProvider().get(), timeout, retry)
        if (userInput?.size() != 0) {
            try {
                wdw.until(new Predicate<WebDriver>() {
                    boolean apply(WebDriver wd) {
                        String inputResult = fillInWithoutVerification(byElement, userInput, doTab, sleepTime)
                        return inputResult.size() > numOfChars
                    }
                })
            } catch (TimeoutException te) {
                throw new WebDriverException("Timeout - Autocomplete failed to initialize in " + timeout + " seconds, actual: " + (System.currentTimeMillis() - start) + "millis.")
            }
        }
    }

    void verifyStopLogicInfo(By stopTextContainer, By stopsCitiesListContainer, String[] stopsCities) {
        verifyTextPresent(STOP_LOGIC_TEXT, stopTextContainer)
        for (String city : stopsCities) {
            verifyTextPresent(Station.valueOf(city).getCityName(), stopsCitiesListContainer)
        }
    }

    void verifyChangePlaneInfo(By changePlaneCityContainer, String changePlaneCity) {
        verifyTextPresent(CHANGE_TEXT, changePlaneCityContainer)
        verifyTextPresent(Station.valueOf(changePlaneCity).getCityName(), changePlaneCityContainer)
    }

    void doubleClick(By by) {
        Actions action = new Actions(webDriver())
        action.doubleClick(webDriver().findElement(by)).perform()
    }

    void verifyCopyrightYear() {
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR))
        waitForElement(COPYRIGHT_YEAR).getText().shouldContain year, "Copyright year is not correct"
    }

    String getCookieWithJavascript(String cookieName) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver();
        String[] cookies = js.executeScript("return document.cookie.split(\";\")")
        cookies.each {
            if (it.contains(cookieName)) {
                return it
            }
        }
        return null
    }

    Cookie getCookie(String cookieName) {
        return webDriver().manage().getCookieNamed(cookieName)
    }


    void verifyJavaScriptVariables( Map variables,String message) {
        int wait = 0;
        while(!isJavaScriptsDoneLoading()){
            sleep(100)
            if(wait>=10)
                break;
            wait++;
        }
        StringBuilder javaScript = new StringBuilder().append("return (")
        int i = 0;
        variables.each {key , value->
            javaScript.append("$key == $value")
            if(i != variables.size()-1)
                javaScript.append(" && ")
            i++
        }
        javaScript.append(")")
        boolean  areSet = executeScript(javaScript.toString())
        areSet.shouldBe true,message
    }

    void verifyJavaScriptDone(int retries = 20) {
        int wait = 0;
        while (!isJavaScriptsDoneLoading()) {
            sleep(100)
            if (wait >= retries)
                break;
            wait++;
        }
    }

    String pageVerificationErrorWrapper(By by, String pageName) {
        waitForElement(by, false, TIME_TO_WAIT_FOR_PAGE_LOAD).shouldNotBe null, "$pageName page did not load in ${TIME_TO_WAIT_FOR_PAGE_LOAD}"
    }

    private By createLinkElement(String link) {
        return By.xpath(".//a[@href='" + link + "']")
    }

    public void clearValue(WebElement element) {
        executeScript("arguments[0].value = '';",element)
    }

    public  Actions getActions() {
        return  new  Actions(webDriver());
    }

}
