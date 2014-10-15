Unaccompanied Minors cannot add early bird to a flight

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to validate EB feature for Unaccompanied Minors (pax age: 5-11)
As an anonymous user
I want to add the EarlyBird check-in and be informed that Unaccompanied Minors are not eligible for EarlyBird

Scenario: UM cannot add early bird to a flight
Given I am flying a round-trip SouthwestNS flight EB eligible with Nonstop segments
And I am a minor traveling alone
When I search and select flights and continue to the Purchase page
And I fill out the purchase form with Early Bird
And I complete the purchase
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
Then I see a message indicating that UMs are not eligible for EB checkin
And I should not see the add-ons section with EarlyBird Check-In at Shopping Cart