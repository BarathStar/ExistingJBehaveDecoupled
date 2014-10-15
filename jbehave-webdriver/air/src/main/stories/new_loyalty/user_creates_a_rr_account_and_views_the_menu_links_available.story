Verify menu links (Snapshot, My Travel, My Rapid Rewards and My Preferences)

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to see all the menu links in my account
As an Anonymous User
I want to create a Rapid Rewards account and check my account

Scenario: User creates a Rapid Rewards account and views the menu links available
Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I decide to complete my account's Preferences
And I access my account's Snapshot
Then I see the My Account Snapshot page
When I access my account's Travels
Then I see the My Travel Snapshot page
When I access my account's Rapid Rewards
Then I see the My Rapid Rewards Account page
When I access my account's Preferences
Then I see the "Username & Password" page