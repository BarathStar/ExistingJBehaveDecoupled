Customer checks-in to BusinessSelect flight and receives a drink coupon

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@dyna_stubs
@project 5.21ffClassicRetirement

Narrative:
In order to get a free drink
As an adult customer
I want to receive my drink coupon when I check in

Scenario: Initial Itinerary - Initial booking round-trip, one adult, Business Select

Given I am flying a BusinessSelect round-trip Southwest Southwest flight
And I book a flight eligible for checkin 1 adult
And I click on the Check in button on the Confirmation Page
And I check the drink coupon checkbox
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
When I view my boarding pass
Then I should receive my drink coupon
