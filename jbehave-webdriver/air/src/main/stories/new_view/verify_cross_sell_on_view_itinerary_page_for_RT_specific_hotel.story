Verify that Southwest.com user sees Hotel Select Rate Page when clicking on a hotel result on Hotel Dynamic Cross-Sell displayed on View Itinerary page for a round-trip PNR.

Meta:
@flow air
@process view
@traveler adult
@user anonymous
@dyna_stubs
@not_live


Narrative:
As a Southwest.com user retrieving my round-trip on View Itinerary page displaying a Hotel Dynamic Cross-Sell
I want to see a Hotel Select Rate Page when click on an specific hotel on Dynamic Cross-Sell
So that I can initiate a hotel booking

Scenario: Any user of southwest.com retrieving a round-trip PNR on View Itinerary page seeing Hotel Dynamic Cross-Sell
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MSY|
    |arrivalStation|DAL|
    |departureDate|+10|
    |returnDate|+12|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
And I have an Air product which is not part of a trip
And I retrieve my itinerary
When I select a specific hotel on Hotel Dynamic Cross-Sell
Then I should be able to see hotel rates for the selected hotel on the Select Hotel Rate page