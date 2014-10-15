View my Car Cancel Reservation Modal for trip with multiple associated products from the Upcoming Trips page

Meta:
@flow Car
@process cancel
@user rr_member
@traveler adult
@dyna_stubs
@not_passing draft
@project_in_dev
@project avengers_13.6
@passenger adult

Narrative:
In order to view a Car Cancel Reservation Modal for trip with multiple product
As a Rapid Rewards Member (with Upcoming Trips)
I want to see the Cancel Reservation link for my Car product on Upcoming Trips page and cancel it being informed about other products in the trip

Scenario:
Rapid Rewards Member views my upcoming trips Car Cancel Reservation link from upcoming trips page

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
When I attempt to cancel the car product on Upcoming Trips page by clicking on Cancel Reservation link
Then I see the modal for multiple product car cancellation