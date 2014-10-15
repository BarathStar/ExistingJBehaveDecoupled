Check-in round-trip anytime air ticket for 2 adults

Meta:
@flow air
@process checkin
@user anonymous
@global_nav_regression
@traveler adult
@dyna_stubs

Narrative:
In order to receive my boarding pass
As an adult traveling with one more adult
I want to check-in for a SouthWest flight

Scenario: Check-in two adults on Southwest Round Trip
Given I am flying a round-trip Southwest Southwest flight
When I book a flight eligible for checkin 2 adult
And I retrieve travel documents
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view 2 southwest boarding passes
