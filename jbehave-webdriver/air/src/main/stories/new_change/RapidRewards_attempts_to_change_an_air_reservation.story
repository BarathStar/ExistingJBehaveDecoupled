Change the Air reservation accessing through "Change" link on "Trip Details" page.

Meta:
@flow air
@process change
@user rr_member
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to try to change an Air reservation
As a Rapid Rewards Member (with Upcoming Trips)
I want to see my Air product on "Trip Details" page and attempt to change it accessing through the "Change" link

Scenario: Rapid Rewards Member attempts to change an Air reservation accessing through "Change" link on "Trip Details" page
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

And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I select an Air reservation which is part of My Trip
And I attempt to change the Air product on Trip Details page
Then I see Southwest Airlines - Change Itinerary page
And I see the passenger's name on Change Itinerary page
And I see the confirmation number of the Air product on Change Itinerary page
And I see the ticket for the Round Trip (nonstop) Air product on Change Itinerary page