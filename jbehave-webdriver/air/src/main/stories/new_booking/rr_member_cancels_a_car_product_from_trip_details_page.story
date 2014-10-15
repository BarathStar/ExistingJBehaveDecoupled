View my Car product (which is part of a multi-product trip) on Trip Details page

Meta:
@flow air
@process booking
@user rr_member
@traveler adult
@removedFromAirbooking
@not_live
@dyna_stubs

Narrative:
In order to cancel a Car product part of a multi-product trip
As a Rapid Rewards Member (with Upcoming Trips)
I want to see my Car product on Trip Details page an cancel it but previously being informed about the other products associated to the trip

Scenario: Rapid Rewards Member cancels a Car product (which is part of a multi-product trip) from Trip Details page
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+2|
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
    |dropOffDate|+2|
    |carType|Compact|
    |carVendor|Budget|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I log in from the account sidebar at the Search Flights page
And I click my account link
And I decide to view all the upcoming trips from account's Snapshot
And I have selected the Car product from My Trip on Upcoming Trips page
And I attempt to cancel the Car product on Trip Details page
Then I see the Associated Products modal
And I see the information of my retrieved car on the Car Associated Products Modal
And I see the Air product as an associated item to my trip on the Car Associated Products Modal
When I confirm that I cancel the car product
Then I see an informative message which states that my Car reservation has been cancelled on the Cancel Confirmation Page
And I see the information of my Car on the Cancel Confirmation Page
And I see the Air Product as associated item of my named trip on the Cancel Confirmation Page