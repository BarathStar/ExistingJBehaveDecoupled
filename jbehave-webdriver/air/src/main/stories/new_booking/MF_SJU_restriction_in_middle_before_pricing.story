Show an Oops message when the user attemps to select a flight using SJU itinerary as a Connection

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project pnr_restriction
@removedFromAirbooking
@dyna_stubs
@not_live

Narrative:

As a customer
I want to select a flight using a SJU itineraries as a connection with itinerary restrictions.
So that I receive an Oops message

Scenario: Receive an Oops message when user tries to book flight using SJU itineraries as a connection

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|FLL|
    |departureDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|SJU|
    |returnStation|ATL|
    |returnDate|+1|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

When I select a flight for pricing with San Juan Restriction
Then I see the oops message indicating the San Juan restriction
