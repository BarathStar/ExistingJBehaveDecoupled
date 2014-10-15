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

Narrative:
In order to enter stations using auto-complete on the vacations page
As a user
I want to see auto-complete functionality on vacations page
to ensure stations returned are correct

Scenario: Ensure invalid route error is shown on autocomplete list when entering invalid stations

Given I am on the Vacations page
When I select AUS in the FromOnVacations field
And I attempt to enter SAT in the ToOnVacations field
Then I should see the ToOnVacations field is empty
