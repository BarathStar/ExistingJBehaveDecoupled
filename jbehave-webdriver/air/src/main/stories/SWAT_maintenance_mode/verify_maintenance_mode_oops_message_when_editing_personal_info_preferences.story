Verify oops message editing personal info preferences with RREdit Maintenance mode is on in as a rr member

Meta:
@process rr
@flow other
@user rr_member
@passenger adult
@dyna_stubs
@not_live
@project SWAT_Maintenance
@project_in_dev

Narrative:
In order to see the oops message
As a rapid rewards member
I want to edit my personal info on Contact Information page when RREdit maintenance mode is On

Scenario: User tries to edit "Personal Information" with RREdit Maintenance Mode On

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I select the preference page
And I choose to navigate the Contact Information link
And I click add/edit for "personal"
Then I should see the maintenance mode oops message