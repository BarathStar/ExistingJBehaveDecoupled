Customer checks-in to Anytime flight and should not receive a drink coupon

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@project 5.21ffClassicRetirement

Narrative:
In order to verify that a drink coupon is not displayed
As an adult customer not flying BusinessSelect
I should not receive a drink coupon when I check in

Scenario: Initial Itinerary - Initial booking round-trip, one adult, Anytime

Given I am flying a Anytime round-trip Southwest Southwest flight
And I book a flight eligible for checkin 1 adult
And I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
When I view my boarding pass
Then I should not receive a drink coupon
