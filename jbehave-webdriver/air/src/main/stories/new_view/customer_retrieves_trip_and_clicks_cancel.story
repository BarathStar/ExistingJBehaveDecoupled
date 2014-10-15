Retrieve a trip and click Cancel Reservation breadcrumb

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to cancel a reservation
As a User
I want to see an empty Cancel Air Reservation form

Scenario: The user retrieves a booked flight and then clicks the Cancel Reservation breadcrumb

Given I am flying a one-way Southwest flight
And I have a flight booked
When I retrieve my itinerary
And I click on the cancel reservation breadcrumb
Then I verify that the Cancel Reservation form is empty
