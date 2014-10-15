Reset my password using a valid token, so that I set a new and different password.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to reset my password
As a Rapid Rewards Member
I want to be able to login into the site after resetting my password successfully by using a valid token

Scenario: Rapid Rewards Member resets his password successfully with a valid token
Given I am a Rapid Rewards Member not logged-in trying to reset my password with an valid token
When I enter the new password and a matching password confirmation
Then I see an informative message stating that my password was reset on the Account Login Page
