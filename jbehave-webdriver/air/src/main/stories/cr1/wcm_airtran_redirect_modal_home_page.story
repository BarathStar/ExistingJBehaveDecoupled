Verify redirect to AirTran.com from home page

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



Scenario: An adult searches for an international flight on the home page

Given I am on the Homepage
When I select a one-way International trip from ATL to CUN on the home page
Then view the AirTran Redirect Modal with WCM Content
