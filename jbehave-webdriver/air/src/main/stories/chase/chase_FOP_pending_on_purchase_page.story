Apply for Chase FOP on purchase page to get pending approval status

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
I want to apply for Chase credit card on purchase page and get a pending status
So that I have to use other FOP for the flight booking

Scenario: Creating a flight booking with Chase approval pending as FOP

Given I am flying a round-trip Southwest Southwest flight
And I select a flight for purchase
When I attempt a chase purchase from purchase page to receive a pending approval status
Then I should be forced to use a different FOP on purchase page
