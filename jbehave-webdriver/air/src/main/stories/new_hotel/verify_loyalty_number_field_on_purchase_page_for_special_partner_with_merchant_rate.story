Verify that the Hotel Loyalty Number field is present for a special partner when a merchant rate room is selected

Meta:
@flow hotel
@process reservation
@user anonymous
@dyna_stubs
@storyid AV-1618
@project_in_dev
@project avengers_13.7
@traveler adult

Narrative:
In order to see the hotel loyalty number field
As an adult
I want to book a merchant rate room for a rr partner hotel

Scenario: Customer tries to Reserve merchant rate for a special RR Partner Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|LAS|
        |checkInDate|+2|
        |checkOutDate|+4|
        |hotelId|119934|
        |hotelName|MGM Grand Hotel & Casino|
And I have available the hotel from my itinerary
And I am a customer looking for a hotel
When I have selected a merchant rate for a special partner hotel and continue to the purchase page
Then I see the Hotel Loyalty number field