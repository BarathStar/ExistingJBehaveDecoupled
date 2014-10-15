Verify an oops message specifying that the password they entered is invalid.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@project_in_dev

Narrative:
In order to change the password
As a Rapid Rewards Member
I want to verify that entering invalid passwords results in an oops message.

Scenario: Change password fails with invalid passwords
Given I am a Rapid Rewards Member
When I login as a Rapid Rewards Member from Login page
And I click on My Preferences page
And I click on edit Username & Password button
And I attempt to change my password with password containing invalid characters blah&blah@
Then I see oops message for invalid password in my account page
When I attempt to change my password with a short password blah
Then I see oops message for a short password in my account page
