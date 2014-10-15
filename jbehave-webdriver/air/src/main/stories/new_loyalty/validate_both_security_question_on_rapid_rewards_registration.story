Verifies that security validations are prompted on the Rapid Rewards Registration page for a new Rapid Rewards Member

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:

In order to validate security questions on Rapid Rewards Registration page
As a new rapid rewards member
I want to verify that I am prompted to answer all of the security questions

Scenario: Both security questions should be selected on Rapid Rewards Registration page
Given I am a Southwest User at "Create an Account" page
When I attempt to confirm the account creation
Then I should get an Oops message requiring me to select both security questions
When I choose my security question 1
And I attempt to confirm the account creation
Then I should get an Oops message requiring me to select security question 2
And I should not get an Oops message requiring me to select security question 1
When I deselect my security question 1
And I choose my security question 2
And I attempt to confirm the account creation
Then I should get an Oops message requiring me to select security question 1
And I should not get an Oops message requiring me to select security question 2
