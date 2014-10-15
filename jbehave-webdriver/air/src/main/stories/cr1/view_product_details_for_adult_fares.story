View full fare details for Southwest only, Airtran only and code share itineraries

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@user anonymous
@dyna_stubs
@storyId DCQA-184, ZR-899
@not_passing draft


Narrative:
As a customer
I want to search for stations with different combinations
So that I view full fare details on the selects flight page.

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
