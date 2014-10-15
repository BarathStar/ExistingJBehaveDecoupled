Verify Seat Selection Button Displayed on Air Purchase Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@seatSelection
@dyna_stubs
@storyId ZR-1142
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Air Purchase Confirmation Page
As a customer
I want to book a Southwest \ AirTran itinerary
So that I can see the AirTran Seat Selection Button

Scenario: Verify AirTran Seat Selection Button on Air Purchase Confirmation Page

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight
Then I should see the confirmation page
And I see the seat selection button
