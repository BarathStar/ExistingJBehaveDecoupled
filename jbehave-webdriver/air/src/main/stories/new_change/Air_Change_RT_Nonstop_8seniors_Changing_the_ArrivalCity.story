Air Change Multi PAX Anonymous Senior changes Nonstop (5)

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

Scenario: Change Itinerary - Changing the arrival city in WN Round Trip flight itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |arrivalStation|DTW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|8|
    |departureDate|+14|
    |returnDate|+21|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |arrivalStation|CLE|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
