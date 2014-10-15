Verify access to Account Travel Snapshot page through the menu links on "My Snapshot Account" section

Meta:
@flow rr
@process loyalty
@user rr_member
@global_nav_regression
@traveler adult
@dyna_stubs

Narrative:
In order to see the Account Travel Snapshot page
As an Anonymous User
I want to create a Rapid Rewards account and access to the Account Travel Snapshot page through the account sidebar

Scenario: User creates a Rapid Rewards account and views the Account Travel Snapshot page in his account accessing through the account sidebar

Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I access my account's information through the account sidebar
Then I see the My Account Snapshot page
When I access my account's Travels
Then I see the My Travel Snapshot page