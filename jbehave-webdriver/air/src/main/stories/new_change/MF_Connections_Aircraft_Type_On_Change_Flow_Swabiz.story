Flight information in the fly-out SWABIZ

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@project multifleet
@dyna_stubs


Narrative:
In order to determine which flight segment to choose when changing an air booking with connections on Swabiz
As an adult
I want to view the aircraft type, capacity and on time performance


Scenario: User sees the flight information when selecting new flights with connections on Swabiz

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|One Way|
    |departureStation|SFO|
    |outboundConnectingStation|DEN|
    |arrivalStation|BWI|
    |departingFlight_carrierCode|WN|
    |outbound_connection_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Change Planes|

And I have a flight booked for a SWABiz Anonymous user
When I select to change my entire itinerary from the confirmation page
And I continue to the select flight page
And I click on the first Change Planes flight number link on the select flight page
Then I see the flight information in the connecting fly-out
