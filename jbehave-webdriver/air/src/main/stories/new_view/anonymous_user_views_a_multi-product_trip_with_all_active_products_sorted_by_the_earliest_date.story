Retrieves an air product which belongs to multi-product trip with all its items active, which will NOT display a Hotel Dynamic Cross-Sell

Meta:
@flow air
@process view
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage
@not_live


Narrative:
In order to view sorted by the earliest date all the active products from a multi-product trip
As an Anonymous User
I want to retrieve my trip which has all its associated items active

Scenario: Anonymous User views sorted by the earliest date all the active products from a multi-product trip that has all its items active. View Itinerary page which will NOT display a Hotel Dynamic Cross-Sell.
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
    |Field|Value|
    |itineraryType|One Way|
    |departureStation|HOU|
    |arrivalStation|DAL|
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
When I retrieve my Air itinerary
Then I see on the View Reservation page that the Air product has the flow trip name
And I see the Air product details
And I see the Associated Items to the trip sorted by the earliest date with their details hidden
And I should not see the Air, Car and Hotel cross sell sections
And I should not see the Hotel Dynamic Cross-Sell