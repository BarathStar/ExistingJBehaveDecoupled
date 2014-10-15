My Travel change upcoming trip

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project codaPostSell
@dyna_stubs
@not_passing draft
@not_live
@project_in_dev
@storyid ZR-693

Narrative: In order modify my Southwest air only itinerary
As a Rapid Rewards member with an upcoming itinerary
I want to enter the change flow for that itinerary from the Snapshot page

Scenario: Redirect Change Reservation for upcoming flight from Snapshot

Given I am a Rapid Rewards Member with an upcoming trip
When I view My Travel
When I click on Change Reservation from my first trip
Then I am redirected to the Change Reservation page
