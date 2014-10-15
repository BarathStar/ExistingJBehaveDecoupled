Retrieve a trip with an Early Bird Eligible Reservation

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to check an Early Bird eligible reservation which belongs to a trip
As an Anonymous User
I want to retrieve the reservation, see all its details and be able to rename the trip

Scenario: Customer views an Early Bird eligible Trip with a flight for 2 passengers
Given I have added a round-trip Early Bird eligible flight for 2 passengers to my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve my itinerary
Then I view the trip name My Trip which can be changed
And I see the Air product details and the Car and Hotel Cross sell sections
And I should not see the Air Cross Sell section and the associated products
And I am able to add the Early Bird Check-in to my Air product
And I see the names of the passengers I have filled in