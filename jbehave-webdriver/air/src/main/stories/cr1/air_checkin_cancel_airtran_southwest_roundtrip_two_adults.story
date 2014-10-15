Verify Cancel button on Check-in page

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@user anonymous
@dyna_stubs
@not_passing the story broke on choosing cancel button. Some steps seem to be related to the old behavior of the check in page

Narrative:
As an Adult
I want to click on Cancel button from Check in and Print Boarding Documents page
So that I am taken to check in landing page


Scenario: Canceling the checkin for a FL_WN Itinerary for multiple adults

Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight eligible for checkin 2 adults
And I retrieve travel documents
And I choose cancel
Then I am sent to the checkin landing page

