Air Change Single Senior Anonymous changes Nonstop (10) for a senior

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to verify as an anonymous Senior Passenger user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Air Change OneWay Senior Passenger from OneWay Nonstop Senior fare to OneWay Nonstop Wanna get Away
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |departureDate|+14|
    |seniorPassengerCount|1|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|WannaGetAway|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page