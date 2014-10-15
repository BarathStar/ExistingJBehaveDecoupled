View my Car product which belongs to a single-product trip from the Trip Details page

Meta:
@flow car
@process cancel
@user rr_memeber
@traveler adult
@dyna_stubs
@not_live

Narrative:
In order to cancel a Car product part of a single-product trip
As a Rapid Rewards Member (with Upcoming Trips)
I want to see my Car product on Trip Details page an cancel it without being informed about other products since the Car is the only item in the trip

Scenario: Rapid Rewards Member cancels a Car product (which is part of a single-product trip) from Trip Details page
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
And I have selected the Car product from My Trip on Upcoming Trips page
And I attempt to cancel the Car product on Trip Details page
Then I see a window that requests me to confirm the cancellation
When I confirm that I cancel the car product
Then I see an informative message which states that my Car reservation has been cancelled on the Cancel Confirmation Page
And I see the information of my Car on the Cancel Confirmation Page