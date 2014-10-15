Purchase A Rapid Rewards Air Ticket in Points, cancel and verify points returned to account

Meta:
@project cr1
@project_in_dev
@airTranOnly
@flow air
@process cancel
@user rr_member
@traveler adult
@dyna_stubs
@storyId DCQA-63, ZR-894


Narrative:
As a rapid rewards member
I want to book a round-trip SouthwestCodeshare flight using points and cancel the flight
So that I view the cancellation confirmation and verify I can get my points back.


Scenario:
Rapid Rewards Member air purchase with points, cancel and replenish points to my account

Given I am flying a round-trip WNFLCodeShare WNFLCodeShare flight
And I am a Rapid Rewards Member with enough points to complete a purchase
And I have booked this flight using my points
When I cancel the flight
Then I view the flight cancellation confirmation

