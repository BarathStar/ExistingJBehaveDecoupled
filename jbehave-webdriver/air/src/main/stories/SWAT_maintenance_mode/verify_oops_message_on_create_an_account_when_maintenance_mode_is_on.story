Verify oops message on enroll with RREdit Maintenance mode is on as an adult

Meta:
@process rr
@flow other
@user Anonymous
@passenger adult
@not_live
@dyna_stubs
@project SWAT_Maintenance
@project_in_dev

Narrative:
In order to see the oops message
As a future rapid rewards member
I want to enroll when maintenance mode is on

Scenario: User tries to Create an Account when the Site is in maintenance mode

Given I am an Anonymous User on the Account Login Page
When I click on the "create an account" on the login page
Then I should see the maintenance mode oops message