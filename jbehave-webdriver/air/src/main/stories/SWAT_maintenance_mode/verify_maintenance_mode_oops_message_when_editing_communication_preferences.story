Verify oops message editing communication preferences with RREdit Maintenance mode is on in as a rr member

Meta:
@process rr
@flow other
@user rr_member
@passenger adult
@not_live
@dyna_stubs
@project SWAT_Maintenance
@project_in_dev

Narrative:
In order to see the oops message
As a rapid rewards member
I want to edit my email subscription info on Communication Preferences page when RREdit maintenance mode is On

Scenario: User tries to edit "email subscription" info with toggle RR Edit Maintenance mode in on

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select the preference page
And I select the Communication Preferences link
Then I should see the maintenance mode oops message