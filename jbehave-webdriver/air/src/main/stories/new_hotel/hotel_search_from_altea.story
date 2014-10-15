Reserve a Hotel Room

Meta:
@flow hotel
@process reservation
@user anonymous
@dyna_stubs
@not_live
@project_in_dev
@project coda

Narrative:
In order to stay at a hotel of my choice
As an adult
I want to view the hotels available for booking

Scenario: Customer Reserve and View Hotel
Given I have the following hotel itinerary data:
        |Field|Value|
        |destination|DAL|
        |checkInDate|+2|
        |checkOutDate|+4|
        |adults|2|
        |children|1|

And I am International customer looking for a hotel
Then I should see all my previously selected data for the hotel
