Verify you get the confirmation number when you purchase an Unaccompanied Minor Air ticket

Meta:
@flow air
@process booking
@user anonymous
@passenger UM
@dyna_stubs
@not_live


Narrative:
As an unaccompanied minor
In order to fly on a date that I want
I want to book a flight and receive a confirmation number
So that


Scenario: Verify you get the confirmation number when you purchase an Unaccompanied Minor Air Ticket

Given I am flying a round-trip Southwest flight with Nonstop segments
And I am a customer searching for round-trip flights from the search flights page
When I select, price and view the Purchase page for a flight
And I fill passenger and parent guardian information and the payment method is <payment>
And I complete the credit card information and finish the purchase
Then I receive an air confirmation number

Examples:
|payment|
|Credit_Card|