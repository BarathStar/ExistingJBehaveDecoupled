AirTran RoundTrip Points Search

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@project cr1
@airTranOnly

Narrative:
In order to see if AirTran flights will be listed in points on SouthWest.com
As a rapid rewards member
I want to logon to my Rapid Rewards Account and perform a  search for AirTran round-trips
and verify that flights are listed with point values

Scenario: Oops Message on Snapshot Page with airtan.com link if the route is AirTran redirect routes and if Southwest has schedules for this route
Given I am traveling as a:
    |Field|Value|
    |adultPassengerCount|1|

And air itinerary data as follows:
   |Field|Value|
   |itineraryType|Round Trip|
   |departureStation|CAK|
   |arrivalStation|DEN|
   |departingFlight_carrierCode|FL|
   |departingFlight_fareClass|AnyTime|
   |arrivingFlight_carrierCode|FL|
   |departureDate|+5|
   |returnDate|+10|

And I am logged in as a valid Rapid Rewards member on the Search Flights page
When I click my account link
When I am searching flights from snapshot page
Then I see an Oops messages from snapshot page indicating that please visit airtran.com to make this booking
