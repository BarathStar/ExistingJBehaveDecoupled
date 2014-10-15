Verify if the auto complete function at account number / username is disabled at home page as rapid rewards user

Meta:
@flow rr
@process view
@user rr_member
@passenger adult
@not_live
@dyna_stubs

Narrative:
As a Adult
I am on the home page and I couldn't be able to use auto complete at account number field
So that

Scenario: Verify if the auto complete function at account number / username is disabled

Given I am a Rapid Rewards Member
When I log in from the home page
And I log out from my Rapid Reward Account
And I attempt to use autocomplete to complete account number field
Then I shouldn't see the account number completed
