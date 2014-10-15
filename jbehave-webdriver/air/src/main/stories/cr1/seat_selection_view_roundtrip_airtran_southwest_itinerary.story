Verify AirTran\Southwest View\Share itinerary page has Seat Select button

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user
@traveler adult
@seatSelection
@dyna_stubs
@msg no verification is added for the AirTran seat selection Page until we can verify airtran.com is returning the same page
@project_in_dev

Narrative:
In order to book a flight using Business Select fare type on AirTran and SouthWest
As a customer
I want to verify AirTran Seat selection when I view the itinerary

Scenario: Viewing a FL_WN Business Select Itinerary for AirTran Seat Selection
Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I have a flight booked
And I retrieve my itinerary
Then I see seat selection options for the AirTran flights on the view itinerary page
When I click the seat selection button on the view itinerary page
