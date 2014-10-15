Verify that the full O&D of my inbound is displayed as an Upcoming trip in the Account Bar for logged-in users

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user logged-in user with upcoming trips in my account
I want to see the entire inbound flight as an Upcoming Trip in the account bar
So that I can clearly identify the origin and final destination of my trip

Scenario: Verify that the full O&D of my inbound is displayed as an Upcoming trip in the Account Bar for logged-in users

Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Open Jaw|
    |departureStation|DAL|
    |departureDate|+1|
    |departureTimeInMinutes|+5|
    |departingFlight_fareClass|BusinessSelect|
    |departingFlight_carrierCode|WN|
    |outboundRouting|1 stop Change Planes|
    |arrivalStation|HOU|
    |returnStation|PHX|
    |returnDate|+1|
    |arrivingFlight_fareClass|BusinessSelect|
    |arrivingFlight_carrierCode|WN|
    |inboundRouting|1 stop Change Planes|
    |connectionStation|SAT|
    |outboundConnectingStation|STL|
    |inboundConnectingStation|STL|

And I have the Upcoming Trip in my account and its inbound flight is either upcoming or in progress
When I login from Login page
Then I see that the account bar displays the information of my entire inbound flight as an upcoming trip