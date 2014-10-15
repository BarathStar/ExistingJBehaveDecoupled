Verify that removing a flight from the shopping cart after narrowing the hotel search will display the select hotel page.

Meta:
@flow hotel
@process booking
@user anonymous
@passenger adult
@dyna_stubs

Narrative:
As an adult
In order to purchase an hotel booking after removing a flight, I want to verify that the select hotel page is visible.
So that

Scenario: Verify that removing a flight from the shopping cart after narrowing the hotel search will display the select hotel page.

Given I am flying a one-way Southwest flight
And I have the following hotel itinerary data:
        |Field|Value|
        |destination|HOU|
        |checkInDate|+1|
        |checkOutDate|+2|
When I search and select my flight
And I click continue to the Price page
And I navigate to the hotel search page
And I search for Hotels
And I select Hotel Area 3
And I remove flight products from cart
Then I get the Select Hotel page without errors
