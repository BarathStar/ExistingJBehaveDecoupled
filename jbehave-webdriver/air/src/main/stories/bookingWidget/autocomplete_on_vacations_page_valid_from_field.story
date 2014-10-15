Enter stations using auto-complete on the Southwest vacations page

Meta:
@flow air
@process booking
@project coda
@project bookingWidget
@user anonymous
@dyna_stubs
@live
@project_in_dev
@storyid OPS-1341
@not_passing draft need to fix search when new booking widget implemented

Narrative:
In order to search for discounted vacation bundles
As a user
I want to see auto-complete functionality on the vacation planner page with valid stations

Scenario: Ensure vacation page shows dal station in from field

Given I am on the Vacations page
When I select DAL in the FromOnVacations field dropdown
Then I should see DAL in FromOnVacations field
