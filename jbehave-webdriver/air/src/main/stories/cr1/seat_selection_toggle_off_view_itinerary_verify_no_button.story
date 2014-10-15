Verify Seat Selection Button is Not Displayed on the View Itinerary Page when Toggled Off

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@traveler adult
@storyId ZR-1156
@seatSelectionOff
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button is not displayed on the View Itinerary Page when seat
selection is toggled off
As a customer
I want to view my Itinerary
So that I can verify the AirTran Seat Selection Button is not displayed

Scenario: Verify AirTran Seat Selection Button is not displayed on View Itinerary Page

Given I have an AirTran reservation
When I retrieve my itinerary
Then I should see the correct view itinerary page
And I should not see the seat selection button on the itinerary page
