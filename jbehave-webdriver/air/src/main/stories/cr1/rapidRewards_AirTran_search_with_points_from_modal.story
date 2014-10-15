AirTran RoundTrip Points Search

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@user rr_member
@traveler adult
@storyId DCQA-200, ZR-894
@dyna_stubs
@project_in_dev

Narrative:
As a customer logged in R.R account from my account page from book a trip modal
I want to search for round-trip Airtran Airtran flight and select points radio button
So that I view all the fares in points.


Scenario: Search for AirTran flights in points from my accounts page

Given I am flying a round-trip AirTran AirTran flight
And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I click my account link
And I search for flights from my account
And I search for round-trip flights using a modified search with points
Then I view all the fares in points
