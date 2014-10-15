Cancel a multi-product trip without being shown the Associated Products Modal for the non-active products

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@project tmAir
@dyna_stubs


Narrative:
In order to view the Associated Products Modal while cancelling a trip
As an Anonymous User
I want to be informed that my trip has active products associated to it after cancelling it

Scenario: Active products are shown when cancelling a reservation and non-active products are not shown when cancelling a reservation
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |departureDate|+1|
    |arrivalStation|DAL|
    |departingFlight_carrierCode|WN|
    |departingFlight_fareClass|Anytime|
    |outboundRouting|Nonstop|

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Alamo|

Given I have included an Air in my shopping cart
And I have included an Hotel in my shopping cart
And I have included an Car in my shopping cart
And I have finished my purchase adding my products to the new trip named with the default name
And I have cancelled the Hotel product which belongs to my trip
When I retrieve the Air reservation to cancel it
And I confirm the air cancellation
Then I see the air information on the cancellation modal
And I see the Car Product on the cancellation modal
And I should not see the Hotel and Air Products as associated items on the Air Associated Products Modal
When I click on the continue button on the cancellation modal
Then I see an informative message which states that my reservation has been cancelled on the Cancel Confirmation Page
And I see the car product as an item associated to my named trip on the Cancel Confirmation Page
And I should not see the hotel product as an associated product on the Cancel Confirmation Page
And I should not be able to rename the trip