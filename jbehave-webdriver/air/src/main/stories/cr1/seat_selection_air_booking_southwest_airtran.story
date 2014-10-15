Verify Southwest\AirTran itinerary displayed on Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@seatSelection
@msg no verification is added for the AirTran seat selection Page until we can verify airtran.com is returning the same page
@dyna_stubs

Narrative:
In order to book a flight using SouthWest and AirTran
As a customer
I want to verify SouthWest\AirTran itinerary displayed on confirmation page


Scenario: Book a WN/FL RoundTrip and Select Seats For My AirTran Flight

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight
Then I should see the confirmation page
And I see seat selection message for the inbound AirTran flight
When I click the inbound seat selection button
