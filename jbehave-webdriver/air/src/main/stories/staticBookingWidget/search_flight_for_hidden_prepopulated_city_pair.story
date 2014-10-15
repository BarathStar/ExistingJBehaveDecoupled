Static booking widget scenario three with hidden, pre-populated fields

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:
As a traveller, I want to enter dates so that I can check the dates for a specified flight

Scenario: Open air_ctd_03 (StaticWidgetHiddenCityPairPage) and enter dates to search for a hidden prepopulated city pair
Given I am on the static widget with hidden input fields containing the origin and destination page
When I enter valid dates and click on the search button
Then Verify that I am on the Select Flights page and HOU and LAS are in From and To fields
