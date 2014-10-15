Cancel Flight As A SwaBiz User

Meta:
@flow air
@process cancel
@user sb_user
@traveler adult
@not_passing draft

Narrative:
In order to cancel my flight
As an adult
I want to receive a cancellation confirmation

Scenario: SWABiz User air cancel
Given I am a SWABiz User with a booked flight
When I cancel the flight
Then I view the flight cancellation confirmation
