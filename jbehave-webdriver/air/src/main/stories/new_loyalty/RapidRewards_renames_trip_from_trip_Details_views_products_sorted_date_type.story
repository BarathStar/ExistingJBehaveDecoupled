Change the named originally provided; and see how products from a trip are first sorted by date and then by type (Air -> Hotel -> Car).

Meta:
@flow rrr
@process loyalty
@user rr_member
@dyna_stubs
@not_live

Narrative:
In order to rename my trip from Trip Details
As a Rapid Rewards Member (with Upcoming Trips)
I want to select my trip and be able to indicate a new trip name and see the products listed by start date first and then by product type

Scenario: Rapid Rewards Member renames a trip from Trip Details page and views products of the trip sorted by date first and then by type
Given I am a Rapid Rewards Member passenger
And I have the following itinerary data:
    |Field|Value|
    |itineraryType|Round Trip|
    |departureStation|HOU|
    |departureDate|+1|
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
    |dropOffDate|+4|
    |carType|Economy|
    |carVendor|Budget|

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have included the Air product in my shopping cart
And I have included the Car product in my shopping cart
And I have included the Hotel product in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I login from Login page
And I decide to view all the upcoming trips from account's Snapshot
And I select the first product of My Trip
Then I see that My Trip is shown at Trip Details page
And I see the details of the products from My Trip are listed by date first and then by type on the Trip Details Page
When I rename the trip by entering valid characters
Then I view the trip name shows the new name
And I see the breadcrumb updated with the new trip name