Oneway Revenue Booking for Checkin

Narrative:
As an Anonymous User  
I want to book a ticket and checkin

Scenario: Oneway Revenue Booking for Checkin

Given I login as rrmember
And I want to book a oneway flight
And The departure city is SJU
And The arrival city is ATL
And book the ticket for 1 passenger with passenger type passengerofsize
And search fare in dollars
And The flight is eligible for check in
And The outbound carrier type is wn
And The inbound carrier type is wn
And I search the flight
And select anytime fare for outbound
And select anytime fare for inbound
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my  itinerary on the confirmation page
When I retrieve my itinerary
Then I view my itinerary
