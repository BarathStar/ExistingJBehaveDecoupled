View my Upcoming Trips so that I can sort them by Trip Date

Meta:
@flow air
@process booking
@user rr_member
@global_nav_regression
@traveler adult
@not_live
@dyna_stubs

Narrative:
In order to validate the sort order of the trips in my Upcoming Trips
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Upcoming Trips and be able to sort them by Trip Date (Latest to Earliest) and by Trip Name (A-Z)

Scenario: Rapid Rewards Member sorts trips by Trip Date (Latest to Earliest) and by Trip Name (A-Z) criteria
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
And I have at least one Upcoming Trip in my account with an Air product which is not part of a trip
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
And I change the sorting option to descending by Trip Date
Then I see that the trip and the Air reservation are listed by date latest to earliest on Upcoming Trips page
When I change the sorting option to ascending by Trip Name
Then I see that the trip and the Air reservation are listed by name in ascending order on Upcoming Trips page