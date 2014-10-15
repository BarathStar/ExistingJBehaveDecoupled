This flow verifies that no user is allowed to book more than 12 guests in 2 Rooms in Hotel booking, getting an according error message.

Meta:
@flow hotel
@user adult
@traveler anonymous
@process booking
@dyna_stubs

Narrative:
In order to validate that no user is allowed to book more than 12 guests in 2 Rooms in Hotel booking
As a user
I want to verify that I get an according error message.

Scenario: Validate that no user is allowed to book more than 12 guests in 2 Rooms in Hotel booking

Given I have the following hotel itinerary data:

        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|
        |adults|7|
        |children|6|
        |rooms|2|

And I am on the hotel search page
When I attempt to search for Hotels
Then I get the Select Hotel page with an according error message not being allowed to continue with the booking flow