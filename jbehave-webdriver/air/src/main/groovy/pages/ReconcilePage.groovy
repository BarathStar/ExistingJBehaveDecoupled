package pages

import com.swacorp.dotcom.webscenarios.air.Data
import domain.Passenger
import fixture.stubs.DynaStubsIntegration
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import pages.elements.ExchangedTicketFlyout
import pages.elements.PurchaseSubForm
import util.CustomerInfoData
import util.ItineraryData
import util.PageName
import util.PricePageData
import util.PurchasePageData
import util.RRContactInformation
import util.TripManagement
import static util.Locators.BREADCRUMB_IDS
import domain.AirReservation
import state.ScenarioState
import org.openqa.selenium.WebElement
import state.Flow
import java.text.DecimalFormat
import util.SelectFlightsPageData
import pages.mixins.*
import steps.thenSteps.MyAccountThenSteps
import steps.conditional.TogglePreferenceCenter

@Mixin(PurchaseVerifications.class)
public class ReconcilePage extends BasePage {

    private static final By NAME_THIS_TRIP = By.id("nameThisTrip")
    static final private String OPT_INTO_NEW_TRIP_ID = "optIntoNewTrip"
    private static final By EXISTING_TRIP_NAME = By.id("existingTripName")
    private static final By TRIP_NAME_FIELD_ID = By.id("trip_name_fields")
    private static final By EMAIL_CONFIRMATION_ADDRESS = By.id("emailConfirmationAddress")
    private static final By SUBMIT_BUTTON = By.id("visibleSubmitButton")
    private static final By TRIP_NAME_FIELD = By.id("tripName")
    private static final By PURCHASE_SUMMARY_TOTAL_COST = By.id("purchaseSummaryTotalCost")
    private static final By APPLY_TRAVEL_FUNDS_BUTTON = By.id("applyTravelFunds")
    private static final By APPLY_TRAVEL_FUNDS_MSG = By.cssSelector("p.applyTravelFundsMessage")
    private static final By RED_ASTERISK_CONTACT_INFO = By.xpath("//div[@id='enterContactInfo']/div/label/span")
    private static final By TABLE_TRAVEL_FOUND = By.id("appliedFundsTableOther")
    private static final By REQUEST_REFUND = By.id("refundRequest")
    private static final By REFUNDABLE_AMOUNT = By.id("refundableAmount")
    private static final By AIR_CHARGES = By.id("tripBalanceWithoutExternalChangeFees")
    private static final By SHOPPING_CART = By.cssSelector(".global_account_bar_header_active span.global_account_bar_header_toggle_open")
    private static final By PASSENGER_INFORMATION = By.cssSelector(".currentPassengerList td")
    private static final By FOOTNOTE = By.id("whoIsFlyingFootnote")
    private static final By ROUTING_HEADER = By.className("tripRoute")
    private static final By NEW_FLIGHT_TOTAL = By.className("total")
    private static final By TOTAL_DUE_NOW = By.id("applyTravelFundsTotalAddonsCost")
    private static final By FUNDS_APPLIED = By.id("purchaseSummaryTotalFundsApplied")
    private static final By TRIP_TOTAL_POINTS = By.id("purchaseSummaryTotalPoints")
    private static final By TRAVEL_FUNDS_CONTAINER = By.className("air")
    private static final By TRAVEL_FUNDS_INFORMATION = By.cssSelector("td p")
    private static final By TRAVEL_FUNDS_FLYOUT = By.cssSelector(".air .overlay-trigger")
    private static final By TRAVEL_FUNDS_FLYOUT_INFORMATION = By.className("article")
    private static final By REFUNDABLE_HOLD = By.id("refundableHold")
    private static final By NON_REFUNDABLE_COST = By.className("cost")
    private static final By HOLD_FUTURE_USE_TEXT = By.cssSelector(".refundableOption li")
    ScenarioState scenarioState
    RRContactInformation rrAccountData
    ItineraryData itineraryData
    PricePageData pricePageData
    PurchasePageData purchasePageData
    CustomerInfoData customerInfoData
    Data data
    Flow flow
    SelectFlightsPageData selectFlightsPageData

    PurchaseSubForm[] subForms;

