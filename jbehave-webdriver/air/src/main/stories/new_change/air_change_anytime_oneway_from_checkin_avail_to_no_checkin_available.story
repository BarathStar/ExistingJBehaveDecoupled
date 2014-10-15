Change existing check-in eligible anytime one way adult flight itinerary to non check-in eligible

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@not_passing broken
@message this test has been failing intermittently to input a new date correctly and thus fails on the check to see if the checkin button is there.  Kevin 12-29-11.

Narrative:
In order to change my flight that was booked for tomorrow
As an adult
I want to travel two days later and receive a updated itinerary

Scenario: Initially book for check-in, modify the PNR and check-in should NOT be available
Given I am flying a one-way Southwest flight
When I book a flight eligible for checkin 1 adult
Then I verify that check-in is available
When I change the flight to a later date
Then I verify that check-in is not available
