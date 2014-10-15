Verify oops message when trying to checkin for an airtran available segment when navataire maintenance mode is on.

Meta:
@project cr1
@airTranOnly
@flow air
@process checkin
@traveler adult
@storyId DCQA65 ZR870
@dyna_stubs
@project_in_dev
@cr1_navitaireMxMode

Narrative:
In order to verify an opps message is displayed for an AirTran operated flight,
the Navitaire maintenance mode has to be on.


Scenario: Attempt to check in for a FL-WN Itinerary while in Navitaire maintenance mode

Given Navitaire service maintenance is turned on
And I have the following itinerary data:

    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|BWI|
    |arrivalStation|ATL|
    |departingFlight_carrierCode|FL|
    |arrivingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |arrivingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |inboundRouting|Nonstop|
When I book a flight eligible for checkin 1 adult
And I retrieve my reservation to checkin
Then I should see the Oops message Checkin is currently unavailable for AirTran operated flights because our system to retrieve AirTran itineraries is down


