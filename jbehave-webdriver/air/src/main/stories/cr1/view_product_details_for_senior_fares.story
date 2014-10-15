View full fare Senior details for Southwest only, AirTran only and code share itineraries

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler senior
@user anonymous
@dyna_stubs
@storyId DCQA-184, ZR-899
@not_passing draft


Narrative:
As a customer
I want to book a flight for senior with different combinations
So that I view full fare details on the select flights page.

Scenario: View full fare details for Southwest only, AirTran only and code share itineraries

Given I am flying a round-trip SouthwestCodeshare SouthwestCodeshare flight
When I am on the Select Flight page for 2 senior passenger(s)
Then I view full fare details
Given I am flying a round-trip AirTran AirTran flight
When I am on the Select Flight page for 2 senior passenger(s)
Then I view full fare details
Given I am flying a round-trip SouthwestCodeshare AirTran flight
And I am on the Select Flight page for 2 senior passenger(s)
Then I view full fare details
