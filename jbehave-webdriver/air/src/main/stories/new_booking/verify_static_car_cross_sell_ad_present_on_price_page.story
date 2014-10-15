Verify that Southwest.com user sees OpenX advice inside Car Cross-Sell on Price Page when toggle is OFF.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a southwest. com user booking a flight
I want to see the static car cross-sell on the Price Page
So that I can see the complete advice.

Scenario: Validate that the static car cross-sell is displayed on the Price Page.

Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAX|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

When I get to the Price page
Then I see the static car cross-sell ad
