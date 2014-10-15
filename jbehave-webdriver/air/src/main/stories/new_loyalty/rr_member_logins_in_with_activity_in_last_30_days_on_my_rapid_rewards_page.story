Loyalty - Verify the display of last activity date on my rapid reward page for a RR who has qualified activity in last 30 days

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

Scenario: Rapid Reward user views activity details page displays appropriate information

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
And I have had Qualifying Activity in the last 30 days
When I select My Rapid Rewards link
Then I should see activities on the Recent Rewards Activity
And Last Activity Date and Expiration Countdown are not displayed