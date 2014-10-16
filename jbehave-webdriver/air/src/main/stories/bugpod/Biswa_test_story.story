Oneway Revenue Booking for Checkin

Narrative:
As an Anonymous User
I want to book a ticket and checkin

GivenStories:
A_initialPage/HomePage.story

Scenario: Oneway Revenue Booking for Checkin
!--Given I login as rrmember

Given I want to book a oneway flight
And The departure city is SJU
And The arrival city is ATL
And Book the ticket for 1 passenger with passenger type passengerofsize
And Book the ticket using fare in dollars
And The flight is eligible for check in
And The outbound carrier type is wn
And The inbound carrier type is wn
And I search the flight
And select anytime fare for outbound
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
When I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my security document
