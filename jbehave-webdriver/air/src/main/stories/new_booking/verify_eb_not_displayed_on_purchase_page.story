Early Bird Section is not displayed on Purchase Page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a southwest.com user who is booking an Air product where the departure date is minor than 36 hours
I do not want to be offered the EarlyBird product
So that I know my flight is not eligible for EarlyBird Checkin.

Scenario: User is not able to add EarlyBird to his flight when the departure is less than 36 hours.

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departureDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

When I am on the purchase page
Then I am not able to add EarlyBird to my flight