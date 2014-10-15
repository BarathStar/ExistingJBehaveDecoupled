Verify print documents for Southwest/Airtan itineraries when navataire is unavailable

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@dyna_stubs
@storyId ZR-1021
@project_in_dev

Narrative:
As an adult
I want to checkin Southwest and AirTran flights
So that I can receive boarding pass and boarding pass shells for code share itineraries when navataire is unavailable

Scenario: checkin for a WN_FL Itinerary when navataire is unavailable

Given I am flying a round-trip WNFLCodeShare WNFLCodeShare flight
When I book outbound and return flights eligible for checkin 1 adult
And Navataire Checkin is down
When I check in from the check in page
And I click check in to view my boarding pass
Then I view 1 southwest boarding passes
And I view 1 Southwest boarding pass shells and 2 AirTran boarding pass shells
