Apply for Chase FOP on purchase page and cancel application process

Meta:
@project 5.22ffClassicRetirement
@project_in_dev
@flow air
@process booking
@traveler adult
@user Anonymous
@dyna_stubs

Narrative:

As a customer
I want to apply for Chase credit card on purchase page and cancel application process
So that I can come back to the price page

Scenario: Applying for chase FOP and cancelling the application process from urchase page

Given I am flying a round-trip Southwest Southwest flight
And I select a flight for purchase
When I cancel chase application
Then I should redirect back to price page
