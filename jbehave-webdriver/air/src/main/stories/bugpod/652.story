Checkin For Age Verified Seniors with Maximum and Minimum Age limits

Narrative:
As an RR Member 
I want to checkin for Age Verified Seniors

GivenStories:
A_initialPage/HomePage.story

Scenario: Checkin For Age Verified Seniors with Maximum and Minimum Age limits

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
When I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass
When I Click Print Preview
Then I should see the Preview of Boarding Pass Print