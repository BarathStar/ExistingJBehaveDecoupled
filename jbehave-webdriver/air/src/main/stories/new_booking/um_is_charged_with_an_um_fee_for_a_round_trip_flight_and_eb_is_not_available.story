UM on the departing flight and a YT on the returning flight are charged the UM Fee and treated as a UM both ways.

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to validate the fee charged to a passenger who is a Minor (pax age: 5-11) on the depart and a Young Traveler (pax age: 12-17) on the return and be informed about this charge
As an anonymous user
I want to be charged with an UM fee per child in addition to the airfare and be shown an informative message related to the UM fee

Scenario: UM on the departing flight and a YT on the returning flight are charged the UM Fee and treated as a UM both ways
Given I am flying a round-trip SouthwestNS flight with Nonstop segments
And I am an minor on the depart and a young traveler on the return
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
And I complete the UM Guardian information for the round-trip itinerary
And I select the Air product on the Shopping Cart
Then I see the Unaccompanied Minor Fee under the Shopping Cart
When I click on the Unaccompanied Minor Fee link
Then I see the Unaccompanied Minor Charge fly-out which informs the UM charges