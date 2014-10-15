Verify A+ Login credentials on Rewards Transfer Login page and view Transferable Awards.Credits, and Points

Meta:
@suite rewardsTransfer
@flow awards
@process exchange
@traveler customer
@storyId ZR214
@dyna_stubs
@not_passing data Tom

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: A valid A+ user logs in with no A+ credits

Given I am logged in as Rapid Rewards member without A+ credits on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I select the Transfer between A+ Rewards and Rapid Rewards button
Then I view the Rewards Transfer login page
When I login with a valid A+ email and password without A+ credits
Then I view the Rewards Transfer page
And I should not see the option to transfer A+ Credits