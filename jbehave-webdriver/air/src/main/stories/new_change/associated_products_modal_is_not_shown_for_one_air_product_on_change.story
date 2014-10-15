Modal is not shown on cancellation or change for a trip with one product

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to not view Associated Products Modal on a trip with a single product
As a User
I want to change my reservation

Scenario: Customer does not view Associated Products Modal on Change Flow when a trip has only one Air product
Given I have an one-way flight booked on a new trip named MyTrip
When I want to change my itinerary
And I select to change my entire itinerary
Then I should not see the Air Associated Products Modal
When I complete the changing process
Then I see that the Air product just changed has the name MyTrip on the Change Confirmation Page
And I see references to other purchases added to the trip on the Change Confirmation page