SwaBiz Admin Checkin

Meta:
@flow air
@process checkin
@user sb_admin
@traveler adult
@not_passing draft

Narrative:
In order to receive my boarding pass
As a swabiz admin
I want to checkin for a flight

Scenario:  SWABiz Admin air checkin
Given I am flying a round-trip Southwest Southwest flight
And I am logged in as a SWABIZ Company Travel Manager
When I search for a flight
And I select and purchase a flight
And I retrieve travel documents
Then I view my boarding pass
