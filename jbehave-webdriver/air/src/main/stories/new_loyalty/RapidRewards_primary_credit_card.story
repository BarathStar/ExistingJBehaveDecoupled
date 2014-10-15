Rapid Rewards Verify Primary Stored Credit Card

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to set the payment information
As a Rapid Rewards Member
I want to verify that my first stored credit card is also the primary one

Scenario: Verify that the first credit card to store is set as primary
Given I am a Rapid Rewards Member passenger
And I login as a Rapid Rewards Member from Login page
When I view the Snapshot page
And I select the preference page
And I access my account's Payment Information
And I select to see the edit mode for my Payment Information
And I complete all the Credit Card information
And I update my Payment Information information
Then I see that the credit card was set as primary