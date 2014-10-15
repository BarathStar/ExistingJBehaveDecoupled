Check-in round-trip business select air ticket for an adult

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to receive my boarding pass
As an adult
I want to book a flight and check-in

Scenario: checkin for a Southwest Itinerary
Given I am flying a BusinessSelect round-trip Southwest Southwest flight
When I book a flight eligible for checkin 1 adult
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
