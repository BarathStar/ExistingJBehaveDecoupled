Verify that users are able to see the trip details when clicking on the link that displays the entire outbound flight in the Account Bar.

Meta:
@flow rr
@process loyalty
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
As a Southwest.com user logged-in user with upcoming trips in my account
I want to click on the the link that displays the entire outbound flight in the Account Bar
So that I can see the details of my entire trip

Scenario: Verify that users are able to see the trip details when clicking on the link that displays the entire outbound flight in the Account Bar

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
        |connectionStation|SAT|
        |outboundConnectingStation|HOU|
        |inboundConnectingStation|STL|

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
And I login from Login page
When I click on the link that shows my outbound flight
Then I am redirected to the Trip Details Page