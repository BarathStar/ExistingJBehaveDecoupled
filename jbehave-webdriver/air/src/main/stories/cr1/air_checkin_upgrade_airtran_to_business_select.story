Upgrade to Business Selection option should be available from check in page for AirTran itinerary

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@storyId DCQA-25, ZR-870
@dyna_stubs
@project_in_dev


Narrative:
As an Adult
I want to check in codeshare itinerary
So that I can see Upgrade to Business Select button


Scenario: View upgrade to Business Select when on checkin page for AirTran only itinerary

Given I am flying a Anytime one-way AirTran flight
When I book a flight eligible for checkin 1 adult
And I retrieve travel documents
And I click on upgrade to business select
Then I verify that business select could be available
When I click on Continue button
Then I see Business Select product available


