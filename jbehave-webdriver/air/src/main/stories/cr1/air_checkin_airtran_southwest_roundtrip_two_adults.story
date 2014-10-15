Verify successful check-in of code share itineraries for 2 adults for a round trip

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@project_in_dev
@storyId DCQA-42, ZR-870

Narrative:
As an adult
I want to check in code share itineraries for 2 adults
So that I can receive 2 boarding passes

Scenario: checkin for a WN_FL Itinerary for multiple adult

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight eligible for checkin 2 adult
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view 2 southwest boarding passes
