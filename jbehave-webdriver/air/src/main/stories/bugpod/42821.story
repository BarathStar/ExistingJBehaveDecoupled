Book OW revenue PNR Business select for an Adult and get a boarding pass

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user RR
@traveler adult
@storyId MH-42821

Narrative:
As an RR user
I want to get boarding pass

GivenStories:
A_initialPage/HomePage.story,
A_loginStories/RRMember.story

Scenario: Book OW revenue PNR Business select for an Adult and get a boarding pass

Given I want to book a oneway flight
And The flight is eligible for check in
And The departure city is ATL
And The arrival city is LAX
And The outbound carrier type is WN
And The outbound fare type is anytime
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
When I click on the Check in button on the Confirmation Page
And I click checkin to create a boarding pass
And I click on the Continue button if I am in the MBP options page
Then I view my boarding pass