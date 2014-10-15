Verify Southwest/Airtran Business Select fare displayed on Select Flights Page

Meta:
@project cr1
@flow air
@process booking
@user
@traveler adult
@removedFromAirbooking
@dyna_stubs

Narrative:
In order to book a WN/FL flight using Business Select fare type
As a customer
I want to verify SouthWest/Airtran Business Select fares displayed on Select Flights page


Scenario: Display a BusinessSelect Fare on WN_FL one way flight

Given I am flying a BusinessSelect one-way WNFLCodeShare flight
When I am a customer searching for one-way flights from the search flights page
Then I see Business Select one-way Fare containing Southwest Airtran segments
