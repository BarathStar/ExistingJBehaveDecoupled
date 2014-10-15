Prevent an Unaccompanied Minor from Purchasing an Air Ticket on AirTran

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler minor_youth
@data schedule
@dyna_stubs


Narrative:
In order to fly on a date that I want
As an unaccompanied minor traveling with a youth
I want to book a flight
But I cannot book travel on AirTran - Southwest round-trip


Scenario:Minor child (5-11) traveling with a youth passenger (under 18) prevented from FL-SW round-trip flight air purchase

Given I am flying a round-trip AirTran SouthwestCodeshare flight
When I book a flight for a minor traveling with a youth
Then I see the UM pop-up modal
