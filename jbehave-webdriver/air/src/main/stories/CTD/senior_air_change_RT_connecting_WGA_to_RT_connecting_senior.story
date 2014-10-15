Change Itinerary -Senior Passenger- RT connecting WannaGetAway to RT connecting Senior fare

Meta:
@project ctd
@flow air
@process changing
@traveler senior
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT connecting WannaGetAway to RT connecting Senior fare

Scenario: Change Itinerary -Senior Passenger- RT connecting WannaGetAway to RT connecting Senior fare

Given air itinerary data as follows:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop Change Planes|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|1 stop Change Planes|
    |departureDate|+10|
    |returnDate|+12|

When I book a flight as a senior
Then I receive an air confirmation number

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|Senior|
    |arrivingFlight_fareClass|Senior|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
