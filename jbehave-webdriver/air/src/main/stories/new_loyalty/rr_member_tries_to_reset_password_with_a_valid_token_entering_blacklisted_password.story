Verify error message password reset fails

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@project_in_dev

Narrative:
In order to reset my password
As a Rapid Rewards Member
I want to be shown an error message when I fail to reset my password because I have entered a blacklisted password

Scenario: Rapid Rewards Member fails to reset his password with a valid token but blacklisted password
Given I am a Rapid Rewards Member not logged-in trying to reset my password with a valid token
When I enter the new password and a matching password confirmation containing a blacklisted password qwerty
Then I see an oops message which indicates that the password entered is blacklisted
