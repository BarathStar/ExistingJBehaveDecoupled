Verify redirect to Early Bird direct purchase path

Meta:
@project cr1
@flow air
@process view
@user anonymous
@traveler adult
@storyId DCAIR6397 ZR-891
@project_in_dev
@not_passing dev triaged

Narrative:
As a customer
I want to retrieve itinerary on the Early Bird check-in page
So that I can verify Early Bird check-in eligibility

Scenario: Early Bird Direct Check-in Purchase

Given I am flying a one-way CodeShare flight with a 1 stop segment
When I book a flight using Early Bird
And I am on the early bird page
And I retrieve itinerary on the Early Bird check-in page
Then I see all non eligible flights
And I see the non eligible message
