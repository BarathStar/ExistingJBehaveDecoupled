Verify that Southwest.com users see car results reflecting different car vendors among results on the Car Cross-Sell on
Confirmation page when toggle PRICE_PAGE_CAR_CROSS_SELL is ON.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user booking a flight
I want to see car results reflecting different car vendors among my results
So that car vendors will not be displayed multiple times inside Car Cross-Sell section.

Scenario: Validate displaying car results reflecting different car vendors among results inside Car Cross-Sell on Confirmation Page.

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
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked
And I get to the Price page
When I continue to the purchase page and complete the booking
Then I should see car results reflecting different car vendors among results inside Car Cross-Sell
