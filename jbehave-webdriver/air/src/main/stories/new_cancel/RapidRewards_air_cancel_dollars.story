Cancel a round-trip anytime flight as a Rapid Rewards member purchased in dollars

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult

Narrative:
In order to cancel my flight purchased in dollars
As an adult
I want to receive a cancellation confirmation

Scenario: Rapid Rewards Member air cancel
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I book a flight
And I cancel the flight
Then I view the flight cancellation confirmation
