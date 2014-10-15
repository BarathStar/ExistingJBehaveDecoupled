Flight information in the fly-out SWABIZ

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@project multifleet
@dyna_stubs


Narrative:
In order to determine which nonStop flight segment to choose when changing an air booking on Swabiz
As an adult
I want to view the aircraft type, capacity and on time performance


Scenario: Scenario: User sees the Nonstop flight information when selecting new flights on Swabiz

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|


And I have the following itinerary data:


    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |arrivalStation|FLL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have a flight booked for a SWABiz Anonymous user
When I select to change my entire itinerary from the confirmation page
And I continue to the select flight page
And I click on the first NonStop flight number link on the select flight page
Then I see the flight information in the single fly-out