Purchase ding fares using mixed fare types

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@removedFromAirbooking

Narrative:
As an adult
I want to purchase a round trip with ding and anytime fares

Scenario: Should purchase round trip with ding and anytime fares in dollars
Given I want to fly a round-trip flight
And I want to pay for my flight with dollars
And I want to use an available Ding fare for my outbound flight
And I want to use an available anytime fare for my inbound flight
When I book a flight that matches my criteria
Then I should receive an air confirmation

Scenario: Should purchase round trip with anytime and ding fares in dollars
Given I want to fly a round-trip flight
And I want to pay for my flight with dollars
And I want to use an available anytime fare for my outbound flight
And I want to use an available Ding fare for my inbound flight
When I book a flight that matches my criteria
Then I should receive an air confirmation