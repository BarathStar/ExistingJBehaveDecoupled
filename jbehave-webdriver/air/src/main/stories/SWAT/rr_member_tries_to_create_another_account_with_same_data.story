Verify that RR member is unable to create another account with the same data that the account that already has

Meta:
@flow rr
@process loyalty
@user anonymous
@passenger adult
@project SWAT
@dyna_stubs
@not_live
@project_in_dev

Narrative:
As an adult
I want to verify that, as an already member, I'm not able to create another account with the same data
So that I create another RR account with the same data

Scenario: Trying to create another account with the same data

Given I am a Rapid Rewards Member
And I am a Southwest User at "Create an Account" page
When I fill in the form with the same data and submit
Then I should not be able to create the account