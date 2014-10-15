My Travel change upcoming trip Altea redirect

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

Narrative: In order to modify a Southwest International itinerary as a RR member I want to navigate to the Modify
Select page for my Southwest International itinerary from the My Travel Detail page

Scenario: Redirect Change Reservation for upcoming International flight from My Travel Detail

Given I am a Rapid Rewards Member with an Altea upcoming trip
When I click to see details of my upcoming trip
And I click on the trip details Change Reservation link
Then I am redirected to the Altea Change Reservation page
