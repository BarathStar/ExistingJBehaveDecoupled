Apply for Chase Credit on purchase page using five digit zip code

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
I want to apply for Chase credit card on purchase page using five digit zip code
So that see the same value for zip1 on purchase page

Scenario: Applying for chase with five digit zip code from purchase page

Given I am flying a round-trip Southwest Southwest flight
And I select a flight for purchase
When I make a chase purchase from purchase page using five digit zip
Then I should see five digit zip1 on purchase page
