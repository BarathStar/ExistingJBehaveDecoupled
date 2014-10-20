MBP as a Anonymous and get boarding pass

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-19662

Narrative:
As an Anonymous user
I want to get boarding pass

GivenStories:
A_initialPage/HomePage.story

Scenario: Cancel reservation with boarding pass

Given I want to book a oneway flight
And The flight is eligible for check in
And The departure city is ATL
And The arrival city is LAX
And The outbound carrier type is WN
And The outbound trip type is non stop
And The outbound fare type is anytime
And Book the ticket for 2 passenger with passenger type adult
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
