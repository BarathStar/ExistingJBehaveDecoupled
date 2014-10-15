Verify error message shows up when seat selection button is selected on the View Reservation Page

Meta:
@project cr1
@flow air
@user Anonymous
@process view
@traveler adult
@storyId DCAIR6546
@dyna_stubs
@cr1_navitaireMxMode
@project_in_dev

Narrative:
In order to try to select a seat when navitaire maintenance mode is on
I want to book a flight and
try to select a seat on the view reservation page

Scenario: I Book my itinerary for FL/WN and FL/WN operated

Given Navitaire service maintenance is turned off
And I am flying a round-trip AirTran SouthwestCodeshare flight
When I have a flight booked
And Navitaire service maintenance is turned on
And I retrieve my itinerary
Then I click the outbound seat selection button on the view itinerary page
And I see the seat selection unavailable message for the outbound AirTran flight on the view itinerary page
