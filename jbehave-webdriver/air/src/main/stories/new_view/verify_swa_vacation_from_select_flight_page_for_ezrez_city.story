Verify that Airline Code, Flight Number, Fare Class and Fare Type are sent to SWA Vacations from Select Flight Page when Southwest.com users select flights for a ezrez city.

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@project Avengers_14.6
@storyId AV-2618

Narrative:
As a Southwest.com user, when I select flights for an ezrez city
I want to see the Airline Code, Flight Number, Fare Class and Fare Type on the SWA Vacations inpath cross-sell
So that I can take advantage of the packages offered.

Scenario: Validate if the Airline Code, Flight Number, Fare Class and Fare Type are sent to SWA Vacations for a ezrez city from the Select Flight Page.

Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |outboundConnectingStation|FLL|
    |arrivalStation|LAS|
    |departingFlight_carrierCode|WN|
    |outbound_connection_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |departingFlight_number|668|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |returningFlight_number|1234|
    |inboundRouting|Nonstop|

When I search and select my flight
Then I check that flight information of my selected flights is included on the SWA Vacations Form with fare type ANY