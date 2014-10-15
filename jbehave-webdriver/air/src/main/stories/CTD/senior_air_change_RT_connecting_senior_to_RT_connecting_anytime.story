Change Itinerary -Senior Passenger- RT connecting Senior to RT connecting Anytime

Meta:
@project ctd
@flow air
@process changing
@traveler senior
@storyId SWACOMTT976

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change RT connecting Senior to RT connecting Anytime

Scenario: Change Itinerary -Senior Passenger- RT connecting Senior to RT connecting Anytime

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
    |departureDate|+2|
    |returnDate|+5|

When I book a flight as a senior
Then I receive an air confirmation number

Given air itinerary data as follows:

    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
