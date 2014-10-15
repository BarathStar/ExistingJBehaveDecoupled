Enter stations using auto-complete on the Interactive Route Map page


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

Narrative:   In order to  enter stations using auto-complete on the Interactive Route Map page
    As a user
    I want to see auto-complete functionality on Interactive Route Map page
    to ensure stations returned are correct


Scenario: Ensure Interactive Route Map Booking widget displays no airport found for inavlid entry in To field

Given I am on the interactive route map page
When I attempt to enter asd in the To field
Then I should see the To field is empty
