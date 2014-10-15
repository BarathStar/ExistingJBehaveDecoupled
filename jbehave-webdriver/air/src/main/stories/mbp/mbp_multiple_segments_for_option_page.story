Validate that the MBP Options page should show multiple segments for flights with multiple segments.

Meta:
@flow air
@process checkin
@user anonymous
@traveler adult
@project mbp
@dyna_stubs
@not_live

Narrative:
In order to see multi itinerary information blocks
As an anonymous adult
I want to check in online with a MBP eligible PNR which has more than one segment

Scenario: When the PNR is MBP eligible and it has multiple segments the user see multiple itinerary information blocks

Given air itinerary data as follows:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|AUS|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |isValidForCheckin|true|
    |departingFlight_fareClass|WannaGetAway|
    |outboundRouting|1 stop No Plane Change|
    |departingFlight_number|10|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |returningFlight_number|15|

And I have a flight booked for checkin and round trip
And I perform a checkin online
When I am in the MBP Options Page

Then I see the different segments with the corresponding flight numbers