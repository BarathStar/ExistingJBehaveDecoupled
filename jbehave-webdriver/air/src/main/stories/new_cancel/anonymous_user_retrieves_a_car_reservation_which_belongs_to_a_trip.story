Attempt to cancel a flight associated to the trip and be redirected back to the Retrieve Car Reservation page when I decide not to cancel the flight so that I can
return to the View Car Reservation when the air cancellation flow is not completed

Meta:
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@project_AccordionPage

Narrative:
In order to return to the Retrieve Car Reservation page
As an Anonymous User
I want to retrieve a car reservation which belongs to a multi-porduct trip, attempt to cancel a flight associated to the trip and be redirected back to Retrieve Car Reservation page when I decide not to cancel the flight

Scenario: Anonymous User decides not to cancel a flight for a multi-product (Air+Car) trip and returns to the Retrieve Car Reservation page
Given I am:
    |Field|Value|
    |adultPassengerCount|1|
    |seniorPassengerCount|0|

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
    |pickUpDate|+2|
    |dropOffDate|+4|
    |carType|MidSize|
    |carVendor|Alamo|

And I have included an Air in my shopping cart
And I have included a Car in my shopping cart
And I have finished my purchase adding my product to a new trip named My Trip
When I retrieve the car itinerary
And I select to cancel the Air product associated to my trip on the Car Reservation page
Then I see the Air product associated to my trip on the Cancel Air Reservation Page
When I decide not to cancel the Air product associated to my trip on the Cancel Air Reservation Page
Then I am redirected back to the Car Reservation page