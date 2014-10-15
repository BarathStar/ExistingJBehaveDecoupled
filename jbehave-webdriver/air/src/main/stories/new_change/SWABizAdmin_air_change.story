Change existing flight itinerary as Swabiz Admin

Meta:
@flow air
@process change
@user sb_admin
@traveler adult
@not_passing broken
@message Dave Batchelor - configuration problems in Jenkins for swabiz?
@change flow

Narrative:
In order to fly on a date that my company wants
As a SwaBiz Admin
I want to change the travel to two days later than the original date and receive a updated itinerary

Scenario: SWABiz Admin air change
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a SWABIZ Company Travel Manager
When I have a booked SwaBiz flight
And I change the flight to a later date
Then I should see the itinerary change confirmation page
