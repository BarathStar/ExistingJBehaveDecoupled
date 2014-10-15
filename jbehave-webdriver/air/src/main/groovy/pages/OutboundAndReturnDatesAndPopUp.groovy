package pages

import java.text.SimpleDateFormat
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.ItineraryDateFactory

class OutboundAndReturnDatesAndPopUp extends BasePage {
    static final int CUTOFF_HOUROFDAY_BEFORE_SEARCHING_NEXT_DAY = 13;
    private static final By LAST_DAY_BEABLE = By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//tr/td[@onclick][last()]/a")
    private static final By UI_DATEPICKER_TRIGGER = By.xpath("//img[@class ='ui-datepicker-trigger']")
    private static final By UI_DATEPICKER_TRIGGER_CAR_WIDGET = By.xpath("//form[@id='booking_widget_car_form']//img[@class='ui-datepicker-trigger']")
    private static final By UI_DATEPICKER_TRIGGER_HOTEL_WIDGET = By.xpath("//form[@id='booking_widget_hotel_form']//img[@class='ui-datepicker-trigger']")
    private static final By UI_DATEPICKER_NEXT = By.xpath("//div[@id='ui-datepicker-div']//a[contains(@class,'ui-datepicker-next')]")
    private static final By MONTHSYEARTDATEPICKER = By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']")
    private static final By MONTHSDATEPICKER = By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month']")
    private static final By YEARDATEPICKER = By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-year']")
    private static final By ELIGIBLE_DATES = By.xpath("//a[contains(@class,'ui-state-default')]")
    private static final By CALENDAR_MONTHS = By.className("ui-datepicker-month")
    private static final By CALENDAR_YEARS = By.className("ui-datepicker-year")
    private static int maxMontsLimits = 12
    private HashMap<String,String> limitsDate = new HashMap<String, String>()
    private static final String DATE_SEPARATOR = "-"
    private static final String DATE_FORMAT = "dd-MMM-yyyy"

    def OutboundAndReturnDatesAndPopUp(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.

    }

    def selectDate(List<WebElement> elements, def inDate) {
        def String dateToBeChanged = inDate.toString()
        for (int i = 0; i < elements.size(); i++) {
            def day = elements.get(i)
            def String dateFromWebElement = day.getText().toString()
            if (dateFromWebElement.equals(dateToBeChanged)) {
                day.click()
                break
            }
        }
    }

    def getDayElementsForSelectedMonth(String outboundOrInbound, String sideOfCalendar = 'left') {
        def DATE_PICKER = [
                "Outbound": "outboundDate",
                "Inbound": "returnDate"
        ]
        def CALENDAR_SIDE = [
                "left": 1,
                "right": 2
        ]
        waitForElement(By.id(DATE_PICKER[outboundOrInbound])).click()
        return waitForElements(By.xpath("//div[@id='ui-datepicker-div']/div[${CALENDAR_SIDE[sideOfCalendar]}]/table/tbody/tr/td/a"))
    }

    def fillInSpecificDate(String fromFld, String toFld, String fromDays, String toDays) {
        def today = Calendar.getInstance()
        def to = Calendar.getInstance()
        def from = Calendar.getInstance()
        from.add(Calendar.DAY_OF_YEAR, fromDays.toInteger())
        to.add(Calendar.DAY_OF_YEAR, toDays.toInteger())
        typeInOutboundOrInboundDate(from.format("MM/dd/yyyy"), "outboundDate")
        typeInOutboundOrInboundDate(to.format("MM/dd/yyyy"), "returnDate")
    }

    protected def enterDate(Date dateToFly) {
        def outDate = waitForElement(By.cssSelector(".outboundDate"))
        outDate.click()
        outDate.sendKeys DELETE_EXISTING + dateToFly.format("MM/dd/yyyy")
    }

    def addOneToADate(String inDate /*MM/DD/YYYY */) {
        return addDaysToADate(inDate, 1)
    }

