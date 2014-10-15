Verify itinerary displayed on Price Page with AirTran only messaging

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCQA-43
@dyna_stubs
@project_in_dev


Narrative:  In order to verify AirTran only itinerary displayed on pricing page
As a customer
I want to verify itinerary displayed on confirmation page with AirTran only messaging

Scenario: Viewing an FL Round Trip Itinerary

Given I am flying a round-trip AirTran AirTran flight
And I am a customer searching for round-trip flights from the search flights page
When I select and view the Price page for a flight
Then I should see the correct pricing page
And I see the AirTran-only specific travel guidelines