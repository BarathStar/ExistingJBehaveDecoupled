Verify a credit card is added once even when you click twice on submit button

Meta:
@flow rr
@process loyalty
@user rr_member
@passenger adult
@not_live
@dyna_stubs

Narrative:
In order to add a credit card
As a Rapid Rewards Member
I want to see that one credit card is added even if I click twice in submit button

Scenario: Verify a credit card is added once even when you click twice on submit button

Given I am a Rapid Rewards Member passenger
And I am logged in and on My Rapid Rewards page
When I view the Snapshot page
And I select the preference page
And I access my account's Payment Information
And I select to see the edit mode for my Payment Information
Then I see the Payment Information page is editable
When I complete all the Credit Card information
And I click on update my Payment Information information twice
Then I see that the credit card was added one time