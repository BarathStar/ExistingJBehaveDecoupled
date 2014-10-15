Show an Oops message when the user attemps to change a flight using SJU itinerary as a Connection

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@project pnr_restriction
@dyna_stubs
@not_passing broken

Narrative:

As a customer
I want to change a flight using SJU as a connection So that I receive an Oops message
So that I receive an Oops message

Scenario: Receive an Oops message when user tries to change flight using SJU itineraries as a connection

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|DAL|
    |departureDate|+4|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|HOU|
    |returnStation|LAX|
    |returnDate|+5|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

And I have a flight booked
When I change the flight with the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|FLL|
    |departureDate|+4|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|SJU|
    |returnStation|ATL|
    |returnDate|+5|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

Then I see the oops message indicating the San Juan restriction
