Show an Oops message on Swabiz when the user attempts to change a flight using SJU itineraries with layover >= 4 hours affected by a SODA event

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@project pnr_restriction
@dyna_stubs
@not_live

Narrative:

As a customer
I want to change a flight using a SJU itineraries on Swabiz with itinerary restrictions affected by a SODA event
So that I receive an Oops message

Scenario: Receive an Oops message when user tries to change flight using SJU itineraries on Swabiz affected by a SODA event

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|DAL|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|HOU|
    |returnStation|LAS|
    |returnDate|+3|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

And I have a flight booked affected by a SODA event for a SWABiz Anonymous user
When I change the flight with the following itinerary data:

    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|SJU|
    |departureDate|+2|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|FLL|
    |returnStation|ATL|
    |returnDate|+3|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

Then I see the oops message indicating the San Juan restriction
