Rewards Transfer A+ Login with RR member that only has transferable Credits

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a rapid rewards member
I want to navigate to the AirTran A+ login screen from multiple points
So I can access the rewards transfer login page and view my transferable Credits

Scenario: Login to Rewards Transfer Page and view transferable credits
Given I am on the Rewards Transfer page
When I select the Credits radio button
Then I view the default transfer amount of 0.25 in the transfer field
And I see a message displaying the correct conversion of 0.25 Rapid Rewards credits to 0.25 A+ credits in the "Confirm Transfer Action" section