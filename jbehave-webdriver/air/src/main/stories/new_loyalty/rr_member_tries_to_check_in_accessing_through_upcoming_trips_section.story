Check in for a future trip, so that I reach "Checkin Online and Print Boarding Passes" page.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to reach "Checkin Online and Print Boarding Passes" page
As a Rapid Rewards Member (with Upcoming Trips)
I want to log in and try to check in accessing through Upcoming Trips section

Scenario: Rapid Rewards Member tries to check in accessing through Upcoming Trips section
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|
    |isValidForCheckin|true|

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I login from Login page
And I access my account's Travels
Then I see the My Travel Snapshot page
And I see the option to check the flight status
When I attempt to check-in the flight
Then I see the Checkin Online page