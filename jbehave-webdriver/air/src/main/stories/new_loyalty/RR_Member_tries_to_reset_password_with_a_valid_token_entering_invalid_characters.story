Verify error message password reset fails

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to reset my password
As a Rapid Rewards Member
I want to be shown an error message when I fail to reset my password because I have entered invalid characters or passwords that do not match

Scenario: Rapid Rewards Member fails to reset his password successfully with a valid token but invalid passwords
Given I am a Rapid Rewards Member not logged-in trying to reset my password with a valid token
When I enter the new password and a matching password confirmation with invalid characters
Then I see an oops message which indicates that the password entered is not valid
When I enter the new password and a password confirmation that do not match
Then I see an oops message which indicates that the passwords do not match