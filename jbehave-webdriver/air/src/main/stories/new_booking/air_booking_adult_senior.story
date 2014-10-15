Purchase a round-trip anytime air ticket for an adult and a senior

Meta:
@flow air
@process booking
@user anonymous
@global_nav_regression
@traveler adul_&senior

Narrative:
In order to fly on a date that I want
As an adult traveling with a senior
I want to book a flight and receive both confirmation numbers

Scenario: adult and senior anytime air purchase
Given I am flying a round-trip Southwest Southwest flight
And I have one adult and senior selected on the select flight page
When I select flights and finish booking
Then I receive an air confirmation number
