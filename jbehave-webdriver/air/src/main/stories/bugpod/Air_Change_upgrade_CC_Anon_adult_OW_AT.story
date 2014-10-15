Upgrade change for anonymous adult for a OW nonstop flight from anytime to Business select fare

Meta:
@bugpodCoreRegression
@flow air
@process change
@user anonymous
@traveler adult

GivenStories:
bugpod/Air_Booking_CC_Anon_adult_OW_AT_NS.story

Scenario: A anonymous user upgrade change

Given I am on the Home page
And I go to the change air reservation page
And I select the flight to change and continue
And I search for a new flight on the next day with Business Select
And I select a new flight
And I click Continue to the Reconcile page
When I fill the reconcile page and continue
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary