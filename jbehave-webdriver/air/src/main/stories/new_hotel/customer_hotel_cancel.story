Cancel a Hotel Reservation

Meta:
@flow hotel
@process cancel
@user anonymous
@traveler adult

Narrative:
In order to cancel my hotel reservation
As an adult
I want to retrieve and cancel a hotel reservation

Scenario: Customer Hotel Reservation
Given I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I am a customer with a reserved hotel room
When I cancel a hotel reservation
Then I receive confirmation that my hotel reservation is canceled
