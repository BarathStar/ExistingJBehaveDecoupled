View my Hotel Cancel Reservation Modal for single Hotel product from the Upcoming Trips page

Meta:
@flow hotel
@process cancel
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@passenger adult
@project avengers_13.6
@project_in_dev

Narrative:
In order to view a Cancel Reservation for single Hotel product
As a Rapid Rewards Member (with Upcoming Trips)
I want to see the Cancel Reservation Modal for my Hotel product on Upcoming Trips page and cancel it without being informed about other products since the Hotel is the only item in the trip

Scenario: Rapid Rewards Member cancels a Hotel product (which is part of a single-product trip) from Upcoming Trips page

Given I am a Rapid Rewards Member passenger
And I have the following hotel itinerary data:

    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have included a hotel in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I decide to view all the upcoming trips from account's Snapshot
When I attempt to cancel the hotel product on Upcoming Trips page by clicking on Cancel Reservation link
Then I see the modal for single product hotel cancellation