FareBreakdown_Oneway_nonstop_SJU-FLL_ItineraryChanged_ChangeFlow.

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
I want to change the reservation to different date of travel with Business Select fare

GivenStories:
bugpod/31725_Air_Booking_Revenue_Anon_OW__AnyTime_NS.story

Scenario: An Anonymous user change the reservation to different date with Business Select fare

Given I am on the Home page
And I go to the change air reservation section from home page
And I select the flight to change
And I search for a new flight on the next day
And I select a new flight
And I click Continue to the Reconcile page
When I fill the reconcile page and continue
Then I see the itinerary changed page