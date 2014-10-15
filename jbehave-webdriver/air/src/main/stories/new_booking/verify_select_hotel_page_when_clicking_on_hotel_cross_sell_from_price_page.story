Verify that Southwest.com user sees Select Hotel Page when clicking on Search Hotel button on Hotel Dynamic Cross-Sell displayed on Price page

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a southwest. com user booking a flight when I see the dynamic hotel cross-sell on the Price Page
I want to click on Search Hotels button
So that I am redirected to the Select Hotel Page to select a hotel from the list of results.

Scenario: Validate that Southwest.com user sees Select Hotel Page when clicking on Search Hotels button on Hotel Dynamic Cross-Sell displayed on Price page.
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
When I click on 'Search Hotels' button on the Hotel Dynamic Cross-Sell
Then I should be able to see hotel results on the Select Hotel page
