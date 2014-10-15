Verify that hotel booking widgets placed on each hotel promotion are working properly as well as the sorting columns showing items sorted in different ways.(Template 2)

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
As a user
I want to verify that items are being sorted by column and I am on the Hotel Price page with my previously selected data populated on it
In order to validate hotel booking widgets on each promotion and sorting by column on the Hotel Promotion Template 2

Scenario: Sorting by columns and row widget validations on the Hotel Promotions Template 2
Given I have the following hotel itinerary data:
          |Field|Value|
          |destination|DAL|
          |checkInDate|+2|
          |checkOutDate|+4|
          |adults|1|
          |children|1|

And I am on Hotel Promotions Template 2
When I sort my promotions by rate
Then I should see hotel promotions sorted by rate
When I sort my promotions by star rate
Then I should see hotel promotions sorted by star rate
When I select a particular promotion for a hotel on template two
And I attempt to book that promotion on template two
Then I should be able to see hotel rates for my hotel on the Select Hotel Rate page