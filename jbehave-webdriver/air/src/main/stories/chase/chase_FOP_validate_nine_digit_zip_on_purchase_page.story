Apply for Chase Credit on purchase page using nine digit zip code

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
I want to apply for Chase credit card on purchase page using nine digit zip code
So that I can see it split into five digit zip1 and four digit zip2 on purchase page

Scenario: Apply Chase as FOP with nine digit zip code from purchase page

Given I am flying a round-trip Southwest Southwest flight
And I select a flight for purchase
When I make a chase purchase from purchase page  using nine digit zip
Then I should see five digit zip1 and four digit zip2 on purchase page
