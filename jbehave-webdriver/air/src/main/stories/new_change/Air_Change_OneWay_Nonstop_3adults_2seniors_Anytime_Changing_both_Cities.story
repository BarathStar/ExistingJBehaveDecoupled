Air Change Mixed Multi PAX Anonymous changes Nonstop (8) for 3 adults and 2 seniors

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

Scenario: Change Itinerary - Change Itinerary - Changing the arrival and departure city in WN OneWay flight itinerary
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |adultPassengerCount|1|
    |departureDate|+7|
    |adultPassengerCount|3|
    |seniorPassengerCount|2|

And I have a flight booked
And I am changing the following itinerary data to:
    |Field|Value|
    |departureStation|LAX|
    |arrivalStation|SFO|

When I retrieve my reservation for change
And I change the flight
Then I should see the itinerary change confirmation page
