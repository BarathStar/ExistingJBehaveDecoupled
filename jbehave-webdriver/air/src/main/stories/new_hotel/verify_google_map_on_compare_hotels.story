Verify that the Hotels map is displayed on the compare hotels page

Meta:
@flow hotel
@process booking
@user anonymous
@passenger adult
@live
@storyId PODIV-1384
@project FF_13.2

Narrative:
As an adult
I want to view the Hotels on a map
So that I can see where my hotel is located

Scenario: Verify that the Hotels map is displayed on the compare hotels page

Given I have the following hotel itinerary data:
    |Field|Value|
    |destination|DAL|
    |checkInDate|+2|
    |checkOutDate|+4|

And I am on the hotel search page
When I search for Hotels
And I check Hotel number 1
And I check Hotel number 2
And I click on the Compare Hotels button
And I click on View Hotels on a Map
Then I should see the Hotel Compare Map
When I click on Hotel marker 2
Then I should see the Hotel Compare Info Window
