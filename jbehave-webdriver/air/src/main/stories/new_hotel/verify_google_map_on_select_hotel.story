Verify that the Hotels map is displayed on the hotel search page

Meta:
@flow hotel
@process booking
@user anonymous
@passenger adult
@live
@storyId PODIV-1372
@project FF_13.2

Narrative:
As an adult
I want to view the Hotels on a map
So that I can see where my hotel is located

Scenario: Verify that the Hotels map is displayed on the hotel search page

Given I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |checkInDate|+2|
    |checkOutDate|+4|

And I am on the hotel search page
When I search for Hotels
When I click on the View Map image
Then I should see the Hotel Map
When I click on the Street View link
Then I should see the Street View