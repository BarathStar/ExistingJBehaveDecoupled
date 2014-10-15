Enter stations using auto-complete on
the Interactive Route Map page To field


Meta:
@project coda
@project bookingWidget
@flow air
@process booking
@live
@dyna_stubs
@user anonymous
@project_in_dev
@storyid OPS-1344
@not_passing draft need to fix search when new booking widget implemented

Narrative:   In order to  enter stations using auto-complete on the Interactive Route Map page
    As a user
    I want to see auto-complete functionality on Interactive Route Map page
    to ensure stations returned are correct


Scenario: Ensure Interactive Route Map page To field shows DAL station

Given I am on the interactive route map page
When I enter dal in the To field
Then I should see the auto-complete list for DAL
