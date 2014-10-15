package pages

import com.swacorp.dotcom.webscenarios.air.config.Domains
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.HotelItineraryData
import com.google.common.collect.Lists

class HotelPromotionsPage extends BasePage{

    private static final String TEMP_ONE_TABS_URL = "/html/app_test/hotel_promotions/template-1-with-tabs.html"
    private static final String TEMP_ONE_TABS_DEF_SORT_PRICE_URL = "/html/app_test/hotel_promotions/template-1-with-tabs-and-default-sorting-by-price.html"
    private static final String TEMP_ONE_NO_TABS_URL = "/html/app_test/hotel_promotions/template-1-without-tabs.html"
    private static final String TEMP_TWO_URL = "/html/app_test/hotel_promotions/template-2.html"



    private static final By TEMP_ONE_TABS_PROMO_SELECTS = By.cssSelector(".ui-tabs-panel:not(.ui-tabs-hide) .submitButton")
	private static final By TEMP_TWO_TABS_PROMO_SELECTS = By.className("hotel_promotion_submit_button")
    private static final By TEMP_ONE_TABS_TAB_LINKS = By.xpath("//a[contains(@href, '#hotel_promotions_template_1_sample_with_tabs_horizontal_menu_tab')]")
    private static final By TEMP_ONE_TABS_SELECTED_TAB_LINK = By.xpath("//li[contains(@class, 'ui-tabs-selected ')]//a")
    private static final By TEMP_ONE_TABS_PROMO_PRICES = By.cssSelector(".ui-tabs-panel:not(.ui-tabs-hide) .daily_rate")
    private static final By TEMP_ONE_TABS_SELECTED_HOTEL_ID = By.xpath("//form/input[@name='locationId']")
    private static final By TEMP_ONE_WITHOUT_TABS_PRICE_ELEMENT = By.className("daily_rate")
    private static final By ADULTS_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_description_container')]//select[@name='adults']")
    private static final By CHILDREN_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_description_container')]//select[@name='children']")

    private static final By CHECK_IN_DATE_BOTTOM_BOOKING_WIDGET = By.xpath("//div[contains(@id,'swa_module_bottom_booking_widget')]//input[@name='checkInDate']")
    private static final By CHECK_OUT_DATE_BOTTOM_BOOKING_WIDGET = By.xpath("//div[contains(@id,'swa_module_bottom_booking_widget')]//input[@name='checkOutDate']")

    private static final By DESTINATION_FIELD_BOTTOM_BOOKING_WIDGET = By.id("hotelDestination");
    private static final By PROMO_CODE_FIELD_BOTTOM_BOOKING_WIDGET = By.xpath("//div[contains(@id,'swa_module_bottom_booking_widget')]//input[@name='promoCode']")
    private static final By SEARCH_BUTTON_BOTTOM_BOOKING_WIDGET = By.xpath("//div[contains(@id,'swa_module_bottom_booking_widget')]//input[@name='book_now']")

    private static final String HOTEL_PROMOTIONS_DEFAULT_TAB_TESTING_URL = "/html/app_test/hotel_promotions/template-1-with-tabs.html"
    private static final String SELECTED_TAB_CLASS = "ui-state-default ui-corner-top ui-tabs-selected ui-state-active"
    private static final String defaultSelectedTabClass = "swa_modules_horizontal_menu_nav_firstItem " + SELECTED_TAB_CLASS
    private static final String HOTEL_PROMOTIONS_SELECTED_TAB_TESTING_URL = HOTEL_PROMOTIONS_DEFAULT_TAB_TESTING_URL + "?tab=PHO"
    private static final By HOTEL_PROMOTIONS_SELECTED_TAB_TESTING = By.id("PHO")
    private static final String HOTEL_PROMOTIONS_INVALID_TAB_TESTING_URL = HOTEL_PROMOTIONS_DEFAULT_TAB_TESTING_URL + "?tab=XXX"
    private static final By HOTEL_PROMOTIONS_ALL_TAB_TESTING = By.xpath("//li[contains(@class,'ui-state-default ui-corner-top')]")

    private static final String PRICE_SORTING_VALUE = "priceAscending"
    private static final String HOTEL_SORT_DROPDOWN = "hotelSortFormSelectBox"
    private static final String DATE_FORMAT = "MM/dd/yyyy"

