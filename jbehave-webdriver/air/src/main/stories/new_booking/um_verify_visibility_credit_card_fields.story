Verify if Credit Card fields are displayed for Unaccompanied Minor Air Purchase

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
I want to be able to fill credit card information when I return to Purchase page
So that

Scenario: Verify if credit card fields are displayed for an Unaccompanied minor air purchase using credit card instead of PayPal

Given I am flying a round-trip Southwest flight with Nonstop segments
And I am a customer searching for round-trip flights from the search flights page
When I select, price and view the Purchase page for a flight
And I attempt to book a flight as UM and the payment method is <payment>
Then I should be able to fill the credit card information

Examples:
|payment|
|PayPal|