Book a Round trip flight with cc that is less than 24 hours out and verify boarding pass.

Meta:
@author Remya
@process Checkin
@traveler adult
@storyId 10709
@user anonymous


Narrative:
As an Anonymous user
I want to book a Round trip flight using Credit card  that is less than 24 hours and verify the Drink Coupon in BP.

GivenStories:
A_initialPage/HomePage.story


Scenario: Book a Round trip flight with CC that is less than 24 hours and verify the BP.


Given I want to book a roundtrip flight
And a departure within 24 hours
And The departure city is DAL
And The arrival city is HOU
And The outbound carrier type is WN
And The inbound carrier type is WN
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
And select businessselect fare for outbound
And select businessselect fare for inbound
And The outbound trip type is non stop
And The inbound trip type is non stop
When I select fare in dollars
And I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary
When I click on the check in link from the quick air links
And I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view and verify my boarding pass