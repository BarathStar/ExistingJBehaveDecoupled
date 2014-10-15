Rapid Rewards Destination Finder

Meta:
@project RR2.0
@flow air
@process my southwest account
@global_nav_regression
@user rapid reward member


Narrative:
In order to fly in points
As a Rapid Rewards Members
I want to go to the destination finder in my account

Scenario: help display first time


Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I am on the destination finder page for the first time
Then I view the help page on top of the map

Scenario:  no help display second time


Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I am on the destination finder page for the second time
Then I view the map without a help page on top of the map

Scenario: rapid reward member destination finder


Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I am on the destination finder page
Then I view the default fare range in points