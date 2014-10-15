Verify that we see an AirTran Boarding Pass

Meta:
@flow air
@project cr1
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@storyId ZR-912
@project_in_dev

Narrative:
In order to receive my boarding pass
As an anonymous adult
I want to check in for a AirTran flight

Scenario: check in for a FL itinerary for one anonymous adult

Given I have an existing AirTran reservation eligible for check in
When I check in from the check in page
And I click check in to view my boarding pass
Then I view and verify my boarding pass
