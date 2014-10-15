Verify FL WN Itinerary displayed on Price Page with Southwest and AirTran messaging

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCQA-43 ZR-1274
@dyna_stubs
@project_in_dev

Narrative:  In order to verify FL WN itinerary displayed on pricing page
As a customer
I want to verify itinerary displayed on confirmation page

Scenario: Viewing a FL WN Round Trip Itinerary

Given I am flying a round-trip AirTran SouthwestCodeshare flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I see the AirTran-mixed specific travel guidelines