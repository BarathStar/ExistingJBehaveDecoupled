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

Scenario: Change Itinerary - Air Change Round Trip Nonstop BusinessSelect to Round Trip Nonstop Senior
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|AUS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|
    |seniorPassengerCount|1|
    |departureDate|+14|
    |returnDate|+21|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departingFlight_fareClass|Senior|
    |arrivingFlight_fareClass|Senior|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page