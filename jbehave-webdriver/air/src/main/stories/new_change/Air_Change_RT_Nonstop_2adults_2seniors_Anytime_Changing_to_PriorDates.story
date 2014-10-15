Air Change - Mixed Multi PAX Anonymous changes Nonstop (8) for 2 adults and 2 seniors

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

Scenario: Change Itinerary - Air Change Round Trip Nonstop Anytime to Round Trip Nonstop Anytime to Prior Dates
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |arrivalStation|DTW|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |adultPassengerCount|2|
    |seniorPassengerCount|2|
    |departureDate|+14|
    |returnDate|+21|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departureDate|+7|
    |returnDate|+14|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
