Flight information in the fly-out

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@project multifleet
@dyna_stubs


Narrative:
In order to determine which nonStop flight segment to choose when changing an air booking
As an adult
I want to view the aircraft type, capacity and on time performance


Scenario: Scenario: User sees the Nonstop flight information when selecting new flights

Given I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|


And I have the following itinerary data:


    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|SEA|
    |arrivalStation|STL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |equipmentCode|733|

And I have a flight booked
When I select to change my entire itinerary from the confirmation page
And I continue to the select flight page
And I click on the first NonStop flight number link on the select flight page
Then I see the flight information in the single fly-out