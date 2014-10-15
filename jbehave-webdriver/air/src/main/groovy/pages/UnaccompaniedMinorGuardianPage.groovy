/* Copyright (c) 2011, Southwest Airlines Co.  All rights reserved. */
package pages

import domain.Guardian
import org.jbehave.web.selenium.WebDriverProvider
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pages.elements.FeeCalculator
import pages.elements.GuardianForm
import static util.Locators.BREADCRUMB_IDS
import util.PageName
import state.Flow
import state.PassengerData

import util.SelectFlightsPageData
import util.PricePageData
import util.PurchasePageData

class UnaccompaniedMinorGuardianPage extends GuardianPage {

    private static final String WINDOWS_TITLE_MSG = 'Southwest Airlines - Unaccompanied Minor Parent/Guardian'

    private static final By TOTAL_UM_CHARGE_TEXT_CSS = By.cssSelector(".serviceChargeForUMTotal .serviceChargeForUMLabel")
    private static final By TOTAL_UM_CHARGE_CSS = By.cssSelector(".serviceChargeForUMTotal .serviceChargeForUMAmount")
    private static final By PREPOPULATE_CHECKBOX = By.id("contactInfoForReturningFlightCheckBox")
    private static final By SUBMIT_BUTTON = By.id("submitButton")
    private static final By PAGE_TITLE = By.cssSelector("#page_title strong")
    private static final By PAGE_SUBTITLE = By.cssSelector("#page_title .unaccompaniedMinorAdultInfo_pageSubTitle")
    private static final By UM_PASSENGER_FULLNAME = By.cssSelector(".serviceChargeForUMRow .serviceChargeForUMLabel")
    private static final By INDIVIDUAL_CHARGES = By.cssSelector(".serviceChargeForUMRow .serviceChargeForUMAmount")
    private static final By TOTAL_CHARGE = By.cssSelector(".serviceChargeForUMTotal .serviceChargeForUMAmount")
    private static final By PARENT_GUARDIAN_CONTACT_INFO = By.className("parentGuardianInfo")
    private static final By OUTBOUND_TIMES = By.cssSelector("#airItinerary0 .routingDetailsTimes")
    private static final By OUTBOUND_CITIES = By.cssSelector("#airItinerary0 .routingDetailsStops")
    private static final By OUTBOUND_FLIGHT_NUMBER = By.cssSelector("#airItinerary0 .flightNumber strong")
    private static final By OUTBOUND_TRAVEL_TIME = By.cssSelector("#airItinerary0 .travelFlightDuration")
    private static final By OUTBOUND_FARE_TYPE = By.cssSelector("#airItinerary0 .fareProductName")
    private static final By OUTBOUND_ROUTING = By.cssSelector("#airItinerary0 .stops")


    private WebDriverProvider provider

    FeeCalculator feeCalculator
    Flow flow
    PassengerData passengerData
    SelectFlightsPageData selectFlightsPageData
    PricePageData pricePageData
    PurchasePageData purchasePageData

    def UnaccompaniedMinorGuardianPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider)
        this.provider = webDriverProvider
    }

    String getRelativePath() {
        return "reservations/unaccompanied-minor-form.html"
    }

    void verifyTheTotalUMTextAndCharge() {
        BigDecimal totalFee = feeCalculator.calculateGuardianCharge()
        isElementPresent((TOTAL_UM_CHARGE_TEXT_CSS)).shouldBe true, "The guardian form should be present"
        waitForElement(TOTAL_UM_CHARGE_TEXT_CSS).getText().shouldContain "Total Unaccompanied Minor Charge", "The text should be: Total Unaccompanied Minor Charge"
        waitForElement(TOTAL_UM_CHARGE_CSS).getText().shouldContain totalFee, "The charge should be $totalFee"
    }

    def verifyPrepopulateCheckboxIsNotDisplayed() {
        isElementPresent(PREPOPULATE_CHECKBOX).shouldBe false, "The prepopulate checkbox should not be present"
    }

    void clickPrepopulateCheckbox() {
        waitForElement(PREPOPULATE_CHECKBOX).click()
    }

    void fillDepartingDropOffGuardian(Guardian guardian) {
        GuardianForm dropForm = GuardianForm.createDepartureDropOffForm(provider)
        dropForm.fillForm(guardian)
    }

    void fillDepartingPickUpGuardian(Guardian guardian) {
        GuardianForm pickForm = GuardianForm.createDeparturePickUpForm(provider)
        pickForm.fillForm(guardian)
    }

    void fillDepartingAlternateGuardian(Guardian guardian) {
        GuardianForm alternateForm = GuardianForm.createDepartureAlternateForm(provider)
        alternateForm.fillForm(guardian)
    }

    void fillReturningDropOffGuardian(Guardian guardian) {
        GuardianForm dropForm = GuardianForm.createReturningDropOffForm(provider)
        dropForm.fillForm(guardian)
    }

    void fillReturningPickUpGuardian(Guardian guardian) {
        GuardianForm pickForm = GuardianForm.createReturningPickUpForm(provider)
        pickForm.fillForm(guardian)
    }

    void fillReturningAlternateGuardian(Guardian guardian) {
        GuardianForm alternateForm = GuardianForm.createReturningAlternateForm(provider)
        alternateForm.fillForm(guardian)
    }

    void verifyDepartingDropOffGuardian(Guardian guardian) {
        GuardianForm dropForm = GuardianForm.createDepartureDropOffForm(provider)
        dropForm.verifyInfo(guardian)
    }

    void verifyDepartingPickUpGuardian(Guardian guardian) {
        GuardianForm pickForm = GuardianForm.createDeparturePickUpForm(provider)
        pickForm.verifyInfo(guardian)
    }

    void verifyDepartingAlternateGuardian(Guardian guardian) {
        GuardianForm alternateForm = GuardianForm.createDepartureAlternateForm(provider)
        alternateForm.verifyInfo(guardian)
    }

    void verifyReturningDropOffGuardian(Guardian guardian) {
        GuardianForm dropForm = GuardianForm.createReturningDropOffForm(provider)
        dropForm.verifyInfo(guardian)
    }

    void verifyReturningPickUpGuardian(Guardian guardian) {
        GuardianForm pickForm = GuardianForm.createReturningPickUpForm(provider)
        pickForm.verifyInfo(guardian)
    }

    void verifyReturningAlternateGuardian(Guardian guardian) {
        GuardianForm alternateForm = GuardianForm.createReturningAlternateForm(provider)
        alternateForm.verifyInfo(guardian)
    }

    void verifyUmGuardianFormTitle() {
        getTitle().shouldBe WINDOWS_TITLE_MSG, "The windows title should be ${WINDOWS_TITLE_MSG}"
    }
    
    void verifyOopsMandatoryFields() {
        def oopsMessages = [
            'The first name of the drop-off parent/guardian for the departing flight was not entered.',
            'The last name of the drop-off parent/guardian for the departing flight was not entered.',
            'The relationship between the Unaccompanied Minor and the drop-off parent/guardian for the departing flight was not entered.',
            'The phone number of the drop-off parent/guardian for the departing flight was not entered completely.',
            'The address of the drop-off parent/guardian for the departing flight was not entered.',
            'The city of the drop-off parent/guardian for the departing flight was not entered.',
            'The state of the drop-off parent/guardian for the departing flight was not entered.',
            'The zip code of the drop-off parent/guardian for the departing flight was not entered.',
            'The preferred method of contact of the drop-off parent/guardian for the departing flight was not selected.',
            'The first name of the pick-up parent/guardian for the departing flight was not entered.',
            'The last name of the pick-up parent/guardian for the departing flight was not entered.',
            'The relationship between the Unaccompanied Minor and the pick-up parent/guardian for the departing flight was not entered.',
            'The phone number of the pick-up parent/guardian for the departing flight was not entered completely.',
            'The address of the pick-up parent/guardian for the departing flight was not entered.',
            'The city of the pick-up parent/guardian for the departing flight was not entered.',
            'The state of the pick-up parent/guardian for the departing flight was not entered.',
            'The zip code of the pick-up parent/guardian for the departing flight was not entered.',
            'The preferred method of contact of the pick-up parent/guardian for the departing flight was not selected.',
            'The first name of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The last name of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The relationship between the Unaccompanied Minor and the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The phone number of the alternate pick-up parent/guardian for the departing flight was not entered completely.',
            'The address of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The city of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The state of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The zip code of the alternate pick-up parent/guardian for the departing flight was not entered.',
            'The preferred method of contact of the alternate pick-up parent/guardian for the departing flight was not selected.',
            'The first name of the drop-off parent/guardian for the returning flight was not entered.',
            'The last name of the drop-off parent/guardian for the returning flight was not entered.',
            'The relationship between the Unaccompanied Minor and the drop-off parent/guardian for the returning flight was not entered.',
            'The phone number of the drop-off parent/guardian for the returning flight was not entered completely.',
            'The address of the drop-off parent/guardian for the returning flight was not entered.',
            'The city of the drop-off parent/guardian for the returning flight was not entered.',
            'The state of the drop-off parent/guardian for the returning flight was not entered.',
            'The zip code of the drop-off parent/guardian for the returning flight was not entered.',
            'The preferred method of contact of the drop-off parent/guardian for the returning flight was not selected.',
            'The first name of the pick-up parent/guardian for the returning flight was not entered.',
            'The last name of the pick-up parent/guardian for the returning flight was not entered.',
            'The relationship between the Unaccompanied Minor and the pick-up parent/guardian for the returning flight was not entered. ',
            'The phone number of the pick-up parent/guardian for the returning flight was not entered completely.',
            'The address of the pick-up parent/guardian for the returning flight was not entered.',
            'The city of the pick-up parent/guardian for the returning flight was not entered.',
            'The state of the pick-up parent/guardian for the returning flight was not entered.',
            'The zip code of the pick-up parent/guardian for the returning flight was not entered. ',
            'The preferred method of contact of the pick-up parent/guardian for the returning flight was not selected.',
            'The first name of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The last name of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The relationship between the Unaccompanied Minor and the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The phone number of the alternate pick-up parent/guardian for the returning flight was not entered completely.',
            'The address of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The city of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The state of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The zip code of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The preferred method of contact of the alternate pick-up parent/guardian for the returning flight was not selected.'
        ]
        verifyOopsMessages(oopsMessages)
    }
    
    void verifyOopsMandatoryFieldsReturningFlight() {
        def oopsMessages = [
            'The first name of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The last name of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The relationship between the Unaccompanied Minor and the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The phone number of the alternate pick-up parent/guardian for the returning flight was not entered completely.',
            'The address of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The city of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The state of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The zip code of the alternate pick-up parent/guardian for the returning flight was not entered.',
            'The preferred method of contact of the alternate pick-up parent/guardian for the returning flight was not selected.'
        ]
        verifyOopsMessages(oopsMessages)
    }
    
    void verifyOopsAboutSameInfoForAlternateAndPickUp() {
        def oopsMessages = [
            'The same person cannot be both the primary and alternate pick-up parent/guardian for the returning flight. Please enter a different primary and alternate parent/guardian.',
            'The same phone number cannot be used for both the primary and alternate pick-up parent/guardian for the returning flight. Please enter a different phone number for the primary and alternate parent/guardians.'
        ]
        verifyOopsMessages(oopsMessages)
    }

    void verifyOopsAboutSameInfoForAlternateAndPickUpDepartingFlight() {
        def oopsMessages = [
            'The same person cannot be both the primary and alternate pick-up parent/guardian for the departing flight. Please enter a different primary and alternate parent/guardian.',
            'The same phone number cannot be used for both the primary and alternate pick-up parent/guardian for the departing flight. Please enter a different phone number for the primary and alternate parent/guardians.'
            ]
        verifyOopsMessages(oopsMessages)
    }

    def verifyBasicPage() {
        super.verifyPage()
        if (flow.isFaultInjected) {
            println "Entry (verify unaccompanied minor page) ---- waiting for fault injection"
            if (flow.isFaultInjected) {sleep(30000)}
            println "Exit (verify unaccompanied minor page) ---- waiting for fault injection"
        }
        verifyPageBreadcrumb(BREADCRUMB_IDS["PurchasePage"])
        pageVerificationErrorWrapper(SUBMIT_BUTTON, PageName.UNACCOMPANIED_MINOR_PAGE)
    }

    def verifyTitle() {
        waitForElement(PAGE_TITLE).text.shouldBe "Unaccompanied Minor", "The page title was not correct"
    }

    def verifySubtitle() {
        waitForElement(PAGE_SUBTITLE).text.shouldBe "Terms, Conditions and Parent/Guardian Information", "The page subtitle was not correct"
    }

    def verifyName() {
        waitForElement(UM_PASSENGER_FULLNAME).text.toUpperCase().shouldBe passengerData.passengers[0].toString().toUpperCase(), "UM full name as not correct"
    }

    def verifyChargesAndTotal() {
        waitForElement(INDIVIDUAL_CHARGES).text.replace('$', '').toBigDecimal().shouldEqual purchasePageData.guardianFee, "UM individual charges were not correct"
        waitForElement(TOTAL_CHARGE).text.replace('$', '').toBigDecimal().shouldEqual purchasePageData.guardianFee, "UM total charge was not correct"
    }

    def verifyParentGuardianContactsInformationArePresent() {
        List<WebElement> contactSections = waitForElements(PARENT_GUARDIAN_CONTACT_INFO)
        contactSections[0].findElement(By.cssSelector("strong")).text.shouldContain "Parent/Guardian at Drop-Off", "Parent/Guardian at Drop-Off section was not present"
        contactSections[1].findElement(By.cssSelector("strong")).text.shouldContain "Parent/Guardian at Pick-Up", "Parent/Guardian at Pick-Up section was not present"
        contactSections[2].findElement(By.cssSelector("strong")).text.shouldContain "Alternate Parent/Guardian at Pick-Up", "Alternate Parent/Guardian at Pick-Up section was not present"
    }

    def verifyDepartureAndArrivalTime() {
        List<WebElement> outboundTimes = waitForElements(OUTBOUND_TIMES)
        outboundTimes[0].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundDepartureTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight departure time did not match flight time on Bug Page"
        outboundTimes[1].text.replaceAll("\\s", "").shouldContain selectFlightsPageData.outboundArrivalTime.replaceAll("\\s", "").toUpperCase(), "Outbound flight arrival time did not match flight time on Bug Page"
    }

    def verifyDepartureAndArrivalCities() {
        List<WebElement> outboundCities = waitForElements(OUTBOUND_CITIES)
        outboundCities[0].text.shouldContain itineraryData.departureStation, "Departure station did not match departure station from the Bug page"
        outboundCities[1].text.shouldContain itineraryData.arrivalStation, "Arrival station did not match arrival station from the Bug page"
    }
    
    def verifyTravelTime()
    {
        waitForElement(OUTBOUND_TRAVEL_TIME).text.replaceAll("\\s", "").replaceFirst("TravelTime", "").shouldContain selectFlightsPageData.outboundTravelTime.replaceAll("\\s", ""), "Outbound Travel time did not match travel time from the Bug page"
    }

    def verifyFlightNumber() {
        waitForElement(OUTBOUND_FLIGHT_NUMBER).text.shouldContain selectFlightsPageData.departingFlight_number, "Outbound flight number did not match flight number from the Bug page"
    }

    def verifyFareType() {
        waitForElement(OUTBOUND_FARE_TYPE)?.getText().replaceAll(" ", "").shouldBe itineraryData.departingFlight_fareClass, "Fare type did not mach the selected one"
    }

    def verifyRoutingType() {
        waitForElement(OUTBOUND_ROUTING)?.text.shouldContain selectFlightsPageData.outboundFlyoutRouting, "Routing type did not match the selected one"
    }
}
