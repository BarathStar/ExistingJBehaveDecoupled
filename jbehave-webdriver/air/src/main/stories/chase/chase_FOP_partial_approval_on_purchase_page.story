Apply for Chase FOP on purchase page to get partial approval

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
I want to apply for Chase credit card on purchase page and get partial aproval
So that I see chase intermediary option page

Scenario: Creating a flight booking with Chase partial approval from purchase page

Given I am flying a round-trip Southwest Southwest flight
And I select a flight for purchase
When I attempt a chase purchase to receive a partial approval
Then I should see Chase intermediary options page
