Verify the different available links under "My Rapid Rewards Summary" section.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see all the links in my account under "My Rapid Rewards Summary" section
As an Anonymous User
I want to create a Rapid Rewards account and check my account

Scenario: User creates a Rapid Rewards account and views the different links available in his account under
"My Rapid Rewards Summary" section
Given I am a Southwest User at "Create an Account" page
When I complete all the mandatory fields
And I select to create a Rapid Rewards account
And I confirm the account creation
And I click my account link
And I select My Rapid Rewards link
And I view the Tier Status page
Then I see the Tier Earning Details page
When I click the back button
Then I see the My Rapid Rewards Account page
When I access my account's Rapid Rewards Companion Pass Details
Then I see the Companion Pass Status page
When I click the back button
Then I see the My Rapid Rewards Account page
