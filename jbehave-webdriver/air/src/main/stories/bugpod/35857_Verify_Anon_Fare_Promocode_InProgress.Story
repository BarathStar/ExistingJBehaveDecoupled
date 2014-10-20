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

GivenStories:
A_initialPage/HomePage.story

Scenario: Verify a select Flight page is returned and the Fares are displayed in Dollars.


Given I am on the Home page
When I search a flight with given url http://southwest-ftst-b.swacorp.com/html/app_test/booking_widgets/air_ctd_test_03.html
And I view the customized booking widget
When I verify my complete itinerary on the customized booking widget
And The trip type is round trip
And The departure city is empty
And The arrival city is empty
And The depart date is empty
And The drrival date is empty
And The adult is 1
And The seniors is 0
And The promocode is empty
And The booking widget displayed as expected
When I select the travel date
And I click on search.
Then I view select flight page
And The fares in dollars
And The trip type is round trip
And The departure city is HOU
And The arrival city is LAS
And The promocode is WCMTEST

