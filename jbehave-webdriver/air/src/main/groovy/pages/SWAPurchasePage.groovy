package pages

import com.swacorp.dotcom.webscenarios.air.Data
import pages.elements.PassengerInfo
import pages.elements.RapidRewardsAccountBar
import pages.mixins.PurchaseVerifications
import pages.AccompanyingAdultPage
import pages.ConfirmationPage
import pages.DisabilitiesPage
import pages.Itinerary
import pages.RepricePage
import pages.SearchFlightsPage
import pages.SelectFlightsPage
import pages.UnaccompaniedMinorGuardianPage
import pages.UnaccompaniedMinorPurchasePage
import pages.YoungTravelerGuardianPage
import pages.elements.CreditCardSubForm
import pages.elements.FeeCalculator
import pages.elements.FlightSearchForm
import pages.elements.PurchaseSubForm
import pages.elements.ShoppingCart
import state.Flow
import state.PassengerData
import state.ScenarioState
import util.CustomerInfoData
import util.ItineraryData
import util.PricePageData
import util.PurchasePageData
import util.RRContactInformation
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import util.TripManagement


class SWAPurchasePage extends BasePage
{

    public static final String CSS_CLASS_FOR_NON_ERROR_OOPS = "errors_no_image"
    private static String CREDIT_CARD_DINERS = "DINERS_CLUB"
    private static String CREDIT_CARD_VISA = "VISA"
    private static String CREDIT_CARD_UATP = "UATP"
    private static final By DISABILITY_BUTTON = By.id("disability_button0")

    Flow flow

    pages.PurchasePage purchasePage
    ConfirmationPage confirmationPage
    Data data
    SearchFlightsPage searchFlightsPage
    SelectFlightsPage selectFlightsPage
    pages.PricePage pricePage
    RepricePage repricePage
    FlightSearchForm flightSearchForm
    CreditCardSubForm creditCardSubForm
    ItineraryData itineraryData
    Itinerary itinerary
    ShoppingCart shoppingCart
    PassengerData passengerData
    ScenarioState scenarioState
    PricePageData pricePageData
    PurchasePageData purchasePageData
    CustomerInfoData customerInfoData
    RRContactInformation rrContactInformation
    RapidRewardsAccountBar rapidRewardsAccountBar
    PassengerInfo passengerInfo
    DisabilitiesPage  disabilitiesPage
    PurchaseVerifications purchaseVerifications
    PayPalPage  payPalPage
    SWADisabilitiesPage swaDisabilitiesPage



    public SWAPurchasePage(WebDriverProvider driverProvider)
    {
        super(driverProvider,"");

    }

