package steps

import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Alias
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.When
import pages.*
import pages.elements.GlobalNavigationFooter
import pages.elements.GlobalNavigationHeader
import pages.elements.PriceTable
import pages.elements.RapidRewardsAccountBar
import pages.elements.ShoppingCart
import state.Flow
import steps.conditional.ToggleGlobalNav
import util.BrowserActions
import util.ItineraryData
import util.RRContactInformation
import state.PassengerData

public class NavigationSteps {

    HomePage homePage
    ViewReservationPage viewReservationPage
    PurchasePage purchasePage
    ConfirmationPage confirmationPage
    PricePage pricePage
    TravelFundsPage travelFundsPage
    BrowserActions browser
    RepricePage repricePage
    SelectFlightsPage selectFlightsPage
    Flow flow
    SearchFlightsPage searchFlightsPage
    ProductPage productPage
    GlobalNavigationHeader globalNavHeaderPage
    GlobalNavigationFooter globalNavFooterPage
    MyAccountPage myAccountPage
    RapidRewardsPartnersPage rapidRewardsPartnersPage
    ShoppingCart shoppingCart
    PriceTable priceTable
    RRContactInformation rrContact
    ItineraryData itineraryData
    PassengerData passengerData
    RapidRewardsAccountBar rapidRewardsAccountBar

    @Given("I am on the Create An Account Page")
    def openCreateAccountPage() {
        homePage.open()
        if (ToggleGlobalNav.isOn()) {
            globalNavHeaderPage.openNewHeaderMenuRapidRewards()
            globalNavHeaderPage.clickOnEnrollLink()
        } else {
            globalNavHeaderPage.openRapidRewardsSubMenu().clickOnEnrollLink()
        }
    }

    @Given("I am on the Itinerary Page")
    def openItineraryPage() {
        viewReservationPage.open()
    }

    @Given("I am on the Homepage")
    @When("I am on the Homepage")
    @Alias("I am a Southwest user at the homepage")
    def openSwaHomepage() {
        homePage.open()
    }

    @Given("I am on the Travel Funds Page")
    def selectTravelFundsPage() {
        travelFundsPage.open()
    }

    @Given("I click continue to the Purchase page")
    @When("I click continue to the Purchase page")
    @Alias("I continue to the Purchase page")
    def continueToPurchasePage() {
        acceptNewPriceAndContinue()
        pricePage.verifyBasicPage()
        if (!DynaStubsIntegration.useDynaStubs()) {
            pricePage.verifyPageHeader()
            if(flow.isRapidRewards || flow.isCustomer) {
                rapidRewardsAccountBar.verifyRRGreeting()
                rapidRewardsAccountBar.verifyLogOutLink()
                rapidRewardsAccountBar.verifyRRName()
                rapidRewardsAccountBar.verifyMyAccountLink()
                if(flow.isRapidRewards) {
                    rapidRewardsAccountBar.verifyRRacountNumber()
                    rapidRewardsAccountBar.verifyTier()
                }
            }
            pricePage.verifyDepartureAndArrivalTime()
            pricePage.verifyFlightNumber()
            pricePage.verifyDepartureAndArrivalCities()
            pricePage.verifyTravelTime()
            pricePage.verifyFareType()
            pricePage.verifyRoutingType()
            pricePage.verifyDate()
            pricePage.verifyCarrierLogos()
            priceTable.verifyPassengerType()
            priceTable.verifyTripTypes()
            priceTable.verifyRouting()
            priceTable.verifyFareType()
            priceTable.verifyNumberOfPassengers()
            priceTable.verifySubTotal()
            priceTable.verifyPriceFareBreakDown()
            pricePage.verifyAirTotalIsEqualToTripTotal()
            pricePage.verifyPriceMatchInPriceTableAndShoppingCart();
            if(passengerData.containsSeniorAndAdultPassengers()) {
                priceTable.verifyMixedPaxSubTotalFromSelectFlightPage()
            }
            priceTable.verifyAirTotalIsPresent()
            pricePage.verifyAirTotalMatchInPriceTableAndShoppingCart()
            priceTable.verifyOutboundAndInboundTotalAmounts()
            priceTable.verifyAirTotalAndFareBreakDownTotal()
            priceTable.verifyPageAndShoppingCartTotal()
            priceTable.verifyTotalOnPage()
            if(!itineraryData.isPromoCertBooking()){
                if(itineraryData.promoCode == null){
                    priceTable.verifyBaseFarePlusTax()
                }
                priceTable.verifyTaxesAndBaseFareInFareBreakDown()
                priceTable.verifyTotalPlusPassengerCount()
                priceTable.verifyTotalOnPageWithTotalInFareBreakDown()
                priceTable.verifyRedeemableMemberPoints()
                priceTable.verifyAdultSeniorCount()
                shoppingCart.verifySaveAndCheckoutButton()
            }
            shoppingCart.airInShoppingCartIsExpanded()
            shoppingCart.modifyAndRemoveLinks()
            shoppingCart.verifyDate()
            shoppingCart.verifyFlightNumber()
            shoppingCart.verifyDepartureAndArrivalTime()
            shoppingCart.verifyOutboundAndInboundTotals()
            shoppingCart.verifyTotalCostBreakdown()
            shoppingCart.verifyTripTotal()
            shoppingCart.verifyDepartureAndArrivalCities()
            shoppingCart.verifyGovtTaxesFeesLinkIsPresent()
            shoppingCart.verifyFareType()
        }
        pricePage.clickContinue()
    }

