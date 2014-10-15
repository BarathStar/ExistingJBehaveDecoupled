User tries to pay Early Bird with UATP Credit Card under Payment Info Module

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing timeout

Narrative:
In order to validate the Payment info section in the accordion purchase page
As a southwest.com user who is booking an Air product
I want to purchase Early Bird with UATP Credit Card

Scenario: User tries to pay Early Bird with UATP Card

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I am on the purchase page
When I attempt to purchase Early Bird using UATP credit card
Then I see EarlyBird Check-In Purchased on air product on Existing purchases in trip