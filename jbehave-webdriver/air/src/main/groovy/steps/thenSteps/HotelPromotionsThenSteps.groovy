package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.HotelPromotionsPage
import pages.SearchHotelsPage


class HotelPromotionsThenSteps {

    HotelPromotionsPage hotelPromotionsPage
    SearchHotelsPage searchHotelPage


    @Then("I should see hotel promotions sorted according to the default sorting option")
    def verifyHotelPromotionsSortedByPrice() {
        hotelPromotionsPage.verifyPromotionsSortedByPrice()
    }

    @Then("I should see all my previously selected tab data")
    def verifyPreviouslySelectedData() {
        hotelPromotionsPage.verifyRowDataPopulatedOnTab()
    }

    @Then("I should see hotel promotions sorted according to the sorting option chosen")
    def verifyHotelPromotionsSortedByPriceOnTemplate1(){
        hotelPromotionsPage.verifyPromotionsSortedByPriceAscTemplate1()
    }

    @Then("I should see an oops message stating that I should add data")
    def verifyHotelBookingWidgetErrors() {
        hotelPromotionsPage.verifyValidationMessageForHotelWidget()
    }

    @Then("I should see hotel promotions sorted by rate")
    def verifyHotelPromotionsSortedByRate() {
        hotelPromotionsPage.verifyPromotionsSortedByColumnRate()
    }

    @Then("I should see hotel promotions sorted by star rate")
    def verifyHotelPromotionsSortedByStar(){
        hotelPromotionsPage.verifyPromotionsSortedByColumnStar()
    }

    @Then("I see that the tab setup in the URL is selected by default in the Hotel Promotion Page")
    def verifyHotelPromotionsTabIsSelected(){
        hotelPromotionsPage.verifyHotelPromotionsSelectedTabsIsCorrect()
    }

    @Then("I see the first tab is selected by default in the Hotel Promotion Page")
    def verifyHotelPromotionsFirstTabIsSelectedByDefault(){
        hotelPromotionsPage.verifyHotelPromotionsFirstTabIsSelectedByDefault()
    }
}
