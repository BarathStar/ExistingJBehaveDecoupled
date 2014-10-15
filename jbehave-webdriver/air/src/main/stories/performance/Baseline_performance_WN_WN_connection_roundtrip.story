Performance-Southwest-Southwest-Connection-RoundTrip

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

Scenario: Southwest

Given I am flying a round-trip SouthwestConnect flight with 1 stop segments
And I am on the Homepage
When I book a flight
Then I receive an air confirmation number