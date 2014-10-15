Change existing check-in eligible business select one way adult flight itinerary to non check-in eligible

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@not_passing draft
@message drafting for performance issues on radiator

Narrative:
In order to change my flight that was booked for tomorrow
As an adult
I want to travel two days later and receive a updated itinerary

Scenario: change check-in eligible flight to 2 days later and verify check-in is not available
Given I am flying a BusinessSelect one-way Southwest flight
When I book a flight eligible for checkin 1 adult
Then I verify that check-in is available
When I change the flight to a later date
Then I verify that check-in is not available
