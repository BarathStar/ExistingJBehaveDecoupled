Verify that Southwest.com user sees Hertz as vendor inside Car Cross-Sell on Price Page when toggle is ON.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing draft PRICE_PAGE_CAR_CROSS_SELL_ON toggle is OFF by default

Narrative:
As a southwest. com user booking a flight
I want to see the dynamic car cross-sell on the Price Page advertising Hertz as the car vendor
So that I can initiate a car booking with Hertz as vendor.

Scenario: Validate that Hertz is displayed as car vendor on the Dynamic Car Cross-Sell on the Price Page.

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAX|
    |arrivalStation|LAS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I get to the Price page
Then I see Hertz vendor on the car cross-sell ad
