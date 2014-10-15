View my Car Cancel Reservation Modal for single Car product from the Upcoming Trips page

Meta:
@flow car
@process cancel
@user rr_member
@traveler adult
@dyna_stubs
@not_live
@passenger adult
@project avengers_13.6
@project_in_dev

Narrative:
In order to view a Cancel Reservation for single Car product
As a Rapid Rewards Member (with Upcoming Trips)
I want to see the Cancel Reservation Modal for my Car product on Upcoming Trips page and cancel it without being informed about other products since the Car is the only item in the trip

Scenario:
Rapid Rewards Member cancels a Car product (which is part of a single-product trip) from Upcoming Trips page
Given I am a Rapid Rewards Member passenger
And I have the following car itinerary data:

    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+4|
    |carType|Compact|
    |carVendor|Budget|

And I have included a Car in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I decide to view all the upcoming trips from account's Snapshot
When I attempt to cancel the car product on Upcoming Trips page by clicking on Cancel Reservation link
Then I see the modal for single product car cancellation