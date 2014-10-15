Display confirmation shell after submitting A+ currency transfer

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to be taken to the confirmation page
When I transfer A+ Rewards Credits to RR credits

Scenario: User on rewards transfer page transfers A+ credits
Given I am on the Rewards Transfer page with A+ credits
When I enter 0.25 A+ credits
And I agree to the transfer rules and continue to transfer
Then I should see the Transfer Confirmation page

Scenario: Transferring A+ credits fails
Given I am logged in as Rapid Rewards member whose account cannot be debited
And I navigate to the Rewards Transfer page as an A+ user with a failing account
When I enter 0.25 A+ credits
And I agree to the transfer rules and continue to transfer
Then I should see a failure message on the Transfer Confirmation page
