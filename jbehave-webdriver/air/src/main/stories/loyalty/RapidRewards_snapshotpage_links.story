Rapid Rewards Snapshot page links

Meta:
@project RR2.0
@flow air
@process my southwest account
@user rapid reward member


Narrative: In order to navigate through the links on the snapshot page
As a Rapid Rewards Members
I want to view my past trips and upcoming trips
I want to view my points progress
I want to view my profile


Scenario: My Travel Link

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Travel link
Then I view my past and upcoming trips

Scenario: My Rapid Rewards Link

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select My Rapid Rewards link
Then I view my points progress

Scenario: My Preferences Link

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select the link My Preferences
Then I should see the My Preferences page