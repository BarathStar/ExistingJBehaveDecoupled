Verify Web Referral Updated Modal Wording

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@removedFromAirbooking
@dyna_stubs
@project webreferral

Narrative:
In order to see international travel policies
As a customer
I want to search for a international flight from the search flights page

Scenario: Add a car to a named trip that only has a flight
Given I am flying a round-trip international flight
When I am a customer searching for round-trip flights from the search flights page
And I view the Web Referral Redirect Modal for Round-trip
And I click to continue from the AirTran Redirect Modal
Then I should see the AirTran search results page