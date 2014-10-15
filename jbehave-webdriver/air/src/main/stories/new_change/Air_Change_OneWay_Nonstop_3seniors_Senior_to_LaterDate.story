Air Change Multi PAX Anonymous Senior changes Nonstop (5) for 3 seniors

Meta:
@flow air
@process change
@user anonymous
@traveler senior
@dyna_stubs

Narrative:
In order to verify as an anonymous Multi PAX Senior user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Air Change OneWay Nonstop Senior to OneWay Nonstop Senior to Later Date
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|LAX|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |seniorPassengerCount|3|
    |departureDate|+14|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departureDate|+21|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
