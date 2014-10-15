Change existing adult Altea flight itinerary

Meta:
@project coda
@flow air
@process change
@user anonymous
@traveler adult
@dyna_stubs
@not_live
@project_in_dev
@storyid PODIV-1389

Narrative: In order to modify my Altea air only itinerary
As an adult with an Altea itinerary
I want to enter the Altea change flow for that itinerary

Scenario: Redirect Change Reservation for upcoming Altea flight

Given I am flying a round-trip Southwest flight with Altea
And I have a flight booked
When I attempt to change my flight itinerary
Then I am redirected to the Altea Change Reservation page
