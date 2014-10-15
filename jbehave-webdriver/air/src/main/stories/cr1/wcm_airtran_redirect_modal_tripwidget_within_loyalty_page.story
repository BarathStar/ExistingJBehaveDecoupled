Verify redirect to AirTran.com from book a trip modal within loyalty Book a Trip widget
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

Given I am logged in as Rapid Rewards member on the Rapid Rewards Account page
When I click the book a trip button
And I select Atlanta in the From field
And I select CUN in the To field
And I click on the modal search button
Then view the AirTran Redirect Modal with WCM Content
