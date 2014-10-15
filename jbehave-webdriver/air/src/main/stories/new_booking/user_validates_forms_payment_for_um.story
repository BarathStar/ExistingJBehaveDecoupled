User validates the Forms of Payment for Unaccompanied Minors

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT

Narrative:
In order to be informed about Travel Funds restrictions related to the Unaccompanied Minor charges
As an Anonymous User
I want to view an informative message indicating that Travel Funds can only be applied to the airfare and not to the UM fee when I attempt to pay the flight with this form of payment

Scenario: User validates the Forms of Payment for Unaccompanied Minors
Given I am flying a round-trip SouthwestNS flight with Nonstop segments
And I am a minor traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
And I apply a LUV Voucher as form of payment
Then I see an informative message indicating that travel funds can only be applied to the airfare
And I see the Unaccompanied Minor Fee on the Applied Travel Funds Summary