Verify Oops messages when you submit without agreeing to rules and conditions on Rewards Transfer page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a A+ rewards member I am on rewards transfer page
I click on Continue without checking the agree to rules of transfer credit checkbox
Then I want to verify that error message is displayed

Scenario: User on rewards transfer does not check agree to rules checkbox
Given I am on the Rewards Transfer page with A+ credits
When I continue without agreeing to rules of credit transfer
Then I should see the read and agree to the transfer rules oops message

When I enter 0.25 A+ credits
And I continue without agreeing to rules of credit transfer
Then I should see the read and agree to the transfer rules oops message

When I enter 0.27 A+ credits
And I continue without agreeing to rules of credit transfer
Then I should not see the read and agree to the transfer rules oops message
And I see the correct error for transferring an invalid A+ credit amount