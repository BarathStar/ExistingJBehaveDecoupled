Purchase a round-trip anytime air ticket for an adult with an invalid card

Meta:
@flow air
@process booking
@user anonymous
@traveler adult

Narrative:
In order to book a flight
As an adult
I want to try to purchase a flight with a invalid credit card

Scenario: adult air purchase with invalid credit card
Given I am flying a round-trip Southwest Southwest flight
And my credit card is unacceptable
When I book a flight
Then I see the credit card denied error
