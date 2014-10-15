Verify maintenance mode when try to view reservation as anonymous user

Meta:
@process view
@flow air
@user anonymous
@passenger adult
@dyna_stubs
@project SWAT_Maintenance
@not_live
@project_in_dev

Narrative:
In order to see the oops message
As a anonymous user
I want to view reservation page when View Reservation maintenance mode is On

Scenario: User tries to view reservation with toggle Air View Maintenance mode on

Given I am on the Home page
When I go to the View Reservation page through the Air menu
Then I should see the maintenance mode oops message