This flow verifies that any user is allowed to book up to 6 guests per 1 Room in Hotel booking.

Meta:
@flow hotel
@user adult
@traveler anonymous
@process booking
@dyna_stubs

Narrative:
In order to validate the any user is able to book up to 6 guests per 1 Room in Hotel booking
As a user
I want to verify that I get the Select Hotel page without errors.

Scenario: Validate any user is able to book up to 6 guests per 1 Room in Hotel booking

Given I have the following hotel itinerary data:

              |Field|Value|
              |destination|DAL|
              |checkInDate|+2|
              |checkOutDate|+4|
              |adults|3|
              |children|3|
              |rooms|1|

And I am on the hotel search page
When I search for Hotels
Then I get the Select Hotel page without errors