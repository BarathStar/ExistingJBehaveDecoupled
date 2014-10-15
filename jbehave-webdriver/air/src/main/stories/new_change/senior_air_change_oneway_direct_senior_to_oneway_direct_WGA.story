Change Itinerary Senior Passenger OneWay direct Senior fare to OneWay direct WannaGetAway

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative: In order to verify user can change a booked itinerary
As a senior
I want to change OneWay direct Senior fare to OneWay direct WannaGetAway

Scenario: Change Itinerary -Senior Passenger- OneWay direct Senior fare to OneWay direct WannaGetAway
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|1 stop No Plane Change|
    |seniorPassengerCount|1|

And I have a flight booked
And air itinerary data as follows:
    |Field|Value|
    |departingFlight_fareClass|WannaGetAway|
    |departureDate|+14|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page


