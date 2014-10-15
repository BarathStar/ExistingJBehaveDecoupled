UM fee for multiple minors who travel in a one way flight

Meta:
@flow air
@process booking
@user anonymous
@traveler um
@dyna_stubs
@project UM/YT
@not_passing takestoolongtorun

Narrative:
In order to validate the fee charged to multiple unaccompanied minors (pax age: 5-11) who travel in an one way flight
As an anonymous user
I want to be charged $50 each way per child in addition to the air fare for UMs to travel.

Scenario: UM fee for multiple minors who travel in a one way flight
Given I am flying a one-way SouthwestNS flight with a Nonstop segment
And We are three minors traveling alone
When I attempt to purchase a flight as a minor
And I confirm there will not be a person traveling as a companion on the UM modal
Then I see the Unaccompanied Minor Fee on the UM Parent/Guardian Form
When I complete the UM Guardian information for the one-way itinerary
And I continue from the UM Guardian page to the Non-Editable Purchase page
And I select the Air product on the Shopping Cart
Then I see the Unaccompanied Minor Fee under the Shopping Cart