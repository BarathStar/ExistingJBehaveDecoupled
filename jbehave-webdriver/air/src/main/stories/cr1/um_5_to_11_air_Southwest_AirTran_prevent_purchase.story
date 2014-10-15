Prevent an Unaccompanied Minor from Purchasing an Air Ticket on AirTran

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@storyId DCQA28
@traveler UM
@dyna_stubs
@project_in_dev

Narrative:
In order to fly on a date that I want
As an unaccompanied minor
I want to book a flight
But I cannot book travel on Southwest - AirTran round-trip


Scenario: Unaccompanied minor (5-11) prevented from WN-FL round-trip flight air purchase

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight for an unaccompanied child
Then I see the UM 5-11 passenger message
