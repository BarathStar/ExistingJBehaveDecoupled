Verify Oops messages with invalid Login credentials on Rewards Transfer Login page

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
As a rapid rewards member
I want to navigate to the AirTran A+ login screen from multiple points
So I can see oops messages for invalid login credentials

Scenario: Invalid Login to Rewards Transfer Page
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I select the Transfer between A+ Rewards and Rapid Rewards button
Then I view the Rewards Transfer login page
When I login with an invalid A+ member id and password
Then I will see invalid A+ credentials Oops message

Scenario: Southwest Rapid Rewards Members and A+ Names Mismatched
Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I select the Transfer between A+ Rewards and Rapid Rewards button
Then I view the Rewards Transfer login page
When I enter a member id that does not match my Rapid Rewards name
Then I see a name mismatch Oops message
