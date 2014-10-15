View my Upcoming Trips so that I see how trips are sorted by default (Trip Date Earliest to Latest) and how products from a trip or reservation are first sorted by date and then by type (Air -> Hotel -> Car); I also want to sort my trips by Trip Name (Z-A)

Meta:
@flow rr
@process loyalty
@user rr_member
@not_live
@dyna_stubs

Narrative:
In order to validate the trip name and the sort order of the products in my Upcoming Trips
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Upcoming Trips and see the products listed by start date first and then by product type

Scenario: Rapid Rewards Member sorts trips by Trip Date (Earliest to Latest)/Trip Name (Z-A) criteria and views products sorted by date first and then by type
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |departureTime|09:00|
    |returnDate|+5|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|
    |arrivingFlight_carrierCode|WN|
    |arrivingFlight_fareClass|Anytime|
    |inboundRouting|Nonstop|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+4|
    |carType|MidSize|
    |carVendor|Budget|

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+1|
    |checkOutDate|+4|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have included a Hotel in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
And I have at least one Upcoming Trip in my account with an Air product which is not part of a trip
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
Then I see that the trip and the Air reservation are listed by date earliest to latest on Upcoming Trips page
And I see that the results can be sorted by different criteria offering by default Trip Date (Earliest to Latest)
And I see the products from the trip are listed by date first and then by type on the Upcoming Trips page
When I change the sorting option to descending by name
Then I see that the trip and the Air reservation are listed by name in descending order on Upcoming Trips page