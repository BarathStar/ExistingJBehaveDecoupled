SwaBiz User View an air itinerary

Meta:
@flow air
@process view
@user sb_user
@traveler adult
@not_passing draft

Narrative:
In order to view an air itinerary
As a SwaBiz User
I want to view a booked flight

Scenario: SWABiz User air view
Given I am a SWABiz User with a booked flight
When I retrieve my itinerary
Then I view my itinerary