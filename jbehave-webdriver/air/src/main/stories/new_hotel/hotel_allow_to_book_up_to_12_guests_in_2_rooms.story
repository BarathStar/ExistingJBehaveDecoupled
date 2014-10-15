This flow verifies that any user is allowed to book up to 12 guests per 2 Rooms in the Hotel booking.

Meta:
@flow hotel
@user adult
@traveler anonymous
@process booking
@dyna_stubs

Narrative:
In order to validate that any user is able to book up to 12 guests per 2 Rooms in the Hotel booking
As a user
I want to verify that I get the Select Hotel page without errors

Scenario: Validate any user is able to book up to 12 guests per 2 Rooms in Hotel booking

Given I have the following hotel itinerary data:

        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|
        |adults|7|
        |children|5|
        |rooms|2|

And I am on the hotel search page
When I search for Hotels
Then I get the Select Hotel page without errors