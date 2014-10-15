Enter stations using auto-complete on the Vacations page

Meta:
@project bookingWidget
@project coda
@flow air
@process booking
@user anonymous
@dyna_stubs
@live
@project_in_dev
@storyid OPS-1341
@not_passing draft need to fix search when new booking widget implemented

Narrative:
In order to enter stations using auto-complete on the vacations page
As a user
I want to see auto-complete functionality on vacations page
to ensure stations returned are correct

Scenario: Ensure valid route is shown on autocomplete list when entering valid stations

Given I am on the Vacations page
When I select DAL in the FromOnVacations field dropdown
When I select AUS in the ToOnVacations field dropdown
Then I should see DAL in FromOnVacations field
And I should see AUS in ToOnVacations field