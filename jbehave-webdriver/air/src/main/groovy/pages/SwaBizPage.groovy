package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import state.Flow

class SwaBizPage extends BasePage {
    static final String DEFAULT_CID = "99576735"
    private final String SWACTMLOGIN = Domains.secureSwabizDomain + "/swabiz/ctm/login"
    private final String SWABIZ_BOOK_TRAVEL_KEEP_CID = Domains.swabizDomain + "/flight/search-flight.html?"
    private final String SWABIZ_CHANGE_TRAVEL = Domains.swabizDomain + "/travel_center/changeItinerary.html"
    private final String SWABIZ_CHECKIN_TRAVEL = Domains.swabizDomain + "/flight/retrieveCheckinDoc.html"
    private final String SWABIZ_EARLY_BIRD = Domains.swabizDomain + "/flight/early-bird-retrieve-reservation.html"
    private final String SWABIZ_CANCEL_TRAVEL = Domains.swabizDomain + "/travel_center/cancelAir.html"
    private final String SWABIZ_VIEW_TRAVEL = Domains.swabizDomain + "/flight/lookup-air-reservation.html"
    private static final By TRAVELER_ACCOUNT_MANAGEMENT = By.xpath("//a[@href='/swabiz/ctm/travel-account-management']")
    private String companyId = DEFAULT_CID
    private String travelManagerFirstName = "Leap"
    private String travelManagerLastName = "Frog"
    private String passWord = "leapfrog"
    private static final By LOW_FARE_CALENDAR_LINK = By.xpath("//a[contains(@href, 'lowFareFinderEntry')]");
    private static final By LOW_FARE_WHERE_WE_FLY_LINK = By.xpath("//a[@href='/travel_center/routemap_dyn.html']");
    private static final By WHERE_WE_FLY_LINK = By.xpath("//a[@href='/flight/pop_whereWeFly.html']");
    private static final By SUBMIT_BUTTON = By.className("submitButton")
    private static final By COMPANY_ID_FIELD = By.id("companyIdField")
    private static final By CAR_TITLE = By.cssSelector("a[title='car']")
    private static final By HOTEL_TITLE = By.cssSelector("a[title='hotel']")
    private static final By BOOK_TRAVEL_LINK = By.partialLinkText("Book Travel")
    private static final By GENERATE_REPORT = By.xpath("//div[@class=\"swaContent\"]//input[@type=\"submit\"]")

    Flow flow

    def SwaBizPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    private def openCompanyTravelManagerLogin() {
        flow.isSwabiz = true
        get(SWACTMLOGIN)
        checkSomethingServed()
    }

    def openChangeTravelPage() {
        flow.isSwabiz = true
        get(SWABIZ_CHANGE_TRAVEL)
        checkSomethingServed()
    }

    def openCheckInTravelPage() {
        flow.isSwabiz = true
        get(SWABIZ_CHECKIN_TRAVEL)
        checkSomethingServed()
    }

    def openCancelTravelPage() {
        flow.isSwabiz = true
        get(SWABIZ_CANCEL_TRAVEL)
        checkSomethingServed()
    }

    def openViewItineraryTravelPage() {
        flow.isSwabiz = true
        get(SWABIZ_VIEW_TRAVEL)
        checkSomethingServed()
    }

    public def openCompanyTravelReservationPage() {
        flow.isSwabiz = true
        get(SWABIZ_BOOK_TRAVEL_KEEP_CID)
        checkSomethingServed()
    }
    void goToCarRentalPage() {
        waitForElement(CAR_TITLE).click()
    }

    void goToHotelReservationPage() {
        waitForElement(HOTEL_TITLE).click()
    }


    private def enterCompanyId() {
        waitForElement(COMPANY_ID_FIELD).sendKeys(getCompanyId())
        waitForElement(SUBMIT_BUTTON).click()
    }

    private String getCompanyId() {
        return (companyId != "")?companyId:DEFAULT_CID;
    }

    def loginAsAnonymousSWABizUser() {
        openCompanyTravelReservationPage()
        enterCompanyId()
    }

    def setCompanyId(String cid) {
        this.companyId = cid
    }

    def setTravelManagerFirstName(String firstName) {
        this.travelManagerFirstName = firstName
    }

    def setTravelManagerLastName(String lastName) {
        this.travelManagerLastName = lastName
    }

    def setPassword(String pass) {
        this.passWord = pass
    }

    def clickOnBookTravel() {
        waitForElement(BOOK_TRAVEL_LINK).click()
    }

    def loginAsCompanyTravelManager() {
        openCompanyTravelManagerLogin()
        companyTravelManagerLogin()
        loginAsAnonymousSWABizUser()
    }

    def loginAsCTM() {
        openCompanyTravelManagerLogin()
        companyTravelManagerLogin()
    }

    def bookForTravelerAsCTM() {
        waitForElement(By.cssSelector("a[href='/swabiz/ctm/travel-account-management']")).click()
        waitForElement(By.cssSelector("input[value='Generate Report']")).click()
        waitForElement(By.cssSelector("input[value='Book']")).click()
    }

    public boolean isLoginFail() {
        boolean loginFail = false
        String page = getPageSource()
        if (page.contains("Please use your browser's BACK button to return to the previous page")) {
            println "--> login fail"
            loginFail = true
        }
        return loginFail
    }

    private def companyTravelManagerLogin() {
        try {
            def id = waitForElement(By.id("j_username"))
            id.sendKeys(DELETE_EXISTING + getCompanyId() + TAB)
            def name = waitForElement(By.id("first_name"))
            name.sendKeys(DELETE_EXISTING + travelManagerFirstName + TAB)
            name = waitForElement(By.id("last_name"))
            name.sendKeys(DELETE_EXISTING + travelManagerLastName + TAB)
            def pwd = waitForElement(By.id("j_password"))
            pwd.sendKeys(DELETE_EXISTING + passWord + TAB)
            waitForElement(By.id("login")).click()
            isLoginFail()

        }
        catch (NoSuchElementException e) {
            println "--> ${e.getMessage()}"
        }
    }

    def clickWhereWeFlyLinkOnSwabizSearchPage() {
        waitForElement(WHERE_WE_FLY_LINK).click()
    }

    def clickWhereWeFlyLinkOnSwabizShortCutPage() {
        waitForElement(LOW_FARE_WHERE_WE_FLY_LINK).click()
    }


    def clickLowFareCalendarLink() {
        waitForElement(LOW_FARE_CALENDAR_LINK).click()
    }

    def openEarlyBirdPage() {
        flow.isSwabiz = true
        get(SWABIZ_EARLY_BIRD)
        checkSomethingServed()
    }

    def clickOnTravelerAccountManagement() {
        waitForElement(TRAVELER_ACCOUNT_MANAGEMENT).click()
    }

    def clickOnGenerateReport() {
        waitForElement(GENERATE_REPORT).click()
    }

    def selectAccountWithLastName(String lastName) {
        By link = By.xpath("//tr[contains(.,'$lastName')]/td/input[@value=\"Book\"]")
        waitForElement(link).click()
    }
}
