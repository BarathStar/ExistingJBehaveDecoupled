Verify a Select Flight page is returned and the Fares are displayed in Dollars and promocode is WCMTEST.

Meta:
@author Thunderbirds
@flow air
@process Select Flight page is returned and the Fares are displayed in Dollars with promocode WCMTEST.
@traveler adult
@storyId 35857

Narrative:
As an Anonymous user
I want to verify a Select Flight page is returned.
So that I can view the select flight page with the correct Fares in Dollars and promocode is WCMTEST.


Scenario: Verify a select Flight page is returned and the Fares are displayed in Dollars.


Given I am on the Home page
When I search a flight with given url /html/app_test/booking_widgets/air_ctd_test_03.html
Then I view the customized booking widget
Then I verify my complete itinerary on the customized booking widget
Then The trip type is round trip
Then The departure city is empty
Then The arrival city is empty
Then The depart date is empty
Then The arrival date is empty
Then The adult is 1
Then The seniors is 0
Then The promocode is empty
Then The booking widget displayed as expected
When I select the travel date and I click on search.
Then I view select flight page
Then The fares in dollars
Then The trip type is round trip
Then The departure city is HOU
Then The arrival city is LAS
Then The promocode is WCMTEST