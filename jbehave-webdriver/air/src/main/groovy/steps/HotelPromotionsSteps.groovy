package steps

import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.HotelPromotionsPage
import pages.SearchHotelsPage
import pages.SelectHotelRatePage
import util.HotelItineraryData


import fixture.stubs.DynaStubsIntegration


class HotelPromotionsSteps {

    HotelPromotionsPage hotelPromotionsPage
    SelectHotelRatePage selectHotelRatePage
    SearchHotelsPage searchHotelsPage
    HotelItineraryData hotelData

    @Given("I am on Hotel Promotions Template 1 with tabs where price is default sorting option")
    def openHotelPromotionTemplateOneWithTabsDefaultSorting() {
        hotelPromotionsPage.openTemplateOneWithTabsDefaultSortingPrice()
    }

    @Given("I am on Hotel Promotions Template 1 with tabs")
    def openHotelPromotionTemplateOneWithTabs() {
        hotelPromotionsPage.openTemplateOneWithTabs()
    }


    @Given("I am on Hotel Promotions Template 1 without tabs")
    def openHotelPromotionTemplateOneWithoutTabs() {
        hotelPromotionsPage.openTemplateOneWithoutTabs()
    }

    @Given("I am on Hotel Promotions Template 2")
    def openHotelPromotionTemplateTwoWithTabs(){
        hotelPromotionsPage.openTemplateTwo()
    }

    @When("I sort my promotions by rate")
    def sortHotelPromotionsPageByRate(){
        hotelPromotionsPage.chooseInDropDownSortByRate()
    }

    @When("I sort my promotions by star rate")
    def sortHotelPromotionsPageByStar(){
        hotelPromotionsPage.chooseInDropDownSortByStarRate();
    }

    @When("I inspect that price sorting option is being used by default")
    def inspectPriceSortingAsDefault() {
        hotelPromotionsPage.inspectPriceSortingOptionSelected()
    }

    @When("I select a particular promotion for a hotel")
    def selectPromotion() {
        hotelPromotionsPage.selectRandomPromotion()
    }

	@When("I select a particular promotion for a hotel on template two")
	def selectPromotionTemplateTwo() {
		hotelPromotionsPage.selectRandomPromotionTemplateTwo()
	}

    @When("I attempt to book that promotion")
    def attemptToBookPromotion() {
        if (DynaStubsIntegration.useDynaStubs()) {
            DynaStubsIntegration dyna = new DynaStubsIntegration()
            dyna.generateSelectHotelSpecification(hotelData.hotelId)
        }
        hotelPromotionsPage.completeRowBookingWidget()
        hotelPromotionsPage.clickOnBookNow()
        selectHotelRatePage.verifyPage()

    }

    @When("I attempt to book that promotion on template two")
    def attemptToBookPromotionTemplateTwo() {

		if (DynaStubsIntegration.useDynaStubs()) {
			DynaStubsIntegration dyna = new DynaStubsIntegration()
			dyna.generateSelectHotelSpecification(hotelData.hotelId)
		}

	    hotelPromotionsPage.completeRowBookingWidgetTempTwo()
        hotelPromotionsPage.clickOnBookNowTempTwo()
	    selectHotelRatePage.verifyPage()
    }

    @When("I complete all the required data in the hotel booking widget")
    def completeHotelPromotionRowWidget() {
        hotelPromotionsPage.completeRowBookingWidget()
    }

    @When("I select another hotel promotion from another tab")
    def selectDifferentPromotionFromOtherTab() {
        hotelPromotionsPage.selectDifferentTab()
        hotelPromotionsPage.selectRandomPromotion()
    }

    @When("I select a different sorting option than the default for the promotions")
    def selectDifferentSortingOptionThanTheDefaultForPromotions() {
        hotelPromotionsPage.selectDifferentSortingOption()
    }

    @When("I attempt to book using the bottom hotel booking widget")
    def attemptToBookPromotionInTemplate1() {
        hotelPromotionsPage.completeBottomBookingWidget()
        hotelPromotionsPage.clickOnSearchButton()
        searchHotelsPage.verifyPage()
    }

    @When("I attempt to book using the bottom hotel booking widget without completing any field")
    def attemptToBookUsingWidgetNotPopulated() {
        hotelPromotionsPage.clickOnSearchButton()
    }

    @When("I access the tabbed version of the Hotel Promotion page having a specific city code setup in its URL")
    def openHotelPromotionsSelectedTab(){
        hotelPromotionsPage.openHotelPromotionsSelectedTab()
    }

    @When("I access the tabbed version of the Hotel Promotion page having an invalid city code setup in its URL")
    def openHotelPromotionsInvalidTab(){
        hotelPromotionsPage.openHotelPromotionsInvalidTab()
    }

    @When("I access the tabbed version of the Hotel Promotion page without having a city code setup in its URL")
    def openHotelPromotionsDefaultTab(){
        hotelPromotionsPage.openHotelPromotionsDefaultTab()
    }
}