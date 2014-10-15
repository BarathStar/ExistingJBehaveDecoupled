Book an Air product and add it to a trip

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to verify if trip management features are present
As an Anonymous User
I want to create a trip with only one Air product

Scenario: Customer creates a trip with only one Air product
Given I am flying a round-trip Southwest Southwest flight
When I search and select a flight and am on the purchase page
And I complete the booking process adding the Air product to a new trip named MyTrip
Then I see that the Air product just booked has the name MyTrip on the Confirmation Page
And I see references to the product added to the trip on the Confirmation page