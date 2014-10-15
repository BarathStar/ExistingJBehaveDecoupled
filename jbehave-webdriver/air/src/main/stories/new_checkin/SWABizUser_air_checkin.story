SwaBiz User Air Checkin

Meta:
@flow air
@process checkin
@user sb_user
@traveler adult
@dyna_stubs
@not_passing draft

Narrative:
In order to receive my boarding pass
As a swabiz user
I want to checkin for a flight

Scenario: SWABiz User air checkin
Given I am flying a round-trip Southwest Southwest flight
And I am an anonymous SWABiz user with a booked flight eligible for checkin 1 adult
When I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
