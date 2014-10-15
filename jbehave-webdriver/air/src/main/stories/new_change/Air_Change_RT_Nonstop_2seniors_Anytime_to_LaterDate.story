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

Scenario: Change Itinerary - Air Change Round Trip Nonstop Anytime to Round Trip Nonstop Anytime to Later Date
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DTW|
    |arrivalStation|MDW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|2|
    |departureDate|+7|
    |returnDate|+14|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departureDate|+14|
    |returnDate|+21|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
