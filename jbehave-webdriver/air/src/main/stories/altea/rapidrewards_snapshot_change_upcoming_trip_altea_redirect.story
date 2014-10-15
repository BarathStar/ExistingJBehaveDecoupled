Snapshot change upcoming trip Altea redirect

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project coda
@dyna_stubs
@not_live
@project_in_dev
@storyid ZR-692

Narrative: In order modify my Altea air only itinerary
As a Rapid Rewards member with an upcoming Altea itinerary
I want to enter the Altea change flow for that itinerary from the Snapshot page

Scenario: Redirect Change Reservation for upcoming Altea flight from Snapshot

Given I am a Rapid Rewards Member with an Altea upcoming trip
When I click the snapshot link
Then I see Altea specific Change Reservation link for upcoming trip
