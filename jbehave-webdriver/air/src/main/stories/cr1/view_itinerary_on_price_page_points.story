Verify itinerary displayed on Price Page Using Points

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@storyId DCQA-43, ZR-899
@not_passing draft

Narrative:
As a customer
I want to book a flight with Points and use different combinations of stations
So that I see the pricing page with correct fare and specific travel guidelines related to the stations.

Scenario: Viewing a WN only Business Select Itinerary

Given I am flying a BusinessSelect round-trip SouthwestCodeshare SouthwestCodeshare flight
And I am a customer searching for round-trip flights with points
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I see the Southwest-mixed specific travel guidelines

Scenario: Viewing a FL_WN Business Select Itinerary

Given I am flying a BusinessSelect round-trip AirTran SouthwestCodeshare flight
And I am a customer searching for round-trip flights with points
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I see the AirTran-mixed specific travel guidelines

Scenario: Viewing a FL only Itinerary

Given I am flying a round-trip AirTran AirTran flight
And I am a customer searching for round-trip flights with points
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I see the AirTran-only specific travel guidelines
