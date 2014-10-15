Verify when a RR is logged in is not allowed enroll someone else

Meta:
@flow rr
@process loyalty
@user rr
@passenger rr_member
@dyna_stubs
@not_passing new_global_nav

Narrative:
As a Rapid Rewards Member logged in
In order to enroll someone else
I should be logged out in order to do it
So that


Scenario: Verify when a RR is logged in the enroll links should not be present

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
Then I should not see enroll links
When I log out from my Rapid Reward Account
And I am on the Home page
Then I should see enroll links