    private static final By CHECK_IN_DATE_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_description_container')]//input[@name='checkInDate']")
    private static final By CHECKOUT_DATE_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_description_container')]//input[@name='checkOutDate']")
    private static final By TEMP_TWO_CHECK_IN_DATE_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_promotions_table_container')]//input[@name='checkInDate']")
    private static final By TEMP_TWO_CHECKOUT_DATE_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_promotions_table_container')]//input[@name='checkOutDate']")
    private static final By BOOK_NOW_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_description_container')]//input[@name='book_now']")

    private static final By TEM_TWO_BOOK_NOW_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_promotions_table_container')]//input[@name='book_now']")
    private static final By TEMP_TWO_SELECT_ADULT_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_promotions_table_container')]//select[@name='adults']")
    private static final By TEMP_TWO_SELECT_CHILDREN_ROW_BOOKING_WIDGET = By.xpath("//div[contains(@class,'hotel_promotions_table_container')]//select[@name='children']")

    private static final By HOTEL_PROMOTION_STAR = By.name("starRating")
    private static final By HOTEL_PROMOTION_PRICE = By.className("hotel_promotion_price")
    private static final String HOTEL_SORT_FORM_SELECT_BOX = "hotelSortFormSelectBox"
    private static final String VALUE_STAR_RATING_LOW_TO_HIGH = "starRatingLowToHigh"
    private static final String VALUE_PRICE_DESCENDING = "priceDescending"

    HotelItineraryData hotelItineraryData
    OutboundAndReturnDatesAndPopUp calendarPopUp

    public HotelPromotionsPage(WebDriverProvider driverProvider) {
        super(driverProvider)
    }

    String getRelativePath() {
        return ""  //TODO change as needed for page validation.
    }

    private void openTemplate(String url) {
        get(Domains.dotcomDomain + url)
        checkNoOops()
    }

    def openTemplateOneWithTabsDefaultSortingPrice() {
        openTemplate(TEMP_ONE_TABS_DEF_SORT_PRICE_URL)
    }

    def openTemplateOneWithTabs() {
        openTemplate(TEMP_ONE_TABS_URL)
    }

    def openTemplateOneWithoutTabs() {
        openTemplate(TEMP_ONE_NO_TABS_URL)
    }

    def openTemplateTwo() {
        openTemplate(TEMP_TWO_URL)
    }

    def chooseInDropDownSortByRate(){
       chooseInDropDownByValue(HOTEL_SORT_FORM_SELECT_BOX, VALUE_PRICE_DESCENDING)
    }

    def chooseInDropDownSortByStarRate(){
        chooseInDropDownByValue(HOTEL_SORT_FORM_SELECT_BOX, VALUE_STAR_RATING_LOW_TO_HIGH)
     }


    def verifyPromotionsSortedByColumnRate() {
       List<Integer> promotionPrices = this.parserPricePromotionHotel(waitForElements(HOTEL_PROMOTION_PRICE))
       List<Integer> promotionPricesSorted = Lists.newArrayList(promotionPrices)
       Collections.sort(promotionPricesSorted,Collections.reverseOrder())
       promotionPrices.equals(promotionPricesSorted).shouldBe true, "Hotel promotions are not shown properly sorted by rate."
    }

    def verifyPromotionsSortedByColumnStar() {
        List<Integer> promotionStar = this.parserStarPromotionHotel(waitForElements(HOTEL_PROMOTION_STAR))
        List<Integer> promotionStarSorted = Lists.newArrayList(promotionStar)
        Collections.sort(promotionStarSorted)
        promotionStar.equals(promotionStarSorted).shouldBe true, "Hotel promotions are not shown properly sorted by star."
    }


    def parserStarPromotionHotel(List<WebElement> elements){
        List<Integer> promotionStar= new ArrayList<Integer>(elements.size())
        Integer star = null

        for (element in elements) {
            star = element.getAttribute("value").toInteger();
            promotionStar.add(star)
        }
        return promotionStar

    }

    def inspectPriceSortingOptionSelected() {
        isOptionInDropDownSelectedByValue(HOTEL_SORT_DROPDOWN, PRICE_SORTING_VALUE).shouldBe true, "Price sorting option should be selected and it is not."
    }

