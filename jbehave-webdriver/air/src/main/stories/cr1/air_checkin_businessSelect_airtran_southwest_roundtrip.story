Verify successful check in for Business select Code Share Itineraries

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCQA-46 ZR-870
@project_in_dev

Narrative:
As an adult
I want to checkin Southwest and AirTran flights
So that I can receive boarding pass for code share itineraries and drink coupons only for Southwest segments


Scenario: checkin for a FL_WN Itinerary

Given I am flying a BusinessSelect round-trip FLWNCodeShare FLWNCodeShare flight
When I book a flight eligible for checkin 1 adult
When I check in from the check in page
And I click check in to view my boarding pass
Then I view boarding passes for Southwest and AirTran

