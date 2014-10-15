Change Itinerary -Senior Passenger- RT connecting Senior fare to RT connecting BusinessSelect

Meta:
@project ctd
@flow air
@process changing
@traveler senior
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT connecting Senior fare to RT connecting BusinessSelect

Scenario: Change Itinerary -Senior Passenger- RT connecting Senior fare to RT connecting BusinessSelect

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|1 stop Change Planes|

When I book a flight as a senior
Then I receive an air confirmation number

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|BusinessSelect|
    |arrivingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