    def ReconcilePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
    }

    String getRelativePath() {
        return "/flight/reconcile.html"
    }

    def verifyPage() {
        super.verifyPage()
        if (!flow.isFaultInjected) {
            verifyPageBreadcrumb(BREADCRUMB_IDS["ReconcilePage"])
        }
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.RECONCILE_PAGE)
    }

    def submit() {
        String title = getTitle()
        waitForElement(SUBMIT_BUTTON).click()
        waitForPageTitleToChangeOrOops(title)
    }

    def submitAndWaitForOops() {
        waitForElement(SUBMIT_BUTTON).click()
        waitForOops()
    }

    def enterEmail(String emailAddress) {
        waitForElement(EMAIL_CONFIRMATION_ADDRESS).sendKeys(emailAddress)
    }

    def fillInPaymentInformation() {
        fillInPaymentInformationWithoutEmail()
        if (!flow.isLoggedIn) {
            enterEmail("tester@wnco.com")
        }
    }

    def fillInPaymentInformationWithoutEmail() {
        if (waitForElement(PURCHASE_SUMMARY_TOTAL_COST).text.contains("\$0.00") == false) {
            subForms.each {it.fillForm()}
        }
    }

    def verifyAirTranTravelFundMsg() {
        waitForElement(APPLY_TRAVEL_FUNDS_MSG).getText().contains("AirTran travel credits cannot be used on southwest.com")
    }

    def fillInPassengerContactInformation() {
        if(TogglePreferenceCenter.isOn()) {
            chooseInDropDownByValue("js-preferred-method-of-contact--contact-method", "Text")
            waitForElement(By.id("js-us-phone-area-code")).sendKeys DELETE_EXISTING + "972"
            waitForElement(By.id("js-us-phone-prefix")).sendKeys DELETE_EXISTING + "312"
            waitForElement(By.id("js-us-phone-line-number")).sendKeys DELETE_EXISTING + "1111"
        } else {
            chooseInDropDownByValue("contactMethod", "Text")
            waitForElement(By.id("contactAreaCode")).sendKeys DELETE_EXISTING + "972"
            waitForElement(By.id("contactPrefix")).sendKeys DELETE_EXISTING + "312"
            waitForElement(By.id("usPhoneLineNumber")).sendKeys DELETE_EXISTING + "1111"
        }

    }

    def enterReconcilePageInformationAndSubmit() {
        verifyPage()
        enterReconcilePageInformationAndSubmitWithoutVerify()
    }

    def enterReconcilePageInformationAndSubmitWithoutVerify() {
        fillInPassengerContactInformation()
        fillInPaymentInformation()
        submit()
    }

    def enterReconcilePageInformationWithoutEmailAndSubmit() {
        verifyPage()
        fillInPassengerContactInformation()
        fillInPaymentInformationWithoutEmail()
        submitAndWaitForOops()
    }

    def selectRequestRefundOfRefundableBalance() {
        waitForElement(REQUEST_REFUND).click()
    }

    def String getRefundableAmount() {
        return waitForElement(REFUNDABLE_AMOUNT).getText()
    }

    void verifyOopsMessageForEmailIsPresented() {
        def backButtonOopsMessage = getExpectedOopsMessage()
        backButtonOopsMessage.shouldContain "The confirmation email address was not entered.", "Oops message does not contain the proper wording"
    }

    void verifyNameThisTripIsChecked() {
        isCheckboxChecked(NAME_THIS_TRIP).shouldBe true, "'Name This Trip' should be selected"
    }

    void verifyAppliedFunds() {
        WebElement table = waitForElement(TABLE_TRAVEL_FOUND)
        BigDecimal newPrice = table.findElement(AIR_CHARGES).getText().minus('$').toBigDecimal()
        BigDecimal totalPrice = table.findElement(By.className("subheader")).findElement(By.className("total")).getText().minus('$').toBigDecimal()
        BigDecimal travelFound = totalPrice.subtract(newPrice)
        AirReservation reservation = scenarioState.getLastAirReservation()
        travelFound.shouldBe reservation.getPrice(), "The applied funds are not the correct ones, this is $travelFound and should be ${reservation.price}"
    }

    void verifyNewTripNameIsChecked() {
        isCheckboxChecked(By.id(OPT_INTO_NEW_TRIP_ID)).shouldBe true, "'New Trip Name' radio button should be not selected"
    }

    void verifyExistingTripsNotPresent() {
        isElementDisplayed(EXISTING_TRIP_NAME).shouldBe false, "'Existing Trip Name' drop down should not be displayed"
    }

    void verifyExistingTripsIsPresent() {
        isElementDisplayed(EXISTING_TRIP_NAME).shouldBe true, "'Existing Trip Name' drop down should be displayed"
    }

    boolean isDefaultTripName() {
        return waitForElement(TRIP_NAME_FIELD).getAttribute("value").matches(TripManagement.DEFAULT_TRIP_NAME)
    }

    def verifyTripNameIsFormattedByDefault() {
        isDefaultTripName().shouldBe true, "Default trip name is not correctly formatted"
    }

    def verifyTripNameSectionNotDisplayed() {
        isElementDisplayed(TRIP_NAME_FIELD_ID).shouldBe false, "The Trip name section should not be visible"
    }

    def verifyTripNameSectionIsDisplayed() {
        isElementDisplayed(TRIP_NAME_FIELD_ID).shouldBe true, "The Trip name section should be visible"
    }

    def verifyRedAsteriskContactInfo() {
        verifyElementNotPresent("Red asterisk inside contact info area", RED_ASTERISK_CONTACT_INFO)
    }

    void verifyNameThisTripIsPresent() {
        verifyElementPresent("Name This Trip", NAME_THIS_TRIP)
    }

    void verifyExistingTripNameDisabled() {
        waitForElement(EXISTING_TRIP_NAME).getAttribute("disabled").shouldBe "true", "The 'Select from Existing Trip' drop down list is not greyed out"
    }

    def verifyPassengerInfoPopulated() {
        List<WebElement> passengerInfo = waitForElements(PASSENGER_INFORMATION)
        String rrNumber
        if(flow.isRapidRewards && !DynaStubsIntegration.useDynaStubs()) {
            rrNumber = rrAccountData.accountNumber
        } else {
            rrNumber = "None Entered"
        }
        passengerInfo[0].text.shouldBe retrievePassangerName().toUpperCase(), "Passenger name was not correct"
        passengerInfo[1].text.shouldContain rrNumber, "Rapid rewards account number was not correct"
        passengerInfo[2].text.shouldContain "None Entered", "The disability assistance was not correct"
    }

    def verifyPassengerFootnote() {
        waitForElement(FOOTNOTE).text.shouldContain "* Passenger details may not be modified with the Change Itinerary feature"
    }

    def verifyTravelFundsAirCharges() {
        DecimalFormat airChargesFormat =  new DecimalFormat("0.00")
        airChargesFormat.format(waitForElement(AIR_CHARGES).text.minus('$').toBigDecimal()).shouldBe airChargesFormat.format(pricePageData.additionalAmountDue), "Travel funds air charges was not the additional amount from new price page"
    }

    def verifyTravelFundsRoutingSubHeader() {
        String header = waitForElement(ROUTING_HEADER).text
        header.shouldContain itineraryData.departureCity
        header.shouldContain itineraryData.arrivalCity
        header.shouldContain itineraryData.itineraryType
    }

    def verifyTravelFundsInformation() {
        List<WebElement> travelFunds = waitForElements(TRAVEL_FUNDS_CONTAINER)
        Date date = new Date()
        String expirationDate = date.plus(365).format("MM/dd/yyyy")
        List<WebElement> travelFundsInformation = travelFunds[0].findElements(TRAVEL_FUNDS_INFORMATION)
        String exchangeTicketText = travelFundsInformation[0].text
        String passengerName = travelFundsInformation[1].text
        String travelFundsText = travelFundsInformation[2].text
        String expirationDateText = travelFundsInformation[3].text
        String remainingFunds = travelFundsInformation[4].text
        BigDecimal fundsApplied = travelFundsInformation[10].text.replace('$','').toBigDecimal()
        BigDecimal flightAmount = scenarioState.getLastAirReservation().price
        exchangeTicketText.shouldBe "Exchanged Ticket", "Exchanged Ticket text was not present in the Travel Funds section"
        passengerName.shouldBe retrievePassangerName().toUpperCase(), "The name was not displayed correctly in the Travel Funds section"
        travelFundsText.shouldContain "Travel Fund", "Travel Fund text was not present in the Travel Funds section"
        travelFundsText.shouldContain scenarioState.lastAirReservation.adultPnr, "Previous PNR not present in the Travel Funds section of Reconcile page"
        expirationDateText.shouldContain "Expiration: " + expirationDate, "Expiration was not displayed correctly in the Travel Funds section"
        remainingFunds.shouldContain "Funds Remaining \$" + Math.abs(fundsApplied - flightAmount), "Remaining funds were incorrect in the Travel Funds section "

        if (itineraryData.hasTravelFunds) {
            travelFundsInformation = travelFunds[1].findElements(TRAVEL_FUNDS_INFORMATION)
            passengerName = travelFundsInformation[0].text
            travelFundsText = travelFundsInformation[1].text
            expirationDateText = travelFundsInformation[2].text
            remainingFunds = travelFundsInformation[3].text
            fundsApplied = travelFundsInformation[8].text.replace('$','').toBigDecimal()
            flightAmount = scenarioState.getFirstAirReservation().price
            passengerName.shouldBe retrievePassangerName().toUpperCase(), "The name was not displayed correctly in the Travel Funds section"
            travelFundsText.shouldContain "Travel Fund", "Travel Fund text was not present in the Travel Funds section"
            expirationDateText.shouldContain "Expiration: " + expirationDate, "Expiration was not displayed correctly in the Travel Funds section"
            remainingFunds.shouldContain "Funds Remaining \$" + (flightAmount - fundsApplied), "Remaining funds were incorrect in the Travel Funds section "
        }
    }

    def String retrievePassangerName() {
        String fullName
        if (flow.isRapidRewards) {
            fullName = rrAccountData.getFullName()
        } else {
            Passenger passenger = scenarioState.getLastAirReservation().getPassengers().get(0)
            fullName = passenger.firstName + " " + passenger.lastName
        }
        fullName
    }

    def verifyTotalAmountForNewFlight() {
        getTotalForNewFlight().shouldEqual pricePageData.tripTotal, "Total amount for new flight did not equal pending air from new price page"
    }

    def BigDecimal getTotalForNewFlight() {
        waitForElement(NEW_FLIGHT_TOTAL).text.replace(' ', '').replace('$', '').replace(',', '').toBigDecimal()
    }

    def verifyTravelFundsInformationFlyOut() {
        Date date = new Date()
        String expirationDate = date.plus(365).format("MM/dd/yyyy")
        List<WebElement> flightOutIcon = waitForElements(TRAVEL_FUNDS_FLYOUT)
        flightOutIcon[0].click()
        List<WebElement> travelFundsFlyOuts = waitForElements(TRAVEL_FUNDS_FLYOUT_INFORMATION)
        ExchangedTicketFlyout ticketFlyout = new ExchangedTicketFlyout(travelFundsFlyOuts[0])
        ticketFlyout.getTitle().shouldBe "Exchanged Ticket", "Exchanged Ticket was not displayed in the Travel Funds flyOut section"
        ticketFlyout.getTotalAmount().shouldEqual pricePageData.availableFunds, "Exchanged ticket amount was not equal as available funds o the new price page"
        ticketFlyout.getFlyoutSubtitle().shouldBe retrievePassangerName().toUpperCase(), "The name in flyout was not correct in the Travel Funds flyOut section"
        ticketFlyout.getTravelFund().shouldContain "Travel Fund", "Travel funds title was incorrect in the Travel Funds flyOut section"
        ticketFlyout.getTravelFund().shouldContain scenarioState.lastAirReservation.adultPnr, "Previous PNR not present in the Travel Funds flyOut section of Reconcile page"
        ticketFlyout.getExpiration().shouldBe "Expiration: " + expirationDate, "Expiration was not displayed correctly in the Travel Funds flyOut section"
        ticketFlyout.getTravelFundsAppliedTitle().shouldBe "Travel Funds Applied Today", "Travel funds applied was not displayed in the Travel Funds flyOut section"
        BigDecimal exchangedTicketAppliedTodayAmount = ticketFlyout.getTravelFundsAppliedAmount()
        purchasePageData.exchangeTicketFundsApplied = exchangedTicketAppliedTodayAmount
        ticketFlyout.getTravelFundsRemainingTitle().shouldBe "Travel Funds Remaining", "Travel funds remaining not displyed"
        BigDecimal exchangedTicketRemainingAmount = ticketFlyout.getTravelFundsRemainingAmount()
        (ticketFlyout.getTotalAmount() - ticketFlyout.getTravelFundsAppliedAmount()).shouldEqual exchangedTicketRemainingAmount,"Travel funds remaining amount was not correct in the Travel Funds flyOut section"
        purchasePageData.exchangeTicketFundsRemaining = exchangedTicketRemainingAmount
        exchangedTicketAppliedTodayAmount.shouldBe pricePageData.availableFunds - exchangedTicketRemainingAmount, "Travel funds applied amount was not displayed in the Travel Funds flyOut section"

        if (itineraryData.hasTravelFunds) {
            flightOutIcon[1].click()
            ticketFlyout = new ExchangedTicketFlyout(travelFundsFlyOuts[1])
            ticketFlyout.getTotalAmount().shouldEqual scenarioState.firstAirReservation.price, "Exchanged ticket amount was not equal as available funds o the new price page"
            ticketFlyout.getTitle().shouldBe retrievePassangerName().toUpperCase(), "The name in flyout was not correct in the Travel Funds flyOut section"
            ticketFlyout.getFlyoutSubtitle().shouldContain "Travel Fund", "Travel funds title was incorrect in the Travel Funds flyOut section"
            ticketFlyout.getExpiration(false).shouldBe "Expiration: " + expirationDate, "Expiration was not displayed correctly in the Travel Funds flyOut section"
            ticketFlyout.getTravelFundsAppliedTitle().shouldBe "Travel Funds Applied Today", "Travel funds applied was not displayed in the Travel Funds flyOut section"
            BigDecimal travelFundsAppliedTodayAmount = ticketFlyout.getTravelFundsAppliedAmount()
            BigDecimal travelFundsRemainingAmount = ticketFlyout.getTravelFundsRemainingAmount()
            purchasePageData.travelFundsApplied = travelFundsAppliedTodayAmount
            purchasePageData.totalTravelFundsApplied = travelFundsAppliedTodayAmount
            purchasePageData.travelFundsRemaining = travelFundsRemainingAmount
            travelFundsAppliedTodayAmount.shouldEqual scenarioState.firstAirReservation.price - purchasePageData.travelFundsRemaining, "Travel funds applied amount was not displayed in the Travel Funds flyOut section"
            ticketFlyout.getTravelFundsRemainingTitle().shouldBe "Travel Funds Remaining", "Travel funds remaining not displyed"
            (ticketFlyout.getTotalAmount() - ticketFlyout.getTravelFundsAppliedAmount()).shouldEqual travelFundsRemainingAmount,"Travel funds remaining amount was not correct in the Travel Funds flyOut section"
        }
    }

    def verifyPurchaseSummary() {
        BigDecimal totalDueNow = waitForElement(TOTAL_DUE_NOW).text.replace('$', '').replace(',', '').toBigDecimal()
        purchasePageData.totalDueNow = totalDueNow
        BigDecimal purchaseTotalCost = waitForElement(PURCHASE_SUMMARY_TOTAL_COST).text.replace('$', '').replace(',', '').toBigDecimal()
        BigDecimal fundsApplied = waitForElement(FUNDS_APPLIED).text.replace('$', '').replace(',', '').replace('(','').replace(')','').toBigDecimal()
        fundsApplied.shouldBe purchasePageData.totalTravelFundsApplied, "Reconcile Funds applied did not match Travel funds applied"
        purchaseTotalCost.shouldBe totalDueNow, "Additional amount due did not match total due now"
        purchaseTotalCost.shouldEqual pricePageData.tripTotal - fundsApplied, "Additional amount due was not correct in the reconcile page"
    }

    def verifyFundsInfo() {
        List<WebElement> travelFunds = waitForElements(TRAVEL_FUNDS_CONTAINER)
        List<WebElement> fundsInformation = travelFunds[0].findElements(TRAVEL_FUNDS_INFORMATION)
        BigDecimal exchangeTicketFundsApplied = fundsInformation[10].text.replace('$', '').toBigDecimal()
        exchangeTicketFundsApplied.shouldEqual purchasePageData.exchangeTicketFundsApplied, "Funds Applied was not equal to Travel Funds Applied Today from Exchanged TicketFlyout Details"
        BigDecimal totalAgainstExchangedTicket = fundsInformation[11].text.replace('$', '').replace('(', '').replace(')', '').toBigDecimal()
        totalAgainstExchangedTicket.shouldEqual exchangeTicketFundsApplied, "Total against Exchanged Ticket was not equal to travel funds Applied"

        if (itineraryData.hasTravelFunds) {
            fundsInformation = travelFunds[1].findElements(TRAVEL_FUNDS_INFORMATION)
            BigDecimal travelFundsApplied = fundsInformation[8].text.replace('$', '').toBigDecimal()
            travelFundsApplied.shouldEqual purchasePageData.travelFundsApplied, "Funds Applied was not equal to Travel Funds Applied Today from Exchanged TicketFlyout Details"
            BigDecimal totalAgainstTravelFunds = fundsInformation[10].text.replace('$', '').replace('(', '').replace(')', '').toBigDecimal()
            totalAgainstTravelFunds.shouldEqual travelFundsApplied, "Total against travel funds was not equal to travel funds Applied"
            pricePageData.additionalAmountDue -= exchangeTicketFundsApplied
        }
        purchasePageData.totalTravelFundsApplied += purchasePageData.exchangeTicketFundsApplied
        BigDecimal totalDueNow = waitForElement(TOTAL_DUE_NOW).text.replace('$', '').replace(',', '').replace('(','').replace(')','').toBigDecimal()
        (getTotalForNewFlight() - purchasePageData.totalTravelFundsApplied).shouldEqual totalDueNow, "Total Due Now was not equals to the difference between Total Amount for new flight and summation of all the applied funds"
    }

    def verifyPayment() {
        if (!pricePageData.additionalAmountDue == BigDecimal.ZERO) {
            List<WebElement> paymentMethods = waitForElements(By.cssSelector(".payment_pref_options div input"))
            paymentMethods.size().shouldEqual 1, "There was more than one payment method"
            paymentMethods[0].getAttribute("value").shouldBe "CREDIT_CARD", "The payment method was not Credit card"
            paymentMethods[0].getAttribute("checked").shouldBe "true"
        }
        customerInfoData.formOfPayment = null
    }

    def verifyTotalPoints() {
        waitForElement(TRIP_TOTAL_POINTS).text.replace('pts','').replace(',','').toBigDecimal().shouldEqual selectFlightsPageData.outboundFlightPoints, "Trip total in points was not equal to trip total from reprice page in the reconcile page"
    }

    def verifyTravelFundsCreditDue() {
        BigDecimal refundableAmount = waitForElement(REFUNDABLE_AMOUNT).text.replace("\$", "").replace("(","").replace(")","").toBigDecimal()
        Math.abs(refundableAmount).shouldBe Math.abs(pricePageData.additionalCreditDue), "Refundable amount did not match additional credit due in the reconcile page"
        waitForElement(REFUNDABLE_HOLD).getAttribute("checked").shouldBe "true", "Hold for future use was not selected by default in the reconcile page"
        waitForElement(REQUEST_REFUND).getAttribute("checked").shouldBe null, "Request a refund of the refundable balance was not present in the reconcile page"
        String nonRefundable = waitForElements(NON_REFUNDABLE_COST)[1].text
        nonRefundable.shouldBe "\$0.00", "Non refundable amount was not \$0.00 in the reconcile page"
        String holdForFutureUse = waitForElements(HOLD_FUTURE_USE_TEXT)[2].text
        holdForFutureUse.shouldBe "Hold for future use.", "Hold for future use was not present in the Non-refundable section in the reconcile page"
    }
    
    boolean verifyRefundableTravelFundsSectionPresent() {
        isElementPresent(REFUNDABLE_AMOUNT)
    }
}
