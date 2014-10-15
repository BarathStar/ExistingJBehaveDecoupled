Calculate Rewards Transfer Value for Rapid Rewards Credits

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a logged-in RR/A+ member
I want to see how much my Southwest loyalty currency is worth in Airtran credits
So I can evaluate my options before I transfer currency


Scenario: Calculate the rewards transfer value with an invalid transfer amount and not enough credits
Given I am on the Rewards Transfer page
When I select the Credits radio button
And I type a transfer amount of .1 credits
Then I see a message displaying the correct conversion of .1 Rapid Rewards credits to 0.1 A+ credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid credit amount

When I type a transfer amount of 0 credits
Then I see a message displaying the correct conversion of 0 Rapid Rewards credits to 0 A+ credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid credit amount

When I type a transfer amount of NaN credits
Then I see a message displaying the correct conversion of NaN Rapid Rewards credits to 0 A+ credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid credit amount

When I clear the Credit transfer amount
Then I see a message displaying the correct conversion of 0 Rapid Rewards credits to 0 A+ credits in the "Confirm Transfer Action" section

When I type a transfer amount of 400 credits
Then I see a message displaying the correct conversion of 400 Rapid Rewards credits to 400 A+ credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for exceeding the transferable credit amount

When I type a transfer amount of 401 credits
Then I see a message displaying the correct conversion of 401 Rapid Rewards credits to 401 A+ credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for exceeding the transferable credit amount