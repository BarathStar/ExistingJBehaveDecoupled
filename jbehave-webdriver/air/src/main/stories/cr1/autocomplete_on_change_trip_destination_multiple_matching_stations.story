Enter stations using auto-complete on the Change Trip page

Meta:
@project bookingWidget
@project coda
@flow air
@process change
@user anonymous
@dyna_stubs
@not_live
@project_in_dev

Narrative:
In order to  enter stations using auto-complete on the Change Trip page
As a user
I want to see auto-complete functionality on change trip page
to ensure stations returned are correct

Scenario: Ensure only one station is shown on autocomplete list when entering letters matching that station

Given I am flying a round-trip Southwest Southwest flight
And I have a flight booked
And I am on the Change Air Reservation Page
When I retrieve my reservation for change
And I select to change my entire itinerary and continue
And I select San in the To field
Then I should see San Diego, CA - SAN in To field
