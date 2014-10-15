Cancel a Car that is part of a multi-product trip I know that there are flights associated to an Adult and a Senior passenger

Meta:
@flow air
@process cancel
@user anonymous
@traveler senior_adult
@project tmAir
@dyna_stubs

Narrative:
In order to view in the Associated Products Modal a Senior and an Adult as separated Air products while cancelling a multi-product trip
As an Anonymous User
I want to retrieve my reservation and cancel the Car product

Scenario: Anonymous User views in the Associated Products Modal on Cancellation Flow a Senior and an Adult as separated Air products while cancelling a Car product that is part of a multi-product trip
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|1|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Alamo|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I retrieve the car itinerary
And I attempt to cancel the Car
Then I see that the Associated Products Modal shows separate Air products
When I accept the car cancellation
Then I see an informative message which states that my Car reservation has been cancelled on the Cancel Confirmation Page
And I see the Air Products as items associated to my named trip on the Cancel Confirmation Page