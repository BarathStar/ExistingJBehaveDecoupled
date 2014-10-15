Static booking widget scenario five with two dropdown fields and web referral

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:   As a traveller, I want to use the dropdowns so that I can search the available routes.

Scenario: When I am on air_ctd_05 (staticWidgetCustomDropdownAirTranRedirectPage), both search fields are dropdowns and I get redirected to the Air Tran website after seeing an info modal
Given I am on the Static Widget Air page with a custom dropdown with a city pair that redirects to FL
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|RIC|
    |carrierDates|FL:F:[today,today+60]|
When I select ATL in the custom arrival dropdown
And I select RIC in the custom departure dropdown
And I enter valid dates and click on the search button and wait for modal
Then I am redirected to the AirTran webpage after seeing a info modal
