Purchase an Adult Air Ticket, for the purpose of checking performance

Meta:
@suite regression
@flow air
@process booking
@user anonymous
@traveler adult
@not_passing draft

Narrative: In order to purchase an Adult Air Ticket, for the purpose of checking performance
As an adult customer
I want to purchase a ticket
so that I can check performance

Scenario: test
Given I am on the Homepage
When I select a one-way Southwest trip from MDW to DTW on the home page
And I select flights and continue to the Purchase page
Then I verify that I am on the Purchase page