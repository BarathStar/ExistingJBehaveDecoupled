Check-in round-trip anytime air ticket for Rapid Rewards member

Meta:
@flow air
@process checkin
@user rr_member
@traveler adult
@not_passing broken

Narrative:
In order to receive my boarding pass
As a rapid rewards member
I want to check-in for my flight

Scenario: Rapid Rewards Member air checkin
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I book a flight
And I retrieve travel documents
Then I view my boarding pass
