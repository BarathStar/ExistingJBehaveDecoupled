Should purchase ding fares in points

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an adult
I want to purchase a ding fare with points

Scenario: Should purchase ding fares in points
Given I want to fly a round-trip flight
And I want to pay for my flight with points
And I want to use an available Ding fare
When I book a flight that matches my criteria
Then I should receive an air confirmation