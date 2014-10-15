Verify AirTran seat selection button does not display on view itinerary page after checkin

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@dyna_stubs
@project_in_dev
@storyId DCAIR-6427, ZR-870


Narrative:
As an adult
I want to View a reservation already checked in
So that seat selection button is not displayed.


Scenario: checkin for a FL_WN Itinerary for one adult

Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight eligible for checkin 1 adult
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
When I return to the checkin page
And I retrieve my itinerary
Then I don't see the seat selection button for my outbound flight
