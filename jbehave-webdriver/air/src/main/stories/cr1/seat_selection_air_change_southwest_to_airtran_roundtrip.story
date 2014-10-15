Retrieve and Change Southwest to an AirTran roundtrip flight

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId DCAIR6413
@seatSelection
@msg no verification is added for the AirTran seat selection Page until we can verify airtran.com is returning the same page
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a user
I want to change a round-trip Southwest to a round-trip AirTran itinerary


Scenario: Changing a WN RoundTrip flight itinerary to FL RoundTrip flight itinerary

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I have a flight booked
Then I should see the confirmation page
When I change to a round-trip AirTran AirTran flight
Then I should see the itinerary change confirmation page
And I see seat selection message for the outbound AirTran flight
When I click the outbound seat selection button
