package steps.thenSteps

import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.Named
import pages.SearchHotelsPage
import pages.ViewHotelReservationPage
import state.CurrentHotelPNR
import pages.SelectHotelRatePage
import pages.SelectHotelPage
import org.jbehave.core.model.ExamplesTable
import pages.PricePage
import pages.PurchasePage

class HotelThenSteps {

    SearchHotelsPage searchHotelsPage
    ViewHotelReservationPage viewHotelReservationPage
    CurrentHotelPNR currentHotelPNR
    SelectHotelRatePage selectHotelRatePage
    SelectHotelPage selectHotelPage
    PricePage pricePage
    PurchasePage purchasePage

    private static String HOTEL_SEARCH_TITLE = "Southwest Airlines - Select Hotel Property"
    private static String SELECT_HOTEL_HTML = "/hotels/select-hotel.html"
    private static String SELECT_HOTEL_RATE_HTML = "/hotels/select-hotel-rate.html"

    @Then("I see the hotel itinerary")
    def verifyHotelItinerary() {
        searchHotelsPage.verifyConfirmationNumber(currentHotelPNR.pnr)
    }

    @Then("I am on the Hotel Reservation page")
    @Alias('I am redirected back to the Hotel Reservation page')
    void verifyIAmOnTheCancelAirReservationPage() {
        viewHotelReservationPage.verifyCurrentPageLocation()
    }

    @Then("I see the associated products to the trip on the Hotel Reservation page")
    void verifyTheAssociatedProductsToTheTripOnHotelReservationPage() {
        viewHotelReservationPage.verifyAssociatedItemsDisplayed()
    }

    @Then("I receive confirmation that my hotel reservation is canceled")
    def verifyHotelCancellation() {
        searchHotelsPage.verifyHotelCancellation()
    }

    @Then("I should be able to see hotel rates for my hotel on the Select Hotel Rate page")
    def verifyHotelRatesForPromotion() {
        selectHotelRatePage.verifySearchDataPopulation()
    }

    @Then("I should see all my previously selected data")
    def verifyDataSelectPageFromPromotion() {
        searchHotelsPage.verifyHotelSearchData()
    }

    @Then("I should see all my previously selected data for the hotel")
    def verifyDataSelected() {
        selectHotelPage.verifyValidHotelDataSelected()
    }

    @Then("I should be on the Hotel Select page")
    @Alias("I get the Select Hotel page without errors")
    def verifySelectPageFromPromotion() {
        selectHotelPage.verifyPageTitle(HOTEL_SEARCH_TITLE)
     }

    @Then("I get the Select Hotel page with an according error message not being allowed to continue with the booking flow")
    def verifySearchingTwoRoomsGuestsLimit() {
        selectHotelPage.verifyRoomsGuestsLimit()
    }

    @Then("I should see the Hotel Map")
    def verifyHotelMap() {
        selectHotelPage.verifyHotelMap()
    }

    @Then("I should see the Street View")
    def verifyStreetView() {
        selectHotelPage.verifyStreetView()
    }

    @Then("I should see the Info Window")
    def verifyInfoWindow() {
        selectHotelRatePage.verifyInfoWindow()
    }

    @Then("I should see the Hotel Compare Map")
    def verifyHotelCompareMap() {
        selectHotelPage.verifyCompareMap()
    }

    @Then("I should see the Hotel Compare Info Window")
    def verifyHotelCompareInfoWindow() {
        selectHotelPage.verifyInfoWindow()
    }

    @Then("I should be able to see hotel results on the Select Hotel page")
    def verifyHotelSearchData() {
        selectHotelPage.shouldBeInPage(SELECT_HOTEL_HTML)
        selectHotelPage.verifyValidHotelDataSelected()
        selectHotelPage.verifyValidHotelDatesSelected()
    }

    @Then("I should be able to see hotel rates for the selected hotel on the Select Hotel Rate page")
    def verifyValidHotelDataSelected() {
        selectHotelRatePage.verifySearchDataPopulation()
        selectHotelRatePage.verifyHotelId()
        selectHotelRatePage.shouldBeInPage(SELECT_HOTEL_RATE_HTML)
    }

    @Then ("I see the Hotel Loyalty number field")
    def verifyHotelLoyaltyNumberFieldOnPurchasePage() {
        purchasePage.verifyHotelLoyaltyNumberField()
    }

    @Then ("I do not see the Hotel Loyalty number field")
    def verifyHotelLoyaltyNumberFieldIsNotDisplayedOnPurchasePage() {
        purchasePage.verifyHotelLoyaltyNumberFieldIsNotDisplayed()
    }

}
