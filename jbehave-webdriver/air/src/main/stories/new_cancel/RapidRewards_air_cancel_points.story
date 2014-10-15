Cancel a round-trip anytime flight as a Rapid Rewards member purchased in points

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@not_passing draft

Narrative:
In order to cancel my flight purchased in points
As an adult
I want to receive a cancellation confirmation

Scenario: Rapid Rewards Member air cancel
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I purchase points
Then I receive my purchase confirmation
When I book a flight with points
Then I should receive an air confirmation
When I cancel the flight
Then I view the flight cancellation confirmation
