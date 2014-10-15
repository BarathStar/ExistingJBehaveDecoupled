Flight information in the fly-out

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project multifleet
@removedFromAirbooking
@dyna_stubs


Narrative:
In order to determine which flight segment to choose when performing an air booking with connections
As an adult
I want to view the aircraft type, capacity and on time performance


Scenario: User sees the aircraft type help pop-up window for a flight with connections

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|STL|
    |outboundConnectingStation|FLL|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |outbound_connection_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|

When I only search a flight
And I click on the first Change Planes flight number link on the select flight page
Then I see the flight information in the connecting fly-out
