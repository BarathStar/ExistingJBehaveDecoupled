View my Upcoming Trips accessing through "My Account Snapshot" and "My Travel Snapshot" pages.

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@not_live
@dyna_stubs

Narrative:
In order to see my Upcoming Trips
As a Rapid Rewards Member (with Upcoming Trips)
I want to see my future trips accessing through "My Account Snapshot" and "My Travel Snapshot" pages

Scenario: Rapid Rewards Member views Upcoming Trips accessing through "My Account Snapshot" and "My Travel Snapshot" pages

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
Then I see that My Trip is shown under Upcoming Trips section on My Account Snapshot page
When I access my account's Travels
Then I see that My Trip is shown under Upcoming Trips section on My Travel Snapshot page