    def verifyPromotionsSortedByPrice() {
        List<Integer> promotionPrices = this.parserPricePromotionHotel(waitForElements(TEMP_ONE_TABS_PROMO_PRICES))
        List<Integer> promotionPricesSorted = Lists.newArrayList(promotionPrices)
        Collections.sort(promotionPricesSorted)
        promotionPrices.equals(promotionPricesSorted).shouldBe true, "Hotel promotions are not being shown properly sorted by price."
    }

    def selectRandomPromotion() {
        List<WebElement> elements = waitForElements(TEMP_ONE_TABS_PROMO_SELECTS)

        Random random = new Random()
        int randomInt = random.nextInt(elements.size())
        elements.get(randomInt).click()

        hotelItineraryData.hotelId =  waitForElement(TEMP_ONE_TABS_SELECTED_HOTEL_ID).getAttribute("value")
    }

	def selectRandomPromotionTemplateTwo() {
		List<WebElement> elements = waitForElements(TEMP_TWO_TABS_PROMO_SELECTS)

		Random random = new Random()
		int randomInt = random.nextInt(elements.size())
		elements.get(randomInt).click()
		hotelItineraryData.hotelId =  waitForElement(TEMP_ONE_TABS_SELECTED_HOTEL_ID).getAttribute("value")
	}

    def completeRowBookingWidget() {
        calendarPopUp.fillIn(CHECK_IN_DATE_ROW_BOOKING_WIDGET,hotelItineraryData.checkInDate.format(DATE_FORMAT))
        calendarPopUp.fillIn(CHECKOUT_DATE_ROW_BOOKING_WIDGET,hotelItineraryData.checkOutDate.format(DATE_FORMAT))
        chooseInDropDownByValue(ADULTS_ROW_BOOKING_WIDGET,hotelItineraryData.adults)
        chooseInDropDownByValue(CHILDREN_ROW_BOOKING_WIDGET,hotelItineraryData.children)
    }

	def completeRowBookingWidgetTempTwo() {
		calendarPopUp.fillIn(TEMP_TWO_CHECK_IN_DATE_ROW_BOOKING_WIDGET,hotelItineraryData.checkInDate.format(DATE_FORMAT))
		calendarPopUp.fillIn(TEMP_TWO_CHECKOUT_DATE_ROW_BOOKING_WIDGET,hotelItineraryData.checkOutDate.format(DATE_FORMAT))
		chooseInDropDownByValue(TEMP_TWO_SELECT_ADULT_ROW_BOOKING_WIDGET,hotelItineraryData.adults)
		chooseInDropDownByValue(TEMP_TWO_SELECT_CHILDREN_ROW_BOOKING_WIDGET,hotelItineraryData.children)
	}

    def clickOnBookNow() {
        waitForElement(BOOK_NOW_ROW_BOOKING_WIDGET).click()
    }

	def clickOnBookNowTempTwo() {
		waitForElement(TEM_TWO_BOOK_NOW_ROW_BOOKING_WIDGET).click()
	}

    def selectDifferentTab() {
        List<WebElement> tabs = waitForElements(TEMP_ONE_TABS_TAB_LINKS)
        WebElement selectedTab = waitForElement(TEMP_ONE_TABS_SELECTED_TAB_LINK)

        for (int i = 0; i < tabs.size(); i++) {
            if(tabs.get(i).getAttribute("href") == selectedTab.getAttribute("href")) {
                tabs.remove(i)
                break
            }
        }

        Random random = new Random()
        int randomInt = random.nextInt(tabs.size())
        tabs.get(randomInt).click()
    }