    @Given("I click the back button")
    @When("I click the back button")
    def clickBackButton() {
        homePage.clickBrowserBackButton()
        homePage.checkSomethingServed()
    }

    @When ("I click the forward button")
    def clickForwardButton(){
        homePage.clickBrowserForwardButton()
        homePage.checkSomethingServed()
    }

    @When("I click the refresh button")
    def clickRefreshButton() {
        homePage.clickBrowserFreshButton()
        homePage.checkSomethingServed()
    }

    @When("I continue to Confirmation page from Purchase page")
    def clickContinueToComplete() {
        clickOnPurchase()
        confirmationPage.verifyPage()
        confirmationPage.storeCurrentPNR()
    }

    @When("I continue to Confirmation page")
    def clickContinueToConfirmation() {
        clickOnPurchase()
    }

    void acceptNewPriceAndContinue() {
        if (repricePage?.isRepricingPage()) {
            if (!DynaStubsIntegration.useDynaStubs()) {
                repricePage.init()
            }
            repricePage.clickContinue()
        }
    }

    @When("I come back to the website")
    def leaveWebsiteAndComeBack() {
        browser.clearSession()
        homePage.open()
    }

    @Given("I come back to select flight")
    @When("I come back to select flight")
    def comeBackFlightPageFromPricePage(){
        pricePage.clickSelectFlightBreadCrumbs()
        selectFlightsPage.verifyPage()
    }

    @When("I complete the purchase")
    def clickOnPurchase() {
        purchasePage.clickVisibleSubmitAsUnaccompaniedMinor()
    }

    @When("I click on product link")
    def clickProductLink() {
        searchFlightsPage.clickProductLink()
    }

    @When("I click on business select link")
    def clickBusinessSelectLink(){
        productPage.clickOnBusinessSelectLink()
    }

    @When("I get to the business select page")
    def showRetrieveReservationToUpgradeToBusinessSelect(){
        homePage.open()
        if (ToggleGlobalNav.isOn()) {
            globalNavHeaderPage.openNewHeaderMenuPlanATripPartOne()
            globalNavHeaderPage.clickOnBookAFlight()
        } else {
            homePage.clickAirSearchButtonWidget()
        }
        clickProductLink()
        clickBusinessSelectLink()
    }

    @When("I click on Buy Points")
    def clickOnBuyPoints() {
        if (ToggleGlobalNav.isOn()) {
            globalNavHeaderPage.openNewHeaderMenuRapidRewards()
            globalNavHeaderPage.clickOnBuyPointsTravelLink()
        } else {
            globalNavHeaderPage.openRapidRewardsSubMenu().clickOnBuyPointsTravelLink()
        }
        globalNavHeaderPage.verifyPage()
    }

