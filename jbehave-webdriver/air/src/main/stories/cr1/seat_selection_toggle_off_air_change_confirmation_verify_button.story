Verify Seat Selection Button is Not Displayed on the Air Change Confirmation Page when Seat Selection is Toggled Off

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1155
@seatSelectionOff
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button is not displayed on the Air Change Confirmation Page when seat
selection is toggled off
As a customer
I want to change an existing Southwest itinerary to an AirTran itinerary
So that I should not see the AirTran Seat Selection Button

Scenario: Verify AirTran Seat Selection Button is not displayed on Air Change Confirmation Page when Toggled Off

Given I have a reservation
When I retrieve my reservation to change
And I change to a round-trip AirTran AirTran flight
Then I should see the itinerary change confirmation page
And I should not see the seat selection button
