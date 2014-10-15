RR member One Way air change for One Adult for a Non Stop Flight with Anytime Fare.

Meta:
@bugpodCoreRegression
@flow air
@process change
@user rr_member
@traveler adult

GivenStories:
bugpod/Air_Booking_CC_RR_adult_OW_AT_NS.story

Scenario: A RR member change the flight

Given I am on the Home page
And I go to the change air reservation page
And I select the flight to change and continue
And I search for a new flight on the next day
And I select a new flight
And I click Continue to the Reconcile page
When I fill the reconcile page and continue
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary

