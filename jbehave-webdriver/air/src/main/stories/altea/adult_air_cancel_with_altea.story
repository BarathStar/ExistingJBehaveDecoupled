Cancel existing adult Altea flight itinerary

Meta:
@project coda
@flow air
@process cancel
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1391

Narrative: In order to cancel my Altea air only itinerary
As an adult with an Altea itinerary
I want to enter the Altea cancel flow for that itinerary

Scenario: Redirect Cancel Reservation for upcoming Altea flight

Given I am flying a round-trip Southwest flight with Altea
And I have a flight booked
When I attempt to cancel the flight
Then I am redirected to the Altea Cancel Page
