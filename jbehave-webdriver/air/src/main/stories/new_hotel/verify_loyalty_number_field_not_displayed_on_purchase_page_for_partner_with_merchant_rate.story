Verify that the Hotel Loyalty Number field is not displayed for a hotel partner when a merchant rate room is selected

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
In order to see that the hotel loyalty number field is not displayed
As an adult
I want to book a merchant rate room for a rr partner hotel

Scenario: Customer tries to Reserve merchant rate for a RR Partner Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|
        |hotelId|6268|
        |hotelName|Wingate by Wyndham Richardson|
And I have available the hotel from my itinerary
And I am a customer looking for a hotel
When I have selected a merchant rate for a partner hotel and continue to the purchase page
Then I do not see the Hotel Loyalty number field