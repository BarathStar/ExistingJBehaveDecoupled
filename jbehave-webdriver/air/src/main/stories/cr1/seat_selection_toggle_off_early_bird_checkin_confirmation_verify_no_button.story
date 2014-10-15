Verify Seat Selection Button is NOT Displayed on the Early Bird Check-in Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1157
@seatSelectionOff
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button does not show on the Early Bird Check-in Confirmation Page
As a customer with a Southwest / AirTran Itinerary
I add EarlyBird Check-in
So that I do not see the AirTran Seat Selection Button is NOT DISPLAYED on the Early Bird Check-in Confirmation Page

Scenario: Verify AirTran Seat Selection Button is NOT DISPLAYED on Early Bird Check-in Confirmation Page

Given I have a Southwest AirTran reservation
When I retrieve my reservation through Early Bird
And I purchase an Early Bird Check-in
Then I see the Early Bird Check-in Confirmation Page
And I do not see the seat selection button on the Early Bird Check-in Confirmation Page