    def purchaseTicket()
  {
      boolean clickSubmit = true
          purchasePage.with
                  {
                      verifyBasicPage()
                      verifyFormOfPaymentHeader()
                      if (flow.isLoggedIn && flow.isRapidRewards)
                      {
                          verifyRRNumber()
                      }
                      flow.tripName = TripManagement.NO_TRIP
                      if (itineraryData.luvVoucher)
                      {
                          purchaseVerifications.clickApplyTravelFunds()
                          fillVoucherAndApply()
                          verifyLuvVoucherApplied()
                      }
                      if (flow.useSavedCreditCard)
                      {
                          verifyPrePopulatedPassengerInfo()
                          verifySavedCreditCardSelectedByDefault()
                          verifySavedCreditCardName()
                          verifyCCPaypalOptionsArePresent()
                      } else {
                          verifyCreditCardSelectedByDefault()
                      }
                      if (itineraryData.giftCard)
                      {
                          purchaseVerifications.clickApplyTravelFunds()
                          fillGiftCardAndApply()
                          verifyTravelFundsRoutingSubheader()
                          verifyTotalAmountForNewFlight()
                          verifyAirCharges()
                          verifyTravelFundsFlyoutData()
                          verifyTravelFundsTable()
                          verifyTravelFundsTotalDueNow()
                          verifyFundsApplied()
                          verifyTotalDueNow()
                      }
                  }
          rapidRewardsAccountBar.with
                  {
                      if (flow.isLoggedIn && (flow.isRapidRewards || flow.isCustomer))
                      {
                          verifyRRGreeting()
                          verifyLogOutLink()
                          verifyRRName()
                          verifyMyAccountLink()
                          if (flow.isRapidRewards)
                          {
                              verifyTier()
                              verifyRRTripPoints()
                              verifyRRacountNumber()
                          }
                      }
                  }
          if (itineraryData.hasTravelFunds)
          {
              passengerInfo.fillForm()
              purchasePage.with
                      {
                          purchaseVerifications.applyTravelFunds()
                          verifyTravelFundsRoutingSubheader()
                          verifyTotalAmountForNewFlight()
                          verifyAirChargesApplied()
                          verifyTravelFundsTableForCancelledPNR()
                          verifyTravelFundsFlyoutDataforCancelledPNR()
                          verifyTravelFundsAppliedToday()
                          verifyTotalAndExchangedTicket()
                          verifyAirTotalDueNow()
                          verifyCreditCardSelectedByDefault()
                          verifyPayPalIsEnabled()
                          verifySummaryFundsApplied()
                          verifyTotalDueNowLink()
                          verifySummaryTotalCostDue()
                      }
              customerInfoData.formOfPayment = null
          }
          purchasePage.with
                  {
                      verifyCreditCardSelectedByDefault()
                      verifyAddEditDisabilitiesButtonIsPresent()
                      fillInAllInformation()
                      setValue("js-preferred-method-of-contact--contact-method", "Email")
                      setValue("js-email", "tester@wnco.com")
                  }
          if (itineraryData.hasDisabilities)
          {
              purchasePage.addDisabilities()
              shoppingCart.with
                      {
                          verifyShoppingCartIsExpanded()
                          verifyAirWidgetIsCollapsed()
                          modifyAndRemoveLinks()
                          expandAirShoppingCart()
                          verifyFlightNumber()
                          verifyFareType()
                          verifyDepartureAndArrivalCities()
                          verifyDepartureAndArrivalTime()
                          verifyTotalCostBreakdown()
                          verifyTripTotal()
                          verifyDate()
                      }
              disabilitiesPage.with
                      {
                          addAssistanceWithWheelchair()
                          checkBlindOrHaveLowVision()
                          verifyBasicPage()
                          verifyPageHeader()
                          verifyContinueButton()
                          verifyCancelButton()
                          submit()
                      }
              purchasePage.with
                      {
                          verifyBasicPage()
                          verifyPaxDetails()
                          verifySelectedDisabilities()
                      }
              customerInfoData.formOfPayment = null
              creditCardSubForm.fillForm()
          }
          shoppingCart.with {
              verifyShoppingCartIsExpanded()
              verifyAirWidgetIsCollapsed()
              modifyAndRemoveLinks()
              verifyTotalAndTotalTripOnShoppingCartAreEqual()
              expandAirShoppingCart()
              verifyFlightNumber()
              verifySaveTripButton()
              verifyDepartureAndArrivalCities()
              verifyDepartureAndArrivalTime()
              verifyTotalCostBreakdown()
              verifyTripTotal()
              verifyGovtTaxesFeesLinkIsPresent()
              verifyFareType()
              verifyDate()
              verifyOutboundAndInboundTotals()
          }
          purchasePage.with {
              verifyTripTotalAndPurchaseTripTotalAreEqual(pricePageData.tripTotal)
              verifyTripTotalAndPurchaseTripTotalAreEqual(shoppingCart.tripTotal())
              verifyTravelersInfoIsDisplayed()
              if (customerInfoData.formOfPayment == "PayPal") {
                  verifyPayPalIsSelected()
                  verifyCreditCardDataNotDisplayed()
                  verifyPayPalTripTotal()
              }
              if (clickSubmit) {
                  clickVisibleSubmit(flow.isUM)
              }
          }

      if(customerInfoData.formOfPayment.equals("PayPal"))
      {
          completePayPalPurchase()

          }
      }

    void completePayPalPurchase()
    {
        payPalPage.with
        {
            verifyTotal()
            enterPayPalCredentialsOnPurchaseTransitionPage()
            verifyTotal()
            clickPayNow()
        }
       }

    def addCWCPOCOptions(String paxIdx)
    {
        waitForElement(By.id("disability_button"+paxIdx)).click()
        swaDisabilitiesPage.checkManualWheelChair()
        swaDisabilitiesPage.checkBringingOxygenConcentrator()
        swaDisabilitiesPage.submit()

    }

}