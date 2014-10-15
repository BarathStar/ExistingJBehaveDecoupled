Verify Seat Selection Button Displayed on the Air Change Confirmation Page

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1145
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the Air Change Confirmation Page
As a customer
I want to change an existing Southwest itinerary to an AirTran iteinerary
So that I can see the AirTran Seat Selection Button

Scenario: Verify AirTran Seat Selection Button on Air Change Confirmation Page

Given I have a reservation
When I retrieve my reservation to change
And I change to a round-trip AirTran AirTran flight
Then I should see the itinerary change confirmation page
And I see the seat selection button

