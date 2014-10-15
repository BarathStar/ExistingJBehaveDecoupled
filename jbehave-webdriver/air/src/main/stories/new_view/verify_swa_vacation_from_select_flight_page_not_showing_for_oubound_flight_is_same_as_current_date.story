Verify that SWA Vacations ad on Select Flight Page is not shown if the current date is the same as the outbound date when Southwest.com users select flights for a ezrez city.

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
As a Southwest.com user, when I select flights for an ezrez city and my outbound flight is the current date
I want to not see the SWA Vacations

Scenario: Validate the SWA Vacations ad for an ezrez city from the Select Flight Page is not visible.

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
    |departureDate|+0|

When I search for my flight
Then I verify that the SWA Vacations cross-sell on select flights page is not being displayed
