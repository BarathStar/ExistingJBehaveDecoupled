Apply for Chase FOP on price page to get partial approval

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
I want to apply for Chase credit card on price page and get partial aproval
So that I see chase intermediary options page

Scenario: Creating a flight booking with Chase partial approval

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
And I select the flight and view the Price page
When I attempt a chase purchase to receive a partial approval
Then I should see Chase intermediary options page
