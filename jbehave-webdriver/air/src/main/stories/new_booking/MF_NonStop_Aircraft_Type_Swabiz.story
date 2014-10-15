Flight information in the fly-out on Swabiz

Meta:
@flow air
@process booking
@user anonymous
@traveler adult
@project multifleet
@removedFromAirbooking
@dyna_stubs


Narrative:
In order to determine which nonStop flight segment to choose when performing an air booking on Swabiz
As an adult
I want to view the aircraft type, capacity and on time performance


Scenario: User sees the flight information for a nonStop flight on Swabiz

Given I am on the swabiz home page
And I have anonymously logged into a SWABiz account
And I am traveling as a:

    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BUR|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |equipmentCode|733|

When I only search a flight on Swabiz
And I click on the first NonStop flight number link on the select flight page
Then I see the flight information in the single fly-out