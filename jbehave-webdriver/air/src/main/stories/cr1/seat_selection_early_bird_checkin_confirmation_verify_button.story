Verify Seat Selection Button Displayed on the Early Bird Check-in Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1150
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Early Bird Check-in Confirmation Page
As a customer with a Southwest / AirTran Itinerary
I add EarlyBird Check-in
So that I can see the AirTran Seat Selection Button on the Early Bird Check-in Confirmation Page

Scenario: Verify AirTran Seat Selection Button on Early Bird Check-in Confirmation Page

Given I have a Southwest AirTran reservation
When I retrieve my reservation through Early Bird
And I purchase an Early Bird Check-in
Then I see the Early Bird Check-in Confirmation Page
And I see the seat selection button on the Early Bird Check-in Confirmation Page



