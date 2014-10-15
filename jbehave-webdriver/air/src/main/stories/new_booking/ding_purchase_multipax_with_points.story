Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing draft

Narrative:
I want to purchase a round trip with ding and anytime fares

Scenario: Book multi-pax flight with ding fares

Given I want to fly a round-trip flight
And I want to fly with 7 friends
And I want to pay for my flight with points
And I want to use an available Ding fare
When I book a flight that matches my criteria
Then I should receive an air confirmation