Meta:
@bugpodCoreRegression
@flow air
@process booking

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story

Scenario: Book a Round trip flight with any time fare that is within next 24 hours out


Given I want to book a roundtrip flight
And The flight is eligible for check in
And The departure city is DAL
And The arrival city is HOU
And The outbound fare type is anytime
And The inbound fare type is anytime
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
When I click on the Check in button on the manage reservation page
And I click on the Continue button if I am in the Check in and Print BP options page
When I click on the Continue button on the Boarding Pass Options page
Then I verify the physical print copy

