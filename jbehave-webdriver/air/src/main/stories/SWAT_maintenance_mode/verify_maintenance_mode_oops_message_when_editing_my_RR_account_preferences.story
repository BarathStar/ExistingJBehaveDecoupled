Verify oops message editing rr account preferences with RREdit Maintenance mode is on in as a rr member

Meta:
@process rr
@flow other
@user rr_member
@dyna_stubs
@not_live
@passenger adult
@project SWAT_Maintenance
@project_in_dev

Narrative:
In order to see the oops message
As a rapid rewards member
I want to edit my user name and password on my preferences page when RREdit maintenance mode is On

Scenario: User tries to edit "User Name and Password" with toggle RREdit maintenance mode is On

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select the preference page
And I click edit for User Name and Password
Then I should see the maintenance mode oops message