    def addDaysToADate(String inDate /*MM/DD/YYYY */, int days) {
        def sdf = new SimpleDateFormat()
        sdf.applyPattern("MM/dd/yyyy")
        def newCal = Calendar.getInstance()
        def processDate = sdf.parse(inDate)
        newCal.setTime(processDate)
        newCal.add(Calendar.DAY_OF_MONTH, + days)
        return newCal
    }

    def selectCalendarDate(def date, String outboundOrInbound) {
        def dateToSelect = date.get(Calendar.DAY_OF_MONTH)
        def List<WebElement> calenderMonth = getDayElementsForSelectedMonth(outboundOrInbound)
        selectDate(calenderMonth, dateToSelect)
    }

    def typeInOutboundOrInboundDate(String dateInputValue, String dateFieldId, boolean withVerification = true) {
        if (withVerification) {
            fillIn(By.id(dateFieldId), dateInputValue)
        } else {
            fillInWithoutVerification(By.id(dateFieldId), dateInputValue)
        }
    }

    Date calculateDateWithin24Hours() {
        return ItineraryDateFactory.chooseDayEligibleForCheckin(CUTOFF_HOUROFDAY_BEFORE_SEARCHING_NEXT_DAY);
    }

    def selectOutboundWithin24Hours() {
        Date dateToFly = calculateDateWithin24Hours();
        enterDate(dateToFly)
    }

    def openCalendarPopUp(String direction = "Outbound"){
        if(direction.equalsIgnoreCase("Outbound")){
            waitForElements(UI_DATEPICKER_TRIGGER)[0].click()
        }else{
            waitForElements(UI_DATEPICKER_TRIGGER)[1].click()
        }
    }

    def openCalendarPopUpInCarWidget(String direction = "Pick-Up"){
        if(direction.equalsIgnoreCase("Pick-Up")){
           waitForElements(UI_DATEPICKER_TRIGGER_CAR_WIDGET)[0].click()
        }else{
           waitForElements(UI_DATEPICKER_TRIGGER_CAR_WIDGET)[1].click()
        }
    }

    def getCalendarNextButton(){
        return waitForElement(UI_DATEPICKER_NEXT)
    }

    def boolean clickOnNextButton(){
        WebElement button = getCalendarNextButton()
        String cl = button.getAttribute('class')
        if(button.isDisplayed() && button.isEnabled() && !cl.contains('ui-state-disable')){
            button.click()
            return true
        }else{
            return false
        }
    }

    def getCalendarMaxDateLimit(String onPage,String widget="air"){
        String msg = ""
        if(widget.equalsIgnoreCase("car")){
           openCalendarPopUpInCarWidget()
        }
        if(widget.equalsIgnoreCase("air")){
            openCalendarPopUp()
        }
        int i = 1
        while(clickOnNextButton() && i < maxMontsLimits){
            i++
        }
        if(i< maxMontsLimits){
            List<WebElement> lastDay = waitForElements(LAST_DAY_BEABLE)
            WebElement monthYear = waitForElement(MONTHSYEARTDATEPICKER)
            msg = lastDay.get(lastDay.size()-1).getText()+monthYear.getText()
        }else{
            msg "Error, the calendar limit can't be reached"
        }
        limitsDate.put(onPage,msg)
        limitsDate.put(onPage+1,msg)
    }

    def verifyCalendarLimitsAreTheSame(){
        limitsDate.each{key,value->
             value.shouldBe limitsDate.values().toArray()[0], "Error in $key the Date limit $value is diferent to ${limitsDate.values().toArray()[0]}"
        }
    }

    Date getMaxDateLimit() {
        return new Date().parse(DATE_FORMAT, getMaxDateString())
    }

    private String getMaxDateString() {
        List<WebElement> days = waitForElements(ELIGIBLE_DATES)
        List<WebElement> months = waitForElements(CALENDAR_MONTHS)
        List<WebElement> years = waitForElements(CALENDAR_YEARS)

        String day = days.last().getText()
        String month = months.last().getText()
        String year = years.last().getText()

        String maxDateString = day + DATE_SEPARATOR + month + DATE_SEPARATOR + year
        return maxDateString
    }
}
