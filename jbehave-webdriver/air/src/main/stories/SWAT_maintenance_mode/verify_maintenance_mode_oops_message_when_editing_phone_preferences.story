Verify oops message editing phone preferences with RREdit Maintenance mode is on as a rr member

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
I want to edit my phone info on Contact Information page when RREdit maintenance mode is On

Scenario: User tries to edit "Phone" with RREdit Maintenance Mode On

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select the preference page
And I choose to navigate the Contact Information link
And I click add/edit for "phone"
Then I should see the maintenance mode oops message