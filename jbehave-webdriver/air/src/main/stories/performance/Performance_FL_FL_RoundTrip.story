Performance-AirTran-AirTran-RoundTrip

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


Scenario: round-trip AirTran AirTran

Given I am flying a round-trip AirTran AirTran flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number