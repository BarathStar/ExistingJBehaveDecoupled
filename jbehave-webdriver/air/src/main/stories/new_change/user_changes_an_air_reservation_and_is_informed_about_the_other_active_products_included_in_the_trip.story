Change an air reservation which belongs to a multi-product trip

Meta:
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to complete the change process for a multi-product trip
As an anonymous user
I want to retrieve the air reservation which belongs to the trip, change it and be informed about other products added to the trip which are active

Scenario: The user changes an air reservation which belongs to a multi-product trip and is informed about the other active products included in the trip
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

And I have the following car itinerary data:
    |Field|Value|
    |pickUpStation|DAL|
    |dropOffStation|DAL|
    |pickUpDate|+1|
    |dropOffDate|+2|
    |carType|MidSize|
    |carVendor|Alamo|

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have included an Air in my shopping cart
And I have included a Hotel in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase adding my products to a new trip named MyTrip
And I have cancelled the car product which belongs to my trip
When I retrieve the Air reservation to change it on the Change Air Reservation Page
And I select to change my entire itinerary and continue
Then I see the information of my retrieved flight on the Air Associated Products Modal
And I see the hotel product as an item associated to my trip on the Air Associated Products Modal
And I should not see the car product as an associated item on the Air Associated Products Modal
When I click continue on the Change Air Reservation Modal
And I complete the changing process
Then I see on the Change Confirmation Page that the Air product just changed has MyTrip as name
And I see references to purchases added to the trip on the Confirmation page
And I see the hotel product which belongs to the existing trip with its details hidden on the Change Confirmation page