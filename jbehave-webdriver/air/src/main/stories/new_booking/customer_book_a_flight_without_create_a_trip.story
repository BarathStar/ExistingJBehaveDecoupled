Book a flight without creating a trip

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project tmAir
@dyna_stubs
@not_passing broken

Narrative:
In order to verify if the trip management features are present
As an Anonymous User
I want to book a flight without creating a trip

Scenario: Anonymous user books only an air product and completes purchase without creating a trip
Given I am flying a round-trip Southwest Southwest flight
When I search and select a flight and am on the purchase page
Then I see the trip section where the option to name the trip is not offered by default on the Purchase page
And I should not be able to add the trip to an existing trip on the Purchase page
When I complete the booking process without adding the air product to a trip
Then I see that the air product just booked has no trip name provided by the user
And I should not see any references to other purchases as the Air product is not added to a trip