Create a Rapid Rewards account and complete my Payment Information afterwards, so that I can set my payment preferences

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to set my payment preferences
As an Anonymous User
I want to create a Rapid Rewards account and complete the Payment Information afterwards

Scenario: User creates a Rapid Rewards account and completes his Payment Information afterwards
Given I am a Southwest User at "Create an Account" page
When I create a Rapid Rewards account
And I decide to complete my account's Preferences
And I access my account's Payment Information
And I select to see the edit mode for my Payment Information
Then I see the Payment Information page is editable
When I complete all the Credit Card information
And I update my Payment Information information
Then I see the Payment Information page is not editable
And I see the Payment Information section can be edited