Change existing flight itinerary as Swabiz User

Meta:
@flow air
@process change
@user sb_user
@traveler adult
@not_passing draft

Narrative:
In order to fly on a date that my company wants
As a SwaBiz User
I want to change the travel to two days later than the original date and receive a updated itinerary

Scenario: SWABiz User air change
Given I am a SWABiz User with a booked flight
When I change the flight to a later date
Then I should see the itinerary change confirmation page
