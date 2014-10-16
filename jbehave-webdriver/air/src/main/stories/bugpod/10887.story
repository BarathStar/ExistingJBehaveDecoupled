Validate special conditions on the BP for Customer Manual WC and POC

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-10887


Narrative:
As an Anonymous user
I want to get a boarding pass with special condition as manual  Wheelchair CWC and POC

GivenStories:
A_initialPage/HomePage.story

Scenario: Validate special conditions on the BP for Customer Manual WC and POC

Given I want to book a roundtrip flight
And The flight is eligible for check in
And The departure city is ATL
And The arrival city is LAX
And The outbound carrier type is WN
And The inbound carrier type is WN
And The outbound trip type is non stop
And The inbound trip type is non stop
And The outbound fare type is anytime
And The inbound fare type is anytime
And Book the ticket for 1 passenger with passenger type disabled
And I search the flight
When I select the flight
Then I view my price details
When I choose the disabilities option for 1 passenger
And I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
When I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass



