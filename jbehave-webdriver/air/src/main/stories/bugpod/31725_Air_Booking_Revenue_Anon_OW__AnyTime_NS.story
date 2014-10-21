FareBreakdown_Oneway_nonstop_SJU-FLL_ItineraryChanged_BookingFlow

Meta:
@bugpodCoreRegression
@flow air
@process booking
@user anonymous
@traveler adult
@team Thunderbirds
@TestcaseId 31725
@storyId SBD-89

Narrative:
As an Anonymous User
I want to book a onw way non stop flight with Business Select fare

GivenStories:
A_initialPage/HomePage.story

Scenario: An Anonymous user book a PNR with Any time fare

Given I want to book a One Way flight
And The departure city is SJU
And The arrival city is FLL
And The outbound trip type is NonStop
And The outbound fare type is Any Time
And Book the ticket for 1 passenger with passenger type adult
And I search the flight
When I select the flight
Then I view my price details
When I purchase the ticket
Then I should see the confirmation page
When I retrieve my itinerary details
Then I view my itinerary