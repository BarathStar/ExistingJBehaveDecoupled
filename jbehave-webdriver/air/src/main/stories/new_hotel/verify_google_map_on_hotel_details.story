Verify that the Hotels map is displayed on the hotel details page

Meta:
@flow hotel
@process booking
@user anonymous
@passenger adult
@live
@storyId PODIV-1373
@project FF_13.2

Narrative:
As an adult
I want to view my Hotel on a map
So that I can see where my Hotel is located

Scenario: Verify that the Hotels map is displayed on the hotel details page

Given I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |checkInDate|+2|
    |checkOutDate|+4|

And I am on the hotel search page
When I search for Hotels
And I select the first Hotel
And I click on the Map tab
Then I should see the Hotel Map
When I click on the Hotel marker
Then I should see the Info Window