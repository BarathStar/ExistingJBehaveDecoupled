Verify that the Hotel Loyalty Number field is present for a partner when a retail rate room is selected

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
I want to book a retail rate room for a rr partner hotel

Scenario: Customer tries to Reserve retail rate for a RR Partner Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|HOU|
        |checkInDate|+2|
        |checkOutDate|+4|
        |hotelId|22182|
        |hotelName|Sheraton Suites Houston at the Galleria|
And I have available the hotel from my itinerary
And I am a customer looking for a hotel
When I have selected a retail rate for a partner hotel and continue to the purchase page
Then I see the Hotel Loyalty number field