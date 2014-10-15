Loyalty - Verify the display of last activity date on rewards activity detail page for a RR who has no qualified activity in 24 months

Meta:
@dyna_stubs
@flow rr
@process loyalty
@user rr_member
@project last_activity_date
@passenger adult

Narrative:
In order to display Last Activity Date and Expiration countdown in reward activity detail page
As a logged in Rapid Rewards Member
I want to be presented with Last Activity Date, Question Mark icon & Expiration countdown based on the calculation on LastAccrualDate shared by CLCS.

Scenario: Verify the Last Activity Date, Question Mark icon & Expiration countdown information when the LAST_ACTIVITY_DATE toggle is ON

Given I am logged in as Rapid Rewards member with no qualified activity (as defined by Siebel) in last 24 months
When I click on the View Details icon in the Recent Rewards Activity section of My RR page
Then I can see Last Activity Date
And I see a Account is Expired based on rules
When I click on the Question Mark icon
Then I see a flyout dialog box for more information