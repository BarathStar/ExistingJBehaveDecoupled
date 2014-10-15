My Travel cancel upcoming trip Altea redirect

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid OPS-1239

Narrative:
In order to cancel a Southwest International itinerary as a RR member I want to navigate to the Cancel reservation page
for an upcoming Southwest international itinerary from within my RR Account My Travel Trip Details page

Scenario: Redirect Cancel Reservation for upcoming International flight from My Travel Detail

Given I am a Rapid Rewards Member with an Altea upcoming trip
When I click to see details of my upcoming trip
And I click on the trip details Cancel Reservation link
Then I am redirected to the Altea Cancel Page
