RoundTrip Revenue Booking for Multi Pax Checkin

Narrative:
As an Anonymous User  
I want to checkin for Multi Pax

GivenStories:
A_initialPage/HomePage.story

Scenario: RoundTrip Revenue Booking for Multi Pax Checkin

Given I want to book a roundtrip flight
And The departure city is DAL
And The arrival city is ELP
And Book the ticket for 4 passenger with passenger type adult
And The flight is eligible for check in
And The outbound carrier type is wn
And The inbound carrier type is wn
And I search the flight
And select anytime fare for outbound
And select anytime fare for inbound
When select fare in dollars
And I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary
When I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass



