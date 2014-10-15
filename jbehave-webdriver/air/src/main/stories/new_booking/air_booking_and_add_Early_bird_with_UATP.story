Verify that I can pay using UATP Credit Card as my form of payment on the Early Bird Check-In Purchase when Southwest.com
users add Early Bird to existing and eligible air bookings.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user adding Early Bird to my eligible Air Booking on the Early Bird Standalone path
I want to be able to select UATP Credit Card as my form of payment on Early Bird Check-In Purchase Page
so that I can complete Early Bird purchase for my Air Booking

Scenario: User add Early Bird to a booked flight paying with UATP Credit Card on Early Bird Path

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|SAT|
    |arrivalStation|HOU|
    |departingFlight_carrierCode|FL|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|

And I have a flight booked
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
When I purchase earlybird for my flight with UATP Credit Card
Then I see earlybird purchase successful page