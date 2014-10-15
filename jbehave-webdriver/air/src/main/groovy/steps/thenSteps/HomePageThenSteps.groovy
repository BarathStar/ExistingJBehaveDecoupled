package steps.thenSteps

import org.jbehave.core.annotations.Then
import pages.HomePage
import pages.elements.FlightSearchForm
import pages.elements.GlobalNavigationHeader
import pages.elements.GlobalNavigationFooter
import com.swacorp.dotcom.webscenarios.air.RRUser
import state.Flow
import steps.conditional.ToggleGlobalNav
import steps.conditional.ToggleHomepage2

class HomePageThenSteps {

    HomePage homePage
    FlightSearchForm flightSearchForm
    Flow flow;
    GlobalNavigationHeader globalNavigationHeader
    GlobalNavigationFooter globalNavigationFooter

    @Then("I should stay on the home page")
    def onHomePage() {
        homePage.onHomePage()
    }

    @Then("the station selections on home page are prepopulated")
    def stationSelectionsOnHomePageArePrePopulated() {
        flightSearchForm.verifyStationsArePrePopulated()
    }

    @Then("Verify that I am on the Homepage")
    void shouldBeOnTheHomepage() {
        homePage.verifyCurrentPageLocation()
    }

    @Then("I should not be redirected from the homepage")
    void shouldNotBeRedirectedFromTheHomePage() {
        homePage.onHomePage()
    }

    @Then("I should see a combo box on Origin/Destination fields")
    def shouldSeeComboBoxOriginDestination() {
        homePage.verifyComboBoxOriginDestination()
    }

    @Then("I verify that I am logged in")
    void shouldBeLoggedIn(){
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.verifyUserLoggedIn()
        } else {
            homePage.verifyLoggedIn()
        }
    }

    @Then("I verify that I am logged out")
    void shouldBeLoggedOut(){
        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.verifyUserLoggedOut()
        } else {
            homePage.verifyLoggedOut()
        }
    }

	@Then("I should see the copyright year on the southwest home page footer")
	def verifyCopyrightYear() {
		homePage.verifyCopyrightYear()
	}

    @Then("I shouldn't see the account number completed")
    def verifyAutoCompleteUserName() {
        RRUser user = flow.getRrUser();

        if (ToggleHomepage2.isOn()) {
            globalNavigationHeader.verifyIfAccountNumberIsCompleted(user.getNumber().substring(0,5))

        } else {
            homePage.verifyIfAccountNumberIsCompleted(user.getNumber().substring(0,5))
        }
    }

    @Then('I see the Special Assistance page')
    def verifySpecialAssistancePage(){
        globalNavigationFooter.verifySpecialAssistancePage();
    }
    
    @Then('I see the Infant Flying page')
    def verifyInfantFlyingPage(){
        if (ToggleGlobalNav.isOn()) {
            globalNavigationFooter.verifyInfantFlyingPage()
        } else {
            globalNavigationHeader.verifyInfantFlyingPage()
        }
    }

    //new header
    @Then('I see valid pages with no oops from menu Plan a Trip part one')
    def clickNewHeaderMenuPlanATripPartOneLinks(){
        globalNavigationHeader.clickNewHeaderMenuPlanATripPartOneLinks();
    }

    @Then('I see valid pages with no oops from menu Plan a Trip part two')
    def clickNewHeaderMenuPlanATripPartTwoLinks(){
        globalNavigationHeader.clickNewHeaderMenuPlanATripPartTwoLinks();
    }

    @Then('I see valid pages with no oops from menu Special Offers')
    def clickNewHeaderMenuSpecialOffersLinks(){
        globalNavigationHeader.clickNewHeaderMenuSpecialOffersLinks();
    }

    @Then('I see valid pages with no oops from menu Rapid Rewards')
    def clickNewHeaderMenuRapidRewardsLinks(){
        globalNavigationHeader.clickNewHeaderMenuRapidRewardsLinks()
    }

    //new footer
    @Then('I see valid pages with no oops on new footer contacts links')
    def validateNoNewFooterContactsLinksErrors() {
        globalNavigationFooter.validateNoNewFooterContactsLinksErrors()
    }

    @Then('I see valid pages with no oops on new footer part one general links')
    def validateNoNewFooterPartOneGeneralLinksErrors() {
        globalNavigationFooter.validateNoNewFooterPartOneGeneralLinksErrors()
    }

    @Then('I see valid pages with no oops on new footer part two general links')
    def validateNoNewFooterPartTwoGeneralLinksErrors() {
        globalNavigationFooter.validateNoNewFooterPartTwoGeneralLinksErrors()
    }

    @Then("I verify the error message in the login dropdown that says \"\$message\"")
    def verifyLogInErrorMessageInTheNewHeader(String message) {
        globalNavigationHeader.verifyLogInErrorMessageInTheNewHeader(message)
    }

    @Then("I verify that tier status is \"\$tier\"")
    def verifyTierStatus(String tier) {
        globalNavigationHeader.verifyTierStatusFlagInHotState(tier)
    }

    @Then("I verify the Air tab")
    def verifyAirTab() {
        homePage.clickFlightTab()
        homePage.verifyBookingWidgetForAir()
    }

    @Then("I verify the Hotel tab")
    def verifyHotelTab() {
        homePage.clickHotelTab()
        homePage.verifyBookingWidgetForHotel()
    }

    @Then("I verify the Car tab")
    def verifyCarTab() {
        homePage.clickCarTab()
        homePage.verifyBookingWidgetForCar()
    }

    @Then("I verify the Vacations tab")
    def verifyVacationsTab() {
        homePage.clickVacationsTab()
        homePage.verifyBookingWidgetForVacations()
    }
}