Rapid Rewards Member stored Credit Card Should Default on the Early Bird Purchase page

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to do a Early Bird upsell with a Stored Credit Card
As a Rapid Rewards Member
I want to verify that the stored credit card is displayed on the Early Bird Purchase page

Scenario: Verify that the stored credit card is displayed on the Early Bird Purchase page
Given I am flying a one-way Southwest flight
And I am a Rapid Rewards Member passenger
When I log in from the account sidebar at the Search Flights page
And I am on the purchase page
And I complete the booking process adding the credit card to the account
And I click the Early Bird upsell button
Then I see my stored credit card preselected on the EarlyBird Purchase Page