    def verifyRowDataPopulatedOnTab() {
        Date checkinDate = new Date().parse(DATE_FORMAT, waitForElement(CHECK_IN_DATE_ROW_BOOKING_WIDGET).getAttribute("value"))
        Date checkoutDate = new Date().parse(DATE_FORMAT, waitForElement(CHECKOUT_DATE_ROW_BOOKING_WIDGET).getAttribute("value"))
        Date checkinDateEntered = hotelItineraryData.checkInDate
        Date checkoutDateEntered = hotelItineraryData.checkOutDate

        (checkinDate.format(DATE_FORMAT) == checkinDateEntered.format(DATE_FORMAT)).shouldBe true, "Check in date $checkinDate populated to a different tab does not match the one ($checkinDateEntered) by the user. "
        (checkoutDate.format(DATE_FORMAT) == checkoutDateEntered.format(DATE_FORMAT)).shouldBe true, "Check out date $checkoutDate populated to a different tab does not match the one ($checkoutDateEntered) by the user. "

        isOptionInDropDownSelectedByValue(ADULTS_ROW_BOOKING_WIDGET, hotelItineraryData.adults).shouldBe true, "The number of adults populated does not match the one selected previously $hotelItineraryData.adults"

        isOptionInDropDownSelectedByValue(CHILDREN_ROW_BOOKING_WIDGET, hotelItineraryData.children).shouldBe true,"The number of children populated does not match the one selected previously $hotelItineraryData.children"
    }

    def selectDifferentSortingOption(){
       chooseInDropDownByValue(HOTEL_SORT_DROPDOWN,PRICE_SORTING_VALUE)
    }

    def completeBottomBookingWidget() {

        calendarPopUp.fillIn(CHECK_IN_DATE_BOTTOM_BOOKING_WIDGET,hotelItineraryData.checkInDate.format(DATE_FORMAT))
        calendarPopUp.fillIn(CHECK_OUT_DATE_BOTTOM_BOOKING_WIDGET,hotelItineraryData.checkOutDate.format(DATE_FORMAT))
        waitForElement(DESTINATION_FIELD_BOTTOM_BOOKING_WIDGET).sendKeys(hotelItineraryData.destination)
        waitForElement(PROMO_CODE_FIELD_BOTTOM_BOOKING_WIDGET).sendKeys(hotelItineraryData.promoCode)
    }

    def clickOnSearchButton(){
        waitForElement(SEARCH_BUTTON_BOTTOM_BOOKING_WIDGET).click()
    }

    def verifyPromotionsSortedByPriceAscTemplate1() {
        List<WebElement> pricesElements = waitForElements(TEMP_ONE_WITHOUT_TABS_PRICE_ELEMENT)
        List<Integer> promotionPrices = parserPricePromotionHotel(pricesElements)
        List<Integer> promotionPricesSorted = Lists.newArrayList(promotionPrices)
        Collections.sort(promotionPricesSorted)
        promotionPrices.equals(promotionPricesSorted).shouldBe true, "Hotel promotions are not being shown properly sorted by price."
    }

    def parserPricePromotionHotel(List<WebElement> elements){
        List<Integer> promotionPrices = new ArrayList<Integer>(elements.size())
        Integer price = null

        for (element in elements) {
            price = element.getText().replace('$', '').toInteger()
            promotionPrices.add(price)
        }
        return promotionPrices
     }


    void verifyValidationMessageForHotelWidget() {
        def oopsMessages = [
                'Please enter a city name, address, point of interest, or an airport code.',
                'Please enter a check-in date.',
                'Please enter a check-out date.'
        ]
        verifyOopsMessages(oopsMessages)
    }

    def openHotelPromotionsSelectedTab() {
        openTemplate(HOTEL_PROMOTIONS_SELECTED_TAB_TESTING_URL)
    }

    void verifyHotelPromotionsSelectedTabsIsCorrect() {
        WebElement selectedTab = waitForElement(HOTEL_PROMOTIONS_SELECTED_TAB_TESTING)

        SELECTED_TAB_CLASS.equals(selectedTab.getAttribute("class")) shouldBe true, "The Hotel promotions tab does not match the one selected."
    }

    def openHotelPromotionsInvalidTab() {
        openTemplate(HOTEL_PROMOTIONS_INVALID_TAB_TESTING_URL)
    }

    def verifyHotelPromotionsFirstTabIsSelectedByDefault() {

        List<WebElement> tabs = waitForElements(HOTEL_PROMOTIONS_ALL_TAB_TESTING)
        defaultSelectedTabClass.equals(tabs.get(0).getAttribute("class")) shouldBe true, "The Hotel promotions tab does not match the one by default."
    }

    def openHotelPromotionsDefaultTab() {
        openTemplate(HOTEL_PROMOTIONS_DEFAULT_TAB_TESTING_URL)
    }
}