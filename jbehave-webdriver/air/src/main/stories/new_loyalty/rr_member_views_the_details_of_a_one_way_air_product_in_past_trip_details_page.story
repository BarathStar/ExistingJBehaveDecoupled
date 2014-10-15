View the Past Flight in Past Trip Details page, so that I can view its detailed information.

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to see the detailed information of a One Way past flight
As a Rapid Rewards Member (with past flights)
I want to log in and view the past flight in "Past Trip Details" page

Scenario: Rapid Rewards Member views the details of a One Way Air product in Past Trip Details page
Given I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|MDW|
    |departureDate|+1|
    |departingFlight_fareClass|BusinessSelect|
    |outboundRouting|Nonstop|
    |arrivalStation|DTW|

And I am a Rapid Rewards Member with one Past Flight in my account
When I login from Login page
And I access my account's Past Flights
And I decide to view all the past flights from account's Snapshot
And I select a One Way past flight from Past Trips page
Then I see the Past Trip Details page
And I see the ticket for the One Way nonstop Air product