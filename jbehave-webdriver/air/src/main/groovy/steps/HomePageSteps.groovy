package steps

import com.swacorp.dotcom.webscenarios.air.Data
import com.swacorp.dotcom.webscenarios.air.RRUser
import domain.PurchaseGiftCardData
import fixture.stubs.DynaStubsIntegration
import org.jbehave.core.annotations.Given
import org.jbehave.core.annotations.Then
import org.jbehave.core.annotations.When
import org.jbehave.core.annotations.Alias
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import pages.CancelAirReservationPage
import pages.GiftCardCheckBalancePage
import pages.HomePage
import pages.SearchFlightsPage
import pages.ViewReservationPage
import pages.elements.AccountBar
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationFooter
import pages.elements.GlobalNavigationHeader
import pages.elements.RapidRewardsAccountBar
import pages.elements.TravelToolsDropDownMenu
import state.Flow
import steps.conditional.ToggleGlobalNav
import steps.conditional.ToggleHomepage2
import util.ItineraryData
import util.ItineraryDateFactory
import pages.elements.ShoppingCart
import pages.PurchasePage

class HomePageSteps {
    HomePage homePage
    SearchFlightsPage searchFlightsPage
    AccountBar accountBar
    CancelAirReservationPage cancelAirReservationPage
    ViewReservationPage viewReservationPage
    FlightSearchForm flightSearchForm
    ItineraryData itineraryData
    TravelToolsDropDownMenu travelToolsDropDownMenu
    GlobalNavigationHeader globalNavigationHeader
    GlobalNavigationFooter globalNavigationFooter
    CommonSteps commonSteps
    GiftCardCheckBalancePage giftCardCheckBalancePage
    ShoppingCart shoppingCart
    Flow flow
    Data data
    PurchasePage purchasePage
    RapidRewardsAccountBar rapidRewardsAccountBar


    @Given("I am on the Gift Card Landing page")
    def openGiftCardLandingPage(){
        openHomePage()
        goToGiftCardLandingPageFromFooterLink()
    }

    @Given("I am on the Home page")
    @When("I am on the Home page")
    void openHomePage() {
        homePage.open()
        homePage.verifyPage()

        if (!DynaStubsIntegration.useDynaStubs()) {
            if (flow.isRapidRewards || flow.isCustomer) {
                verifyLoggedInInformation()
            }
        }
    }

    @When("I search for flights as a senior from the Home page")
    void searchFlights() {
        openHomePage()
        flightSearchForm.selectAdultAndSeniorPassengers(0 ,1)
        homePage.submit()
    }

    @When("I select a one-way \$carrier trip from \$from_code to \$to_code on the home page")
        def selectOneWayTripOnHomePage(String carrier, String from, String to) {
            commonSteps.setupOneWayCarrierFlight(carrier)
            homePage.clickOneWay()
            itineraryData.departureStation = from
            itineraryData.arrivalStation = to
            homePage.fillInAirports(from, to)
            homePage.enterDepartureDate(ItineraryDateFactory.getAnyAvailableDepartureDate())
            homePage.submit();
        }

