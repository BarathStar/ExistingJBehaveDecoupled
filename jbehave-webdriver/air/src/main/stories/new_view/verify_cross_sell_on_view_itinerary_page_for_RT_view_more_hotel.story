Verify that Southwest.com user sees Select Hotel Page when clicking on View More Hotels on Hotel Dynamic Cross-Sell displayed on View Itinerary page for a round-trip PNR.

Meta:
@flow air
@process view
@traveler adult
@user anonymous
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user retrieving my round-trip on View Itinerary page displaying a Hotel Dynamic Cross-Sell
I want to see a Select Hotel Page when I click on View More Hotels on the Hotel Dynamic Cross-Sell
So that I can initiate a hotel booking

Scenario: Any user of Southwest.com retrieving a round-trip PNR on View Itinerary page, which is displaying Hotel Dynamic Cross-Sell, who selects to View More Hotels
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MSY|
    |arrivalStation|DAL|
    |arrivalCity|Dallas|
    |departureDate|+10|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
And I have an Air product which is not part of a trip
And I retrieve my itinerary
When I select to view more hotels
Then I should be able to see hotel results on the Select Hotel page