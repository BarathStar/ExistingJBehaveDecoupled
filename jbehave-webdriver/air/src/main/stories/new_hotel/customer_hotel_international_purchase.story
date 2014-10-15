Reserve an International Hotel Room

Meta:
@flow hotel
@process reservation
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to have a place to stay
As a customer
I want to book an international hotel and
receive a confirmation number

Scenario: Customer Reserve, View, and Cancel Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|MEX|
        |checkInDate|+2|
        |checkOutDate|+4|

And I am a customer looking for a hotel
When I reserve a hotel room
Then I receive a hotel confirmation number