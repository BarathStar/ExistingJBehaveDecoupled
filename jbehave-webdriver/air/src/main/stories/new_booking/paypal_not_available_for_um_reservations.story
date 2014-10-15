Verify PayPal is not available for Unaccompanied Minor reservations

Meta:
@flow air
@process booking
@traveler um
@user anonymous
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to be informed about PayPal form of payment when the passenger is an Unaccompanied Minor (pax age: 5-11)
As an anonymous user
I want to view the PayPal option disabled when I attempt to book a flight for an Unaccompanied Minor

Scenario: User validates the PayPal form of payment for Unaccompanied Minors
Given I am flying a round-trip SouthwestNS flight with Nonstop segments
And I am a minor traveling alone
When I search and select flights and continue to the Purchase page
Then I am able to select PayPal as a payment method
When I fill out the purchase form
And I complete the purchase
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
Then I should not be able to select PayPal as a payment method
And I see an informative message indicating that itineraries with add-ons may not use PayPal