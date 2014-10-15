Air change with an upgrade from AT to BS and 1 plane change

Meta:
@bugpodCoreRegression
@flow air
@process change
@user anonymous
@traveler adult
@storyId MH-1031

Narrative:
As a user
I want to upgrade plane air ticket from check in

GivenStories:
bugpod/Air_Booking_CC_Anon_adult_checkin_eligible_OW_AT_1PC.story

Scenario: Adult passenger upgrade from check in
Given I am on the Home page
And I go to the Checkin online page through the Air menu
And I attempt to view the checkin details for my flight
And I choose to upgrade to business select
And I click on Continue button on the select flight to upgrade
And I click Continue to the Reconcile page
When I fill the reconcile page and continue
Then I see the itinerary changed page
When I retrieve my itinerary
Then I view my itinerary