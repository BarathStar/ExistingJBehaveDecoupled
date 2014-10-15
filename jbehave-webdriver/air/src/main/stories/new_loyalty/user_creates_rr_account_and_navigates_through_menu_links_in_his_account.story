Check all the different menu links accessing through "My Account" link on the account sidebar and starting on "My Rapid Rewards" section.

Meta:
@flow rr
@process loyalty
@user rr_member
@global_nav_regression
@traveler adult
@dyna_stubs

Narrative:
In order to see all the menu links in my account
As an Anonymous User
I want to create a Rapid Rewards account and access through the account sidebar ("My Account" link) and
start on "My Rapid Rewards" section

Scenario: User creates a Rapid Rewards account and views the menu links in his account accessing through
the account bar ("My Account" link) and starting on "My Rapid Rewards" section

Given I am a Southwest User at "Create an Account" page
When I complete all the mandatory fields
And I select to create a Rapid Rewards account
And I confirm the account creation
And I click my account link
Then I see the My Account Snapshot page
When I select My Rapid Rewards link
Then I see the My Rapid Rewards Account page
When I view the My Travel page
Then I see the My Travel Snapshot page
When I access my account's Preferences
Then I see the "Username & Password" page
