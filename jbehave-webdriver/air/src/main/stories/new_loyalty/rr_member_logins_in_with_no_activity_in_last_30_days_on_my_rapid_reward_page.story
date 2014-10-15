Loyalty - Verify the display of last activity date on my rapid reward page for a RR who has no qualified activity in last 30 days

Meta:
@dyna_stubs
@flow rr
@process loyalty
@user rr_member
@project last_activity_date
@passenger adult

Narrative:
In order to display Last Activity Date and Expiration countdown in Recent Activity section of My RR page
As a logged in Rapid Rewards Member
I want to be presented with Last Activity Date, Question Mark icon & Expiration countdown based on the calculation on LastAccrualDate shared by CLCS.

Scenario: Verify the Last Activity Date, Question Mark icon & Expiration countdown information when the LAST_ACTIVITY_DATE toggle is ON

Given I am logged in as Rapid Rewards member with no qualified activity (as defined by Siebel) in last 30 days
When I select My Rapid Rewards link
Then I can see Last Activity Date in recent reward activity section of my RR page
And I see a Account Expiration countdown in month(s) & days(s) based on rules in recent reward activity section of my RR page
When I click on the Question Mark icon
Then I see a flyout dialog box for more information