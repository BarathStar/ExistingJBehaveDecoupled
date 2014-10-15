Verify that Southwest.com user sees Hotel Select Rate Page when clicking on a hotel result on Hotel Dynamic Cross-Sell displayed on View Itinerary page for a Single Round-Trip PNR and Car reservation saved in a trip.

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user retrieving my Single Round-Trip linked to Car I have included an Air in my shopping cart reservation, saved in a trip on View Itinerary page which is displaying a Hotel Dynamic Cross-Sell
I want to see a Hotel Select Rate Page when click on a specific hotel on Dynamic Cross sell
So that I can initiate a hotel booking.

Scenario: Any user of southwest.com retrieving a Single Round-Trip PNR saved in a trip on View Itinerary page which is displaying a Hotel Dynamic Cross-Sell.
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MSY|
    |arrivalStation|DAL|
    |departureDate|+1|
    |returnDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |adultPassengerCount|1|

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to the new trip named with the default name
And I retrieve my Air itinerary
When I select a specific hotel on Hotel Dynamic Cross-Sell
Then I should be able to see hotel rates for my hotel on the Select Hotel Rate page