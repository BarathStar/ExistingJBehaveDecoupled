Prevent an Unaccompanied Minor from Purchasing an Air Ticket on AirTran

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@storyId DCQA28
@traveler UM
@data schedule
@dyna_stubs
@project_in_dev

Narrative:
In order to fly on a date that I want
As an unaccompanied minor
I want to book a flight
But I cannot book round-trip travel on AirTran

Scenario:Unaccompanied minor (under 5) and youth prevented from FL round-trip flight air purchase
Given I am flying a round-trip AirTran AirTran flight
When I book a flight for an unaccompanied young child
Then I see the UM under 5 passenger message
