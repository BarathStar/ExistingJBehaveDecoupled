Verify associated products modal is not shown when trip has only one Air product

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@not_passing broken
@project tmAir

Narrative:
In order to cancel a trip with only one Air product
As a User
I want to cancel the air reservation without being shown any associated item because there is only one product added to the trip.

Scenario: Customer cancels a trip with only one Air product
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |departureDate|+1|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have included a Air in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve the Air reservation to cancel it
And I confirm the air cancellation
Then I should not see the Air Associated Products Modal
And I view the flight cancellation confirmation
And I should not see any associated product on the Cancel Reservation Confirmation Page