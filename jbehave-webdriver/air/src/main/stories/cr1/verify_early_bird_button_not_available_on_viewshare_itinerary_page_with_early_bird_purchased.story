Verify view itinerary page with EB purchased does not show EB button

Meta:
@project cr1
@airTranOnly
@flow air
@process view
@user anonymous
@traveler adult
@storyId DCAIR6397 ZR-891
@project_in_dev
@not_passing dev triaged

Narrative:
As a customer
I want to see the View/Share itinerary page with EB purchased
So that I can verify Early Bird upsell button is not on itinerary page

Scenario: EB button does not appear in view itinerary page after EB purchased

Given I have markets and flights available for <itineraryType>
When I book a flight using Early Bird
And I retrieve my itinerary
Then I should not see the Early Bird upsell button

Examples:
|itineraryType|
|Southwest-Southwest-RoundTrip|
|AirTran-Southwest-RoundTrip|