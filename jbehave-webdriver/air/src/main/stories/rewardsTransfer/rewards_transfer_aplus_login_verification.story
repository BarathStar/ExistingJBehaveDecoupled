Verify A+ Login credentials on Rewards Transfer Login page and view Transferable Awards.Credits, and Points

Meta:
@suite rewardsTransfer
@flow awards
@process exchange
@traveler customer
@storyId ZR214
@dyna_stubs
@not_passing no data Tom

Narrative:
As a rapid rewards member
I want to navigate to the AirTran A+ login screen from multiple points
So I can access the rewards transfer login page and view my transferable Awards, Credits, and Points

Scenario: Login to Rewards Transfer Page and view transferable Awards, Credits, and Points

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I select the Transfer A+ Rewards/Rapid Rewards quick links option
Then I view the Rewards Transfer login page
When I login with a valid A+ member id and password
Then I view the Rewards Transfer page

Scenario: Login to Rewards Transfer Page and view transferable Awards, Credits, and Points with A+ Email

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I view the My Rapid Rewards page
And I select the Transfer between A+ Rewards and Rapid Rewards button
Then I view the Rewards Transfer login page
When I login with a valid A+ email and password
Then I view the Rewards Transfer page
And I should see the option to transfer A+ Credits
