Calculate Rewards Transfer Value for Rapid Rewards Points

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a logged-in RR/A+ member
I want to see how much my loyalty currency is worth on the other carrier
So I can evaluate my options before I transfer currency

Scenario: Calculate the rewards transfer value with an invalid transfer amount and enough points, or valid transfer amound and not enough points
Given I am on the Rewards Transfer page
When I enter 50 points
Then I see the credit conversion amount on the AirTran transfer side of 0.04 credits
And I see a message displaying the correct conversion of 50 points to 0.04 credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid points amount

When I enter 0 points
Then I see the credit conversion amount on the AirTran transfer side of 0 credits
And I see a message displaying the correct conversion of 0 points to 0 credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid points amount

When I enter NaN points
Then I see the credit conversion amount on the AirTran transfer side of 0 credits
And I see a message displaying the correct conversion of NaN points to 0 credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for transferring an invalid points amount

When I enter 990000000 points
Then I see the credit conversion amount on the AirTran transfer side of 825,000 credits
And I see a message displaying the correct conversion of 990,000,000 points to 825,000 credits in the "Confirm Transfer Action" section
When I agree to the transfer rules and continue to transfer
Then I see the correct error for exceeding the transferable amount
