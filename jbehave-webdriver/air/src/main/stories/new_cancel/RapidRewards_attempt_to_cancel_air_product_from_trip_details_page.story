View my Upcoming Trips so that

Meta:
@flow air
@process cancel
@user rr_member
@traveler adult
@project codaPostSell
@dyna_stubs
@not_passing draft
@not_live
@project_in_dev
@storyid OPS-1238

Narrative:
In order to validate the access to Cancel Air Reservation page from my Upcoming Trip Details
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Upcoming Trips and see the Cancel Reservation link for the Air product

Scenario: Rapid Rewards Member accesses Cancel Air Reservation page from Upcoming Trip Details page
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
And I attempt to cancel the Air product on Trip Details page
Then I am on the Cancel Air Reservation Page
And I see the air product on the Cancel Air Reservation page