Select car vendor and vehicle type on air price page car cross sell widget

Meta:
@flow car
@process reservation
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to select a car
As a user
I want to select a car and a vendor on the car price page car cross sell

Scenario: Verify Car Vendor & Vehicle Type car banner parameters in air price page
Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|AUS|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|AUS|
    |dropOffStation|AUS|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|FullSize|
    |carVendor|Hertz|

When I get to the Price page
Then I should be able to select a car vendor and a vehicle type within the car cross sell widget
When I attempt to add a car through the car cross sell widget
Then I should see my selections populated on the select car page
