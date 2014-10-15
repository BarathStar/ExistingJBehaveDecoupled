My Travel Upcoming Trips List add companion to upcoming trip Altea redirect

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid OPS-1238

Narrative:
In order to book a companion fare for my existing Altea booking
As a Rapid Rewards member with an upcoming Altea itinerary
I want to enter the Altea companion purchase flow for that itinerary from the My Travel Upcoming Trips List page

Scenario:Redirect Add a Companion for an upcoming Altea flight from My Travel Upcoming Trips List page

Given I am a Rapid Rewards Member with a designated companion and an Altea upcoming trip
When I view My Travel Upcoming Trips List
And I click on Add a Companion from my first trip
Then I am redirected to the Altea Add a Companion page
