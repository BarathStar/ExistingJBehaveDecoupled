Verify Stacktrace is not shown when A Senior And WGA Fares Are Selected

Meta:
@flow air
@process booking
@user anonymous
@passenger senior
@dyna_stubs
@storyId SWAT-1665
@project SWAT
@project_in_dev

Narrative:
As a senior
In order to book a flight
I want to choose senior and WGA fares and see the booking price
So that

Scenario: verify that the price page is shown after senior and WGA fares are selected

Given I am an senior traveling alone
And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|LAS|
    |arrivalStation|MSY|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Senior|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|WannaGetAway|
    |inboundRouting|Nonstop|
    |departureDate|+7|
    |returnDate|+14|

When I search and select my flight and attempt to continue to the Price page
Then I should see that I am redirected to the pricing page