Book a flight for aggressive confirmation page

Meta:
@flow air
@process booking
@user anonymous
@not_passing draft

Narrative: In order to book a flight for aggressive confirmation page
As a customer
I want to see the rating for listed hotels

Scenario: Aggressive confirmation with hotel listings

Given I am flying a round-trip Southwest Southwest flight
When I book a flight
Then for each hotel listed there should be a rating