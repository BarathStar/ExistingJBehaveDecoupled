RoundTrip Anytime Dollar Booking for Inhibited PAX Checkin
Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@storyId MH-9858

Narrative:
As an Anonymous User  
I want to Book a ticket and checkin for Inhibited User

GivenStories:
A_initialPage/HomePage.story

Scenario: RoundTrip Anytime Dollar Booking for Inhibited PAX Checkin

Given I want to book a RoundTrip flight
And The flight is eligible for check in
And The departure city is ATL
And The arrival city is LAX
And The outbound carrier type is WN
And The inbound carrier type is WN
And The outbound trip type is non stop
And The inbound trip type is non stop
And The outbound fare type is  anytime
And The inbound fare type is anytime
And Book the ticket for 1 passenger with passenger type inhibited
And I search the flight
When I select the flight
Then I view my price details
When I purchase the ticket
Then I view my complete itinerary on the confirmation page
When I retrieve my itinerary details
Then I view my itinerary details
