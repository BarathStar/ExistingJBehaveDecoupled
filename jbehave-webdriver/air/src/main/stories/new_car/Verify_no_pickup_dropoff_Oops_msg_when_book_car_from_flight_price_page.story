Verify there is no pickup or drop off location Oops message on the Car Price Page from flight price page as an adult

Meta:
@flow car
@process booking
@passenger adult
@user anonymous
@dyna_stubs
@not_live

Narrative:
As an adult
In order to search for a car
I want to verify that there is no Oops message about pickup and drop off location in search car page from flight price page
So that

Scenario: verify no Oops message about pickup and drop off location is shown on the Price page

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |arrivalStation|SMF|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

Given I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Hertz|

When I get to the Price page
And I attempt to add a car through the ad in the car cross sell widget
And I go to url car cross sell advertise
Then I shouldn't see any Oops message in Select Car Page