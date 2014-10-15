Retrieve Hotel Reservation

Meta:
@flow hotel
@process retrieve
@traveler adult
@traveler adult
@bashFixingStory
@not_passing draft

Narrative:
In order to see my hotel reservation details
As an adult
I want to retrieve the hotel reservation using my confirmation number

Scenario: Customer Hotel Retrieve
Given I have the following hotel itinerary data:

    |Field|Value|
    |destination|DAL|
    |hotelName|Marriot|
    |checkInDate|+2|
    |checkOutDate|+4|

And I am a customer with a reserved hotel room
When I retrieve the hotel itinerary
Then I see the hotel itinerary
