Verify Seat Selection Button Not Displayed on Air Purchase Confirmation Page when Toggled Off

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@seatSelectionOff
@dyna_stubs
@storyId ZR-1154
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button is not displayed on the Air Purchase Confirmation Page when seat
selection is toggled off
As a customer
I want to book a Southwest \ AirTran itinerary
So that I can verify the AirTran Seat Selection Button is not displayed

Scenario: Verify AirTran Seat Selection Button is not displayed on Air Purchase Confirmation Page

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight
Then I should see the confirmation page
And I should not see the seat selection button
