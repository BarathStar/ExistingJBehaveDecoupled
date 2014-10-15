Apply for Chase Credit on price page using nine digit zip code

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
I want to apply for Chase credit card on price page using nine digit zip code
So that I can see it split into five digit zip1 and four digit zip2 on purchase page

Scenario: Apply Chase as FOP with nine digit zip code from price page

Given I am flying a round-trip Southwest Southwest flight
And I am on the select flights page
And I select the flight and view the Price page
When I make a chase purchase from price page  using nine digit zip
Then I should see five digit zip1 and four digit zip2 on purchase page
