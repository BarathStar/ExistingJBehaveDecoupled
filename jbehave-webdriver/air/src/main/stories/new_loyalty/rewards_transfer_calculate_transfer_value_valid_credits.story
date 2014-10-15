Calculate Rewards Transfer Value for Rapid Rewards Credtis

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a logged-in RR/A+ member
I want to see how much my loyalty currency is worth on the other carrier
So I can evaluate my options before I transfer currency in credits

Scenario: Calculate the rewards transfer value with a valid transfer amount and enough credits
Given I am on the Rewards Transfer page
When I select the Credits radio button
Then I view the default transfer amount of 0.25 in the transfer field
And I see a message displaying the correct conversion of 0.25 Rapid Rewards credits to 0.25 A+ credits in the "Confirm Transfer Action" section
When I type a transfer amount of 1.25 credits
Then I see a message displaying the correct conversion of 1.25 Rapid Rewards credits to 1.25 A+ credits in the "Confirm Transfer Action" section
