Verify access to Rapid Rewards Account page through the menu link on "My Snapshot Account" section

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see the Rapid Rewards Account page
As an Anonymous User
I want to create a Rapid Rewards account and access to the Rapid Rewards Account page through the account sidebar

Scenario: User creates a Rapid Rewards account and views the Rapid Rewards Account page accessing through the account sidebar

Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I access my account's information through the account sidebar
Then I see the My Account Snapshot page
When I access my account's Rapid Rewards
Then I see the My Rapid Rewards Account page