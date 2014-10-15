Air Change Single Anonymous Adult changes Nonstop (16) for an adult

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to verify as an anonymous Single Adult user on the Book Travel section of southwest.com
As a user
I want to change the Nonstop flights that match criteria for my trip so that I can successfully make a change to booked itinerary

Scenario: Change Itinerary - Changing the arrival city in WN OneWay flight itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|DAL|
    |arrivalStation|SAT|
    |departingFlight_carrierCode|WN|
    |arrivingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |arrivalStation|AUS|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
