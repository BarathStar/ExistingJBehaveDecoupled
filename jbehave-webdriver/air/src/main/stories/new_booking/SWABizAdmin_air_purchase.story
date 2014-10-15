SwaBiz Admin air purchase

Meta:
@flow air
@process booking
@user sb_admin
@traveler adult
@not_passing draft

Narrative:
In order to fly on a date that my company wants
As a SwaBiz Admin
I want to book a flight and
receive a confirmation number

Scenario: SWABiz Admin Anonymous purchase
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a SWABIZ Company Travel Manager
And I am a customer looking for round-trip flights
When I select and purchase a flight
Then I should see the SWABiz confirmation page
