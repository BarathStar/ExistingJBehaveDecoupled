Verify Seat Selection Button Displayed on the View Itinerary Page

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1148
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button on the View Itinerary Page
As a customer
I want to view my Itinerary
So that I can see the AirTran Seat Selection Button

Scenario: Verify AirTran Seat Selection Button on View Itinerary Page

Given I have an AirTran reservation
When I retrieve my itinerary
Then I should see the correct view itinerary page
And I see the seat selection button on the itinerary page

