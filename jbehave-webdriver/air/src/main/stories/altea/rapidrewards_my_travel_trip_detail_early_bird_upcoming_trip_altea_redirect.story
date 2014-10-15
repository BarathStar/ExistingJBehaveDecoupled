My Travel upcoming trip early bird Altea redirect

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid OPS-1379

Narrative:
As a Rapid Rewards Member
I want to be able to add Early Bird Checkin from within My Rapid Rewards Account My Travel Trip Details page for International Upcoming Trip
so that I can board the plane earlier than the other people

Scenario: Redirect Early Bird purchase for upcoming International flight from My Upcoming Trips Detail page

Given I am a Rapid Rewards Member with an Altea upcoming trip on the My Account page
When I click to see details of my upcoming trip
And I attempt to purchase Early Bird on Trip Details page
Then I am redirected to the Altea EarlyBird page