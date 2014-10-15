AirTran RoundTrip Points Search

Meta:
@project cr1
@airTranOnly
@flow booking
@process awards
@user rr_member
@storyId DCQA200
!-- DCQA123
@dyna_stubs

Narrative:
In order to select from among AirTran flights when redeeming my points
As a customer or logged in RR member
I want to see AirTran operated flights correctly priced in points on the select flights page


Scenario:  Search for AirTran flights in dollars, then switch/toggle to points display

Given I am flying a round-trip AirTran AirTran flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I am on the select flights page
And I search for round-trip flights using a modified search with points
Then I view all the fares in points
