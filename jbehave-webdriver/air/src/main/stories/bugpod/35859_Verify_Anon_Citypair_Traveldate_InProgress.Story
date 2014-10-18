Verify a flight page on southwest.com and check city pair and travel dates displayed correctly.

Narrative:
As an Anonymous user
I want to search a flight on southwest.com with given url link
So that I can view the list of flights with the correct city pairing and travel dates.

Meta:
@author Thunderbirds
@flow air
@process Search a flight & check travel date displayed with correct city pair.
@traveler adult
@storyId 35859


Scenario: Search a flight and get the date and city pair details.


Given I am on the Home page
When I search a flight with given url http://southwest-ftst-b.swacorp.com/html/app_test/booking_widgets/air_ctd_test_05.html
And I view the customized booking widget
When I verify my complete itinerary on the customized booking widget
And The trip type is round trip
And The departure city is SJU
And The arrival city is FLL
And The adult is 1
And The seniors is 0
When I select the travel date
And I click on search.
And I view list of flights
Then I verify flights with correct city pairing and travel dates.


