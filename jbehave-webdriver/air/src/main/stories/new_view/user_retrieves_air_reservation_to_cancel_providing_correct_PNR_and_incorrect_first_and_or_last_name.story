Retrieve my Air reservation despite the wrong information provided.

Meta:
@flow air
@process view
@user rr_member
@traveler adult
@dyna_stubs

Narrative:
In order to retrieve an Air reservation to cancel it
As an Anonymous User
I want to provide correct PNR and incorrect first name and/or last name

Scenario:
User retrieves an Air reservation to cancel it by providing correct PNR and
incorrect first name and/or last name information

Given I am traveling as a:
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

And I have the flight booked for a passenger named JOSH EVNIN
And I am on the Cancel Air Reservation Page
When I retrieve the Air reservation by providing the first name's vowels changed
Then I see the Southwest Airlines - Cancel Air Reservation page
When I am on the Cancel Air Reservation Page
And I retrieve the Air reservation by providing the last name's vowels changed
Then I see the Southwest Airlines - Cancel Air Reservation page
When I am on the Cancel Air Reservation Page
And I retrieve the Air reservation by providing the first letter of first name
Then I see the Southwest Airlines - Cancel Air Reservation page