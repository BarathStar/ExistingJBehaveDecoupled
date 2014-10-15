Verify that default sorting option is taking place and that hotel booking data is being properly populated from the hotel booking widgets placed on each hotel row. (Template 1)

Meta:
@flow hotel
@process booking
@user anonymous
@traveler adult
@dyna_stubs

Narrative:
In order to validate default sorting and booking on the Hotel Promotion Template 1 with tabs
As a user
I want to verify that default sorting is displayed and I am able to book a particular hotel promotion

Scenario: User sees the default sorting and attempts to book a hotel on the Hotel Promotions Template 1 with tabs
Given I have the following hotel itinerary data:
          |Field|Value|
          |destination|DAL|
          |checkInDate|+2|
          |checkOutDate|+4|
          |adults|1|
          |children|0|

And I am on Hotel Promotions Template 1 with tabs where price is default sorting option
When I inspect that price sorting option is being used by default
Then I should see hotel promotions sorted according to the default sorting option
When I select a particular promotion for a hotel
And I attempt to book that promotion
Then I should be able to see hotel rates for my hotel on the Select Hotel Rate page