Verify access to Account Preferences page through the menu link on "My Snapshot Account" section

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see the Account Preferences page
As an Anonymous User
I want to create a Rapid Rewards account and access to the Account Preferences page through the account sidebar

Scenario: User creates a Rapid Rewards account and views the Account Preferences page accessing through the account sidebar

Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I access my account's information through the account sidebar
Then I see the My Account Snapshot page
When I access my account's Preferences
Then I see the "Username & Password" page