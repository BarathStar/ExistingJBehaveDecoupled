Purchase ding fares in dollars

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As an adult
I want to purchase a ding fare with dollars

Scenario: Should purchase ding fares in dollars
Given I want to fly a round-trip flight
And I want to pay for my flight with dollars
And I want to use an available Ding fare
When I book a flight that matches my criteria
Then I should receive an air confirmation