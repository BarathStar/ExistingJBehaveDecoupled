Static booking widget scenario five with an autocomplete and a dropdown

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:   As a traveller, I want to use an autocomplete and a dropdown so that I can search the available routes.

Scenario: When I am on air_ctd_07 (StaticWidgetCustomDepartureDefaultArrival), both search fields are dropdowns and I get redirected to the Air Tran website after seeing an info modal
Given I am on the custom departure and default arrival page
And The following routes are available:
    |Field|Value|
    |originStation|ATL|
    |destinationStation|RIC|
    |carrierDates|FL:F:[today,today+60]|
When I select ATL in the From field in the static booking widget
And I select RIC in the default departure dropdown
And I enter valid dates and click on the search button and wait for modal
Then I am redirected to the AirTran webpage after seeing a info modal
