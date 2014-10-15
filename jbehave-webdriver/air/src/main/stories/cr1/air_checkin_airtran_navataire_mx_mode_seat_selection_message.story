Verify error message shows up when seat selection button is selected on the checkin landing page

Meta:
@project cr1
@flow air
@user Anonymous
@process checkin
@traveler adult
@storyId DCAIR6425
@dyna_stubs
@cr1_navitaireMxMode
@project_in_dev

Narrative:
As a customer
I want to book a flight
So that I select a seat on the checkin landing page when Naviataire maintenance mode is ON and I verify the error message.

Scenario: Verifying the error message on the check-in page when we select  seat selection button when Navitaire maintenance mode is ON.

Given Navitaire service maintenance is turned off
And I am flying a round-trip SouthwestCodeshare AirTran flight
When I book outbound and return flights eligible for checkin 1 adult
And Navitaire service maintenance is turned on
And I retrieve my reservation to checkin
And I click the seat selection button on the checkin page
Then I see the seat selection unavailable message for the AirTran flight on the checkin page
