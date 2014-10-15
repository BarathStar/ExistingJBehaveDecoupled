Verify that users are able to see the trip details when clicking on the link that displays the entire inbound flight in the Account Bar.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user logged-in user with upcoming trips in my account
I want to click on the the link that displays the entire inbound flight in the Account Bar
So that I can see the details of my entire trip

Scenario: Scenario: Verify that the full O&D of my inbound is displayed as an Upcoming trip in the Account Bar for logged-in users

Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
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
And I login from Login page
When I click on the link that shows my inbound flight
Then I am redirected to the Trip Details Page