Performance-Southwest-AirTran-AirTran-Southwest-RoundTrip

Meta:
@suite pagePerformance
@project CR1_PAGE_PERFORMANCE
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult


Narrative: In order to fly on a date that I want
As an adult
I want to book a flight and receive a confirmation number


Scenario: formerly Southwest-AirTran-AirTran-Southwest-RoundTrip; now SW-FL-FL-SW

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I book a flight
Then I receive an air confirmation number