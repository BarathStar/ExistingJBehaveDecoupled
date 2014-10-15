SwaBiz Admin air View

Meta:
@flow air
@process view
@user sb_admin
@traveler adult
@not_passing draft

Narrative:
In order to view my booking reservation
As a SwaBiz Admin
I want to retrieve my booking using my confirmation number

Scenario: SWABiz Admin air view
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a SWABIZ Company Travel Manager
When I search for a flight
And I select and purchase a flight
And I retrieve my itinerary
Then I view my itinerary
