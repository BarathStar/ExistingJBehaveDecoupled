Air Change Single Senior Anonymous changes Nonstop (10)

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

Scenario: Change Itinerary - Air Change Round Trip Senior Passenger from Round Trip Nonstop Senior fare to Round Trip Nonstop Anytime
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Senior|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|1|
    |departureDate|+14|
    |returnDate|+21|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page