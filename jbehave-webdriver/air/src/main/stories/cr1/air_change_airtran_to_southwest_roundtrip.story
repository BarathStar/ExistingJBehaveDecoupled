Retrieve and Change airtran to southwest roundtrip flight

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId DCQA34
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change a round-trip itinerary


Scenario: Changing a FL RoundTrip flight itinerary to WN RoundTrip flight itinerary

Given I am flying a round-trip AirTran AirTran flight
When I have a flight booked
Then I should see the confirmation page
When I change to a round-trip SouthwestCodeshare SouthwestCodeshare flight
Then I should see the itinerary change confirmation page