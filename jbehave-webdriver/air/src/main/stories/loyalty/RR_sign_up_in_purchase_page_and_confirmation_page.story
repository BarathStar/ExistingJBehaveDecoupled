Verify RR sign up module in purchase page and confirmation page

Meta:
@flow rr
@process loyalty
@user anonymous
@traveler adult
@passenger adult
@dyna_stubs
@project avengers_13.7
@project_in_dev

Narrative:
As an anonymous user in Southwest.com
I want to sign up on the Air Booking Purchase page
So that I can earn rewards point for this trip

Scenario: Verify RR sign up module in purchase page and confirmation page.
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And the RR Sign Up on Purchase Page and Confirmation Page toggle is ON
And the RR Sign Up on Confirmation Page toggle is OFF
When I am on the purchase page
And I fill in the Purchase form with RR Signup checked and submit
Then I view RR Sign Up Module in Confirmation Page