    @When("On home page I select one-way \$carrier trip from \$from_code to \$to_code on \$departureDate")
    def selectATripOnHomePage(String carrier, String from, String to, String date) {
        commonSteps.setupOneWayCarrierFlight(carrier)
        homePage.clickOneWay()
        itineraryData.departureStation = from
        itineraryData.arrivalStation = to
        homePage.fillInAirports(from, to)
        Date departureDate = null
        if ("today".equalsIgnoreCase(date)) {
            departureDate = new Date()
        } else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy")
            DateTime dt = formatter.parseDateTime(date)
            departureDate = dt.toDate()
        }
        homePage.enterDepartureDate(departureDate)
        homePage.submit();
    }

    @When("On home page I select one-way \$carrier trip from \$from_code to \$to_code \$days day from today")
    def selectATripInTheFutureOnHomePage(String carrier, String from, String to, String days) {
        commonSteps.setupOneWayCarrierFlight(carrier)
        homePage.clickOneWay()
        itineraryData.departureStation = from
        itineraryData.arrivalStation = to
        homePage.fillInAirports(from, to)
        Date departureDate = new DateTime().plusDays(Integer.parseInt(days)).toDate();
        homePage(departureDate)
        homePage.submit();
    }

    @Then("I verify that the data is retained in Air tab")
    def verifyDataRetentionForAirTab() {
        homePage.verifyDataRetentionForAirTab()
    }

    @Then("I verify that the data is retained in Hotel tab")
    def verifyDataRetentionForHotelTab() {
        homePage.verifyDataRetentionForHotelTab()
    }

    @Then("I verify that the data is retained in Car tab")
    def verifyDataRetentionForCarTab() {
        homePage.verifyDataRetentionForCarTab()
    }

    @Then("I verify that the data is retained in Vacation tab")
    def verifyDataRetentionForVacationTab() {
        homePage.verifyDataRetentionForVacationsTab()
    }

    @When("I click on the Vacations tab")
    def selectVacationTab() {
        homePage.clickOnVacationTab()
    }

    @Given("I click on the Car tab in widget")
    @Alias("I have clicked on the Car tab in widget")
    def clickOnCarTabInWidget() {
        homePage.clickCarTabWidget()
    }

    @When("I click on the Vacations tab in widget")
    def clickOnVacationsTabInWidget() {
        homePage.clickVacationsTabWidget()
    }

    @When("I clear the dates on the booking widget")
    def clearDates() {
        homePage.clearDates()
    }

    @Given("I navigate to the book a flight page")
    @When("I click on the search button in air widget")
    def clickOnAirSearchButtonWidget() {
        homePage.clickAirSearchButtonWidget()
        searchFlightsPage.verifyPage()
        if (!DynaStubsIntegration.useDynaStubs()) {
            if (flow.isRapidRewards || flow.isCustomer) {
                verifyLoggedInInformation()
            }
        }
    }

    @When("I click on the view all travel tools link on the Home Page")
    def openTravelToolsPageByViewAllLink() {
        travelToolsDropDownMenu.clickViewAllTravelToolsLink()
    }

    @When("I click on the Where we fly link on the Home Page")
    def clickOnHomepageWhereWeFlyLink() {
        homePage.clickWhereWeFlyLink()
    }

    @When("I click on the Travel Tools MySouthwest Account dropdown link")
    def clickOnTravelToolsMySouthwestAccountLink() {
        travelToolsDropDownMenu.clickMySouthwestAccountLink()
    }

    @When("I click on the southwestgiftcard option from the Global Nav Header")
    def goToGiftCardLandingPageFromAirLink() {
        globalNavigationHeader.openAirSubMenu().clickOnSouthwestGiftCardLink()
    }
    @When("I click on the southwestgiftcard link from the Global Nav Footer")
    def goToGiftCardLandingPageFromFooterLink() {
        if(flow.purchaseGiftCardData==null){
            PurchaseGiftCardData purchaseGiftCardData = PurchaseGiftCardData.createBillingGiftCardData()
            if (!DynaStubsIntegration.useDynaStubs()){
                purchaseGiftCardData.setCreditCard(data.getRandomCreditCardOnlyVisa())
            }
            flow.purchaseGiftCardData = purchaseGiftCardData
        }

        globalNavigationFooter.verifyPage()
        globalNavigationFooter.clickOnSouthwestGiftCardLink()
        giftCardCheckBalancePage.verifyPage()
    }

    @When("I click on the southwestgiftcard Balance link on the Home Page")
    def openGiftCardBalancePageBySouthwestGiftcardLink() {
        travelToolsDropDownMenu.clickSouthwestGiftCardBalanceLink()
    }

    @When("I click on the Travel fund Balance link on the Home Page")
    def openTravelFundBalanceLink() {
        travelToolsDropDownMenu.clickSouthwestTravelFundBalancelink()
    }

    @When("I search for a flight from Home Page")
    void clickSearchButton() {
        homePage.submit()
    }

    @When('I access the cancel air reservation option')
    def goToCancelReservationPageFromAirLink(){
        openHomePage()
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnManageAirReservationLink()
            viewReservationPage.clickCancelReservationBreadcrumb()
        } else {
            globalNavigationHeader.openAirSubMenu().clickOnCancelReservationLink()
        }
        cancelAirReservationPage.verifyPage()
    }

    @Given("I am at the View/Share Itinerary Page")
    @When("I am at the View/Share Itinerary Page")
    def goToViewItinerary() {
        globalNavigationHeader.clickOnAirLinkMenu()
        searchFlightsPage.clickManageReservationLink()
    }

    @When("I click on Contact Us link")
    def clickOnContactUsLink(){
        homePage.clickOnContactUsLink()
    }

    @When("I click on the My Account link in the footer")
    def clickOnMyAccountLinkInFooter(){
        homePage.clickOnMyAccountLinkInFooter()
    }

    @When("I click on the My Account Login link in the footer")
    def clickOnMyAccountLoginLinkInFooter(){
        homePage.clickOnMyAccountLoginLinkInFooter()
    }

    @When("I click on Book a Flight link")
    def clickOnBookFlightLink(){
        homePage.clickOnBookFlightLink()
    }

    @When("I click on the language switcher link")
    def clickOnLanguageSwitcherLink(){
        homePage.clickOnLanguageSwitcherLink()
    }

    @When("I want to see the Origin and Destination as a combo by pressing a key combination")
    def sendHotKeysCtrlAltA() {
        homePage.sendHotKeys()
    }

    @When("I select Supplier Information")
    def clickOnSupplierInformationLink(){
        globalNavigationFooter.clickOnSupplierInformationLink()
     }

    @When("I select Southwest Merchandise under the Southwest Products section on the page footer")
    def clickSouthWestMerchandiseLink() {
        globalNavigationFooter.clickSouthWestMerchandiseLink()
    }

   @When("I select Careers")
    def clickOnCareersLink() {
        globalNavigationFooter.clickOnCareerLink()
    }

    @When("I select Why Fly Link")
    def clickOnWhyFlyLink() {
        globalNavigationFooter.clickOnWhyFlyLink()
    }

    @When("I select International Travel")
    def clickOnInternationalTravelLink() {
        globalNavigationHeader.clickOnInternationalTravelLink()
    }

    @When("I click on Lost and Found Link on the page footer")
    def clickOnLostAndFoundLink() {
        globalNavigationFooter.clickOnLostAndFoundLink()
    }

    @When("I click the Save Flight button in Home Page")
    def clickOnSaveFlightButton() {
        shoppingCart.clickSaveFlightButton()
    }

   @When("I search for a flight from Home Page without departure and arrival")
    def clickSearchButtonWithoutVerify() {
        homePage.submit(false)
    }

    @When("I select southwestgiftcard in the page header")
    def clickSouthwestGiftCardLink() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnSouthwestGiftCardLink()
        } else {
            homePage.clickOnSouthwestGiftCardLink()
        }
        homePage.checkSomethingServed()
    }

    @When("I click on Business Select option on the page footer")
    def clickOnBusinessSelect(){
        homePage.clickBusinessSelect()
    }

    @Given("I logout from account bar")
    @When("I click on the logout link")
    def clickLogoutLink(){
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.clickOnLogoutLink()
        } else {
            homePage.clickOnLogoutLink()
        }

        flow.isLoggedIn = false
        flow.isRapidRewards = false
        flow.isRapidRewardsAfterPurchase = true
    }

    @When("I select Advertise with Southwest link under the About Southwest section on the page footer")
    def clickOnAdvertiseWithSouthwestLinkFooter(){
        globalNavigationFooter.clickOnAdvertiseWithSouthwestLinkFooter()
    }

    @When("I open the Special Offers menu")
    def moveMouseOverOnSpecialOfferMenu(){
        globalNavigationHeader.openSpecialOffersMenu()
    }

    @When("I open the Vacations menu")
    def moveMouseOverOnVacationsMenu(){
        globalNavigationHeader.openVacationsMenu()
    }

    @When ("I click on the Why Fly Southwest? link under Flying Southwest from Global Nav")
    def goToGetAwayThroughFlyingSouthwestMenu(){
        globalNavigationHeader.openFlyingSouthwestSubMenu().clickOnWhyFlySouthwestLink()
    }

    @When("I click on Search by Map link in the Home Air Booking Widget")
    def clickSearchByMapLink(){
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnSearchByMap()
        } else {
            homePage.clickSearchByMapLink()
        }
    }

    @When("I open the Car menu")
    def moveMouseOverOnCarMenu(){
        globalNavigationHeader.openCarSubMenu()
    }

    @When("I select Special Assistance under the Customer Service section on the page footer")
    def clickOnSpecialAssistanceLink() {
        globalNavigationFooter.clickOnSpecialAssistanceLink()
    }

    @When("I click on check-in in global navigation")
    def clickOnCheckInGlobalNav() {
        if (ToggleGlobalNav.isOn()) {
            globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
            globalNavigationHeader.clickOnCheckinFlightLink()
        } else {
            globalNavigationHeader.openAirSubMenu().clickOnCheckInLink()
        }
    }

    //new header
    @When("I click on the new header menu Plan a Trip part one links")
    def openNewHeaderMenuPlanATripPartOne() {
        globalNavigationHeader.openNewHeaderMenuPlanATripPartOne()
    }

    @When("I click on the new header menu Plan a Trip part two links")
    def openNewHeaderMenuPlanATripPartTwo() {
        globalNavigationHeader.openNewHeaderMenuPlanATripPartTwo()
    }

    @When("I click on the new header menu Special Offers links")
    def openNewHeaderMenuSpecialOffers() {
        globalNavigationHeader.openNewHeaderMenuSpecialOffers()
    }

    @When("I click on the new header menu Rapid Rewards links")
    def openNewHeaderMenuRapidRewards() {
        globalNavigationHeader.openNewHeaderMenuRapidRewards()
    }

    //new footer
    @When("I click on the new footer contacts links")
    def clickNewFooterContactsLinks() {
        globalNavigationFooter.clickNewFooterContactsLinks()
    }

    @When("I click on the new footer part one general links")
    def clickNewFooterPartOneGeneralLinks() {
        globalNavigationFooter.clickNewFooterPartOneGeneralLinks()
    }

    @When("I click on the new footer part two general links")
    def clickNewFooterPartTwoGeneralLinks() {
        globalNavigationFooter.clickNewFooterPartTwoGeneralLinks()
    }

    @When("I click on the Login dropdown link")
    void openLoginDropdown() {
        globalNavigationHeader.openLoginDropdown()
    }

    @When("I login as a RRMember with username and password")
    void loggedInAsRapidRewardsMemberWithUserNameAndPassword() {
        RRUser rrUser = flow.getRrUser()
        globalNavigationHeader.logInAsRapidRewardsMember(rrUser)
    }



    private void verifyLoggedInInformation() {
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.with {
                verifyRRGreetingAndName()
                verifyUserLoggedIn()
                if (flow.isRapidRewards && flow.userLoggedInRapidRewardsAccountType.equals("A-List")) {
                    verifyTierStatusFlagInHotState(flow.userLoggedInRapidRewardsAccountType)
                }
            }
        } else {
            rapidRewardsAccountBar.with {
                verifyRRGreeting()
                verifyLogOutLink()
                verifyRRName()
                verifyMyAccountLink()
                if (flow.isRapidRewards) {
                    verifyTier()
                    verifyRRacountNumber()
                }
            }
        }
    }
}
