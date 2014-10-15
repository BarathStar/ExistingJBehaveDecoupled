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

Scenario: Calculate the rewards transfer value with a valid transfer amount and enough points
Given I am on the Rewards Transfer page
When I enter 300 points
Then I see the credit conversion amount on the AirTran transfer side of 0.25 credits
And I see a message displaying the correct conversion of 300 points to 0.25 credits in the "Confirm Transfer Action" section
