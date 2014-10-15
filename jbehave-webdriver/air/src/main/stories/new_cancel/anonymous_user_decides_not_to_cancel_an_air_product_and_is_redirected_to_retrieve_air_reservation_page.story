Redirected to Retrieve Air Reservation page

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@project tmAir

Narrative:
In order to return to the Cancel Air Reservation
As an Anonymous User
I want to retrieve an air reservation which belongs to a multi-porduct trip in order to cancel it and be redirected back to Cancel Air Reservation page when I decide not to cancel the flight

Scenario: Anonymous User decides not to cancel a flight for a multi-product trip and returns to Retrieve Air and Car Reservation pages
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

And I have included a Car in my shopping cart
And I have included an Air in my shopping cart
And I have finished my purchase adding my products to a new trip named My Trip
When I retrieve the Air reservation to cancel it
And I decide not to cancel the Air product associated to my trip on the Cancel Air Reservation Page
Then I am redirected to the Cancel Air Reservation page