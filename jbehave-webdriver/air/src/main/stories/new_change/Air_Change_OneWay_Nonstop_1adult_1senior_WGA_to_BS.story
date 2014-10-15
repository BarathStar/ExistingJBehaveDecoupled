Air Change Mixed Multi PAX Anonymous changes Nonstop (8) for 1 adult and 1 senior

Meta:
@flow air
@process change
@user anonymous
@traveler senior_adult
@dyna_stubs

Narrative:
In order to verify as an anonymous Mixed PAX user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Air Change OneWay Nonstop WGA to OneWay Nonstop BusinessSelect
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|LAS|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |seniorPassengerCount|1|
    |departureDate|+14|

And I have a flight booked
And I am changing the following itinerary data to:
   |Field|Value|
   |departingFlight_fareClass|BusinessSelect|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
