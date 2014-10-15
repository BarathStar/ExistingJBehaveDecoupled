Rewards Transfer A+ Login with RR member that only has transferable Points

Meta:
@suite rewardsTransfer
@flow awards
@process exchange
@traveler customer
@storyId ZR206
@not_passing pending
@dyna_stubs

!-- This story requires special RR account

Narrative:
As a rapid rewards member
I want to navigate to the AirTran A+ login screen from multiple points
So I can access the rewards transfer login page and view my transferable Points

Scenario: Login to Rewards Transfer Page and view transferable Points

Given I am logged in as a pointsOnly Rapid Rewards member
When I view the My Rapid Rewards page
And I select the Transfer between A+ Rewards and Rapid Rewards button
Then I view the Rewards Transfer login page
When I login with a valid A+ member id and password
Then I view the Rewards Transfer page
And I view my transferable Points
And I do not see transferable Awards and Certificates
And I do not see transferable Credits
