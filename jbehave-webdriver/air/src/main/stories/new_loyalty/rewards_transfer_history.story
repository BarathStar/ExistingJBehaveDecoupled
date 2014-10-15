View Rewards Activity Detail Page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a Rapid Rewards user who has transferred currency between Southwest RR and AirTran A+
I want to view my past transactions on the rewards activity page
So that I can confirm and review my transfer activity

Scenario: Rapid Reward user views credit in rewards transfer history
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
And I am on the Rapid Rewards history page to view APlus credit transfer activity
Then I should see a APlus debit transfer transaction for 2 credits
And I should see a Reward transfer reversal transaction for 300 points
And I should see a Reward transfer reversal transaction for 8 credits