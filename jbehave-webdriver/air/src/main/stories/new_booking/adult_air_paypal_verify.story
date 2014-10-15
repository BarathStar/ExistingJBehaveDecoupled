Purchase a round-trip anytime Air Ticket for an adult paying by paypal

Meta:
@flow air
@process booking
@user anonymous
@passenger adult
@removedFromAirbooking
@not_live
@dyna_stubs

Narrative:
As an adult
In order to fly on a date that I want
I want to be able to book a flight with PayPal
So that

Scenario: verify if PayPal button is enabled on Purchase page

Given I am flying a round-trip Southwest Southwest flight
And I am a customer searching for round-trip flights from the search flights page
When I select, price and view the Purchase page for a flight
Then I am able to select PayPal as a payment method