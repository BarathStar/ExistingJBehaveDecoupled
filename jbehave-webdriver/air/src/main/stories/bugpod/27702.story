RoundTrip Revenue Booking and checkin for Age Verified Senior and Senior

Narrative:
As an RR Member 
I want to Book and checkin a RoundTrip flight for Age Verified Senior

GivenStories:
A_initialPage/HomePage.story

Scenario: RoundTrip Revenue Booking and checkin for Age Verified Senior and Senior

Given I login as ageverifiedsenior
And I want to book a roundtrip flight
And The departure city is DAL
And The arrival city is ELP
And Book the ticket for 2 passenger with passenger type senior
And The flight is eligible for check in
And The outbound carrier type is wn
And The inbound carrier type is wn
And I search the flight
And The outbound trip type is direct
And The inbound trip type is direct
And select senior fare for outbound
And select senior fare for inbound
When select fare in Dollars
And I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary
Given I am on the Home page
When I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
And I click checkin to create a boarding pass
Then I view my senior boarding pass
And I view my security document