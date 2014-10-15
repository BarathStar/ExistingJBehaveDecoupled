Verify that Southwest.com user sees Hotel Select Rate Page when clicking on a hotel result on Hotel Dynamic Cross-Sell displayed on Price page.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@not_passing draft ENHANCED_LAYOUT_HOTEL_CROSS_SELL_PRICE_PAGE toggle is OFF by default

Narrative:
As a southwest. com user booking a flight
I want to see the dynamic hotel cross-sell on the Price Page
So that I can initiate a hotel booking.

Scenario: Validate that Southwest.com user sees Hotel Select Rate Page when clicking on a hotel result on Hotel Dynamic Cross-Sell displayed on Price page.
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAX|
    |arrivalStation|LAS|
    |departureDate|+1|
    |returnDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |adultPassengerCount|1|

And I get to the Price page
When I select a specific hotel on the Hotel Dynamic Cross-Sell
Then I should be able to see hotel rates for the selected hotel on the Select Hotel Rate page
