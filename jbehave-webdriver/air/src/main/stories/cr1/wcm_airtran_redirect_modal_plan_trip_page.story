Verify redirect to AirTran.com from book a trip modal
Meta:
@project cr1
@flow air
@process booking
@traveler adult

@project webreferral
@note this test should pass for each following state
@CR1_TOGGLE_WEBREFOFF_TOGGLE
@CR1_TOGGLE_WEBREF_TOGGLE
@CR1_TOGGLE_OFF_WEBREF_TOGGLE

Narrative:
In order to show the booking flow for AirTran international destinations
As a Customer
I want to select an AirTran market pair that are not serviced by southwest.com


Scenario: An adult searches for an international flight on marketing Page


Given I am flying a round-trip international flight
When I am a customer searching for round-trip flights from the search flights page
Then view the AirTran Redirect Modal with WCM Content
