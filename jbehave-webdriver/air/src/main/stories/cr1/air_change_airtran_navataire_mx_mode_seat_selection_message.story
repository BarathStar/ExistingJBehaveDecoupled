Verify error message shows up when seat selection button is selected on Change Confirmation Page

Meta:
@project cr1
@flow air
@process change
@traveler adult
@storyId DCAIR6546
@cr1_navitaireMxMode
@project_in_dev
@not_passing draft

Narrative:
As a customer
I want to book a flight and change the reservation
So that I select a seat on the change confirmation page when navitaire maintenance mode is ON and I see the error message.

Scenario: I Book my itinerary for FL/WN and FL/WN operated

Given Navitaire service maintenance is turned off
And I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight
Then I receive an air confirmation number
When I change to a round-trip AirTran AirTran flight
Then I should see the confirmation page
When Navitaire service maintenance is turned on
And I click the outbound seat selection button
Then I see the seat selection unavailable message for the outbound AirTran flight