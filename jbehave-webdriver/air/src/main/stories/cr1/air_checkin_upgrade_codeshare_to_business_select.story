Verify that upgrade is NOT available on checkin on the Select Flights to Upgrade page

Meta:
@project cr1
@airTranOnly
@flow air
@process booking
@traveler adult
@storyId DCQA25
@message dyna_stubs not working due to missing support of multi-segments in one OND


Narrative:
Verify that upgrade to business select is available for a code share itinerary.


Scenario: Check in for a WN-FL Itinerary with an attempt to upgrade to Business Selection

Given I am flying a Anytime one-way WNFLCodeShare flight
When I book a flight eligible for checkin 1 adult
And I retrieve travel documents
And I click on upgrade to business select
Then I verify that business select could be available
When I click on Continue button
Then I see Business Select product available
