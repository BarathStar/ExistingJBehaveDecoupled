Anonymous user purchase air refresh page while confirmation is processing

Meta:
@flow air
@bugpodCoreRegression
@process booking
@user anonymous
@traveler adult
@storyId 11737


Narrative:
I want to verify the billing page for any duplicate charges when i press refresh button

GivenStories:
A_initialPage/HomePage.story

Scenario: Verify the purchase page when the refresh button is pressed from the air purchase Confirmation page

Given I want to book a roundtrip flight
And The flight is eligible for check in
And The departure city is DAL
And The arrival city is MDW
And The outbound carrier type is WN
And The inbound carrier type is WN
And The outbound fare type is anytime
And The inbound fare type is anytime
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
