Verify that hotel booking widget placed on each hotel promotion. (Template 1)

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to verify that I am on the Hotel Select page with my previously selected data populated on it
In order to validate hotel bottom booking widget and sorting of hotel promotions on the Hotel Promotion Template 1 without tabs

Scenario: Promotions sorting and bottom hotel booking widget validations on the Hotel Promotions Template 1 without tabs

Given I have the following hotel itinerary data:

    |Field|Value|
    |destination|DAL|
    |checkInDate|+2|
    |checkOutDate|+4|
    |promoCode|12345|
    |adults|2|
    |children|none|

And I am on Hotel Promotions Template 1 without tabs
When I select a different sorting option than the default for the promotions
Then I should see hotel promotions sorted according to the sorting option chosen
When I attempt to book using the bottom hotel booking widget
Then I should be on the Hotel Select page
And I should see all my previously selected data