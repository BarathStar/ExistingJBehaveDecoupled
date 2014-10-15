Apply for Chase FOP on price page and cancel application process

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
I want to apply for Chase credit card on price page and cancel application process
So that I can come back to the price page

Scenario: Applying for chase FOP and cancelling the application process from price page

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
And I select the flight and view the Price page
When I cancel chase application
Then I should redirect back to price page
