Performance-Southwest-Southwest-RoundTrip

Meta:
@suite pagePerformance
@project BASELINE_PAGE_PERFORMANCE
@flow air
@process booking
@user anonymous
@traveler adult

Narrative: In order to fly on a date that I want
As an adult
I want to book a flight and receive a confirmation number

Scenario: Purchase an Adult Southwest round-trip Flight and record some performance metrics

Given I am flying a round-trip Southwest Southwest flight
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number