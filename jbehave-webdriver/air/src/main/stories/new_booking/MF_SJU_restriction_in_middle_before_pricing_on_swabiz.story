Show an Oops message on Swabiz when the user attempts to select a flight using SJU itinerary as a Connection

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
I want to select a flight using a SJU itineraries on Swabiz with itinerary restrictions.
So that I receive an Oops message

Scenario: Receive an Oops message when user tries to book flight using SJU itineraries on Swabiz

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

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

When I select a flight for pricing with San Juan Restriction on Swabiz
Then I see the oops message indicating the San Juan restriction
