Using the back button from the confirmation page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a user on the Currency Transfer confirmation page
I want to view a page with options when I click back after a successful or failed currency transfer
So that I do not nullify my transfer

Scenario: View an opps message when the user hits the back button from the Rewards Transfer Confirmation page
Given I am on the Rewards Transfer Confirmation page after a successful transfer
When I click the back button
Then I should see the please do not use your back button from the Rewards Transfer Confirmation page oops message