Verify oops message showed trying to view travel funds unused on Travel Funds Page

Meta:
@flow air
@process checkin
@user anonymous
@passenger adult
@dyna_stubs
@not_live

Narrative:
As an user
I want to see an special oops message showed trying to use travel funds used in a flight that has not been departed
So that

Scenario: Verify oops message showed trying to view travel funds unused

Given I have 0 amount travel funds balance with 0 refundable amount and 0 nonrefundable amount
And I am on the Home page
When I go to the View Reservation page through the Air menu
And I click on the View Travel Funds link
And I fill in travel fund's fields with valid data
And I confirm to Check the Travel Funds
Then I see the correct balances and totals for the Travel Funds