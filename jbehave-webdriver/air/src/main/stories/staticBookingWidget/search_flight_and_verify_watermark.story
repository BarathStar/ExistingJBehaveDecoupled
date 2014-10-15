Static booking widget scenario one with two autocomplete fields

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:
As a traveller, I want to use the autocomplete so that I can see the available routes.

Scenario: When I am on air_ctd_01_t11b (StaticWidgetAirTranRedirectPage), the departure and arrival fields have a watermark and use autocomplete
Given I am on the static widget Air Tran redirect page
And I see watermarks in the From and To fields
When I select DAL and HOU in the From and To fields and verify
And I enter valid dates and click on the search button
Then Verify that I am on the Select Flights page and DAL and HOU are in From and To fields
