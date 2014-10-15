Verify EB indicator on view/share itinerary page with EB purchased

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
So that I can verify EB indicator on View/Share itinerary page

Scenario: Verify EB indicator on View Itinerary Page

Given I am flying a round-trip SouthwestCodeshare AirTran flight
When I book a flight using Early Bird
And I retrieve my itinerary
Then I see the EB indicator
