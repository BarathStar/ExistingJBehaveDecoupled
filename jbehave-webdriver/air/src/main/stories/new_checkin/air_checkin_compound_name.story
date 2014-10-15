Check-in round-trip anytime select air ticket for an adult with compound first and last name

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to receive my boarding pass with my name correctly printed
As an adult with a compound name
I want to checkin for a flight

Scenario: Verify first and last compound passenger names containing spaces are printed correctly on Boarding Pass
Given I am flying a round-trip Southwest Southwest flight
When I have a compound name and book a flight eligible for checkin
And I click on the Check in button on the Confirmation Page
Then I should see the Check In page with my compound first and compound last name on
When I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view and verify my boarding pass
