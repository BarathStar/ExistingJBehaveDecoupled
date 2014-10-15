Purchase an Adult Air Ticket

Meta:
@suite pagePerformance
@project CR1_PAGE_PERFORMANCE
@airTranOnly
@flow air
@process booking
@user anonymous
@traveler adult
@storyId DCAIR-5455
@not_passing draft


Narrative: In order to fly on a date that I want
As an adult
I want to book a flight and receive a confirmation number


Scenario: Southwest-Southwest round-trip

Given I am flying a round-trip Southwest Southwest flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number

Scenario: AirTran-AirTran round-trip

Given I am flying a round-trip AirTran AirTran flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number


Scenario: Southwest codeshare round-trip

Given I am flying a round-trip CodeShare CodeShare flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number

Scenario: Southwest-AirTran round-trip (formerly Southwest-AirTran-AirTran-Southwest, no known market for this)

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number