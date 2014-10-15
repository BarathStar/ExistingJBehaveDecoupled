View an error message when an Air, Car or Hotel product cannot be retrieved

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@project tmAir
@dyna_stubs
@not_live

Narrative:
In order to see an error message when an Air, Car or Hotel product cannot be retrieved
As an Anonymous User
I want to retrieve a multi-product trip which products are not available at that moment

Scenario: Anonymous User views an error message when an Air, Car or Hotel product cannot be retrieved
Given I have the Trip Management Database down
And I am:
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
And I have included a Car in my shopping cart
And I have included a Hotel in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve my Air itinerary
Then I see an error message for each of the products in the trip specifying that the product could not be retrieved