Anonymous User decides not to cancel an Air product (which belongs to a multi-product trip) and is redirected to Retrieve Air Reservation page

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to return to the Retrieve Hotel Reservation page
As an Anonymous User
I want to retrieve a hotel reservation which belongs to a multi-porduct trip, attempt to cancel a flight associated to the trip and be redirected back to Retrieve Hotel Reservation page when I decide not to cancel the flight

Scenario: Anonymous User decides not to cancel a flight for a multi-product (Air+Hotel) trip and returns to the Retrieve Hotel Reservation page
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

And I have selected the following itinerary data:
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

And I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I have included an Air in my shopping cart
And I have included a Hotel in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve the hotel itinerary
And I select to cancel the Air product associated to my trip
Then I see the Air product associated to my trip on the Cancel Air Reservation Page
When I decide not to cancel the Air product associated to my trip on the Cancel Air Reservation Page
Then I am redirected back to the Hotel Reservation page