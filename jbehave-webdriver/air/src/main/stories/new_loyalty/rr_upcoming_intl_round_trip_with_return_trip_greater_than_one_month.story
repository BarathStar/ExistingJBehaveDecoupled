Verify that rapid reward member user is able to see his/her upcoming trip that has return date at least a month after departure.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs
@story DCAIR-7899

Narrative:
In order to validate that an international round trip with return date more than one month after departure is displayed correctly on the RR account snapshot page and upcoming trips detail pages
As a Rapid Rewards Member, I book an international round trip that has return date at least a month after departure.
I log into my RR account, then I click on the first upcoming trip to view the details.

Scenario: User is a Rapid Rewards member not logged in, books an itinerary for a flight in which the return flight is more than one month after the departure, then logs in, clicks on My Account, and sees the trip displayed correctly.

Given I am logged in as Rapid Rewards Member with the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|DAL|
    |departureDate|+1|
    |departureTime|07:05|
    |departingFlight_fareClass|BusinessSelect|
    |departingFlight_carrierCode|WN|
    |outboundRouting|Nonstop|
    |arrivalStation|MEX|
    |returnDate|+35|
    |returnTime|12:35|
    |arrivingFlight_fareClass|BusinessSelect|
    |arrivingFlight_carrierCode|WN|
    |inboundRouting|Nonstop|
    |isAltea|true|
When I click on my first upcoming trip
Then I see on the Trip Details page that the Air reservation has default trip name
