Loyalty - Verify the display of last activity date on account bar for a RR who has no qualified activity in last 30 days
Meta:
@dyna_stubs
@flow rr
@process loyalty
@user rr_member
@project last_activity_date
@passenger adult

Narrative:
In order to display Last Activity Date in Account bar
As a logged in Rapid Rewards Member
I want to be presented with Last Activity Date and Question Mark icon based on the LastAccrualDate shared by CLCS.

Scenario: Verify the last activity date when the LAST_ACTIVITY_DATE toggle is ON

Given I am logged in as Rapid Rewards member with no qualified activity (as defined by Siebel) in last 30 days
Then I can see Last Activity Date in account bar
When I click on the Question Mark icon
Then I see a flyout dialog box for more information