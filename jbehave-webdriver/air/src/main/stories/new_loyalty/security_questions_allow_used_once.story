Verify that questions are being used only once

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate security questions on Rapid Rewards Registration page
As a rapid rewards member
I want to verify that once a question is selected it is removed from the other dropdown

Scenario: Only allow questions to be used once
Given I am a Southwest User at "Create an Account" page
When I choose my security question 1
Then I should not see my security question 1 choice on my security question 2 options
When I choose my security question 2
Then I should not see my security question 2 choice on my security question 1 options
