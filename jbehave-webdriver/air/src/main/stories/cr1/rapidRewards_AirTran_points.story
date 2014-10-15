AirTran RoundTrip Search for Rapid Rewards Points Search

Meta:
@project cr1
@airTranOnly
@flow booking
@process awards
@user rr_member
@storyId DCQA200
!-- DCQA123
@maybe

Narrative:
In order to see if AirTran flights will be listed in points on SouthWest.com
As a customer
I want to logon to my Rapid Rewards Account and search for AirTran round-trip flight with points


Scenario: Verify that AirTran searching for round-trip flights lists

Given I am flying a round-trip AirTran AirTran flight
And I am logged in as a goodUser Rapid Rewards member on the Search Flights page
When I am a customer searching for round-trip flights with points
Then I view all the fares in points
When I search for round-trip flights using a modified search with points
Then I view all the fares in points

