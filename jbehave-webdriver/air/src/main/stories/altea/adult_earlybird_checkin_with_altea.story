Add early bird to adult Altea flight itinerary

Meta:
@project coda
@flow air
@process booking
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1409

Narrative:
In order to board the plane early
As an adult
I want to add EarlyBird to my Altea reservation

Scenario: Altea Air Booking and add early bird

Given I am flying a round-trip Southwest flight with Altea
When I have a flight booked
And I am on the early bird page
And I try to retrieve itinerary on the Early Bird check-in page
Then I am redirected to the Altea EarlyBird page
