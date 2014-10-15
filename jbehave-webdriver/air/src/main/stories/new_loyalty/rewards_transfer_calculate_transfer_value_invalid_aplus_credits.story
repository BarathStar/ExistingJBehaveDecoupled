Calculate Rewards Transfer Value for Rapid Rewards Credits

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a logged-in RR/A+ member
I want to see how much my Airtran loyalty currency is worth in Southwest credits
So I can evaluate my options before I transfer currency

Scenario: Calculate the rewards transfer value with an invalid transfer amount and not enough credits
Given I am on the Rewards Transfer page with A+ credits
When I enter 0.1 A+ credits
Then I see a message displaying the correct conversion of .1 A+ credits to 0.1 Rapid Rewards credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid A+ credit amount

When I enter 0 A+ credits
Then I see a message displaying the correct conversion of 0 A+ credits to 0 Rapid Rewards credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid A+ credit amount

When I enter NaN A+ credits
Then I see a message displaying the correct conversion of NaN A+ credits to 0 Rapid Rewards credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid A+ credit amount

When I enter 20000 A+ credits
Then I see a message displaying the correct conversion of 20000 A+ credits to 20000 Rapid Rewards credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for exceeding the transferable A+ credit amount
