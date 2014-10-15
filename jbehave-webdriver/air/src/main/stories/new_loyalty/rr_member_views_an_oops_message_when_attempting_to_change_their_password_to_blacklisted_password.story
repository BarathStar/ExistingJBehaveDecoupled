Verify an oops message specifying that the password they entered contains a blacklisted password.

Meta:
@flow rr
@process loyalty
@user rr_member
@dyna_stubs
@project_in_dev

Narrative:
In order to reset my password
As a Rapid Rewards Member
I want to be shown an error message when I fail to reset my password because I have entered a blacklisted password

Scenario: Rapid Rewards Member views an oops message in "My Preferences" page when they try to change their password to a new one, which contains a blacklisted word
Given I am a Rapid Rewards Member
When I login as a Rapid Rewards Member from Login page
And I click on My Preferences page
And I click on edit Username & Password button
And I attempt to change my password with a blacklisted password qwerty
Then I see oops message for blacklisted password qwerty in my account page

