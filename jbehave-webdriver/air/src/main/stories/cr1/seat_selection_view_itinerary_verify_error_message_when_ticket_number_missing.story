Verify Seat Selection Button shows an error message when selected on the View Itinerary Page when the navitaire ticket number is missing

Meta:
@project cr1
@airTranOnly
@flow air
@process changing
@traveler adult
@storyId ZR-1658
@seatSelection
@dyna_stubs
@project_in_dev

Narrative:
In order to verify the AirTran Seat Selection Button is disabled on the View Itinerary Page
As a customer
I want to view my Itinerary that doesn't have a navitaire assigned ticket number
So that I can see the AirTran Seat Selection Button shows an error message when selected

Scenario: Verify AirTran Seat Selection Button is disabled on View Itinerary Page

Given I have an AirTran reservation that is missing ticket numbers
When I retrieve my itinerary
Then I should see the correct view itinerary page
And I see the seat selection button on the itinerary page
Then I click the outbound seat selection button on the view itinerary page
And I see the seat selection unavailable message for the outbound AirTran flight on the view itinerary page

