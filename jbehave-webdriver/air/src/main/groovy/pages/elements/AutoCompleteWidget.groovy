package pages.elements

import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.BasePage
import static org.junit.Assert.fail
import static util.Locators.searchWidgetData

class AutoCompleteWidget extends BasePage {

    private static final By ORIGIN_AIRPORT = By.id("originAirport_displayed")
    private static final By MODIFY_SEARCH_SUBMIT_BUTTON = By.id("modifySearchSubmitButton")
    private static final By SEARCH_WIDGET = By.id("carhotel_air_modify_search_widget_addl_opts")
    private static final By AUTOCOMPLETE_LIST_PATH = By.xpath("//div[@class='ac_results' and contains(@style,'display: block')]")
    private static final By AUTOCOMPLETE_LIST_RESULTS = By.cssSelector(".ac_results ul li")
    private static final By MODIFY_SEARCH_TITLE_TEXT = By.id("modifySearchTitleText")
    private static final String AC_RESULTS_CLASSNAME = "ac_results"
    private static final String NORMAL_CLASSNAME = "normal"

    public AutoCompleteWidget(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    void fillInAutoCompleteData(String field, String userInput, boolean doTab = false) {
        fillIn(By.id(field), userInput, doTab)
    }

    void fillInAndSelectAutoCompleteData(String field, String userInput) {
        fillInWithoutVerification(By.id(field), userInput,true)
    }

     def verifyAutoCompleteDropDownNoPresent() {
            if (waitForElement(By.className('ac_results'),false) !=null) {
                fail("Autocomplete widget is present")
            }
    }

    def verifyAutoCompleteCityFullName(String station_info) {
        waitForElement(ORIGIN_AIRPORT).getAttribute("value").shouldContain station_info, "Station full name was not found in the autocomplete widget"
    }

    def verifyIfContentExistsInAutoCompleteList(String content) {
        def emailList = waitForElement(By.className(AC_RESULTS_CLASSNAME)).findElements(By.className(NORMAL_CLASSNAME))
        emailList.each {
            it.getText().shouldContain content, "Content not present in the autocomplete widget"
        }
    }

    def verifyAutoCompleteWidgetContent(String station_info) {
        fail "Do not try to verify the contents of the autocomplete list with JBehave! See Voltron!"
    }

    def verifyAutoCompleteListStartsWith(String station) {
        fail "Do not try to verify the contents of the autocomplete list with JBehave! See Voltron!"
    }

    def selectFromList(String station) {
        List<WebElement> autoCompleteResults = waitForElements(By.className(AC_RESULTS_CLASSNAME))
        WebElement autoCompleteElement = autoCompleteResults.find { element ->
            element.getCssValue("display") == "block"
        }

        WebElement selectedElement = autoCompleteElement.findElement(By.tagName("strong"))
        if(selectedElement.getAttribute("innerHTML").toLowerCase().contains(station.toLowerCase())){
            selectedElement.click()
        }else{
            fail("input element contains questionable contents: " + selectedElement.getAttribute("innerHTML"))
        }
    }

    public String selectFromListRandom() {
        List<WebElement> listCities = waitForElements(AUTOCOMPLETE_LIST_RESULTS)
        Random random = new Random()
        int randomInt = random.nextInt(listCities.size() - 1)
        String textCity = listCities[randomInt].getText().trim()
        listCities[randomInt].click()
        return textCity
    }

    boolean airTranRedirectVerify() {
        def element = waitForElement(By.id("airTranContinueButton"))
        if (element.text.equals("Continue")) {
            return true
        }
        return false

    }

    void checkAirTranRedirectVerify() {
        airTranRedirectVerify().shouldBe true, "No Redirect Modal found"
    }

    def airTranModalContinueButton() {
        def el = waitForElement(By.xpath("//input[@id = 'airTranContinueButton']"))
        el.click()
        waitForElement(By.xpath("airTranRedirectModalCancel")).click()
        def fl = waitForElement(By.xpath("//input[@id = 'airTranContinueButton']"))
        fl.click()
    }

    def expandSearchWidget(String button) {
        waitForElement(By.xpath(searchWidgetData.get(button))).click()
    }

    def collapseSearchWidget(){
        waitForElement(MODIFY_SEARCH_TITLE_TEXT).click()
    }

    def verifySearchWidgetExpands() {
        def verify = waitForElement(MODIFY_SEARCH_SUBMIT_BUTTON)
        def text = verify.getAttribute("title")
        text.equals("Search").shouldBe true,"-----Search button not found-----"
    }

    def verifySearchWidgetCollapsed() {
        def verify = waitForElement(SEARCH_WIDGET)
        def textverify = verify.getAttribute("style")
        textverify.equals("display: block;").shouldBe true, "-----Seach Widget did not Collapse-----"
    }

    def verifyDropDownCollapsed() {
            if(waitForElement(By.xpath("//div[@class='ac_results']/ul/li[1]"),false)!=null) {
                fail "destination drop down should be closed, but is not"
            }
    }

    def clickSouthwestSearchNavField() {
        waitForElement(By.id("globalnav_header_utility_search_input")).click()
    }
}
