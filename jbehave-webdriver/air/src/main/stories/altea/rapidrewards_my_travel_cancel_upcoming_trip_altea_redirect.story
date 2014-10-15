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
@storyid OPS-1238

Narrative: In order modify my Altea air only itinerary
As a Rapid Rewards member with an upcoming Altea itinerary
I want to enter the Altea cancel flow for that itinerary from the My Travel page

Scenario: Redirect Cancel Reservation for upcoming Altea flight from My Travel

Given I am a Rapid Rewards Member with an Altea upcoming trip
When I am on My Travel
And I click on Cancel Reservation from my first trip
Then I am redirected to the Altea Cancel Reservation Page