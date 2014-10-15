Verify the "More Rewards" link.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@not_live

Narrative:
In order to see the "More Rewards" link for the Rapid Rewards Chase Credit Card
As a Rapid Rewards Member (with a Chase Credit Card)
I want to access My Rapid Rewards section on the account sidebar

Scenario: Rapid Rewards Member (with a Chase Credit Card) views "More Rewards" link on the account sidebar
Given I am a Rapid Rewards Member with a Chase Credit Card
When I log in from the account sidebar at the Search Flights page
And I expand My Rapid Rewards section
Then I see the Rapid Rewards Credit Card holder section on the account sidebar