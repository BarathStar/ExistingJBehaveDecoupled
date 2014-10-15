Change a round-trip anytime flight as a Rapid Rewards member purchased in dollars

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@not_passing draft

Narrative:
In order to fly on a date that I can
As an adult
I want to travel on a different date and receive an updated itinerary

Scenario: Rapid Rewards Member air change
Given I am flying a round-trip Southwest Southwest flight
When I login as a valid Rapid Rewards member on the Rapid Rewards Account page
And I have a flight booked
And I change the flight to a later date
Then I should see the itinerary change confirmation page that has RR number displayed
