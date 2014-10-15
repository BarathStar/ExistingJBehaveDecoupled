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
In order to validate the access to Cancel Air Reservation page from my Upcoming Trips
As a Rapid Rewards Member (with Upcoming Trips)
I want to check my Upcoming Trips and see the Cancel Reservation link for the Air product

Scenario: Rapid Rewards Member accesses Cancel Air Reservation page from Upcoming Trips page
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
    |returnDate|+2|
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
    |pickUpDate|+0|
    |dropOffDate|+1|
    |carType|MidSize|
    |carVendor|Budget|

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have included a Hotel in my shopping cart
And I have at least one Upcoming Trip in my account with an Air, a Car and a Hotel in a trip with the default name
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
Then I see the details of the products from the trip are listed by date first and then by type on the Upcoming Trips page
When I attempt to cancel the Air product on Upcoming Trips page
Then I see the Southwest Airlines - Cancel Air Reservation page
And I see the air product on the Cancel Air Reservation page