Cancel a round-trip anytime flight as a SwaBiz Admin

Meta:
@flow air
@process cancel
@user sb_admin
@traveler adult
@not_passing draft

Narrative:
In order to cancel my flight
As a SwaBiz Admin
I want to receive a cancellation confirmation

Scenario: SWABiz Admin air cancel
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a SWABIZ Company Travel Manager
When I search for a flight
And I select and purchase a flight
And I cancel the flight
Then I view the flight cancellation confirmation
