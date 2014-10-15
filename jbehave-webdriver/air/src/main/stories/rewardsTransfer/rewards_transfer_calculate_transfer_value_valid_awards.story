Calculate Rewards Transfer Value for Rapid Rewards Credits

Meta:
@suite rewardsTransfer
@flow awards
@process exchange
@traveler rapid rewards
@storyId ZR207
@not_passing pending Tom|Molly fix

!-- Needs RR account with the 5 different types of awards and certificates

Narrative:
As a logged-in RR/A+ member
I want to see how much my Awards are worth on Airtran
So I can evaluate my options before I transfer currency


Scenario: Select Awards and Certificates to view transfer rates

Given I am on the Rewards Transfer page
When I click on the See More Awards link
And I select a Promo Certificate
Then I see the award conversion amount on the AirTran transfer side of 8 credits
And I should see a confirmation message that matches the selected award

When I select a Standard Certificate
Then I see the award conversion amount on the AirTran transfer side of 8 credits
And I should see a confirmation message that matches the selected award

When I select a Freedom Award
Then I see the award conversion amount on the AirTran transfer side of 32 credits
And I should see a confirmation message that matches the selected award

When I select a Standard Award
Then I see the award conversion amount on the AirTran transfer side of 16 credits
And I should see a confirmation message that matches the selected award

When I select a Freedom Certificate
Then I see the award conversion amount on the AirTran transfer side of 16 credits
And I should see a confirmation message that matches the selected award
