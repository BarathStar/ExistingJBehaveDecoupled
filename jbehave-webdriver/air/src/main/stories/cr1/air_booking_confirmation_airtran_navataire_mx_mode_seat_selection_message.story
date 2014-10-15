Verify error message shows up when seat selection button is selected on the Confirmation Page

Meta:
@project cr1
@flow air
@user Anonymous
@process Information Search
@traveler adult
@storyId DCAIR6546
@dyna_stubs
@cr1_navitaireMxMode
@project_in_dev
@not_passing draft

Narrative:
As a customer
I want to book a flight and select the seat on the confirmation page and the view share itinerary page when Navitaire maintenance mode is ON.
So that I verify the message.

Scenario: I Book my itinerary for FL and WN operated

Given Navitaire service maintenance is turned off
And I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight
And Navitaire service maintenance is turned on
Then I receive an air confirmation number
When I click the outbound seat selection button
Then I see seat selection navitaire maintenance message
