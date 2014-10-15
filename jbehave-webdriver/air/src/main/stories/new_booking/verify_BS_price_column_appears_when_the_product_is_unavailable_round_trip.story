Verify that the business select fare column appears when the product is not available for that origin and destination
Meta:
@dyna_stubs
@flow air
@user anonymous
@process booking
@passenger adult
@not_live
@storyId SWAT-2640

Narrative:
As an adult
In order to make an Air booking, I want to verify that the BS fare column appears when the product is not available
So that

Scenario: Verify that the business select fare column appears when the product is not available for that origin and destination
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureDate|+1|
    |departureStation|MAF|
    |returnDate|+1|
    |arrivalStation|GEG|
    |departingFlight_carrierCode|WN|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |inboundRouting|Nonstop|

And the fare class Business Select is unavailable
When I attempt to search for flights from the flight search page
Then I verify the Business Select fare Column is displayed