    @When("I click on More Rewards")
    def clickOnMoreRewards() {

        if (ToggleGlobalNav.isOn()) {
            globalNavHeaderPage.openNewHeaderMenuRapidRewards()
            globalNavHeaderPage.clickOnMoreRewardsLink()
        } else {
            globalNavHeaderPage.openRapidRewardsSubMenu().clickOnMoreRewardsLink()
        }

        globalNavHeaderPage.verifyPage()
    }

    @When("I click on Rapid Rewards Shopping")
    def clickOnRapidRewardsShopping() {
        globalNavHeaderPage.openRapidRewardsSubMenu().clickOnRapidRewardsShoppingLink()
        globalNavHeaderPage.verifyPage()
    }

    @When("I click on Rapid Rewards Partners")
    def clickOnRapidRewardsPartners(){
        globalNavHeaderPage.openRapidRewardsSubMenu()
        globalNavHeaderPage.clickOnPartnersLink()
        globalNavHeaderPage.verifyPage()
    }

    @When("I click on Rapid Rewards Partners Category named \$RRPCatNamed")
    def clickOnRapidRewardsPartnersCategoryNamed(String RRPCatNamed){
        rapidRewardsPartnersPage.clickOnRapidRewardsPartnersCategory(RRPCatNamed)
    }

    @When("I click on Rapid Rewards Partners named \$RRPNamed")
    def clickOnRapidRewardsPartnersNamed(String RRPNamed){
        rapidRewardsPartnersPage.clickOnRapidRewardsPartners(RRPNamed)
    }

    @When("I select \$vendorCar for Vendor Car")
    def selectCarVendor(String vendorCar){
        rapidRewardsPartnersPage.selectImgVendorCar(vendorCar)
    }

    @When("I click on Browser Back Button to go to the Price page from Purchase page")
    def clickBackBrowserButtonToPricePageFromPurchasePage(){
        purchasePage.clickBrowserBackButton()
    }

	@When("I select Airport Information under the Air section in Global Nav bar")
	def selectAirportInformation() {
        if (ToggleGlobalNav.isOn()) {
            globalNavFooterPage.clickAirportInformationLink()
        } else {
            globalNavHeaderPage.clickAirportInformationLink()
        }
        globalNavHeaderPage.verifyPage()
	}

    @When("I click on the link that shows my outbound flight")
    @Alias("I click on the link that shows my inbound flight")
    def clickLinkDepartureArrivalCitiesOfUpcomingFlight() {
        myAccountPage.clickAccountBarMyTravelLink()
        myAccountPage.clickLinkDepartureArrivalCitiesOfInboundFlight()
    }

    @When("I click Check In Online under My Travel in Global Nav bar")
    def clickAccountBarMyTravelCheckInOnline() {
        myAccountPage.clickAccountBarMyTravelCheckInOnline()
    }

    @When("I click Check In button under Upcoming Trips section on My Account page")
    def clickCheckInButtonUnderUpcomingTripsOnMyAccount() {
        myAccountPage.clickCheckInButtonUnderUpcomingTripsOnMyAccount()
    }

    @When("I come back to previous page in another windows")
    def comeBackToPreviousPageInBeforeWindow() {
        myAccountPage.comeBackToBeforeWindow(flow.windowBefore)
    }

    @When("I select Infant Flying under the Air section in Global Nav bar")
    def selectInfantFlying() {
        if (ToggleGlobalNav.isOn()) {
            globalNavFooterPage.clickOnInfantFlyingLink()
        } else {
            globalNavHeaderPage.clickOnInfantFlyingLink()
        }
        globalNavHeaderPage.verifyPage()
    }

    @When("I click Subscribe to Flight Status Messaging link from the confirmation page")
    def clickSubscribeLinkAndSwitchWindowFocus() {
        confirmationPage.openFlightStatusNotificationPageByLinkClick()
    }
}