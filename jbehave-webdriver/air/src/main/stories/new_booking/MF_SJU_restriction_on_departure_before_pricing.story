Show an Oops message when the user attempts to select a flight using SJU itineraries with layover >= 4 hours

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
I want to select a flight using a SJU itineraries with itinerary restrictions.
So that I receive an Oops message

Scenario: Receive an Oops message when user tries to book flight using SJU itineraries

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|SJU|
    |departureDate|+1|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|FLL|
    |returnStation|ATL|
    |returnDate|+2|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

When I select a flight for pricing with San Juan Restriction on departure
Then I see the oops message indicating the San Juan restriction
