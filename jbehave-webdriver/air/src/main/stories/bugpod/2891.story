Book a Round trip flight with points that is more than 24 hours out.
 
Meta:
@author Dhanya
@process Checkin
@traveler adult
@storyId 2891
 
Narrative:
As a RR member
I want to book a Round trip flight using BS points that is more than 24 hours out.
 
GivenStories:
A_initialPage/HomePage.story,A_loginStories/RRMember.story

 
 
Scenario: Book a Round trip flight with points that is more than 24 hours out.
 
 
Given I want to book a roundtrip flight
And The departure city is ATL
And The arrival city is LAX
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
When I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
Then I see an Oops messages from check in page indicating that The request to check in and print your Boarding Pass is more than 24 hours prior to your scheduled departure