Static booking widget scenario one with web referral

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:
As a traveller, I want to try to search an air tran flight on the static booking widget so that I get redirected to the Air Tran web site.

Scenario: When I am on air_ctd_01_t11 page (StaticWidgetAirTranRedirectPage), I use autocomplete and get redirected to the Air Tran website after seeing an info modal
Given I am on the static widget Air Tran redirect page
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|RIC|
    |carrierDates|FL:F:[today,today+60]|
When I select ATL in the From field in the static booking widget
And I select RIC in the To field in the static booking widget
And I enter valid dates and click on the search button and wait for modal
Then I am redirected to the AirTran webpage after seeing a info modal
