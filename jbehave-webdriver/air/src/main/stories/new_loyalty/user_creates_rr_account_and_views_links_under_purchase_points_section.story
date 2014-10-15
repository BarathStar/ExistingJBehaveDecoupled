Verify the different available links under "Purchase Points" section.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@points_dot_com_off

Narrative:
In order to see all the links in my account under "Purchase Points" section
As an Anonymous User
I want to create a Rapid Rewards account and check my account

Scenario: User creates a Rapid Rewards account and views the different links available in his account under "Purchase Points" section
Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I access my account's information through the account sidebar
And I select My Rapid Rewards link
And I decide to obtain more points
Then I see the Purchase Points page
When I click the back button
Then I see the My Rapid Rewards Account page
When I decide to get more points
Then I see the Purchase Points page
When I click the back button
Then I see the My Rapid Rewards Account page
