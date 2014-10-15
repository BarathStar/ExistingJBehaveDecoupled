Reset password using an invalid token and verify an error message indicating that the web address used is not valid.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to reset my password
As a Rapid Rewards Member
I want to be shown an error message when I attempt to reset my password by using an invalid token.

Scenario: Rapid Rewards Member tries to reset his password by using an invalid token
Given I am a Rapid Rewards Member not logged-in trying to reset my password with an invalid token
When I enter the new password and a matching password confirmation
Then I see an oops message which informs that the token is not valid