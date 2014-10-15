This flow verifies the hotel booking widgets placed on each hotel promotion. (Template 1)

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs


Narrative:
In order to validate hotel booking widgets on each promotion on the Hotel Promotion Template 1 with tabs
As a user
I want to verify that I am on another tab with my previously selected data populated on it

Scenario: Hotel promotion template row widget validation
Given I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |checkInDate|+2|
    |checkOutDate|+4|
    |adults|2|
    |children|0|

And I am on Hotel Promotions Template 1 with tabs
When I select a particular promotion for a hotel
And I complete all the required data in the hotel booking widget
And I select another hotel promotion from another tab
Then I should see all my previously selected tab data