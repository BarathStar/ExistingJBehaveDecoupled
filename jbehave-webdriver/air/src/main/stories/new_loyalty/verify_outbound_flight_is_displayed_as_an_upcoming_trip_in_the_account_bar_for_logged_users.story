Verify that the full O&D of my outbound is displayed as an Upcoming trip in the Account Bar for logged-in users

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user logged-in user with upcoming trips in my account
I want to see the entire outbound flight as an Upcoming Trip in the account bar
So that I can clearly identify the origin and final destination of my trip

Scenario: Verify that the full O&D of my outbound is displayed as an Upcoming trip in the Account Bar for logged-in users

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
    |arrivalStation|MDW|
    |returnDate|+1|
    |arrivingFlight_fareClass|BusinessSelect|
    |arrivingFlight_carrierCode|WN|
    |inboundRouting|1 stop Change Planes|
    |connectionStation|STL|
    |outboundConnectingStation|HOU|
    |inboundConnectingStation|STL|

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I login from Login page
Then I see that the account bar displays the information of my entire outbound flight as an upcoming trip