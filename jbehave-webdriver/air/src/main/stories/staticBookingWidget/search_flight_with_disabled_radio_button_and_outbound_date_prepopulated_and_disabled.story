Static booking widget scenario two with prepopulated origin and destination

Meta:
@flow air
@process booking
@traveler adult
@project_in_dev
@project bookingWidget
@dyna_stubs

Narrative:
As a traveller, I want to enter a departure date in order to check flight dates for the advertised flight

Scenario: Open air_ctd_02.html (StaticWidgetRadioButtonAndDateDisabledPage) and enter a departure date to search for advertised flight

Given I am on the air ctd test page with a static booking widget featuring radio button and date disabled
And I verify the radio button and the outbound date is disabled and pre-populated
When I select HOU and PHX in the From and To fields and verify
And I complete static widget with valid outbound date and click search
Then Verify that I am on the Select Flights page and HOU and PHX are in From and To fields
