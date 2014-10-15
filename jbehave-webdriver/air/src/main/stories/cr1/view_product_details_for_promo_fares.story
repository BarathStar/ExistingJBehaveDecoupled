View full fare details for Southwest only, Airtran only and code share itineraries

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@traveler user
@user anonymous
@dyna_stubs
@storyId DCQA-184, ZR-899
@not_passing draft


Narrative: In order to view full fare details
as an Adult
I search for flights and view fare details

Scenario: View full fare details for Southwest only, Airtran only and code share itineraries

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I am on the Select Flight page for 2 adult passenger(s)
Then I view full fare details
Given I am flying a round-trip AirTran AirTran flight
When I am on the Select Flight page for 2 adult passenger(s)
Then I view full fare details
Given I am flying a round-trip SouthwestCodeshare AirTran flight
And I am on the Select Flight page for 2 adult passenger(s)
Then I view full fare details
