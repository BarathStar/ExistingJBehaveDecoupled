Verify AirTran seat selection button is displayed on check in landing page

Meta:
@flow air
@project cr1
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@storyId ZR-912
@seatSelection
@project_in_dev

Narrative:
In order to select seats for my AirTran flight
As an anonymous adult
I should see the seat selection button on the check in page

Scenario: check in for a WN_FL itinerary for one anonymous adult

Given I have an existing AirTran reservation eligible for check in
When I check in from the check in page
Then I see the View / Select Seat(s) button on the check in page
