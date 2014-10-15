Snapshot add companion to upcoming trip Altea redirect

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

Narrative:
In order to book a companion fare for my existing Altea booking
As a Rapid Rewards member with an upcoming Altea itinerary
I want to enter the Altea companion purchase flow for that itinerary from the Snapshot page

Scenario:Redirect Add a Companion for an upcoming Altea flight from Snapshot

Given I am a Rapid Rewards Member with a designated companion and an Altea upcoming trip
When I view Snapshot
Then I see Altea Add a Companion link for upcoming trip
