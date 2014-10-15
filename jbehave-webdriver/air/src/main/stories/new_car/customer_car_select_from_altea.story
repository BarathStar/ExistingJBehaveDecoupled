View a car reservation

Meta:
@flow car
@process retrieve
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project coda

Narrative:
In order to select a car
As an adult
I want to view the cars available for selection when coming from SWA international

Scenario: Customer Car Retrieve
Given I have the following car itinerary data:
        |Field|Value|
        |pickUpStation|DAL|
        |pickUpDate|+2|
        |dropOffDate|+4|
        |carType|ECONOMY|

When I look up a car coming from Altea
Then I should see cars available to select