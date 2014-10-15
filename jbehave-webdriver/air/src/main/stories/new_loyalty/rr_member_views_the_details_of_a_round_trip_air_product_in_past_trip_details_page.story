View the Past Flight in Past Trip Details page, so that I can view its detailed information.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see the detailed information of a Round Trip past flight
As a Rapid Rewards Member (with past flights)
I want to log in and view the past flight in "Past Trip Details" page

Scenario: Rapid Rewards Member views the details of a Round Trip Air product in Past Trip Details page
Given I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|MDW|
    |departureDate|+1|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|DTW|
    |returnDate|+3|
    |arrivingFlight_fareClass|BusinessSelect|
    |inboundRouting|Nonstop|

And I am a Rapid Rewards Member with one Past Flight in my account
When I login from Login page
And I access my account's Past Flights
And I decide to view all the past flights from account's Snapshot
And I select a Round Trip past flight from Past Trips page
Then I see the Past Trip Details page
And I see the ticket for the Round Trip Air product